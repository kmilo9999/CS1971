package game.alch;

import fxengine.application.FXFrontEnd;
import fxengine.components.Component;
import fxengine.components.ComponentFactory;
import fxengine.components.ComponetContants;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import fxengine.objects.GameWorld;
import fxengine.scene.GameWorldScene;
import fxengine.system.MouseEventSystem;


public class AlchScene extends GameWorldScene{

	
	
	public AlchScene(String name, FXFrontEnd application) {
		super(name, application);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initScene() 
	{
		
        // Initialize game world
		super.initScene();
		
		
		GameObject gameObject = new GameObject("Sprite1");
		
		
		Component graphicsComponent =  ComponentFactory.getInstance().createComponent(ComponetContants.graphics);
		Component tranformComponent =  ComponentFactory.getInstance().createComponent(ComponetContants.transform);
		//Component mouseEventsComponent =  ComponentFactory.getInstance().createComponent(ComponetContants.mouseEvents);
		//Component keyEventsComponent =  ComponentFactory.getInstance().createComponent(ComponetContants.keyEvents);
		Component mouseControllerComponent =  ComponentFactory.getInstance().createComponent(ComponetContants.controllerMouseEvents);
		Component keyControllerComponent =  ComponentFactory.getInstance().createComponent(ComponetContants.controllerKeyEvents);
		((TransformComponent)tranformComponent).setPosition(new Vec2d(0,-2));
		
		gameObject.addComponent(graphicsComponent);
		gameObject.addComponent(tranformComponent);
		//gameObject.addComponent(mouseEventsComponent);
		//gameObject.addComponent(keyEventsComponent);
		gameObject.addComponent(mouseControllerComponent);
		gameObject.addComponent(keyControllerComponent);
		
		
		this.myGameWorld.addGameObject(gameObject, GameWorld.FrontLayer);		
		
		
		GameObject camera = new GameObject("Camera");
		Component cameraMouseControllerComponent =  ComponentFactory.getInstance().createComponent(ComponetContants.cameraControllerMouseEvents);
		Component cameraKeyControllerComponent =  ComponentFactory.getInstance().createComponent(ComponetContants.cameraControllerKeyEvents);
		camera.addComponent(cameraMouseControllerComponent);
		camera.addComponent(cameraKeyControllerComponent);
		
		this.myGameWorld.addGameObject(camera, GameWorld.FrontLayer);	
	}
	
	

	

}
