package fxengine.collision;

import fxengine.math.Vec2d;

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
	public boolean collides(CollisionShape o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean collidesCircle(CircleCollisionShape c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean collidesAABShape(AABCollideShape aab) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean collidesPoint(Vec2d s2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void initialize(Vec2d position, Vec2d size) {
		// TODO Auto-generated method stub
		
	}
	
}
