package darkelfe14728.personalarmor.armor.part;


/**
 * @author Julien Rosset
 * 
 *         Generic armor part.
 */
public interface IArmorPart
{
    /**
     * @return Part "name".
     */
    public String getName();

    /**
     * @return Base value for item values (Space, Weight, Energy)
     */
    public int getBaseFactor();

    // public boolean isModuleApplicable()
}
