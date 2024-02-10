package dev.williamknowleskellett.tntree;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> TNTREE_KEY = registerKey("tntree");

    public static void shrimp(Registerable<String> a) {
    }

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        // var placedFeatureRegistryEntryLookup =
        // context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);

        TnTreeMod.LOGGER.info("configured bootstrap");

        register(context,
                TNTREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(Blocks.ANCIENT_DEBRIS),
                        new StraightTrunkPlacer(5, 6, 3),
                        BlockStateProvider.of(Blocks.BLUE_CONCRETE),
                        new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 4),
                        new TwoLayersFeatureSize(1, 0, 2)).build());

        // register(context, DOGWOOD_SPAWN_KEY, Feature.RANDOM_SELECTOR,
        // new RandomFeatureConfig(List.of(new
        // RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(ModPlacedFeatures.DOGWOOD_PLACED_KEY),
        // 0.5f)),
        // placedFeatureRegistryEntryLookup.getOrThrow(ModPlacedFeatures.DOGWOOD_PLACED_KEY)));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(TnTreeMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(
            Registerable<ConfiguredFeature<?, ?>> context,
            RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
