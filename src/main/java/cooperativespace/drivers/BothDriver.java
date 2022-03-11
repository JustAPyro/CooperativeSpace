package cooperativespace.drivers;

import cooperativespace.client.Client;
import cooperativespace.core.CoreGame;

public class BothDriver {
    public static void main(String[] args) {

        String PORT_NUMBER = "9875";        // Port the client will connect to
        String IP_ADDRESS = "localhost";    // IP that the client will connect to

        // Start the core game server
        new CoreGame();

        // Pass parameters into main method of the client lass
        Client.main(new String[]{IP_ADDRESS, PORT_NUMBER});

    }
}
