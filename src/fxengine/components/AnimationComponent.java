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
	private long myLapseTime = 0;
	private int currentFrame = 0;
	private boolean myDoAnimate = false;
	
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
		
		if(myDoAnimate)
		{
			myLapseTime += nanosSincePreviousTick;
			if(myLapseTime > 450000000 )
			{
				advanceCurrentFrameAnimation();
				myLapseTime = 0;
			}	
		}
		else
		{
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

	public String getCurrentAnimation() {
		return myCurrentAnimationName;
	}

	public void setCurrentAnimation(String currentAnimation) {
		if(this.myCurrentAnimationName != currentAnimation)
		{
			this.myCurrentAnimationName = currentAnimation;
			
			this.needsUpdate = true;	
		}
		
	}

	public Map<String, Animation> getAnimations() {
		return myAnimations;
	}

	public void setAnimations(Map<String, Animation> myAnimations) {
		this.myAnimations = myAnimations;
	}
	
	public Animation getAnimation(String animationName) {
		return myAnimations.get(animationName);
	}

	public void setAnimations(String animationName, Animation animation) {
		this.myAnimations.put(animationName, animation);
	}
	
	public void advanceCurrentFrameAnimation()
	{
		//this.myDoAnimate = true;
		if(this.myCurrentAnimation != null )
		{
			this.myCurrentFrame++;
			SpriteAnimationComponent spriteAnimation = (SpriteAnimationComponent) this.myParent.getComponent(ComponentContants.sprite_animation);
			spriteAnimation.setCurrentFrame(this.myCurrentFrame % this.myCurrentAnimation.getNumFrames().y);	
		}
	}
	
	public void stopCurrentFrameAnimation()
	{
	    this.myDoAnimate = false; 
	    this.myCurrentAnimationName = "";
	    SpriteAnimationComponent spriteAnimation = (SpriteAnimationComponent) this.myParent.getComponent(ComponentContants.sprite_animation);
		spriteAnimation.setCurrentFrame(0);
	}
	
	public void playCurrentAnimation()
	{
		this.myDoAnimate = true;
	}
	
	public int getMyCurrentFrame() {
		return myCurrentFrame;
	}

	public void setMyCurrentFrame(int myCurrentFrame) {
		this.myCurrentFrame = myCurrentFrame;
	}

}
