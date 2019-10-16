package game.wiz;

import java.util.List;

import fxengine.components.Animation;
import fxengine.components.AnimationComponent;
import fxengine.components.AnimationNonControlledComponent;
import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
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
		
		AnimationComponent animationComponent = (AnimationComponent)this.getComponent(ComponentContants.animation);
		animationComponent.getAnimations().forEach((name,animation)->{
			int keyCode = Integer.parseInt(name);
			animationNonControlled.bindAnimationToKey(keyCode, name);
		});
		
		this.setTag(WizScene.enemy);
	}
}
