package darkelfe14728.personalarmor.armor.part;

/**
 * @author Julien Rosset
 * 
 *         Armor (abstract) forearm part.
 */
public abstract class AbstractForearm
    implements IArmorPart
{
    /**
     * @see darkelfe14728.personalarmor.armor.part.IArmorPart#getTagName()
     */
    @Override
    public String getTagName()
    {
        return "forearm";
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
