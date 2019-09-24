package fxengine.collision;

import fxengine.math.Vec2d;

public abstract class CollisionShape {

	public abstract boolean collidesCircle(CircleCollisionShape c);
	public abstract boolean collidesAABShape(AABCollideShape aab);
	public abstract boolean collides(CollisionShape o);
	public abstract boolean collidesPoint(Vec2d s2);
	public abstract void initialize(Vec2d position, Vec2d size);
	
}
