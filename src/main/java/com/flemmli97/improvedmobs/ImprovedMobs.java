package com.flemmli97.improvedmobs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.flemmli97.improvedmobs.handler.CommandIMDifficulty;
import com.flemmli97.improvedmobs.handler.config.ConfigHandler;

import net.minecraft.world.GameRules.ValueType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = ImprovedMobs.MODID, name = ImprovedMobs.MODNAME, version = ImprovedMobs.VERSION, guiFactory = "com.flemmli97.improvedmobs.GuiFactory", dependencies = "required:tenshilib;")
public class ImprovedMobs {

    public static final String MODID = "improvedmobs";
    public static final String MODNAME = "Improved Mobs";
    public static final String VERSION = "${@VERSION}";
    public static final Logger logger = LogManager.getLogger(ImprovedMobs.MODID);
        
    @Instance
    public static ImprovedMobs instance = new ImprovedMobs();
        
     
    @SidedProxy(clientSide="com.flemmli97.improvedmobs.ClientProxy", serverSide="com.flemmli97.improvedmobs.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
    
    @EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
    	if(ConfigHandler.enableDifficultyScaling)
    		event.registerServerCommand(new CommandIMDifficulty());
    	if(!event.getServer().getWorld(0).getGameRules().hasRule("doIMDifficulty"))
    		event.getServer().getWorld(0).getGameRules().addGameRule("doIMDifficulty", "true", ValueType.BOOLEAN_VALUE);
    }
}
    