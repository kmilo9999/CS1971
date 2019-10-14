package game.wiz;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import fxengine.components.AIMovementComponent;
import fxengine.components.Animation;
import fxengine.components.AnimationComponent;
import fxengine.components.AnimationControllerComponent;
import fxengine.components.AnimationNonControlledComponent;
import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;

public class WizAiCharacter extends WizCharacter{

	public WizAiCharacter(String id, String characterName, Vec2d initPosition, List<Animation> animations) {
		super(id, characterName,initPosition, animations);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize()
	{
		AnimationNonControlledComponent animationNonControlled = (AnimationNonControlledComponent)ComponentFactory.getInstance().createComponent(ComponentContants.AutoAnimation);
		Component aiMovementComponent = ComponentFactory.getInstance().createComponent(ComponentContants.AIMovement);
		this.addComponent(animationNonControlled);
		this.addComponent(aiMovementComponent);
		
		super.initialize();
		
		TransformComponent transform = (TransformComponent)this.getComponent(ComponentContants.transform);
		
		Queue<Vec2d> pathPoints = new LinkedList<Vec2d>();
		pathPoints.add(new Vec2d(transform.getPosition()));
		pathPoints.add(new Vec2d(transform.getPosition().x,transform.getPosition().y + 36));
		pathPoints.add(new Vec2d(transform.getPosition().x,transform.getPosition().y + 72) );
		pathPoints.add(new Vec2d(transform.getPosition().x + 64 ,transform.getPosition().y + 72) );
		
		((AIMovementComponent) aiMovementComponent).setPathPoints(pathPoints);
		
		//Component keyControllerComponent = ComponentFactory.getInstance()
		//		.createComponent(ComponentContants.controllerKeyEvents);
		
		//this.addComponent(animationNonControlled);
		//this.addComponent(keyControllerComponent);
		//this.addComponent(aiMovementComponent);
		AnimationComponent animationComponent = (AnimationComponent)this.getComponent(ComponentContants.animation);
		animationComponent.getAnimations().forEach((name,animation)->{
			int keyCode = Integer.parseInt(name);
			animationNonControlled.bindAnimationToKey(keyCode, name);
		});
	}
}
