package application;

import java.util.LinkedList;

import javafx.scene.canvas.Canvas;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import menu.Align;

public class GameObjects {
	
	private static GameObjects gameObjects = null;
	
	private Canvas gameCanvas = null; // Canvas the game is being played on
	private LinkedList<Sprite> sprites = null;
	private LinkedList<Player> players = new LinkedList<Player>();
	
	private GameObjects() {
		
	}
	
	public static GameObjects get() {
		if (gameObjects == null) {
			gameObjects = new GameObjects();
		}
		
		return gameObjects;
	}
	
	public Canvas gameCanvas() {
		if (gameCanvas == null)
			throw new RuntimeException("Tried to retrieve gameCanvas before setting");
		return gameCanvas;
	}
	
	public void setGameCanvas(Canvas gameCanvas) {
		this.gameCanvas = gameCanvas;
	}
	
	public void addPlayer(Player p) {
		players.add(p);
	}
	
	public LinkedList<Player> players() {
		return players;
	}
	
	public LinkedList<Sprite> sprites() {
		if (sprites == null) 
			throw new RuntimeException("Tried to retrieve spriteList before setting");
		return sprites;
	}
	
	public void setSprites(LinkedList<Sprite> sprites) {
		this.sprites = sprites;
	}
	

	// FONT GETTERS
	public Font menuTitleFont(int size) {
		return Font.font("veranda", FontWeight.BOLD, FontPosture.REGULAR, size);
	}
	

}
