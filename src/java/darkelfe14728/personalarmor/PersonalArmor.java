package darkelfe14728.personalarmor;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * @author Julien Rosset
 *
 * Mod entry point.
 */
@Mod(
    modid        = PersonalArmor.MOD_ID,
    name         = PersonalArmor.MOD_NAME,
    version      = PersonalArmor.MOD_VERSION
)
public class PersonalArmor
{
    public static final String   MOD_ID          = "personalarmor";
    public static final String   MOD_NAME        = "Personal Armor";
    public static final String   MOD_VERSION     = "0.0.1";
    public static final String   MOD_DESCRIPTION = "Not a simply armor : powerful and modular";
    public static final String[] MOD_AUTHORS     = {"Julien Rosset"};
    public static final String   MOD_CREDITS     = "";
    
    @Instance
    public static PersonalArmor instance = new PersonalArmor();
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {}
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {}
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {}
}
