package com.bmt.kaleidoscope_world_liquor.event;

import com.bmt.kaleidoscope_world_liquor.KaleidoscopeWorldLiquor;
import com.bmt.kaleidoscope_world_liquor.init.ModPaintings;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KaleidoscopeWorldLiquor.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeTabEventHandler {

    @SubscribeEvent
    public static void addPaintingsToTabs(BuildCreativeModeTabContentsEvent event) {
        ResourceLocation tavernDecoTab = ResourceLocation.tryBuild("kaleidoscope_tavern", "tavern_deco");
        if (event.getTabKey().location().equals(tavernDecoTab)) {
            var entries = event.getEntries();
            ItemStack anchorItem = null;

            for (var entry : entries) {
                if (entry.getKey().is(ModItems.MASTER_MARISA_PAINTING.get())) {
                    anchorItem = entry.getKey();
                    break;
                }
            }

            if (anchorItem != null) {
                entries.putAfter(anchorItem, new ItemStack(ModPaintings.BFXM_PAINTING_ITEM.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                entries.putAfter(anchorItem, new ItemStack(ModPaintings.BMT_PAINTING_ITEM.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                entries.putAfter(anchorItem, new ItemStack(ModPaintings.CHEN_PAINTING_ITEM.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                entries.putAfter(anchorItem, new ItemStack(ModPaintings.DREAM_PAINTING_ITEM.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                entries.putAfter(anchorItem, new ItemStack(ModPaintings.CHA_PAINTING_ITEM.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                entries.putAfter(anchorItem, new ItemStack(ModPaintings.RABBIT_PAINTING_ITEM.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                entries.putAfter(anchorItem, new ItemStack(ModPaintings.CH_PAINTING_ITEM.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                entries.putAfter(anchorItem, new ItemStack(ModPaintings.QXXY_PAINTING_ITEM.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            }
        }
    }
}