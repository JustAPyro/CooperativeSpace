package cooperativespace.network;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ConcurrentHashMap;

public class NetworkServer extends Thread {

    private static final Logger logger = LogManager.getLogger(NetworkServer.class);

    private int portNumber;

    private DatagramSocket socket;

    private ConcurrentHashMap<String, boolean[]> clientPresses;

    public NetworkServer(int portNumber) {
        this.portNumber = portNumber;
        this.start();
    }

    @Override
    public void run() {

        // Log that the server has been launched
        logger.info("Server Listener launched");

        try {

            // Creating a datagram packet for incoming data and create a byte buffer
            final byte[] incomingBuffer = new byte[1];
            final DatagramPacket incomingPacket = new DatagramPacket(incomingBuffer, 1);

            // Start the socket
            socket = new DatagramSocket(portNumber);

            // Synchronize hashset
            clientPresses = new ConcurrentHashMap<>();

            while (true) {


                // Receive incoming data
                socket.receive(incomingPacket);

                // Collect the address and port of the packet
                InetAddress address = incomingPacket.getAddress();
                int port = incomingPacket.getPort();

                // Construct the client ID using address:port (Should be unique)
                String clientID = address.toString() + ":" + port;

                // Format the incoming packet
                String inputs = String.format("%07d", Integer.parseInt(Integer.toBinaryString(incomingPacket.getData()[0])));
                System.out.println(inputs);
                char[] chars = inputs.toCharArray();
                boolean[] keyPress = new boolean[chars.length];
                for (int i = 0; i < chars.length; i++)
                    keyPress[i] = chars[i] == '0';

                clientPresses.put(clientID, keyPress);

            }

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
