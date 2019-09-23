package fxengine.components;

import fxengine.event.Event;
import fxengine.event.EventsConstants;

public class CameraKeyControllerBehavior extends KeyEventComponent{

	public CameraKeyControllerBehavior(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void processEvent(Event e) {
		// TODO Auto-generated method stub
		super.processEvent(e);
		
		if (e.type.equals(EventsConstants.KeyPressed)) {
			keyPressed(e.strValue);
		} else if (e.type.equals(EventsConstants.KeyReleased)) {
			keyReleased(e.strValue);
		} else if (e.type.equals(EventsConstants.KeyTyped)) {
			keyTyped(e.strValue);
		}
		
	}

	private void keyTyped(String strValue) {
		// TODO Auto-generated method stub
		
	}

	private void keyReleased(String strValue) {
		// TODO Auto-generated method stub
		
	}

	private void keyPressed(String strValue) {
		// TODO Auto-generated method stub
		
	}

}
