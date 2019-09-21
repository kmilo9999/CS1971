package fxengine.system;

import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;

public class TransformSystem extends BaseGameSystem{

	private Vec2d myPosition = new Vec2d(0);
	private Vec2d myRotation = new Vec2d(0);
	private Vec2d myScale= new Vec2d(0);
	
	

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		myPosition = new Vec2d(0);
		myRotation = new Vec2d(0);
		myScale= new Vec2d(0);
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void draw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub
		
	}

	
	public Vec2d getPosition() {
		return myPosition;
	}

	public void setPosition(Vec2d position) {
		this.myPosition = position;
	}

	public Vec2d getRotation() {
		return myRotation;
	}

	public void setRotation(Vec2d rotation) {
		this.myRotation = rotation;
	}

	public Vec2d getScale() {
		return myScale;
	}

	public void setScale(Vec2d scale) {
		this.myScale = scale;
	}

	
}
