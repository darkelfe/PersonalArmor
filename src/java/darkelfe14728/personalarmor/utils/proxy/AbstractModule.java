package darkelfe14728.personalarmor.utils.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import darkelfe14728.personalarmor.PersonalArmor;


/**
 * @author Julien Rosset
 * 
 *         Mod's modules interface.
 */
public abstract class AbstractModule
{
    public abstract String getPrefix();
    public String getNamePrefix()
    {
        return this.getPrefix() + ".";
    }
    public String getTexturePrefix()
    {
        return PersonalArmor.MOD_ID + ":" + this.getPrefix() + "/";
    }

    public abstract void preInit(FMLPreInitializationEvent event);
    public abstract void init(FMLInitializationEvent event);
    public abstract void postInit(FMLPostInitializationEvent event);
}
