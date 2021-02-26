package ships;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Player;
import inventory.ItemCategory;
import inventory.Itemizable;

// This is a super-class for all ship types
public abstract class Ship implements Itemizable{

	// Name of the type of ship
	private String shipName;
	
	// Forward and turning power
	private double forwardPower;
	private double turningPower;
	
	// Default health and max health
	private double health = 100;
	private double maxHealth = 100;
	
	// Image for drawing ship
	private Image shipImage;
	
	// Reference to parent (Player) [Sometimes we need this, for example if the ship is Focusable]
	private Player player;
	
	// Getters & Setters :
	
	// ========== Ship Name ==========
	
	/**
	 * Sets the name of the ship type.
	 * 
	 * @param shipName Name of the ship type.
	 */
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	
	/**
	 * Get the name of the ship type
	 * 
	 * @return The name of the ship type.
	 */
	public String getShipName() {
		return shipName;
	}
	
	// ========== Forward Power ==========

	/**
	 * Sets the forward power of the ship. This determines the accelerating power.
	 * 
	 * @param forwardPower Forward power of the ship
	 */
	public void setForwardPower(double forwardPower) {
		this.forwardPower = forwardPower;
	}
	
	/**
	 * Returns the forward power of the ship. This is used to determine accelerating power.
	 * 
	 * @return The forward power of the ship.
	 */
	public double getForwardPower() {
		return forwardPower;
	}
	
	// ========== Turning Power ==========
	
	/**
	 * Sets the turning power of the ship, which determines how quickly the ship can turn and navigate.
	 * 
	 * @param turningPower The amount of turning power
	 */
	public void setTurningPower(double turningPower) {
		this.turningPower = turningPower;
	}
	
	/**
	 * Returns the turning power of the ship - This is used to calculate how quickily the ship turns and adjusts course.
	 * 
	 * @return The turning power of the ship.
	 */
	public double getTurningPower() {
		return turningPower;
	}
	
	// ========== Health ==========
	
	/**
	 * Utility function to change the health of the ship without requiring a call to getHealth(). Can be used to heal or damage this ship (Using a negative value)
	 * 
	 * @param healthChange The amount of health to either restore (Or if negative, remove)
	 */
	public void changeHealth(double healthChange) {
		health = health + healthChange;
	}
	
	/**
	 * This sets the health of the ship to a specific value.
	 * 
	 * @param health The desired health of the ship.
	 */
	public void setHealth(double health) {
		this.health = health;
	}
	
	/**
	 * Returns the current health of the ship.
	 * 
	 * @return Health of the ship
	 */
	public double getHealth() {
		return health;
	}
	
	/**
	 * Sets the health based on a percentage of the ships max health.
	 * 
	 * @param percent The percent of max health you would like current health set to.
	 */
	public void setHealthPercent(double percent) {
		health = (percent/100)*maxHealth;
	}
	
	/**
	 * Gets the health, returned as a percentage of the ships max health.
	 * 
	 * @return The health of the ship represented as a percent of max health.
	 */
	public double getHealthPercent() {
		return (health/maxHealth)*100;
	}
	
	// ========== Max Health ==========
	
	/**
	 * Sets the max health of a ship.
	 * 
	 * @param maxHealth Desired max health.
	 */
	public void setMaxHealth(double maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	/**
	 * Returns the maximum health of the ship.
	 * 
	 * @return The maximum health of the ship as a double.
	 */
	public double getMaxHealth() {
		return maxHealth;
	}
	
	// ========== Ship Image ==========
	
	/**
	 * Sets the ships image based on a file location represented as a String.
	 * 
	 * @param fileLocation The file location as a String
	 */
	public void setShipImage(String fileLocation) {
		try {
			setShipImage(new FileInputStream(fileLocation));
		} catch (FileNotFoundException e) {
			//TODO: 9 - Error Management, load backup image here;
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Sets the ships image based on a file input stream.
	 * 
	 * @param inputStream The input string containing desired ship image.
	 */
	public void setShipImage(FileInputStream inputStream) {
		setShipImage(new Image(inputStream));
	}
	
	/**
	 * Sets the ships image using an Image object.
	 * 
	 * @param shipImage Image object to be set to ship.
	 */
	public void setShipImage(Image shipImage) {
		this.shipImage = shipImage;
	}
	
	/**
	 * Returns the ships image.
	 * 
	 * @return The ship image as an Image object./
	 */
	public Image getShipImage() {
		return shipImage;
	}
	
	// ========== Player ==========
	
	// Gets the parent object
	public Player getPlayer() {
		return player;
	}
	
	// Sets the parent player object
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	// All itemizable items must implement these two methods
	
	// Draws the icon for this as an item on menu and inventory
	public void drawItemIcon(GraphicsContext gc, int x, int y, int width, int height) {
		gc.save();
		gc.setFill(Color.LIGHTYELLOW);
		gc.setStroke(Color.BLACK);
		gc.fillRoundRect(x - width/2, y - height/2, width, height, Itemizable.rounding, Itemizable.rounding);
		gc.strokeRoundRect(x - width/2, y - height/2, width, height, Itemizable.rounding, Itemizable.rounding);
		gc.drawImage(shipImage, x-25, y-25, 50, 50);
		
		gc.restore();
	}
	
	// Returns a string that's a description of the ship!
	public abstract String getDescription();
	
	// Returns the type of item this is
	public ItemCategory getCategory() {
		return ItemCategory.SHIP; // Return that this is a ship item
	}
}
