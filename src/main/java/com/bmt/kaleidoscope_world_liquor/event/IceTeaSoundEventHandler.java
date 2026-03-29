package com.bmt.kaleidoscope_world_liquor.event;

import com.bmt.kaleidoscope_world_liquor.KaleidoscopeWorldLiquor;
import com.bmt.kaleidoscope_world_liquor.init.ModSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = KaleidoscopeWorldLiquor.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class IceTeaSoundEventHandler {
    
    private static final Map<UUID, Integer> drinkingTicksMap = new HashMap<>();

    @SubscribeEvent
    public static void onStartDrinking(LivingEntityUseItemEvent.Start event) {
        if (!(event.getEntity() instanceof Player player)) return;

        ItemStack itemStack = event.getItem();
        if (isIceTeaItem(itemStack)) {
            drinkingTicksMap.put(player.getUUID(), 0);
        }
    }

    @SubscribeEvent
    public static void onTickDrinking(LivingEntityUseItemEvent.Tick event) {
        if (!(event.getEntity() instanceof Player player)) return;

        ItemStack itemStack = event.getItem();
        if (!isIceTeaItem(itemStack)) return;

        UUID playerId = player.getUUID();
        if (drinkingTicksMap.containsKey(playerId)) {
            int ticks = drinkingTicksMap.get(playerId);
            drinkingTicksMap.put(playerId, ticks + 1);

            if (ticks % 10 == 0 && !player.level().isClientSide()) {
                player.level().playSound(
                        null,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        ModSounds.ICE_TEA_EAT.get(),
                        SoundSource.PLAYERS,
                        1.0F,
                        1.0F
                );
            }
        }
    }

    @SubscribeEvent
    public static void onFinishDrinking(LivingEntityUseItemEvent.Finish event) {
        if (!(event.getEntity() instanceof Player player)) return;

        ItemStack itemStack = event.getItem();
        if (!isIceTeaItem(itemStack)) return;

        UUID playerId = player.getUUID();
        drinkingTicksMap.remove(playerId);

        if (!player.level().isClientSide()) {
            player.level().playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    ModSounds.ICE_TEA_EAT.get(),
                    SoundSource.PLAYERS,
                    1.3F,
                    0.7F
            );
        }
    }

    @SubscribeEvent
    public static void onStopDrinking(LivingEntityUseItemEvent.Stop event) {
        if (!(event.getEntity() instanceof Player player)) return;

        ItemStack itemStack = event.getItem();
        if (!isIceTeaItem(itemStack)) return;

        UUID playerId = player.getUUID();
        drinkingTicksMap.remove(playerId);
    }

    private static boolean isIceTeaItem(ItemStack itemStack) {
        if (itemStack.isEmpty()) return false;

        String className = itemStack.getItem().getClass().getName();
        return className.equals("com.starmeow.smc.items.IceTea") ||
                itemStack.getItem().getDescriptionId().toLowerCase().contains("ice_tea");
    }
}