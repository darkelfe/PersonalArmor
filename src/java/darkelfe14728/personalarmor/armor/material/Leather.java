package darkelfe14728.personalarmor.armor.material;

import darkelfe14728.personalarmor.armor.part.IArmorPart;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * @author Julien Rosset
 *
 * Leather material
 */
public class Leather
    implements IMaterial
{
    @Override
    public String getName()
    {
        return "leather";
    }

    @Override
    public int getEnergyFactor()
    {
        return 0;
    }
    @Override
    public int getWeightFactor()
    {
        return 2;
    }
    @Override
    public int getSpaceFactor()
    {
        return 10;
    }

    @Override
    public int getAssemblingFactor()
    {
        return 10;
    }
    @Override
    public int getProtectionFactor()
    {
        return 10;
    }
    @Override
    public int getDurabilityFactor()
    {
        return 10;
    }

    @Override
    public ItemStack getCraftingMaterial()
    {
        return new ItemStack(Items.leather);
    }

    
    @Override
    public boolean isApplicableToPart(IArmorPart part)
    {
        return true;
    }
}
