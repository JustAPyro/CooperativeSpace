package cooperativespace.sprites;

import cooperativespace.sprites.components.Actor;
import cooperativespace.sprites.components.Physics;
import javafx.scene.canvas.Canvas;

public abstract class Sprite implements Actor, Physics {

    // - - - - - - - - - - Instance Variables - - - - - - - - - -
    // All sprites contain:

    private double posX;
    private double posY;
    private double rotation;

    private double xVelocity;
    private double yVelocity;

    abstract public void load();

    abstract public void update();

    abstract public void draw(Canvas canvas);

    abstract public byte[] pack();

    abstract public void unpack(byte[] playerPacket);

    // - - - - - - - - - - ACTOR Methods - - - - - - - - - -

    @Override
    public double getXVelocity() {
        return xVelocity;
    }

    @Override
    public void setXVelocity(double speed) {
        this.xVelocity = xVelocity;
    }

    @Override
    public void modifyXVelocity(double increment) {
        xVelocity += increment;
    }

    @Override
    public double getYVelocity() {
        return yVelocity;
    }

    @Override
    public void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    @Override
    public void modifyYVelocity(double increment) {
        yVelocity += increment;
    }

    @Override
    public double getRotation() {
        return rotation;
    }

    @Override
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    @Override
    public void modifyRotation(double increment) {
        this.rotation += increment;
    }

    // - - - - - - - - - - PHYSICS Methods - - - - - - - - - -



    public double getXPosition() {
        return posX;
    }

    public void setXPosition(double x) {
        posX = x;
    }

    public void modifyXPosition(double increment) {
        posX += increment;
    }

    public double getYPosition() {
        return posY;
    }

    public void setYPosition(double y) {
        posY = y;
    }

    public void modifyYPosition(double increment) {
        posY += increment;
    }

}
