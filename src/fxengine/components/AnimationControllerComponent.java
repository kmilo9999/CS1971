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
	
	private long myLapseTime = 0;
	private int currentFrame = 0;
	
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
		
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		
		if(!myCurrentAnimationName.isEmpty())
		{
			myLapseTime += nanosSincePreviousTick;	
		}
		else
		{
			myLapseTime = 0;
		}
		
		
		if(myLapseTime > 450000000 && !myCurrentAnimationName.isEmpty())
		{

			AnimationComponent animationComponent = (AnimationComponent) this.myParent
					.getComponent(ComponentContants.animation);
			animationComponent.advanceCurrentFrameAnimation();

			myLapseTime = 0;
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
			keyReleased(e.strValue);
		}
		else if(e.type.equals(EventsConstants.KeyReleased))
		{
			keyPressed(e.strValue);
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
		int keyCode = (int) value.charAt(0) - 97;
		
		if(myCurrentAnimationName == myAnimationStates.get(keyCode))
		{
			return;
		}
		
		AnimationComponent animationComponent = (AnimationComponent) this.myParent
				.getComponent(ComponentContants.animation);
		if(myAnimationStates.containsKey(keyCode)
				&& animationComponent != null
				&& animationComponent.getAnimations().containsKey(myAnimationStates.get(keyCode)))
		{

			myCurrentAnimationName = myAnimationStates.get(keyCode);
			animationComponent.setCurrentAnimation(myCurrentAnimationName);
		}
	}

	private void keyReleased(String value) {
		// TODO Auto-generated method stub
		int keyCode = (int) value.charAt(0) - 97;
		if(!myCurrentAnimationName.isEmpty() && myCurrentAnimationName == myAnimationStates.get(keyCode))
		{
			 //stop animation
			this.myCurrentAnimationName = "";
			AnimationComponent animationComponent = (AnimationComponent) this.myParent
					.getComponent(ComponentContants.animation);
			animationComponent.stopCurrentFrameAnimation();
			this.myLapseTime = 0;
			
		}
		
		
		/*if(myAnimationStates.containsKey(keyCode))
		{
			String animationName = myAnimationStates.get(keyCode);
			AnimationComponent animationComponent = (AnimationComponent) this.myParent
					.getComponent(ComponentContants.animation);
			animationComponent.setCurrentAnimation(animationName);
		}*/
		
	}
	
	public void bindAnimationToKey(int keycode, String animationName)
	{
		if(keycode >=  97 && keycode <= 25 )
		{
			myAnimationStates.put(keycode, animationName);	
		}
	}

}
