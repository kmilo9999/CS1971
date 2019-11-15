package fxengine.collision;


import fxengine.math.Vec2d;
import fxengine.raycasting.Ray;

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
	public boolean isColliding(CollisionShape o) {
		// TODO Auto-generated method stub
		return o.isCollidingCircle(this);
	}

	@Override
	public boolean isCollidingCircle(CircleCollisionShape c) {
		// TODO Auto-generated method stub
		if(c.getCenter().dist2(center) <= (c.getRadius() + radius) * (c.getRadius() + radius)  )
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean isCollidingAAB(AABCollideShape aab) {
		// TODO Auto-generated method stub
		return aab.isCollidingCircle(this);
		
	}
	
	@Override
	public boolean isCollidingPoint(Vec2d s2)
	{
		if(s2.dist2(center) <= radius*radius)
		{
			return true;
		}
		
		return false;
	}

	@Override
	public void update(Vec2d position, Vec2d size) {
		// TODO Auto-generated method stub
		
		center = new Vec2d(position.x +  size.x/2 ,position.y +  size.y/2 );
		radius = size.x/2;
	}

	@Override
	public Vec2d collisionCircle(CircleCollisionShape c) {
		// TODO Auto-generated method stub
		double sumRadi = this.radius + c.radius;
		double distCenter = this.center.dist(c.center);
		
		if(distCenter < sumRadi)
		{
			Vec2d mvtAxis = c.center.minus(this.center);
			
			Vec2d distanceVector1 = c.center.minus(this.center);
			if(mvtAxis.dot(distanceVector1) >= 0) 
			{
				mvtAxis = mvtAxis.reflect();
			}
			
	        return mvtAxis.normalize().smult((sumRadi-distCenter )/2 );
		}	
		
        
        
		return new Vec2d(0);
		
	}

	@Override
	public Vec2d collisionAABS(AABCollideShape c) {
		// TODO Auto-generated method stub
		return c.collisionCircle(this);
	}

	@Override
	public Vec2d colliding(CollisionShape o) {
		// TODO Auto-generated method stub
		return o.collisionCircle(this);
	}

	@Override
	public Vec2d collidingPoint(Vec2d s2) {
		// TODO Auto-generated method stub
		if(s2.dist2(this.center) <= this.radius*this.radius)
		{
			return s2;
		}
		return null;
	}

	@Override
	public double raycast(Ray ray) {
		if(getCenter().dist(ray.getSource()) > getRadius())
		{
			
			// outside circle
			Vec2d l = getCenter().minus(ray.getSource()).projectOnto(ray.getDirection());
			Vec2d pointOnAxis = l.plus(ray.getSource());
			double tca = getCenter().minus(ray.getSource()).dot(ray.getDirection().normalize());
			double d2 = l.dot(l) - ( tca * tca);
			double thc = Math.sqrt(getRadius() * getRadius()  - d2 * d2);
			double t =  tca - thc;
			
			if(tca > 0 && pointOnAxis.dist2(getCenter()) <= getRadius()*getRadius())
			{
				return t;
			}
			
			return 0;
			
			
			
		}
		else
		{
		
			// inside circle
			Vec2d l = getCenter().minus(ray.getSource()).projectOnto(ray.getDirection().normalize());
			Vec2d pointOnAxis = l.plus(ray.getSource());
			/*float d2 = pointOnAxis.minus(s1.center).mag();
			float tca = l.dot(l);
			float thc = (float)Math.sqrt(s1.getRadius() * s1.getRadius()  - d2 * d2);
			float t =  tca + thc;
			//Vec2f point = s2.src.plus(s2.dir.smult(t));*/

			double d2 = pointOnAxis.minus(getCenter()).mag2();
			double t = l.mag() + (float)Math.sqrt(getRadius() * getRadius()  - d2 );
			
			if( pointOnAxis.dist2(getCenter()) <= getRadius() * getRadius())
			{
				return t;
			}
			
			return 0;
		}
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
