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
    private double size;


    private boolean isReal;

    public Planet(boolean isReal, String pathToImage, double posX, double posY, int size) {
        if (isReal)
            image = new Image(pathToImage, size, size, true, true);
        this.posX = posX;
        this.posY = posY;
        this.size = size;
        this.isReal = isReal;
    }

    public void update() {
        collider.update(this);
    }

    public void draw(Canvas canvas) {
        canvas.getGraphicsContext2D().drawImage(image, posX, posY);

        if (collider.showCollider)
            canvas.getGraphicsContext2D().strokeOval(posX - (size/2), posY - (size/2), size, size);

    }


    @Override
    public double getXPosition() {
        return posX;
    }

    @Override
    public double getYPosition() {
        return posY;
    }

    @Override
    public double getColliderSphereSize() {
        return size/2;
    }

    @Override
    public boolean isReal() {
        return isReal;
    }
}
