package darkelfe14728.personalarmor.armor;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkelfe14728.personalarmor.PersonalArmor;
import darkelfe14728.personalarmor.core.utils.ArmorType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.EnumHelper;

/**
 * @author Julien Rosset
 *
 * The 'fake' armor worn to enable personal armor.
 */
public class ArmorWornItem
    extends ItemArmor
{
    public static final String UNLOCALIZED_NAME = "worn";
    
    /**
     * The armor material : unbreakable, no protection (provided by personal armor parts directly), no enchantability.
     */
    public static ArmorMaterial MATERIAL_WORN = EnumHelper.addArmorMaterial("PERSONALARMOR_WORN", 0, new int[] {0, 0, 0, 0}, 0);

    /**
     * New armor.
     * 
     * @param type Armor part type.
     */
    public ArmorWornItem(ArmorType type)
    {
        super(MATERIAL_WORN, 0, type.getType());
        
        this.setUnlocalizedName(ArmorModule.instance.getNamePrefix() + UNLOCALIZED_NAME + "." + type.getUnlocalizedName());
        this.setTextureName(ArmorModule.instance.getTexturePrefix() + UNLOCALIZED_NAME + "/" + type.getUnlocalizedName());
    }
    @SuppressWarnings("unchecked")
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, @SuppressWarnings("rawtypes") List textList, boolean sneak)
    {
        textList.add(StatCollector.translateToLocal("interface." + ArmorModule.instance.getNamePrefix() + UNLOCALIZED_NAME + ".description"));
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        return PersonalArmor.MOD_ID + ":textures/items/" + ArmorModule.instance.getPrefix() + "/" + UNLOCALIZED_NAME + "/layer_" + (this.armorType == 2 ? "2" : "1") + ".png";
    }
}
