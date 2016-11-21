package darkelfe14728.personalarmor.utils.custom;

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
