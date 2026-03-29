package com.bmt.kaleidoscope_world_liquor.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collection;
import java.util.Random;

@SuppressWarnings("all")
@Mod.EventBusSubscriber
public class TreasureGuideEffect extends MobEffect {

    private static final Random RANDOM = new Random();

    public TreasureGuideEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFFD700);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity attacker) {
            if (attacker.hasEffect(com.bmt.kaleidoscope_world_liquor.init.ModEffects.TREASURE_GUIDE_EFFECT.get())) {
                int amplifier = attacker.getEffect(com.bmt.kaleidoscope_world_liquor.init.ModEffects.TREASURE_GUIDE_EFFECT.get()).getAmplifier();

                double baseChance = 0.15;
                double extraChance = amplifier * 0.05;
                double totalChance = baseChance + extraChance;

                if (RANDOM.nextDouble() < totalChance) {
                    Collection<ItemEntity> dropEntities = event.getEntity().captureDrops();
                    if (!dropEntities.isEmpty()) {
                        for (ItemEntity itemEntity : dropEntities) {
                            ItemStack drop = itemEntity.getItem();
                            if (!drop.isEmpty()) {
                                ItemStack doubledDrop = drop.copy();
                                doubledDrop.setCount(drop.getCount() * 2);

                                ItemEntity newItemEntity = new ItemEntity(
                                        event.getEntity().level(),
                                        event.getEntity().getX(),
                                        event.getEntity().getY(),
                                        event.getEntity().getZ(),
                                        doubledDrop
                                );
                                event.getEntity().level().addFreshEntity(newItemEntity);
                            }
                        }
                    }
                }
            }
        }
    }
}