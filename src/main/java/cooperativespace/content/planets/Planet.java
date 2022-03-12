package cooperativespace.content.planets;

import cooperativespace.GameObject;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class Planet implements GameObject {

    private Image image;

    private double posX;
    private double posY;
    private double rot;

    public Planet(String pathToImage, double posX, double posY, int size) {
        image = new Image(pathToImage, size, size, true, true);
        this.posX = posX;
        this.posY = posY;
    }

    public void draw(Canvas canvas) {
        canvas.getGraphicsContext2D().drawImage(image, posX, posY);
    }


}
