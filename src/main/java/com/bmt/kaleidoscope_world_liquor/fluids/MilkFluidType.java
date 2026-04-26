package com.bmt.kaleidoscope_world_liquor.fluids;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class MilkFluidType extends FluidType {
    public MilkFluidType(Properties properties) {
        super(properties);
    }

    @Override
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {
            @Override
            public @NotNull ResourceLocation getStillTexture() {
                return ResourceLocation.parse("kaleidoscope_world_liquor:block/milk_still");
            }

            @Override
            public @NotNull ResourceLocation getFlowingTexture() {
                return ResourceLocation.parse("kaleidoscope_world_liquor:block/milk_flowing");
            }

            @Override
            public int getTintColor() {
                return IClientFluidTypeExtensions.super.getTintColor();
            }
        });
    }
}