package dev.williamknowleskellett.tntree.blocks;

import dev.williamknowleskellett.tntree.TnTreeMod;
import dev.williamknowleskellett.tntree.world.tree.TnTreeSaplingGenerator;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block TNTREE_SAPLING = registerBlock("tntree_sapling",
            new SaplingBlock(new TnTreeSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(TnTreeMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(TnTreeMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        TnTreeMod.LOGGER.info("Registering ModBlocks for " + TnTreeMod.MOD_ID);
    }
}
