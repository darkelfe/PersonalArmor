package darkelfe14728.personalarmor.armor.part;

/**
 * @author Julien Rosset
 * 
 *         Armor back part.
 */
public class Back
    implements IArmorPart
{
    @Override
    public String getName()
    {
        return "back";
    }

    /**
     * @see darkelfe14728.personalarmor.armor.part.IArmorPart#getBaseFactor()
     */
    @Override
    public int getBaseFactor()
    {
        return 150;
    }

    @Override
    public int getCraftingMaterialQuantity()
    {
        return 10;
    }
}
