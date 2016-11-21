package darkelfe14728.personalarmor.armor.part;

/**
 * @author Julien Rosset
 * 
 *         Armor head part.
 */
public class Head
    implements IArmorPart
{
    @Override
    public String getTagName()
    {
        return "head";
    }

    /**
     * @see darkelfe14728.personalarmor.armor.part.IArmorPart#getBaseFactor()
     */
    @Override
    public int getBaseFactor()
    {
        return 50;
    }
}
