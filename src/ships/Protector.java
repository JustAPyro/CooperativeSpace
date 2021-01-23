package ships;

import application.Focusable;
import application.Player;
import inventory.Itemizable;

public class Protector extends Ship implements Focusable{

	public Protector() {
		
		setShipName("Protector");
		setShipImage("./Resources/Images/Ships/ship_fixed.png");
		setForwardPower(0.025);
		setTurningPower(1);
			
	}
	
	public Protector(Player player) {
		setPlayer(player);
		setShipName("Protector");
		setShipImage("./Resources/Images/Ships/ship_fixed.png");
		setForwardPower(0.025);
		setTurningPower(1);
	}
	
	@Override
	public double getX() {
		return getPlayer().getX();
	}

	@Override
	public double getY() {
		return getPlayer().getY();
	}

	@Override
	public boolean stacksWith(Itemizable item) {
		if (item.getClass().equals(this.getClass()))
			return true;
		else
			return false;
	}

	@Override
	public String getDescription() {
		return "A protector ship: Capable of claiming screen focus for long distance travel and armed with support weaponry this is a great addition to any fleet despite it's lower speed and manueverability.";
	}
	
	
	
}
