package fxengine.components;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;

public class AnimationComponent extends Component{

	
	private Map<String,Animation> myAnimations = new HashMap<String, Animation>();
	private String myCurrentAnimationName;
	private Animation myCurrentAnimation;
	private boolean needsUpdate = false;
	private int myCurrentFrame = 0 ;
	
	public AnimationComponent(String name) {
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
		if(this.needsUpdate)
		{
			SpriteAnimationComponent spriteAnimation = (SpriteAnimationComponent) this.myParent.getComponent(ComponentContants.sprite_animation);
			if(spriteAnimation != null)
			{
				myCurrentAnimation = myAnimations.get(this.myCurrentAnimationName);
				spriteAnimation.setCurrentFrame(0);
				spriteAnimation.setFramePosition(myCurrentAnimation.getTexturePosition());
				spriteAnimation.setFrameSize(myCurrentAnimation.getFrameSize());
				spriteAnimation.setNumFrames(myCurrentAnimation.getNumFrames());
			}	
			
			this.needsUpdate = false;
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

	public String getCurrentAnimation() {
		return myCurrentAnimationName;
	}

	public void setCurrentAnimation(String currentAnimation) {
		this.myCurrentAnimationName = currentAnimation;
		this.needsUpdate = true;
	}

	public Map<String, Animation> getAnimations() {
		return myAnimations;
	}

	public void setAnimations(Map<String, Animation> myAnimations) {
		this.myAnimations = myAnimations;
	}
	
	public void advanceCurrentFrameAnimation()
	{
		SpriteAnimationComponent spriteAnimation = (SpriteAnimationComponent) this.myParent.getComponent(ComponentContants.sprite_animation);
		if(this.myCurrentAnimation != null && spriteAnimation != null)
		{
			this.myCurrentFrame++;
			spriteAnimation.setCurrentFrame(this.myCurrentFrame % this.myCurrentAnimation.getNumFrames().y);	
		}
	}
	
	public void stopCurrentFrameAnimation()
	{
		SpriteAnimationComponent spriteAnimation = (SpriteAnimationComponent) this.myParent.getComponent(ComponentContants.sprite_animation);
		if(this.myCurrentAnimation != null && spriteAnimation != null)
		{
			this.myCurrentFrame = spriteAnimation.getCurrentFrame();
		}
	}
	
	public int getMyCurrentFrame() {
		return myCurrentFrame;
	}

	public void setMyCurrentFrame(int myCurrentFrame) {
		this.myCurrentFrame = myCurrentFrame;
	}

}
