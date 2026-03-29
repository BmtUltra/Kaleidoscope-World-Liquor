package com.bmt.kaleidoscope_world_liquor.init;

import com.bmt.kaleidoscope_world_liquor.KaleidoscopeWorldLiquor;
import com.github.ysbbbbbb.kaleidoscopetavern.item.DrinkBlockItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
