package game.alch;

import fxengine.application.FXFrontEnd;
import fxengine.components.CollisionComponent;
import fxengine.components.Component;
import fxengine.components.ComponentFactory;
import fxengine.components.ComponentContants;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import fxengine.objects.GameWorld;
import fxengine.scene.GameWorldScene;


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
		Component graphicsComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.graphics);
		Component tranformComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.transform);
		//Component mouseEventsComponent =  ComponentFactory.getInstance().createComponent(ComponetContants.mouseEvents);
		//Component keyEventsComponent =  ComponentFactory.getInstance().createComponent(ComponetContants.keyEvents);
		Component mouseControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerMouseEvents);
		Component keyControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerKeyEvents);
		((TransformComponent)tranformComponent).setPosition(new Vec2d(3,10));
		Component collisionCompomemt =  ComponentFactory.getInstance().createComponent(ComponentContants.collision);
		
		gameObject.addComponent(graphicsComponent);
		gameObject.addComponent(tranformComponent);
		//gameObject.addComponent(mouseEventsComponent);
		//gameObject.addComponent(keyEventsComponent);
		gameObject.addComponent(mouseControllerComponent);
		gameObject.addComponent(keyControllerComponent);
		gameObject.addComponent(collisionCompomemt);
		
		
		
		this.myGameWorld.addGameObject(gameObject, GameWorld.FrontLayer);		
		
		
		GameObject camera = new GameObject("Camera");
		Component cameraMouseControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.cameraControllerMouseEvents);
		Component cameraKeyControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.cameraControllerKeyEvents);
		camera.addComponent(cameraMouseControllerComponent);
		camera.addComponent(cameraKeyControllerComponent);
		
		this.myGameWorld.addGameObject(camera, GameWorld.FrontLayer);	
		
		GameObject gameObject2 = new GameObject("Sprite2");
		Component graphicsComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.graphics);
		Component tranformComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.transform);
		//Component mouseEventsComponent =  ComponentFactory.getInstance().createComponent(ComponetContants.mouseEvents);
		//Component keyEventsComponent =  ComponentFactory.getInstance().createComponent(ComponetContants.keyEvents);
		Component mouseControllerComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerMouseEvents);
		Component keyControllerComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerKeyEvents);
		((TransformComponent)tranformComponent2).setPosition(new Vec2d(50));
		Component collisionCompomemt2 =  ComponentFactory.getInstance().createComponent(ComponentContants.collision);
		((CollisionComponent)collisionCompomemt2).getHitList().add("Sprite1");
		
		gameObject2.addComponent(graphicsComponent2);
		gameObject2.addComponent(tranformComponent2);
		//gameObject.addComponent(mouseEventsComponent);
		//gameObject.addComponent(keyEventsComponent);
		gameObject2.addComponent(mouseControllerComponent2);
		gameObject2.addComponent(keyControllerComponent2);
		gameObject2.addComponent(collisionCompomemt2);
		
		
		this.myGameWorld.addGameObject(gameObject2, GameWorld.FrontLayer);
	}
	
	

	

}
