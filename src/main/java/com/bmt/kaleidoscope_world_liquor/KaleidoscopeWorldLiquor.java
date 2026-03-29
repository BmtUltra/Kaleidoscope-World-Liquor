package com.bmt.kaleidoscope_world_liquor;

import com.bmt.kaleidoscope_world_liquor.init.*;
import com.bmt.kaleidoscope_world_liquor.init.register.IceTeaRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(KaleidoscopeWorldLiquor.MODID)
public class KaleidoscopeWorldLiquor
{
    public static final String MODID = "kaleidoscope_world_liquor";
    public KaleidoscopeWorldLiquor(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModPaintings.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);
        IceTeaRegistry.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
    public static ResourceLocation id(String name) {
        return ResourceLocation.tryBuild(MODID, name);
    }
}
