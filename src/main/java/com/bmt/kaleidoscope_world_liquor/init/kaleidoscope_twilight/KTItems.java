package com.bmt.kaleidoscope_world_liquor.init.kaleidoscope_twilight;

import com.bmt.kaleidoscope_world_liquor.KaleidoscopeWorldLiquor;
import com.bmt.kaleidoscope_world_liquor.init.ModFoods;
import com.bmt.kaleidoscope_world_liquor.item.CompatFoodItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KTItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, KaleidoscopeWorldLiquor.MODID);

    public static final RegistryObject<Item> LIANGSHAN_ICE_CONE = ITEMS.register("liangshan_ice_cone",
            () -> new CompatFoodItem(new Item.Properties().stacksTo(64).food(ModFoods.LIANGSHAN_ICE_CONE), "kaleidoscope_twilight")
    );

    public static final RegistryObject<Item> KITA_STUFFED_CRISP = ITEMS.register("kita_stuffed_crisp",
            () -> new CompatFoodItem(new Item.Properties().stacksTo(64).food(ModFoods.KITA_STUFFED_CRISP), "kaleidoscope_twilight")
    );

    public static final RegistryObject<Item> POCHI_PUDDING = ITEMS.register("pochi_pudding",
            () -> new CompatFoodItem(new Item.Properties().stacksTo(64).food(ModFoods.POCHI_PUDDING), "kaleidoscope_twilight", net.minecraft.world.item.Items.BOWL)
    );

    public static final RegistryObject<Item> MAGIC_CRISPY_CORNER = ITEMS.register("magic_crispy_corner",
            () -> new CompatFoodItem(new Item.Properties().stacksTo(64).food(ModFoods.MAGIC_CRISPY_CORNER), "kaleidoscope_twilight")
    );

    public static void register(net.minecraftforge.eventbus.api.IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}