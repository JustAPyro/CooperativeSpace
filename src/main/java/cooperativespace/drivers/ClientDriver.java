package cooperativespace.drivers;

import cooperativespace.client.Client;

/**
 * This is a driver class that will launch just the client.
 */
public class ClientDriver {
    public static void main(String[] args) {

        String PORT_NUMBER = "9875";        // Port the client will connect to
        String IP_ADDRESS = "localhost";    // IP that the client will connect to

        // Pass parameters into main method of the client lass
        Client.main(new String[]{IP_ADDRESS, PORT_NUMBER});
    }
}
