package fxengine.components;

import fxengine.event.Event;
import fxengine.event.EventsConstants;
import fxengine.math.Vec2d;
import fxengine.system.KeyboardEventSystem;
import javafx.scene.canvas.GraphicsContext;


public class KeyEventComponent extends Component{

	protected boolean[] keys = new boolean[256];
	protected boolean[] specialKeys = new boolean[3]; 
	
	public KeyEventComponent(String name) {
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
	public void onKeyTyped(String value) {
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
	
	public void onKeyReleased(String value) {
		// TODO Auto-generated method stub
		if(value.length() == 1)
		{
			int keyCode = (int)value.charAt(0) - 97;
			if(keyCode >= 0 && keyCode < 256)
            {
            	keys[(int)value.charAt(0) - 97] = false;	
            }

		}
		else if(value.equals(KeyboardEventSystem.SHIFT_KEY))
		{
			specialKeys[0] = false;
		}else if(value.equals(KeyboardEventSystem.CONTROL_KEY))
		{
			specialKeys[1] = false;
		}else if(value.equals(KeyboardEventSystem.ALT_KEY))
		{
			specialKeys[2] = false;
		}
		
		//System.out.println("onKeyReleased " + value);
	}
	
	public void onKeyPressed(String value) {
		// TODO Auto-generated method stub
		if(value.length() == 1)
		{
			int keyCode = (int)value.charAt(0) - 97;
			if(keyCode >= 0 && keyCode < 256)
            {
				keys[keyCode] = true;	
			}
			
		}
		else if(value.equals(KeyboardEventSystem.SHIFT_KEY))
		{
			specialKeys[0] = true;
		}else if(value.equals(KeyboardEventSystem.CONTROL_KEY))
		{
			specialKeys[1] = true;
		}else if(value.equals(KeyboardEventSystem.ALT_KEY))
		{
			specialKeys[2] = true;
		}
	    //System.out.println("onKeyPressed " + value );
	}
	
	public void processEvent(Event e)
	{
		if(e.type.equals(EventsConstants.KeyPressed))
		{
			onKeyPressed(e.strValue);
		}
		else if(e.type.equals(EventsConstants.KeyReleased))
		{
			onKeyReleased(e.strValue);
		}
		else if(e.type.equals(EventsConstants.KeyTyped))
		{
			onKeyTyped(e.strValue);
		}
	
	}


	@Override
	public Component clone() {
		Component clone = ComponentFactory.getInstance().createComponent(this.myName);
		clone.myParent =this.myParent;
		return clone;
	}
}
