package fxengine.collision;

import fxengine.math.Vec2d;
import fxengine.raycasting.Ray;

public  class PolygonShape extends CollisionShape {
	
	protected Vec2d[] points;
	
	public PolygonShape(Vec2d ... points) {
		this.points = points;
	}
	
	public int getNumPoints() {
		return points.length;
	}
	
	public Vec2d getPoint(int i) {
		return points[i];
	}

	@Override
	public boolean isColliding(CollisionShape o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCollidingCircle(CircleCollisionShape c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCollidingAAB(AABCollideShape aab) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCollidingPoint(Vec2d s2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(Vec2d position, Vec2d size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vec2d collisionCircle(CircleCollisionShape c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vec2d collisionAABS(AABCollideShape c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vec2d colliding(CollisionShape o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vec2d collidingPoint(Vec2d s2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double raycast(Ray ray) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isCollidingPolygon(PolygonColliderShape o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vec2d collidingPolygon(PolygonColliderShape o) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
