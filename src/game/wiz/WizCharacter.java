package game.wiz;

import java.util.List;

import fxengine.collision.CollisionConstants;
import fxengine.collision.CollisionShape;
import fxengine.collision.CollisionShapeFactory;
import fxengine.components.Animation;
import fxengine.components.CollisionComponent;
import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.components.SpriteAnimationComponent;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;
import fxengine.math.Vec2i;
import fxengine.objects.GameObject;

public abstract class WizCharacter extends GameObject{
  
   public WizCharacter(String id, String characterName) {
		super(id);
		// TODO Auto-generated constructor stub
		myName = characterName;
		
	}

   public enum State
   {
	   idle, walk_left, walk_right, walk_up, walk_down
   }
   
   
   
   private String myName;
   

   private State myCurrentState;
   
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
		
		Component animation = ComponentFactory.getInstance().createComponent(ComponentContants.animation);
	

		Component tranformComponent = ComponentFactory.getInstance().createComponent(ComponentContants.transform);
		Component mouseControllerComponent = ComponentFactory.getInstance()
				.createComponent(ComponentContants.controllerMouseEvents);
		Component keyControllerComponent = ComponentFactory.getInstance()
				.createComponent(ComponentContants.controllerKeyEvents);
		((TransformComponent) tranformComponent).setPosition(new Vec2d(3, 10));
		Component collisionCompomemt = ComponentFactory.getInstance().createComponent(ComponentContants.collision);
		CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
		((CollisionComponent) collisionCompomemt).setCollisionShape(myCollisionShape);
		// ((CollisionComponent)collisionCompomemt).getHitList().add(alch_circle);

		this.addComponent(graphicsComponent);
		this.addComponent(tranformComponent);
		this.addComponent(mouseControllerComponent);
		this.addComponent(keyControllerComponent);
		this.addComponent(collisionCompomemt);
		this.addComponent(spriteAnimation);

		super.initialize();
   }
   
   public State getState()
   {
	   return this.myCurrentState;
   }
   
   public void setState(State state)
   {
	  this.myCurrentState = state;
   }

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}
   
}
