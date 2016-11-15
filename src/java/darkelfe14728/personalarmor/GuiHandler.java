package darkelfe14728.personalarmor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;


/**
 * @author Julien Rosset
 *
 * GUI Handler
 * Auto-generate GUI id.
 * Return correct object associated to an GUI id. 
 */
public class GuiHandler
    implements IGuiHandler
{
    /**
     * Internal counter of already generated GUI id-s.
     */
    private static int gui_generator = 0;
    /**
     * Generate a new GUI id.
     * 
     * @return The new GUI id.
     */
    public static int nextGuiId()
    {
        return ++gui_generator;
    }

    /*
     * @see cpw.mods.fml.common.network.IGuiHandler#getServerGuiElement(int, net.minecraft.entity.player.EntityPlayer, net.minecraft.world.World, int, int, int)
     */
    @Override
    public Object getServerGuiElement(int gui_id, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }
    /*
     * @see cpw.mods.fml.common.network.IGuiHandler#getClientGuiElement(int, net.minecraft.entity.player.EntityPlayer, net.minecraft.world.World, int, int, int)
     */
    @Override
    public Object getClientGuiElement(int gui_id, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }
}
