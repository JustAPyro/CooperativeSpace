package interactions;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class Alert {

	String msg;
	int displayTime;
	double opacityPulse = 0.5;
	double pulseHeight = 25;
	
	double x;
	double y;
	int w = 400; int h = 100;
	int cornerRound= 50;
	
	public Alert(String msg, int displayTime) {
		this.displayTime = displayTime;
		this.msg = msg;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		x = screenSize.getWidth()/2;
		y = screenSize.getHeight()*0.75;
	}
	
	public void draw(GraphicsContext gc) {
		
		if (displayTime <= 0 && opacityPulse <= 0)
			return;
		
		if (displayTime < 0)
			opacityPulse -= 0.01;
		else
			opacityPulse = (100-pulseHeight+Math.sin(displayTime/pulseHeight)*pulseHeight)/100;

		gc.save();
		
		gc.setFill(Color.rgb(255, 255, 0, opacityPulse));
		gc.setStroke(Color.rgb(0, 0, 0, opacityPulse));
	
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		gc.strokeRoundRect(x - w/2, y + h/2, w, h, cornerRound, cornerRound);
		gc.fillRoundRect(x - w/2, y + h/2, w, h, cornerRound, cornerRound);
		
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.setFill(Color.rgb(255, 0, 0, opacityPulse));
		gc.setFont(Font.font("veranda", FontWeight.BOLD, FontPosture.REGULAR, 40));
		gc.fillText("Alert!", x, y + 75);
		
		gc.setFont(Font.font("veranda", FontWeight.NORMAL, FontPosture.REGULAR, 25));
		gc.fillText(msg, x, y + 115);
		
		
		
		gc.restore();
		
		displayTime--;
	}
	
	
	
}
