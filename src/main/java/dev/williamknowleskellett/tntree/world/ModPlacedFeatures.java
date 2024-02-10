package dev.williamknowleskellett.tntree;

import java.util.List;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

public class ModPlacedFeatures {
    // public static final RegistryKey<PlacedFeature> DOGWOOD_CHECKED_KEY =
    // registerKey("dogwood_checked");
    public static final Identifier TNTREE_PLACED_ID = new Identifier(TnTreeMod.MOD_ID, "tntree_placed");
    public static final RegistryKey<PlacedFeature> TNTREE_PLACED_KEY = registerKey("tntree_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        // TnTreeMod.LOGGER.info("placed bootstrap");

        RegistryEntryLookup<ConfiguredFeature<?, ?>> configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        // register(context, DOGWOOD_CHECKED_KEY,
        // configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.DOGWOOD_KEY),
        // List.of(PlacedFeatures.wouldSurvive(ModBlocks.DOGWOOD_SAPLING)));
        register(context, TNTREE_PLACED_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.TNTREE_KEY),
                VegetationPlacedFeatures.treeModifiers(PlacedFeatures.createCountExtraModifier(10, 0.1f, 1)));

    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(TnTreeMod.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
            RegistryEntry<ConfiguredFeature<?, ?>> configuration,
            List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
