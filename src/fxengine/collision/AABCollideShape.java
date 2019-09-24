package fxengine.collision;

import fxengine.math.Vec2d;

public class AABCollideShape extends CollisionShape {
	
	protected Vec2d topLeft;
	protected Vec2d size;

	public AABCollideShape(Vec2d topLeft, Vec2d size) {
		this.topLeft = topLeft;
		this.size = size;
	}
	
	/////
	
	public Vec2d getTopLeft() {
		return topLeft;
	}
	
	public Vec2d getSize() {
		return size;
	}

	@Override
	public boolean collides(CollisionShape o) {
		// TODO Auto-generated method stub
		return o.collidesAABShape(this);
	}

	@Override
	public boolean collidesCircle(CircleCollisionShape c) {
		// TODO Auto-generated method stub
		double clampedValueX = Math.max(topLeft.x, Math.min(c.getCenter().x, topLeft.x + size.x));
		double clampedValueY = Math.max(topLeft.y, Math.min(c.getCenter().y, topLeft.y + size.y));
		
		return c.collidesPoint(new Vec2d(clampedValueX,clampedValueY));
		
	}

	@Override
	public boolean collidesAABShape(AABCollideShape other) {
		// TODO Auto-generated method stub
		
		
		Vec2d aBottomRigth = new Vec2d(topLeft.x + size.x,topLeft.y + size.y );
		Vec2d bBottomRigth = new Vec2d(other.getTopLeft().x + other.getSize().x ,other.getTopLeft().y + other.getSize().y );
		
		if (topLeft.x > bBottomRigth.x   || other.getTopLeft().x > aBottomRigth.x) 
		{
			return false; 
		}
		  
		// If one rectangle is above other 
		if (topLeft.y > bBottomRigth.y || other.getTopLeft().y > aBottomRigth.y)
		{
			//System.out.println("this topLeft: " +topLeft);
			//System.out.println("this bottom right: " +new Vec2d(topLeft).plus(size));
			//System.out.println("other topLeft: " +other.getTopLeft());
			//System.out.println("other bottom right: " +new Vec2d(other.getTopLeft()).plus(other.getSize()));
			
		    return false;
		} 
		  
	
		Interval intervalx1 = getInterval(new Vec2d(1,0) ,topLeft ,size);
		Interval intervalx2 = getInterval(new Vec2d(1,0) ,other.getTopLeft() ,other.getSize());
		
		
		Interval intervaly1 = getInterval(new Vec2d(0, 1) ,topLeft ,size);
		Interval intervaly2 = getInterval(new Vec2d(0, 1) ,other.getTopLeft() ,other.getSize());
		
		return intervalx1.overlap(intervalx2) && intervaly1.overlap(intervaly2);
		
		
	}

	@Override
	public boolean collidesPoint(Vec2d s2) {
		// TODO Auto-generated method stub
		if((topLeft.x <= s2.x && topLeft.x +size.x >= s2.x)
				&& (topLeft.y <= s2.y && topLeft.y +size.x >= s2.y))
		{
			return true;
		}
		return false;
	}
	
	private Interval getInterval(Vec2d axis,Vec2d p1,Vec2d p2)
	{
		double pTopleft = p1.dot(axis);
		Vec2d bottomRight = new Vec2d(p1.x + p2.x ,p1.y + p2.y );
		double pBottomRight = bottomRight.dot(axis);
		
		return new Interval(Math.min(pTopleft, pBottomRight),Math.max(pTopleft, pBottomRight));
	}

	@Override
	public void initialize(Vec2d position, Vec2d size) {
		// TODO Auto-generated method stub
		this.topLeft =position;
		this.size =size;
	}
	
	

}
