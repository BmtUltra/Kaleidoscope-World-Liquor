package com.bmt.kaleidoscope_world_liquor.compat.jei;

import com.bmt.kaleidoscope_world_liquor.KaleidoscopeWorldLiquor;
import com.bmt.kaleidoscope_world_liquor.init.ModBlocks;
import com.bmt.kaleidoscope_world_liquor.init.ModRecipes;
import com.bmt.kaleidoscope_world_liquor.crafting.FreezerRecipe;
import com.google.common.collect.Lists;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FreezerCategory implements IRecipeCategory<FreezerRecipe> {
    public static final RecipeType<FreezerRecipe> TYPE = RecipeType.create(KaleidoscopeWorldLiquor.MODID, "freezer", FreezerRecipe.class);

    private static final ResourceLocation BG = ResourceLocation.parse("kaleidoscope_world_liquor:textures/gui/jei/freezer.png");
    private static final Component TITLE = Component.translatable("block.kaleidoscope_world_liquor.freezer");

    public static final int WIDTH = 180;
    public static final int HEIGHT = 150;

    private final IDrawable bgDraw;
    private final IDrawable iconDraw;

    public FreezerCategory(IGuiHelper guiHelper) {
        this.bgDraw = guiHelper.createDrawable(BG, 0, 0, WIDTH, HEIGHT);
        this.iconDraw = guiHelper.createDrawableItemLike(ModBlocks.FREEZER.get().asItem());
    }

    public static List<FreezerRecipe> getRecipes() {
        ClientLevel level = Minecraft.getInstance().level;
        if (level == null) {
            return List.of();
        }
        List<FreezerRecipe> recipes = Lists.newArrayList();
        recipes.addAll(level.getRecipeManager().getAllRecipesFor(ModRecipes.FREEZER_TYPE.get()));
        return recipes;
    }

    @Override
    public void draw(@NotNull FreezerRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView, @NotNull GuiGraphics guiGraphics, double mouseX, double mouseY) {
        this.bgDraw.draw(guiGraphics);
    }

    @Override
    @SuppressWarnings("all")
    public void setRecipe(IRecipeLayoutBuilder builder, FreezerRecipe recipe, IFocusGroup focuses) {
        int fluidBuckets = recipe.getInputFluid().getAmount() / 1000;
        ItemStack fluidStack = new ItemStack(recipe.getInputFluid().getFluid().getBucket(), Math.max(1, fluidBuckets));
        builder.addSlot(RecipeIngredientRole.INPUT, 10, 9)
                .addItemStack(fluidStack);

        int offsetX = 0;
        for (int i = 0; i < recipe.getInputItems().size(); i++) {
            ItemStack inputStack = recipe.getInputItems().get(i);
            if (!inputStack.isEmpty()) {
                builder.addSlot(RecipeIngredientRole.INPUT, 30 + offsetX, 9)
                        .addItemStack(inputStack.copy());
                offsetX += 18;
            }
        }

        ItemStack conditionStack = recipe.getExtractCondition().copy();
        if (!conditionStack.isEmpty()) {
            builder.addSlot(RecipeIngredientRole.CATALYST, 115, 100)
                    .addItemStack(conditionStack);
        }

        int seconds = recipe.getCraftTime() / 20;
        ItemStack outputStack = recipe.getResultItem(Minecraft.getInstance().level.registryAccess()).copy();

        builder.addSlot(RecipeIngredientRole.OUTPUT, 152, 86)
                .addItemStack(outputStack)
                .addTooltipCallback((recipeSlotView, tooltip) -> {
                    tooltip.add(Component.literal("§7冷冻时间: §f" + seconds + " 秒"));
                });
    }

    @Override
    public @NotNull RecipeType<FreezerRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return TITLE;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    @Nullable
    @Override
    public IDrawable getIcon() {
        return iconDraw;
    }
}