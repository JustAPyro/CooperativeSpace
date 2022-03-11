package cooperativespace.client;

import cooperativespace.core.Action;
import cooperativespace.core.CoreGame;
import cooperativespace.network.NetworkClient;

import cooperativespace.stage.WorldStage;
import cooperativespace.stage.ZoneOne;
import cooperativespace.utilities.UtilByte;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

/**
 * This class maintains all the client side GUI and logic.
 */
public class Client extends Application {

    // Create a logger to manage the class
    private final static Logger logger = LogManager.getLogger(Client.class);

    // Used to track which keys are being pressed
    private final HashSet<KeyCode> keysPressed = new HashSet<>();

    // This is the list of keys that will actually be packed & sent to server
    private static final KeyCode[] keybindings = new KeyCode[8];

    // Variables for networking
    private NetworkClient networkClient;
    private static String ipAddress;
    private static int portNumber;

    // TODO: The world stage should be loaded on commands from a message
    // The current worldStage (i.e. zone or loaded chunks)
    WorldStage worldStage = new ZoneOne();

    // The canvas we're drawing on
    Canvas canvas;

    public static void main(String[] args) {

        // Use the launch arguments to set the IP and port
        if (args.length > 0) {
            ipAddress = args[0];
            portNumber = Integer.parseInt(args[1]);
        }

        // Then launch javafx
        launch(args);

    }

    // Entry point
    @Override
    public void start(Stage stage) {

        // Set the title
        stage.setTitle("Cooperative Space");

        // Add the main canvas
        VBox box = new VBox();
        canvas = new Canvas(1000, 700);
        box.getChildren().add(canvas);

        // Load in the hotkey configuration
        loadHotkeys();

        // Record scene key presses
        Scene scene = new Scene(box);
        scene.setOnKeyPressed(e -> keysPressed.add(e.getCode()));
        scene.setOnKeyReleased(e -> keysPressed.remove(e.getCode()));

        // Create a network
        networkClient = new NetworkClient(ipAddress, portNumber);

        // Load the stage
        worldStage.loadAssets();

        // Game loop on the clint side (note this is locked at 60fps)
        AnimationTimer clock = new AnimationTimer() {
            @Override
            public void handle(long l) {

                // Get the graphics context to draw with
                GraphicsContext gc = canvas.getGraphicsContext2D();

                // Clear the canvas
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                // package the current client-side inputs...
                byte[] keyPackage = packageInputs(keysPressed);

                // ... Then use the client network to push those inputs to server
                networkClient.push(keyPackage);

                // Then, if our network-side client has data
                if (networkClient.hasData()) {

                    // Unpack the data and draw the new world stage.
                    worldStage.unpackState(networkClient.getReceivedData());
                    worldStage.draw(canvas);

                }

            }
        }; clock.start();

        // Set the scene and show the stage
        stage.setScene(scene);
        stage.show();

        // log that the Client was set up
        logger.info("Client completed set-up");

    }

    /**
     * This method handles getting the correct information from the config.json and loading the
     * hotkey configuration desired.
     */
    private void loadHotkeys() {

        // Note that we're loading the config to logs
        logger.info("Attempting to load hotkeys from config file");

        // Create a new file parser
        JSONParser jsonParser = new JSONParser();

        // Try opening a file
        try (FileReader reader = new FileReader("src/main/resources/config.json")) {

            // Read JSON file
            Object obj = jsonParser.parse(reader);

            // Convert to JSON object and fetch the keybindings object
            JSONObject configsObject = (JSONObject) obj;
            JSONObject keybindingsObject = (JSONObject) configsObject.get("keybindings");

            // Log that we've successfully opened/read the file
            logger.info("Config file read, attempting to load values");

            // Insert the keybindings into a tracking array
            for (int i = 0; i < keybindings.length; i++) {

                // Start by finding which action we are attempting to bind
                Action actionToBind = CoreGame.actionByteEncodingOrder[i];

                // Assign the keycode from the keybindings object
                keybindings[i] = KeyCode.getKeyCode(keybindingsObject.get(actionToBind.toString()).toString());

            }

            // Log that we've successfully (hopefully) loaded the hotkeys
            logger.info("Hotkeys loaded successfully");

        }
        catch (FileNotFoundException e) {
            // Log that we failed to find the file
            logger.error("Game config file not found");
            logger.error(e);
        }
        catch (IOException e) {
            // Log that there was an interruption or exception while reading file
            logger.error("IO Exception while reading from config file");
            logger.error(e);
        }
        catch (ParseException e) {
            // Log that there was an error parsing the config file
            logger.error("Error occurred while parsing game config file");
            logger.error(e);
        }

    }

    /**
     * This method packages all current inputs into a byte[] structure to send
     *
     * @return A byte array representing current key-presses
     */
    private static byte[] packageInputs(HashSet<KeyCode> inputs) {

        // start with our working byte
        byte b = 0;

        // Then for each of our keybindings
        for (int i = 0; i < keybindings.length; i++) {

            // If the key is in our current inputs
            if (inputs.contains(keybindings[i]))

                // Set the appropriate bit in our working byte
                b = UtilByte.setBit(b, i);
        }

        // Return the results
        return new byte[]{b};

    }



}
