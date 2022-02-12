package menu;

import inventory.Offer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class MenuOffer extends MenuElement implements Selectable, Scrollable{

	Offer offer;
	int vBuffer;
	
	public MenuOffer(MainMenu main, Offer offer) {
		this.width = main.width - main.getHorizontalBuffer()*2;
		this.height = 60;
		this.x = main.x + main.getHorizontalBuffer();
		this.y = main.getNextY();
		vBuffer = main.getVerticalBuffer();
		this.offer = offer;
		main.addToSelection(this);
		main.setScrolling(this);
		main.add(this);
	}
	
	public Offer getOffer() {
		return offer;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		
		// Save the GC
		gc.save();
		
		// Set to drawing color
		gc.setFill(Color.BLACK);
		
		// Set to drawing color
		gc.setFill(Color.BLACK);
		
		// Set the font to an appropriate size to write quantities
		gc.setFont(Font.font("veranda", FontWeight.BOLD, FontPosture.REGULAR, 16));
		
		// Set alignment center to draw centered quantities
		gc.setTextAlign(TextAlignment.CENTER);
		
		// Draw the input item
		offer.getInput().drawItemIcon(gc, x+height/2, y + vBuffer, height, height);
		gc.fillText(Integer.toString(offer.getInputQuantity()), x+height/2, y + vBuffer + 27); // Drawing the input quantity
		
		// Generate and draw an arrow indicating conversion direction
		int aw = 46;  int ah = 34; // Variables to make changing arrow position easy
		double[][] arr=createRightArrow(x+width/2-(aw/2), y + vBuffer - (ah/2), aw, ah); // Generating arrow coordinates
		gc.fillPolygon(arr[0], arr[1], 8); // Draws the arrow
		
		// Draw the output item
		offer.getOutput().drawItemIcon(gc, x+width-height/2, y + vBuffer, height, height);
		gc.fillText(Integer.toString(offer.getOutputQuantity()), x+width-height/2, y + vBuffer + 27); // Drawing the output quantity
		
		// Return the gc to default value
		gc.restore();
		
	}

	private double[][] createRightArrow(int x, int y, int width, int height) {
		double[][] ret = new double[2][8];

		ret[0][0] = x + width;
		ret[0][1] = x + width - width/3;
		ret[0][2] = x + width - width/3;
		ret[0][3] = x;
		ret[0][4] = x;
		ret[0][5] = x + width - width/3;
		ret[0][6] = x + width - width/3;
		ret[0][7] = x + width;

		ret[1][0] = y + height/2;
		ret[1][1] = y;
		ret[1][2] = y + height/3;
		ret[1][3] = y + height/3;
		ret[1][4] = y + height - height/3;
		ret[1][5] = y + height - height/3;
		ret[1][6] = y + height;
		ret[1][7] = y + height/2;
		
	    return ret; 
		
		
	}

	@Override
	public void select() {
		offer.execute();
		
	}

	@Override
	public void drawSelected(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
		// Save the GC
		gc.save();
		
		// Set to drawing color
		gc.setFill(Color.RED);
		
		// Set the font to an appropriate size to write quantities
		gc.setFont(Font.font("veranda", FontWeight.BOLD, FontPosture.REGULAR, 16));
		
		// Set alignment center to draw centered quantities
		gc.setTextAlign(TextAlignment.CENTER);
		
		// Draw the input item
		offer.getInput().drawItemIcon(gc, x+height/2, y + vBuffer, height, height); // Drawing the item
		gc.fillText(Integer.toString(offer.getInputQuantity()), x+height/2, y + vBuffer + 27); // Drawing the input quantity
		
		// Generate and draw an arrow indicating conversion direction
		int aw = 46;  int ah = 34; // Variables to make changing arrow position easy
		double[][] arr=createRightArrow(x+width/2-(aw/2), y + vBuffer - (ah/2), aw, ah); // Generating arrow coordinates
		gc.fillPolygon(arr[0], arr[1], 8); // Draws the arrow
		
		// Draw the output item
		offer.getOutput().drawItemIcon(gc, x+width-height/2, y + vBuffer, height, height); // Drawing the item
		gc.fillText(Integer.toString(offer.getOutputQuantity()), x+width-height/2, y + vBuffer + 27); // Drawing the output quantity
		
		
		// Return the gc to default value
		gc.restore();		
	}

	@Override
	public void setToolTip() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void left() {
		offer.incrementQuantity(false);
		
	}

	@Override
	public void right() {
		offer.incrementQuantity(true);
		
	}
	
}
