package com.chenjdy.kaleidoscope_world_liquor.init;

import com.chenjdy.kaleidoscope_world_liquor.KaleidoscopeWorldLiquor;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KaleidoscopeWorldLiquor.MODID);

    public static final RegistryObject<CreativeModeTab> KALEIDOSCOPE_WORLD_LIQUOR_TAB =
            CREATIVE_MODE_TABS.register("kaleidoscope_world_liquor_tab", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.BOMBAY_SAPPHIRE_GIN.get()))
                    .title(Component.translatable("itemGroup.kaleidoscope_world_liquor_tab"))
                    .displayItems((pParameters,output) -> {
                        output.accept(ModItems.CORN_BUCKET.get());
                        output.accept(ModItems.SUGAR_CANE_BUCKET.get());

                        output.accept(ModItems.BOMBAY_SAPPHIRE_GIN.get());
                        output.accept(ModItems.JACK_DANIEL.get());
                        output.accept(ModItems.SMIRNOFF_RED_VODKA.get());
                        output.accept(ModItems.ABSOLUT_VODKA.get());
                        output.accept(ModItems.PINA_COLADA.get());
                        output.accept(ModItems.MAOTAI.get());
                        output.accept(ModItems.BACARDI_CARTA_BLANCA.get());
                        output.accept(ModItems.SPIRYT_VODKA.get());
                        output.accept(ModItems.SKYY_VODKA.get());
                        output.accept(ModItems.COLA.get());
                    })
                    .build());
}
