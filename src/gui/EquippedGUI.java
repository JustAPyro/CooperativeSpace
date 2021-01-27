package gui;

import java.util.LinkedList;

import application.GameObjects;
import application.Player;
import javafx.scene.canvas.GraphicsContext;

public class EquippedGUI {

	private static EquippedGUI eqgui = null;
	
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
		for (Player p : player) {
			if (p.isEquipOpen())
				active = true;
		}
		
		GraphicsContext gc = GameObjects.get().gameCanvas().getGraphicsContext2D();
		gc.fillRect(0, 0, 50, 50);
		
	}
	
	
}
