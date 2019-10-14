package game.wiz;

import java.util.List;

import fxengine.components.Animation;
import fxengine.components.AnimationComponent;
import fxengine.components.AnimationControllerComponent;
import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.math.Vec2d;

public class WizControllableCharacter extends WizCharacter{

	public WizControllableCharacter(String id, String characterName, Vec2d initPosition,List<Animation> animations ) {
		super(id, characterName,initPosition,animations);
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
		
		Component keyControllerComponent = ComponentFactory.getInstance()
				.createComponent(ComponentContants.controllerKeyEvents);
		
		this.addComponent(animationKeyControllers);
		this.addComponent(keyControllerComponent);
	}

}
