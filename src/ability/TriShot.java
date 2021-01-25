package ability;

import inventory.Itemizable;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class TriShot extends Ability{

	@Override
	public void drawItemIcon(GraphicsContext gc, int x, int y, int width, int height) {
		gc.save();
		gc.setFill(Color.LIGHTBLUE);
		gc.setStroke(Color.BLACK);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.setFont(Font.font("veranda", FontWeight.BOLD, FontPosture.REGULAR, 36));
		gc.fillRoundRect(x - width/2, y - height/2, width, height, Itemizable.rounding, Itemizable.rounding);
		gc.strokeRoundRect(x - width/2, y - height/2, width, height, Itemizable.rounding, Itemizable.rounding);
		gc.setFill(Color.BLACK);
		
		System.out.println("Execute");
		
		gc.restore();
		
	}

	@Override
	public boolean stacksWith(Itemizable item) {
		// TODO Auto-generated method stub
		return false;
	}

}
