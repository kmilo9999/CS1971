package game.nin;

import java.util.List;

import fxengine.components.Animation;
import fxengine.components.AnimationComponent;
import fxengine.components.AnimationNonControlledComponent;
import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.math.Vec2d;


public class NinAICharacter extends NinCharacter{

	public NinAICharacter(String id, Vec2d initialPosition, List<Animation> animations) {
		super(id, initialPosition, animations);
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
			if(!name.isEmpty())
			{
				int keyCode = Integer.parseInt(name);
				animationNonControlled.bindAnimationToKey(keyCode, name);	
			}

		});
		
		this.setTag(NinScene.ENEMY);
	}
}
