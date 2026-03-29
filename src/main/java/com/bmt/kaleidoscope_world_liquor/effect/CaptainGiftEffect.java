package com.bmt.kaleidoscope_world_liquor.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class CaptainGiftEffect extends MobEffect {
    public CaptainGiftEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x1E90FF);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}