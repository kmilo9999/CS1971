package fxengine.components;

import fxengine.event.Event;
import fxengine.event.EventsConstants;
import fxengine.math.Vec2d;
import fxengine.system.KeyboardEventSystem;
import javafx.scene.canvas.GraphicsContext;


public class KeyControllerBehaviorComponent extends KeyEventComponent{

	
	
	public KeyControllerBehaviorComponent(String name) {
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
			
			if((int)value.charAt(0) - 97 == 22)
			{
				TransformComponent transform = (TransformComponent) this.myParent.getComponent(ComponetContants.transform);
				transform.setPosition(new Vec2d(transform.getPosition().x,transform.getPosition().y - 1));
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
