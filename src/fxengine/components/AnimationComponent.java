package fxengine.components;

import java.util.ArrayList;
import java.util.List;

import fxengine.math.Vec2d;

public class AnimationComponent extends SpriteComponent{

	private List<Vec2d> myFrames;
	private Vec2d myFrameSize;
	private int myNumFrames;
	private String myAnimationName;
    private int myCurrentFrame = 0;
	

	public AnimationComponent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		myFrames = new ArrayList<Vec2d>();
	}
	
	@Override
	public void initialize() 
	{
  	    // load image and layout 
		super.initialize();
		for(int i = 0; i < myNumFrames; i++)
		{
			double xPos = i * myFrameSize.x;
			double yPos = myFrameSize.y;
			myFrames.add(new Vec2d(xPos,yPos));
			
		}
	
		
	}
	
	@Override
	public void update(long nanosSincePreviousTick)
	{
		Vec2d currentFramePos = myFrames.get(myFrames.size()% myCurrentFrame);
		mySpriteXCoordinates = currentFramePos.x;
		mySpriteYCoordinates = currentFramePos.y;
	}
	
	public Vec2d getFrameSize() {
		return myFrameSize;
	}

	public void setFrameSize(Vec2d frameSize) {
		this.myFrameSize = frameSize;
	}

	public int getNumFrames() {
		return myNumFrames;
	}

	public void setNumFrames(int numFrames) {
		this.myNumFrames = numFrames;
	}

	public String getMyAnimationName() {
		return myAnimationName;
	}

	public void setMyAnimationName(String myAnimationName) {
		this.myAnimationName = myAnimationName;
	}

	public int getCurrentFrame() {
		return myCurrentFrame;
	}

	public void setCurrentFrame(int myCurrentFrame) {
		this.myCurrentFrame = myCurrentFrame;
	}	
	
	public void nextFrame()
	{
		this.myCurrentFrame++;
	}

}
