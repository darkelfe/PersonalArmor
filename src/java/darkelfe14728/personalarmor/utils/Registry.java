package darkelfe14728.personalarmor.utils;

import net.minecraft.block.Block;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.registry.GameRegistry;
import darkelfe14728.personalarmor.utils.network.AbstractMessageHandler;
import darkelfe14728.personalarmor.utils.network.PacketDispatcher;


/**
 * @author Julien Rosset
 * 
 *         Global class for registration
 */
public abstract class Registry
{
    /**
     * Register a new block
     * 
     * @param block
     *            The block to register
     */
    public static void registerBlock(Block block)
    {
        LogHelper.info("Register block : " + block.getUnlocalizedName());
        GameRegistry.registerBlock(block, block.getUnlocalizedName());
    }

    /**
     * Register a new message
     * 
     * @param handlerClass
     *            Class to react to message
     * @param messageClass
     *            Message class
     */
    public static <T extends IMessage> void registerMessage(Class<? extends AbstractMessageHandler<T>> handlerClass, Class<T> messageClass)
    {
        PacketDispatcher.registerMessage(handlerClass, messageClass);
    }
}
