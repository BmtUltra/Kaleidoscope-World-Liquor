package com.bmt.kaleidoscope_world_liquor.init;

import com.github.ysbbbbbb.kaleidoscopetavern.KaleidoscopeTavern;
import com.github.ysbbbbbb.kaleidoscopetavern.block.deco.PaintingBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintings {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, KaleidoscopeTavern.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, KaleidoscopeTavern.MOD_ID);

    public static final RegistryObject<Block> BFXM_PAINTING = BLOCKS.register("bfxm_painting",
            PaintingBlock::new);

    public static final RegistryObject<Block> BMT_PAINTING = BLOCKS.register("bmt_painting",
            PaintingBlock::new);

    public static final RegistryObject<Block> DREAM_PAINTING = BLOCKS.register("dream_painting",
            PaintingBlock::new);

    public static final RegistryObject<Block> CHA_PAINTING = BLOCKS.register("cha_painting",
            PaintingBlock::new);

    public static final RegistryObject<Block> CHEN_PAINTING = BLOCKS.register("chen_painting",
            PaintingBlock::new);

    public static final RegistryObject<Block> RABBIT_PAINTING = BLOCKS.register("rabbit_painting",
            PaintingBlock::new);

    public static final RegistryObject<Block> CH_PAINTING = BLOCKS.register("ch_painting",
            PaintingBlock::new);

    public static final RegistryObject<Block> QXXY_PAINTING = BLOCKS.register("qxxy_painting",
            PaintingBlock::new);


    public static final RegistryObject<Item> BFXM_PAINTING_ITEM = ITEMS.register("bfxm_painting",
            () -> new BlockItem(BFXM_PAINTING.get(), new Item.Properties()));

    public static final RegistryObject<Item> BMT_PAINTING_ITEM = ITEMS.register("bmt_painting",
            () -> new BlockItem(BMT_PAINTING.get(), new Item.Properties()));

    public static final RegistryObject<Item> DREAM_PAINTING_ITEM = ITEMS.register("dream_painting",
            () -> new BlockItem(DREAM_PAINTING.get(), new Item.Properties()));

    public static final RegistryObject<Item> CHA_PAINTING_ITEM = ITEMS.register("cha_painting",
            () -> new BlockItem(CHA_PAINTING.get(), new Item.Properties()));

    public static final RegistryObject<Item> CHEN_PAINTING_ITEM = ITEMS.register("chen_painting",
            () -> new BlockItem(CHEN_PAINTING.get(), new Item.Properties()));

    public static final RegistryObject<Item> RABBIT_PAINTING_ITEM = ITEMS.register("rabbit_painting",
            () -> new BlockItem(RABBIT_PAINTING.get(), new Item.Properties()));

    public static final RegistryObject<Item> CH_PAINTING_ITEM = ITEMS.register("ch_painting",
            () -> new BlockItem(CH_PAINTING.get(), new Item.Properties()));

    public static final RegistryObject<Item> QXXY_PAINTING_ITEM = ITEMS.register("qxxy_painting",
            () -> new BlockItem(QXXY_PAINTING.get(), new Item.Properties()));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
    }
}