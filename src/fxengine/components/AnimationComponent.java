package fxengine.components;

import java.util.ArrayList;
import java.util.List;

import fxengine.UISystem.Layout;
import fxengine.UISystem.UIConstants;
import fxengine.manager.Resource;
import fxengine.manager.ResourceManager;
import fxengine.manager.Resource.ResourceType;
import fxengine.math.Vec2d;

public class AnimationComponent extends SpriteComponent{

	private List<Vec2d> myFrames;
	private Vec2d myFrameSize;
	private Vec2d myNumFrames;
	private Vec2d myFramePosition;
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
  	    if(!isInitialized)
  	    {
  	   // load image and layout 
  			//super.initialize();
  	    	TransformComponent transform = (TransformComponent)this.myParent.getComponent(ComponentContants.transform);
			if(transform != null)
			{
				if(!transform.isInitialized)
				{
					transform.initialize();
				}
				
				if(!this.myFilePath.isEmpty())
				{
					
					if(loadImageSprite())
					{
						myLayout = new Layout(transform.getPosition().x, transform.getPosition().y, this.myFrameSize.x, this.myFrameSize.y, UIConstants.GRAY);
			  			for(int i = 0; i < myNumFrames.x ; i++)
			  			{
			  				double xPos = (i * myFrameSize.x) + myFramePosition.x;
			  				
			  				for(int j = 0 ; j < myNumFrames.y;j++)
			  				{
			  					
			  					double yPos = (j * myFrameSize.y) + myFramePosition.y;
			  					myFrames.add(new Vec2d(xPos,yPos));	
			  					
			  				}
			  			}
			  			
			  			isInitialized = true;

					}
					else
					{
						isInitialized = false;
						return;
					}
					
				}
			}
			
  			
  	    }
		
	}
	
	@Override
	public void update(long nanosSincePreviousTick)
	{
		//Vec2d currentFramePos = myFrames.get(myFrames.size()% myCurrentFrame);
		
		Vec2d framePos =  getPosCurrentFrame();
		mySpriteXCoordinates = framePos.x;
		mySpriteYCoordinates = framePos.y;
		super.update(nanosSincePreviousTick);
	}
	
	@Override
	protected boolean loadImageSprite()
	{
		if(!this.myFilePath.isEmpty())
		{
				
			Resource imageResource = ResourceManager.getIntance().createOrGetResource(myFilePath, ResourceType.Image);
			if(imageResource.isLoaded())
			{
				this.mySourceImage = imageResource.getImage();
				this.myImageWidth = this.myFrameSize.x;
				this.myImageHeight = this.myFrameSize.y;
				return true;
			}
		}
		
		return false;
	}
	
	public Vec2d getFrameSize() {
		return myFrameSize;
	}

	public void setFrameSize(Vec2d frameSize) {
		this.myFrameSize = frameSize;
	}

	public Vec2d getNumFrames() {
		return myNumFrames;
	}

	public void setNumFrames(Vec2d numFrames) {
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

	public Vec2d getFramePosition() {
		return myFramePosition;
	}

	public void setFramePosition(Vec2d myFramePosition) {
		this.myFramePosition = myFramePosition;
	}
	
	public Vec2d getPosCurrentFrame()
	{
		double row = Math.floor(myCurrentFrame / myNumFrames.x);
	    double col = Math.floor(myCurrentFrame % myNumFrames.x);
	    
	    double posx = myFramePosition.x + col * myFrameSize.x; 
	    double posy = myFramePosition.y + row * myFrameSize.y;
		//double posx = myFramePosition.x + (myCurrentFrame * myFrameSize.x) % myNumFrames.x;
		//double posy = myFramePosition.y + (myCurrentFrame * myFrameSize.y)/ myNumFrames.x;
		return new Vec2d(posx,posy);
	}

}
