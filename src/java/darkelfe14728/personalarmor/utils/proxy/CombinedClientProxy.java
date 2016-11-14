package darkelfe14728.personalarmor.utils.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;


/**
 * @author Julien Rosset
 * 
 *         Combined Client (local party) part of proxies.
 */
public class CombinedClientProxy
    extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
    }
    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
    }
    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
    }

    @Override
    public EntityPlayer getPlayer(MessageContext context)
    {
        if(context.side.isClient())
            return Minecraft.getMinecraft().thePlayer;
        else
            return super.getPlayer(context);
    }
}
