package com.bmt.kaleidoscope_world_liquor.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

import static net.minecraft.world.effect.MobEffects.*;

public class ModFoods {
    //凉
    public static final FoodProperties LIANGSHAN_ICE_CONE = new FoodProperties.Builder()
            .nutrition(5).saturationMod(0.4f)
            .effect(() -> new MobEffectInstance(FIRE_RESISTANCE, 8 * 60 * 20), 1.0F)
            .effect(() -> new MobEffectInstance(MOVEMENT_SPEED, 8 * 60 * 20), 1.0F)
            .alwaysEat().build();
    //喜
    public static final FoodProperties KITA_STUFFED_CRISP = new FoodProperties.Builder()
            .nutrition(6).saturationMod(0.5f)
            .effect(() -> new MobEffectInstance(LUCK, 8 * 60 * 20), 1.0F)
            .effect(() -> new MobEffectInstance(SATURATION, 8 * 60 * 20), 1.0F)
            .alwaysEat().build();
    //波
    public static final FoodProperties POCHI_PUDDING = new FoodProperties.Builder()
            .nutrition(8).saturationMod(0.7f)
            .effect(() -> new MobEffectInstance(REGENERATION, 8 * 60 * 20), 1.0F)
            .effect(() -> new MobEffectInstance(LUCK, 8 * 60 * 20), 1.0F)
            .alwaysEat().build();
    //妙
    public static final FoodProperties MAGIC_CRISPY_CORNER = new FoodProperties.Builder()
            .nutrition(4).saturationMod(0.3f)
            .effect(() -> new MobEffectInstance(DIG_SPEED, 8 * 60 * 20), 1.0F)
            .alwaysEat().build();
}