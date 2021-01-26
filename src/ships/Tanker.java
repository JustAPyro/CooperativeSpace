package ships;

import inventory.Itemizable;

public class Tanker extends Ship {

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
