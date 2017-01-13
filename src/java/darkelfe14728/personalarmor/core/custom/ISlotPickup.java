package darkelfe14728.personalarmor.core.custom;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;


/**
 * @author Julien Rosset
 * 
 */
public interface ISlotPickup
{
    void onSlotPickup(EntityPlayer player, int index, ItemStack stack);
}
