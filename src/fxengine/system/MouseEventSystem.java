package fxengine.system;

import fxengine.components.ComponetContants;
import fxengine.components.MouseControllerBehaviorComponent;
import fxengine.event.Event;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class MouseEventSystem extends EventSystem{
	
	private int mapMouseButtonToInt(MouseButton mb)
	{		
		if( mb == MouseButton.PRIMARY)
		{
			return 0;
		}
		else if(mb== MouseButton.SECONDARY)
		{
			return 1;
		}
		else if(mb == MouseButton.MIDDLE)
		{
			return 1;
		}
		
		return -1;
	}
	
	protected void onMouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for(GameObject gameObject:myGameObjects)
		{
			if(gameObject.hasComponent(ComponetContants.mouseEvents)) {
				// check if the click was within the sprite area
				MouseControllerBehaviorComponent mouseEventComponent = (MouseControllerBehaviorComponent)gameObject.getComponent(ComponetContants.mouseEvents);
				mouseEventComponent.mouseClicked( new Vec2d(e.getX(),e.getY()), mapMouseButtonToInt(e.getButton()));
			}
		}
	}

	
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
				MouseControllerBehaviorComponent mouseEventComponent = (MouseControllerBehaviorComponent)gameObject.getComponent(ComponetContants.mouseEvents);
				mouseEventComponent.processEvent(evt);
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
