package fxengine.components;

import fxengine.math.Vec2d;
import fxengine.math.Vec2i;
import game.wiz.WizCharacter;
import game.wiz.WizCharacter.State;

public class Animation {
      
	private Vec2d myTextureFramePostion;
	private Vec2d myFrameSize;
	private Vec2i myNumFrames;
	

    public Animation(State state, Vec2d position, Vec2d frameSize, Vec2i numFrames)
    {
    	  this.myTextureFramePostion = position;
    	  this.myFrameSize = frameSize;
    	  this.myNumFrames = numFrames;
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

}
