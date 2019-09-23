package fxengine.system;

import fxengine.components.CameraKeyControllerBehavior;
import fxengine.components.CameraMouseControllerBehavior;
import fxengine.components.ComponetContants;
import fxengine.components.MouseControllerBehaviorComponent;
import fxengine.components.MouseEventComponent;
import fxengine.event.Event;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class MouseEventSystem extends EventSystem{
	

	
	protected void onMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	protected void onMouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	protected void onMouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	protected void onMouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	protected void onMouseWheelMoved(ScrollEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	protected void onFocusChanged(boolean newVal) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onNotify(Event evt) {
		// TODO Auto-generated method stub
		for(GameObject gameObject:myGameObjects)
		{
			if(gameObject.hasComponent(ComponetContants.mouseEvents)) {
				MouseEventComponent mouseEventComponent = (MouseEventComponent)gameObject.getComponent(ComponetContants.mouseEvents);
				mouseEventComponent.processEvent(evt);
			}
			
			if(gameObject.hasComponent(ComponetContants.controllerMouseEvents)) {
				MouseControllerBehaviorComponent mouseControllerBehaviorComponent = (MouseControllerBehaviorComponent)gameObject.getComponent(ComponetContants.controllerMouseEvents);
				mouseControllerBehaviorComponent.processEvent(evt);
			}
			
			if(gameObject.hasComponent(ComponetContants.cameraControllerMouseEvents)) {
				CameraMouseControllerBehavior cameraMouseControllerBehavior = (CameraMouseControllerBehavior)gameObject.getComponent(ComponetContants.cameraControllerMouseEvents);
				cameraMouseControllerBehavior.processEvent(evt);
			}
		}
	}


	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		myComponentsOfInterest.add(ComponetContants.mouseEvents);
		myComponentsOfInterest.add(ComponetContants.controllerMouseEvents);
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
