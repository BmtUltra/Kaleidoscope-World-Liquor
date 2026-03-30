package com.bmt.kaleidoscope_world_liquor.mixin;

import com.bmt.kaleidoscope_world_liquor.init.ModEffects;
import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
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
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Entity.class)
public class CaptainGiftMixin {

    @Definition(id = "collide", method = "Lnet/minecraft/world/entity/Entity;collide(Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/phys/Vec3;")
    @Expression("? = ?.collide(?)")
    @ModifyVariable(method = "move", ordinal = 1, index = 3, name = "vec32", at = @At(value = "MIXINEXTRAS:EXPRESSION", shift = At.Shift.AFTER))
    public Vec3 captainGift$enableWaterWalking(Vec3 original) {
        if (!((Entity) (Object) this instanceof LivingEntity entity))
            return original;

        if (original.y > 0)
            return original;

        if (!entity.hasEffect(ModEffects.CAPTAIN_GIFT_EFFECT.get()))
            return original;

        if (entity.isShiftKeyDown())
            return original;

        Level level = entity.level();

        int[][] offsets = {
                { 1, 0, 1 }, { 1, 0, 0 }, { 1, -1, 0 }, { 1, 0, -1 },
                { 0, 0, 1 }, { 0, 0, 0 }, { 0, -1, 0 }, { 0, 0, -1 },
                { -1, 0, 1 }, { -1, 0, 0 }, { -1, -1, 0 }, { -1, 0, -1 }
        };

        double highestWaterY = original.y;
        boolean foundWater = false;

        for (int[] offset : offsets) {
            BlockPos sourcePos = entity.blockPosition();
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
                    Shapes.create(entity.getBoundingBox().inflate(0.5)),
                    BooleanOp.AND)) {
                double height = shape.max(Direction.Axis.Y) - entity.getY() - 1;

                if (highestWaterY < height) {
                    highestWaterY = height;
                    foundWater = true;
                }
            }
        }

        if (foundWater) {
            entity.fallDistance = 0F;
            entity.setOnGround(true);

            return new Vec3(original.x, highestWaterY, original.z);
        }
        return original;
    }
}