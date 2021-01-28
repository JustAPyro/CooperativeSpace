package gui;

import java.util.LinkedList;

import application.GameObjects;
import application.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

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
	
		gc.fillRect(canvas.getWidth()/2-width/2, canvas.getHeight()/2-height/2, width, height);
		
	}
	
	
}
