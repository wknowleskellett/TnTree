package dev.williamknowleskellett.tntree.world.tree;

import dev.williamknowleskellett.tntree.TnTreeMod;
import dev.williamknowleskellett.tntree.mixin.FoliagePlacerTypeInvoker;
import dev.williamknowleskellett.tntree.world.tree.custom.TnTreeFoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class ModFoliagePlacerTypes {
    public static final FoliagePlacerType<?> TNTREE_FOLIAGE_PLACER = FoliagePlacerTypeInvoker.callRegister("tntree_foliage_placer", TnTreeFoliagePlacer.CODEC);

    public static void register() {
        TnTreeMod.LOGGER.info("Registering Foliage Placer for " + TnTreeMod.MOD_ID);
    }
}
