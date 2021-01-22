package events;

import java.util.LinkedList;
import java.util.Random;

import application.GameObjects;
import application.Planet;
import application.SpaceRock;
import application.Sprite;
import gui.GUI;
import javafx.scene.canvas.Canvas;

public class AstroidBombardment extends Event{
	
	double bombardmentDifficulty = 10; //difficulty of the bombardment
	double bombardmentSpeed = 0.2;
	int bombardmentSize = 128;
	private Planet planetTarget;
	Random rand;
	Canvas gameCanvas;
	LinkedList<Sprite> allSprites;
	
	public AstroidBombardment(Planet planet, double length) {
		super(length);
		rand = new Random();
		planetTarget = planet;
		gameCanvas = GameObjects.get().gameCanvas();
		allSprites = GameObjects.get().sprites();
		
		GUI.getInstance().alert("Warning! Astroids Incoming!", 300);
	}

	
	
	@Override
	public void eventManage(double timeSince) {
		int chance = rand.nextInt(2000);
		if (chance < bombardmentDifficulty)
			allSprites.add(new SpaceRock(gameCanvas, planetTarget, bombardmentSpeed, bombardmentSize));
		
	}



	@Override
	public void eventEnd() {
		GUI.getInstance().alert("Astroid Bombardment Defended!", 300);
		
	}
	
}
