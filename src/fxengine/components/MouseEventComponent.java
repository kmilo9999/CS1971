package fxengine.components;

import fxengine.event.Event;
import fxengine.event.EventsConstants;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;

public class MouseEventComponent extends ObservableComponent {

	public MouseEventComponent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public static final int RightClick = 0;
	public static final int LeftClick = 1;
	public static final int MiddleClick = 2;
	
	protected Vec2d myLastPosition;
	protected Vec2d myPosition;

	protected boolean[] mouseButtons = new boolean[3];
	
	@Override
	public void processEvent(Event e) {
		// TODO Auto-generated method stub
		if(e.type.equals(EventsConstants.MouseMoved))
		{
			this.onMouseMoved(e.vec2dValue);
		}
		else if(e.type.equals(EventsConstants.MouseButtonPressed))
		{
			this.onMousePressed(e.vec2dValue,e.intValue);
		}
		else if(e.type.equals(EventsConstants.MouseButtonReleased))
		{
			this.onMouseReleased(e.vec2dValue,e.intValue);
		}
		else if(e.type.equals(EventsConstants.MouseWheelMoved))
		{
			this.onMouseWheelMoved(e.vec2dValue);
		}
		else if(e.type.equals(EventsConstants.MouseDragged))
		{
			this.onMouseDragged(e.vec2dValue, e.intValue);
		}
	}


	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		myLastPosition = new Vec2d(0);
		myPosition = new Vec2d(0);
	}
	
	public void onMouseMoved(Vec2d position)
	{
		myLastPosition = myPosition;
		myPosition = position;	
		//System.out.println("MOUSE MOVE "+myPosition.x +" "+ myPosition.y);
	}
	

	protected final void onMouseDragged(Vec2d vec2dValue, int intValue) {
		// TODO Auto-generated method stub
		
	}

	protected final void onMouseWheelMoved(Vec2d vec2dValue) {
		// TODO Auto-generated method stub
		
	}

	protected final void onMouseReleased(Vec2d vec2dValue, int keyValue) {
		// TODO Auto-generated method stub
		mouseButtons[keyValue] = false;
	}

	protected final  void onMousePressed(Vec2d vec2dValue, int keyValue) {
		// TODO Auto-generated method stub
		if(!mouseButtons[keyValue])
		{
			mouseButtons[keyValue] = true;	
		}
		
	}

	protected final void onMouseClicked(Vec2d vec2dValue, int pressedButton) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub
		
	}


	

}
