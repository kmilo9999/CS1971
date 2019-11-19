package fxengine.components;

import fxengine.math.Vec2d;
import fxengine.math.Vec2i;


public class Animation {
      
	private Vec2d myTextureFramePostion;
	private Vec2d myFrameSize;
	private Vec2i myNumFrames;
	private String myAnimationName;
	

    public Animation(String name, Vec2d position, Vec2d frameSize, Vec2i numFrames)
    {
    	  this.myTextureFramePostion = position;
    	  this.myFrameSize = frameSize;
    	  this.myNumFrames = numFrames;
    	  this.myAnimationName = name;
    }

	public Vec2d getTexturePosition() {
		return myTextureFramePostion;
	}

	public Vec2d getFrameSize() {
		return myFrameSize;
	}

	public Vec2i getNumFrames() {
		return myNumFrames;
	}

	public String getAnimationName() {
		return myAnimationName;
	}

	public void setAnimationName(String myAnimationName) {
		this.myAnimationName = myAnimationName;
	}

}
