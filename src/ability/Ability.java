package ability;

import inventory.ItemCategory;
import inventory.Itemizable;

// This is the super-class for all abilities
public abstract class Ability implements Itemizable{

	Slot slot;
	
	public Ability() {
		
	}
	
	public ItemCategory getCategory() {
		return ItemCategory.ABILITY;
	}
	
}
