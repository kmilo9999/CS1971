package fxengine.system;

import java.util.List;

import fxengine.components.ComponentContants;
import fxengine.components.GraphicsComponent;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;

public class GraphicsSystem extends BaseGameSystem{

	
	private List<List<GameObject>> myLayerGameObjects;
	
	private Vec2d panelScreenViewPortUpperLeft = new Vec2d(100,50);
	private Vec2d panelScreenViewPortSize = new Vec2d(400);


	private Vec2d panelGameViewPortUpperLeft = new Vec2d(0);
	private double viewportScaleFactor = 1.0;
	
    
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		myComponentsOfInterest.add(ComponentContants.graphics);
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		for(GameObject gameObject:myGameObjects)
		{
			GraphicsComponent gComponent = (GraphicsComponent)gameObject.getComponent(ComponentContants.graphics);
			if(gComponent != null)
			{
				gComponent.update(nanosSincePreviousTick);	
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
		for(List<GameObject> gameObjects:myLayerGameObjects)
		{
			for(GameObject gameObject:gameObjects) 
			{
				GraphicsComponent gComponent = (GraphicsComponent)gameObject.getComponent(ComponentContants.graphics);
				
				if(gComponent != null)
				{
					gComponent.draw(graphicsCx);	
				}
			}
		}
	
	}
	
	public Vec2d getPanelScreenViewPortUpperLeft() {
		return panelScreenViewPortUpperLeft;
	}

	public void setPanelScreenViewPortUpperLeft(Vec2d panelScreenViewPortUpperLeft) {
		this.panelScreenViewPortUpperLeft = panelScreenViewPortUpperLeft;
	}

	public Vec2d getPanelScreenViewPortSize() {
		return panelScreenViewPortSize;
	}

	public void setPanelScreenViewPortSize(Vec2d panelScreenViewPortSize) {
		this.panelScreenViewPortSize = panelScreenViewPortSize;
	}
	

	public Vec2d getPanelGameViewPort() {
		return panelGameViewPortUpperLeft;
	}

	public void setPanelGameViewPort(Vec2d panelGameViewPort) {
		this.panelGameViewPortUpperLeft = panelGameViewPort;
	}
  
	public List<List<GameObject>> getLayerGameObjects() {
		return myLayerGameObjects;
	}

	public void setLayerGameObjects(List<List<GameObject>> layerGameObjects) {
		this.myLayerGameObjects = layerGameObjects;
	}
	
	public double getViewportScaleFactor() {
		return viewportScaleFactor;
	}

	public void setViewportScaleFactor(double viewportScaleFactor) {
		this.viewportScaleFactor = viewportScaleFactor;
	}

	

	
}
