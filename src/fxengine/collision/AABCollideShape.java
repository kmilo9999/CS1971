package fxengine.collision;

import fxengine.math.Vec2d;

public class AABCollideShape extends CollisionShape {
	
	protected Vec2d myTopLeft;
	protected Vec2d mySize;

	public AABCollideShape(Vec2d topLeft, Vec2d size) {
		this.myTopLeft = topLeft;
		this.mySize = size;
	}
	
	/////
	
	public Vec2d getTopLeft() {
		return myTopLeft;
	}
	
	public Vec2d getSize() {
		return mySize;
	}

	@Override
	public boolean collides(CollisionShape o) {
		// TODO Auto-generated method stub
		return o.collidesAABShape(this);
	}

	@Override
	public boolean collidesCircle(CircleCollisionShape c) {
		// TODO Auto-generated method stub
		double clampedValueX = Math.max(myTopLeft.x, Math.min(c.getCenter().x, myTopLeft.x + mySize.x));
		double clampedValueY = Math.max(myTopLeft.y, Math.min(c.getCenter().y, myTopLeft.y + mySize.y));
		
		return c.collidesPoint(new Vec2d(clampedValueX,clampedValueY));
		
	}

	@Override
	public boolean collidesAABShape(AABCollideShape other) {
		// TODO Auto-generated method stub
		
		
		Vec2d aBottomRigth = new Vec2d(myTopLeft.x + mySize.x,myTopLeft.y + mySize.y );
		Vec2d bBottomRigth = new Vec2d(other.getTopLeft().x + other.getSize().x ,other.getTopLeft().y + other.getSize().y );
		
		if (myTopLeft.x > bBottomRigth.x   || other.getTopLeft().x > aBottomRigth.x) 
		{
			return false; 
		}
		  
		if (myTopLeft.y > bBottomRigth.y || other.getTopLeft().y > aBottomRigth.y)
		{
		    return false;
		} 
		  
	
		Interval intervalx1 = getInterval(new Vec2d(1,0) ,myTopLeft ,mySize);
		Interval intervalx2 = getInterval(new Vec2d(1,0) ,other.getTopLeft() ,other.getSize());
		
		
		Interval intervaly1 = getInterval(new Vec2d(0, 1) ,myTopLeft ,mySize);
		Interval intervaly2 = getInterval(new Vec2d(0, 1) ,other.getTopLeft() ,other.getSize());
		
		return intervalx1.overlap(intervalx2) && intervaly1.overlap(intervaly2);
		
		
	}

	@Override
	public boolean collidesPoint(Vec2d s2) {
		// TODO Auto-generated method stub
		if((myTopLeft.x <= s2.x && myTopLeft.x +mySize.x >= s2.x)
				&& (myTopLeft.y <= s2.y && myTopLeft.y +mySize.x >= s2.y))
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
	public void update(Vec2d position, Vec2d size) {
		// TODO Auto-generated method stub
		this.myTopLeft =position;
		this.mySize =size;
	}

	public void setTopLeft(Vec2d topLeft) {
		this.myTopLeft = topLeft;
	}

	public void setSize(Vec2d size) {
		this.mySize = size;
	}
	
	

}
