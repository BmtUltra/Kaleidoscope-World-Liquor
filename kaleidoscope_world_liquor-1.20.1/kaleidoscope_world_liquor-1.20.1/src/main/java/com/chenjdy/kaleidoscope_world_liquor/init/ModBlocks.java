package com.chenjdy.kaleidoscope_world_liquor.init;

import com.chenjdy.kaleidoscope_world_liquor.KaleidoscopeWorldLiquor;
import com.github.ysbbbbbb.kaleidoscopetavern.block.brew.DrinkBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, KaleidoscopeWorldLiquor.MODID);

    // 孟买蓝宝石金酒
    public static final RegistryObject<Block> BOMBAY_SAPPHIRE_GIN = BLOCKS.register("bombay_sapphire_gin",
            () -> DrinkBlock.create()
                    .maxCount(4)
                    .shapes(
                            Block.box(4, 0, 4, 12, 15, 12),
                            Block.box(0, 0, 4, 16, 15, 12),
                            Shapes.or(
                                    Block.box(0, 0, 8, 16, 15, 16),
                                    Block.box(4, 0, 0, 12, 15, 16)
                            ),
                            Block.box(0, 0, 0, 16, 16, 16)
                    ).build().get());

    // 杰克丹尼
    public static final RegistryObject<Block> JACK_DANIEL = BLOCKS.register("jack_daniel",
            () -> DrinkBlock.create()
                    .maxCount(4)
                    .shapes(
                            Block.box(4, 0, 4, 12, 15, 12),
                            Block.box(0, 0, 4, 16, 15, 12),
                            Shapes.or(
                                    Block.box(0, 0, 8, 16, 15, 16),
                                    Block.box(4, 0, 0, 12, 15, 16)
                            ),
                            Block.box(0, 0, 0, 16, 16, 16)
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
                            Block.box(4, 0, 4, 12, 15, 12),
                            Block.box(0, 0, 4, 16, 15, 12),
                            Shapes.or(
                                    Block.box(0, 0, 8, 16, 15, 16),
                                    Block.box(4, 0, 0, 12, 15, 16)
                            ),
                            Block.box(0, 0, 0, 16, 16, 16)
                    ).build().get());

    // 马利宝椰子朗姆酒
    public static final RegistryObject<Block> PINA_COLADA = BLOCKS.register("pina_colada",
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

    // 飞天茅台
    public static final RegistryObject<Block> MAOTAI = BLOCKS.register("maotai",
            () -> DrinkBlock.create()
                    .maxCount(4)
                    .shapes(
                            Block.box(4, 0, 4, 12, 15, 12),
                            Block.box(0, 0, 4, 16, 15, 12),
                            Shapes.or(
                                    Block.box(0, 0, 8, 16, 15, 16),
                                    Block.box(4, 0, 0, 12, 15, 16)
                            ),
                            Block.box(0, 0, 0, 16, 16, 16)
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
}
