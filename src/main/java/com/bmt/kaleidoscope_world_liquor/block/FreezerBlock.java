package com.bmt.kaleidoscope_world_liquor.block;

import com.bmt.kaleidoscope_world_liquor.block.entity.FreezerBlockEntity;
import com.bmt.kaleidoscope_world_liquor.init.ModBlocks;
import com.bmt.kaleidoscope_world_liquor.init.ModFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class FreezerBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    public static final BooleanProperty WORKING = BooleanProperty.create("working");

    private static final VoxelShape SHAPE_NORTH = Block.box(0.0D, 0.0D, 1.0D, 16.0D, 14.5D, 14.0D);
    private static final VoxelShape SHAPE_SOUTH = Block.box(0.0D, 0.0D, 2.0D, 16.0D, 14.5D, 15.0D);
    private static final VoxelShape SHAPE_EAST = Block.box(2.0D, 0.0D, 0.0D, 15.0D, 14.5D, 16.0D);
    private static final VoxelShape SHAPE_WEST = Block.box(1.0D, 0.0D, 0.0D, 14.0D, 14.5D, 16.0D);

    public FreezerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(OPEN, false)
                .setValue(WORKING, false));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case EAST -> SHAPE_EAST;
            case WEST -> SHAPE_WEST;
            default -> Shapes.block();
        };
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) return InteractionResult.SUCCESS;
        if (!(level.getBlockEntity(pos) instanceof FreezerBlockEntity be)) return InteractionResult.PASS;

        if (state.getValue(WORKING)) {
            int remaining = Math.max(0, (be.getMaxProgress() - be.getProgress()) / 20);
            player.displayClientMessage(Component.translatable("message.kaleidoscope_world_liquor.freezer.remaining_time", remaining), true);
            return InteractionResult.CONSUME;
        }

        if (player.isShiftKeyDown()) {
            boolean isOpen = state.getValue(OPEN);

            if (isOpen) {
                boolean success = be.tryStartCrafting();
                if (success) {
                    level.setBlock(pos, state.setValue(OPEN, false).setValue(WORKING, true), 3);
                    player.displayClientMessage(Component.translatable("message.kaleidoscope_world_liquor.freezer.start_crafting"), true);
                } else {
                    level.setBlock(pos, state.setValue(OPEN, false), 3);
                }
            } else {
                BlockPos abovePos = pos.above();
                if (level.getBlockState(abovePos).isAir()) {
                    level.setBlock(pos, state.setValue(OPEN, true), 3);
                }
            }
            level.playSound(null, pos, isOpen ? net.minecraft.sounds.SoundEvents.BARREL_CLOSE : net.minecraft.sounds.SoundEvents.BARREL_OPEN, net.minecraft.sounds.SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.CONSUME;
        }

        if (state.getValue(OPEN)) {
            ItemStack held = player.getItemInHand(hand);

            if (be.hasOutput()) {
                be.extractOutput(player);
                level.playSound(null, pos, net.minecraft.sounds.SoundEvents.ITEM_PICKUP, net.minecraft.sounds.SoundSource.BLOCKS, 1.0F, 1.0F);
                return InteractionResult.CONSUME;
            }

            if (!held.isEmpty()) {
                if (held.is(Items.MILK_BUCKET)) {
                    if (be.tank.getFluidAmount() + 1000 <= be.tank.getCapacity()) {
                        be.tank.fill(new FluidStack(ModFluids.MILK_STILL.get(), 1000), IFluidHandler.FluidAction.EXECUTE);
                        if(!player.isCreative()){
                            held.shrink(1);
                            player.addItem(new ItemStack(Items.BUCKET));
                        }
                        level.playSound(null, pos, net.minecraft.sounds.SoundEvents.BUCKET_EMPTY, net.minecraft.sounds.SoundSource.BLOCKS, 1.0F, 1.0F);
                        return InteractionResult.CONSUME;
                    }
                }
                if (FluidUtil.interactWithFluidHandler(player, hand, level, pos, hit.getDirection())) {
                    return InteractionResult.CONSUME;
                }
            }

            if (held.isEmpty()) {
                be.extractItem(player);
                level.playSound(null, pos, net.minecraft.sounds.SoundEvents.ITEM_FRAME_REMOVE_ITEM, net.minecraft.sounds.SoundSource.BLOCKS, 0.8F, 1.1F);
            } else {
                be.insertItem(held, player);
                level.playSound(null, pos, net.minecraft.sounds.SoundEvents.ITEM_FRAME_ADD_ITEM, net.minecraft.sounds.SoundSource.BLOCKS, 0.8F, 1.1F);
            }
            return InteractionResult.CONSUME;
        }
        return InteractionResult.CONSUME;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN, WORKING);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof FreezerBlockEntity freezer) {
                for (int i = 0; i < 4; i++) {
                    Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), freezer.inventory.getStackInSlot(i));
                }
            }
        }
        super.onRemove(state, level, pos, newState, moved);
    }

    @Nullable @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new FreezerBlockEntity(pos, state); }
    @Nullable @Override public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) { return createTickerHelper(type, ModBlocks.FREEZER_BE.get(), FreezerBlockEntity::tick); }
    @Override public RenderShape getRenderShape(BlockState state) { return RenderShape.MODEL; }
}