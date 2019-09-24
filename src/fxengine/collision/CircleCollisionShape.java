package fxengine.collision;

import java.util.Vector;

import fxengine.math.Vec2d;

public  class CircleCollisionShape extends CollisionShape {
	
	protected Vec2d center;
	protected double radius;
	
	
	public CircleCollisionShape(Vec2d center, float radius) {
		this.center = center;
		this.radius = radius;
	}
	
	/////
	
	public Vec2d getCenter() {
		return center;
	}
	
	public double getRadius() {
		return radius;
	}

	@Override
	public boolean collides(CollisionShape o) {
		// TODO Auto-generated method stub
		return o.collidesCircle(this);
	}

	@Override
	public boolean collidesCircle(CircleCollisionShape c) {
		// TODO Auto-generated method stub
		if(c.getCenter().dist2(center) <= (c.getRadius() + radius) * (c.getRadius() + radius)  )
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean collidesAABShape(AABCollideShape aab) {
		// TODO Auto-generated method stub
		aab.collidesCircle(this);
		return false;
	}
	
	@Override
	public boolean collidesPoint(Vec2d s2)
	{
		if(s2.dist2(center) <= radius*radius)
		{
			return true;
		}
		
		return false;
	}

	@Override
	public void initialize(Vec2d position, Vec2d size) {
		// TODO Auto-generated method stub
		
		center = new Vec2d(position.x +  size.x/2 ,position.y +  size.y/2 );
		radius = size.x/2;
	}
	
	
}
