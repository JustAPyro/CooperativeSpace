package cooperativespace.client;

import cooperativespace.core.Action;
import cooperativespace.core.CoreGame;
import cooperativespace.network.NetworkClient;

import cooperativespace.stage.WorldStage;
import cooperativespace.stage.ZoneOne;
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

public class Client extends Application {

    // Create a logger to manage the class
    private final static Logger logger = LogManager.getLogger(Client.class);

    // Used to track which keys are being pressed
    private final HashSet<KeyCode> keysPressed = new HashSet<>();

    // This is the list of keys that will actually be packed & sent to server
    private static final KeyCode[] keybindings = new KeyCode[4];

    // Variables for networking
    private NetworkClient networkClient;
    private String ipAddress = "localhost";
    private int portNumber = 9875;

    WorldStage worldStage = new ZoneOne();

    Canvas canvas;

    public static void main(String[] args) {
        launch(args);
    }

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

        AnimationTimer clock = new AnimationTimer() {
            @Override
            public void handle(long l) {

                GraphicsContext gc = canvas.getGraphicsContext2D();

                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                byte[] keyPackage = packageInputs(keysPressed);
                networkClient.push(keyPackage);

                if (networkClient.hasData()) {
                    worldStage.unpackState(networkClient.getReceivedData());
                    worldStage.draw(canvas);
                }
            }
        }; clock.start();

        stage.setScene(scene);
        stage.show();

        // log that the Client was set up
        logger.info("Client completed set-up");
    }


    private void loadHotkeys() {

        logger.info("Attempting to load hotkeys from config file");

        // Create a new file parser
        JSONParser jsonParser = new JSONParser();

        // Try opening a file
        try (FileReader reader = new FileReader("C:\\Users\\Luke\\Downloads\\CooperativeSpace-primary\\CooperativeSpace-primary\\src\\main\\resources\\config.json")) {

            // Read JSON file
            Object obj = jsonParser.parse(reader);

            // Convert to JSON object and fetch the keybindings object
            JSONObject configsObject = (JSONObject) obj;
            JSONObject keybindingsObject = (JSONObject) configsObject.get("keybindings");

            logger.info("Config file read, attempting to load values");

            // Insert the keybindings into a tracking array
            for (int i = 0; i < keybindings.length; i++) {

                // Start by finding which action we are attempting to bind
                Action actionToBind = CoreGame.actionByteEncodingOrder[i];

                // Assign the keycode from the keybindings object
                keybindings[i] = KeyCode.getKeyCode(keybindingsObject.get(actionToBind.toString()).toString());

            }

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
     * @return
     */
    private static byte[] packageInputs(HashSet<KeyCode> inputs) {
        byte b = 0;
        for (int i = 0; i < keybindings.length; i++) {
            if (inputs.contains(keybindings[i]))
                b = setBit(b, i);
        }

        return new byte[]{b};
    }

    private static byte setBit(byte b, int position) {
        return b |= 1 << position;
    }

    private static byte clearBit(byte b, int position) {
        return b &= ~(1 << position);
    }


}
