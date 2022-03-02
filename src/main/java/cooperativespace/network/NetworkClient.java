package cooperativespace.network;

import javafx.scene.input.KeyCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.*;
import java.util.HashSet;

public class NetworkClient extends Thread {

    private static final Logger logger = LogManager.getLogger(NetworkClient.class);

    private DatagramSocket socket;
    private DatagramPacket packet;

    private InetAddress ipAddress;
    private int portNumber;

    private byte byteOut;

    public NetworkClient(String ipAddress, int portNumber) {
        try {

            // Parse and assign the IP and port number
            this.ipAddress = parseAddress(ipAddress);
            this.portNumber = portNumber;

            // Initialize a datagram socket
            socket = new DatagramSocket();

        }
        catch (SocketException | UnknownHostException e)  {

            // If an error is thrown log it
            logger.error(e.toString());

        }
    }

    public void push(HashSet<KeyCode> keysPressed) {

    }



    private static byte setBit(byte b, int position) {
        return b |= 1 << position;
    }

    private static byte clearBit(byte b, int position) {
        return b &= ~(1 << position);
    }

    private InetAddress parseAddress(String ipAddress) throws UnknownHostException {

        if (ipAddress.toLowerCase().contains("localhost"))
            return InetAddress.getLocalHost();

        InetAddress[] possibleIPs = InetAddress.getAllByName(ipAddress);

        if (possibleIPs.length == 0)
            throw new UnknownHostException("Couldn't parse IP Address");

        return possibleIPs[0];
    }

}
