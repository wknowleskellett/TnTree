package dev.williamknowleskellett.tntree;

import dev.williamknowleskellett.tntree.data.ModLootTableGenerator;
import dev.williamknowleskellett.tntree.data.ModModelProvider;
import dev.williamknowleskellett.tntree.data.ModRecipeGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryBuilder.BootstrapFunction;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;

public class TnTreeDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        // TnTreeMod.LOGGER.info("datagen init");
        FabricDataGenerator.Pack pack = 
        fabricDataGenerator.createPack();
        // BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.TNTREE_PLACED_KEY);

        pack.addProvider(ModLootTableGenerator::new);
        pack.addProvider(ModRecipeGenerator::new);
        pack.addProvider(ModModelProvider::new);
        // pack.addProvider(ModWorldGenerator::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        // TnTreeMod.LOGGER.info("datagen buildregistry");

        BootstrapFunction<ConfiguredFeature<?, ?>> configuredFeatureBootstrap = new BootstrapFunction<ConfiguredFeature<?, ?>>() {
            @Override
            public void run(Registerable<ConfiguredFeature<?, ?>> registerable) {
                ModConfiguredFeatures.bootstrap(registerable);
            }
        };

        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, configuredFeatureBootstrap);

        BootstrapFunction<PlacedFeature> placedFeatureBootstrap = new BootstrapFunction<PlacedFeature>() {
            @Override
            public void run(Registerable<PlacedFeature> registerable) {
                ModPlacedFeatures.bootstrap(registerable);
            }
        };
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, placedFeatureBootstrap);
    }
}
