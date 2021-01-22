package events;

import gui.GUI;

// Superclass for all game events
public abstract class Event {

	private double length;		// Length of event
	private double progress;	// Duration since start of event
	
	// Constructor
	public Event(double length) { 
		this.length = length; // Set the length to the given parameter
		progress = 0; // Set progress to 0


	}
	
	// Update function gets called repeatedly
	public Boolean update(double timeSince) {
		progress += timeSince; // Add time since last frame to progress
		
		eventManage(timeSince); // call the event manage method (Subclasses are required to implement this)
		
		if (progress >= length) { // If our progress is the same or longer then the desired length of event
			end(); // Call the events end() function
			return false; // Return false so the eventManager knows the event is over
		}
		else { // Otherwise, if the event is still running
			return true; // Return true
		}
	}
	
	// Private function gets called before the event gets set null
	private void end() { // Handles all the clean up all events have before ending
		eventEnd();
	}
	
	// Required abstract method that manages the event each frame
	public abstract void eventManage(double timeSince);
	
	// Required abstract method that is called to end the event
	public abstract void eventEnd();
	
	
}
