package application; // Package with the rest of the application

import java.io.FileNotFoundException;	// Throws file not found
import javafx.application.Application;	// Application for JFX
import javafx.stage.Stage;				// Stage for JFX

public class driver extends Application{ // Driver extends the application

	public static void main(String[] args) { // Main entry point
		launch(); //Launch application
		
		
	}
	
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException // Application / stage start
	{
		new GameMainMenu(primaryStage);
		
		// Uncomment this to start a new game
		//Game game = new Game(primaryStage); // Launch new game
	}
	
}
