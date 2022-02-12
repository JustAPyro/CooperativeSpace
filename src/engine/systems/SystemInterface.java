package engine.systems;

import engine.messaging.Message;

/**
 * This abstract interface declares what methods a engine system are required to implement
 * in order to communicate with the message bus (and thus other systems)
 */
public abstract interface SystemInterface {

    public abstract void handleMessage(Message msg);

    public abstract void sendMessage(Message msg);

}
