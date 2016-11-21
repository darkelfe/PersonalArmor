package darkelfe14728.personalarmor.building;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import darkelfe14728.personalarmor.utils.LogHelper;
import darkelfe14728.personalarmor.utils.network.AbstractBidirectionalMessageHandler;


/**
 * @author Julien Rosset
 * 
 */
public class DesignTableGuiPacket
    extends AbstractBidirectionalMessageHandler<DesignTableGuiPacket>
    implements IMessage
{
    private ItemStack output;

    public DesignTableGuiPacket()
    {
        this(null);
    }
    public DesignTableGuiPacket(ItemStack output)
    {
        super();
        this.output = output;
    }

    @Override
    public void fromBytes(ByteBuf from)
    {
        this.output = ByteBufUtils.readItemStack(from);
    }
    @Override
    public void toBytes(ByteBuf to)
    {
        ByteBufUtils.writeItemStack(to, this.output);
    }

    @Override
    public IMessage onClientMessage(EntityPlayer player, DesignTableGuiPacket message, MessageContext context)
    {
        LogHelper.info("Message on client-side !");
        this.onMessage(player, message, context);
        return null;
    }
    @Override
    public IMessage onServerMessage(EntityPlayer player, DesignTableGuiPacket message, MessageContext context)
    {
        LogHelper.info("Message on server-side !");
        this.onMessage(player, message, context);
        return null;
    }

    private void onMessage(EntityPlayer player, DesignTableGuiPacket message, MessageContext context)
    {
        LogHelper.info((message.output == null ? "aïe" : "cool"));
        
        Container container = player.openContainer;
        if(container instanceof DesignTableContainer)
            ((DesignTableContainer)container).setOuput(message.output);
    }
}
