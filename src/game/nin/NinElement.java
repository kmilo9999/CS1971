package game.nin;

import fxengine.collision.CollisionConstants;
import fxengine.collision.CollisionShape;
import fxengine.collision.CollisionShapeFactory;
import fxengine.components.CollisionComponent;
import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.components.PhysicsComponent;
import fxengine.components.SpriteComponent;
import fxengine.components.TiledSpriteComponent;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;

public class NinElement extends GameObject{

	
	private Vec2d originalPos;
	private String textFilePath;
	private double restitution;
	private float mass;
	private String collisionType = CollisionConstants.AABShape;
	
	public NinElement(String id, Vec2d initialPosition, String texture, float mass ,double restitution) 
	{
		super(id);
		// TODO Auto-generated constructor stub
		this.originalPos =  initialPosition;
		this.textFilePath = texture;
		this.restitution = restitution;
		this.mass = mass;
		
	}
	
	public NinElement(String id, Vec2d initialPosition, String texture, float mass ,double restitution, String collisionType) 
	{
		super(id);
		this.originalPos =  initialPosition;
		this.textFilePath = texture;
		this.restitution = restitution;
		this.mass = mass;
		this.collisionType = collisionType;
	}

	@Override
	public void initialize() {
		
		Component graphicsComponent = ComponentFactory.getInstance().createComponent(ComponentContants.graphics);
	     Component spriteComponent = ComponentFactory.getInstance().createComponent(ComponentContants.sprite);
			((SpriteComponent)spriteComponent).setFilePath(this.textFilePath);
			
	    Component tranformComponent = ComponentFactory.getInstance().createComponent(ComponentContants.transform);
	    ((TransformComponent) tranformComponent).setPosition(originalPos);
	    Component collisionComponemt = ComponentFactory.getInstance().createComponent(ComponentContants.collision);
		  CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(this.collisionType);
		  ((CollisionComponent) collisionComponemt).setCollisionShape(myCollisionShape);
		  
		  Component physicsComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.physics);
		  ((PhysicsComponent) physicsComponent).setMass(this.mass);
		  ((PhysicsComponent) physicsComponent).setRestitution(this.restitution);
		 
		  
		  this.addComponent(graphicsComponent);
		  this.addComponent(spriteComponent);
		  this.addComponent(tranformComponent);
		  this.addComponent(collisionComponemt);
		  this.addComponent(physicsComponent);
		  this.setTag(NinScene.ENEMY);
		  super.initialize();
	}

}
