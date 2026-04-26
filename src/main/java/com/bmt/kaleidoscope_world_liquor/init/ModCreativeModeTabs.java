package com.bmt.kaleidoscope_world_liquor.init;

import com.bmt.kaleidoscope_world_liquor.KaleidoscopeWorldLiquor;
import com.bmt.kaleidoscope_world_liquor.init.kaleidoscope_twilight.KTItems;
import com.bmt.kaleidoscope_world_liquor.init.smc.SMCItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("all")
public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KaleidoscopeWorldLiquor.MODID);

    public static final RegistryObject<CreativeModeTab> KALEIDOSCOPE_WORLD_LIQUOR_TAB =
            CREATIVE_MODE_TABS.register("kaleidoscope_world_liquor_tab", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.BOMBAY_SAPPHIRE_GIN.get()))
                    .title(Component.translatable("itemGroup.kaleidoscope_world_liquor_tab"))
                    .displayItems((pParameters,output) -> {
                        output.accept(ModItems.BOMBAY_SAPPHIRE_GIN.get());
                        output.accept(ModItems.JACK_DANIEL.get());
                        output.accept(ModItems.SMIRNOFF_RED_VODKA.get());
                        output.accept(ModItems.ABSOLUT_VODKA.get());
                        output.accept(ModItems.PINA_COLADA.get());
                        output.accept(ModItems.MAOTAI.get());
                        output.accept(ModItems.BACARDI_CARTA_BLANCA.get());
                        output.accept(ModItems.SPIRYT_VODKA.get());
                        output.accept(ModItems.SKYY_VODKA.get());
                        output.accept(ModItems.JOHNNIE_WALKER.get());
                        output.accept(ModItems.LAFITE_1982.get());
                        output.accept(ModItems.STRONGBOW.get());
                        output.accept(ModItems.DASSAI.get());
                        output.accept(ModItems.KWAS_CHLEBOWY.get());
                        output.accept(ModItems.BAMBOO_LEAF_GREEN_LIQUOR.get());
                        output.accept(ModItems.COLA.get());
                        output.accept(ModItems.COOL_TEA.get());
                        output.accept(ModItems.BAR_STOOL_WHITE.get());
                        output.accept(ModItems.BAR_STOOL_LIGHT_GRAY.get());
                        output.accept(ModItems.BAR_STOOL_GRAY.get());
                        output.accept(ModItems.BAR_STOOL_BLACK.get());
                        output.accept(ModItems.BAR_STOOL_BROWN.get());
                        output.accept(ModItems.BAR_STOOL_RED.get());
                        output.accept(ModItems.BAR_STOOL_ORANGE.get());
                        output.accept(ModItems.BAR_STOOL_YELLOW.get());
                        output.accept(ModItems.BAR_STOOL_LIME.get());
                        output.accept(ModItems.BAR_STOOL_GREEN.get());
                        output.accept(ModItems.BAR_STOOL_CYAN.get());
                        output.accept(ModItems.BAR_STOOL_LIGHT_BLUE.get());
                        output.accept(ModItems.BAR_STOOL_BLUE.get());
                        output.accept(ModItems.BAR_STOOL_PURPLE.get());
                        output.accept(ModItems.BAR_STOOL_MAGENTA.get());
                        output.accept(ModItems.BAR_STOOL_PINK.get());
                        output.accept(ModItems.FREEZER.get());

                        if (!ModList.get().isLoaded("kaleidoscope_twilight")) {
                            output.accept(KTItems.LIANGSHAN_ICE_CONE.get());
                            output.accept(KTItems.KITA_STUFFED_CRISP.get());
                            output.accept(KTItems.POCHI_PUDDING.get());
                            output.accept(KTItems.MAGIC_CRISPY_CORNER.get());
                        }

                        if (!ModList.get().isLoaded(SMCItems.SMC_MODID)) {
                            output.accept(SMCItems.SMC_ICE_TEA_ITEM.get());
                        }
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
