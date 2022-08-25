package supercoder79.greggen;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import supercoder79.greggen.world.GregGenBOPWorldType;
import supercoder79.greggen.world.GregGenWorldType;

@Mod(modid = Tags.MODID, version = Tags.VERSION, name = Tags.MODNAME, acceptedMinecraftVersions = "[1.7.10]")
public final class GregGen {

	private GregGenWorldType worldType;
	private GregGenBOPWorldType worldTypeBop;

	@Mod.EventHandler
	public void onModPreInit(FMLPreInitializationEvent aEvent) {
		this.worldType = new GregGenWorldType();

		// Add bop world type
		if (Loader.isModLoaded("BiomesOPlenty")) {
			this.worldTypeBop = new GregGenBOPWorldType();
		}
	}
}
