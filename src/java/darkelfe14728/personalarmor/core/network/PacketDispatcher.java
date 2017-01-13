package darkelfe14728.personalarmor.core.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import darkelfe14728.personalarmor.PersonalArmor;


/**
 * @author Julien Rosset
 * 
 *         Facility class for sending message.
 */
public class PacketDispatcher
{
    /**
     * Global counter for automatic packet ID at registration time.
     */
    private static byte                       packetID   = 0;

    /**
     * The internal dispatcher.
     */
    private static final SimpleNetworkWrapper dispatcher = NetworkRegistry.INSTANCE.newSimpleChannel(PersonalArmor.MOD_ID);

    /**
     * Register the message handler.
     * Auto packet ID generation and guess side from context.
     * 
     * @param handlerClass
     *            Class to react to message
     * @param messageClass
     *            Message class
     */
    public static final <T extends IMessage> void registerMessage(Class<T> messageClass, Class<? extends AbstractMessageHandler<T>> handlerClass)
    {
        if(AbstractClientMessageHandler.class.isAssignableFrom(handlerClass) || AbstractBidirectionalMessageHandler.class.isAssignableFrom(handlerClass))
            PacketDispatcher.dispatcher.registerMessage(handlerClass, messageClass, PacketDispatcher.packetID++, Side.CLIENT);

        if(AbstractServerMessageHandler.class.isAssignableFrom(handlerClass) || AbstractBidirectionalMessageHandler.class.isAssignableFrom(handlerClass))
            PacketDispatcher.dispatcher.registerMessage(handlerClass, messageClass, PacketDispatcher.packetID++, Side.SERVER);
    }
    public static final <T extends AbstractMessageHandler<T>& IMessage> void registerMessage(Class<T> packet)
    {
        PacketDispatcher.registerMessage(packet, packet);
    }

    /*
     * ==========================
     * Convenient sending methods
     * ==========================
     */
    /**
     * Send a message to a specific player
     * 
     * @param message
     *            The message.
     * @param player
     *            The player.
     */
    public static final void sendToPlayer(IMessage message, EntityPlayerMP player)
    {
        PacketDispatcher.dispatcher.sendTo(message, player);
    }
    /**
     * Send a message to everyone.
     * 
     * @param message
     *            The message.
     */
    public static final void sendToAll(IMessage message)
    {
        PacketDispatcher.dispatcher.sendToAll(message);
    }
    /**
     * Send a message to everyone around a point.
     * 
     * @param message
     *            The message.
     * @param point
     *            The target point.
     */
    public static final void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint point)
    {
        PacketDispatcher.dispatcher.sendToAllAround(message, point);
    }
    /**
     * Send a message to everyone around a point.
     * 
     * @param message
     *            The message.
     * @param dimension
     *            The dimension.
     * @param x
     *            X coordinate inside dimension.
     * @param y
     *            Y coordinate inside dimension.
     * @param z
     *            Z coordinate inside dimension.
     * @param range
     *            Range around the point.
     */
    public static final void sendToAllAround(IMessage message, int dimension, double x, double y, double z, double range)
    {
        PacketDispatcher.dispatcher.sendToAllAround(message, new NetworkRegistry.TargetPoint(dimension, x, y, z, range));
    }
    /**
     * Send a message to everyone around a player.
     * 
     * @param message
     *            The message.
     * @param player
     *            The player.
     * @param range
     *            The range around the player.
     */
    public static final void sendToAllAroundPlayer(IMessage message, EntityPlayer player, double range)
    {
        PacketDispatcher.sendToAllAround(message, player.worldObj.provider.dimensionId, player.posX, player.posY, player.posZ, range);
    }
    /**
     * Send a message to a specific dimension.
     * 
     * @param message
     *            The message.
     * @param dimension
     *            The dimension.
     */
    public static final void sendToDimension(IMessage message, int dimension)
    {
        PacketDispatcher.dispatcher.sendToDimension(message, dimension);
    }
    /**
     * Send a message to server side.
     * 
     * @param message
     *            The message.
     */
    public static final void sendToServer(IMessage message)
    {
        PacketDispatcher.dispatcher.sendToServer(message);
    }
}
