package darkelfe14728.personalarmor.core.network;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


/**
 * @author Julien Rosset
 * 
 *         Generic handler for client side messages.
 */
public abstract class AbstractClientMessageHandler<T extends IMessage>
    extends AbstractMessageHandler<T>
{
    @Override
    @SideOnly(Side.SERVER)
    public IMessage onServerMessage(EntityPlayer player, T message, MessageContext context)
    {
        // Client side only, so do nothing in case of server side
        return null;
    }
}
