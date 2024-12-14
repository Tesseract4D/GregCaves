package mods.tesseract.gregcaves.hook;

import cn.tesseract.mycelium.asm.Hook;
import cn.tesseract.mycelium.asm.ReturnCondition;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mods.tesseract.gregcaves.GregCaves;
import mods.tesseract.gregcaves.world.MapGenGregCaves;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.event.terraingen.InitMapGenEvent;

import java.util.Random;

public class GregCavesHook {
    @SubscribeEvent
    public void mapGen(InitMapGenEvent e) {
        if (e.type == InitMapGenEvent.EventType.CAVE)
            e.newGen = new MapGenGregCaves();
    }

    @Hook(injectOnExit = true, targetMethod = "<init>")
    public static void BiomeDecorator(BiomeDecorator c) {
        c.generateLakes = false;
    }

    @Hook(injectOnExit = true)
    public static void genBiomeTerrain(BiomeGenBase c, World w, Random r, Block[] blocks, byte[] b, int cx, int cz, double d) {
        if (GregCaves.smoothBedrock) {
            int i1 = cx & 15;
            int j1 = cz & 15;
            int k1 = blocks.length / 256;
            for (int l1 = 5; l1 > 0; --l1) {
                int i2 = (j1 * 16 + i1) * k1 + l1;
                if (blocks[i2] == Blocks.bedrock)
                    blocks[i2] = Blocks.stone;
            }
        }
    }

    @Hook(returnCondition = ReturnCondition.ON_TRUE)
    public static boolean generate(WorldGenMinable c, World w, Random r, int x, int y, int z) {
        if (GregCaves.reduceOreGen && y < GregCaves.reduceOreGenY && w.provider.dimensionId == 0)
            return r.nextFloat() < GregCaves.reduceOreGenRate;
        return false;
    }

    @Hook(returnCondition = ReturnCondition.ALWAYS, injectOnLine = 9)
    public static void digBlock(MapGenRavine c, Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop, @Hook.LocalVariable(index = 10) Block top, @Hook.LocalVariable(index = 11) Block filler, @Hook.LocalVariable(index = 12) Block block) {
        if (block == Blocks.stone || block == filler || block == top) {
            if (y < GregCaves.caveLavaLevel - 1) {
                data[index] = Blocks.flowing_lava;
            } else {
                data[index] = null;
                if (foundTop && data[index - 1] == filler) {
                    data[index - 1] = top;
                }
            }
        }
    }
}
