package com.bmt.kaleidoscope_world_liquor.init;

import com.bmt.kaleidoscope_world_liquor.KaleidoscopeWorldLiquor;
import com.github.ysbbbbbb.kaleidoscopetavern.item.DrinkBlockItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HoneyBottleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, KaleidoscopeWorldLiquor.MODID);

    // 孟买蓝宝石金酒
    public static final RegistryObject<Item> BOMBAY_SAPPHIRE_GIN = ITEMS.register("bombay_sapphire_gin",
            () -> new DrinkBlockItem(ModBlocks.BOMBAY_SAPPHIRE_GIN.get()));

    // 杰克丹尼
    public static final RegistryObject<Item> JACK_DANIEL = ITEMS.register("jack_daniel",
            () -> new DrinkBlockItem(ModBlocks.JACK_DANIEL.get()));

    // 斯米诺红牌伏特加
    public static final RegistryObject<Item> SMIRNOFF_RED_VODKA = ITEMS.register("smirnoff_red_vodka",
            () -> new DrinkBlockItem(ModBlocks.SMIRNOFF_RED_VODKA.get()));

    // 绝对伏特加
    public static final RegistryObject<Item> ABSOLUT_VODKA = ITEMS.register("absolut_vodka",
            () -> new DrinkBlockItem(ModBlocks.ABSOLUT_VODKA.get()));

    // 马利宝椰子朗姆酒
    public static final RegistryObject<Item> PINA_COLADA = ITEMS.register("pina_colada",
            () -> new DrinkBlockItem(ModBlocks.PINA_COLADA.get()));

    // 飞天茅台
    public static final RegistryObject<Item> MAOTAI = ITEMS.register("maotai",
            () -> new DrinkBlockItem(ModBlocks.MAOTAI.get()));

    // 百加得白朗姆
    public static final RegistryObject<Item> BACARDI_CARTA_BLANCA = ITEMS.register("bacardi_carta_blanca",
            () -> new DrinkBlockItem(ModBlocks.BACARDI_CARTA_BLANCA.get()));

    // 生命之水96
    public static final RegistryObject<Item> SPIRYT_VODKA = ITEMS.register("spiryt_vodka",
            () -> new DrinkBlockItem(ModBlocks.SPIRYT_VODKA.get()));

    // 深蓝伏特加
    public static final RegistryObject<Item> SKYY_VODKA = ITEMS.register("skyy_vodka",
            () -> new DrinkBlockItem(ModBlocks.SKYY_VODKA.get()));

    // 尊尼获加
    public static final RegistryObject<Item> JOHNNIE_WALKER = ITEMS.register("johnnie_walker",
            () -> new DrinkBlockItem(ModBlocks.JOHNNIE_WALKER.get()));

    // 拉菲
    public static final RegistryObject<Item> LAFITE_1982 = ITEMS.register("lafite_1982",
            () -> new DrinkBlockItem(ModBlocks.LAFITE_1982.get()));

    // 强弓经典苹果酒
    public static final RegistryObject<Item> STRONGBOW = ITEMS.register("strongbow",
            () -> new DrinkBlockItem(ModBlocks.STRONGBOW.get()));

    // 獺祭
    public static final RegistryObject<Item> DASSAI = ITEMS.register("dassai",
            () -> new DrinkBlockItem(ModBlocks.DASSAI.get()));

    // 格瓦斯
    public static final RegistryObject<Item> KWAS_CHLEBOWY = ITEMS.register("kwas_chlebowy",
            () -> new DrinkBlockItem(ModBlocks.KWAS_CHLEBOWY.get()));

    // 竹叶青
    public static final RegistryObject<Item> BAMBOO_LEAF_GREEN_LIQUOR = ITEMS.register("bamboo_leaf_green_liquor",
            () -> new DrinkBlockItem(ModBlocks.BAMBOO_LEAF_GREEN_LIQUOR.get()));
    // 凉冰红茶
    public static final RegistryObject<Item> COOL_TEA = ITEMS.register("cool_tea",
            () -> new DrinkBlockItem(ModBlocks.COOL_TEA.get()));

    // 可乐
    public static final RegistryObject<Item> COLA = ITEMS.register("cola",
            () -> new HoneyBottleItem(
                  new Item.Properties()
                          .stacksTo(1)
                          .craftRemainder(Items.GLASS_BOTTLE)
                          .food(new FoodProperties.Builder()
                                  .alwaysEat()
                                  .effect (() -> new MobEffectInstance(MobEffects.DIG_SPEED, 300, 0), 1.0F)
                                  .effect (() -> new MobEffectInstance (MobEffects.MOVEMENT_SPEED, 300, 0), 1.0F)
                                  .build()
                          )
            )
    );

    public static final RegistryObject<Item> BAR_STOOL_BLACK = ITEMS.register("bar_stool_black",
            () -> new BlockItem(ModBlocks.BAR_STOOL_BLACK.get(), new Item.Properties()));
    public static final RegistryObject<Item> BAR_STOOL_WHITE = ITEMS.register("bar_stool_white",
            () -> new BlockItem(ModBlocks.BAR_STOOL_WHITE.get(), new Item.Properties()));
    public static final RegistryObject<Item> BAR_STOOL_LIGHT_GRAY = ITEMS.register("bar_stool_light_gray",
            () -> new BlockItem(ModBlocks.BAR_STOOL_LIGHT_GRAY.get(), new Item.Properties()));
    public static final RegistryObject<Item> BAR_STOOL_GRAY = ITEMS.register("bar_stool_gray",
            () -> new BlockItem(ModBlocks.BAR_STOOL_GRAY.get(), new Item.Properties()));
    public static final RegistryObject<Item> BAR_STOOL_BROWN = ITEMS.register("bar_stool_brown",
            () -> new BlockItem(ModBlocks.BAR_STOOL_BROWN.get(), new Item.Properties()));
    public static final RegistryObject<Item> BAR_STOOL_RED = ITEMS.register("bar_stool_red",
            () -> new BlockItem(ModBlocks.BAR_STOOL_RED.get(), new Item.Properties()));
    public static final RegistryObject<Item> BAR_STOOL_ORANGE = ITEMS.register("bar_stool_orange",
            () -> new BlockItem(ModBlocks.BAR_STOOL_ORANGE.get(), new Item.Properties()));
    public static final RegistryObject<Item> BAR_STOOL_YELLOW = ITEMS.register("bar_stool_yellow",
            () -> new BlockItem(ModBlocks.BAR_STOOL_YELLOW.get(), new Item.Properties()));
    public static final RegistryObject<Item> BAR_STOOL_LIME = ITEMS.register("bar_stool_lime",
            () -> new BlockItem(ModBlocks.BAR_STOOL_LIME.get(), new Item.Properties()));
    public static final RegistryObject<Item> BAR_STOOL_GREEN = ITEMS.register("bar_stool_green",
            () -> new BlockItem(ModBlocks.BAR_STOOL_GREEN.get(), new Item.Properties()));
    public static final RegistryObject<Item> BAR_STOOL_CYAN = ITEMS.register("bar_stool_cyan",
            () -> new BlockItem(ModBlocks.BAR_STOOL_CYAN.get(), new Item.Properties()));
    public static final RegistryObject<Item> BAR_STOOL_LIGHT_BLUE = ITEMS.register("bar_stool_light_blue",
            () -> new BlockItem(ModBlocks.BAR_STOOL_LIGHT_BLUE.get(), new Item.Properties()));
    public static final RegistryObject<Item> BAR_STOOL_BLUE = ITEMS.register("bar_stool_blue",
            () -> new BlockItem(ModBlocks.BAR_STOOL_BLUE.get(), new Item.Properties()));
    public static final RegistryObject<Item> BAR_STOOL_PURPLE = ITEMS.register("bar_stool_purple",
            () -> new BlockItem(ModBlocks.BAR_STOOL_PURPLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> BAR_STOOL_MAGENTA = ITEMS.register("bar_stool_magenta",
            () -> new BlockItem(ModBlocks.BAR_STOOL_MAGENTA.get(), new Item.Properties()));
    public static final RegistryObject<Item> BAR_STOOL_PINK = ITEMS.register("bar_stool_pink",
            () -> new BlockItem(ModBlocks.BAR_STOOL_PINK.get(), new Item.Properties()));

    public static final RegistryObject<Item> FREEZER = ITEMS.register("freezer",
            () -> new BlockItem(ModBlocks.FREEZER.get(), new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
