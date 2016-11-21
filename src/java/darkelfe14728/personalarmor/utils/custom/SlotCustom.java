package darkelfe14728.personalarmor.utils.custom;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


/**
 * @author Julien Rosset
 * 
 *         Custom slot management.
 *         Use {@link IInventoryCustom.getSlotStackLimit} for ItemStack limit.
 *         Check item validity using {@link IInventory.isItemValidForSlot}.
 */
public class SlotCustom
    extends Slot
{
    private final ISlotPickup callback;

    public SlotCustom(IInventoryCustom inventory, int index, int x, int y)
    {
        this(inventory, index, x, y, null);
    }
    public SlotCustom(IInventoryCustom inventory, int index, int x, int y, ISlotPickup callback)
    {
        super(inventory, index, x, y);
        this.callback = callback;
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return super.inventory.isItemValidForSlot(super.getSlotIndex(), stack);
    }

    @Override
    public int getSlotStackLimit()
    {
        return ((IInventoryCustom)super.inventory).getSlotStackLimit(super.getSlotIndex());
    }

    @Override
    public void onPickupFromSlot(EntityPlayer player, ItemStack stack)
    {
        if(this.callback != null)
            this.callback.onSlotPickup(player, this.slotNumber, stack);
    }
}
