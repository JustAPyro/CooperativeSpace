package ships;

import inventory.Itemizable;

public class Fighter extends Ship{
	
	public Fighter() {
		
		setShipName("Fighter");
		setShipImage("./Resources/Images/Ships/redShip.png");
		setForwardPower(0.1);
		setTurningPower(3);
			
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
		// TODO Auto-generated method stub
		return null;
	}
	
}


