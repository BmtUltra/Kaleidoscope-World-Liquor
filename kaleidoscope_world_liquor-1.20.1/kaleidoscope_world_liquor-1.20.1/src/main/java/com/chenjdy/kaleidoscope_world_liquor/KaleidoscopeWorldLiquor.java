package com.chenjdy.kaleidoscope_world_liquor;

import com.chenjdy.kaleidoscope_world_liquor.init.ModBlocks;
import com.chenjdy.kaleidoscope_world_liquor.init.ModCreativeModeTabs;
import com.chenjdy.kaleidoscope_world_liquor.init.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(KaleidoscopeWorldLiquor.MODID)
public class KaleidoscopeWorldLiquor
{
    public static final String MODID = "kaleidoscope_world_liquor";
    public KaleidoscopeWorldLiquor(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

    }



    public static ResourceLocation id(String name) {
        return ResourceLocation.tryBuild(MODID, name);
    }
}
