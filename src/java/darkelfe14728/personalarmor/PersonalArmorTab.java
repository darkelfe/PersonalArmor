package darkelfe14728.personalarmor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;


/**
 * @author Julien Rosset
 * 
 *         Mod's creative tab.
 */
public class PersonalArmorTab
    extends CreativeTabs
{
    public static final PersonalArmorTab instance         = new PersonalArmorTab();

    public static final String           UNLOCALIZED_NAME = "personalarmor";

    /**
     * New creative tab
     */
    public PersonalArmorTab()
    {
        super(UNLOCALIZED_NAME);
    }

    /*
     * @see net.minecraft.creativetab.CreativeTabs#getTabIconItem()
     */
    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem()
    {
        return Items.diamond_chestplate;
    }
}
