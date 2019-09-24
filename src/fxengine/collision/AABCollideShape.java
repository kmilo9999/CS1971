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
		double clampedValueX = Math.max(topLeft.x, Math.min(c.getCenter().x, topLeft.x));
		double clampedValueY = Math.max(topLeft.y, Math.min(c.getCenter().y, topLeft.y));
		
		return c.collidesPoint(new Vec2d(clampedValueX,clampedValueY));
		
	}

	@Override
	public boolean collidesAABShape(AABCollideShape aab) {
		// TODO Auto-generated method stub
		
		if (topLeft.x > aab.getTopLeft().x + aab.getSize().x || aab.getTopLeft().x > topLeft.x + size.x) 
		{
			return false; 
		}
		  
		// If one rectangle is above other 
		if (topLeft.y < aab.getTopLeft().y + aab.getSize().y || aab.getTopLeft().y < topLeft.y + size.y)
		{
			System.out.println(topLeft);
			System.out.println(new Vec2d(topLeft).plus(size));
			System.out.println(aab.getTopLeft());
			System.out.println(new Vec2d(aab.getTopLeft()).plus(aab.getSize()));
			
		    return false;
		} 
		  
	
		Interval intervalx1 = getInterval(new Vec2d(1,0) ,topLeft ,size);
		Interval intervalx2 = getInterval(new Vec2d(1,0) ,aab.getTopLeft() ,aab.getSize());
		
		
		Interval intervaly1 = getInterval(new Vec2d(0, 1) ,topLeft ,size);
		Interval intervaly2 = getInterval(new Vec2d(0, 1) ,aab.getTopLeft() ,aab.getSize());
		
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
