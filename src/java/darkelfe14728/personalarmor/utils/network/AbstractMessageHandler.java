package darkelfe14728.personalarmor.utils.network;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkelfe14728.personalarmor.PersonalArmor;


/**
 * @author Julien Rosset
 * 
 *         Generic message handler : allow to react to message reception
 */
public abstract class AbstractMessageHandler<T extends IMessage>
    implements IMessageHandler<T, IMessage>
{
    @Override
    public IMessage onMessage(T message, MessageContext context)
    {
        if(context.side.isClient())
            return onClientMessage(PersonalArmor.proxy.getPlayer(context), message, context);
        else
            return onServerMessage(PersonalArmor.proxy.getPlayer(context), message, context);
    }

    /**
     * Override to define reaction and response message to a client side message
     * 
     * @param player
     *            The player (message target)
     * @param message
     *            The receive message
     * @param context
     *            The context
     * 
     * @return The response message (if any, else null)
     */
    @SideOnly(Side.CLIENT)
    public abstract IMessage onClientMessage(EntityPlayer player, T message, MessageContext context);
    /**
     * Override to define reaction and response message to a server side message
     * 
     * @param player
     *            The player (message target)
     * @param message
     *            The receive message
     * @param context
     *            The context
     * 
     * @return The response message (if any, else null)
     */
    @SideOnly(Side.SERVER)
    public abstract IMessage onServerMessage(EntityPlayer player, T message, MessageContext context);
}
