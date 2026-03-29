package com.bmt.kaleidoscope_world_liquor.init;

import com.bmt.kaleidoscope_world_liquor.KaleidoscopeWorldLiquor;
import com.bmt.kaleidoscope_world_liquor.effect.CaptainGiftEffect;
import com.bmt.kaleidoscope_world_liquor.effect.MultiJumpEffect;
import com.bmt.kaleidoscope_world_liquor.effect.TequilaEffect;
import com.bmt.kaleidoscope_world_liquor.effect.TreasureGuideEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, KaleidoscopeWorldLiquor.MODID);

    public static final RegistryObject<MobEffect> TEQUILA_EFFECT = MOB_EFFECTS.register("tequila",
            TequilaEffect::new);

    public static final RegistryObject<MobEffect> CAPTAIN_GIFT_EFFECT = MOB_EFFECTS.register("captain_gift",
            CaptainGiftEffect::new);

    public static final RegistryObject<MobEffect> TREASURE_GUIDE_EFFECT = MOB_EFFECTS.register("treasure_guide",
            TreasureGuideEffect::new);

    public static final RegistryObject<MobEffect> MULTI_JUMP_EFFECT = MOB_EFFECTS.register("multi_jump",
            MultiJumpEffect::new);

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}