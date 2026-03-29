package com.chenjdy.kaleidoscope_world_liquor.init;

import com.chenjdy.kaleidoscope_world_liquor.KaleidoscopeWorldLiquor;
import com.github.ysbbbbbb.kaleidoscopetavern.fluid.JuiceFluidType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraftforge.registries.ForgeRegistries.FLUIDS;
import static net.minecraftforge.registries.ForgeRegistries.Keys.FLUID_TYPES;

public class ModFluids {
    public static final ResourceLocation CORN_JUICE_ID = KaleidoscopeWorldLiquor.id("corn_juice");
    public static final ResourceLocation FLOWING_CORN_JUICE_ID = KaleidoscopeWorldLiquor.id("flowing_corn_juice");

    public static final ResourceLocation SUGAR_CANE_JUICE_ID = KaleidoscopeWorldLiquor.id("sugar_cane_juice");
    public static final ResourceLocation FLOWING_SUGAR_CANE_JUICE_ID = KaleidoscopeWorldLiquor.id("flowing_sugar_cane_juice");

    public static final RegistryObject<FluidType> CORN_JUICE_TYPE = RegistryObject.create(CORN_JUICE_ID, FLUID_TYPES.location(), KaleidoscopeWorldLiquor.MODID);
    public static final RegistryObject<FluidType> SUGAR_CANE_JUICE_TYPE = RegistryObject.create(SUGAR_CANE_JUICE_ID, FLUID_TYPES.location(), KaleidoscopeWorldLiquor.MODID);

    public static final RegistryObject<Fluid> CORN_JUICE = RegistryObject.create(CORN_JUICE_ID, FLUIDS);
    public static final RegistryObject<Fluid> FLOWING_CORN_JUICE = RegistryObject.create(FLOWING_CORN_JUICE_ID, FLUIDS);

    public static final RegistryObject<Fluid> SUGAR_CANE_JUICE = RegistryObject.create(SUGAR_CANE_JUICE_ID, FLUIDS);
    public static final RegistryObject<Fluid> FLOWING_SUGAR_CANE_JUICE = RegistryObject.create(FLOWING_SUGAR_CANE_JUICE_ID, FLUIDS);

    public static void register(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.FLUID_TYPES, helper -> {
            helper.register(CORN_JUICE_ID, new JuiceFluidType (CORN_JUICE_ID, 0));
            helper.register(SUGAR_CANE_JUICE_ID, new JuiceFluidType (SUGAR_CANE_JUICE_ID, 0));
        });

        event.register(ForgeRegistries.Keys.FLUIDS, helper -> {
            ForgeFlowingFluid.Properties cornJuice = new ForgeFlowingFluid.Properties(CORN_JUICE_TYPE, CORN_JUICE, FLOWING_CORN_JUICE)
                    .bucket(ModItems.CORN_BUCKET);
            ForgeFlowingFluid.Properties sugar_caneJuice = new ForgeFlowingFluid.Properties(SUGAR_CANE_JUICE_TYPE, SUGAR_CANE_JUICE, FLOWING_SUGAR_CANE_JUICE)
                    .bucket(ModItems.SUGAR_CANE_BUCKET);

            helper.register(CORN_JUICE_ID, new ForgeFlowingFluid.Source(cornJuice));
            helper.register(FLOWING_CORN_JUICE_ID, new ForgeFlowingFluid.Flowing(cornJuice));

            helper.register(SUGAR_CANE_JUICE_ID, new ForgeFlowingFluid.Source(sugar_caneJuice));
            helper.register(FLOWING_SUGAR_CANE_JUICE_ID, new ForgeFlowingFluid.Flowing(sugar_caneJuice));
        });
    }
}
