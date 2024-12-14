package mods.tesseract.gregcaves;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cn.tesseract.mycelium.asm.minecraft.HookLoader;
import mods.tesseract.gregcaves.hook.GregCavesHook;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

@Mod(modid = "gregcaves")
public final class GregCaves extends HookLoader {
    public static boolean smoothBedrock;
    public static boolean reduceOreGen;
    public static int reduceOreGenY;
    public static float reduceOreGenRate;
    public static int caveLavaLevel;
    public static boolean disableUndergroundLiquid;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        syncConfig(e.getSuggestedConfigurationFile());
        MinecraftForge.TERRAIN_GEN_BUS.register(new GregCavesHook());
    }

    public static void syncConfig(File f) {
        Configuration configuration = new Configuration(f);
        smoothBedrock = configuration.getBoolean("smoothBedrock", Configuration.CATEGORY_GENERAL, false, "Only generates one layer of bedrock.");
        reduceOreGen = configuration.getBoolean("reduceOreGen", Configuration.CATEGORY_GENERAL, true, "Reduce ores in the deep.");
        reduceOreGenY = configuration.getInt("reduceOreGenY", Configuration.CATEGORY_GENERAL, 33, 0, 256, "Reduce ores start height.");
        reduceOreGenRate = configuration.getFloat("reduceOreGenRate", Configuration.CATEGORY_GENERAL, 0.6F, 0, 1, "1 = all, 0 = does nothing");
        caveLavaLevel = configuration.getInt("caveLavaLevel", Configuration.CATEGORY_GENERAL, 2, 0, 256, "For greg caves requires smoothBedrock true to work.");
        disableUndergroundLiquid = configuration.getBoolean("disableUndergroundLiquid", Configuration.CATEGORY_GENERAL, true, "Disable water and lava source generations in underground.");
        if (configuration.hasChanged())
            configuration.save();
    }

    @Override
    protected void registerHooks() {
        registerHookContainer("mods.tesseract.gregcaves.hook.GregCavesHook");
    }
}
