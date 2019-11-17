package fxengine.particleeffects;

import fxengine.collision.CollisionConstants;
import fxengine.collision.CollisionShape;
import fxengine.collision.CollisionShapeFactory;
import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.components.CollisionComponent;
import fxengine.components.PhysicsComponent;
import fxengine.components.SpriteComponent;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;

public class Particle extends GameObject{

	public static String PARTICLE ="particle";
	
	private String myTextFilePath;
	private Vec2d myOriginalPos;
	
	private float myMass;
	private double myRestitution;
	
	private int myTimeToLive;
	private long myCurrentTimeLife = 0;
    private boolean myInitialImpusle = false;
    private Vec2d myInitialDirection;

	public Particle(String id, String textFilePath, Vec2d initialPos,
			float mass, float restitution,int timeToLive, Vec2d intialDirection) {
		super(id);
		
		this.myTextFilePath = textFilePath;
		this.myMass = mass;
		this.myRestitution = restitution;
		this.myTimeToLive = timeToLive;
		this.myInitialDirection = intialDirection;
		this.myOriginalPos = initialPos;
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void initialize()
	{
		Component graphicsComponent = ComponentFactory.getInstance().createComponent(ComponentContants.graphics);
	     Component spriteComponent = ComponentFactory.getInstance().createComponent(ComponentContants.sprite);
			((SpriteComponent)spriteComponent).setFilePath(this.myTextFilePath);
			
	    Component tranformComponent = ComponentFactory.getInstance().createComponent(ComponentContants.transform);
	    ((TransformComponent) tranformComponent).setPosition(this.myOriginalPos);
	    Component collisionComponemt = ComponentFactory.getInstance().createComponent(ComponentContants.collision);
		  CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
		  ((CollisionComponent) collisionComponemt).setCollisionShape(myCollisionShape);
		  
		  Component physicsComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.physics);
		  ((PhysicsComponent) physicsComponent).setMass(this.myMass);
		  ((PhysicsComponent) physicsComponent).setRestitution(this.myRestitution);
		 
		  
		  this.addComponent(graphicsComponent);
		  this.addComponent(spriteComponent);
		  this.addComponent(tranformComponent);
		  this.addComponent(collisionComponemt);
		  this.addComponent(physicsComponent);
		  this.setTag(PARTICLE);
		  super.initialize();
	}
	
	@Override
	public void onTick(long nanosSincePreviousTick)
	{
		
		
		if(myTimeToLive > 0)
		{
			if(!myInitialImpusle)
			{
				 ((PhysicsComponent)this.getComponent(ComponentContants.physics)).applyImpulse(this.myInitialDirection);
				 myInitialImpusle = true;
			}
			
			myCurrentTimeLife+=nanosSincePreviousTick;
		    if(myCurrentTimeLife >1000000000)
		    {
		    	myTimeToLive--;
		    	myCurrentTimeLife = 0;
		    }
		}
		
	}


	public int getTimeToLive() {
		return myTimeToLive;
	}


	public void setTimeToLive(int timeToLive) {
		this.myTimeToLive = timeToLive;
	}


	public Vec2d getOriginalPos() {
		return myOriginalPos;
	}


	public void setOriginalPos(Vec2d originalPos) {
		this.myOriginalPos = originalPos;
	}
	
	
}
