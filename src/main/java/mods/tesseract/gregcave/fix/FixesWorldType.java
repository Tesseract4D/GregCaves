package mods.tesseract.gregcave.fix;

import mods.tesseract.gregcave.world.ChunkGeneratorGC;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderFlat;
import net.tclproject.mysteriumlib.asm.annotations.EnumReturnSetting;
import net.tclproject.mysteriumlib.asm.annotations.Fix;

import static net.minecraft.world.WorldType.FLAT;

public class FixesWorldType {
    @Fix(returnSetting = EnumReturnSetting.ALWAYS)
    public static IChunkProvider getChunkGenerator(WorldType c, World world, String generatorOptions) {
        return c == FLAT ? new ChunkProviderFlat(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions) : new ChunkGeneratorGC(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
    }

    @Fix(insertOnExit = true, targetMethod = "<init>")
    public static void BiomeDecorator(BiomeDecorator c) {
        c.generateLakes = false;
    }
}