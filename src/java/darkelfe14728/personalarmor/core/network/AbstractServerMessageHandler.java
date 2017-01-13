package darkelfe14728.personalarmor.core.network;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


/**
 * @author Julien Rosset
 * 
 *         Generic handler for server side messages.
 */
public abstract class AbstractServerMessageHandler<T extends IMessage>
    extends AbstractMessageHandler<T>
{

    @Override
    @SideOnly(Side.CLIENT)
    public IMessage onClientMessage(EntityPlayer player, T message, MessageContext context)
    {
        // Server side only, so do nothing in case of client side
        return null;
    }
}
