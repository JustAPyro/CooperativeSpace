package cooperativespace.content.planets;

import cooperativespace.GameObject;
import cooperativespace.sprites.components.Collider;
import cooperativespace.sprites.components.ColliderComponent;
import cooperativespace.sprites.components.Physics;
import cooperativespace.sprites.components.PhysicsComponent;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class Planet implements GameObject, Collider {

    private final ColliderComponent collider = new ColliderComponent(this);

    private Image image;

    private double posX;
    private double posY;
    private double rot;

    PhysicsComponent physics = new PhysicsComponent();

    public Planet(String pathToImage, double posX, double posY, int size) {
        image = new Image(pathToImage, size, size, true, true);
        this.posX = posX;
        this.posY = posY;
    }

    public void update() {

    }

    public void draw(Canvas canvas) {
        canvas.getGraphicsContext2D().drawImage(image, posX, posY);
    }


    @Override
    public double getX() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double getY() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double getSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
