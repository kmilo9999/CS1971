package game.nin;

import java.util.ArrayList;
import java.util.List;

import fxengine.collision.CollisionConstants;
import fxengine.collision.CollisionShape;
import fxengine.collision.CollisionShapeFactory;
import fxengine.components.CollisionComponent;
import fxengine.components.Component;
import fxengine.components.PhysicsComponent;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.components.SpriteComponent;
import fxengine.components.TransformComponent;
import fxengine.components.TiledSpriteComponent;
import fxengine.components.Animation;
import fxengine.components.AnimationComponent;
import fxengine.math.Vec2d;
import fxengine.math.Vec2i;
import fxengine.objects.GameObject;

public class NinAnimatedElement extends GameObject{

	
	protected Vec2d originalPos;
	protected String textFilePath;
	protected double restitution;
	protected float mass;
	protected String collisionType = CollisionConstants.AABShape;
	
	public NinAnimatedElement(String id, Vec2d initialPosition, String texture, float mass ,double restitution) 
	{
		super(id);
		// TODO Auto-generated constructor stub
		this.originalPos =  initialPosition;
		this.textFilePath = texture;
		this.restitution = restitution;
		this.mass = mass;
		
	}
	
	public NinAnimatedElement(String id, Vec2d initialPosition, String texture, float mass ,double restitution, String collisionType) 
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
	    // Component spriteComponent = ComponentFactory.getInstance().createComponent(ComponentContants.sprite);
		//	((SpriteComponent)spriteComponent).setFilePath(this.textFilePath);
	
		
		Component spriteAnimation = ComponentFactory.getInstance().createComponent(ComponentContants.tiled_sprite);
		((TiledSpriteComponent)spriteAnimation).setFilePath(this.textFilePath);
		
		//characters
		
				
		Animation animation = new Animation("BlockAnimation",new Vec2d(0,0),new Vec2d(35,32),new Vec2i(1,3));	
		
		
        Component animationComponent = ComponentFactory.getInstance().createComponent(ComponentContants.animation);
		
		((AnimationComponent)animationComponent).setAnimations(animation.getAnimationName(),animation);
		
		((TiledSpriteComponent)spriteAnimation).setFrameSize(animation.getFrameSize());
		((TiledSpriteComponent)spriteAnimation).setNumFrames(animation.getNumFrames());
		((TiledSpriteComponent)spriteAnimation).setFramePosition(animation.getTexturePosition());
		((TiledSpriteComponent)spriteAnimation).setCurrentFrame(0);
		((AnimationComponent)animationComponent).setCurrentAnimation("BlockAnimation");
		((AnimationComponent)animationComponent).playCurrentAnimation();
		
	    Component tranformComponent = ComponentFactory.getInstance().createComponent(ComponentContants.transform);
	    ((TransformComponent) tranformComponent).setPosition(originalPos);
	    Component collisionComponemt = ComponentFactory.getInstance().createComponent(ComponentContants.collision);
		  CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(this.collisionType);
		  ((CollisionComponent) collisionComponemt).setCollisionShape(myCollisionShape);
		  
		  Component physicsComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.physics);
		  ((PhysicsComponent) physicsComponent).setMass(this.mass);
		  ((PhysicsComponent) physicsComponent).setRestitution(this.restitution);
		  ((PhysicsComponent) physicsComponent).setGravityMultiplier(0);
		  
		  this.addComponent(graphicsComponent);
		  this.addComponent(spriteAnimation);
		  this.addComponent(animationComponent);
		  this.addComponent(tranformComponent);
		  this.addComponent(collisionComponemt);
		  this.addComponent(physicsComponent);
		  this.setTag(NinScene.ENEMY);
		  super.initialize();
	}

	@Override
	public void onTick(long nanosSincePreviousTick)
	{
		
	}


}
