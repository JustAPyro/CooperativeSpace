package cooperativespace.network;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.*;

public class NetworkClient extends Thread {

    // declare a static logger to track functionality
    private static final Logger logger = LogManager.getLogger(NetworkClient.class);

    // The UDP socket and packet buffer
    private DatagramSocket socket;
    private DatagramPacket packet;

    // Internet (IP) address and port number
    private InetAddress ipAddress;
    private int portNumber;

    // Buffer for the received data and the sending data
    private byte[] receivedData;
    private byte byteOut;

    /**
     * Parses and assigns the the ip and port number, then launches the listening thread to
     * collect new input.
     *
     * @param ipAddress The IP of server (or "localhost" for that)
     * @param portNumber The port number you want to target
     */
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

    /**
     * Note: This method doesn't get called on the server thread
     * @param payload The bytes you want to send
     */
    public void push(byte[] payload) {
        try {
            packet = new DatagramPacket(payload, payload.length, ipAddress, portNumber);
            socket.send(packet);
        }
        catch (IOException e) {
            logger.error(e.toString());
        }
    }

    @Override
    public void run() {
        try {

            final byte[] incomingBuffer = new byte[25];
            final DatagramPacket incomingPacket = new DatagramPacket(incomingBuffer, incomingBuffer.length);

            while (true) {
                socket.receive(incomingPacket);
                receivedData = incomingPacket.getData();
            }

        } catch (IOException e) {
            e.printStackTrace();
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

    public boolean hasData() {
        return receivedData != null;
    }

    public byte[] getReceivedData() {
        return receivedData;
    }

}
