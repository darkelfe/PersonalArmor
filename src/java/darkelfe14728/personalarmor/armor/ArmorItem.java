package darkelfe14728.personalarmor.armor;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkelfe14728.personalarmor.PersonalArmor;
import darkelfe14728.personalarmor.PersonalArmorTab;
import darkelfe14728.personalarmor.armor.material.IMaterial;
import darkelfe14728.personalarmor.armor.part.IArmorPart;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

/**
 * @author Julien Rosset
 *
 */
public class ArmorItem
    extends Item
{
    public static final String UNLOCALIZED_NAME = "armor";
    
    public static final String NBT_GROUP    = PersonalArmor.MOD_ID;
    public static final String NBT_PART     = "Part";
    public static final String NBT_MATERIAL = "Material";
    
    private Map<String, IIcon> icons = null;

    public ArmorItem()
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
    public void addInformation(ItemStack stack, EntityPlayer player, @SuppressWarnings("rawtypes") List textList, boolean sneak)
    {
        String material_str = "?";
        
        IMaterial material = getMaterial(stack);
        if(material != null)
            material_str = StatCollector.translateToLocal("material." + ArmorModule.instance.getNamePrefix() + material.getName());
        
        textList.add(StatCollector.translateToLocal("interface.material") + " : " + material_str);
    }

    @SuppressWarnings("unchecked")
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, @SuppressWarnings("rawtypes") List itemList)
    {
        Set<IArmorPart> parts     = ArmorModule.Parts.getValues();
        Set<IMaterial>  materials = ArmorModule.Materials.getValues();
        
        for(IMaterial material : materials)
            for(IArmorPart part : parts)
                itemList.add(newItemStack(item, part, material));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register)
    {
        this.icons = new Hashtable<>(ArmorModule.Parts.size() * ArmorModule.Materials.size());
        
        Set<IArmorPart> parts     = ArmorModule.Parts.getValues();
        Set<IMaterial>  materials = ArmorModule.Materials.getValues();
        
        for(IMaterial material : materials)
            for(IArmorPart part : parts)
                this.icons.put(
                        part.getName() + "#" + material.getName(), 
                        register.registerIcon(this.getIconString() + "_" + part.getName() + "_" + material.getName())
                );
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
        
        IMaterial material = getMaterial(stack);
        if(material == null)
            return null;
        
        return this.icons.get(part.getName() + '#' + material.getName());
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
    public static ItemStack newItemStack(Item item, IArmorPart part, IMaterial material)
    {
        return newItemStack(item, part, material, 1);
    }
    public static ItemStack newItemStack(Item item, IArmorPart part, IMaterial material, int quantity)
    {
        return newItemStack(item, part, material, quantity, 0);
    }
    public static ItemStack newItemStack(Item item, IArmorPart part, IMaterial material, int quantity, int damage)
    {
        NBTTagCompound data = new NBTTagCompound();
        NBTTagCompound group = new NBTTagCompound();
        group.setString(NBT_PART, part.getName());
        group.setString(NBT_MATERIAL, material.getName());
        data.setTag(ArmorSchematicItem.NBT_GROUP, group);

        ItemStack stack = new ItemStack(item, quantity, damage);
        stack.setTagCompound(data);

        return stack;
    }
    
    public static IArmorPart getArmorPart(ItemStack stack)
    {
        if(!stack.hasTagCompound())
            return null;
        
        NBTTagCompound data = stack.getTagCompound();
        if(!data.hasKey(NBT_GROUP))
            return null;
        
        data = data.getCompoundTag(NBT_GROUP);
        if(!data.hasKey(NBT_PART))
            return null;
        
        return ArmorModule.Parts.getValue(data.getString(NBT_PART));
    }
    public static IMaterial getMaterial(ItemStack stack)
    {
        if(!stack.hasTagCompound())
            return null;
        
        NBTTagCompound data = stack.getTagCompound();
        if(!data.hasKey(NBT_GROUP))
            return null;
        
        data = data.getCompoundTag(NBT_GROUP);
        if(!data.hasKey(NBT_MATERIAL))
            return null;
        
        return ArmorModule.Materials.getValue(data.getString(NBT_MATERIAL));
    }
}
