package game.nin;

import java.util.List;

import fxengine.collision.CollisionConstants;
import fxengine.collision.CollisionShape;
import fxengine.collision.CollisionShapeFactory;
import fxengine.components.Animation;
import fxengine.components.AnimationComponent;
import fxengine.components.CollisionComponent;
import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.components.TiledSpriteComponent;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;

public class NinCharacter extends GameObject{

	public static String idle = "";
	public static String moveUp = "22";
	public static String moveDown = "18";
	public static String moveLeft = "0";
	public static String moveRight = "3";
	
	protected List<Animation> myAnimations;
	private Vec2d originalPos ;
	
	public NinCharacter(String id, Vec2d initialPosition, List<Animation> animations) {
		super(id);
		// TODO Auto-generated constructor stub
		this.originalPos = initialPosition;
		this.myAnimations = animations;
	}
	
	 @Override
	 public void initialize() {
		 
		 Component graphicsComponent = ComponentFactory.getInstance().createComponent(ComponentContants.graphics);
	     Component spriteAnimation = ComponentFactory.getInstance().createComponent(ComponentContants.tiled_sprite);
			((TiledSpriteComponent)spriteAnimation).setFilePath("img/ramona.png");
	     Component animationComponent = ComponentFactory.getInstance().createComponent(ComponentContants.animation);
	     for(Animation animation:myAnimations)
		 {
			((AnimationComponent)animationComponent).setAnimations(animation.getAnimationName(),animation);	
		 }
	     
	     //set default animation
		 Animation defaultAnimation =((AnimationComponent)animationComponent).getAnimation(idle);
		 ((TiledSpriteComponent)spriteAnimation).setFrameSize(defaultAnimation.getFrameSize());
		 ((TiledSpriteComponent)spriteAnimation).setNumFrames(defaultAnimation.getNumFrames());
		 ((TiledSpriteComponent)spriteAnimation).setFramePosition(defaultAnimation.getTexturePosition());
		 ((TiledSpriteComponent)spriteAnimation).setCurrentFrame(0);
		 
		  Component tranformComponent = ComponentFactory.getInstance().createComponent(ComponentContants.transform);
			//Component keyControllerComponent = ComponentFactory.getInstance()
			//		.createComponent(ComponentContants.controllerKeyEvents);
		  ((TransformComponent) tranformComponent).setPosition(originalPos);
		  Component collisionComponemt = ComponentFactory.getInstance().createComponent(ComponentContants.collision);
		  CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
		  ((CollisionComponent) collisionComponemt).setCollisionShape(myCollisionShape);
		
		  this.addComponent(graphicsComponent);
		  this.addComponent(tranformComponent);
		  //this.addComponent(mouseControllerComponent);
		  //this.addComponent(keyControllerComponent);
			
		  this.addComponent(collisionComponemt);
		  this.addComponent(spriteAnimation);
		  this.addComponent(animationComponent);
			
		  super.initialize();
	 }

}
