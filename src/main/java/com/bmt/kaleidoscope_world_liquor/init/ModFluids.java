package com.bmt.kaleidoscope_world_liquor.init;

import com.bmt.kaleidoscope_world_liquor.fluids.MilkFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, "kaleidoscope_world_liquor");

    public static RegistryObject<ForgeFlowingFluid> MILK_STILL;
    public static RegistryObject<ForgeFlowingFluid> MILK_FLOWING;

    static {
        ForgeFlowingFluid.Properties properties = new ForgeFlowingFluid.Properties(
                ModFluidTypes.MILK_TYPE,
                () -> MILK_STILL.get(),
                () -> MILK_FLOWING.get()
        ).bucket(() -> net.minecraft.world.item.Items.MILK_BUCKET)
                .block(ModBlocks.MILK_LIQUID_BLOCK);

        MILK_STILL = FLUIDS.register("milk_still", () -> new MilkFluid.Still(properties));
        MILK_FLOWING = FLUIDS.register("milk_flowing", () -> new MilkFluid.Flowing(properties));
    }
}