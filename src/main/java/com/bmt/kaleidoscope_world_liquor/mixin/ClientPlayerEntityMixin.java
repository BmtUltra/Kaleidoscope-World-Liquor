package com.bmt.kaleidoscope_world_liquor.mixin;

import com.bmt.kaleidoscope_world_liquor.effect.MultiJumpEffect;
import com.bmt.kaleidoscope_world_liquor.init.ModEffects;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayer {
    @Unique
    private int multiJump$jumpCount = 0;
    @Unique
    private boolean multiJump$jumpedLastTick = false;

    public ClientPlayerEntityMixin(ClientLevel clientLevel, GameProfile gameProfile) {
        super(clientLevel, gameProfile);
    }

    @Inject(method = "aiStep", at = @At("HEAD"))
    private void multiJump$tickMovement(CallbackInfo info) {
        LocalPlayer player = (LocalPlayer) (Object) this;

        if (!player.hasEffect(ModEffects.MULTI_JUMP_EFFECT.get())) {
            multiJump$jumpCount = 0;
            multiJump$jumpedLastTick = false;
            return;
        }

        var effect = player.getEffect(ModEffects.MULTI_JUMP_EFFECT.get());
        if (effect == null) return;

        int amplifier = effect.getAmplifier();
        int maxJumps = MultiJumpEffect.getExtraJumps(amplifier);

        if (player.onGround() || player.onClimbable()) {
            multiJump$jumpCount = maxJumps;
        }

        if (multiJump$canJump(player)) {
            if (!player.onGround() && !multiJump$jumpedLastTick &&
                    multiJump$jumpCount > 0 && player.getDeltaMovement().y < 0) {

                if (player.input.jumping && !player.getAbilities().flying) {
                    multiJump$jumpCount--;

                    player.jumpFromGround();

                    player.fallDistance = 0.0F;

                    multiJump$jumpedLastTick = true;
                    return;
                }
            }
        }
        multiJump$jumpedLastTick = player.input.jumping;
    }

    @Unique
    private boolean multiJump$wearingUsableElytra(LocalPlayer player) {
        ItemStack chestItemStack = player.getItemBySlot(EquipmentSlot.CHEST);
        return chestItemStack.getItem() == Items.ELYTRA && ElytraItem.isFlyEnabled(chestItemStack);
    }

    @Unique
    private boolean multiJump$canJump(LocalPlayer player) {
        return !multiJump$wearingUsableElytra(player) && !player.isFallFlying() && !player.isPassenger()
                && !player.isInWater() && !player.hasEffect(MobEffects.LEVITATION);
    }
}