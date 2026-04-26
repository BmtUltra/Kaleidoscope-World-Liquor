package com.bmt.kaleidoscope_world_liquor.init;

import com.bmt.kaleidoscope_world_liquor.KaleidoscopeWorldLiquor;
import com.bmt.kaleidoscope_world_liquor.fluids.MilkFluidType;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluidTypes {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, KaleidoscopeWorldLiquor.MODID);

    public static final RegistryObject<FluidType> MILK_TYPE = FLUID_TYPES.register("milk",
            () -> new MilkFluidType(FluidType.Properties.create()
                    .descriptionId("fluid.kaleidoscope_world_liquor.milk")
                    .canConvertToSource(false)
                    .canDrown(true)
                    .canExtinguish(false)
                    .canHydrate(false)
                    .canPushEntity(true)
                    .canSwim(true))
    );
}