package gui;

import java.util.LinkedList;

import application.GameObjects;
import application.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class EquippedGUI {

	private static EquippedGUI eqgui = null;
	
	private int width = 600;
	private int height = 300;
	
	private  EquippedGUI() {
		
	}
	
	public static EquippedGUI get() {
		if (eqgui == null)
			eqgui = new EquippedGUI();
		
		return eqgui;
	}
	
	public void draw() {
		LinkedList<Player> players = GameObjects.get().players();
		boolean active = false;
		for (Player p : players) {
			if (p.isEquipOpen())
				active = true;
		}
	
		Canvas canvas = GameObjects.get().gameCanvas();
		GraphicsContext gc = canvas.getGraphicsContext2D();
	
		gc.setFill(Color.AZURE);
		gc.fillRect(canvas.getWidth()/2-width/2, canvas.getHeight()/2-height/2, width, height);
		gc.strokeRect(canvas.getWidth()/2-width/2, canvas.getHeight()/2-height/2, width, height);
		
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFont(GameObjects.get().menuTitleFont(36));
		gc.setFill(Color.BLACK);
		gc.fillText("Equipped", canvas.getWidth()/2,  canvas.getHeight()/3+100);
		
		drawPlayerEquip(GameObjects.get().players().getFirst(), canvas.getWidth()/2, canvas.getHeight()/3+200);
	}
	
	private void drawPlayerEquip(Player player, double x, double y) {
		Canvas canvas = GameObjects.get().gameCanvas();
		GraphicsContext gc = canvas.getGraphicsContext2D();
	
		gc.save();
		
		Image ship = player.getShip().getShipImage();
		gc.drawImage(ship, x-ship.getWidth()/2, y-ship.getHeight()/2);
		
		gc.strokeRect(x+ship.getWidth()/2+10, y+10, 25, 25);
		gc.strokeRect(x+ship.getWidth()/2+10, y-35, 25, 25);
		gc.strokeRect(x-ship.getWidth()/2-10-25, y+10, 25, 25);
		gc.strokeRect(x-ship.getWidth()/2-10-25, y-35, 25, 25);
		
		gc.setFill(Color.LIGHTPINK);
		gc.fillRect(x+ship.getWidth()/2+10, y+10, 25, 25);
		
		gc.setFill(Color.LIGHTBLUE);
		gc.fillRect(x+ship.getWidth()/2+10, y-35, 25, 25);
		
		gc.setFill(Color.LIGHTGREEN);
		gc.fillRect(x-ship.getWidth()/2-10-25, y+10, 25, 25);
		
		gc.setFill(Color.LIGHTYELLOW);
		gc.fillRect(x-ship.getWidth()/2-10-25, y-35, 25, 25);		
		
		gc.restore();

		
	}
	
	
}
