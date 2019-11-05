package fxengine.components;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;

import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;

public class AnimationNonControlledComponent extends Component{

	private Map<Integer,String> myAnimationStates = new HashMap<Integer, String>();
	private String myCurrentAnimationName = new String();
	private Vec2d myCurrentDirection; 
	
	public AnimationNonControlledComponent(String name) {
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
		this.myCurrentDirection = new Vec2d(0);
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		if(myCurrentDirection == null || myCurrentDirection.isZero() )
		{
			AnimationComponent animationComponent = (AnimationComponent) this.myParent
					.getComponent(ComponentContants.animation);
			myCurrentAnimationName = myAnimationStates.get(18);
			animationComponent.setCurrentAnimation(myCurrentAnimationName);
			animationComponent.playCurrentAnimation();
		}
		else if(myCurrentDirection.x > myCurrentDirection.y)
		{
			// Move right or left
			if(myCurrentDirection.x > 0)
			{
				AnimationComponent animationComponent = (AnimationComponent) this.myParent
						.getComponent(ComponentContants.animation);
				myCurrentAnimationName = myAnimationStates.get(3);
				animationComponent.setCurrentAnimation(myCurrentAnimationName);
				animationComponent.playCurrentAnimation();
			}else
			{
				AnimationComponent animationComponent = (AnimationComponent) this.myParent
						.getComponent(ComponentContants.animation);
				myCurrentAnimationName = myAnimationStates.get(22);
				animationComponent.setCurrentAnimation(myCurrentAnimationName);
				animationComponent.playCurrentAnimation();
			}
		
		}
		else
		{
			// Move up or down
			if (myCurrentDirection.y > 0) {
				
				AnimationComponent animationComponent = (AnimationComponent) this.myParent
						.getComponent(ComponentContants.animation);
				myCurrentAnimationName = myAnimationStates.get(18);
				animationComponent.setCurrentAnimation(myCurrentAnimationName);
				animationComponent.playCurrentAnimation();
				
			} else {
				
				AnimationComponent animationComponent = (AnimationComponent) this.myParent
						.getComponent(ComponentContants.animation);
				myCurrentAnimationName = myAnimationStates.get(0);
				animationComponent.setCurrentAnimation(myCurrentAnimationName);
				animationComponent.playCurrentAnimation();
			}
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
	
	public void bindAnimationToKey(int keycode, String animationName)
	{
		if(keycode >=  0 && keycode <= 25 )
		{
			myAnimationStates.put(keycode, animationName);	
		}
	}

	public Vec2d getCurrentDirection() {
		return myCurrentDirection;
	}

	public void setCurrentDirection(Vec2d currentDirection) {
		this.myCurrentDirection = currentDirection;
	}

	@Override
	public Element saveState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadState() {
		// TODO Auto-generated method stub
		
	}


}
