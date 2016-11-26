package darkelfe14728.personalarmor.building;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import darkelfe14728.personalarmor.armor.ArmorItem;
import darkelfe14728.personalarmor.armor.ArmorModule;
import darkelfe14728.personalarmor.armor.ArmorSchematicItem;
import darkelfe14728.personalarmor.armor.material.IMaterial;
import darkelfe14728.personalarmor.armor.part.IArmorPart;
import darkelfe14728.personalarmor.utils.custom.IInventoryCustom;


/**
 * @author Julien Rosset
 * 
 * Assembling Table's Tile Entity
 * Store assembling table inventory :
 *  - Schematic (input)
 *  - Crafting material (input)
 *  - Armor (output)
 * 
 * @see AssemblingTable
 */
public class AssemblingTableTE
    extends TileEntity
    implements IInventoryCustom
{
    public static final int INVENTORY_SIZE = 3;

    private static final String UNLOCALIZED_NAME = "container." + BuildingModule.instance.getNamePrefix() + AssemblingTable.UNLOCALIZED_NAME;

    private static final String TAG_INVENTORY      = "Items";
    private static final String TAG_INVENTORY_SLOT = "Slot";
    private static final String TAG_CUSTOMNAME     = "CustomName";
    private static final String TAG_PROCESS        = "Process";
    private static final String TAG_PROCESS_TIME   = "Time";

    public static final int SLOT_INPUT_SCHEMATIC = 0;
    public static final int SLOT_INPUT_MATERIAL  = 1;
    public static final int SLOT_OUTPUT          = 2;

    /**
     * The block custom name.
     * Only if renamed, in anvil for example.
     */
    private String customName;
    /**
     * Inventory's stacks.
     */
    private ItemStack[] inventory;
    
    /**
     * Current processed time.
     */
    private int processTime;
    /**
     * The armor item that will appears in output slot (when process end).
     */
    private ItemStack processOutput;

    public AssemblingTableTE()
    {
        this.inventory = new ItemStack[this.getSizeInventory()];
    }

    @Override
    public void updateEntity()
    {
        if(this.worldObj.isRemote)
            return;
        
        boolean update = false;
        
        if(this.processTime > 0)
        {
            if(this.processOutput == null)
                this.processTime = 0;
            else
            {
                if(++this.processTime >= this.calculateProcessTime())
                {
                    this.setInventorySlotContents(SLOT_OUTPUT, this.processOutput);
                    this.processTime = 0;
                    this.processOutput = null;
                    
                    update = true;
                }
            }
        }
        
        if(this.processTime == 0)
            update = this.startProcess();
        
        if(update)
            this.markDirty();
    }
    /**
     * @return Total process time for current process output.
     */
    private int calculateProcessTime()
    {
        IArmorPart part     = ArmorItem.getArmorPart(this.processOutput);
        IMaterial  material = ArmorItem.getMaterial(this.processOutput);
        
        if(part == null || material == null)
            return 0;
        
        return part.getBaseFactor() * material.getAssemblingFactor();
    }
    /**
     * Start process (consume input).
     * 
     * @return False if process can't start.
     */
    private boolean startProcess()
    {
        // Armor part
        ItemStack schematic = this.getStackInSlot(SLOT_INPUT_SCHEMATIC);
        if(schematic == null)
            return false;
        if(schematic.stackSize <= 0)
            return false;
        IArmorPart part = ArmorSchematicItem.getArmorPart(schematic);
        if(part == null)
            return false;
        
        // Crafting material
        ItemStack crafting_material = this.getStackInSlot(SLOT_INPUT_MATERIAL);
        if(crafting_material == null)
            return false;
        if(crafting_material.stackSize < part.getCraftingMaterialQuantity())
            return false;
        
        // Find a matching Material
        IMaterial material = null;
        for(IMaterial material_possibility : ArmorModule.Materials.getValues())
        {
            if(!material_possibility.isApplicableToPart(part))
                continue;
            
            if(!crafting_material.isItemEqual(material_possibility.getCraftingMaterial()))
                continue;
            
            material = material_possibility;
        }
        
        // Build theoretical output item
        ItemStack output_theory = ArmorItem.newItemStack(ArmorModule.Items.ArmorItem, part, material);
        
        // Output slot empty or compatible with material
        ItemStack output = this.getStackInSlot(SLOT_OUTPUT);
        if(output != null)
        {
            if(!ItemStack.areItemStackTagsEqual(output, output_theory))
                return false;
            
            if(output.stackSize + output_theory.stackSize > output.getMaxStackSize())
                return false;
        }
        
        // All is OK, so consuming input
        this.processOutput = output_theory;
        this.decrStackSize(SLOT_INPUT_MATERIAL, part.getCraftingMaterialQuantity());
        
        return true;
    }
    
    public String getCustomName()
    {
        return this.customName;
    }
    public void setCustomName(String name)
    {
        this.customName = name;
    }

    @Override
    public String getInventoryName()
    {
        return(this.hasCustomInventoryName() ? this.customName : AssemblingTableTE.UNLOCALIZED_NAME);
    }
    @Override
    public boolean hasCustomInventoryName()
    {
        return this.customName != null && this.customName != "";
    }

    @Override
    public int getSizeInventory()
    {
        return AssemblingTableTE.INVENTORY_SIZE;
    }
    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }
    @Override
    public int getSlotStackLimit(int slot)
    {
        switch(slot)
        {
            case SLOT_INPUT_SCHEMATIC:
                return 1; 
            
            case SLOT_INPUT_MATERIAL:
                return this.getInventoryStackLimit();

            case SLOT_OUTPUT:
                return 1;
        }

        return this.getInventoryStackLimit();
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        if(slot < 0 || slot >= this.getSizeInventory())
            return null;

        return this.inventory[slot];
    }
    @Override
    public void setInventorySlotContents(int slot, ItemStack stack)
    {
        if(slot < 0 || slot >= this.getSizeInventory())
            return;

        if(stack != null)
        {
            if(stack.stackSize > this.getInventoryStackLimit())
                stack.stackSize = this.getInventoryStackLimit();
            else if(stack.stackSize <= 0)
                stack = null;
        }

        this.inventory[slot] = stack;
    }

    @Override
    public ItemStack decrStackSize(int slot, int quantity)
    {
        ItemStack atSlot = this.getStackInSlot(slot);
        if(atSlot == null)
            return null;

        ItemStack out;
        if(atSlot.stackSize <= quantity)
        {
            out = atSlot.copy();
            atSlot = null;
        }
        else
            out = atSlot.splitStack(quantity);

        this.setInventorySlotContents(slot, atSlot);

        this.markDirty();
        return out;
    }
    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        ItemStack stack = this.getStackInSlot(slot);
        this.setInventorySlotContents(slot, null);
        return stack;
    }

    @Override
    public void openInventory()
    {}
    @Override
    public void closeInventory()
    {}

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && player.getDistanceSq(this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5) <= 64;
    }
    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
        switch(slot)
        {
            case SLOT_INPUT_SCHEMATIC:
                return stack.getItem() == ArmorModule.Items.ArmorSchematicItem;
            
            case SLOT_INPUT_MATERIAL:
                return true;

            case SLOT_OUTPUT:
                return false;
        }

        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        if(compound.hasKey(TAG_CUSTOMNAME))
            this.setCustomName(compound.getString(TAG_CUSTOMNAME));

        NBTTagList list = compound.getTagList(TAG_INVENTORY, 10);
        for(int idx = 0; idx < list.tagCount(); idx++)
        {
            NBTTagCompound elem = list.getCompoundTagAt(idx);

            int slot = elem.getByte(TAG_INVENTORY_SLOT);
            this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(elem));
        }

        NBTTagCompound process = compound.getCompoundTag(TAG_PROCESS);
        this.processTime   = process.getShort(TAG_PROCESS_TIME);
        this.processOutput = ItemStack.loadItemStackFromNBT(process);
    }
    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        if(this.hasCustomInventoryName())
            compound.setString(AssemblingTableTE.TAG_CUSTOMNAME, this.getCustomName());

        NBTTagList list = new NBTTagList();
        for(int slot = 0; slot < this.getSizeInventory(); slot++)
        {
            if(this.getStackInSlot(slot) != null)
            {
                NBTTagCompound elem = new NBTTagCompound();
                elem.setByte(AssemblingTableTE.TAG_INVENTORY_SLOT, (byte)slot);
                this.getStackInSlot(slot).writeToNBT(elem);

                list.appendTag(elem);
            }
        }
        compound.setTag(AssemblingTableTE.TAG_INVENTORY, list);

        NBTTagCompound process = new NBTTagCompound();
        process.setShort(TAG_PROCESS_TIME, (short)this.processTime);
        this.processOutput.writeToNBT(process);
        compound.setTag(TAG_PROCESS, process);
    }



}
