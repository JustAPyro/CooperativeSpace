package cooperativespace.UI;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HUD {


    public static void draw(Canvas canvas) {

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.save();

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(20, 20, 210, 38);

        gc.setFill(Color.RED);
        gc.fillRect(25, 25, 200, 14);

        gc.setFill(Color.BLUE);
        gc.fillRect(25, 45, 200, 8);

        gc.restore();

    }
}
