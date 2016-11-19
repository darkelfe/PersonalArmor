package darkelfe14728.personalarmor.armor.part;

/**
 * @author Julien Rosset
 *
 * Armor (abstract) hand part.
 */
public abstract class AbstractHand
    implements IArmorPart
{
    /**
     * @see darkelfe14728.personalarmor.armor.part.IArmorPart#getBaseFactor()
     */
    @Override
    public int getBaseFactor()
    {
        return 40;
    }
}
