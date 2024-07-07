package mods.tesseract.gregcave;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.tclproject.mysteriumlib.asm.common.CustomLoadingPlugin;
import net.tclproject.mysteriumlib.asm.common.FirstClassTransformer;

@Mod(modid = "gregcave")
public final class GregCave extends CustomLoadingPlugin {
    public String[] getASMTransformerClass() {
        return new String[]{FirstClassTransformer.class.getName()};
    }

    public void registerFixes() {
        registerClassWithFixes("mods.tesseract.gregcave.fix.FixesWorldType");
    }
}