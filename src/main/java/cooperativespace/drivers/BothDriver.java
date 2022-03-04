package cooperativespace.drivers;

import cooperativespace.client.Client;
import cooperativespace.core.CoreGame;

public class BothDriver {
    public static void main(String[] args) {
        new CoreGame();
        Client.main(args);
    }
}
