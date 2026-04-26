package com.bmt.kaleidoscope_world_liquor;

import com.bmt.kaleidoscope_world_liquor.init.*;
import com.bmt.kaleidoscope_world_liquor.init.kaleidoscope_twilight.KTItems;
import com.bmt.kaleidoscope_world_liquor.init.smc.SMCItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(KaleidoscopeWorldLiquor.MODID)
@SuppressWarnings("all")
public class KaleidoscopeWorldLiquor {
    public static final String MODID = "kaleidoscope_world_liquor";
    public KaleidoscopeWorldLiquor(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        ModFluidTypes.FLUID_TYPES.register(modEventBus);
        ModFluids.FLUIDS.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModPaintings.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        SMCItems.register(modEventBus);
        ModEffects.register(modEventBus);
        if (!ModList.get().isLoaded("kaleidoscope_twilight")) {
            KTItems.register(modEventBus);
        }
        MinecraftForge.EVENT_BUS.register(this);
    }
    public static ResourceLocation id(String name) {
        return ResourceLocation.tryBuild(MODID, name);
    }
}