package inventory;

// This is a simple enum for the type of an Itemizable item
public enum ItemCategory {
	ABILITY,	// Ability items let ships have special attacks or utilities
	SHIP,		// Ships are any objects that can be piloted that inherit from ship.java
	MATERIAL,	// Materials are resources like oxygen and carbon
	MONEY; 		// Returns for "items" that are just money
}
