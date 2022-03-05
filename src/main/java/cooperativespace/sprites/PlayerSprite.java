package cooperativespace.sprites;

import cooperativespace.components.ActorComponent;
import cooperativespace.core.Action;
import cooperativespace.utilities.UtilByte;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashSet;

public class PlayerSprite implements Sprite{

    private ActorComponent actorComponent = new ActorComponent();

    private Image image;
    public double x;
    public double y;
    double rot = 23;

    public PlayerSprite() {
        load();
    }

    public void update(HashSet<Action> actions) {
        actorComponent.update(this, actions);
    }

    @Override
    public void load() {

        image = new Image("C:\\Users\\Luke\\Downloads\\CooperativeSpace-primary\\CooperativeSpace-primary\\src\\main\\resources\\images\\ship.png", 100, 100, true, true);
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        rot = 100;
        gc.save();

        gc.translate(x-image.getWidth()/2, y-image.getHeight()/2);
        gc.rotate(rot);
        //
        gc.drawImage(image, 0, 0);
        gc.restore();
    }


    @Override
    public byte[] pack() {
        byte[] packet = new byte[6];

        byte[] bRot = UtilByte.shortToByteArray((short) rot);
        byte[] bX = UtilByte.shortToByteArray((short) x);
        byte[] bY = UtilByte.shortToByteArray((short) y);

        System.arraycopy(bX, 0, packet, 0, 2);
        System.arraycopy(bY, 0, packet, 2, 2);
        System.arraycopy(bRot, 0, packet, 4, 2);

        return packet;
    }

    @Override
    public void unpack(byte[] playerPacket) {

        byte[] bX = new byte[2];
        byte[] bY = new byte[2];
        byte[] bRot = new byte[2];

        System.arraycopy(playerPacket, 0, bX, 0, 2);
        System.arraycopy(playerPacket, 2, bY, 0, 2);
        System.arraycopy(playerPacket, 4, bRot, 0, 2);

        // TODO : FIX THIS AT SOME POINT< AXIS ARE MESSED UP
        x = UtilByte.byteArrayToShort(bY);
        y = UtilByte.byteArrayToShort(bX);
        rot = UtilByte.byteArrayToShort(bRot);

    }
}
