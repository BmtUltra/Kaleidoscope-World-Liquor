package com.bmt.kaleidoscope_world_liquor.event;

import com.bmt.kaleidoscope_world_liquor.KaleidoscopeWorldLiquor;
import com.bmt.kaleidoscope_world_liquor.init.ModEffects;
import com.bmt.kaleidoscope_world_liquor.mixins.accessor.LivingEntityAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@Mod.EventBusSubscriber(modid = KaleidoscopeWorldLiquor.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandlers {

    private static final Random RANDOM = new Random();

    private static final double GRAVITY = 0.08D;
    private static final double JUMP_POWER = -0.32D;

    private static final Map<UUID, Boolean> PLAYER_IS_INVERTED = new HashMap<>();

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        LivingEntity target = event.getEntity();
        if (target.level().isClientSide()) return;

        if (event.getSource().getEntity() instanceof LivingEntity attacker) {
            if (attacker.hasEffect(ModEffects.TREASURE_GUIDE_EFFECT.get())) {
                int amplifier = Objects.requireNonNull(attacker.getEffect(ModEffects.TREASURE_GUIDE_EFFECT.get())).getAmplifier();

                double baseChance = 0.4;
                double extraChance = amplifier * 0.15;
                double totalChance = baseChance + extraChance;

                Collection<ItemEntity> drops = target.captureDrops();
                if (!drops.isEmpty()) {

                    for (ItemEntity itemEntity : drops) {
                        target.level().addFreshEntity(itemEntity);
                    }

                    if (RANDOM.nextDouble() < totalChance) {
                        for (ItemEntity itemEntity : drops) {
                            ItemStack stack = itemEntity.getItem();
                            if (!stack.isEmpty()) {
                                ItemStack extra = stack.copy();
                                ItemEntity extraEntity = new ItemEntity(
                                        target.level(),
                                        target.getX(),
                                        target.getY(),
                                        target.getZ(),
                                        extra
                                );
                                target.level().addFreshEntity(extraEntity);
                            }
                        }
                    }
                }
            }
        }
    }

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

    @SubscribeEvent
    public static void onExperiencePickup(PlayerXpEvent.PickupXp event) {
        Player player = event.getEntity();
        ExperienceOrb orb = event.getOrb();

        if (player.hasEffect(ModEffects.TREASURE_GUIDE_EFFECT.get())) {
            int amplifier = Objects.requireNonNull(player.getEffect(ModEffects.TREASURE_GUIDE_EFFECT.get())).getAmplifier();

            float baseBonus = 0.25f;
            float extraBonus = amplifier * 0.25f;
            float totalBonus = 1.0f + baseBonus + extraBonus;

            int originalValue = orb.getValue();
            orb.value = (int) (originalValue * totalBonus);
        }
    }

    @SubscribeEvent
    public static void onExperienceChange(PlayerXpEvent.XpChange event) {
        Player player = event.getEntity();

        if (player.hasEffect(ModEffects.TREASURE_GUIDE_EFFECT.get())) {
            int amplifier = Objects.requireNonNull(player.getEffect(ModEffects.TREASURE_GUIDE_EFFECT.get())).getAmplifier();

            float baseBonus = 0.25f;
            float extraBonus = amplifier * 0.25f;
            float totalBonus = 1.0f + baseBonus + extraBonus;

            int originalAmount = event.getAmount();
            int bonusAmount = (int) (originalAmount * totalBonus);
            event.setAmount(bonusAmount);
        }
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        boolean hasReverseGravity = player.hasEffect(ModEffects.REVERSE_GRAVITY.get());
        UUID uuid = player.getUUID();

        if (hasReverseGravity) {
            Vec3 motion = player.getDeltaMovement();
            player.setDeltaMovement(motion.x, motion.y + (GRAVITY * 2), motion.z);
            player.setOnGround(true);

            if (((LivingEntityAccessor)player).isJumping()) {
                player.setDeltaMovement(motion.x, JUMP_POWER, motion.z);
                ((LivingEntityAccessor)player).setJumping(false);
            }
        }

        if (player.level().isClientSide) {
            boolean alreadyInverted = PLAYER_IS_INVERTED.getOrDefault(uuid, false);

            if (hasReverseGravity && !alreadyInverted) {
                player.setXRot(-player.getXRot());
                PLAYER_IS_INVERTED.put(uuid, true);
            }

            if (!hasReverseGravity && alreadyInverted) {
                player.setXRot(-player.getXRot());
                PLAYER_IS_INVERTED.remove(uuid);
            }
        }
    }
}