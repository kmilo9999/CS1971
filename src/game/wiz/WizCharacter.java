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
import fxengine.components.TiledSpriteComponent;
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
   
   
   
   private Vec2d rangeVisionTopLef;
   private Vec2d rangeVisionSize;
  
   private Vec2d originalPos = new Vec2d(20, 20);
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
		
		Component spriteAnimation = ComponentFactory.getInstance().createComponent(ComponentContants.tiled_sprite);
		((TiledSpriteComponent)spriteAnimation).setFilePath("img/charactes_sprite_sheet.png");
		
		
		
		Component animationComponent = ComponentFactory.getInstance().createComponent(ComponentContants.animation);
		
		for(Animation animation:myAnimations)
		{
			((AnimationComponent)animationComponent).setAnimations(animation.getAnimationName(),animation);	
		}
		
		
		//set default animation
		Animation defaultAnimation =((AnimationComponent)animationComponent).getAnimation(moveDown);
		((TiledSpriteComponent)spriteAnimation).setFrameSize(defaultAnimation.getFrameSize());
		((TiledSpriteComponent)spriteAnimation).setNumFrames(defaultAnimation.getNumFrames());
		((TiledSpriteComponent)spriteAnimation).setFramePosition(defaultAnimation.getTexturePosition());
		((TiledSpriteComponent)spriteAnimation).setCurrentFrame(0);
		

	    Component tranformComponent = ComponentFactory.getInstance().createComponent(ComponentContants.transform);
		Component keyControllerComponent = ComponentFactory.getInstance()
				.createComponent(ComponentContants.controllerKeyEvents);
		((TransformComponent) tranformComponent).setPosition(originalPos);
		Component collisionCompomemt = ComponentFactory.getInstance().createComponent(ComponentContants.collision);
		CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
		((CollisionComponent) collisionCompomemt).setCollisionShape(myCollisionShape);
		((CollisionComponent)collisionCompomemt).getHitList().add(WizScene.goal);

		this.addComponent(graphicsComponent);
		this.addComponent(tranformComponent);
		//this.addComponent(mouseControllerComponent);
		this.addComponent(keyControllerComponent);
		
		this.addComponent(collisionCompomemt);
		this.addComponent(spriteAnimation);
		this.addComponent(animationComponent);
		
		super.initialize();
		
		double topLeftx = ((TransformComponent) tranformComponent).getPosition().x;
		double topLefty = ((TransformComponent) tranformComponent).getPosition().y;
		
		rangeVisionTopLef = new Vec2d(topLeftx - 10,topLefty - 10);
		rangeVisionSize  = new Vec2d( 32* 3, 36 * 3);
		
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

	public Vec2d getRangeVisionTopLef() {
		return rangeVisionTopLef;
	}

	public void setRangeVisionTopLef(Vec2d rangeVisionTopLef) {
		this.rangeVisionTopLef = rangeVisionTopLef;
	}

	public Vec2d getRangeVisionSize() {
		return rangeVisionSize;
	}

	public void setRangeVisionSize(Vec2d rangeVisionSize) {
		this.rangeVisionSize = rangeVisionSize;
	}
	
	public void resetCharacterPos()
	{
		((TransformComponent)this.getComponent(ComponentContants.transform)).setPosition(originalPos);
	}
   
}
