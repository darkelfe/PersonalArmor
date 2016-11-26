package darkelfe14728.personalarmor.armor.part;

/**
 * @author Julien Rosset
 * 
 *         Armor (abstract) up leg part.
 */
public abstract class AbstractLegUp
    implements IArmorPart
{
    /**
     * @see darkelfe14728.personalarmor.armor.part.IArmorPart#getName()
     */
    @Override
    public String getName()
    {
        return "legup";
    }

    /**
     * @see darkelfe14728.personalarmor.armor.part.IArmorPart#getBaseFactor()
     */
    @Override
    public int getBaseFactor()
    {
        return 60;
    }

    @Override
    public int getCraftingMaterialQuantity()
    {
        return 4;
    }
}
