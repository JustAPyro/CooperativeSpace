package events;

public class EventManager {

	private static EventManager manager = null; // This is the one reference to this object that will ever exist
	
	private EventManager() {
		
	}
	
	public static EventManager get() {
		if (manager == null)
			manager = new EventManager();
		
		return manager;
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}

	
}
