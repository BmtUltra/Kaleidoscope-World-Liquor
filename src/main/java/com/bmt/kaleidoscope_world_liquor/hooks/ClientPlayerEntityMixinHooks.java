package com.bmt.kaleidoscope_world_liquor.hooks;

import com.bmt.kaleidoscope_world_liquor.init.ModEffects;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ClientPlayerEntityMixinHooks {

    private int multiJump$jumpCount = 0;
    private boolean multiJump$jumpedLastTick = false;

    public void tickMovement(LocalPlayer player) {
        if (!player.hasEffect(ModEffects.MULTI_JUMP_EFFECT.get())) {
            multiJump$jumpCount = 0;
            multiJump$jumpedLastTick = false;
            return;
        }

        var effect = player.getEffect(ModEffects.MULTI_JUMP_EFFECT.get());
        if (effect == null) return;

        int amplifier = effect.getAmplifier();
        int maxJumps = amplifier + 1;

        if (player.onGround() || player.onClimbable()) {
            multiJump$jumpCount = maxJumps;
        }

        if (canJump(player)) {
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

    private boolean wearingUsableElytra(LocalPlayer player) {
        ItemStack chestItemStack = player.getItemBySlot(EquipmentSlot.CHEST);
        return chestItemStack.getItem() == Items.ELYTRA && ElytraItem.isFlyEnabled(chestItemStack);
    }

    private boolean canJump(LocalPlayer player) {
        return !wearingUsableElytra(player) && !player.isFallFlying() && !player.isPassenger()
                && !player.isInWater() && !player.hasEffect(MobEffects.LEVITATION);
    }
}