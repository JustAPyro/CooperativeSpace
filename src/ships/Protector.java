package ships;

import application.Focusable;
import application.Player;
import inventory.Itemizable;

/**
 * This class handles all logic and information related to the Protector Class ships.
 * 
 * @author Luke Hanna (Github.com/JustAPyro)
 * @version 1.0
 */
public class Protector extends Ship implements Focusable{

	/**
	 * Empty constructor allows you to create a generic instance of the Protector ship with base values for forward and turning power.
	 */
	public Protector() {
		
		setShipName("Protector");
		setShipImage("./Resources/Images/Ships/ship_fixed.png");
		setForwardPower(0.025);
		setTurningPower(1);
			
	}
	
	/**
	 * This constructor allows you to create a generic instance of the protector class ship and immediately attach the ship to a player-controller.
	 * 
	 * @param player The player you wish to attach this instance of ship to.
	 */
	public Protector(Player player) {
		setPlayer(player);
		setShipName("Protector");
		setShipImage("./Resources/Images/Ships/ship_fixed.png");
		setForwardPower(0.025);
		setTurningPower(1);
	}
	
	/**
	 * Returns the X position of the Protector ship based on player data.
	 */
	@Override
	public double getX() {
		return getPlayer().getX();
	}

	/**
	 * Returns the Y Position of the Protector ship based on player data.
	 */
	@Override
	public double getY() {
		return getPlayer().getY();
	}

	/**
	 * Returns true if the passed item should stack with the Protector ship in interfactes (Namely inventory and shop menus)
	 * 
	 * @param item The item you want to know if the Protector stacks with
	 * 
	 * @return True if the item should stack with the protector, false if they are not compatible.
	 */
	@Override
	public boolean stacksWith(Itemizable item) {
		if (item.getClass().equals(this.getClass()))
			return true;
		else
			return false;
	}

	/**
	 * Returns a valid description of the Protector class ships, this is an overridden method based on the Ship Class and is used mainly for tooltips.
	 */
	@Override
	public String getDescription() {
		return "A protector ship: Capable of claiming screen focus for long distance travel and armed with support weaponry this is a great addition to any fleet despite it's lower speed and manueverability.";
	}
	
	
	
}
