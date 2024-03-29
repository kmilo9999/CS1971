package fxengine.components;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import fxengine.event.Event;
import fxengine.event.EventsConstants;
import fxengine.objects.GameObject;
import fxengine.system.KeyboardEventSystem;
import javafx.scene.canvas.GraphicsContext;

public class KeyEventComponent extends ObservableComponent{

	protected boolean[] keys = new boolean[256];
	protected boolean[] specialKeys = new boolean[4]; 
	
	
	public KeyEventComponent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processEvent(Event e) {
		// TODO Auto-generated method stub
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

	private final void onKeyTyped(String strValue) {
		// TODO Auto-generated method stub
		
	}

	private final void onKeyReleased(String value) {
		// TODO Auto-generated method stub
		if (value.length() == 1) {
			int keyCode = (int) value.charAt(0) - 97;
			if (keyCode >= 0 && keyCode < 256) {
				keys[(int) value.charAt(0) - 97] = false;
			}

		} else if (value.equals(KeyboardEventSystem.SHIFT_KEY)) {
			specialKeys[0] = false;
		} else if (value.equals(KeyboardEventSystem.CONTROL_KEY)) {
			specialKeys[1] = false;
		} else if (value.equals(KeyboardEventSystem.ALT_KEY)) {
			specialKeys[2] = false;
		}
		else if (value.equals(KeyboardEventSystem.SPACEBAR_KEY)) {
			specialKeys[3] = false;
		}
	}

	private final void onKeyPressed(String value) {
		// TODO Auto-generated method stub

		if (value.length() == 1) {
			int keyCode = (int) value.charAt(0) - 97;
			if (keyCode >= 0 && keyCode < 256) {
				keys[keyCode] = true;
			}

		} else if (value.equals(KeyboardEventSystem.SHIFT_KEY)) {
			specialKeys[0] = true;
		} else if (value.equals(KeyboardEventSystem.CONTROL_KEY)) {
			specialKeys[1] = true;
		} else if (value.equals(KeyboardEventSystem.ALT_KEY)) {
			specialKeys[2] = true;
		}
		else if (value.equals(KeyboardEventSystem.SPACEBAR_KEY)) {
			specialKeys[3] = true;
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

	public boolean getSpecialKeyState(int keycode)
	{
		return specialKeys[keycode];
	}

	@Override
	public Element saveState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadState(Node node) {
		// TODO Auto-generated method stub
		
	}

}
