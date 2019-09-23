package fxengine.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fxengine.UISystem.Layout;
import fxengine.UISystem.UIConstants;
import fxengine.components.ComponetContants;
import fxengine.math.Vec2d;
import fxengine.system.BaseGameSystem;
import fxengine.system.GraphicsSystem;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;

public class GameWorld {

	public static final int FrontLayer = 1;
	public static final int BackLayer = 0;
	
	private int numLayers =2; 
	
	private List<List<GameObject>> myGameObjects;
	
	private List<List<GameObject>> myDirtyObjects;
	//private Map<String,Integer> myCountGameobjects;
	private Map<String,BaseGameSystem> mySystems;

	private GameObject mySelected = null;
	private boolean myNeedsUpdate = false;

	private Affine myAffineTransform;
	
	private Layout myClipLayout; 

	private boolean initClip = false;
	
	public GameWorld()
	{
		myGameObjects = new ArrayList<List<GameObject>>();
		myDirtyObjects = new ArrayList<List<GameObject>>();
		for(int i = 0 ; i < numLayers ;i++)
		{
			myGameObjects.add(new ArrayList<GameObject>());
			myDirtyObjects.add(new ArrayList<GameObject>());
		}
		
		this.mySystems = new HashMap<String, BaseGameSystem>();
		myClipLayout = new Layout(0, 0, 400, 400, UIConstants.TRANSPARENT,2.5);
	}
	
	public void initialize() 
	{
		
	   
	}
	
	public void update(long nanosSincePreviousTick)
	{

		if(myNeedsUpdate)
		{
			//	add new objects
			for(List<GameObject> newObjects:myDirtyObjects)
			{
			  for(GameObject newObject:newObjects)
			   {
				 myGameObjects.add(newObjects);
		         for(Map.Entry<String,BaseGameSystem>  systemEntry : mySystems.entrySet())
				 {
		        	 systemEntry.getValue().addGameObject(newObject);		
				 }
			   }
				   
				 
			}
		 
		   //myDirtyObjects.clear();
			myDirtyObjects.get(0).clear();
			//myDirtyObjects.get(1).clear();
		   myNeedsUpdate = false;
		}
		
		for(Map.Entry<String,BaseGameSystem>  systemEntry: mySystems.entrySet())
		{
			systemEntry.getValue().update(nanosSincePreviousTick);
		}
	}
	
	public void draw(GraphicsContext graphicsCx)
	{
		//Store the clip and transform (g.save())
		
		if(mySystems.containsKey(ComponetContants.graphics))
		{
			
			GraphicsSystem graphicsSystem = (GraphicsSystem) mySystems.get(ComponetContants.graphics);
			myAffineTransform = graphicsCx.getTransform();
			graphicsCx.save();
			
			//Set the clip
			
			//graphicsCx.rect(panelScreenViewPortUpperLeft.x,panelScreenViewPortUpperLeft.y,panelScreenViewPortSize.x,panelScreenViewPortSize.y);
			//graphicsCx.setLineWidth(3.0);
			//graphicsCx.setStroke(UIConstants.BLACK);
			//graphicsCx.stroke();
			//graphicsCx.clip();	
			myClipLayout.onDraw(graphicsCx);
		
			
			//Set the transform
			//graphicsSystem.setPanelGameViewPort(panelGameViewPort);
			//graphicsSystem.setPanelScreenViewPort(panelScreenViewPort);
			
			
			Affine transform = graphicsCx.getTransform();
			transform.appendTranslation(-graphicsSystem.getPanelGameViewPort().x, -graphicsSystem.getPanelGameViewPort().y);
			transform.appendScale(graphicsSystem.getViewportScaleFactor(), graphicsSystem.getViewportScaleFactor());
			transform.appendTranslation(graphicsSystem.getPanelScreenViewPortUpperLeft().x, graphicsSystem.getPanelScreenViewPortUpperLeft().y);
			graphicsCx.setTransform(transform);
			
			//Draw the game-space in its own coordinates
			graphicsSystem.setLayerGameObjects(myGameObjects);
			graphicsSystem.draw(graphicsCx);
			
			//Restore the clip and transform
			graphicsCx.restore();
		}
		
	
		
		
		
	}
	
	public void destroy() 
	{
		
	}
	
	public void addGameObject(GameObject gameObject, int layer)
	{
		GameObject tempGO = lookupGameObject(gameObject.getId());  
		if(tempGO == null)
		{
			// a new game object
			gameObject.setGameWorld(this);
			gameObject.initialize();
			List<GameObject> gameObjects = myGameObjects.get(layer); 
			gameObject.setLayerOrder(layer);
			gameObjects.add(gameObject);	
			
			for(Map.Entry<String,BaseGameSystem> systemEntry : mySystems.entrySet())
			{
				systemEntry.getValue().addGameObject(gameObject);
			}
			
		}
		
	}
	
	public void registerIntoSystem(String gameObjectId, String componentName )
	{
		for(List<GameObject> gameObjets:myGameObjects)
		{
			for(GameObject gameObject: gameObjets)
			{
				// look for that specific game object
				if(gameObject.getId().equals(gameObjectId))
				{
					// add it to the system interested in the component
					BaseGameSystem system = mySystems.get(componentName);
					if(mySystems != null)
					{
						system.addGameObject(gameObject);
					}
				}
			}
		}
		
	}
	
	public GameObject lookupGameObject(String id) {
		for(List<GameObject> gameObjets:myGameObjects)
		{
			for(GameObject gameObject:gameObjets)
			{
				if(gameObject.getId().equals(id))
				{
					return gameObject;
				}
			}	
		}
		
		return null;
	}
	
	public int countGameObject(String id) {
		int count = 0;
		for(List<GameObject> gameObjets:myGameObjects)
		{
			for(GameObject gameObject:gameObjets)
			{
				if(gameObject.getId().equals(id))
				{
					count++;
				}
			}	
		}
		
		return count;
	}
	
	
	public BaseGameSystem getSystem(String systemName) {
		return mySystems.get(systemName);
	}

	public void addSystem(String name, BaseGameSystem system) {
		this.mySystems.put(name,system);
	}

	public Affine getAffineTransform() {
		return myAffineTransform;
	}

	public void setAffineTransform(Affine myAffineTransform) {
		this.myAffineTransform = myAffineTransform;
	}
	
	public Vec2d getPanelScreenViewPortUpperLeft() {
		if(mySystems.containsKey(ComponetContants.graphics))
		{
			GraphicsSystem graphicsSystem = (GraphicsSystem) mySystems.get(ComponetContants.graphics);
			return graphicsSystem.getPanelScreenViewPortUpperLeft();
		}
		return null;
	}

	public Vec2d getPanelScreenViewPortSize() {
		if(mySystems.containsKey(ComponetContants.graphics))
		{
			GraphicsSystem graphicsSystem = (GraphicsSystem) mySystems.get(ComponetContants.graphics);
			return graphicsSystem.getPanelScreenViewPortSize();	
		}
		return null;
	}


	public Vec2d getPanelGameViewPort() {
		if(mySystems.containsKey(ComponetContants.graphics))
		{
			GraphicsSystem graphicsSystem = (GraphicsSystem) mySystems.get(ComponetContants.graphics);
			return graphicsSystem.getPanelGameViewPort();	
		}
		return null ;
	}
	
	
	public void setPanelGameViewPort(Vec2d pos) {
		if(mySystems.containsKey(ComponetContants.graphics))
		{
			GraphicsSystem graphicsSystem = (GraphicsSystem) mySystems.get(ComponetContants.graphics);
			 graphicsSystem.setPanelGameViewPort(pos);	
		}
		
	}
	
	public double getViewportScaleFactor() {
		if(mySystems.containsKey(ComponetContants.graphics))
		{
			GraphicsSystem graphicsSystem = (GraphicsSystem) mySystems.get(ComponetContants.graphics);
			return graphicsSystem.getViewportScaleFactor();	
		}
		return -1;
	}


	public void addClonedGameObject(GameObject clone) {
		// TODO Auto-generated method stub
		int count = countGameObject(clone.getId());  
		clone.setId(clone.getId() + (count+1));
		// a new game object
		clone.setGameWorld(this);
		//myGameObjects.add(clone);
		List<GameObject> dirtyObjects = myDirtyObjects.get(clone.getLayerOrder());
		dirtyObjects.add(clone);
		
		myNeedsUpdate = true;
	}
	
	public boolean isNeedsUpdate() {
		return myNeedsUpdate;
	}

	public void setNeedsUpdate(boolean myNeedsUpdate) {
		this.myNeedsUpdate = myNeedsUpdate;
	}

	public GameObject getSelected() {
		return mySelected;
	}

	public void setSelected(GameObject selected) {
		this.mySelected = selected;
	}
	
	public Vec2d screenToGameTransform(Vec2d screenCoordinates)
	{
		if(mySystems.containsKey(ComponetContants.graphics) && myAffineTransform!=null)
		{
			GraphicsSystem graphicsSystem = (GraphicsSystem) mySystems.get(ComponetContants.graphics);
			Affine affineTransformation = myAffineTransform.clone();
			affineTransformation.appendTranslation(screenCoordinates.x-graphicsSystem.getPanelScreenViewPortUpperLeft().x , screenCoordinates.y -graphicsSystem.getPanelScreenViewPortUpperLeft().y);
			affineTransformation.appendScale(1/graphicsSystem.getViewportScaleFactor(), 1/graphicsSystem.getViewportScaleFactor());
			affineTransformation.appendTranslation(graphicsSystem.getPanelGameViewPort().x , graphicsSystem.getPanelGameViewPort().y);
			return new Vec2d(affineTransformation.getTx(),affineTransformation.getTy());
		
		}
		
		return null;
		
	}
	
	
	public Vec2d gameToScreenTransform(Vec2d gameCoordinates)
	{
		if(mySystems.containsKey(ComponetContants.graphics) && myAffineTransform!=null)
		{
			GraphicsSystem graphicsSystem = (GraphicsSystem) mySystems.get(ComponetContants.graphics);
			Affine affineTransformation = myAffineTransform.clone();
			
			affineTransformation.appendTranslation(gameCoordinates.x-graphicsSystem.getPanelGameViewPort().x , gameCoordinates.y -graphicsSystem.getPanelGameViewPort().y);
			affineTransformation.appendScale(graphicsSystem.getViewportScaleFactor(), graphicsSystem.getViewportScaleFactor());
			affineTransformation.appendTranslation(graphicsSystem.getPanelScreenViewPortUpperLeft().x , graphicsSystem.getPanelScreenViewPortUpperLeft().y);
			return new Vec2d(affineTransformation.getTx(),affineTransformation.getTy());
		}
		
		return null;
		
	}
}
