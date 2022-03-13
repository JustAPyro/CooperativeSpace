package cooperativespace.sprites;

import cooperativespace.sprites.commands.Command;
import cooperativespace.sprites.components.ActionComponent;
import cooperativespace.sprites.components.InputComponent;
import cooperativespace.sprites.components.PhysicsComponent;
import cooperativespace.core.Action;
import cooperativespace.utilities.UtilByte;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileReader;
import java.util.HashSet;

public class PlayerSprite extends Sprite {

    // - - - - - - - - - - Instance Methods - - - - - - - - - -

    // - - - - - Components - - - - -

    // This component handles input, can be switched out for, example, an AI input
    private final InputComponent inputComponent = new InputComponent();

    // This component handles processing commands into action, I.E. a sprite that "does something" (like movement)
    private final ActionComponent actionComponent = new ActionComponent(this);

    // This component handles the physics, which includes updating game positions and checking for collisions
    private final PhysicsComponent physicsComponent = new PhysicsComponent();

    // - - - - - - - - - - Constructors - - - - - - - - - -

    public PlayerSprite(boolean isReal) {
        super(isReal);
    }

    public PlayerSprite(boolean isReal, int x, int y) {
        super(isReal, x, y);
    }

    // - - - - - Other - - - - -

    // The image that represents the sprite
    private Image image;

    // Constructor just calls load (Note that eventually load() should be called on a separate thread on loading screens


    // Updates the player sprite using the provided action/inputs
    public void update(HashSet<Action> actions) {

        // Process inputs and get commands
        HashSet<Command> commands = inputComponent.update(actions);

        // update our action component so it can execute commands
        actionComponent.update(commands);

        // Update the physics component
        physicsComponent.update(this);

    }

    // Loads any resources necessary to run the player
    @Override
    public void load() {

        image = new Image("file:src/main/resources/images/ship.png", 64, 64, true, true);

    }

    // How to draw the player
    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.save();

        gc.translate(getXPosition()+image.getWidth()/2, getYPosition()+image.getWidth()/2);
        gc.rotate(getRotation());
        gc.translate(-image.getHeight()/2, -image.getWidth()/2);

        //
        gc.drawImage(image, 0, 0);
        gc.restore();
    }

    // IMPORTANT : This is how the server packs the player information to send to client
    @Override
    public byte[] pack() {
        byte[] packet = new byte[6];

        byte[] bRot = UtilByte.shortToByteArray((short) getRotation());
        byte[] bX = UtilByte.shortToByteArray((short) getXPosition());
        byte[] bY = UtilByte.shortToByteArray((short) getYPosition());

        System.arraycopy(bX, 0, packet, 0, 2);
        System.arraycopy(bY, 0, packet, 2, 2);
        System.arraycopy(bRot, 0, packet, 4, 2);

        return packet;
    }

    // IMPORTANT : This is how the client UNPACKS the player info and loads it into local objects
    @Override
    public void unpack(byte[] playerPacket) {

        byte[] bX = new byte[2];
        byte[] bY = new byte[2];
        byte[] bRot = new byte[2];

        System.arraycopy(playerPacket, 0, bX, 0, 2);
        System.arraycopy(playerPacket, 2, bY, 0, 2);
        System.arraycopy(playerPacket, 4, bRot, 0, 2);

        // TODO : FIX THIS AT SOME POINT< AXIS ARE MESSED UP
        setXPosition(UtilByte.byteArrayToShort(bX));
        setYPosition(UtilByte.byteArrayToShort(bY));
        setRotation(UtilByte.byteArrayToShort(bRot));

    }
}
