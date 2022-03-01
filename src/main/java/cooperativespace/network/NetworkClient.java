package cooperativespace.network;

import javafx.scene.input.KeyCode;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;

public class NetworkClient extends Thread {

    private InetAddress ipAddress;
    int portNumber;

    private byte byteOut;

    public NetworkClient(String ipAddress, int portNumber) {
        try {

            this.ipAddress = parseAddress(ipAddress);
            this.portNumber = portNumber;
            DatagramSocket socket = new DatagramSocket();

        }
        catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
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
