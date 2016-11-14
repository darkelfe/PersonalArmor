package darkelfe14728.personalarmor.utils.proxy;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;


/**
 * @author Julien Rosset
 * 
 *         Common part of proxies classes.
 */
public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {}
    public void init(FMLInitializationEvent event)
    {}
    public void postInit(FMLPostInitializationEvent event)
    {}

    public EntityPlayer getPlayer(MessageContext context)
    {
        return context.getServerHandler().playerEntity;
    }
}
