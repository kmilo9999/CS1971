package fxengine.system;

import java.util.ArrayList;
import java.util.List;

import fxengine.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;

public abstract class BaseGameSystem {
	
	
	// Filtered list of game objects of interest
	List<GameObject> myGameObjects = new ArrayList<GameObject>();
	
	
	public abstract void initialize();

	public abstract void update(long nanosSincePreviousTick);
	
	public abstract void draw(GraphicsContext graphicsCx);
	
	public abstract void destroy();
	
	public void addGameObject(GameObject gameObject)
	{
		myGameObjects.add(gameObject);
	}
	
}
