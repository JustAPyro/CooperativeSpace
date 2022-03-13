package cooperativespace.stage;

import cooperativespace.UI.HUD;
import cooperativespace.sprites.PlayerSprite;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

/**
 * Represents the first zone
 */
public class ZoneOne extends WorldStage {

    Image background;


    @Override
    public void loadAssets() {


        background = new Image("file:src/main/resources/images/openworld.png",
                512, 512, true, true);

    }


    public void draw(Canvas canvas) {

        canvas.getGraphicsContext2D().drawImage(background, 0, 0);
        canvas.getGraphicsContext2D().drawImage(background, 512, 0);
        canvas.getGraphicsContext2D().drawImage(background, 0, 512);
        canvas.getGraphicsContext2D().drawImage(background, 512, 512);



        HUD.draw(canvas);

    }

}
