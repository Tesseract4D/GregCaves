package mods.tesseract.gregcaves;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mods.tesseract.gregcaves.world.MapGenGregCaves;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.tclproject.mysteriumlib.asm.common.CustomLoadingPlugin;
import net.tclproject.mysteriumlib.asm.common.FirstClassTransformer;

import java.io.File;

@Mod(modid = "gregcaves")
public final class GregCaves extends CustomLoadingPlugin {
    public static boolean smoothBedrock;
    public static boolean reduceOreGen;
    public static int reduceOreGenY;
    public static float reduceOreGenRate;
    public static int caveLavaLevel;
    public static boolean disableUndergroundLiquid;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        syncConfig(e.getSuggestedConfigurationFile());
        MinecraftForge.TERRAIN_GEN_BUS.register(this);
    }

    @SubscribeEvent
    public void mapGen(InitMapGenEvent e) {
        if (e.type == InitMapGenEvent.EventType.CAVE)
            e.newGen = new MapGenGregCaves();
    }

    public static void syncConfig(File f) {
        Configuration configuration = new Configuration(f);
        smoothBedrock = configuration.getBoolean("smoothBedrock", Configuration.CATEGORY_GENERAL, true, "Only generates one layer of bedrock.");
        reduceOreGen = configuration.getBoolean("reduceOreGen", Configuration.CATEGORY_GENERAL, true, "Reduce ores in the deep.");
        reduceOreGenY = configuration.getInt("reduceOreGenY", Configuration.CATEGORY_GENERAL, 33, 0, 256, "Reduce ores start height.");
        reduceOreGenRate = configuration.getFloat("reduceOreGenRate", Configuration.CATEGORY_GENERAL, 0.7F,0,1, "1 = all, 0 = does nothing");
        caveLavaLevel = configuration.getInt("caveLavaLevel", Configuration.CATEGORY_GENERAL, 2, 0, 256, "");
        disableUndergroundLiquid = configuration.getBoolean("disableUndergroundLiquid", Configuration.CATEGORY_GENERAL, true, "Disable water and lava source generations in underground.");
        if (configuration.hasChanged())
            configuration.save();
    }

    public String[] getASMTransformerClass() {
        return new String[]{FirstClassTransformer.class.getName()};
    }

    public void registerFixes() {
        registerClassWithFixes("mods.tesseract.gregcaves.fix.FixesGC");
    }
}
