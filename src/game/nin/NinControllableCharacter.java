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

public class NinControllableCharacter extends NinElement{

	public NinControllableCharacter(String id, Vec2d initialPosition,String filePath, float mass, double restitution) {
		super(id, initialPosition,  filePath,  mass , restitution);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initialize() {
		
		super.initialize();
		//AnimationNonControlledComponent animationNonControlled = (AnimationNonControlledComponent)ComponentFactory.getInstance().createComponent(ComponentContants.AutoAnimation);
		//Component aiMovementComponent = ComponentFactory.getInstance().createComponent(ComponentContants.AIMovement);
		//this.addComponent(animationNonControlled);
		//this.addComponent(aiMovementComponent);
		
		/*Component physicsComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.physics);
		((PhysicsComponent) physicsComponent).setRestitution(0.38);
		((PhysicsComponent) physicsComponent).setMass(1.f);
		
		this.addComponent(physicsComponent);*/
		
	
		this.setTag("character");
		Component keyControllerComponent = ComponentFactory.getInstance()
				.createComponent(ComponentContants.controllerKeyEvents);
		
		this.addComponent(keyControllerComponent);
		
		CollisionComponent collisionCompomemt = (CollisionComponent)this.getComponent(ComponentContants.collision);
		((CollisionComponent)collisionCompomemt).getHitList().add(NinScene.ENEMY);
		//((CollisionComponent)collisionCompomemt).getHitList().add(WizScene.ligth);
		//((CollisionComponent)collisionCompomemt).getHitList().add(WizScene.enemy);
	   
	}

}
