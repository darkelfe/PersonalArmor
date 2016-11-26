package darkelfe14728.personalarmor.building;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import darkelfe14728.personalarmor.utils.custom.IInventoryCustom;


/**
 * @author Julien Rosset
 * 
 *         Design Table's Tile Entity
 *         Store design table inventory :
 *         - Paper (input)
 * 
 * @see DesignTable
 */
public class DesignTableTE
    extends TileEntity
    implements IInventoryCustom
{
    public static final int     INVENTORY_SIZE     = 2;

    private static final String UNLOCALIZED_NAME   = "container." + BuildingModule.instance.getNamePrefix() + "designTable";

    private static final String TAG_INVENTORY      = "Items";
    private static final String TAG_INVENTORY_SLOT = "Slot";
    private static final String TAG_CUSTOMNAME     = "CustomName";

    public static final int     SLOT_INPUT_PAPER   = 0;
    public static final int     SLOT_OUTPUT        = 1;

    /**
     * The block custom name.
     * Only if renamed, in anvil for example.
     */
    private String              customName;
    /**
     * Inventory's stacks.
     */
    private final ItemStack[]         inventory;

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
        return(this.hasCustomInventoryName() ? this.customName : DesignTableTE.UNLOCALIZED_NAME);
    }
    @Override
    public boolean hasCustomInventoryName()
    {
        return this.customName != null && this.customName != "";
    }

    @Override
    public int getSizeInventory()
    {
        return DesignTableTE.INVENTORY_SIZE;
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
            case SLOT_INPUT_PAPER:
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
            case SLOT_INPUT_PAPER:
                return stack.getItem() == Items.paper;

            case SLOT_OUTPUT:
                return false;
        }

        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        if(compound.hasKey(DesignTableTE.TAG_CUSTOMNAME))
            this.setCustomName(compound.getString(DesignTableTE.TAG_CUSTOMNAME));

        NBTTagList list = compound.getTagList(DesignTableTE.TAG_INVENTORY, 10);
        for(int idx = 0; idx < list.tagCount(); idx++)
        {
            NBTTagCompound elem = list.getCompoundTagAt(idx);

            int slot = elem.getByte(DesignTableTE.TAG_INVENTORY_SLOT);
            this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(elem));
        }
    }
    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        if(this.hasCustomInventoryName())
            compound.setString(DesignTableTE.TAG_CUSTOMNAME, this.getCustomName());

        NBTTagList list = new NBTTagList();
        for(int slot = 0; slot < this.getSizeInventory(); slot++)
        {
            if(this.getStackInSlot(slot) != null)
            {
                NBTTagCompound elem = new NBTTagCompound();
                elem.setByte(DesignTableTE.TAG_INVENTORY_SLOT, (byte)slot);
                this.getStackInSlot(slot).writeToNBT(elem);

                list.appendTag(elem);
            }
        }
        compound.setTag(DesignTableTE.TAG_INVENTORY, list);
    }

}
