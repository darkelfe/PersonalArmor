package darkelfe14728.personalarmor.armor;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkelfe14728.personalarmor.PersonalArmor;
import darkelfe14728.personalarmor.PersonalArmorTab;
import darkelfe14728.personalarmor.armor.part.IArmorPart;
import darkelfe14728.personalarmor.utils.Utils;


/**
 * @author Julien Rosset
 * 
 *         An armor (part) schematic
 */
public class ArmorSchematicItem
    extends Item
{
    public static final String NBT_GROUP = PersonalArmor.MOD_ID;
    public static final String NBT_PART  = "Part";

    public ArmorSchematicItem()
    {
        this.setMaxStackSize(1);
        this.setHasSubtypes(true);
        this.setCreativeTab(PersonalArmorTab.instance);
        this.setUnlocalizedName(ArmorModule.instance.getNamePrefix() + "schematic");
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        String name = super.getUnlocalizedName(stack);

        if(stack.hasTagCompound())
        {
            NBTTagCompound data = stack.getTagCompound();
            if(data.hasKey(ArmorSchematicItem.NBT_GROUP))
            {
                data = data.getCompoundTag(ArmorSchematicItem.NBT_GROUP);
                if(data.hasKey(ArmorSchematicItem.NBT_PART))
                {
                    name += "." + data.getString(ArmorSchematicItem.NBT_PART);
                }
            }
        }

        return name;
    }

    @SuppressWarnings("unchecked")
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, @SuppressWarnings("rawtypes") List itemList)
    {
        for(IArmorPart part : Utils.getClassStaticElements(ArmorModule.Parts.class, IArmorPart.class))
            itemList.add(ArmorSchematicItem.newItemStack(item, part));
    }

    /**
     * Create a new ItemStack of ArmorSchematicItem.
     * 
     * @param item
     *            The ArmorSchematicItem.
     * @param part
     *            The armor part associated.
     * @return The new ItemStack
     */
    public static ItemStack newItemStack(Item item, IArmorPart part)
    {
        return ArmorSchematicItem.newItemStack(item, part, 1);
    }
    public static ItemStack newItemStack(Item item, IArmorPart part, int quantity)
    {
        return ArmorSchematicItem.newItemStack(item, part, quantity, 0);
    }
    public static ItemStack newItemStack(Item item, IArmorPart part, int quantity, int damage)
    {
        NBTTagCompound data = new NBTTagCompound();
        NBTTagCompound group = new NBTTagCompound();
        group.setString(ArmorSchematicItem.NBT_PART, part.getTagName());
        data.setTag(ArmorSchematicItem.NBT_GROUP, group);

        ItemStack stack = new ItemStack(item, quantity, damage);
        stack.setTagCompound(data);

        return stack;
    }
}
