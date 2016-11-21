package darkelfe14728.personalarmor.armor;

import net.minecraft.item.Item;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import darkelfe14728.personalarmor.armor.part.IArmorPart;
import darkelfe14728.personalarmor.utils.ExtractableList;
import darkelfe14728.personalarmor.utils.LogHelper;
import darkelfe14728.personalarmor.utils.Registry;
import darkelfe14728.personalarmor.utils.proxy.AbstractModule;


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

    public static class Parts
        extends ExtractableList
    {
        public static final IArmorPart ArmLeft      = new darkelfe14728.personalarmor.armor.part.ArmLeft();
        public static final IArmorPart ArmRight     = new darkelfe14728.personalarmor.armor.part.ArmRight();
        public static final IArmorPart Back         = new darkelfe14728.personalarmor.armor.part.Back();
        public static final IArmorPart Belt         = new darkelfe14728.personalarmor.armor.part.Belt();
        public static final IArmorPart Chest        = new darkelfe14728.personalarmor.armor.part.Chest();
        public static final IArmorPart FootLeft     = new darkelfe14728.personalarmor.armor.part.FootLeft();
        public static final IArmorPart FootRight    = new darkelfe14728.personalarmor.armor.part.FootRight();
        public static final IArmorPart ForearmLeft  = new darkelfe14728.personalarmor.armor.part.ForearmLeft();
        public static final IArmorPart ForearmRight = new darkelfe14728.personalarmor.armor.part.ForearmRight();
        public static final IArmorPart HandLeft     = new darkelfe14728.personalarmor.armor.part.HandLeft();
        public static final IArmorPart HandRight    = new darkelfe14728.personalarmor.armor.part.HandRight();
        public static final IArmorPart Head         = new darkelfe14728.personalarmor.armor.part.Head();
        public static final IArmorPart LegDownLeft  = new darkelfe14728.personalarmor.armor.part.LegDownLeft();
        public static final IArmorPart LegDownRight = new darkelfe14728.personalarmor.armor.part.LegDownRight();
        public static final IArmorPart LegUpLeft    = new darkelfe14728.personalarmor.armor.part.LegUpLeft();
        public static final IArmorPart LegUpRight   = new darkelfe14728.personalarmor.armor.part.LegUpRight();
    }
    public static class Items
        extends ExtractableList
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
        Registry.registerItem(Items.ArmorSchematic);
        LogHelper.stopBlock();
    }
    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        LogHelper.startBlock(ArmorModule.MODULE_NAME);
        LogHelper.stopBlock();
    }
}
