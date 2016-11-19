package darkelfe14728.personalarmor.armor;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import darkelfe14728.personalarmor.utils.LogHelper;
import darkelfe14728.personalarmor.utils.proxy.AbstractModule;

/**
 * @author Julien Rosset
 *
 */
public class ArmorModule
    extends AbstractModule
{
    public static final String MODULE_ID   = "armor";
    public static final String MODULE_NAME = "Armor";

    public static final ArmorModule instance = new ArmorModule();
    
    private AbstractModule[] submodules = {};

    @Override
    public String getPrefix()
    {
        return MODULE_ID;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        LogHelper.startBlock(MODULE_NAME);
        for(AbstractModule submodule : submodules)
        {
            submodule.preInit(event);
        }
        LogHelper.stopBlock();
    }
    @Override
    public void init(FMLInitializationEvent event)
    {
        LogHelper.startBlock(MODULE_NAME);
        for(AbstractModule submodule : submodules)
        {
            submodule.init(event);
        }
        LogHelper.stopBlock();
    }
    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        LogHelper.startBlock(MODULE_NAME);
        for(AbstractModule submodule : submodules)
        {
            submodule.postInit(event);
        }
        LogHelper.stopBlock();
    }
}
