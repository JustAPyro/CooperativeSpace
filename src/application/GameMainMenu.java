package application;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GameMainMenu {

	private Stage window; // Variable for the window
	private Scene scene; // Variable for scene in the window
	private GraphicsContext gc; // Variable for the graphics context to draw on this screen
	private Dimension screenSize;
	private Canvas canvas;
	
	private double menuCenter;
	
	private Image titleText;
	private Image titleBackground;
	
	private Font spaceFont;
	StackPane layout;
	int selection = 0;
	
	public GameMainMenu(Stage primaryStage) {
		window = primaryStage;
		window.setTitle("Cooperative Space Alpha"); // Set the title of the window
		window.setFullScreen(true); // Set the game to full-screen by default
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Canvas canvas = new Canvas(screenSize.getWidth(), screenSize.getHeight());
		gc = canvas.getGraphicsContext2D();
		menuCenter = canvas.getWidth()/2;
		
		try {
			
			titleText = new Image(new FileInputStream(ResourceLocations.titlePageText));
			titleBackground = new Image(new FileInputStream(ResourceLocations.titlePageBack));
			
			spaceFont = Font.loadFont("file:resources/Fonts/MadeInSpace.ttf", 45);
		}
		catch (FileNotFoundException e) {
			System.out.println(e);
		}

	// Animation timer is used to draw everything on the screen and animate changes / Background
	AnimationTimer frame = new AnimationTimer() { // Creating an animation timer
		@Override // Overriding the handle function to be able to draw stuff
		public void handle(long timeStamp) {
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			
			gc.drawImage(titleBackground, 0, 0);
			gc.drawImage(titleText, screenSize.getWidth()/2-titleText.getWidth()/2, screenSize.getHeight()/5);
			
			gc.setFont(spaceFont);
			gc.setFill(Color.AZURE);
			gc.setTextAlign(TextAlignment.CENTER);
			

			gc.fillText("Play (New world)", menuCenter, 500);
			gc.fillText("Play (Load save)", menuCenter, 600);
			gc.fillText("Settings", menuCenter, 700);
			gc.fillText("Exit", menuCenter, 800);
			
			gc.setFill(Color.RED);
			if (selection == 0) {
				gc.fillText("Play (New world)", menuCenter, 500);
			} 
			else if (selection == 1) {
				gc.fillText("Play (Load save)", menuCenter, 600);
			}
			else if (selection == 2) {
				gc.fillText("Settings", menuCenter, 700);
			}
			else if (selection == 3) {
				gc.fillText("Exit", menuCenter, 800);
			}
			
		}
	};
	frame.start();
	
	layout = new StackPane(); // Creating a new layout
	layout.getChildren().add(canvas); // Get children
	Scene scene = new Scene(layout); // Add the layout
	
	
	scene.setOnKeyPressed(event -> {
		if (event.getCode() == KeyCode.W) { 
			if (selection == 0) {
				selection = 3;
			}
			else {
				selection--;
			}
		} 
		else if (event.getCode() == KeyCode.S) {
			if (selection == 3) {
				selection = 0;
			}
			else {
				selection++;
			}
		}
		else if (event.getCode() == KeyCode.SPACE) {
			if (selection == 0) {
				playWorld();
			}
			else if (selection == 1) {
				loadWorld();
			}
			else if (selection == 2) {
				openSettings();
			}
			else if (selection == 3) {
				exit();
			}
		}
	});
	
	window.setScene(scene); // Add the scene to the window;
	window.show(); // Make sure the window is visible
		
		
	}
	
	private void playWorld() {
		try {
			Game game = new Game(layout);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadWorld() {
		
	}

	private void openSettings() {
		
	}
	
	private void exit() {
		System.exit(0);
	}
	
}
