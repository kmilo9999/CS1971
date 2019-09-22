package fxengine.components;

import fxengine.event.Event;

public abstract class ObservableComponent extends Component{

	public ObservableComponent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	
	public abstract void processEvent(Event e);

}
