package mods.tesseract.gregcaves;

import cpw.mods.fml.common.Mod;
import mods.tesseract.gregcaves.world.ChunkGeneratorGC;
import net.tclproject.mysteriumlib.asm.common.CustomLoadingPlugin;
import net.tclproject.mysteriumlib.asm.common.FirstClassTransformer;
import rwg.world.ChunkGeneratorRealistic;

@Mod(modid = "gregcaves")
public final class GregCaves extends CustomLoadingPlugin {
    public String[] getASMTransformerClass() {
        return new String[]{FirstClassTransformer.class.getName()};
    }

    public void registerFixes() {
        registerClassWithFixes("mods.tesseract.gregcaves.fix.FixesGC");
    }
}
