package game.alch;

import fxengine.application.FXFrontEnd;
import fxengine.collision.CollisionConstants;
import fxengine.collision.CollisionShape;
import fxengine.collision.CollisionShapeFactory;
import fxengine.components.CollisionComponent;
import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.components.GraphicsComponent;
import fxengine.components.SpriteComponent;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import fxengine.objects.GameWorld;
import fxengine.scene.GameWorldScene;
import javafx.scene.canvas.GraphicsContext;


public class AlchScene extends GameWorldScene{


	AlchMenu myMenu;
	
	public AlchScene(String name, FXFrontEnd application) {
		super(name, application);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initScene() 
	{

		
		myMenu = new AlchMenu(this);
        // Initialize game world
		super.initScene();
		
		
		GameObject gameObject = new GameObject("Sprite1");
		Component graphicsComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.graphics);
		SpriteComponent mySprite1 = new SpriteComponent("resources/img/square.png");
		((GraphicsComponent)graphicsComponent).setSprite(mySprite1);
		
		Component tranformComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.transform);
		//Component mouseEventsComponent =  ComponentFactory.getInstance().createComponent(ComponetContants.mouseEvents);
		//Component keyEventsComponent =  ComponentFactory.getInstance().createComponent(ComponetContants.keyEvents);
		Component mouseControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerMouseEvents);
		Component keyControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerKeyEvents);
		((TransformComponent)tranformComponent).setPosition(new Vec2d(3,10));
		Component collisionCompomemt =  ComponentFactory.getInstance().createComponent(ComponentContants.collision);
		CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
		((CollisionComponent)collisionCompomemt).setCollisionShape(myCollisionShape);
		
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
		SpriteComponent mySprite2 = new SpriteComponent("resources/img/circle.png");
		((GraphicsComponent)graphicsComponent2).setSprite(mySprite2);
		Component tranformComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.transform);
		//Component mouseEventsComponent =  ComponentFactory.getInstance().createComponent(ComponetContants.mouseEvents);
		//Component keyEventsComponent =  ComponentFactory.getInstance().createComponent(ComponetContants.keyEvents);
		Component mouseControllerComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerMouseEvents);
		Component keyControllerComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerKeyEvents);
		((TransformComponent)tranformComponent2).setPosition(new Vec2d(50));
		Component collisionCompomemt2 =  ComponentFactory.getInstance().createComponent(ComponentContants.collision);
		((CollisionComponent)collisionCompomemt2).getHitList().add("Sprite1");
		CollisionShape myCollisionShape2 = CollisionShapeFactory.getInstance().createShape(CollisionConstants.CIRCLEShape);
		((CollisionComponent)collisionCompomemt2).setCollisionShape(myCollisionShape2);
		
		
		gameObject2.addComponent(graphicsComponent2);
		gameObject2.addComponent(tranformComponent2);
		//gameObject.addComponent(mouseEventsComponent);
		//gameObject.addComponent(keyEventsComponent);
		gameObject2.addComponent(mouseControllerComponent2);
		gameObject2.addComponent(keyControllerComponent2);
		gameObject2.addComponent(collisionCompomemt2);
		
		
		this.myGameWorld.addGameObject(gameObject2, GameWorld.FrontLayer);
	}
	
	
	@Override
	public void onDraw(GraphicsContext graphicsContext)
	{
		
		super.onDraw(graphicsContext);
	}

	
	public GameObject createAlchGameObject(String id, Vec2d position, AlchElementMenu element )
	{
		
		String newId = id + (this.myGameWorld.getNumGameObjects() + 1);
		GameObject alchGameObject = new GameObject(newId);
		Component graphicsComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.graphics);
		SpriteComponent sprite = new SpriteComponent(element.getFilePath());
		((GraphicsComponent)graphicsComponent).setSprite(sprite);
		Component tranformComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.transform);
		Component mouseControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerMouseEvents);
		Component keyControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerKeyEvents);
		((TransformComponent)tranformComponent).setPosition(position);
		Component collisionCompoment =  ComponentFactory.getInstance().createComponent(ComponentContants.collision);
		((CollisionComponent)collisionCompoment).getHitList().add("Sprite1");
		if(element.getElementType().equals("CIRCLE"))
		{
			CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.CIRCLEShape);
			((CollisionComponent)collisionCompoment).setCollisionShape(myCollisionShape);	
		}
		else if(element.getElementType().equals("SQUARE"))
		{
			CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
			((CollisionComponent)collisionCompoment).setCollisionShape(myCollisionShape);
		}
			
		alchGameObject.addComponent(graphicsComponent);
		alchGameObject.addComponent(tranformComponent);
		//gameObject.addComponent(mouseEventsComponent);
		//gameObject.addComponent(keyEventsComponent);
		alchGameObject.addComponent(mouseControllerComponent);
		alchGameObject.addComponent(keyControllerComponent);
		alchGameObject.addComponent(collisionCompoment);
		
		this.myGameWorld.addGameObject(alchGameObject, GameWorld.FrontLayer);
		
		return alchGameObject;
	}

}
