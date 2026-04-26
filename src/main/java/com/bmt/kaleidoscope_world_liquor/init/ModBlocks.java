package com.bmt.kaleidoscope_world_liquor.init;

import com.bmt.kaleidoscope_world_liquor.KaleidoscopeWorldLiquor;
import com.bmt.kaleidoscope_world_liquor.block.ChairBlock;
import com.bmt.kaleidoscope_world_liquor.block.FreezerBlock;
import com.bmt.kaleidoscope_world_liquor.block.entity.FreezerBlockEntity;
import com.github.ysbbbbbb.kaleidoscopetavern.block.brew.DrinkBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, KaleidoscopeWorldLiquor.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, KaleidoscopeWorldLiquor.MODID);

    // 孟买蓝宝石金酒
    public static final RegistryObject<Block> BOMBAY_SAPPHIRE_GIN = BLOCKS.register("bombay_sapphire_gin",
            () -> DrinkBlock.create()
                    .maxCount(4)
                    .shapes(
                            Block.box(5, 0, 5, 11, 16, 11),
                            Block.box(1, 0, 5, 15, 16, 11),
                            Shapes.or(
                                    Block.box(1, 0, 9, 15, 16, 15),
                                    Block.box(5, 0, 1, 11, 16, 15)
                            ),
                            Block.box(1, 0, 1, 15, 16, 15)
                    ).build().get());

    // 杰克丹尼
    public static final RegistryObject<Block> JACK_DANIEL = BLOCKS.register("jack_daniel",
            () -> DrinkBlock.create()
                    .maxCount(4)
                    .shapes(
                            Block.box(4.5, 0, 4.5, 11.5, 13, 11.5),
                            Block.box(0.5, 0, 4.5, 15.5, 13, 11.5),
                            Shapes.or(
                                    Block.box(0.5, 0, 8.5, 15.5, 13, 15.5),
                                    Block.box(4.5, 0, 0.5, 11.5, 13, 15.5)
                            ),
                            Block.box(0.5, 0, 0.5, 15.5, 13, 15.5)
                    ).build().get());

    // 斯米诺红牌伏特加
    public static final RegistryObject<Block> SMIRNOFF_RED_VODKA = BLOCKS.register("smirnoff_red_vodka",
            () -> DrinkBlock.create()
                    .maxCount(4)
                    .shapes(
                            Block.box(6, 0, 6, 10, 16, 10),
                            Block.box(2, 0, 6, 14, 16, 10),
                            Shapes.or(
                                    Block.box(2, 0, 10, 14, 16, 14),
                                    Block.box(6, 0, 2, 10, 16, 14)
                            ),
                            Block.box(2, 0, 2, 14, 16, 14)
                    ).build().get());

    // 绝对伏特加
    public static final RegistryObject<Block> ABSOLUT_VODKA = BLOCKS.register("absolut_vodka",
            () -> DrinkBlock.create()
                    .maxCount(4)
                    .shapes(
                            Block.box(5, 0, 5, 11, 14, 11),
                            Block.box(1, 0, 5, 15, 14, 11),
                            Shapes.or(
                                    Block.box(1, 0, 9, 15, 14, 15),
                                    Block.box(5, 0, 1, 11, 14, 15)
                            ),
                            Block.box(1, 0, 1, 15, 14, 15)
                    ).build().get());

    // 马利宝椰子朗姆酒
    public static final RegistryObject<Block> PINA_COLADA = BLOCKS.register("pina_colada",
            () -> DrinkBlock.create()
                    .maxCount(4)
                    .shapes(
                            Block.box(6, 0, 6, 10, 15, 10),
                            Block.box(2, 0, 6, 14, 15, 10),
                            Shapes.or(
                                    Block.box(2, 0, 10, 14, 15, 14),
                                    Block.box(6, 0, 2, 10, 15, 14)
                            ),
                            Block.box(2, 0, 2, 14, 15, 14)
                    ).build().get());

    // 飞天茅台
    public static final RegistryObject<Block> MAOTAI = BLOCKS.register("maotai",
            () -> DrinkBlock.create()
                    .maxCount(4)
                    .shapes(
                            Block.box(5, 0, 5, 11, 13, 11),
                            Block.box(1, 0, 5, 15, 13, 11),
                            Shapes.or(
                                    Block.box(1, 0, 9, 15, 13, 15),
                                    Block.box(5, 0, 1, 11, 13, 15)
                            ),
                            Block.box(1, 0, 1, 15, 13, 15)
                    ).build().get());

    // 百加得白朗姆
    public static final RegistryObject<Block> BACARDI_CARTA_BLANCA = BLOCKS.register("bacardi_carta_blanca",
            () -> DrinkBlock.create()
                    .maxCount(4)
                    .shapes(
                            Block.box(6, 0, 6, 10, 16, 10),
                            Block.box(2, 0, 6, 14, 16, 10),
                            Shapes.or(
                                    Block.box(2, 0, 10, 14, 16, 14),
                                    Block.box(6, 0, 2, 10, 16, 14)
                            ),
                            Block.box(2, 0, 2, 14, 16, 14)
                    ).build().get());

    // 生命之水96
    public static final RegistryObject<Block> SPIRYT_VODKA = BLOCKS.register("spiryt_vodka",
            () -> DrinkBlock.create()
                    .maxCount(4)
                    .shapes(
                            Block.box(6, 0, 6, 10, 16, 10),
                            Block.box(2, 0, 6, 14, 16, 10),
                            Shapes.or(
                                    Block.box(2, 0, 10, 14, 16, 14),
                                    Block.box(6, 0, 2, 10, 16, 14)
                            ),
                            Block.box(2, 0, 2, 14, 16, 14)
                    ).build().get());

    // 深蓝伏特加
    public static final RegistryObject<Block> SKYY_VODKA = BLOCKS.register("skyy_vodka",
            () -> DrinkBlock.create()
                    .maxCount(4)
                    .shapes(
                            Block.box(6, 0, 6, 10, 16, 10),
                            Block.box(2, 0, 6, 14, 16, 10),
                            Shapes.or(
                                    Block.box(2, 0, 10, 14, 16, 14),
                                    Block.box(6, 0, 2, 10, 16, 14)
                            ),
                            Block.box(2, 0, 2, 14, 16, 14)
                    ).build().get());

    // 尊尼获加
    public static final RegistryObject<Block> JOHNNIE_WALKER = BLOCKS.register("johnnie_walker",
            () -> DrinkBlock.create()
                    .maxCount(4)
                    .shapes(
                            Block.box(6, 0, 6, 10, 14, 10),
                            Block.box(2, 0, 6, 14, 14, 10),
                            Shapes.or(
                                    Block.box(2, 0, 10, 14, 14, 14),
                                    Block.box(6, 0, 2, 10, 14, 14)
                            ),
                            Block.box(2, 0, 2, 14, 14, 14)
                    ).build().get());

    // 拉菲
    public static final RegistryObject<Block> LAFITE_1982 = BLOCKS.register("lafite_1982",
            () -> DrinkBlock.create()
                    .maxCount(4)
                    .shapes(
                            Block.box(6, 0, 6, 10, 16, 10),
                            Block.box(2, 0, 6, 14, 16, 10),
                            Shapes.or(
                                    Block.box(2, 0, 10, 14, 16, 14),
                                    Block.box(6, 0, 2, 10, 16, 14)
                            ),
                            Block.box(2, 0, 2, 14, 16, 14)
                    ).build().get());

    // 强弓经典苹果酒
    public static final RegistryObject<Block> STRONGBOW = BLOCKS.register("strongbow",
            () -> DrinkBlock.create()
                    .maxCount(4)
                    .shapes(
                            Block.box(6, 0, 6, 10, 16, 10),
                            Block.box(2, 0, 6, 14, 16, 10),
                            Shapes.or(
                                    Block.box(2, 0, 10, 14, 16, 14),
                                    Block.box(6, 0, 2, 10, 16, 14)
                            ),
                            Block.box(2, 0, 2, 14, 16, 14)
                    ).build().get());

    // 獺祭
    public static final RegistryObject<Block> DASSAI = BLOCKS.register("dassai",
            () -> DrinkBlock.create()
                    .maxCount(4)
                    .shapes(
                            Block.box(6, 0, 6, 10, 16, 10),
                            Block.box(2, 0, 6, 14, 16, 10),
                            Shapes.or(
                                    Block.box(2, 0, 10, 14, 16, 14),
                                    Block.box(6, 0, 2, 10, 16, 14)
                            ),
                            Block.box(2, 0, 2, 14, 16, 14)
                    ).build().get());

    // 格瓦斯
    public static final RegistryObject<Block> KWAS_CHLEBOWY = BLOCKS.register("kwas_chlebowy",
            () -> DrinkBlock.create()
                    .maxCount(4)
                    .shapes(
                            Block.box(5, 0, 5, 11, 16, 11),
                            Block.box(1, 0, 5, 15, 16, 11),
                            Shapes.or(
                                    Block.box(1, 0, 9, 15, 16, 15),
                                    Block.box(5, 0, 1, 11, 16, 15)
                            ),
                            Block.box(1, 0, 1, 15, 16, 15)
                    ).build().get());

    // 竹叶青
    public static final RegistryObject<Block> BAMBOO_LEAF_GREEN_LIQUOR = BLOCKS.register("bamboo_leaf_green_liquor",
            () -> DrinkBlock.create()
                    .maxCount(4)
                    .shapes(
                            Block.box(5, 0, 5, 11, 13, 11),
                            Block.box(1, 0, 5, 15, 13, 11),
                            Shapes.or(
                                    Block.box(1, 0, 9, 15, 13, 15),
                                    Block.box(5, 0, 1, 11, 13, 15)
                            ),
                            Block.box(1, 0, 1, 15, 13, 15)
                    ).build().get());

    // 凉冰红茶
    public static final RegistryObject<Block> COOL_TEA = BLOCKS.register("cool_tea",
            () -> DrinkBlock.create()
                    .maxCount(4)
                    .shapes(
                            Block.box(4, 0, 5, 12, 14, 11),
                            Block.box(0.5, 0, 4, 15.5, 14, 12),
                            Shapes.or(
                                    Block.box(0.5, 0, 7, 15.5, 14, 15.5),
                                    Block.box(4, 0, 0.5, 12, 14, 7)
                            ),
                            Block.box(0.5, 0, 0.5, 15.5, 14, 15.5)
                    ).build().get());

    //黑色吧台凳
    public static final RegistryObject<Block> BAR_STOOL_BLACK = BLOCKS.register("bar_stool_black",
            () -> new ChairBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(1.0F)
                    .noOcclusion()));
    //白色吧台凳
    public static final RegistryObject<Block> BAR_STOOL_WHITE = BLOCKS.register("bar_stool_white",
            () -> new ChairBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(1.0F)
                    .noOcclusion()));
    //淡灰色吧台凳
    public static final RegistryObject<Block> BAR_STOOL_LIGHT_GRAY = BLOCKS.register("bar_stool_light_gray",
            () -> new ChairBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(1.0F)
                    .noOcclusion()));
    //灰色吧台凳
    public static final RegistryObject<Block> BAR_STOOL_GRAY = BLOCKS.register("bar_stool_gray",
            () -> new ChairBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(1.0F)
                    .noOcclusion()));
    //棕色吧台凳
    public static final RegistryObject<Block> BAR_STOOL_BROWN = BLOCKS.register("bar_stool_brown",
            () -> new ChairBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(1.0F)
                    .noOcclusion()));
    //红色吧台凳
    public static final RegistryObject<Block> BAR_STOOL_RED = BLOCKS.register("bar_stool_red",
            () -> new ChairBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(1.0F)
                    .noOcclusion()));
    //橙色吧台凳
    public static final RegistryObject<Block> BAR_STOOL_ORANGE = BLOCKS.register("bar_stool_orange",
            () -> new ChairBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(1.0F)
                    .noOcclusion()));
    //黄色吧台凳
    public static final RegistryObject<Block> BAR_STOOL_YELLOW = BLOCKS.register("bar_stool_yellow",
            () -> new ChairBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(1.0F)
                    .noOcclusion()));
    //黄绿色吧台凳
    public static final RegistryObject<Block> BAR_STOOL_LIME = BLOCKS.register("bar_stool_lime",
            () -> new ChairBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(1.0F)
                    .noOcclusion()));
    //绿色吧台凳
    public static final RegistryObject<Block> BAR_STOOL_GREEN = BLOCKS.register("bar_stool_green",
            () -> new ChairBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(1.0F)
                    .noOcclusion()));
    //青色吧台凳
    public static final RegistryObject<Block> BAR_STOOL_CYAN = BLOCKS.register("bar_stool_cyan",
            () -> new ChairBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(1.0F)
                    .noOcclusion()));
    //淡蓝色吧台凳
    public static final RegistryObject<Block> BAR_STOOL_LIGHT_BLUE = BLOCKS.register("bar_stool_light_blue",
            () -> new ChairBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(1.0F)
                    .noOcclusion()));
    //蓝色吧台凳
    public static final RegistryObject<Block> BAR_STOOL_BLUE = BLOCKS.register("bar_stool_blue",
            () -> new ChairBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(1.0F)
                    .noOcclusion()));
    //紫色吧台凳
    public static final RegistryObject<Block> BAR_STOOL_PURPLE = BLOCKS.register("bar_stool_purple",
            () -> new ChairBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(1.0F)
                    .noOcclusion()));
    //品红色吧台凳
    public static final RegistryObject<Block> BAR_STOOL_MAGENTA = BLOCKS.register("bar_stool_magenta",
            () -> new ChairBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(1.0F)
                    .noOcclusion()));
    //粉色吧台凳
    public static final RegistryObject<Block> BAR_STOOL_PINK = BLOCKS.register("bar_stool_pink",
            () -> new ChairBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(1.0F)
                    .noOcclusion()));
    //冰柜
    public static final RegistryObject<Block> FREEZER = BLOCKS.register("freezer",
            () -> new FreezerBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WATER)
                    .strength(5.0F,1200F)
                    .noOcclusion()));

    @SuppressWarnings("all")
    public static final RegistryObject<BlockEntityType<FreezerBlockEntity>> FREEZER_BE = BLOCK_ENTITIES.register("freezer",
           () -> BlockEntityType.Builder.of(FreezerBlockEntity::new, FREEZER.get()).build(null));

    public static final RegistryObject<LiquidBlock> MILK_LIQUID_BLOCK = BLOCKS.register("milk_liquid",
            () -> new LiquidBlock(ModFluids.MILK_STILL, net.minecraft.world.level.block.state.BlockBehaviour.Properties.of().noCollission().liquid()));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        BLOCK_ENTITIES.register(eventBus);
    }
}
