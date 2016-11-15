package darkelfe14728.personalarmor.building;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import darkelfe14728.personalarmor.utils.DyeColor;
import darkelfe14728.personalarmor.utils.LogHelper;
import darkelfe14728.personalarmor.utils.Registry;
import darkelfe14728.personalarmor.utils.proxy.AbstractModule;


/**
 * @author Julien Rosset
 * 
 */
public class BuildingModule
    extends AbstractModule
{
    public static final String         MODULE_ID   = "building";
    public static final String         MODULE_NAME = "Building";

    public static final BuildingModule instance    = new BuildingModule();

    public static class Blocks
    {
        public static final Block designTable = new DesignTable();
    }

    @Override
    public String getPrefix()
    {
        return MODULE_ID;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        LogHelper.startBlock(MODULE_NAME);

        Registry.registerBlockWithTileEntity(Blocks.designTable, DesignTableTE.class);

        LogHelper.stopBlock();
    }
    @Override
    public void init(FMLInitializationEvent event)
    {
        LogHelper.startBlock(MODULE_NAME);

        Registry.registerBlockRecipe(Blocks.designTable, new Object[] {
            "FI", "C ",
            'F', Items.feather,
            'I', DyeColor.BLACK.getItem(),
            'C', net.minecraft.init.Blocks.crafting_table
        });

        LogHelper.stopBlock();
    }
    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        LogHelper.startBlock(MODULE_NAME);
        LogHelper.stopBlock();
    }
}
