package game.wiz;

import fxengine.math.Vec2d;

public class Animation {
      
	private int myState;
	private Vec2d myPosition;
	private Vec2d myFrameSize;
	private Vec2d myNumFrames;

    public Animation(int state, Vec2d position, Vec2d frameSize, Vec2d numFrames)
    {
    	  this.myState = state;
    	  this.myPosition = position;
    	  this.myFrameSize = frameSize;
    	  this.myNumFrames = numFrames;
    }

	public Vec2d getPosition() {
		return myPosition;
	}

	public Vec2d getFrameSize() {
		return myFrameSize;
	}

	public Vec2d getNumFrames() {
		return myNumFrames;
	}

	public int getState() {
		return myState;
	}
      
}
