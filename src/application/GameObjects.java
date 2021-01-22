package application;

import java.util.LinkedList;

import javafx.scene.canvas.Canvas;

public class GameObjects {
	
	private static GameObjects gameObjects = null;
	
	private Canvas gameCanvas = null; // Canvas the game is being played on
	private LinkedList<Sprite> sprites = null;
	
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
	
	public LinkedList<Sprite> sprites() {
		if (sprites == null) 
			throw new RuntimeException("Tried to retrieve spriteList before setting");
		return sprites;
	}
	
	public void setSprites(LinkedList<Sprite> sprites) {
		this.sprites = sprites;
	}
	

}
