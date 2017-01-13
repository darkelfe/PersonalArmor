package darkelfe14728.personalarmor.building;

import darkelfe14728.personalarmor.PersonalArmor;
import darkelfe14728.personalarmor.PersonalArmorTab;
import darkelfe14728.personalarmor.core.InventoryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Julien Rosset
 *
 */
public class AssemblingTable
    extends BlockContainer
{
    public static final String UNLOCALIZED_NAME = "assemblingTable";

    /**
     * New assembling table
     */
    public AssemblingTable()
    {        
        super(Material.rock);

        this.setBlockName(BuildingModule.instance.getNamePrefix() + UNLOCALIZED_NAME);
        this.setBlockTextureName(BuildingModule.instance.getTexturePrefix() + UNLOCALIZED_NAME);
        this.setCreativeTab(PersonalArmorTab.instance);
        this.setHardness(1.0F);
        this.setResistance(10F);
        this.setStepSound(Block.soundTypeStone);
        this.setHarvestLevel("pickaxe", 2);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new AssemblingTableTE();
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
    {
        AssemblingTableTE tile = (AssemblingTableTE)world.getTileEntity(x, y, z);
        InventoryHelper.dropInWorld(world, x, y, z, tile);

        super.breakBlock(world, x, y, z, block, metadata);
    }
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack)
    {
        if(stack.hasDisplayName())
            ((AssemblingTableTE)world.getTileEntity(x, y, z)).setCustomName(stack.getDisplayName());

        super.onBlockPlacedBy(world, x, y, z, placer, stack);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if(!world.isRemote)
        {
            player.openGui(PersonalArmor.instance, AssemblingTableGui.GUI_ID, world, x, y, z);
            return true;
        }

        return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
    }
}
