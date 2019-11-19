package fxengine.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import fxengine.UISystem.Layout;
import fxengine.UISystem.UIConstants;
import fxengine.UISystem.UIElement;
import fxengine.components.ComponentContants;
import fxengine.math.Vec2d;
import fxengine.system.BaseGameSystem;
import fxengine.system.GraphicsSystem;
import fxengine.system.PhysicsSystem;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;

public class GameWorld {

	public static final int CameraLayer = 99;
	public static final int DestructableLayer = 5;
	public static final int ParticlesLayer = 4;
	public static final int StaticObjectLayer = 3;
	public static final int EnemyLayer = 2;
	public static final int PlayerLayer = 1;
	public static final int BackLayer = 0;
	
	public int numLayers = 6; 
	
	private List<List<GameObject>> myGameObjects;
	
	private List<List<GameObject>> myDirtyObjects;
	
	private List<String> toRemoveList;
	
	//private Map<String,Integer> myCountGameobjects;
	private Map<String,BaseGameSystem> mySystems;

	private GameObject mySelected = null;
	private boolean mySceneUpdated = false;

	private Affine myAffineTransform;
	
	private UIElement myGameViewPort;
	private UIElement myWorldViewPort; 
	private UIElement myScrenViewPort;

	private static int debug_mode = 0;
	
	private int numGameObjects = 0;
	
	private Vec2d myScreenViewPortPos = new Vec2d(100,50);

	private Vec2d myScreenViewPortSize = new Vec2d(750,450);
	
	public double deltax =0;
	public double deltay =0;
	
	private GameObject mySceneCamera = null;
	
	//private boolean isClearingObjects = false;
	
	public GameWorld()
	{
		myGameObjects = new ArrayList<List<GameObject>>();
		myDirtyObjects = new ArrayList<List<GameObject>>();
		toRemoveList = new ArrayList<String>();
		
		for(int i = 0 ; i < numLayers ;i++)
		{
			myGameObjects.add(new ArrayList<GameObject>());
			myDirtyObjects.add(new ArrayList<GameObject>());
		}
		
		this.mySystems = new HashMap<String, BaseGameSystem>();
		myGameViewPort = new Layout(myScreenViewPortPos.x, myScreenViewPortPos.y, myScreenViewPortSize.x, myScreenViewPortSize.y, UIConstants.TRANSPARENT,2.5);
		
		myWorldViewPort = new Layout(myScreenViewPortPos.x,myScreenViewPortPos.y, myScreenViewPortSize.x,myScreenViewPortSize.y, UIConstants.TRANSPARENT,2.5,UIConstants.RED);
		myScrenViewPort = new Layout(myScreenViewPortPos.x,myScreenViewPortPos.y, myScreenViewPortSize.x,myScreenViewPortSize.y, UIConstants.TRANSPARENT,2.5,UIConstants.GREEN);
	}
	
	public void initialize() 
	{
		
	   
	}
	
	public void update(long nanosSincePreviousTick)
	{

		if(mySceneUpdated)
		{
			
			//remove objects
			for(String gameObjectId: toRemoveList)
			{
				
				int indexFound = -1;
				for(int i = 0 ; i< myGameObjects.size();i++)
				{
				   Queue<GameObject> q = new LinkedList<GameObject>();
				   List<GameObject> currentLayer = myGameObjects.get(i);
				   for(int j = 0; j < currentLayer.size(); j++) 
				   {
					  if(currentLayer.get(j).getId().equals(gameObjectId))
					  {
						   for(Map.Entry<String,BaseGameSystem>  systemEntry : mySystems.entrySet())
						   {
					        	 systemEntry.getValue().removeGameObject(gameObjectId);		
						   }
						   indexFound = i;
						  
					  }
					  else
					  {
						  q.add(currentLayer.get(j));
					  }
				   }
				   
				   if(indexFound >=0 )
				   {
					   numGameObjects--;
					   currentLayer.clear();
					   while (!q.isEmpty())
						   currentLayer.add(q.remove());
				   }
				}
				
			}
			
			
			
			//	add new objects
			
		    for(int i = 0; i< myDirtyObjects.size();i++)
			{
		      List<GameObject> newObjects = myDirtyObjects.get(i);
		      List<GameObject> gameObjectLayer= myGameObjects.get(i);
		      
			  for(GameObject newObject:newObjects)
			   {
				  
				 gameObjectLayer.add(newObject);
				 newObject.setLayerOrder(i);
		         for(Map.Entry<String,BaseGameSystem>  systemEntry : mySystems.entrySet())
				 {
		        	 systemEntry.getValue().addGameObject(newObject);		
				 }
		         
		         newObject.initialize();
			   }
				   
				 
			}
		 
		   for(List<GameObject> dirtyLayer:myDirtyObjects)
		   {
			   dirtyLayer.clear();
		   }
		    
		   //myDirtyObjects.get(0).clear();
		   toRemoveList.clear();
		   //myDirtyObjects.get(1).clear();
		   mySceneUpdated = false;
		   
		 
		   
		}
		
		
		//update systems
		for(Map.Entry<String,BaseGameSystem>  systemEntry: mySystems.entrySet())
		{
			if(systemEntry.getKey().equals(ComponentContants.physics))
			{
				((PhysicsSystem)systemEntry.getValue()).setLayerGameObjects(myGameObjects);
			}
			
			systemEntry.getValue().update(nanosSincePreviousTick);
		}	
		
		
		
	}
	
	public void draw(GraphicsContext graphicsCx)
	{
		//Store the clip and transform (g.save())
		
		if(mySystems.containsKey(ComponentContants.graphics))
		{
			
			GraphicsSystem graphicsSystem = (GraphicsSystem) mySystems.get(ComponentContants.graphics);
			myAffineTransform = graphicsCx.getTransform();
			graphicsCx.save();
			
			//Set the clip
			
			//graphicsCx.rect(panelScreenViewPortUpperLeft.x,panelScreenViewPortUpperLeft.y,panelScreenViewPortSize.x,panelScreenViewPortSize.y);
			//graphicsCx.setLineWidth(3.0);
			//graphicsCx.setStroke(UIConstants.BLACK);
			//graphicsCx.stroke();
			//graphicsCx.clip();	
			myGameViewPort.onDraw(graphicsCx);
		
			
			//Set the transform
			//graphicsSystem.setPanelGameViewPort(panelGameViewPort);
			//graphicsSystem.setPanelScreenViewPort(panelScreenViewPort);
			if(debug_mode == 1)
			{
				myWorldViewPort.setPosition(graphicsSystem.getPanelGameViewPortTopLeft());
				myScrenViewPort.setPosition(graphicsSystem.getPanelScreenViewPortUpperLeft());
				myWorldViewPort.onDraw(graphicsCx);
				myScrenViewPort.onDraw(graphicsCx);	
			}
			
			
			Vec2d viewPortCenterGameSpace = this.screenToGameTransform(graphicsSystem.getPanelScreenViewPortSize()).sdiv(2);
			
			
			Affine transform = graphicsCx.getTransform();
			
			transform.appendTranslation(-graphicsSystem.getPanelGameViewPortTopLeft().x, -graphicsSystem.getPanelGameViewPortTopLeft().y);
			transform.appendTranslation(viewPortCenterGameSpace.x, viewPortCenterGameSpace.y);
			transform.appendTranslation(-graphicsSystem.getPanelScreenViewPortSize().x/2, -graphicsSystem.getPanelScreenViewPortSize().y/2);
			transform.appendScale(graphicsSystem.getViewportScaleFactor(), graphicsSystem.getViewportScaleFactor());
			transform.appendTranslation(graphicsSystem.getPanelScreenViewPortSize().x/2, graphicsSystem.getPanelScreenViewPortSize().y/2);
			transform.appendTranslation(-viewPortCenterGameSpace.x, -viewPortCenterGameSpace.y);
			transform.appendTranslation(graphicsSystem.getPanelScreenViewPortUpperLeft().x, graphicsSystem.getPanelScreenViewPortUpperLeft().y);
			
			transform.appendTranslation(deltax, deltay);
			
			Vec2d scalesSize = graphicsSystem.getPanelScreenViewPortSize().smult(graphicsSystem.getViewportScaleFactor());
			Vec2d diff = scalesSize.minus(graphicsSystem.getPanelScreenViewPortSize());
			
			//double deltaZoomFactor = 
			
			//System.out.println(diff);
			
			//graphicsSystem.getPanelScreenViewPortSize() * graphicsSystem.getViewportScaleFactor() - graphicsSystem.getPanelScreenViewPortSize(); 
			
			//transform.appendTranslation(-diff.x + 25,-diff.y + 25) ;
			transform.appendTranslation(-diff.x,-diff.y ) ;
			
			
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

		// a new game object
		gameObject.setGameWorld(this);
		gameObject.initialize();
		List<GameObject> gameObjects = myGameObjects.get(layer);
		gameObject.setLayerOrder(layer);
		gameObjects.add(gameObject);
		numGameObjects++;

		for (Map.Entry<String, BaseGameSystem> systemEntry : mySystems.entrySet()) {
			systemEntry.getValue().addGameObject(gameObject);
		}
		
	}
	
	
	public List<GameObject> getGameObjectsByLayer( int layer)
	{
		return myGameObjects.get(layer);
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
	
	public int countGameObjects(String id) {
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
		if(mySystems.containsKey(ComponentContants.graphics))
		{
			GraphicsSystem graphicsSystem = (GraphicsSystem) mySystems.get(ComponentContants.graphics);
			return graphicsSystem.getPanelScreenViewPortUpperLeft();
		}
		return null;
	}

	public Vec2d getPanelScreenViewPortSize() {
		if(mySystems.containsKey(ComponentContants.graphics))
		{
			GraphicsSystem graphicsSystem = (GraphicsSystem) mySystems.get(ComponentContants.graphics);
			return graphicsSystem.getPanelScreenViewPortSize();	
		}
		return null;
	}


	public Vec2d getPanelGameViewPortTopLeft() {
		if(mySystems.containsKey(ComponentContants.graphics))
		{
			GraphicsSystem graphicsSystem = (GraphicsSystem) mySystems.get(ComponentContants.graphics);
			return graphicsSystem.getPanelGameViewPortTopLeft();	
		}
		return null ;
	}
	
	
	public void setPanelGameViewPort(Vec2d pos) {
		if(mySystems.containsKey(ComponentContants.graphics))
		{
			GraphicsSystem graphicsSystem = (GraphicsSystem) mySystems.get(ComponentContants.graphics);
			 graphicsSystem.setPanelGameViewPort(pos);	
		}
		
	}
	
	public void setPanelScreenViewPortUpperLeft(Vec2d pos) {
		if(mySystems.containsKey(ComponentContants.graphics))
		{
			GraphicsSystem graphicsSystem = (GraphicsSystem) mySystems.get(ComponentContants.graphics);
			graphicsSystem.setPanelScreenViewPortUpperLeft(pos);	
		}
		
	}
	
	public double getViewportScaleFactor() {
		if(mySystems.containsKey(ComponentContants.graphics))
		{
			GraphicsSystem graphicsSystem = (GraphicsSystem) mySystems.get(ComponentContants.graphics);
			return graphicsSystem.getViewportScaleFactor();	
		}
		return -1;
	}

	public void setViewportScaleFactor(double scale) {
		if(mySystems.containsKey(ComponentContants.graphics) && scale >= 1)
		{
			GraphicsSystem graphicsSystem = (GraphicsSystem) mySystems.get(ComponentContants.graphics);
			graphicsSystem.setViewportScaleFactor(scale);	
		}
		
	}

	public void addClonedGameObject(GameObject clone) {
		// TODO Auto-generated method stub
		int count = countGameObjects(clone.getId());  
		clone.setId(clone.getId() + (count+1));
		// a new game object
		clone.setGameWorld(this);
		//myGameObjects.add(clone);
		List<GameObject> dirtyObjects = myDirtyObjects.get(clone.getLayerOrder());
		dirtyObjects.add(clone);
		
		mySceneUpdated = true;
	}
	
	
	public void addDirtyGameObject(GameObject gameObject, int layer) {
		
		// a new game object
		gameObject.setGameWorld(this);
		//myGameObjects.add(clone);
		List<GameObject> dirtyObjects = myDirtyObjects.get(layer);
		dirtyObjects.add(gameObject);
		
		mySceneUpdated = true;
	}
	
	public void removeGameObject(String gameObjectId) {
	
		toRemoveList.add(gameObjectId);
		mySceneUpdated = true;
	}
	
	
	public boolean isNeedsUpdate() {
		return mySceneUpdated;
	}

	public void setNeedsUpdate(boolean myNeedsUpdate) {
		
		this.mySceneUpdated = myNeedsUpdate;
	}

	public GameObject getSelected() {
		return mySelected;
	}

	public void setSelected(GameObject selected) {
		if(this.mySelected == null || this.mySelected.getId().equals(selected.getId()))
		{			
			this.mySelected = selected;
		}
	}
	
	public Vec2d screenToGameTransform(Vec2d screenCoordinates)
	{
		if(mySystems.containsKey(ComponentContants.graphics) && myAffineTransform!=null)
		{
			GraphicsSystem graphicsSystem = (GraphicsSystem) mySystems.get(ComponentContants.graphics);
			Affine affineTransformation = myAffineTransform.clone();
			affineTransformation.appendTranslation(screenCoordinates.x-graphicsSystem.getPanelScreenViewPortUpperLeft().x , screenCoordinates.y -graphicsSystem.getPanelScreenViewPortUpperLeft().y);
			affineTransformation.appendScale(1/graphicsSystem.getViewportScaleFactor(), 1/graphicsSystem.getViewportScaleFactor());
			affineTransformation.appendTranslation(graphicsSystem.getPanelGameViewPortTopLeft().x , graphicsSystem.getPanelGameViewPortTopLeft().y);
			return new Vec2d(affineTransformation.getTx(),affineTransformation.getTy());
		
		}
		
		return null;
		
	}
	
	
	public Vec2d gameToScreenTransform(Vec2d gameCoordinates)
	{
		if(mySystems.containsKey(ComponentContants.graphics) && myAffineTransform!=null)
		{
			GraphicsSystem graphicsSystem = (GraphicsSystem) mySystems.get(ComponentContants.graphics);
			Affine affineTransformation = myAffineTransform.clone();
			Vec2d viewPortCenterGameSpace = this.screenToGameTransform(graphicsSystem.getPanelScreenViewPortSize()).sdiv(2);
			affineTransformation.appendTranslation(viewPortCenterGameSpace.x, viewPortCenterGameSpace.y);
			affineTransformation.appendTranslation(gameCoordinates.x-graphicsSystem.getPanelGameViewPortTopLeft().x , gameCoordinates.y -graphicsSystem.getPanelGameViewPortTopLeft().y);
			affineTransformation.appendTranslation(-graphicsSystem.getPanelScreenViewPortSize().x/2,  -graphicsSystem.getPanelScreenViewPortSize().y/2);
			affineTransformation.appendScale(graphicsSystem.getViewportScaleFactor(), graphicsSystem.getViewportScaleFactor());
			affineTransformation.appendTranslation(graphicsSystem.getPanelScreenViewPortSize().x/2,  graphicsSystem.getPanelScreenViewPortSize().y/2);
			affineTransformation.appendTranslation(graphicsSystem.getPanelScreenViewPortUpperLeft().x , graphicsSystem.getPanelScreenViewPortUpperLeft().y);
			affineTransformation.appendTranslation(-viewPortCenterGameSpace.x, -viewPortCenterGameSpace.y);
			return new Vec2d(affineTransformation.getTx(),affineTransformation.getTy());
		}
		
		return null;
		
	}
	
	
	public int getNumGameObjects()
	{
		return numGameObjects;
	}
	
	public Vec2d getMyScreenViewPortPos() {
		return myScreenViewPortPos;
	}

	public void setMyScreenViewPortPos(Vec2d myScreenViewPortPos) {
		this.myScreenViewPortPos = myScreenViewPortPos;
	}

	public Vec2d getMyScreenViewPortSize() {
		return myScreenViewPortSize;
	}

	public void setMyScreenViewPortSize(Vec2d myScreenViewPortSize) {
		this.myScreenViewPortSize = myScreenViewPortSize;
	}
	
	public void destroyGameObjects()
	{
		//this.isClearingObjects = true;
		for(List<GameObject> gameObjects: myGameObjects)
		{
			for(GameObject gameObject: gameObjects)
			{
				this.removeGameObject(gameObject.getId());
			}
		}
		
	}

	public GameObject getSceneCamera() {
		return mySceneCamera;
	}

	public void setSceneCamera(GameObject sceneCamera) {
		if(this.mySceneCamera == null & sceneCamera != null)
		{
			mySceneCamera = sceneCamera;
			mySceneCamera.setGameWorld(this);
			mySceneCamera.initialize();
			mySceneCamera.setLayerOrder(CameraLayer);

			for (Map.Entry<String, BaseGameSystem> systemEntry : mySystems.entrySet()) {
				systemEntry.getValue().addGameObject(mySceneCamera);
			}
			this.mySceneCamera = sceneCamera;
		}
		
	}
	
	

}
