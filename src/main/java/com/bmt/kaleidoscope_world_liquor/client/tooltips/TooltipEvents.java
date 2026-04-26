package com.bmt.kaleidoscope_world_liquor.client.tooltips;

import com.bmt.kaleidoscope_world_liquor.KaleidoscopeWorldLiquor;
import com.bmt.kaleidoscope_world_liquor.init.kaleidoscope_twilight.KTItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KaleidoscopeWorldLiquor.MODID)
public class TooltipEvents {
    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        if (!ModList.get().isLoaded("kaleidoscope_twilight")) {
            return;
        }
        var stack = event.getItemStack();
        if (stack.is(KTItems.LIANGSHAN_ICE_CONE.get())) {
            event.getToolTip().add(Component.translatable("item.kaleidoscope_chinesefood.liangshan_ice_cone.tooltip").withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        }
        if (stack.is(KTItems.KITA_STUFFED_CRISP.get())) {
            event.getToolTip().add(Component.translatable("item.kaleidoscope_chinesefood.kita_stuffed_crisp.tooltip").withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        }
        if (stack.is(KTItems.POCHI_PUDDING.get())) {
            event.getToolTip().add(Component.translatable("item.kaleidoscope_chinesefood.pochi_pudding.tooltip").withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        }
        if (stack.is(KTItems.MAGIC_CRISPY_CORNER.get())) {
            event.getToolTip().add(Component.translatable("item.kaleidoscope_chinesefood.magic_crispy_corner.tooltip").withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        }
    }
}