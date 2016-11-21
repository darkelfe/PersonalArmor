package darkelfe14728.personalarmor.building;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkelfe14728.personalarmor.PersonalArmor;
import darkelfe14728.personalarmor.PersonalArmorTab;
import darkelfe14728.personalarmor.utils.BlockFace;
import darkelfe14728.personalarmor.utils.InventoryHelper;


/**
 * @author Julien Rosset
 * 
 *         Design Table's block : choose the armor part to create.
 */
public class DesignTable
    extends BlockContainer
{
    public static final String UNLOCALIZED_NAME = "designTable";

    public enum TextureFace
    {
            TOP(0),
            OTHER(1);

        private final int index;

        private TextureFace(int index)
        {
            this.index = index;
        }

        public int getIndex()
        {
            return this.index;
        }
    }

    public IIcon[] textures = new IIcon[2];

    /**
     * New design table
     */
    public DesignTable()
    {
        super(Material.wood);

        this.setBlockName(BuildingModule.instance.getNamePrefix() + DesignTable.UNLOCALIZED_NAME);
        this.setBlockTextureName(BuildingModule.instance.getTexturePrefix() + DesignTable.UNLOCALIZED_NAME);
        this.setCreativeTab(PersonalArmorTab.instance);
        this.setHardness(1.0F);
        this.setResistance(10F);
        this.setStepSound(Block.soundTypeWood);
        this.setHarvestLevel("axe", 1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        TextureFace face;
        if(BlockFace.TOP.equals(side))
            face = TextureFace.TOP;
        else
            face = TextureFace.OTHER;

        return this.textures[face.getIndex()];
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.textures[TextureFace.TOP.getIndex()] = register.registerIcon(this.textureName + "_top");
        this.textures[TextureFace.OTHER.getIndex()] = register.registerIcon(this.textureName + "_other");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new DesignTableTE();
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
    {
        DesignTableTE tile = (DesignTableTE)world.getTileEntity(x, y, z);
        InventoryHelper.dropInWorld(world, x, y, z, tile);

        super.breakBlock(world, x, y, z, block, metadata);
    }
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack)
    {
        if(stack.hasDisplayName())
            ((DesignTableTE)world.getTileEntity(x, y, z)).setCustomName(stack.getDisplayName());

        super.onBlockPlacedBy(world, x, y, z, placer, stack);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if(!world.isRemote)
        {
            player.openGui(PersonalArmor.instance, DesignTableGui.GUI_ID, world, x, y, z);
            return true;
        }

        return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
    }
}
