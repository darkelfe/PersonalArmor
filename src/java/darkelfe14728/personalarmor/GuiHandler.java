package darkelfe14728.personalarmor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import darkelfe14728.personalarmor.building.AssemblingTableContainer;
import darkelfe14728.personalarmor.building.AssemblingTableGui;
import darkelfe14728.personalarmor.building.AssemblingTableTE;
import darkelfe14728.personalarmor.building.DesignTableContainer;
import darkelfe14728.personalarmor.building.DesignTableGui;
import darkelfe14728.personalarmor.building.DesignTableTE;


/**
 * @author Julien Rosset
 * 
 *         GUI Handler
 *         Auto-generate GUI id.
 *         Return correct object associated to an GUI id.
 */
public class GuiHandler
    implements IGuiHandler
{
    /*
     * @see cpw.mods.fml.common.network.IGuiHandler#getServerGuiElement(int, net.minecraft.entity.player.EntityPlayer,
     * net.minecraft.world.World, int, int, int)
     */
    @Override
    public Object getServerGuiElement(int gui_id, EntityPlayer player, World world, int x, int y, int z)
    {
        switch(gui_id)
        {
            case DesignTableGui.GUI_ID:
                return new DesignTableContainer((DesignTableTE)world.getTileEntity(x, y, z), player.inventory);
                
            case AssemblingTableGui.GUI_ID:
                return new AssemblingTableContainer((AssemblingTableTE)world.getTileEntity(x, y, z), player.inventory);
        }

        return null;
    }
    /*
     * @see cpw.mods.fml.common.network.IGuiHandler#getClientGuiElement(int, net.minecraft.entity.player.EntityPlayer,
     * net.minecraft.world.World, int, int, int)
     */
    @Override
    public Object getClientGuiElement(int gui_id, EntityPlayer player, World world, int x, int y, int z)
    {
        switch(gui_id)
        {
            case DesignTableGui.GUI_ID:
                return new DesignTableGui((DesignTableTE)world.getTileEntity(x, y, z), player.inventory);

            case AssemblingTableGui.GUI_ID:
                return new AssemblingTableGui((AssemblingTableTE)world.getTileEntity(x, y, z), player.inventory);
        }

        return null;
    }
}
