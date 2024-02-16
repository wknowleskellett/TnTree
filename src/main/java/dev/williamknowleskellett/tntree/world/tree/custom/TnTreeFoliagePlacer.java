package dev.williamknowleskellett.tntree.world.tree.custom;

import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.williamknowleskellett.tntree.world.tree.ModFoliagePlacerTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class TnTreeFoliagePlacer extends FoliagePlacer {
    public static final Codec<TnTreeFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> createCodec(instance).apply(instance, TnTreeFoliagePlacer::new));
    protected final int height;

    protected static <P extends TnTreeFoliagePlacer> Products.P3<RecordCodecBuilder.Mu<P>, IntProvider, IntProvider, Integer> createCodec(RecordCodecBuilder.Instance<P> builder) {
        return fillFoliagePlacerFields(builder).and(Codec.intRange(0, 16).fieldOf("height").forGetter((placer) -> placer.height));
    }

    public TnTreeFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    protected FoliagePlacerType<?> getType() {
        return ModFoliagePlacerTypes.TNTREE_FOLIAGE_PLACER;
    }

    protected void generate(TestableWorld world, FoliagePlacer.BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, FoliagePlacer.TreeNode treeNode, int foliageHeight, int radius, int offset) {
        BlockPos center = treeNode.getCenter();
        int foliageRadius = treeNode.getFoliageRadius();
        boolean giantTrunk = treeNode.isGiantTrunk();

        if (!this.isPositionInvalid(random, 0, offset, 0, Math.max(radius + foliageRadius - 1 - offset / 2, 0), giantTrunk)) {
            BlockPos.Mutable pos = new BlockPos.Mutable().set(center, 0, offset, 0);
            placeFoliageBlock(world, placer, random, config, pos);
            if (TreeFeature.canReplace(world, pos)) {
                BlockState blockState = BlockStateProvider.of(Blocks.TNT).get(random, pos);

                placer.placeBlock(pos, blockState);
            }
        }

        for(int i = offset; i >= offset - foliageHeight; --i) {
            int j = Math.max(radius + foliageRadius - 1 - i / 2, 0);
            this.generateSquare(world, placer, random, config, center, j, i, giantTrunk);
        }
    }

    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return this.height;
    }

    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return dx == radius && dz == radius && (random.nextInt(2) == 0 || y == 0);
    }

    protected static boolean placeFoliageBlock(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, BlockPos pos) {
        if (!TreeFeature.canReplace(world, pos)) {
            return false;
        } else {
            BlockState blockState = config.foliageProvider.get(random, pos);
            if (blockState.contains(Properties.WATERLOGGED)) {
                blockState = blockState.with(Properties.WATERLOGGED, world.testFluidState(pos, (fluidState) -> fluidState.isEqualAndStill(Fluids.WATER)));
            }

            placer.placeBlock(pos, blockState);
            return true;
        }
    }
}
