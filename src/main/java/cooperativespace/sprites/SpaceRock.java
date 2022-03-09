package cooperativespace.sprites;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class SpaceRock extends Sprite{

    Image image;

    public SpaceRock(boolean isReal) {
        super(isReal);
    }

    @Override
    public void load() {
        image = new Image("C:\\Users\\Luke\\Downloads\\CooperativeSpace-primary\\CooperativeSpace-primary\\src\\main\\resources\\images\\rock.png");
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.getGraphicsContext2D().drawImage(image, getXPosition(), getYPosition());
    }

    @Override
    public byte[] pack() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void unpack(byte[] playerPacket) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
