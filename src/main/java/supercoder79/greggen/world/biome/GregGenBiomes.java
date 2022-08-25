package supercoder79.greggen.world.biome;

import net.minecraft.world.biome.BiomeGenBase;
import supercoder79.greggen.util.BiomePicker;

import java.util.HashMap;
import java.util.Map;

public final class GregGenBiomes {
    public static final Map<Integer, ShadowBiome> SHADOW_BIOMES = new HashMap<>();
    public static final Map<Integer, BiomePicker> BIOME_PICKERS = new HashMap<>();

    // Savanna biomes
    public static final ShadowBiome SAVANNA_SCRUB = new SavannaScrubBiome(256);
    public static final ShadowBiome SPRUCE_SAVANNA = new SpruceSavannaBiome(257);

    public static void init() {
        // -1 means use existing id
        BIOME_PICKERS.computeIfAbsent(BiomeGenBase.savanna.biomeID, (id) -> new BiomePicker()).add(-1, 1.0);
    }
}
