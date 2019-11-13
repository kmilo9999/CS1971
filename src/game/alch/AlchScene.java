package game.alch;

import java.util.List;

import fxengine.application.FXFrontEnd;
import fxengine.application.GameApplication;
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
	public static final String alch_dbz = "ALCHE9";
	
	private String circleStr = "circle";
	private String squareStr = "square";
	private String diamonStr = "diamon";
	private String triangleStr = "triangle";
	private String starStr = "star";
	private String arrowStr = "arrow";
	private String checkStr = "check";
	private String cloudStr = "cloud";
	private String dbzStr = "dbz";
	
	
	private int numCircles = 0;
	private int numSquares = 0;
	private int numDiamons = 0;
	private int numTriangles = 0;
	private int numStars = 0;
	private int numArrows = 0;
	private int numChecks = 0;
	private int numClouds = 0;
	private int numDbz = 0;
	
	public AlchScene(String name, GameApplication application) {
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
		SpriteComponent mySprite1 = new SpriteComponent(ComponentContants.sprite);
		mySprite1.setFilePath("img/square.png");
		((GraphicsComponent)graphicsComponent).setSprite(mySprite1);
		
		Component tranformComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.transform);
		Component mouseControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerMouseEvents);
		Component keyControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerKeyEvents);
		((TransformComponent)tranformComponent).setPosition(new Vec2d(3,10));
		Component collisionCompomemt =  ComponentFactory.getInstance().createComponent(ComponentContants.collision);
		CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
		((CollisionComponent)collisionCompomemt).setCollisionShape(myCollisionShape);
		((CollisionComponent)collisionCompomemt).getHitList().add(alch_circle);
		
		gameObject.addComponent(graphicsComponent);
		gameObject.addComponent(tranformComponent);
		gameObject.addComponent(mouseControllerComponent);
		gameObject.addComponent(keyControllerComponent);
		gameObject.addComponent(collisionCompomemt);
		gameObject.addComponent(mySprite1);
		
		
		//this.myGameWorld.addGameObject(gameObject, GameWorld.FrontLayer);		
		
		///----------- Camera
		GameObject camera = new GameObject("Camera");
		Component cameraMouseControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.cameraControllerMouseEvents);
		Component cameraKeyControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.cameraControllerKeyEvents);
		camera.addComponent(cameraMouseControllerComponent);
		camera.addComponent(cameraKeyControllerComponent);
		
		this.myGameWorld.addGameObject(camera, GameWorld.PlayerLayer);
		///----------------------------------
		
		GameObject gameObject2 = new GameObject("alch_elem2");
		gameObject2.setTag(alch_circle);
		Component graphicsComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.graphics);
		SpriteComponent mySprite2 = new SpriteComponent(ComponentContants.sprite);
		mySprite2.setFilePath("img/circle.png");		
		Component tranformComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.transform);
	    Component mouseControllerComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerMouseEvents);
		Component keyControllerComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerKeyEvents);
		((TransformComponent)tranformComponent2).setPosition(new Vec2d(50));
		Component collisionComponemt2 =  ComponentFactory.getInstance().createComponent(ComponentContants.collision);
		((CollisionComponent)collisionComponemt2).getHitList().add(alch_square);
		CollisionShape myCollisionShape2 = CollisionShapeFactory.getInstance().createShape(CollisionConstants.CIRCLEShape);
		((CollisionComponent)collisionComponemt2).setCollisionShape(myCollisionShape2);
		
		
		gameObject2.addComponent(graphicsComponent2);
		gameObject2.addComponent(tranformComponent2);
		gameObject2.addComponent(mouseControllerComponent2);
		gameObject2.addComponent(keyControllerComponent2);
		gameObject2.addComponent(collisionComponemt2);
		gameObject2.addComponent(mySprite2);
		
		this.myGameWorld.addGameObject(gameObject2, GameWorld.PlayerLayer);
		numCircles++;
	}
	
	

	@Override
	public void onTick(long nanosSincePreviousTick)
	{
		List<GameObject> gameObjects = this.myGameWorld.getGameObjectsByLayer(GameWorld.PlayerLayer);
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
		    		   
		    		   this.myGameWorld.removeGameObject(gameObject.getId());
		    		   this.myGameWorld.removeGameObject(otherId);
		    		   
		    		// collisionState.getOtherCollider().setMarkForDestoryed(true);
		    		   TransformComponent transform = (TransformComponent) gameObject.getComponent(ComponentContants.transform);
		    		   if(transform != null)
		    		   {
		    			   if(gameObject.getTag().equals(alch_circle) && 
		    					   colliding.getTag().equals(alch_square))
		    			   {
		    				   this.createAlchGameObjectFromCollision("alch_elem", alch_diamon, transform.getPosition());   
		    			   }
		    			   else if(gameObject.getTag().equals(alch_triangle) && 
		    					   colliding.getTag().equals(alch_diamon))
		    			   {
		    				   this.createAlchGameObjectFromCollision("alch_elem", alch_check, transform.getPosition());   
		    			   }
		    			   else if(gameObject.getTag().equals(alch_star) && 
		    					   colliding.getTag().equals(alch_triangle))
		    			   {
		    				   this.createAlchGameObjectFromCollision("alch_elem", alch_arrow, transform.getPosition());   
		    			   }
		    			   else if(gameObject.getTag().equals(alch_arrow) && 
		    					   colliding.getTag().equals(alch_check))
		    			   {
		    				   this.createAlchGameObjectFromCollision("alch_elem", alch_cloud, transform.getPosition());
		    			   }
		    			   else if(gameObject.getTag().equals(alch_cloud) && 
		    					   colliding.getTag().equals(alch_diamon))
		    			   {
		    				   this.createAlchGameObjectFromCollision("alch_elem", alch_dbz, transform.getPosition());
		    			   }
		    		   }
		    		    
		    		
		    	   }
		       //}
	       }
	      
	    }
	    
	    super.onTick(nanosSincePreviousTick);
	}
	
	public GameObject createAlchGameObjectFromMenu(String id, Vec2d position, AlchElementMenu element )
	{
		
		
		Component graphicsComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.graphics);
		SpriteComponent spriteComponent = new SpriteComponent(ComponentContants.sprite);
		spriteComponent.setFilePath(element.getFilePath());
		Component tranformComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.transform);
		Component mouseControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerMouseEvents);
		Component keyControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerKeyEvents);
		((TransformComponent)tranformComponent).setPosition(position);
		Component collisionCompoment =  ComponentFactory.getInstance().createComponent(ComponentContants.collision);
		String elemTag = "";
		String elemType = "";
		int elemCount = 0;
		
		if(element.getElementType().equals("CIRCLE"))
		{
			elemTag = alch_circle;
			elemType = circleStr;
			elemCount = ++numCircles;
			CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.CIRCLEShape);
			((CollisionComponent)collisionCompoment).setCollisionShape(myCollisionShape);
			((CollisionComponent)collisionCompoment).getHitList().add(alch_square);
		}
		else if(element.getElementType().equals("SQUARE"))
		{
			elemTag = alch_square;
			elemType = squareStr;
			elemCount = ++numSquares;
			CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
			((CollisionComponent)collisionCompoment).setCollisionShape(myCollisionShape);
			((CollisionComponent)collisionCompoment).getHitList().add(alch_circle);
		}
		if(element.getElementType().equals("TRIANGLE")) {
			
			elemTag = alch_triangle;
			elemType = triangleStr;
			elemCount = ++numTriangles;
			CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
			((CollisionComponent)collisionCompoment).setCollisionShape(myCollisionShape);
			((CollisionComponent)collisionCompoment).getHitList().add(alch_diamon);
		}
		if(element.getElementType().equals("STAR"))
		{
			elemTag = alch_star;
			elemType = starStr;
			elemCount = ++numStars;
			CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
			((CollisionComponent)collisionCompoment).setCollisionShape(myCollisionShape);
			((CollisionComponent)collisionCompoment).getHitList().add(alch_triangle);
		}
			
		
		String newId = id + elemType + elemCount;
		GameObject alchGameObject = new GameObject(newId);
		alchGameObject.setTag(elemTag);
		
		alchGameObject.addComponent(graphicsComponent);
		alchGameObject.addComponent(tranformComponent);
		alchGameObject.addComponent(mouseControllerComponent);
		alchGameObject.addComponent(keyControllerComponent);
		alchGameObject.addComponent(collisionCompoment);
		alchGameObject.addComponent(spriteComponent);
		
		this.myGameWorld.addGameObject(alchGameObject, GameWorld.PlayerLayer);
		
		return alchGameObject;
	}
	
	public GameObject createAlchGameObjectFromCollision(String id, String type, Vec2d position )
	{
		
		
		Component graphicsComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.graphics);
		
		
		Component tranformComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.transform);
		Component mouseControllerComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerMouseEvents);
		Component keyControllerComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerKeyEvents);
		((TransformComponent)tranformComponent2).setPosition(position);
		
		Component collisionCompoment =  ComponentFactory.getInstance().createComponent(ComponentContants.collision);
		SpriteComponent spriteComponent = new SpriteComponent(ComponentContants.sprite);
		
		String elemTag = "";
		String elemType = "";
		int elemCount = 0;
		
		if(type.equals(alch_diamon) )
		{
			elemTag = alch_diamon;
			elemType = diamonStr;
			elemCount = ++numDiamons;
			
			CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
			((CollisionComponent)collisionCompoment).setCollisionShape(myCollisionShape);
			//((CollisionComponent)collisionCompoment).getHitList().add(alch_circle);
			
			spriteComponent.setFilePath("img/diamon.png");
		}
		else if(type.equals(alch_check)) {
			
			elemTag = alch_check;
			elemType = checkStr;
			elemCount = ++numChecks;
			
			CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
			((CollisionComponent)collisionCompoment).setCollisionShape(myCollisionShape);
			//((CollisionComponent)collisionCompoment).getHitList().add(alch_diamon);
			spriteComponent.setFilePath("img/check.png");
		}
		else if(type.equals(alch_arrow))
		{	
			elemTag = alch_arrow;
			elemType = arrowStr;
			elemCount = ++numArrows;
			
			CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
			((CollisionComponent)collisionCompoment).setCollisionShape(myCollisionShape);
			((CollisionComponent)collisionCompoment).getHitList().add(alch_check);
			spriteComponent.setFilePath("img/arrow.png");
		}
	    else if(type.equals(alch_cloud)) {
			
			elemTag = alch_cloud;
			elemType = cloudStr;
			elemCount = ++numClouds;
			
			CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
			((CollisionComponent)collisionCompoment).setCollisionShape(myCollisionShape);
			((CollisionComponent)collisionCompoment).getHitList().add(alch_diamon);
			spriteComponent.setFilePath("img/cloud.png");
		}
		
        else if(type.equals(alch_dbz)) {
			
			elemTag = alch_dbz;
			elemType = dbzStr;
			elemCount = ++numDbz;
			
			CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
			((CollisionComponent)collisionCompoment).setCollisionShape(myCollisionShape);
			//((CollisionComponent)collisionCompoment).getHitList().add(alch_diamon);
			spriteComponent.setFilePath("img/dragonballsuper.png");
		}
		
		((GraphicsComponent)graphicsComponent2).setSprite(spriteComponent);
		
		
		String newId = id + elemType + elemCount;
		GameObject alchGameObject = new GameObject(newId);
		alchGameObject.setTag(elemTag);
		
		alchGameObject.addComponent(graphicsComponent2);
		alchGameObject.addComponent(tranformComponent2);
		alchGameObject.addComponent(spriteComponent);
		alchGameObject.addComponent(mouseControllerComponent2);
		alchGameObject.addComponent(keyControllerComponent2);
		alchGameObject.addComponent(collisionCompoment);
		
		
		this.myGameWorld.addDirtyGameObject(alchGameObject, GameWorld.PlayerLayer);
		
		return alchGameObject;
	}

}
