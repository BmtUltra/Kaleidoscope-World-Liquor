package com.bmt.kaleidoscope_world_liquor.event;

import com.bmt.kaleidoscope_world_liquor.KaleidoscopeWorldLiquor;
import com.bmt.kaleidoscope_world_liquor.init.ModEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = KaleidoscopeWorldLiquor.MODID)
public class DamageEvents {

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity.hasEffect(ModEffects.TEQUILA_EFFECT.get())) {
            int amplifier = Objects.requireNonNull(entity.getEffect(ModEffects.TEQUILA_EFFECT.get())).getAmplifier();

            float maxDamagePercent = 0.40f - (amplifier * 0.05f);
            if (maxDamagePercent < 0.05f) {
                maxDamagePercent = 0.05f;
            }

            float maxHealth = entity.getMaxHealth();
            float maxAllowedDamage = maxHealth * maxDamagePercent;

            if (event.getAmount() > maxAllowedDamage) {
                event.setAmount(maxAllowedDamage);
            }
        }
    }
}