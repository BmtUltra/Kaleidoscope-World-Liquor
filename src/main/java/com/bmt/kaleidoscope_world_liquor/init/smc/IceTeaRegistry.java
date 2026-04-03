package com.bmt.kaleidoscope_world_liquor.init.smc;

import com.github.ysbbbbbb.kaleidoscopetavern.block.brew.DrinkBlock;
import com.github.ysbbbbbb.kaleidoscopetavern.item.DrinkBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class IceTeaRegistry {
    public static final String SMC_MODID = "smc";

    public static final DeferredRegister<Item> SMC_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SMC_MODID);
    public static final DeferredRegister<Block> SMC_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SMC_MODID);

    public static final RegistryObject<Block> SMC_ICE_TEA_BLOCK = SMC_BLOCKS.register("ice_tea",
            () -> DrinkBlock.create()
                    .maxCount(4)
                    .irregular()
                    .shapes(
                            Block.box(4, 0, 5, 12, 14, 11),
                            Block.box(0.5, 0, 4, 15.5, 14, 12),
                            Shapes.or(
                                    Block.box(0.5, 0, 7, 15.5, 14, 15.5),
                                    Block.box(4, 0, 0.5, 12, 14, 7)
                            ),
                            Block.box(0.5, 0, 0.5, 15.5, 14, 15.5)
                    ).build().get());

    public static final RegistryObject<Item> SMC_ICE_TEA_ITEM = SMC_ITEMS.register("ice_tea",
            () -> new DrinkBlockItem(SMC_ICE_TEA_BLOCK.get()));

    public static void register(net.minecraftforge.eventbus.api.IEventBus eventBus) {
        SMC_BLOCKS.register(eventBus);
        SMC_ITEMS.register(eventBus);
    }
}