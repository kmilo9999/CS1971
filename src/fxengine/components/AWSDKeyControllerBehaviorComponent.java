package fxengine.components;

import fxengine.event.Event;
import fxengine.event.EventsConstants;
import fxengine.math.Vec2d;
import fxengine.system.KeyboardEventSystem;
import javafx.scene.canvas.GraphicsContext;


public class AWSDKeyControllerBehaviorComponent extends KeyEventComponent{

	private Vec2d upVec = new Vec2d(0,-1);
	private Vec2d downVec = new Vec2d(0,1);
	private Vec2d rightVec = new Vec2d(1,0);
	private Vec2d leftVec = new Vec2d(-1,0);
	
	private double speed = 0.85;
	
	public AWSDKeyControllerBehaviorComponent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		if(keys[22])
		{
			
			TransformComponent transform = (TransformComponent) this.myParent
					.getComponent(ComponentContants.transform);
			if(transform != null)
			{
				transform.setPosition(transform.getPosition().plus(upVec.smult(speed)));	
			}
			

		}
		
		if(keys[0])
		{
		
			TransformComponent transform = (TransformComponent) this.myParent
					.getComponent(ComponentContants.transform);
			
			if(transform != null)
			{
				transform.setPosition(transform.getPosition().plus(leftVec.smult(speed)));	
			}
		}
		
		if(keys[18])
		{
		
			TransformComponent transform = (TransformComponent) this.myParent
					.getComponent(ComponentContants.transform);
			if(transform != null)
			{
				transform.setPosition(transform.getPosition().plus(downVec.smult(speed)));	
			}
		}
		
		if(keys[3])
		{
		
			TransformComponent transform = (TransformComponent) this.myParent
					.getComponent(ComponentContants.transform);
			if(transform != null)
			{
				transform.setPosition(transform.getPosition().plus(rightVec.smult(speed)));	
			}
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param value
	 */
	public void keyTyped(String value) {
		// TODO Auto-generated method stub
		//System.out.println("onKeyTyped " + value);
		if(value.length() == 1)
		{
			
			if((int)value.charAt(0) - 97 == 2 && this.myParent.isSelected())
			{
				
				this.myParent.clone();
			}
			
		}
	}
	
	public void keyReleased(String value) {
		// TODO Auto-generated method stub
	
		
		//System.out.println("onKeyReleased " + value);
	}
	
	public void keyPressed(String value) {
		// TODO Auto-generated method stub
		
	    //System.out.println("onKeyPressed " + value );
	}
	
	public void processEvent(Event e)
	{
		super.processEvent(e);
		
		if(e.type.equals(EventsConstants.KeyPressed))
		{
			keyReleased(e.strValue);
		}
		else if(e.type.equals(EventsConstants.KeyReleased))
		{
			keyPressed(e.strValue);
		}
		else if(e.type.equals(EventsConstants.KeyTyped))
		{
			keyTyped(e.strValue);
		}
	
	}


	@Override
	public Component clone() {
		Component clone = ComponentFactory.getInstance().createComponent(this.myName);
		clone.myParent =this.myParent;
		return clone;
	}
}
