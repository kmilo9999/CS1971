package game.wiz;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import fxengine.components.AIMovementComponent;
import fxengine.components.Animation;
import fxengine.components.AnimationComponent;
import fxengine.components.AnimationControllerComponent;
import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;

public class WizAiCharacter extends WizCharacter{

	public WizAiCharacter(String id, String characterName, List<Animation> animations) {
		super(id, characterName, animations);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize()
	{
		super.initialize();
		AnimationControllerComponent animationKeyControllers = (AnimationControllerComponent)ComponentFactory.getInstance().createComponent(ComponentContants.keyAnimationController);
		AnimationComponent animationComponent = (AnimationComponent)this.getComponent(ComponentContants.animation);
		animationComponent.getAnimations().forEach((name,animation)->{
			int keyCode = Integer.parseInt(name);
			animationKeyControllers.bindAnimationToKey(keyCode, name);
		});
		
		Component aiMovementComponent = ComponentFactory.getInstance().getInstance().createComponent(ComponentContants.AIMovement);
		
		
		TransformComponent transform = (TransformComponent)this.getComponent(ComponentContants.transform);
		
		Queue<Vec2d> pathPoints = new LinkedList<Vec2d>();
		pathPoints.add(new Vec2d(transform.getPosition()));
		pathPoints.add(new Vec2d(transform.getPosition().x,transform.getPosition().y + 30));
		pathPoints.add(new Vec2d(transform.getPosition().x,transform.getPosition().y + 90) );
		
		((AIMovementComponent) aiMovementComponent).setPathPoints(pathPoints);
		
		//Component keyControllerComponent = ComponentFactory.getInstance()
		//		.createComponent(ComponentContants.controllerKeyEvents);
		
		this.addComponent(animationKeyControllers);
		//this.addComponent(keyControllerComponent);
		this.addComponent(aiMovementComponent);
	}
}
