package gui;

import java.util.LinkedList;

import application.GameObjects;
import application.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

// This is a singleton class in charge of managing the equipped/character GUI's
public class EquippedGUI {

	// One instance of the class saved as static
	private static EquippedGUI eqgui = null;
	
	// Total height and width of the GUI
	private int width = 600;
	private int height = 300;
	
	// If the GUI is open or not
	private boolean open = false;
	
	// Private constructor
	private  EquippedGUI() {
		
	}
	
	// Singleton get method
	public static EquippedGUI get() {
		
		// If there's no instance already created then create a new one
		if (eqgui == null)
			eqgui = new EquippedGUI();
		
		// Otherwise return the one that we have
		return eqgui;
	}
	
	// Toggles the open state of the GUI
	public void toggle()
	{
		// Toggles if the character pane is open or not
		open = !open; 
	}
	
	// Called each frame to draw the GUI
	public void draw() {
		
		if (open == false)	// If the character pane isn't open
			return;			// Just skip drawing everything
		
		// Get the list of all players
		LinkedList<Player> players = GameObjects.get().players();
		
		// This bit checks who has an equipment  menu open and who doesn't
		boolean active = false;
		for (Player p : players) {
			if (p.isEquipOpen())
				active = true;
		}
	
		// Get the game canvas and generate the graphics context
		Canvas canvas = GameObjects.get().gameCanvas();
		GraphicsContext gc = canvas.getGraphicsContext2D();
	
		// Set fill to the background fill color
		gc.setFill(Color.AZURE);
		
		// Fill and stroke the main menu rect
		gc.fillRect(canvas.getWidth()/2-width/2, canvas.getHeight()/2-height/2, width, height);
		gc.strokeRect(canvas.getWidth()/2-width/2, canvas.getHeight()/2-height/2, width, height);
		
		// Draw the title of the pane
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFont(GameObjects.get().menuTitleFont(36));
		gc.setFill(Color.BLACK);
		gc.fillText("Equipped", canvas.getWidth()/2,  canvas.getHeight()/3+100);
		
		// Use the sub-method to draw the characters info into the pane
		drawPlayerEquip(GameObjects.get().players().getFirst(), canvas.getWidth()/2, canvas.getHeight()/3+200);
	}
	
	// This is a sub-method of the draw() function, it manages drawing a specific /player/ character pane at /x/, /y/
	private void drawPlayerEquip(Player player, double x, double y) {
		
		// Start by getting the canvas and Graphics context
		Canvas canvas = GameObjects.get().gameCanvas();
		GraphicsContext gc = canvas.getGraphicsContext2D();
	
		// Save it because we will be making modificaitons
		gc.save();
		
		// Get and draw the players image
		Image ship = player.getShip().getShipImage();
		gc.drawImage(ship, x-ship.getWidth()/2, y-ship.getHeight()/2);
		
		
		// Stroke the power slots
		gc.strokeRect(x+ship.getWidth()/2+10, y+10, 25, 25);
		gc.strokeRect(x+ship.getWidth()/2+10, y-35, 25, 25);
		gc.strokeRect(x-ship.getWidth()/2-10-25, y+10, 25, 25);
		gc.strokeRect(x-ship.getWidth()/2-10-25, y-35, 25, 25);
		
		// Fill the power slots
		gc.setFill(Color.LIGHTPINK);
		gc.fillRect(x+ship.getWidth()/2+10, y+10, 25, 25);
		
		gc.setFill(Color.LIGHTBLUE);
		gc.fillRect(x+ship.getWidth()/2+10, y-35, 25, 25);
		
		gc.setFill(Color.LIGHTGREEN);
		gc.fillRect(x-ship.getWidth()/2-10-25, y+10, 25, 25);
		
		gc.setFill(Color.LIGHTYELLOW);
		gc.fillRect(x-ship.getWidth()/2-10-25, y-35, 25, 25);		
		
		// Restore the gc before completing sub method
		gc.restore();

		
	}
	
	
}
