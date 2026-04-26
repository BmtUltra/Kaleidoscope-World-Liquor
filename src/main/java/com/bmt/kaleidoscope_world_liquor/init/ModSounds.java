package com.bmt.kaleidoscope_world_liquor.init;

import com.bmt.kaleidoscope_world_liquor.KaleidoscopeWorldLiquor;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, KaleidoscopeWorldLiquor.MODID);

    public static final RegistryObject<SoundEvent> ICE_TEA_EAT =
            SOUND_EVENTS.register("ice_tea_eat", () ->
                    SoundEvent.createVariableRangeEvent(KaleidoscopeWorldLiquor.id("ice_tea_eat")));

    public static final RegistryObject<SoundEvent> COOL_ICE_TEA_DRINK =
            SOUND_EVENTS.register("cool_ice_tea_drink", () ->
                    SoundEvent.createVariableRangeEvent(KaleidoscopeWorldLiquor.id("cool_ice_tea_drink")));
}