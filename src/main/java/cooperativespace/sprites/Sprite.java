package cooperativespace.sprites;

import javafx.scene.canvas.Canvas;

public abstract class Sprite {

    // - - - - - - - - - - Instance Variables - - - - - - - - - -
    // All sprites contain:

    private double speed;
    private double rotation;

    abstract public void load();

    abstract public void update();

    abstract public void draw(Canvas canvas);

    abstract public byte[] pack();

    abstract public void unpack(byte[] playerPacket);

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void modifySpeed(double increment) {
        speed += increment;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public void modifyRotation(double increment) {
        this.rotation += increment;
    }

}
