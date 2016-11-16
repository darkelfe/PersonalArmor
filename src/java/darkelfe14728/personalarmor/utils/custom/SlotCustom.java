package darkelfe14728.personalarmor.utils.custom;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


/**
 * @author Julien Rosset
 *
 * Custom slot management.
 * Use {@link IInventoryCustom.getSlotStackLimit} for ItemStack limit.
 * Check item validity using {@link IInventory.isItemValidForSlot}.
 */
public class SlotCustom
    extends Slot
{
    public SlotCustom(IInventoryCustom inventory, int index, int x, int y)
    {
        super(inventory, index, x, y);
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
}
