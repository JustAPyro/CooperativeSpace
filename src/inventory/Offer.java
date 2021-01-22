package inventory;

// An offer is used for buying/trading/selling purposes, it can be stored in store menus and then "executed" to actually make the trade
// Not all items trade on a 1 to 1 value and some items can't be split so Offers have an amount of input quantity and an amount of output quantity, as well as the items being traded
public class Offer {

	private Itemizable input;	// Item input
	private int inputQuantity;	// Amount of input
	private int lcdInput;
	
	private Itemizable output;	// Item output
	private int outputQuantity;	// Amount of output
	private int lcdOutput;
	
	private boolean adjustable = true; // If this is true the player can adjust how much they want to trade
	
	// Constructor for standard offer, with arguments passed (quantityX [of] itemX [for] quantityY [of] itemY) 
	public Offer(int inputQuantity, Itemizable input, int outputQuantity, Itemizable output) {
		this.inputQuantity = inputQuantity;		// Item input
		this.input = input;						// Item input quantity
		this.outputQuantity = outputQuantity;	// Item output
		this.output = output;					// Item output Quantity
		
		// TODO: Add a function here that finds lowest common denominator of input/output ration and assigns it to lcdOutput and lcdInput
		// increment quantity should be adjusted to increment by those values (For now we just assume the constructor is giving LCD)
		
		lcdInput = inputQuantity;
		lcdOutput = outputQuantity;
	}
	
	// Get method for input quantity
	public int getInputQuantity() {
		return inputQuantity;
	}
	
	// Get method of output quantity
	public int getOutputQuantity() {
		return outputQuantity;
	}
	
	private int lcm(int n1, int n2) {
		return (n1 / gcd(n1, n2)) * n2;
	}
	
	private int gcd(int n1, int n2) {
		if (n2 == 0) {
			return n1;
		}
		return  gcd(n2, n1 % n2);
	}
	
	// Execute the offer based on current input and output quantity
	public void execute() {

		Boolean hasResources = InventoryAdv.removeItem(input, inputQuantity); // If the player has the input remove it
		System.out.println(hasResources);
		if (hasResources) { // If they had the resources then
			InventoryAdv.addItem(output, outputQuantity); // and add the output
		}
		else {
			int have = InventoryAdv.getQuantity(input); // Get the quantity we DO have
			int gcd = gcd(inputQuantity, outputQuantity); // Get the GCD to calculate conversion rate
			
			// Conversion rate is a fraction, dividing both by gcd allows us to simplify and get min in/out
			int minIn = inputQuantity/gcd; 
			int minOut = outputQuantity/gcd;
			
			int actualTransaction = have/minIn; // Calculate how many transactions we can make based on how much the player actually has and minIn
			
			InventoryAdv.removeItem(input, actualTransaction * minIn); // Subtract the max amount we can
			InventoryAdv.addItem(output, actualTransaction * minOut); // Add the max amount we can
		}
		
	}
	
	// Execute the offer (Do the trade) for a specific double quantity
	public void execute(double quantity) {
		
		// TODO: Fix this execute function to actually do the trade based on quantity
		
	}
	
	// Method to increment the quantity of the offer (true is positive, false is negative)
	public void incrementQuantity(boolean increase) {
		if (adjustable == false) // If this offer is not adjustable
			return; // Return false
		
		if (increase == true) {
			inputQuantity = inputQuantity + lcdInput;
			outputQuantity = outputQuantity + lcdOutput;
		} 
		else {
			inputQuantity = inputQuantity - lcdInput;
			 outputQuantity = outputQuantity - lcdOutput;
		}
	}
	
	public String toString() {
		return inputQuantity + " of " + input.toString() + " for " + outputQuantity + " of " + output.toString();
	}
	
	// Get method that returns the input item for offer
	public Itemizable getInput() {
		return input; // Return the input item
	}
	
	// Get method that returns the output item for offer
	public Itemizable getOutput() {
		return output; // Return the output item
	}
}
