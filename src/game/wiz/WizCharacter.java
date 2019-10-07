package game.wiz;

import java.util.List;
import java.util.Map;

import fxengine.collision.CollisionConstants;
import fxengine.collision.CollisionShape;
import fxengine.collision.CollisionShapeFactory;
import fxengine.components.Animation;
import fxengine.components.AnimationComponent;
import fxengine.components.AnimationControllerComponent;
import fxengine.components.CollisionComponent;
import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.components.SpriteAnimationComponent;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;
import fxengine.math.Vec2i;
import fxengine.objects.GameObject;
import fxengine.system.AnimationSystem;

public abstract class WizCharacter extends GameObject{

	
   public static String moveUp = "22";
   public static String moveDown = "18";
   public static String moveLeft = "0";
   public static String moveRight = "3";
	
	
   protected List<Animation> myAnimations;
   
	
   public WizCharacter(String id, String characterName, List<Animation> animations) {
		super(id);
		// TODO Auto-generated constructor stub
		this.myName = characterName;
		this.myAnimations =  animations;
	}

   private String myName;
      
   @Override
   public void initialize()
   {
		Component graphicsComponent = ComponentFactory.getInstance().createComponent(ComponentContants.graphics);
		
		Component spriteAnimation = ComponentFactory.getInstance().createComponent(ComponentContants.sprite_animation);
		((SpriteAnimationComponent)spriteAnimation).setFilePath("img/charactes_sprite_sheet.png");
		
		//animation.setFrameSize(new Vec2d(32, 36));
		//animation.setNumFrames(new Vec2i(1, 3));
		//animation.setFramePosition(new Vec2d(0, 36));
		//animation.setCurrentFrame(currentFrame);
		
		Component animationComponent = ComponentFactory.getInstance().createComponent(ComponentContants.animation);
		
		for(Animation animation:myAnimations)
		{
			((AnimationComponent)animationComponent).setAnimations(animation.getAnimationName(),animation);	
		}
		
		//set default animation
		Animation defaultAnimation =((AnimationComponent)animationComponent).getAnimation(moveDown);
		((SpriteAnimationComponent)spriteAnimation).setFrameSize(defaultAnimation.getFrameSize());
		((SpriteAnimationComponent)spriteAnimation).setNumFrames(defaultAnimation.getNumFrames());
		((SpriteAnimationComponent)spriteAnimation).setFramePosition(defaultAnimation.getTexturePosition());
		((SpriteAnimationComponent)spriteAnimation).setCurrentFrame(0);
		

		Component tranformComponent = ComponentFactory.getInstance().createComponent(ComponentContants.transform);
		//Component mouseControllerComponent = ComponentFactory.getInstance()
		//		.createComponent(ComponentContants.controllerMouseEvents);
		Component keyControllerComponent = ComponentFactory.getInstance()
				.createComponent(ComponentContants.controllerKeyEvents);
		((TransformComponent) tranformComponent).setPosition(new Vec2d(3, 10));
		Component collisionCompomemt = ComponentFactory.getInstance().createComponent(ComponentContants.collision);
		CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
		((CollisionComponent) collisionCompomemt).setCollisionShape(myCollisionShape);
		// ((CollisionComponent)collisionCompomemt).getHitList().add(alch_circle);

		this.addComponent(graphicsComponent);
		this.addComponent(tranformComponent);
		//this.addComponent(mouseControllerComponent);
		this.addComponent(keyControllerComponent);
		
		this.addComponent(collisionCompomemt);
		this.addComponent(spriteAnimation);
		this.addComponent(animationComponent);
		
		super.initialize();
   }

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public List<Animation> getAnimations() {
		return myAnimations;
	}

	public void setAnimations(List<Animation> myAnimations) {
		this.myAnimations = myAnimations;
	}
   
}
