package darkelfe14728.personalarmor.armor.part;

/**
 * @author Julien Rosset
 *
 * Armor (abstract) foot part.
 */
public abstract class AbstractFoot
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
