package fxengine.collision;

import fxengine.math.Vec2d;
import fxengine.raycasting.Ray;

public abstract class CollisionShape {

	protected static final double MAXIMUM_RANGE = 1000000.0f;
	
	public abstract boolean isCollidingCircle(CircleCollisionShape c);
	public abstract boolean isCollidingAAB(AABCollideShape aab);
	public abstract boolean isCollidingPolygon(PolygonColliderShape o);
	public abstract boolean isColliding(CollisionShape o);
	public abstract boolean isCollidingPoint(Vec2d s2);
	
	public abstract Vec2d collisionCircle(CircleCollisionShape c);
	public abstract Vec2d collisionAABS(AABCollideShape c);
	public abstract Vec2d collidingPolygon(PolygonColliderShape o);
	public abstract Vec2d colliding(CollisionShape o);
	public abstract Vec2d collidingPoint(Vec2d s2);
	
	public abstract double raycast( Ray ray);
	
	
	public abstract void update(Vec2d position, Vec2d size);
	
}
