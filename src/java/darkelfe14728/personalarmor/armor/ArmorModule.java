package darkelfe14728.personalarmor.armor;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import darkelfe14728.personalarmor.armor.material.MaterialRegistry;
import darkelfe14728.personalarmor.armor.part.ArmorPartRegistry;
import darkelfe14728.personalarmor.core.LogHelper;
import darkelfe14728.personalarmor.core.proxy.AbstractModule;
import darkelfe14728.personalarmor.core.registry.RegistryHelper;
import darkelfe14728.personalarmor.core.utils.ArmorType;


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

    public static final ArmorPartRegistry Parts     = new ArmorPartRegistry(); 
    public static final MaterialRegistry  Materials = new MaterialRegistry();
    public static class Items
    {
        public static final Item ArmorSchematicItem = new ArmorSchematicItem();
        public static final Item ArmorItem          = new ArmorItem();
        
        public static final Item ArmorWornHelmet     = new ArmorWornItem(ArmorType.HELMET    );
        public static final Item ArmorWornChestplate = new ArmorWornItem(ArmorType.CHESTPLATE);
        public static final Item ArmorWornLeggings   = new ArmorWornItem(ArmorType.LEGGINGS  );
        public static final Item ArmorWornBoots      = new ArmorWornItem(ArmorType.BOOTS     );
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
        RegistryHelper.registerItem(Items.ArmorSchematicItem);
        RegistryHelper.registerItem(Items.ArmorItem);
        
        RegistryHelper.registerItem(Items.ArmorWornHelmet);
        RegistryHelper.registerRecipe(new ItemStack(Items.ArmorWornHelmet, 1), new Object[] {
            "SSS", 
            "S S",
            
            'S', net.minecraft.init.Items.stick
        });
        RegistryHelper.registerItem(Items.ArmorWornChestplate);
        RegistryHelper.registerRecipe(new ItemStack(Items.ArmorWornChestplate, 1), new Object[] {
            "S S", 
            "SSS",
            "SSS",
            
            'S', net.minecraft.init.Items.stick
        });
        RegistryHelper.registerItem(Items.ArmorWornLeggings);
        RegistryHelper.registerRecipe(new ItemStack(Items.ArmorWornLeggings, 1), new Object[] {
            "SSS", 
            "S S",
            "S S",
            
            'S', net.minecraft.init.Items.stick
        });
        RegistryHelper.registerItem(Items.ArmorWornBoots);
        RegistryHelper.registerRecipe(new ItemStack(Items.ArmorWornBoots, 1), new Object[] {
            "S S", 
            "S S",
            
            'S', net.minecraft.init.Items.stick
        });
        LogHelper.stopBlock();
    }
    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        LogHelper.startBlock(ArmorModule.MODULE_NAME);
        LogHelper.stopBlock();
    }
}
