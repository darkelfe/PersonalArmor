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
     * @see darkelfe14728.personalarmor.armor.part.IArmorPart#getTagName()
     */
    @Override
    public String getTagName()
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
}
