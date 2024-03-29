package fxengine.system;

import java.util.ArrayList;

import java.util.List;


import fxengine.objects.GameObject;
import fxengine.objects.GameWorld;
import javafx.scene.canvas.GraphicsContext;

public abstract class BaseGameSystem {
	
	
	// Filtered list of game objects of interest
	
	protected List<GameObject> myGameObjects = new ArrayList<GameObject>();
	
	protected List<String> myComponentsOfInterest = new ArrayList<String>();
	
	public abstract void initialize();

	public abstract void update(long nanosSincePreviousTick);
	
	public abstract void draw(GraphicsContext graphicsCx);
	
	public abstract void destroy();
	
	public void addGameObject(GameObject gameObject)
	{
		myGameObjects.add(gameObject);
	}
	
	public void removeGameObject(String gameObjectId)
	{
		for(GameObject gameObject:myGameObjects)
		{
			if(gameObject.getId().equals(gameObjectId))
			{
				myGameObjects.remove(gameObject);	
				break;	
			}
				
			
		}
		
	}

}
