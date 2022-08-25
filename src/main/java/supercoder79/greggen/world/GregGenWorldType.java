package supercoder79.greggen.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;

public final class GregGenWorldType extends WorldType {
    public GregGenWorldType() {
        super("greggen");
    }

    @Override
    public IChunkProvider getChunkGenerator(World world, String generatorOptions) {
        return new GregGenChunkGenerator(world, world.getSeed(), true);
    }

    @Override
    public WorldChunkManager getChunkManager(World world) {
        return super.getChunkManager(world);
    }
}
