package fxengine.system;

import fxengine.event.Event;

public abstract class EventSystem extends BaseGameSystem{
	
	public abstract void onNotify(Event evt);

}
