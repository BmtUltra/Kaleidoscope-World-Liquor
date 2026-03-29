package com.bmt.kaleidoscope_world_liquor.event;

import com.bmt.kaleidoscope_world_liquor.KaleidoscopeWorldLiquor;
import com.bmt.kaleidoscope_world_liquor.init.ModEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Mod.EventBusSubscriber(modid = KaleidoscopeWorldLiquor.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CropHarvestEventHandler {

    private static final Random RANDOM = new Random();

    @SubscribeEvent
    public static void onBlockHarvest(BlockEvent.BreakEvent event) {
        if (event.getPlayer() != null && !event.getPlayer().level().isClientSide()) {
            Player player = event.getPlayer();
            BlockState state = event.getState();
            Block block = state.getBlock();

            boolean isCrop = block instanceof CropBlock;

            if (isCrop && player.hasEffect(ModEffects.TREASURE_GUIDE_EFFECT.get())) {
                int amplifier = Objects.requireNonNull(player.getEffect(ModEffects.TREASURE_GUIDE_EFFECT.get())).getAmplifier();

                double baseChance = 0.15;
                double extraChance = amplifier * 0.05;
                double totalChance = baseChance + extraChance;

                if (RANDOM.nextDouble() < totalChance) {
                    if (player.level() instanceof ServerLevel serverLevel) {
                        List<ItemStack> drops = Block.getDrops(state, serverLevel, event.getPos(), null, player, player.getMainHandItem());

                        for (ItemStack drop : drops) {
                            if (!drop.isEmpty()) {
                                ItemStack doubledDrop = drop.copy();
                                doubledDrop.setCount(drop.getCount() * 2);

                                ItemEntity itemEntity = new ItemEntity(
                                        player.level(),
                                        event.getPos().getX() + 0.5,
                                        event.getPos().getY() + 0.5,
                                        event.getPos().getZ() + 0.5,
                                        doubledDrop
                                );
                                player.level().addFreshEntity(itemEntity);
                            }
                        }
                    }
                }
            }
        }
    }
}