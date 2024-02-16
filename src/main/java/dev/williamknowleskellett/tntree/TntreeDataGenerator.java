package dev.williamknowleskellett.tntree;

import dev.williamknowleskellett.tntree.data.ModLootTableProvider;
import dev.williamknowleskellett.tntree.data.ModModelProvider;
import dev.williamknowleskellett.tntree.data.ModWorldGenerator;
import dev.williamknowleskellett.tntree.world.ModConfiguredFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class TnTreeDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        // TnTreeMod.LOGGER.info("datagen init");
        FabricDataGenerator.Pack pack = 
        fabricDataGenerator.createPack();
        // BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.TNTREE_PLACED_KEY);

        pack.addProvider(ModLootTableProvider::new);
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModWorldGenerator::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        // TnTreeMod.LOGGER.info("datagen buildregistry");

        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
//        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
    }
}
