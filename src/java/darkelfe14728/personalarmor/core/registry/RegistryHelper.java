package darkelfe14728.personalarmor.core.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.registry.GameRegistry;
import darkelfe14728.personalarmor.core.LogHelper;
import darkelfe14728.personalarmor.core.network.AbstractMessageHandler;
import darkelfe14728.personalarmor.core.network.PacketDispatcher;


/**
 * @author Julien Rosset
 * 
 *         Global class for registration
 */
public abstract class RegistryHelper
{
    /**
     * Register a new item.
     * 
     * @param item
     *            The item to register.
     */
    public static void registerItem(Item item)
    {
        GameRegistry.registerItem(item, item.getUnlocalizedName());
    }

    /**
     * Register a new block
     * 
     * @param block
     *            The block to register.
     */
    public static void registerBlock(Block block)
    {
        LogHelper.info("Register block : " + block.getUnlocalizedName());
        GameRegistry.registerBlock(block, block.getUnlocalizedName());
    }
    /**
     * Register a new tile entity (of an registered block).
     * 
     * @param block
     *            The block associated with tile entity.
     * @param tile_entity
     *            The tile entity class.
     */
    public static void registerTileEntity(Block block, Class<? extends TileEntity> tile_entity)
    {
        LogHelper.info("Register tile entity for block : " + block.getUnlocalizedName());
        GameRegistry.registerTileEntity(tile_entity, block.getUnlocalizedName() + ".tile_entity");
    }
    /**
     * Register a new block and its tile entity
     * 
     * @param block
     *            The block.
     * @param tile_entity
     *            The tile entity class.
     */
    public static void registerBlockWithTileEntity(Block block, Class<? extends TileEntity> tile_entity)
    {
        RegistryHelper.registerBlock(block);
        RegistryHelper.registerTileEntity(block, tile_entity);
    }

    /**
     * Register a generic recipe.
     * 
     * @param output
     *            Output item stack.
     * @param params
     *            The recipe design.
     */
    public static void registerRecipe(ItemStack output, Object... params)
    {
        GameRegistry.addRecipe(output, params);
    }
    /**
     * Register a recipe for a block.
     * 
     * @param output
     *            Output block.
     * @param params
     *            The recipe design.
     */
    public static void registerBlockRecipe(Block output, Object... params)
    {
        LogHelper.info("Register recipe : block " + output.getUnlocalizedName());
        RegistryHelper.registerRecipe(new ItemStack(output), params);
    }

    /**
     * Register a new message
     * 
     * @param handlerClass
     *            Class to react to message
     * @param messageClass
     *            Message class
     */
    public static <T extends IMessage> void registerMessage(Class<T> messageClass, Class<? extends AbstractMessageHandler<T>> handlerClass)
    {
        PacketDispatcher.registerMessage(messageClass, handlerClass);
    }
    public static final <T extends AbstractMessageHandler<T>& IMessage> void registerMessage(Class<T> packet)
    {
        PacketDispatcher.registerMessage(packet);
    }
}
