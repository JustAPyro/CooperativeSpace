package cooperativespace.network;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.*;

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

            // Start the thread
            this.start();

        }
        catch (SocketException | UnknownHostException e)  {

            // If an error is thrown log it
            logger.error(e.toString());

        }
    }

    public void push(byte[] payload) {
        try {
            packet = new DatagramPacket(payload, payload.length, ipAddress, portNumber);
            socket.send(packet);
        }
        catch (IOException e) {
            logger.error(e.toString());
        }
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
