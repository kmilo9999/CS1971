package fxengine.raycasting;

import fxengine.math.Vec2d;

public class Ray {

	private Vec2d mySource;
	private Vec2d myDirection;
	
	
	public Ray(Vec2d source, Vec2d dir) {
		// TODO Auto-generated constructor stub
		this.mySource = source;
		this.myDirection = dir;
	}


	public Vec2d getSource() {
		return mySource;
	}


	public void setSource(Vec2d source) {
		this.mySource = source;
	}


	public Vec2d getDirection() {
		return myDirection;
	}


	public void setDirection(Vec2d direction) {
		this.myDirection = direction;
	}
	
}
