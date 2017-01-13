package darkelfe14728.personalarmor.core.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;


/**
 * @author Julien Rosset
 * 
 *         Generic handler for message received on both sides.
 */
public abstract class AbstractBidirectionalMessageHandler<T extends IMessage>
    extends AbstractMessageHandler<T>
{}
