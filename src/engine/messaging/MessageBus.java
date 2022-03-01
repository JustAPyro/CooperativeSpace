package engine.messaging;

import engine.systems.SystemInterface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MessageBus {

    ArrayList<SystemInterface> subsystems;  // A reference to each subsystem on this message bus
    Queue<Message> messageQueue;            // A queue for all messages being sent

    public MessageBus() {

        // Initialize the data structures
        subsystems = new ArrayList<>();
        messageQueue = new LinkedList<>();

    }

    // Launch engine from console
    public static void main(String[] args) {
        Message.create(MessageType.INITIALIZE_SYSTEMS);
    }

}
