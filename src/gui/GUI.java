package gui;

import java.util.LinkedList;

import application.Player;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import sun.java2d.windows.GDIWindowSurfaceData;

public class GUI {

	private static GUI instanceGUI = null;
	
	private LinkedList<Player> players = new LinkedList<Player>();
	
	private GUI() {
		// Private constructor
	}
	
	// get Instance to get the one possible instance
	public static GUI getInstance() {
		if (instanceGUI == null) {
			instanceGUI = new GUI();
		}
		
		return instanceGUI;
	}
	
	// Adds players to the GUI (Can pass in up to 4)
	public void addPlayers(Player ... players) {
		if (players.length > 4 || players.length < 1) {
			throw new RuntimeException("Can only have 1-4 players!");
		}
		
		for (int i = 0; i < players.length; i ++) {
			this.players.add(players[i]);
		}
	}
	
	public void draw(GraphicsContext gc) {
		gc.save(); // Save the graphics brush
		
		players.forEach(player -> drawPlayerGUI(player, gc));
		
		
	}
	
	private void drawPlayerGUI(Player player, GraphicsContext gc) {
		int x = player.getGUIx();
		int y = player.getGUIy();
		int healthWidth = 300; int healthHeight = 30;
		
		gc.save();
		
		//gc.strokeRect(x, y, 500, 500);
		
		gc.setFill(player.getPlayerColor()); gc.setStroke(Color.BLACK); // Set stroke and fill based on player color
		gc.setFont(Font.font("veranda", FontWeight.BOLD, FontPosture.ITALIC, 26));
		gc.fillText(player.getName(), x, y + 60);
		gc.strokeText(player.getName(), x, y + 60);
		
		gc.setFill(Color.DARKRED);
		gc.fillRect(x, y, healthWidth*(player.getShip().getHealthPercent()/100), healthHeight);
		gc.strokeRect(x, y, healthWidth, healthHeight);
		
		gc.setFill(Color.BLACK);
		gc.setTextAlign(TextAlignment.CENTER); gc.setTextBaseline(VPos.CENTER);
		gc.setFont(Font.font("veranda", FontWeight.BOLD, FontPosture.REGULAR, 20));
		gc.fillText("Health: " + player.getShip().getHealth() + " / " + player.getShip().getMaxHealth(), x + healthWidth/2, y + healthHeight/2);
		
		gc.restore();
	}
	
	
}
