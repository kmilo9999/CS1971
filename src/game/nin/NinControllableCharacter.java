package game.nin;

import java.util.List;

import fxengine.components.Animation;
import fxengine.components.AnimationComponent;
import fxengine.components.AnimationControllerComponent;
import fxengine.components.AnimationNonControlledComponent;
import fxengine.components.CollisionComponent;
import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.components.PhysicsComponent;
import fxengine.math.Vec2d;

public class NinControllableCharacter extends NinCharacter{

	public NinControllableCharacter(String id, Vec2d initialPosition, List<Animation> animations) {
		super(id, initialPosition, animations);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initialize() {
		
		
		//AnimationNonControlledComponent animationNonControlled = (AnimationNonControlledComponent)ComponentFactory.getInstance().createComponent(ComponentContants.AutoAnimation);
		//Component aiMovementComponent = ComponentFactory.getInstance().createComponent(ComponentContants.AIMovement);
		//this.addComponent(animationNonControlled);
		//this.addComponent(aiMovementComponent);
		
		Component physicsComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.physics);
		((PhysicsComponent) physicsComponent).setRestitution(10);
		
		this.addComponent(physicsComponent);
		
		super.initialize();
		AnimationControllerComponent animationKeyControllers = (AnimationControllerComponent)ComponentFactory.getInstance().createComponent(ComponentContants.keyAnimationController);
		AnimationComponent animationComponent = (AnimationComponent)this.getComponent(ComponentContants.animation);
		
		animationComponent.getAnimations().forEach((name,animation)->{
			if(!name.isEmpty())
			{
				int keyCode = Integer.parseInt(name);
				animationKeyControllers.bindAnimationToKey(keyCode, name);	
			}
			
		});
		
	
		
		Component keyControllerComponent = ComponentFactory.getInstance()
				.createComponent(ComponentContants.controllerKeyEvents);
		
		this.addComponent(animationKeyControllers);
		this.addComponent(keyControllerComponent);
		
		CollisionComponent collisionCompomemt = (CollisionComponent)this.getComponent(ComponentContants.collision);
		((CollisionComponent)collisionCompomemt).getHitList().add(NinScene.ENEMY);
		//((CollisionComponent)collisionCompomemt).getHitList().add(WizScene.ligth);
		//((CollisionComponent)collisionCompomemt).getHitList().add(WizScene.enemy);
	   
	}

}
