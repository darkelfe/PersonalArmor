package darkelfe14728.personalarmor.building;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import darkelfe14728.personalarmor.utils.InventoryHelper;
import darkelfe14728.personalarmor.utils.custom.ISlotPickup;
import darkelfe14728.personalarmor.utils.custom.SlotCustom;


/**
 * @author Julien Rosset
 * 
 *         Design Table's GUI-Container
 * 
 * @see DesignTable
 */
public class DesignTableContainer
    extends Container
    implements ISlotPickup
{
    private static final int    SLOT_START = 0;
    private static final int    SLOT_STOP  = DesignTableContainer.SLOT_START + DesignTableTE.INVENTORY_SIZE - 1;

    private final DesignTableTE tile;

    /**
     * New GUI-Container
     */
    public DesignTableContainer(DesignTableTE tile, IInventory playerInventory)
    {
        this.tile = tile;

        // Design Table
        this.addSlotToContainer(new SlotCustom(this.tile, DesignTableTE.SLOT_INPUT_PAPER, 17, 53, this));
        this.addSlotToContainer(new SlotCustom(this.tile, DesignTableTE.SLOT_OUTPUT, 143, 53, this));

        // Inventory
        for(int line = 0; line < InventoryHelper.Constants.INVENTORY.NB_LINES; line++)
        {
            for(int column = 0; column < InventoryHelper.Constants.INVENTORY.NB_COLUMNS; column++)
            {
                this.addSlotToContainer(new Slot(playerInventory, InventoryHelper.Constants.INVENTORY.SLOT_START + line * InventoryHelper.Constants.INVENTORY.NB_COLUMNS + column,
                        DesignTableGui.GUI_INV_OFFSET_H + column * InventoryHelper.Constants.INVENTORY.SLOT_OFFSET_H + 1, DesignTableGui.GUI_INV_OFFSET_V + line
                                * InventoryHelper.Constants.INVENTORY.SLOT_OFFSET_V + 1));
            }
        }

        // Hotbar
        for(int line = 0; line < InventoryHelper.Constants.HOTBAR.NB_LINES; line++)
        {
            for(int column = 0; column < InventoryHelper.Constants.HOTBAR.NB_COLUMNS; column++)
            {
                this.addSlotToContainer(new Slot(playerInventory, InventoryHelper.Constants.HOTBAR.SLOT_START + line * InventoryHelper.Constants.HOTBAR.NB_COLUMNS + column,
                        DesignTableGui.GUI_HOTBAR_OFFSET_H + column * InventoryHelper.Constants.HOTBAR.SLOT_OFFSET_H + 1, DesignTableGui.GUI_HOTBAR_OFFSET_V + line
                                * InventoryHelper.Constants.HOTBAR.SLOT_OFFSET_V + 1));
            }
        }
    }

    public void setOuput(ItemStack output)
    {
        if(output == null)
            return;

        this.tile.setInventorySlotContents(DesignTableTE.SLOT_OUTPUT, output);
    }
    @Override
    public void onSlotPickup(EntityPlayer player, int index, ItemStack stack)
    {
        if(index == DesignTableTE.SLOT_OUTPUT)
            this.tile.decrStackSize(DesignTableTE.SLOT_INPUT_PAPER, stack.stackSize);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return this.tile.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int fromSlot)
    {
        ItemStack stack = null;
        Slot slot = (Slot)this.inventorySlots.get(fromSlot);

        if(slot != null && slot.getHasStack())
        {
            ItemStack current = slot.getStack();
            stack = current.copy();

            if(DesignTableContainer.SLOT_START <= fromSlot && fromSlot <= DesignTableContainer.SLOT_STOP)
            {
                // Design Table => Player Inventory
                if(!this.mergeItemStack(current, InventoryHelper.Constants.HOTBAR.SLOT_START + DesignTableContainer.SLOT_STOP, InventoryHelper.Constants.INVENTORY.SLOT_STOP
                        + DesignTableContainer.SLOT_STOP, false))
                    return null;
            }
            else
            {
                // Player Inventory => Design Table
                if(!this.mergeItemStack(current, DesignTableContainer.SLOT_START, DesignTableContainer.SLOT_STOP, false))
                    return null;
            }

            if(current.stackSize == 0)
                slot.putStack((ItemStack)null);
            else
                slot.onSlotChanged();

            if(current.stackSize == stack.stackSize)
                return null;
            slot.onPickupFromSlot(player, current);
        }

        return stack;
    }
    @Override
    protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseAnalysis)
    {
        boolean success = false;
        int index = startIndex;

        if(reverseAnalysis)
            index = endIndex - 1;

        Slot slot;
        ItemStack stackInSlot;

        if(stack.isStackable())
        {
            // Search slots already with this item
            while(stack.stackSize > 0 && (!reverseAnalysis && index < endIndex || reverseAnalysis && index >= startIndex))
            {
                slot = (Slot)this.inventorySlots.get(index);
                stackInSlot = slot.getStack();

                if(stackInSlot != null && stackInSlot.getItem() == stack.getItem() && (!stack.getHasSubtypes() || stack.getItemDamage() == stackInSlot.getItemDamage())
                        && ItemStack.areItemStackTagsEqual(stack, stackInSlot))
                {
                    int qte = stackInSlot.stackSize + stack.stackSize;
                    int maxSize = Math.min(stack.getMaxStackSize(), slot.getSlotStackLimit());

                    if(qte <= maxSize)
                    {
                        stack.stackSize = 0;
                        stackInSlot.stackSize = qte;
                        slot.onSlotChanged();

                        success = true;
                        break;
                    }
                    else if(stackInSlot.stackSize < maxSize)
                    {
                        stack.stackSize -= stack.getMaxStackSize() - stackInSlot.stackSize;
                        stackInSlot.stackSize = stack.getMaxStackSize();
                        slot.onSlotChanged();

                        success = true;
                    }
                }

                if(reverseAnalysis)
                    --index;
                else
                    ++index;
            }
        }

        // Search empty slot (which can accept this item !)
        if(stack.stackSize > 0)
        {
            if(reverseAnalysis)
                index = endIndex - 1;
            else
                index = startIndex;

            while(!reverseAnalysis && index < endIndex || reverseAnalysis && index >= startIndex && stack.stackSize > 0)
            {
                slot = (Slot)this.inventorySlots.get(index);
                stackInSlot = slot.getStack();

                if(stackInSlot == null && slot.isItemValid(stack))
                {
                    if(stack.stackSize < slot.getSlotStackLimit())
                    {
                        slot.putStack(stack.copy());
                        stack.stackSize = 0;
                        success = true;

                        break;
                    }
                    else
                    {
                        ItemStack newstack = stack.copy();
                        newstack.stackSize = slot.getSlotStackLimit();
                        slot.putStack(newstack);
                        stack.stackSize -= slot.getSlotStackLimit();

                        success = true;
                    }
                }

                if(reverseAnalysis)
                    --index;
                else
                    ++index;
            }
        }

        return success;
    }

}
