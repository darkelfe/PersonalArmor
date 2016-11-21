package darkelfe14728.personalarmor.armor.part;

import darkelfe14728.personalarmor.armor.ArmorSchematicItem;


/**
 * @author Julien Rosset
 * 
 *         Generic armor part.
 */
public interface IArmorPart
{
    /**
     * @return NBT Tag value for {@link ArmorSchematicItem.NBT_PART}
     */
    public String getTagName();

    /**
     * @return Base value for item values (Space, Weight, Energy)
     */
    public int getBaseFactor();

    // public boolean isModuleApplicable()
}
