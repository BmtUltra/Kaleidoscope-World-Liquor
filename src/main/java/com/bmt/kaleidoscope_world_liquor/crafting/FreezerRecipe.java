package com.bmt.kaleidoscope_world_liquor.crafting;

import com.bmt.kaleidoscope_world_liquor.init.ModRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;

public class FreezerRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final FluidStack inputFluid;
    private final NonNullList<ItemStack> inputItems;
    private final ItemStack resultItem;
    private final int craftTime;
    private final ResourceLocation resultTexture;
    private final ItemStack extractCondition;

    public FreezerRecipe(ResourceLocation id, FluidStack inputFluid, NonNullList<ItemStack> inputItems, ItemStack resultItem, int craftTime, ResourceLocation resultTexture, ItemStack extractCondition) {
        this.id = id;
        this.inputFluid = inputFluid;
        this.inputItems = inputItems;
        this.resultItem = resultItem;
        this.craftTime = craftTime;
        this.resultTexture = resultTexture;
        this.extractCondition = extractCondition;
    }

    public boolean matches(FluidStack fluid, NonNullList<ItemStack> items, Level level) {
        if (!fluid.getFluid().isSame(this.inputFluid.getFluid()) || fluid.getAmount() < this.inputFluid.getAmount()) {
            return false;
        }

        for (int i = 0; i < 4; i++) {
            ItemStack recipeStack = this.inputItems.get(i);
            ItemStack invStack = items.get(i);

            if (recipeStack.isEmpty()) {
                if (!invStack.isEmpty()) return false;
            }
            else {
                if (invStack.isEmpty() || !ItemStack.isSameItem(recipeStack, invStack)) return false;
            }
        }
        return true;
    }

    public boolean canExtract(ItemStack heldItem) {
        if (this.extractCondition.isEmpty()) {
            return true;
        }
        return ItemStack.isSameItem(heldItem, this.extractCondition);
    }

    @Override @Deprecated
    public boolean matches(SimpleContainer pContainer, Level pLevel) { return false; }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, net.minecraft.core.RegistryAccess pAccess) {
        return this.resultItem.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) { return true; }

    @Override
    public ItemStack getResultItem(net.minecraft.core.RegistryAccess pAccess) { return this.resultItem; }

    @Override
    public ResourceLocation getId() { return this.id; }

    @Override
    public RecipeSerializer<?> getSerializer() { return ModRecipes.FREEZER_SERIALIZER.get(); }

    @Override
    public RecipeType<?> getType() { return ModRecipes.FREEZER_TYPE.get(); }

    public FluidStack getInputFluid() { return inputFluid; }
    public NonNullList<ItemStack> getInputItems() { return inputItems; }
    public ItemStack getResultItem() { return resultItem; }
    public int getCraftTime() { return craftTime; }
    public ResourceLocation getResultTexture() { return resultTexture; }
    public ItemStack getExtractCondition() { return extractCondition; }

    @SuppressWarnings("all")
    public static final FreezerRecipe EMPTY = new FreezerRecipe(
            ResourceLocation.parse("kaleidoscope_world_liquor:empty"),
            FluidStack.EMPTY,
            NonNullList.withSize(4, ItemStack.EMPTY),
            ItemStack.EMPTY,
            0,
            null,
            ItemStack.EMPTY
    );
}