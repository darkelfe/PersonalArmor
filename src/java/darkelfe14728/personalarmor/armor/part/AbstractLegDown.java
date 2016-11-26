package darkelfe14728.personalarmor.armor.part;

/**
 * @author Julien Rosset
 * 
 *         Armor (abstract) down leg part.
 */
public abstract class AbstractLegDown
    implements IArmorPart
{
    /**
     * @see darkelfe14728.personalarmor.armor.part.IArmorPart#getName()
     */
    @Override
    public String getName()
    {
        return "legdown";
    }

    /**
     * @see darkelfe14728.personalarmor.armor.part.IArmorPart#getBaseFactor()
     */
    @Override
    public int getBaseFactor()
    {
        return 60;
    }
}
