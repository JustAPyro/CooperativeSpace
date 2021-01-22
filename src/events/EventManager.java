package events;

import application.Planet;

public class EventManager {

	private static EventManager manager = null; // This is the one reference to this object that will ever exist
	
	private Event currentEvent;
	private Boolean eventInProgress = false;
	
	private EventManager() {
		
	}
	
	public static EventManager get() {
		if (manager == null)
			manager = new EventManager();
		
		return manager;
	}
	
	public void startEvent(int time) {

	}
	
	public void startAstroidBombardment(Planet planet, int time) {
		double timeMillis = (double) time * 1000;
		currentEvent = new AstroidBombardment(planet, timeMillis);
	}

	public void update(double timeSince) {
		if (currentEvent != null) {
			eventInProgress = currentEvent.update(timeSince);
			if (eventInProgress != true) {
				currentEvent = null;
			}
				
		}
		
	}

	
}
