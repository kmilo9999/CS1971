package fxengine.system;

import fxengine.components.CameraKeyControllerBehavior;
import fxengine.components.ComponentContants;
import fxengine.components.AWSDKeyControllerBehaviorComponent;
import fxengine.components.AnimationControllerComponent;
import fxengine.components.KeyEventComponent;
import fxengine.event.Event;
import fxengine.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;

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
			if(gameObject.hasComponent(ComponentContants.keyEvents)) {
				KeyEventComponent keyEventComponent = (KeyEventComponent)gameObject.getComponent(ComponentContants.keyEvents);
				keyEventComponent.processEvent(evt);
			}
			if(gameObject.hasComponent(ComponentContants.controllerKeyEvents)) {
				AWSDKeyControllerBehaviorComponent keyControllerBehaviorComponent = (AWSDKeyControllerBehaviorComponent)gameObject.getComponent(ComponentContants.controllerKeyEvents);
				keyControllerBehaviorComponent.processEvent(evt);
			}
			
			if(gameObject.hasComponent(ComponentContants.cameraControllerKeyEvents)) {
				CameraKeyControllerBehavior cameraKeyControllerBehavior = (CameraKeyControllerBehavior)gameObject.getComponent(ComponentContants.cameraControllerKeyEvents);
				cameraKeyControllerBehavior.processEvent(evt);
			}
			
			if(gameObject.hasComponent(ComponentContants.keyAnimationController)) {
				AnimationControllerComponent animationKeyControllerBehavior = (AnimationControllerComponent)gameObject.getComponent(ComponentContants.keyAnimationController);
				animationKeyControllerBehavior.processEvent(evt);
			}
		}
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		myComponentsOfInterest.add(ComponentContants.keyEvents);
		myComponentsOfInterest.add(ComponentContants.controllerKeyEvents);
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		for(GameObject gameObject:myGameObjects)
		{
			if(gameObject.hasComponent(ComponentContants.controllerKeyEvents)) {
				AWSDKeyControllerBehaviorComponent keyControllerBehaviorComponent = (AWSDKeyControllerBehaviorComponent)gameObject.getComponent(ComponentContants.controllerKeyEvents);
				keyControllerBehaviorComponent.update(nanosSincePreviousTick);
			}
			
			if(gameObject.hasComponent(ComponentContants.keyAnimationController)) {
				AnimationControllerComponent keyControllerBehaviorComponent = (AnimationControllerComponent)gameObject.getComponent(ComponentContants.keyAnimationController);
				keyControllerBehaviorComponent.update(nanosSincePreviousTick);
			}
		}
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
