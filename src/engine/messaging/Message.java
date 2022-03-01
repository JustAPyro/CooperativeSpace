package engine.messaging;

public class Message {

    // The type of message this is
    private MessageType messageType;

    // Private constructor to ensure that all messages are created through the factory methods
    private Message(MessageType messageType) {
        this.messageType = messageType;
    }

    // Allows creation of messages
    public static Message create(MessageType messageType) {
        return new Message(messageType);
    }

}
