package darkelfe14728.personalarmor.building;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;


/**
 * @author Julien Rosset
 *
 * Design Table's Tile Entity
 * Store design table inventory :
 *  - Paper (input)
 */
public class DesignTableTE
    extends TileEntity
    implements IInventory
{
    private static final String UNLOCALIZED_NAME = "container." + BuildingModule.instance.getNamePrefix() + "designTable";
    
    private static final String TAG_INVENTORY      = "Items";
    private static final String TAG_INVENTORY_SLOT = "Slot";
    private static final String TAG_CUSTOMNAME     = "CustomName";

    /**
     * The block custom name.
     * Only if renamed, in anvil for example.
     */
    private String customName;
    /**
     * Inventory's stacks.
     */
    private ItemStack[] inventory;
    
    public DesignTableTE()
    {
        this.inventory = new ItemStack[this.getSizeInventory()];
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
        return (this.hasCustomInventoryName() ? this.customName : UNLOCALIZED_NAME);
    }
    @Override
    public boolean hasCustomInventoryName()
    {
        return this.customName != null && this.customName != "";
    }
    
    @Override
    public int getSizeInventory()
    {
        return 1;
    }
    @Override
    public int getInventoryStackLimit()
    {
        return 64;
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
        return 
            this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this &&        
            player.getDistanceSq(this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5) <= 64;
    }
    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {        
        switch(slot)
        {
            case 1:
                return stack.getItem() == Items.paper;
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
            
            int slot = (int)elem.getByte(TAG_INVENTORY_SLOT);
            this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(elem));
        }
    }
    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        
        if(this.hasCustomInventoryName())
            compound.setString(TAG_CUSTOMNAME, this.getCustomName());
        
        NBTTagList list = new NBTTagList();
        for(int slot = 0; slot < this.getSizeInventory(); slot++)
        {
            NBTTagCompound elem = new NBTTagCompound();
            elem.setByte(TAG_INVENTORY_SLOT, (byte)slot);
            this.getStackInSlot(slot).writeToNBT(elem);
            
            list.appendTag(elem);
        }
        compound.setTag(TAG_INVENTORY, list);
    }
}
