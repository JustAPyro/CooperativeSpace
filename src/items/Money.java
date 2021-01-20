package items;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import inventory.ItemCategory;
import inventory.Itemizable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Money implements Itemizable{

	Image img;
	
	// Deftault constructor initalizes the image
	public Money() {
		try {
			img = new Image(new FileInputStream("./Resources/Images/gold.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void drawItemIcon(GraphicsContext gc, int x, int y, int width, int height) {
		gc.save(); // Save graphic status
		
		gc.setFill(Color.YELLOW); //Fill with yellow
		gc.setStroke(Color.BLACK); // Stroke with black
		
		// fill and stroke the background of the item
		gc.fillRoundRect(x - width/2, y - height/2, width, height, Itemizable.rounding, Itemizable.rounding);
		gc.strokeRoundRect(x - width/2, y - height/2, width, height, Itemizable.rounding, Itemizable.rounding);
		
		//draw the icon for gold 
		gc.drawImage(img, x - (width-10)/2, y - height/3, width-10, height/2);
		
		gc.restore();
		
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.MONEY;
	}

	@Override
	public boolean stacksWith(Itemizable item) {
		if (this.getClass().equals(item.getClass())) {
			return true;
		}
		return false;
	}

}
