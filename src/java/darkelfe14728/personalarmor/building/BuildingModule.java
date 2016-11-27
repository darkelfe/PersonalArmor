package darkelfe14728.personalarmor.building;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import darkelfe14728.personalarmor.utils.DyeColor;
import darkelfe14728.personalarmor.utils.LogHelper;
import darkelfe14728.personalarmor.utils.proxy.AbstractModule;
import darkelfe14728.personalarmor.utils.registry.RegistryHelper;


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
        public static final Block     designTable = new     DesignTable();
        public static final Block assemblingTable = new AssemblingTable();
    }

    @Override
    public String getPrefix()
    {
        return BuildingModule.MODULE_ID;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        LogHelper.startBlock(BuildingModule.MODULE_NAME);

        RegistryHelper.registerBlockWithTileEntity(Blocks.    designTable,     DesignTableTE.class);
        RegistryHelper.registerBlockWithTileEntity(Blocks.assemblingTable, AssemblingTableTE.class);

        LogHelper.stopBlock();
    }
    @Override
    public void init(FMLInitializationEvent event)
    {
        LogHelper.startBlock(BuildingModule.MODULE_NAME);

        RegistryHelper.registerBlockRecipe(Blocks.designTable, new Object[] {
                "FI", 
                "C ",
                
                'F', Items.feather, 
                'I', DyeColor.BLACK.getItem(), 
                'C', net.minecraft.init.Blocks.crafting_table
        });
        RegistryHelper.registerBlockRecipe(Blocks.assemblingTable, new Object[] {
                "TST", 
                "SCS", 
                "TST",
                
                'T', Items.stick, 
                'S', net.minecraft.init.Blocks.stone, 
                'C', net.minecraft.init.Blocks.crafting_table
        });

        LogHelper.stopBlock();
    }
    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        LogHelper.startBlock(BuildingModule.MODULE_NAME);
        RegistryHelper.registerMessage(DesignTableGuiPacket.class);
        LogHelper.stopBlock();
    }
}
