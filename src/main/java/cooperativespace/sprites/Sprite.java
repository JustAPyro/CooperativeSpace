package cooperativespace.sprites;

import cooperativespace.sprites.components.Actor;
import cooperativespace.sprites.components.Collider;
import cooperativespace.sprites.components.Physics;
import javafx.scene.canvas.Canvas;

public abstract class Sprite implements Actor, Physics, Collider {

    // - - - - - - - - - - Instance Variables - - - - - - - - - -
    // All sprites contain:

    private double posX;
    private double posY;
    private double rotation;

    private double xVelocity;
    private double yVelocity;

    protected double size = 64;
    private boolean isReal;

    // - - - - - - - - - - Constructors - - - - - - - - - -

    public Sprite(boolean isReal) {
        if (isReal)
            load();
    }

    /**
     * Constructs a sprite at a specified location
     * @param isReal If true this sprite is realized and will call load()
     * @param posX The x position of the sprite
     * @param posY The y position of the sprite
     */
    public Sprite(boolean isReal, double posX, double posY) {

        // If this is a realized sprite, load the assets
        if (isReal)
            load();

        // Set the provided position
        this.posX = posX;
        this.posY = posY;

    }

    abstract public void load();

    public void update() {

    }

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

    // - - - - - - - - - - COLLIDERS - - - - - - - - - -

    // GetXPosition

    // GetYPosition

    public double getColliderSphereSize() {
        return size/2;
    }

    public boolean isReal() {
        return isReal;
    }

}
