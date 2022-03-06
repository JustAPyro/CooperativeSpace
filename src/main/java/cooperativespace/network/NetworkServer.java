package cooperativespace.network;

import cooperativespace.core.Action;
import cooperativespace.core.CoreGame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public class NetworkServer extends Thread {

    private static final Logger logger = LogManager.getLogger(NetworkServer.class);

    private int portNumber;

    private DatagramSocket socket;

    private ConcurrentHashMap<String, HashSet<Action>> clientPresses;

    public NetworkServer(int portNumber) {
        this.portNumber = portNumber;
        this.start();
    }

    public void push(byte[] packet) {
        try {
            for (String clientAddress : clientPresses.keySet()) {
                String[] information = clientAddress.split(":");
                InetAddress address = InetAddress.getByName(information[0].replace("/", ""));
                int port = Integer.parseInt(information[1]);
                DatagramPacket packetOut = new DatagramPacket(packet, packet.length ,address, port);
                socket.send(packetOut);

            }
        }
        catch (UnknownHostException e) {
            logger.error("Couldn't find IP Address");
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error("Interrupt/Error during server push");
            logger.error(e.toString());
        }

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

                // Get the hashset of actions for this client, otherwise create/insert a new hashset
                HashSet<Action> actions = clientPresses.computeIfAbsent(clientID, k -> new HashSet<>());

                // Get the byte representing the client inputs
                byte inputByte = incomingPacket.getData()[0];

                // For each bit of the input byte, either add or remove actions from the action set
                for (int i = 0; i < 8; i++) {
                    if (((inputByte >>> i) & 1) == 1) {
                        actions.add(CoreGame.actionByteEncodingOrder[i]);
                    }
                    else
                        actions.remove(CoreGame.actionByteEncodingOrder[i]);
                }


            }

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public int getNumberOfConnections() {
        return clientPresses.size();
    }

    public ArrayList<String> getAllPlayers() {

        return new ArrayList<>(clientPresses.keySet());

    }

    public ConcurrentHashMap<String, HashSet<Action>> getActionMap() {
        return clientPresses;
    }

}
