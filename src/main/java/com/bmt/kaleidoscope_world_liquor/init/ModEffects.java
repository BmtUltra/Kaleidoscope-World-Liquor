package com.bmt.kaleidoscope_world_liquor.init;

import com.bmt.kaleidoscope_world_liquor.KaleidoscopeWorldLiquor;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, KaleidoscopeWorldLiquor.MODID);

    public static final RegistryObject<MobEffect> TEQUILA_EFFECT = MOB_EFFECTS.register("tequila",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0xFFD700) {
                @Override
                public boolean isDurationEffectTick(int duration, int amplifier) {
                    return true;
                }
            });

    public static final RegistryObject<MobEffect> CAPTAIN_GIFT_EFFECT = MOB_EFFECTS.register("captain_gift",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0x1E90FF) {
                @Override
                public boolean isDurationEffectTick(int duration, int amplifier) {
                    return true;
                }
            });

    public static final RegistryObject<MobEffect> TREASURE_GUIDE_EFFECT = MOB_EFFECTS.register("treasure_guide",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0xFFD700) {
                @Override
                public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
                }

                @Override
                public boolean isDurationEffectTick(int duration, int amplifier) {
                    return false;
                }
            });

    public static final RegistryObject<MobEffect> MULTI_JUMP_EFFECT = MOB_EFFECTS.register("multi_jump",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0x00FF00) {
                @Override
                public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
                }

                @Override
                public boolean isDurationEffectTick(int duration, int amplifier) {
                    return false;
                }
            });

    public static final RegistryObject<MobEffect> REVERSE_GRAVITY = MOB_EFFECTS.register("reverse_gravity",
            () -> new MobEffect(MobEffectCategory.NEUTRAL, 0x00FFFF) {
                @Override
                public boolean isDurationEffectTick(int duration, int amplifier) {
                    return true;
                }
            });

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}