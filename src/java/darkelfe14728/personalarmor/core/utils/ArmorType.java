package darkelfe14728.personalarmor.core.utils;

/**
 * @author Julien Rosset
 * 
 *         Enumerate blocks faces.
 */
public enum ArmorType
{
    HELMET    (0, "helmet"    ),
    CHESTPLATE(1, "chestplate"),
    LEGGINGS  (2, "leggings"  ),
    BOOTS     (3, "boots"     );

    private final int type;
    private final String unlocalized_name;

    ArmorType(int type, String unlocalized_name)
    {
        this.type = type;
        this.unlocalized_name = unlocalized_name;
    }

    public int getType()
    {
        return this.type;
    }
    public String getUnlocalizedName()
    {
        return this.unlocalized_name;
    }
}
