package fxengine.system;

import fxengine.components.ComponetContants;
import fxengine.components.KeyEventComponent;
import fxengine.components.MouseEventComponent;
import fxengine.event.Event;
import fxengine.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class KeyboardEventSystem extends EventSystem{

	public static final String SHIFT_KEY = "Shift";
	public static final String CONTROL_KEY = "CONTROL";
	public static final String ALT_KEY = "ALT";
	
	@Override
	public void onNotify(Event evt) {
		// TODO Auto-generated method stub
		for(GameObject gameObject:myGameObjects)
		{
			if(gameObject.hasComponent(ComponetContants.keyEvents)) {
				KeyEventComponent keyEventComponent = (KeyEventComponent)gameObject.getComponent(ComponetContants.keyEvents);
				keyEventComponent.processEvent(evt);
			}
		}
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	


}
