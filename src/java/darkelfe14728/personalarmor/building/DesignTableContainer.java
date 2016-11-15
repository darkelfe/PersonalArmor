package darkelfe14728.personalarmor.building;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;


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
    

    /**
     * New GUI-Container
     */
    public DesignTableContainer()
    {}

    /* 
     * @see net.minecraft.inventory.Container#canInteractWith(net.minecraft.entity.player.EntityPlayer)
     */
    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }
}
