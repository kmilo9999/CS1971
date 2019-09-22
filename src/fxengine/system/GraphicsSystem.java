package fxengine.system;

import java.util.List;

import fxengine.components.ComponetContants;
import fxengine.components.GraphicsComponent;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;

public class GraphicsSystem extends BaseGameSystem{

	
	private List<List<GameObject>> myLayerGameObjects;
	
	private Vec2d panelScreenViewPortUpperLeft = new Vec2d(0);
	private Vec2d panelScreenViewPortSize = new Vec2d(400);


	private Vec2d panelGameViewPort = new Vec2d(0,0);
	private double viewportScaleFactor = 1.0;
	
    
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		for(GameObject gameObject:myGameObjects)
		{
			GraphicsComponent gComponent = (GraphicsComponent)gameObject.getComponent(ComponetContants.graphics);
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
				GraphicsComponent gComponent = (GraphicsComponent)gameObject.getComponent(ComponetContants.graphics);
				//gComponent.clip(panelScreenViewPortUpperLeft,panelScreenViewPortSize,viewportScaleFactor,panelGameViewPort);
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
		return panelGameViewPort;
	}

	public void setPanelGameViewPort(Vec2d panelGameViewPort) {
		this.panelGameViewPort = panelGameViewPort;
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
