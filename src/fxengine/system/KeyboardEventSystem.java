package fxengine.system;

import fxengine.components.CameraKeyControllerBehavior;
import fxengine.components.ComponetContants;
import fxengine.components.KeyControllerBehaviorComponent;
import fxengine.components.KeyEventComponent;
import fxengine.components.MouseControllerBehaviorComponent;
import fxengine.event.Event;
import fxengine.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class KeyboardEventSystem extends EventSystem{

	public static final String SHIFT_KEY = "Shift";
	public static final String CONTROL_KEY = "CONTROL";
	public static final String ALT_KEY = "ALT";
	
	public static final int SHIFT_KEYCODE = 0;
	public static final int CONTROL_KEYCODE = 1;
	public static final int ALT_KEYCODE = 2;
	
	@Override
	public void onNotify(Event evt) {
		// TODO Auto-generated method stub
		for(GameObject gameObject:myGameObjects)
		{
			if(gameObject.hasComponent(ComponetContants.keyEvents)) {
				KeyEventComponent keyEventComponent = (KeyEventComponent)gameObject.getComponent(ComponetContants.keyEvents);
				keyEventComponent.processEvent(evt);
			}
			if(gameObject.hasComponent(ComponetContants.controllerKeyEvents)) {
				KeyControllerBehaviorComponent keyControllerBehaviorComponent = (KeyControllerBehaviorComponent)gameObject.getComponent(ComponetContants.controllerKeyEvents);
				keyControllerBehaviorComponent.processEvent(evt);
			}
			
			if(gameObject.hasComponent(ComponetContants.cameraControllerKeyEvents)) {
				CameraKeyControllerBehavior cameraKeyControllerBehavior = (CameraKeyControllerBehavior)gameObject.getComponent(ComponetContants.cameraControllerKeyEvents);
				cameraKeyControllerBehavior.processEvent(evt);
			}
		}
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		myComponentsOfInterest.add(ComponetContants.keyEvents);
		myComponentsOfInterest.add(ComponetContants.controllerKeyEvents);
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
