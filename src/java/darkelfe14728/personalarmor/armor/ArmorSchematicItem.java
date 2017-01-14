package darkelfe14728.personalarmor.armor;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkelfe14728.personalarmor.PersonalArmor;
import darkelfe14728.personalarmor.PersonalArmorTab;
import darkelfe14728.personalarmor.armor.part.IArmorPart;


/**
 * @author Julien Rosset
 * 
 * An armor (part) schematic
 */
public class ArmorSchematicItem
    extends Item
{
    public static final String UNLOCALIZED_NAME = "schematic";
    
    public static final String NBT_GROUP = PersonalArmor.MOD_ID;
    public static final String NBT_PART  = "Part";
    
    private Map<String, IIcon> icons = null;

    public ArmorSchematicItem()
    {
        this.setMaxStackSize(1);
        this.setHasSubtypes(true);
        this.setCreativeTab(PersonalArmorTab.instance);
        
        this.setUnlocalizedName(ArmorModule.instance.getNamePrefix() + UNLOCALIZED_NAME);
        this.setTextureName(ArmorModule.instance.getTexturePrefix() + UNLOCALIZED_NAME);
    }
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        String name = super.getUnlocalizedName(stack);
        IArmorPart part = getArmorPart(stack);
        
        if(part != null)
            name += "." + part.getName();

        return name;
    }

    @SuppressWarnings("unchecked")
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, @SuppressWarnings("rawtypes") List itemList)
    {
        for(IArmorPart part : ArmorModule.Parts.getValues())
            itemList.add(ArmorSchematicItem.newItemStack(item, part));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register)
    {
        this.icons = new Hashtable<>(ArmorModule.Parts.size());
        
        for(IArmorPart part : ArmorModule.Parts.getValues())
            this.icons.put(part.getName(), register.registerIcon(this.getIconString() + "/" + part.getName()));
    }
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconIndex(ItemStack stack)
    {
        return this.getIconFromItemStack(stack);
    }
    @Override
    public IIcon getIcon(ItemStack stack, int pass)
    {
        return this.getIconFromItemStack(stack);
    }
    private IIcon getIconFromItemStack(ItemStack stack)
    {
        if(this.icons == null)
            return null;
        
        IArmorPart part = getArmorPart(stack);
        if(part == null)
            return null;
        
        return this.icons.get(part.getName());
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
        group.setString(ArmorSchematicItem.NBT_PART, part.getName());
        data.setTag(ArmorSchematicItem.NBT_GROUP, group);

        ItemStack stack = new ItemStack(item, quantity, damage);
        stack.setTagCompound(data);

        return stack;
    }
    
    public static IArmorPart getArmorPart(ItemStack stack)
    {
        NBTTagCompound data = getNBTGroup(stack);
        if(data == null)
            return null;
        
        if(!data.hasKey(ArmorSchematicItem.NBT_PART))
            return null;
        
        return ArmorModule.Parts.getValue(data.getString(ArmorSchematicItem.NBT_PART));
    }
    private static NBTTagCompound getNBTGroup(ItemStack stack)
    {
        if(!stack.hasTagCompound())
            return null;
        
        NBTTagCompound data = stack.getTagCompound();
        if(!data.hasKey(NBT_GROUP))
            return null;
        
        return data.getCompoundTag(NBT_GROUP);
    }
}
