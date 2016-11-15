package darkelfe14728.personalarmor.building;

import darkelfe14728.personalarmor.utils.InventoryHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;


/**
 * @author Julien Rosset
 *
 * Design Table's GUI-Container
 * 
 * @see DesignTable
 */
public class DesignTableContainer
    extends Container
{
    private DesignTableTE tile;

    /**
     * New GUI-Container
     */
    public DesignTableContainer(DesignTableTE tile, IInventory playerInventory)
    {
        this.tile = tile;
        
        // Hotbar
        for(int line = 0; line < InventoryHelper.Constants.HOTBAR.NB_LINES; line++)
        {
            for(int column = 0; column < InventoryHelper.Constants.HOTBAR.NB_COLUMNS; column++)
            {
                this.addSlotToContainer(new Slot(
                        this.tile, 
                        InventoryHelper.Constants.HOTBAR.SLOT_START + line + column,
                          8 + column * InventoryHelper.Constants.HOTBAR.SLOT_OFFSET_H,
                        142 +   line * InventoryHelper.Constants.HOTBAR.SLOT_OFFSET_V
                ));
            }
        }
        // Inventory
        for(int line = 0; line < InventoryHelper.Constants.INVENTORY.NB_LINES; line++)
        {
            for(int column = 0; column < InventoryHelper.Constants.INVENTORY.NB_COLUMNS; column++)
            {
                this.addSlotToContainer(new Slot(
                        this.tile,
                        InventoryHelper.Constants.INVENTORY.SLOT_START + line + column,
                          8 + column * InventoryHelper.Constants.INVENTORY.SLOT_OFFSET_H,
                        142 +   line * InventoryHelper.Constants.INVENTORY.SLOT_OFFSET_V
                ));
            }
        }
        
        // Paper
        this.addSlotToContainer(new Slot(this.tile, InventoryHelper.Constants.INVENTORY.SLOT_STOP + 1, 0, 0));
    }

    /* 
     * @see net.minecraft.inventory.Container#canInteractWith(net.minecraft.entity.player.EntityPlayer)
     */
    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return this.tile.isUseableByPlayer(player);
    }
}
