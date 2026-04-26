package com.bmt.kaleidoscope_world_liquor.entity;

import com.bmt.kaleidoscope_world_liquor.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class ChairEntity extends Entity {

    public ChairEntity(EntityType<? extends ChairEntity> type, Level level) {
        super(type, level);
        this.noPhysics = true;
    }

    public static ChairEntity create(Level level, BlockPos pos) {
        ChairEntity entity = new ChairEntity(ModEntities.OAK_LOG_STOOL.get(), level);
        entity.setPos(pos.getX() + 0.5, pos.getY() + 0.7, pos.getZ() + 0.5);
        return entity;
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {}

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {}

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            boolean shouldDiscard = this.getPassengers().isEmpty();
            if (!shouldDiscard) {
                BlockPos pos = this.blockPosition();
                Block block = this.level().getBlockState(pos).getBlock();
                shouldDiscard = !(block instanceof com.bmt.kaleidoscope_world_liquor.block.ChairBlock);
            }
            if (shouldDiscard) {
                this.discard();
            }
        }
    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity pPassenger) {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            Vec3 candidatePos = new Vec3(
                    this.getX() + direction.getStepX() * 1.0,
                    this.getY(),
                    this.getZ() + direction.getStepZ() * 1.0
            );
            if (isPositionSafe(pPassenger, candidatePos)) {
                return candidatePos.add(0, 0.1, 0);
            }
        }
        return super.getDismountLocationForPassenger(pPassenger);
    }

    private boolean isPositionSafe(LivingEntity passenger, Vec3 pos) {
        AABB playerBoundingBox = passenger.getBoundingBox().move(pos.subtract(passenger.position()));
        return this.level().noCollision(passenger, playerBoundingBox);
    }
}