package dev.williamknowleskellett.tntree.data;

import dev.williamknowleskellett.tntree.blocks.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Blocks;

public class ModLootTableProvider extends FabricBlockLootTableProvider{

    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.TNTREE_SAPLING);
        //addDrop(Blocks.OAK_LEAVES,leavesDrops(Blocks.OAK_LEAVES, ModBlocks.TNTREE_SAPLING,0.0025f));
    }
    
}
