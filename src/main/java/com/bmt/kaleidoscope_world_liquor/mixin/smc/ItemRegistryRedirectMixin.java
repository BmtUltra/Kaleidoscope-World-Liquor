package com.bmt.kaleidoscope_world_liquor.mixin.smc;

import com.starmeow.smc.init.ItemRegistry;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Supplier;

@Mixin(ItemRegistry.class)
public class ItemRegistryRedirectMixin {

    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraftforge/registries/DeferredRegister;register(Ljava/lang/String;Ljava/util/function/Supplier;)Lnet/minecraftforge/registries/RegistryObject;"
            ),
            remap = false
    )
    private static RegistryObject<Item> redirectIceTeaRegistration(
            DeferredRegister<Item> instance,
            String name,
            Supplier<Item> supplier
    ) {
        if ("ice_tea".equals(name)) {
            return instance.register(name, () -> new Item(new Item.Properties()));
        }
        return instance.register(name, supplier);
    }
}