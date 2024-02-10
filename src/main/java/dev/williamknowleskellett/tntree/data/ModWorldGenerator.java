package dev.williamknowleskellett.tntree.data;

import java.util.concurrent.CompletableFuture;

import dev.williamknowleskellett.tntree.TnTreeMod;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;

public class ModWorldGenerator extends FabricDynamicRegistryProvider {

    public ModWorldGenerator(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public String getName() {
        return TnTreeMod.MOD_ID;
    }

    @Override
    protected void configure(WrapperLookup registries, Entries entries) {
    }
}
