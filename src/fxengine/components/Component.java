package fxengine.components;

import fxengine.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;

public abstract class Component {

	
	protected GameObject myParent;
	
	protected String myName;
	
	protected boolean isEnabled = true;
	
	public Component(String name)
	{
		this.myName = name;
	}
	
	public abstract Component clone();
	
	
	public abstract void initialize();

	public abstract void update(long nanosSincePreviousTick);
	
	public abstract void destroy();
	
	public abstract void draw(GraphicsContext graphicsCx);
	
	protected boolean isInitialized = false;

	public GameObject getParent() {
		return myParent;
	}

	public void setParent(GameObject parent) {
		this.myParent = parent;
	}

	public String getName() {
		return myName;
	}

	public void setName(String name) {
		this.myName = name;
	}

	public boolean isInitialized() {
		return isInitialized;
	}

	public void setInitialized(boolean isInitialized) {
		this.isInitialized = isInitialized;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
}
