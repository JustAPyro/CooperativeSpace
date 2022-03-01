package cooperativespace.client;

import cooperativespace.network.NetworkClient;

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

import java.util.HashSet;

public class Client extends Application {

    // Create a logger to manage the class
    private final static Logger logger = LogManager.getLogger(Client.class);

    // Used to track which keys are being pressed
    private final HashSet<KeyCode> keysPressed = new HashSet<>();

    // Theis is the list of keys that will actually be sent to server
    private final KeyCode[] keysToPush =
            {KeyCode.W, KeyCode.A, KeyCode.S, KeyCode.D};

    // Variables for networking
    private NetworkClient networkClient;
    private String ipAddress;
    private int portNumber;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        // Set the title
        stage.setTitle("Cooperative Space");

        // Add the main canvas
        VBox box = new VBox();
        Canvas canvas = new Canvas(stage.getWidth(), stage.getHeight());
        box.getChildren().add(canvas);

        // Record scene key presses
        Scene scene = new Scene(box);
        scene.setOnKeyPressed(e -> keysPressed.add(e.getCode()));
        scene.setOnKeyReleased(e -> keysPressed.remove(e.getCode()));

        networkClient = new NetworkClient(ipAddress, portNumber);
        networkClient.start();

        StringBuilder binaryByte = new StringBuilder();
        AnimationTimer clock = new AnimationTimer() {
            @Override
            public void handle(long l) {


                networkClient.push(keysPressed);

                short[] positions = new short[4];
                //if (networkClient.hasData())
                //    positions = PongGame.decodeState(networkClient.getReceivedData());

                GraphicsContext gc = canvas.getGraphicsContext2D();

                // Clear the canvas
                gc.clearRect(0,  0, canvas.getWidth(), canvas.getHeight());

            }
        };
        clock.start();

        stage.setScene(scene);
        stage.show();

        // log that the Client was set up
        logger.info("Client completed set-up");
    }

}
