package com.bmt.kaleidoscope_world_liquor.hooks;

import com.bmt.kaleidoscope_world_liquor.init.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CaptainGiftMixinHooks {

    public static Vec3 enableWaterWalking(Entity entity, Vec3 original) {
        if (!(entity instanceof LivingEntity livingEntity))
            return original;

        if (original.y > 0)
            return original;

        if (!livingEntity.hasEffect(ModEffects.CAPTAIN_GIFT_EFFECT.get()))
            return original;

        if (livingEntity.isShiftKeyDown())
            return original;

        Level level = livingEntity.level();

        int[][] offsets = {
                { 1, 0, 1 }, { 1, 0, 0 }, { 1, -1, 0 }, { 1, 0, -1 },
                { 0, 0, 1 }, { 0, 0, 0 }, { 0, -1, 0 }, { 0, 0, -1 },
                { -1, 0, 1 }, { -1, 0, 0 }, { -1, -1, 0 }, { -1, 0, -1 }
        };

        double highestWaterY = original.y;
        boolean foundWater = false;

        for (int[] offset : offsets) {
            BlockPos sourcePos = livingEntity.blockPosition();
            BlockPos pos = new BlockPos(
                    sourcePos.getX() + offset[0],
                    sourcePos.getY() + offset[1],
                    sourcePos.getZ() + offset[2]);

            FluidState fluidState = level.getFluidState(pos);

            if (fluidState.isEmpty() || !fluidState.is(FluidTags.WATER))
                continue;

            VoxelShape shape = Shapes.block().move(
                    pos.getX(),
                    pos.getY() + fluidState.getOwnHeight(),
                    pos.getZ());

            if (Shapes.joinIsNotEmpty(
                    shape,
                    Shapes.create(livingEntity.getBoundingBox().inflate(0.5)),
                    BooleanOp.AND)) {
                double height = shape.max(Direction.Axis.Y) - livingEntity.getY() - 1;

                if (highestWaterY < height) {
                    highestWaterY = height;
                    foundWater = true;
                }
            }
        }

        if (foundWater) {
            livingEntity.fallDistance = 0F;
            livingEntity.setOnGround(true);

            return new Vec3(original.x, highestWaterY, original.z);
        }
        return original;
    }
}