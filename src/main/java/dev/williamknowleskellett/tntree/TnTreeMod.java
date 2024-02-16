package dev.williamknowleskellett.tntree;

import dev.williamknowleskellett.tntree.blocks.ModBlocks;
import dev.williamknowleskellett.tntree.world.ModPlacedFeatures;
import dev.williamknowleskellett.tntree.world.tree.ModFoliagePlacerTypes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.GenerationStep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TnTreeMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "tntree";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.registerModBlocks();
		ModFoliagePlacerTypes.register();

//		BiomeModifications.addFeature(
//				BiomeSelectors.foundInOverworld(),
//				GenerationStep.Feature.VEGETAL_DECORATION,
//				RegistryKey.of(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures.TNTREE_PLACED_ID));

		
        // BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.TNTREE_PLACED_KEY);
	            // GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_CHERRY);//ModPlacedFeatures.TNTREE_PLACED_KEY);
	}
}