package darkelfe14728.personalarmor.armor;

import net.minecraft.item.Item;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import darkelfe14728.personalarmor.armor.part.ArmorPartRegistry;
import darkelfe14728.personalarmor.utils.LogHelper;
import darkelfe14728.personalarmor.utils.proxy.AbstractModule;
import darkelfe14728.personalarmor.utils.registry.RegistryHelper;


/**
 * @author Julien Rosset
 * 
 */
public class ArmorModule
    extends AbstractModule
{
    public static final String      MODULE_ID   = "armor";
    public static final String      MODULE_NAME = "Armor";

    public static final ArmorModule instance    = new ArmorModule();

    public static final ArmorPartRegistry Parts = new ArmorPartRegistry(); 
    public static class Items
    {
        public static final Item ArmorSchematic = new ArmorSchematicItem();
    }

    @Override
    public String getPrefix()
    {
        return ArmorModule.MODULE_ID;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        LogHelper.startBlock(ArmorModule.MODULE_NAME);
        LogHelper.stopBlock();
    }
    @Override
    public void init(FMLInitializationEvent event)
    {
        LogHelper.startBlock(ArmorModule.MODULE_NAME);
        RegistryHelper.registerItem(Items.ArmorSchematic);
        LogHelper.stopBlock();
    }
    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        LogHelper.startBlock(ArmorModule.MODULE_NAME);
        LogHelper.stopBlock();
    }
}
