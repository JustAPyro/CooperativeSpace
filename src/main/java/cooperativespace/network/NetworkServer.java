package cooperativespace.network;

public class NetworkServer extends Thread {

    private int portNumber;

    public NetworkServer(int portNumber) {
        this.portNumber = portNumber;
    }

}
