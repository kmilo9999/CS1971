package fxengine.collision;

import fxengine.math.Vec2d;

public abstract class CollisionShape {

	public abstract boolean isCollidingCircle(CircleCollisionShape c);
	public abstract boolean isCollidingAAB(AABCollideShape aab);
	public abstract boolean isColliding(CollisionShape o);
	public abstract boolean isCollidingPoint(Vec2d s2);
	
	public abstract Vec2d collisionCircle(CircleCollisionShape c);
	public abstract Vec2d collisionAABS(AABCollideShape c);
	public abstract Vec2d colliding(CollisionShape o);
	public abstract Vec2d collidingPoint(Vec2d s2);
	
	public abstract void update(Vec2d position, Vec2d size);
	
}
