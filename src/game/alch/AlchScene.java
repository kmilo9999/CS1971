package game.alch;

import java.util.List;

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
	
	public static final String alch_circle = "ALCHE1";
	public static final String alch_square = "ALCHE2";
	public static final String alch_diamon = "ALCHE3";
	public static final String alch_triangle = "ALCHE4";
	public static final String alch_star = "ALCHE5";
	public static final String alch_arrow = "ALCHE6";
	public static final String alch_check = "ALCHE7";
	public static final String alch_cloud = "ALCHE8";
	
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
		
		
		GameObject gameObject = new GameObject("alch_elem");
		gameObject.setTag(alch_square);
		Component graphicsComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.graphics);
		SpriteComponent mySprite1 = new SpriteComponent("resources/img/square.png");
		((GraphicsComponent)graphicsComponent).setSprite(mySprite1);
		
		Component tranformComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.transform);
		Component mouseControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerMouseEvents);
		Component keyControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerKeyEvents);
		((TransformComponent)tranformComponent).setPosition(new Vec2d(3,10));
		Component collisionCompomemt =  ComponentFactory.getInstance().createComponent(ComponentContants.collision);
		CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
		((CollisionComponent)collisionCompomemt).setCollisionShape(myCollisionShape);
		//((CollisionComponent)collisionCompomemt).getHitList().add(alch_circle);
		
		gameObject.addComponent(graphicsComponent);
		gameObject.addComponent(tranformComponent);
		gameObject.addComponent(mouseControllerComponent);
		gameObject.addComponent(keyControllerComponent);
		gameObject.addComponent(collisionCompomemt);
		
		
		
		//this.myGameWorld.addGameObject(gameObject, GameWorld.FrontLayer);		
		
		///----------- Camera
		GameObject camera = new GameObject("Camera");
		Component cameraMouseControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.cameraControllerMouseEvents);
		Component cameraKeyControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.cameraControllerKeyEvents);
		camera.addComponent(cameraMouseControllerComponent);
		camera.addComponent(cameraKeyControllerComponent);
		
		this.myGameWorld.addGameObject(camera, GameWorld.FrontLayer);
		///----------------------------------
		
		GameObject gameObject2 = new GameObject("alch_elem2");
		gameObject2.setTag(alch_circle);
		Component graphicsComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.graphics);
		SpriteComponent mySprite2 = new SpriteComponent("resources/img/circle.png");
		((GraphicsComponent)graphicsComponent2).setSprite(mySprite2);
		Component tranformComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.transform);
	    Component mouseControllerComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerMouseEvents);
		Component keyControllerComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerKeyEvents);
		((TransformComponent)tranformComponent2).setPosition(new Vec2d(200));
		Component collisionCompomemt2 =  ComponentFactory.getInstance().createComponent(ComponentContants.collision);
		((CollisionComponent)collisionCompomemt2).getHitList().add(alch_square);
		CollisionShape myCollisionShape2 = CollisionShapeFactory.getInstance().createShape(CollisionConstants.CIRCLEShape);
		((CollisionComponent)collisionCompomemt2).setCollisionShape(myCollisionShape2);
		
		
		gameObject2.addComponent(graphicsComponent2);
		gameObject2.addComponent(tranformComponent2);
		gameObject2.addComponent(mouseControllerComponent2);
		gameObject2.addComponent(keyControllerComponent2);
		gameObject2.addComponent(collisionCompomemt2);
		
		
		this.myGameWorld.addGameObject(gameObject2, GameWorld.FrontLayer);
	}
	
	

	@Override
	public void onTick(long nanosSincePreviousTick)
	{
		List<GameObject> gameObjects = this.myGameWorld.getGameObjectsByLayer(GameWorld.FrontLayer);
	    for(GameObject gameObject:gameObjects)
	    {
	       //if(!gameObject.isMarkForDestoryed())
	      // {
	    	   CollisionComponent collisionState = (CollisionComponent) gameObject.getComponent(ComponentContants.collision);
		       if(collisionState != null && collisionState.isCollided())
		       {
		    	   GameObject colliding = collisionState.getOtherCollider();
		    	   if(collisionState.getHitList().contains(colliding.getTag()))
		    	   {
		    		   String otherId = collisionState.getColliderId();
		    		   
		    		   this.myGameWorld.toRemoveGameObject(gameObject.getId());
		    		   this.myGameWorld.toRemoveGameObject(otherId);
		    		   
		    		// collisionState.getOtherCollider().setMarkForDestoryed(true);
		    		   TransformComponent transform = (TransformComponent) gameObject.getComponent(ComponentContants.transform);
		    		   if(transform != null)
		    		   {
		    			   if(gameObject.getTag().equals(alch_circle) && 
		    					   colliding.getTag().equals(alch_square))
		    			   {
		    				   this.createAlchGameObjectFromCollision("alch_elem", alch_diamon, transform.getPosition());   
		    			   }
		    			   if(gameObject.getTag().equals(alch_triangle) && 
		    					   colliding.getTag().equals(alch_diamon))
		    			   {
		    				   this.createAlchGameObjectFromCollision("alch_elem", alch_check, transform.getPosition());   
		    			   }
		    			   if(gameObject.getTag().equals(alch_star) && 
		    					   colliding.getTag().equals(alch_triangle))
		    			   {
		    				   this.createAlchGameObjectFromCollision("alch_elem", alch_arrow, transform.getPosition());   
		    			   }
		    			      
		    		   }
		    		    
		    		   
		    			/*
						((GameObject) myGameObjects.get(i)).getGameWorld()
								.toRemoveGameObject(((GameObject) myGameObjects.get(i)).getId());
						
						((GameObject) myGameObjects.get(i)).getGameWorld()
						.toRemoveGameObject(((GameObject) myGameObjects.get(j)).getId());*/
		    	   }
		       //}
	       }
	      
	    }
	    
	    super.onTick(nanosSincePreviousTick);
	}
	
	public GameObject createAlchGameObjectFromMenu(String id, Vec2d position, AlchElementMenu element )
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
		
		if(element.getElementType().equals("CIRCLE"))
		{
			alchGameObject.setTag(alch_circle);
			CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.CIRCLEShape);
			((CollisionComponent)collisionCompoment).setCollisionShape(myCollisionShape);
			((CollisionComponent)collisionCompoment).getHitList().add(alch_square);
		}
		else if(element.getElementType().equals("SQUARE"))
		{
			alchGameObject.setTag(alch_square);
			CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
			((CollisionComponent)collisionCompoment).setCollisionShape(myCollisionShape);
			//((CollisionComponent)collisionCompoment).getHitList().add(alch_circle);
		}
		if(element.getElementType().equals("TRIANGLE")) {
			
			alchGameObject.setTag(alch_triangle);
			CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
			((CollisionComponent)collisionCompoment).setCollisionShape(myCollisionShape);
			((CollisionComponent)collisionCompoment).getHitList().add(alch_diamon);
		}
		if(element.getElementType().equals("STAR"))
		{
			alchGameObject.setTag(alch_star);
			CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
			((CollisionComponent)collisionCompoment).setCollisionShape(myCollisionShape);
			((CollisionComponent)collisionCompoment).getHitList().add(alch_triangle);
		}
			
		alchGameObject.addComponent(graphicsComponent);
		alchGameObject.addComponent(tranformComponent);
		alchGameObject.addComponent(mouseControllerComponent);
		alchGameObject.addComponent(keyControllerComponent);
		alchGameObject.addComponent(collisionCompoment);
		
		this.myGameWorld.addGameObject(alchGameObject, GameWorld.FrontLayer);
		
		return alchGameObject;
	}
	
	public GameObject createAlchGameObjectFromCollision(String id, String type, Vec2d position )
	{
		String newId = id + (this.myGameWorld.getNumGameObjects() + 1);
		GameObject alchGameObject = new GameObject(newId);
		alchGameObject.setTag(type);
		Component graphicsComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.graphics);
		
		
		Component tranformComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.transform);
		Component mouseControllerComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerMouseEvents);
		Component keyControllerComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerKeyEvents);
		((TransformComponent)tranformComponent2).setPosition(position);
		
		Component collisionCompoment =  ComponentFactory.getInstance().createComponent(ComponentContants.collision);
		SpriteComponent mySprite2 = null;
		if(type.equals(alch_diamon))
		{
			alchGameObject.setTag(alch_diamon);
			CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
			((CollisionComponent)collisionCompoment).setCollisionShape(myCollisionShape);
			((CollisionComponent)collisionCompoment).getHitList().add(alch_circle);
			mySprite2 = new SpriteComponent("resources/img/diamon.png");
		}
		else if(type.equals(alch_check)) {
			
			alchGameObject.setTag(alch_check);
			CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
			((CollisionComponent)collisionCompoment).setCollisionShape(myCollisionShape);
			((CollisionComponent)collisionCompoment).getHitList().add(alch_diamon);
			mySprite2 = new SpriteComponent("resources/img/check.png");
		}
		else if(type.equals(alch_arrow))
		{	
			alchGameObject.setTag(alch_arrow);
			CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
			((CollisionComponent)collisionCompoment).setCollisionShape(myCollisionShape);
			//((CollisionComponent)collisionCompoment).getHitList().add(alch_triangle);
			mySprite2 = new SpriteComponent("resources/img/arrow.png");
		}
		((GraphicsComponent)graphicsComponent2).setSprite(mySprite2);
		
		
		
		alchGameObject.addComponent(graphicsComponent2);
		alchGameObject.addComponent(tranformComponent2);
		
		alchGameObject.addComponent(mouseControllerComponent2);
		alchGameObject.addComponent(keyControllerComponent2);
		alchGameObject.addComponent(collisionCompoment);
		
		
		this.myGameWorld.addDirtyGameObject(alchGameObject, GameWorld.FrontLayer);
		
		return alchGameObject;
	}

}
