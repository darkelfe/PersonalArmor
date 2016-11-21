package darkelfe14728.personalarmor.utils.network;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import darkelfe14728.personalarmor.PersonalArmor;


/**
 * @author Julien Rosset
 * 
 *         Generic message handler : allow to react to message reception
 */
public abstract class AbstractMessageHandler<T extends IMessage>
    implements IMessageHandler<T, IMessage>
{
    public AbstractMessageHandler()
    {}

    @Override
    public IMessage onMessage(T message, MessageContext context)
    {
        if(context.side.isClient())
            return onClientMessage(PersonalArmor.proxy.getPlayer(context), message, context);
        else
            return onServerMessage(PersonalArmor.proxy.getPlayer(context), message, context);
    }

    public abstract IMessage onClientMessage(EntityPlayer player, T message, MessageContext context);
    public abstract IMessage onServerMessage(EntityPlayer player, T message, MessageContext context);
}
