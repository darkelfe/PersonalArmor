package darkelfe14728.personalarmor.armor.part;

/**
 * @author Julien Rosset
 * 
 *         Armor chest part
 */
public class Chest
    implements IArmorPart
{
    @Override
    public String getName()
    {
        return "chest";
    }

    /**
     * @see darkelfe14728.personalarmor.armor.part.IArmorPart#getBaseFactor()
     */
    @Override
    public int getBaseFactor()
    {
        return 100;
    }

    @Override
    public int getCraftingMaterialQuantity()
    {
        return 8;
    }
}
