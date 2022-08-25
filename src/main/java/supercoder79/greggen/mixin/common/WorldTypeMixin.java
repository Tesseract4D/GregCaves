package supercoder79.greggen.mixin.common;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import supercoder79.greggen.world.GregGenChunkGenerator;

@Mixin(WorldType.class)
public class WorldTypeMixin{
    @Overwrite(remap = false)
    public IChunkProvider getChunkGenerator(World world, String generatorOptions)
    {
       return new GregGenChunkGenerator(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
    }
}
