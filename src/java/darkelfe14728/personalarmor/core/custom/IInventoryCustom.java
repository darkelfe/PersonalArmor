package darkelfe14728.personalarmor.core.custom;

import net.minecraft.inventory.IInventory;


/**
 * @author Julien Rosset
 * 
 *         Custom inventory interface.
 * 
 * @see SlotCustom
 */
public interface IInventoryCustom
    extends IInventory
{
    /**
     * @param slot
     *            The slot.
     * 
     * @return The stack limit for a particular slot.
     */
    public int getSlotStackLimit(int slot);
}
