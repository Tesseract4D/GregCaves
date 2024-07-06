package supercoder79.greggen.fix;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderFlat;
import net.minecraft.world.gen.MapGenCaves;
import net.tclproject.mysteriumlib.asm.annotations.EnumReturnSetting;
import net.tclproject.mysteriumlib.asm.annotations.Fix;
import supercoder79.greggen.world.GregGenChunkGenerator;

import static net.minecraft.world.WorldType.FLAT;

public class FixesWorldType {
    @Fix(returnSetting = EnumReturnSetting.ALWAYS)
    public static IChunkProvider getChunkGenerator(WorldType c, World world, String generatorOptions) {
        return c == FLAT ? new ChunkProviderFlat(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions) : new GregGenChunkGenerator(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
    }

    @Fix(returnSetting = EnumReturnSetting.ALWAYS,insertOnLine = )
    public static void digBlock(MapGenCaves c, Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop) {
    }
}
