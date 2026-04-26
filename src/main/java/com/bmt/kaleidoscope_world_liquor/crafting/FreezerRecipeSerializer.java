package com.bmt.kaleidoscope_world_liquor.crafting;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class FreezerRecipeSerializer implements RecipeSerializer<FreezerRecipe> {
    @Override
    @SuppressWarnings("all")
    public @NotNull FreezerRecipe fromJson(@NotNull ResourceLocation pRecipeId, @NotNull JsonObject pJson) {
        String fluidId = GsonHelper.getAsString(pJson, "fluid");
        Fluid fluid = ForgeRegistries.FLUIDS.getValue(ResourceLocation.parse(fluidId));
        int fluidAmount = GsonHelper.getAsInt(pJson, "fluid_amount", 1000);
        FluidStack inputFluid = new FluidStack(fluid, fluidAmount);

        NonNullList<ItemStack> inputItems = NonNullList.withSize(4, ItemStack.EMPTY);
        JsonArray itemsArray = GsonHelper.getAsJsonArray(pJson, "items", new JsonArray());
        for (int i = 0; i < 4; i++) {
            if (i < itemsArray.size()) {
                String itemId = itemsArray.get(i).getAsString();
                if (!itemId.isEmpty()) {
                    inputItems.set(i, new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(itemId)))));
                }
            }
        }

        JsonObject resultObj = GsonHelper.getAsJsonObject(pJson, "result");
        String resultId = GsonHelper.getAsString(resultObj, "item");
        int resultCount = GsonHelper.getAsInt(resultObj, "count", 1);
        ItemStack resultItem = new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(resultId))), resultCount);

        int craftTime = GsonHelper.getAsInt(pJson, "craft_time", 200);
        String textureId = GsonHelper.getAsString(pJson, "texture");

        ItemStack extractCondition = ItemStack.EMPTY;
        if (pJson.has("extract_condition")) {
            String condId = GsonHelper.getAsString(pJson, "extract_condition");
            if (!condId.isEmpty()) {
                extractCondition = new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(condId))));
            }
        }
        return new FreezerRecipe(pRecipeId, inputFluid, inputItems, resultItem, craftTime, ResourceLocation.parse(textureId), extractCondition);
    }

    @Override
    public FreezerRecipe fromNetwork(@NotNull ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
        FluidStack inputFluid = FluidStack.readFromPacket(pBuffer);
        NonNullList<ItemStack> inputItems = NonNullList.withSize(4, ItemStack.EMPTY);
        for (int i = 0; i < 4; i++) {
            inputItems.set(i, pBuffer.readItem());
        }
        ItemStack resultItem = pBuffer.readItem();
        int craftTime = pBuffer.readInt();
        ResourceLocation resultTexture = pBuffer.readResourceLocation();
        ItemStack extractCondition = pBuffer.readItem();
        return new FreezerRecipe(pRecipeId, inputFluid, inputItems, resultItem, craftTime, resultTexture, extractCondition);
    }

    @Override
    public void toNetwork(FriendlyByteBuf pBuffer, FreezerRecipe pRecipe) {
        pRecipe.getInputFluid().writeToPacket(pBuffer);
        for (int i = 0; i < 4; i++) {
            pBuffer.writeItem(pRecipe.getInputItems().get(i));
        }
        pBuffer.writeItem(pRecipe.getResultItem());
        pBuffer.writeInt(pRecipe.getCraftTime());
        pBuffer.writeResourceLocation(pRecipe.getResultTexture());
        pBuffer.writeItem(pRecipe.getExtractCondition());
    }
}