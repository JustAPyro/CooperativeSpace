package cooperativespace.core;

import cooperativespace.content.zones.OpenZone;
import cooperativespace.network.NetworkServer;
import cooperativespace.stage.WorldStage;
import cooperativespace.stage.ZoneOne;


public class CoreGame implements Runnable {

    // This array establishes which bit in the client-sent package is what action
    public static final Action[] actionByteEncodingOrder =
            {Action.ACCELERATE, Action.REVERSE, Action.ROTATE_RIGHT, Action.ROTATE_LEFT,
            Action.ABILITY_ONE, Action.ABILITY_TWO, Action.ABILITY_THREE, Action.ABILITY_FOUR};

    private final static String ipAddress = "localhost";
    private final static int portNumber = 9875;

    // These variables determine how often certain method calls happen in game loop
    private final static double UPS = 60; // Updates per second
    private final static double PPS = 60;  // Pushes per second (Server)

    // - - - - - - - - - - Instance Variables - - - - - - - - - -

    // this interface object holds the state information
    WorldStage worldStage = new OpenZone(false);

    // The server managing network communication
    NetworkServer server;

    // Game loop running
    private boolean loopRunning;

    public CoreGame() {
        // Start a new game thread
        Thread gameThread = new Thread(this, "tGame");
        gameThread.start();
    }

    // Initialize at the start of the game
    public void onStart() {

        // Create a new server
        server = new NetworkServer(portNumber);

    }

    // Update positions
    public void onUpdate() {

        // Update the world stage
        worldStage.update(server.getActionMap());

    }

    // Push to clients
    public void onPush() {
        byte[] state = worldStage.packageState();
        server.push(state);
    }

    @Override
    public void run() {

        // Run the on-start method
        onStart();

        // Log the initial time the loop started
        long initialTime = System.nanoTime();

        // Declare the time triggers
        final double timeU = 1000000000 / UPS;
        final double timeP = 1000000000 / PPS;

        // Delta/change in time for triggers
        double deltaU = 0, deltaP = 0;

        // Main game processing loop
        loopRunning = true;
        while (loopRunning) {

            // Save the current time
            long currentTime = System.nanoTime();

            // Increment our delta times
            deltaU += (currentTime - initialTime) / timeU;
            deltaP += (currentTime - initialTime) / timeP;

            // Reset the initial time for the next iteration
            initialTime = currentTime;

            // deltaU(update) is greater than 1 execute that
            if (deltaU >= 1) {
                onUpdate();
                deltaU--;
            }

            if (deltaP >= 1) {
                onPush();
                deltaP--;
            }
        }
    }
}
