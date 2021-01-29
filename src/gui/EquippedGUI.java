package gui;

import java.util.LinkedList;

import application.GameObjects;
import application.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
		
		gc.setFill(Color.BLACK);
		gc.strokeText("Equipped", canvas.getWidth()/2,  canvas.getHeight()/4);
	}
	
	
}
