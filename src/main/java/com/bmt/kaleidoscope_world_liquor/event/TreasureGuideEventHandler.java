package com.bmt.kaleidoscope_world_liquor.event;

import com.bmt.kaleidoscope_world_liquor.KaleidoscopeWorldLiquor;
import com.bmt.kaleidoscope_world_liquor.init.ModEffects;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = KaleidoscopeWorldLiquor.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TreasureGuideEventHandler {
    
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
}