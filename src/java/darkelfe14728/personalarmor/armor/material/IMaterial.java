package darkelfe14728.personalarmor.armor.material;

import darkelfe14728.personalarmor.armor.part.IArmorPart;
import net.minecraft.item.ItemStack;

/**
 * @author Julien Rosset
 * 
 * Generic armor material
 */
public interface IMaterial
{
    /**
     * @return Material "name".
     */
    public String getName();
    
    /**
     * @return Energy factor.
     */
    public int getEnergyFactor();
    /**
     * @return Weight factor.
     */
    public int getWeightFactor();
    /**
     * @return Space factor.
     */
    public int getSpaceFactor();
    
    /**
     * @return Protection factor.
     */
    public int getProtectionFactor();
    /**
     * @return Durability factor.
     */
    public int getDurabilityFactor();

    /**
     * @return ItemStack to use in crafting/repair process.
     */
    public ItemStack getCraftingMaterial();
    
    /**
     * @param part The armor part concerned.
     * 
     * @return True if material can be used with this armor part, else False. Default True.
     */
    public boolean isApplicableToPart(IArmorPart part);
}
