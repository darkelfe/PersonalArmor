package darkelfe14728.personalarmor.building;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkelfe14728.personalarmor.PersonalArmorTab;
import darkelfe14728.personalarmor.utils.BlockFace;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;


/**
 * @author Julien Rosset
 * 
 *         Design table : choose the armor part to create.
 */
public class DesignTable
    extends Block
{
    public static final String UNLOCALIZED_NAME = "designTable";

    public enum TextureFace
    {
            TOP(0),
            OTHER(1);

        private int index;

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

        this.setBlockName(BuildingModule.instance.getNamePrefix() + UNLOCALIZED_NAME);
        this.setBlockTextureName(BuildingModule.instance.getTexturePrefix() + UNLOCALIZED_NAME);
        this.setCreativeTab(PersonalArmorTab.instance);
        this.setHardness(1.0F);
        this.setResistance(10F);
        this.setStepSound(soundTypeWood);
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

        return textures[face.getIndex()];
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.textures[TextureFace.TOP.getIndex()] = register.registerIcon(this.textureName + "_top");
        this.textures[TextureFace.OTHER.getIndex()] = register.registerIcon(this.textureName + "_other");
    }
}
