package fxengine.application;

import java.util.ArrayList;
import java.util.List;

import fxengine.math.Vec2d;
import fxengine.scene.BaseScene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class GameApplication extends FXFrontEnd{
	private List<BaseScene> myScenes = new ArrayList<BaseScene>();
	private BaseScene currentScene;
	
	
	public GameApplication(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void onTick(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		
		//verify new scene is set
		if(!this.currentScene.isActive())
		{
			this.currentScene.cleanScene();
			for(BaseScene scene: myScenes)
			{
				if(scene.isActive() )
				{
					scene.setActive(true);
					this.currentScene = scene;
					
				}
				if(!scene.isInitialized())
				{
					this.currentScene.setCurrentWindowSize(this.currentStageSize);
					this.currentScene.initScene();
					//this.currentScene.onResize(this.currentStageSize);
				}

				
				break;
			}
		}
		
		
		
		
		this.currentScene.onTick(nanosSincePreviousTick);
	}

	@Override
	protected void onDraw(GraphicsContext g) {
		// TODO Auto-generated method stub
		this.currentScene.onDraw(g);
		
	}

	@Override
	protected void onKeyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		this.currentScene.onKeyTyped(e);
	}

	@Override
	protected void onKeyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
		this.currentScene.onKeyPressed(event);
	}

	@Override
	protected void onKeyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		this.currentScene.onKeyReleased(e);
	}

	@Override
	protected void onMouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	
		this.currentScene.onMouseClicked(e);
		
	}

	@Override
	protected void onMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		this.currentScene.onMousePressed(e);
	}

	@Override
	protected void onMouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		this.currentScene.onMouseReleased(e);
	}

	@Override
	protected void onMouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		this.currentScene.onMouseDragged(e);
	}

	@Override
	protected void onMouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		this.currentScene.onMouseMoved(e);
	}

	@Override
	protected void onMouseWheelMoved(ScrollEvent e) {
		// TODO Auto-generated method stub
		this.currentScene.onMouseWheelMoved(e);
	}

	@Override
	protected void onFocusChanged(boolean newVal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onResize(Vec2d newSize) {
		// TODO Auto-generated method stub
		
		this.currentScene.onResize(newSize);
	}

	@Override
	protected void onShutdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onStartup() {
		// TODO Auto-generated method stub
		if(this.currentScene == null)
		{
			for(BaseScene scene: myScenes)
			{
				// finds the first active scene on the list and load it
				if(scene.isActive() && !scene.isInitialized())
				{
					this.currentScene = scene;
					this.currentScene.setCurrentWindowSize(this.currentStageSize);
					this.currentScene.initScene();
					//this.currentScene.onResize(this.currentStageSize);			
					return;
				}
			}
		}
		
		
	}
	
	public void setActiveScreen(String name)
	{
		if(this.currentScene != null)
		{
			this.currentScene.setActive(false);
		}
		for(BaseScene scene: myScenes)
		{
			if(scene.getSceneName().equals(name) && !scene.isActive())
			{
				scene.setActive(true);
				return;
			}
		}
	}
	
	public void addScene(BaseScene scene)
	{
		this.myScenes.add(scene);
	}
	
	
}
