package com.bmt.kaleidoscope_world_liquor.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CompatFoodItem extends Item {
    private final String fakeModId;
    @Nullable
    private final Item remainderItem;

    public CompatFoodItem(Item.Properties properties, String fakeModId) {
        this(properties, fakeModId, null);
    }

    public CompatFoodItem(Item.Properties properties, String fakeModId, @Nullable Item remainderItem) {
        super(properties);
        this.fakeModId = fakeModId;
        this.remainderItem = remainderItem;
    }

    @Override
    public @Nullable String getCreatorModId(ItemStack stack) {
        return fakeModId;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
        ItemStack resultStack = super.finishUsingItem(stack, level, livingEntity);

        if (this.remainderItem != null) {
            if (stack.isEmpty()) {
                return new ItemStack(this.remainderItem);
            } else {
                livingEntity.spawnAtLocation(new ItemStack(this.remainderItem));
                return resultStack;
            }
        }
        return resultStack;
    }
}