package ships;

import inventory.Itemizable;

/**
 * TODO: Flesh out the Tanker class ship further
 * 
 * @author Luke Hanna (Github.com/JustAPyro)
 * @version 1.0
 */
public class Tanker extends Ship {

	
	/**
	 * This returns True if the passed item is compatible to stack with this class object in shop menus and inventory.
	 * 
	 * @param item The item you want to know if this ship will stack with in shop & Inventory.
	 * 
	 * @return True if the items should stack, false otherwise.
	 */
	@Override
	public boolean stacksWith(Itemizable item) {
		if (item.getClass().equals(this.getClass()))
			return true;
		else
			return false;
	}

	/**
	 * TODO: Fill out description for the Ship class.
	 * 
	 * This returns a custom description for the Ship class vessel.
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
