package fxengine.components;

import java.util.HashMap;
import java.util.Map;

import fxengine.event.Event;
import fxengine.event.EventsConstants;
import fxengine.system.KeyboardEventSystem;
import javafx.scene.canvas.GraphicsContext;

public class AnimationControllerComponent extends KeyEventComponent{

	private Map<Integer,String> myAnimationStates = new HashMap<Integer, String>();
	private String myCurrentAnimationName = new String();
	
	
	public AnimationControllerComponent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		myCurrentAnimationName = myAnimationStates.get(100);
		AnimationComponent animationComponent = (AnimationComponent) this.myParent
				.getComponent(ComponentContants.animation);
		animationComponent.setCurrentAnimation(myCurrentAnimationName);
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		
		if(keys[22])
		{
			AnimationComponent animationComponent = (AnimationComponent) this.myParent
					.getComponent(ComponentContants.animation);
			myCurrentAnimationName = myAnimationStates.get(22);
			animationComponent.setCurrentAnimation(myCurrentAnimationName);
			animationComponent.playCurrentAnimation();
		}
		
		if(keys[3])
		{
			AnimationComponent animationComponent = (AnimationComponent) this.myParent
					.getComponent(ComponentContants.animation);
			myCurrentAnimationName = myAnimationStates.get(3);
			animationComponent.setCurrentAnimation(myCurrentAnimationName);
			animationComponent.playCurrentAnimation();
		}
		
		if(keys[18])
		{
			AnimationComponent animationComponent = (AnimationComponent) this.myParent
					.getComponent(ComponentContants.animation);
			myCurrentAnimationName = myAnimationStates.get(18);
			animationComponent.setCurrentAnimation(myCurrentAnimationName);
			animationComponent.playCurrentAnimation();
		}
		
		if(keys[0])
		{
			AnimationComponent animationComponent = (AnimationComponent) this.myParent
					.getComponent(ComponentContants.animation);
			myCurrentAnimationName = myAnimationStates.get(0);
			animationComponent.setCurrentAnimation(myCurrentAnimationName);
			animationComponent.playCurrentAnimation();
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void processEvent(Event e)
	{
		super.processEvent(e);
		
		if(e.type.equals(EventsConstants.KeyPressed))
		{
			keyPressed(e.strValue);
		}
		else if(e.type.equals(EventsConstants.KeyReleased))
		{
			keyReleased(e.strValue);
		}
		else if(e.type.equals(EventsConstants.KeyTyped))
		{
			keyTyped(e.strValue);
		}
	
	}

	private void keyTyped(String strValue) {
		// TODO Auto-generated method stub
		
	}

	private void keyPressed(String value) {
		// TODO Auto-generated method stub
	
	
	}

	private void keyReleased(String value) {
		// TODO Auto-generated method stub
	
		if(!myCurrentAnimationName.isEmpty())
		{
			 //stop animation
			this.myCurrentAnimationName = "";
			AnimationComponent animationComponent = (AnimationComponent) this.myParent
					.getComponent(ComponentContants.animation);
			animationComponent.stopCurrentFrameAnimation();
			
			
		}
		
		
	}
	
	public void bindAnimationToKey(int keycode, String animationName)
	{
		if(keycode >=  0 && keycode <= 25 )
		{
			myAnimationStates.put(keycode, animationName);	
		}
	}

}
