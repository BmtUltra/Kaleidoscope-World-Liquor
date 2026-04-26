package com.bmt.kaleidoscope_world_liquor.client.renderer;

import com.bmt.kaleidoscope_world_liquor.block.FreezerBlock;
import com.bmt.kaleidoscope_world_liquor.block.entity.FreezerBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import java.util.Random;

public class FreezerRenderer implements BlockEntityRenderer<FreezerBlockEntity> {
    private final ItemRenderer itemRenderer;

    @SuppressWarnings("unused")
    public FreezerRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(FreezerBlockEntity be, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight, int packedOverlay) {
        Level level = be.getLevel();
        if (level == null) return;

        Direction facing = be.getBlockState().getValue(FreezerBlock.FACING);

        if (!be.tank.getFluid().isEmpty()) {
            this.drawFluid(be, poseStack, buffer, packedLight, facing);
        }

        this.drawFloatingItems(be, poseStack, buffer, packedLight, facing);

        if (be.hasOutput() && be.getOutputTexture() != null) {
            this.drawResultTexture(be, poseStack, buffer, packedLight, facing);
        }
    }

    private void drawFluid(FreezerBlockEntity be, PoseStack poseStack, MultiBufferSource source, int light, Direction facing) {
        try {
            Fluid fluid = be.tank.getFluid().getFluid();
            if (fluid == null) return;

            IClientFluidTypeExtensions fluidType = IClientFluidTypeExtensions.of(fluid);
            ResourceLocation textureLoc = fluidType.getStillTexture();

            if (textureLoc == null) {
                textureLoc = ResourceLocation.parse("minecraft:block/water_still");
            }

            @SuppressWarnings("deprecation")
            TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(textureLoc);
            if (sprite == null) return;

            float x, z, width, depth;
            float y = 4F * 0.0625F;
            float maxHeight = 6F * 0.0625F;

            switch (facing) {
                case NORTH, SOUTH -> {
                    x = 0.0625F;
                    z = 2.0F * 0.0625F;
                    width = 15.0F * 0.0625F;
                    depth = 12.0F * 0.0625F;
                }
                case EAST -> {
                    x = 2.0F * 0.0625F;
                    z = 0.5F * 0.0625F;
                    width = 12.0F * 0.0625F;
                    depth = 15.0F * 0.0625F;
                }
                case WEST -> {
                    x = 0.0625F;
                    z = 0.5F * 0.0625F;
                    width = 12.0F * 0.0625F;
                    depth = 15.0F * 0.0625F;
                }
                default -> {
                    x = 2.5F * 0.0625F;
                    z = 0.0625F;
                    width = 12.0F * 0.0625F;
                    depth = 13.0F * 0.0625F;
                }
            }

            float height = maxHeight * ((float) be.tank.getFluidAmount() / (float) be.tank.getCapacity());
            int color = fluidType.getTintColor();
            if (color == 0) color = 0xFFFFFFFF;

            float r = (float) (color >> 16 & 255) / 255.0F, g = (float) (color >> 8 & 255) / 255.0F, b = (float) (color & 255) / 255.0F;

            VertexConsumer consumer = source.getBuffer(RenderType.translucent());
            Matrix4f matrix = poseStack.last().pose();
            float minU = sprite.getU0(), maxU = sprite.getU1(), minV = sprite.getV0(), maxV = sprite.getV1();

            consumer.vertex(matrix, x, y + height, z).color(r, g, b, 1.0F).uv(maxU, minV).uv2(light).normal(0,1,0).endVertex();
            consumer.vertex(matrix, x, y + height, z + depth).color(r, g, b, 1.0F).uv(minU, minV).uv2(light).normal(0,1,0).endVertex();
            consumer.vertex(matrix, x + width, y + height, z + depth).color(r, g, b, 1.0F).uv(minU, maxV).uv2(light).normal(0,1,0).endVertex();
            consumer.vertex(matrix, x + width, y + height, z).color(r, g, b, 1.0F).uv(maxU, maxV).uv2(light).normal(0,1,0).endVertex();

        } catch (Exception ignored) {
        }
    }

    private void drawFloatingItems(FreezerBlockEntity be, PoseStack poseStack, MultiBufferSource buffer, int packedLight, Direction facing) {
        final float pixel = 0.0625F;
        final float centerX = 0.5F;
        final float centerZ = 0.5F;

        final float squareSize = 8 * pixel;
        final float halfSquare = squareSize / 2F;

        float baseY;
        if (be.tank.getFluid().isEmpty()) {
            baseY = 0.2F;
        } else {
            baseY = 0.55F;
        }

        float[][] quadrants = {
                {centerX - halfSquare, centerX,     centerZ - halfSquare, centerZ},
                {centerX,     centerX + halfSquare, centerZ - halfSquare, centerZ},
                {centerX - halfSquare, centerX,     centerZ,     centerZ + halfSquare},
                {centerX,     centerX + halfSquare, centerZ,     centerZ + halfSquare}
        };

        for (int slot = 0; slot < 4; slot++) {
            ItemStack stack = be.inventory.getStackInSlot(slot);
            if (stack.isEmpty()) continue;

            Random random = new Random(be.getBlockPos().hashCode() + slot * 999);
            poseStack.pushPose();

            float[] area = quadrants[slot];
            float minX = area[0];
            float maxX = area[1];
            float minZ = area[2];
            float maxZ = area[3];

            float x = minX + random.nextFloat() * (maxX - minX);
            float z = minZ + random.nextFloat() * (maxZ - minZ);
            float y = baseY + random.nextFloat() * 0.01F + (slot * 0.03F);

            poseStack.translate(x, y, z);
            poseStack.mulPose(Axis.YP.rotationDegrees(random.nextFloat() * 360F));
            poseStack.mulPose(Axis.XP.rotationDegrees(90));
            float scale = 0.30F + random.nextFloat() * 0.05F;
            poseStack.scale(scale, scale, scale);

            itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, be.getLevel(), 0);
            poseStack.popPose();
        }
    }

    @SuppressWarnings("deprecation")
    private void drawResultTexture(FreezerBlockEntity be, PoseStack poseStack, MultiBufferSource source, int light, Direction facing) {
        ResourceLocation textureLoc = be.getOutputTexture();
        int count = be.getOutputCount();
        if (textureLoc == null || count <= 0) return;

        TextureAtlasSprite sprite = Minecraft.getInstance()
                .getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(textureLoc);

        VertexConsumer consumer = source.getBuffer(RenderType.translucent());
        Matrix4f matrix = poseStack.last().pose();

        float x, z, width, depth;
        switch (facing) {
            case NORTH, SOUTH -> {
                x = 0.0625F;
                z = 2.0F * 0.0625F;
                width = 15.0F * 0.0625F;
                depth = 12.0F * 0.0625F;
            }
            case EAST -> {
                x = 2.0F * 0.0625F;
                z = 0.5F * 0.0625F;
                width = 12.0F * 0.0625F;
                depth = 15.0F * 0.0625F;
            }
            case WEST -> {
                x = 0.0625F;
                z = 0.5F * 0.0625F;
                width = 12.0F * 0.0625F;
                depth = 15.0F * 0.0625F;
            }
            default -> {
                x = 2.5F * 0.0625F;
                z = 0.0625F;
                width = 12.0F * 0.0625F;
                depth = 13.0F * 0.0625F;
            }
        }

        float baseY = 12F * 0.0625F;
        float sinkOffset = (5 - count) * 0.08F;
        float y = baseY - sinkOffset;

        float u0 = sprite.getU0();
        float u1 = sprite.getU1();
        float v0 = sprite.getV0();
        float v1 = sprite.getV1();

        consumer.vertex(matrix, x, y, z).color(1F,1F,1F,1F).uv(u1, v0).uv2(light).normal(0,1,0).endVertex();
        consumer.vertex(matrix, x, y, z + depth).color(1F,1F,1F,1F).uv(u1, v1).uv2(light).normal(0,1,0).endVertex();
        consumer.vertex(matrix, x + width, y, z + depth).color(1F,1F,1F,1F).uv(u0, v1).uv2(light).normal(0,1,0).endVertex();
        consumer.vertex(matrix, x + width, y, z).color(1F,1F,1F,1F).uv(u0, v0).uv2(light).normal(0,1,0).endVertex();
    }

    @Override
    public boolean shouldRenderOffScreen(@NotNull FreezerBlockEntity be) {
        return true;
    }
}