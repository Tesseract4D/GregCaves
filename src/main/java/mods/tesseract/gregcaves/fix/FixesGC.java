package mods.tesseract.gregcaves.fix;

import mods.tesseract.gregcaves.world.ChunkGeneratorGC;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderFlat;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.tclproject.mysteriumlib.asm.annotations.EnumReturnSetting;
import net.tclproject.mysteriumlib.asm.annotations.Fix;
import net.tclproject.mysteriumlib.asm.annotations.LocalVariable;

import static net.minecraft.world.WorldType.FLAT;

public class FixesGC {
    @Fix(returnSetting = EnumReturnSetting.ALWAYS)
    public static IChunkProvider getChunkGenerator(WorldType c, World world, String generatorOptions) {
        return c == FLAT ? new ChunkProviderFlat(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions) : new ChunkGeneratorGC(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
    }

    @Fix(insertOnExit = true, targetMethod = "<init>")
    public static void BiomeDecorator(BiomeDecorator c) {
        c.generateLakes = false;
    }

    @Fix(returnSetting = EnumReturnSetting.ALWAYS, insertOnLine = 9)
    public static void digBlock(MapGenCaves c, Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop, @LocalVariable(index = 10) Block top, @LocalVariable(index = 11) Block filler, @LocalVariable(index = 12) Block block) {
        if (block == Blocks.stone || block == filler || block == top) {
            if (y < 2) {
                data[index] = Blocks.lava;
            } else {
                data[index] = null;

                if (foundTop && data[index - 1] == filler) {
                    data[index - 1] = top;
                }
            }
        }
    }

    @Fix(returnSetting = EnumReturnSetting.ALWAYS, insertOnLine = 9)
    public static void digBlock(MapGenRavine c, Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop, @LocalVariable(index = 10) Block top, @LocalVariable(index = 11) Block filler, @LocalVariable(index = 12) Block block) {
        if (block == Blocks.stone || block == filler || block == top) {
            data[index] = null;
            if (foundTop && data[index - 1] == filler) {
                data[index - 1] = top;
            }
        }
    }
}
