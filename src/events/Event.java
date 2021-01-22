package events;

import gui.GUI;

public abstract class Event {

	private double length;
	private double progress;
	
	private long start;
	private long end;
	
	public Event(double length) {
		this.length = length;
		progress = 0;


	}
	
	public Boolean update(double timeSince) {
		progress += timeSince;
		
		eventManage(timeSince);
		
		if (progress >= length) { // If more time has gone on then the length of 
			end();
			return false;
		}
		else {
			return true;
		}
	}
	
	
	public abstract void eventManage(double timeSince);
	
	
	// Private function gets called before the event gets set null
	private void end() {
		eventEnd();
	}
	
	public abstract void eventEnd();
	
	
}
