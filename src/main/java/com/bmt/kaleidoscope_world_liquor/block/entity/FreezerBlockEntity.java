package com.bmt.kaleidoscope_world_liquor.block.entity;

import com.bmt.kaleidoscope_world_liquor.init.ModBlocks;
import com.bmt.kaleidoscope_world_liquor.init.ModRecipes;
import com.bmt.kaleidoscope_world_liquor.crafting.FreezerRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class FreezerBlockEntity extends BlockEntity {
    public FreezerRecipe recipe = FreezerRecipe.EMPTY;

    public final FluidTank tank = new FluidTank(1000) {
        @Override
        protected void onContentsChanged() {
            setChanged();
            if (level != null && !level.isClientSide) {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            }
        }
    };
    private final LazyOptional<IFluidHandler> fluidHandler = LazyOptional.of(() -> tank);

    public final ItemStackHandler inventory = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (level != null && !level.isClientSide) {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            }
        }
    };
    private final LazyOptional<ItemStackHandler> itemHandler = LazyOptional.of(() -> inventory);

    private int progress = 0;
    private int maxProgress = 0;
    private int outputCount = 0;
    private ResourceLocation outputTexture = null;

    public FreezerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.FREEZER_BE.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, FreezerBlockEntity be) {
        if (level.isClientSide) return;

        if (state.getValue(com.bmt.kaleidoscope_world_liquor.block.FreezerBlock.WORKING)) {
            be.progress++;

            if (be.progress >= be.maxProgress) {
                be.finishCrafting(state, level, pos);
            }

            if (be.progress % 20 == 0) {
                setChanged(level, pos, state);
            }
        }
    }

    public boolean tryStartCrafting() {
        if (this.level == null) return false;

        Optional<FreezerRecipe> recipe = level.getRecipeManager()
                .getAllRecipesFor(ModRecipes.FREEZER_TYPE.get()).stream()
                .filter(r -> r.matches(this.tank.getFluid(), this.inventoryToNonNullList(), level))
                .findFirst();

        if (recipe.isPresent()) {
            this.maxProgress = recipe.get().getCraftTime();
            this.progress = 0;
            this.recipe = recipe.get();

            this.tank.drain(recipe.get().getInputFluid().getAmount(), IFluidHandler.FluidAction.EXECUTE);
            for (int i = 0; i < 4; i++) {
                this.inventory.setStackInSlot(i, ItemStack.EMPTY);
            }

            setChanged();
            return true;
        }
        return false;
    }

    private void finishCrafting(BlockState state, Level level, BlockPos pos) {
        if (this.recipe != FreezerRecipe.EMPTY) {
            this.outputCount = this.recipe.getResultItem(level.registryAccess()).getCount();
            this.outputTexture = this.recipe.getResultTexture();
        }

        level.setBlock(pos, state
                .setValue(com.bmt.kaleidoscope_world_liquor.block.FreezerBlock.WORKING, false), 3);
    }

    public void insertItem(ItemStack stack, Player player) {
        for (int i = 0; i < 4; i++) {
            if (inventory.getStackInSlot(i).isEmpty()) {
                ItemStack copy = stack.copy();
                copy.setCount(1);
                inventory.setStackInSlot(i, copy);
                if (!player.isCreative()) stack.shrink(1);
                return;
            }
        }
    }

    public void extractItem(Player player) {
        for (int i = 3; i >= 0; i--) {
            if (!inventory.getStackInSlot(i).isEmpty()) {
                player.addItem(inventory.extractItem(i, 1, false));
                return;
            }
        }
    }

    public boolean hasOutput() { return this.outputCount > 0; }

    @SuppressWarnings("all")
    public void extractOutput(Player player) {
        if (this.outputCount > 0 && this.recipe != FreezerRecipe.EMPTY) {
            ItemStack held = player.getItemInHand(InteractionHand.MAIN_HAND);

            if (!this.recipe.canExtract(held)) {
                player.displayClientMessage(Component.translatable("message.kaleidoscope_world_liquor.freezer.need_item", this.recipe.getExtractCondition().getHoverName()), true);
                return;
            }

            ItemStack extractItem = this.recipe.getExtractCondition();
            if (!extractItem.isEmpty() && !player.isCreative()) {
                held.shrink(1);
                player.setItemInHand(InteractionHand.MAIN_HAND, held);
            }

            ItemStack stack = this.recipe.getResultItem(player.level().registryAccess()).copy();
            stack.setCount(1);
            player.addItem(stack);

            this.outputCount--;
            setChanged();
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    private NonNullList<ItemStack> inventoryToNonNullList() {
        NonNullList<ItemStack> list = NonNullList.withSize(4, ItemStack.EMPTY);
        for (int i = 0; i < 4; i++) {
            list.set(i, inventory.getStackInSlot(i));
        }
        return list;
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Tank", tank.writeToNBT(new CompoundTag()));
        tag.put("Inventory", inventory.serializeNBT());
        tag.putInt("Progress", progress);
        tag.putInt("MaxProgress", maxProgress);
        tag.putInt("OutputCount", outputCount);
        if (outputTexture != null) tag.putString("OutputTexture", outputTexture.toString());
        if (this.recipe != FreezerRecipe.EMPTY) {
            tag.putString("RecipeId", this.recipe.getId().toString());
        }
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        tank.readFromNBT(tag.getCompound("Tank"));
        inventory.deserializeNBT(tag.getCompound("Inventory"));
        progress = tag.getInt("Progress");
        maxProgress = tag.getInt("MaxProgress");
        outputCount = tag.getInt("OutputCount");
        if (tag.contains("OutputTexture")) outputTexture = ResourceLocation.parse(tag.getString("OutputTexture"));

        if (this.level != null && this.level.isClientSide && tag.contains("RecipeId")) {
            ResourceLocation recipeId = ResourceLocation.parse(tag.getString("RecipeId"));
            this.recipe = level.getRecipeManager()
                    .getAllRecipesFor(ModRecipes.FREEZER_TYPE.get())
                    .stream().filter(r -> r.getId().equals(recipeId))
                    .findFirst().orElse(FreezerRecipe.EMPTY);
        }
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() { return saveWithoutMetadata(); }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() { return ClientboundBlockEntityDataPacket.create(this); }

    @Override
    @SuppressWarnings("all")
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        load(pkt.getTag());
        if (this.level != null && this.level.isClientSide) {
            CompoundTag tag = pkt.getTag();
            if (tag != null && tag.contains("RecipeId")) {
                ResourceLocation recipeId = ResourceLocation.parse(tag.getString("RecipeId"));
                this.recipe = level.getRecipeManager()
                        .getAllRecipesFor(ModRecipes.FREEZER_TYPE.get())
                        .stream().filter(r -> r.getId().equals(recipeId))
                        .findFirst().orElse(FreezerRecipe.EMPTY);
            }
        }
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.FLUID_HANDLER) return fluidHandler.cast();
        if (cap == ForgeCapabilities.ITEM_HANDLER) return itemHandler.cast();
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        fluidHandler.invalidate();
        itemHandler.invalidate();
    }

    public int getProgress() { return progress; }
    public int getMaxProgress() { return maxProgress; }
    public int getOutputCount() { return outputCount; }
    public ResourceLocation getOutputTexture() { return outputTexture; }
}