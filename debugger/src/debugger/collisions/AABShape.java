package debugger.collisions;

import debugger.support.Vec2f;
import debugger.support.shapes.Shape;

public abstract class AABShape extends Shape {
	
	protected Vec2f topLeft;
	protected Vec2f size;

	public AABShape(Vec2f topLeft, Vec2f size) {
		this.topLeft = topLeft;
		this.size = size;
	}
	
	/////
	
	public Vec2f getTopLeft() {
		return topLeft;
	}
	
	public Vec2f getSize() {
		return size;
	}

}
