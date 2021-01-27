package gui;

import java.util.LinkedList;

import application.Player;

public class EquippedGUI {

	private static EquippedGUI eqgui = null;
	
	private  EquippedGUI() {
		
	}
	
	public static EquippedGUI get() {
		if (eqgui == null)
			eqgui = new EquippedGUI();
		
		return eqgui;
	}
	
	public void draw(LinkedList<Player> player) {
		boolean active = false;
		for (Player p : player) {
			if (p.isEquipOpen())
				active = true;
		}
		
	}
	
	
}
