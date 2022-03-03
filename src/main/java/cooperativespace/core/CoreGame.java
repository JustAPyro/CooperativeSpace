package cooperativespace.core;

import cooperativespace.network.NetworkServer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public class CoreGame implements Runnable {

    // This array establishes which bit in the client-sent package is what action
    public static final Action[] actionByteEncodingOrder =
            {Action.ACCELERATE, Action.REVERSE, Action.ROTATE_RIGHT, Action.ROTATE_LEFT};

    private final static String ipAddress = "localhost";
    private final static int portNumber = 9875;

    // These variables determine how often certain method calls happen in game loop
    private final static double UPS = 60; // Updates per second
    private final static double PPS = 60;  // Pushes per second (Server)

    // - - - - - - - - - - Instance Variables - - - - - - - - - -

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
        ConcurrentHashMap<String, HashSet<Action>> actions = server.getActionMap();
        for (String player : actions.keySet()) {
            System.out.println(actions.get(player));
        }
    }

    // Push to clients
    public void onPush() {

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
