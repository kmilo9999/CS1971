package debugger.collisions;

import debugger.support.Vec2f;
import debugger.support.interfaces.Week2Reqs;



public final class Week2 extends Week2Reqs {

	// AXIS-ALIGNED BOXES
	
	@Override
	public boolean isColliding(AABShape s1, AABShape s2) {
		Vec2f aBottomRigth = new Vec2f(s1.topLeft.x + s1.size.x,s1.topLeft.y + s1.size.y );
		Vec2f bBottomRigth = new Vec2f(s2.getTopLeft().x + s2.getSize().x ,s2.getTopLeft().y + s2.getSize().y );
		
		if (s1.topLeft.x > bBottomRigth.x   || s2.getTopLeft().x > aBottomRigth.x) 
		{
			return false; 
		}
		  
		if (s1.topLeft.y > bBottomRigth.y || s2.getTopLeft().y > aBottomRigth.y)
		{
		    return false;
		} 
		
		Interval intervalx1 = getInterval(new Vec2f(1,0) ,s1.topLeft ,s1.size);
		Interval intervalx2 = getInterval(new Vec2f(1,0) ,s2.getTopLeft() ,s2.getSize());
		
		
		Interval intervaly1 = getInterval(new Vec2f(0, 1) ,s1.topLeft ,s1.size);
		Interval intervaly2 = getInterval(new Vec2f(0, 1) ,s2.getTopLeft() ,s2.getSize());
		
		return intervalx1.overlap(intervalx2) && intervaly1.overlap(intervaly2);
	}

	@Override
	public boolean isColliding(AABShape s1, CircleShape s2) {
		double clampedValueX = Math.max(s1.topLeft.x, Math.min(s2.getCenter().x, s1.topLeft.x + s1.size.x));
		double clampedValueY = Math.max(s1.topLeft.y, Math.min(s2.getCenter().y, s1.topLeft.y + s1.size.y));
		
		Vec2f point = new Vec2f(clampedValueX,clampedValueY);
		if(point.dist2(s2.center) <= s2.radius*s2.radius)
		{
			return true;
		}
		
		return false;
	}

	@Override
	public boolean isColliding(AABShape s1, Vec2f s2) {
		if((s1.topLeft.x <= s2.x && s1.topLeft.x + s1.size.x >= s2.x)
				&& (s1.topLeft.y <= s2.y && s1.topLeft.y + s1.size.x >= s2.y))
		{
			return true;
		}
		return false;
	}

	// CIRCLES
	
	@Override
	public boolean isColliding(CircleShape s1, AABShape s2) {
		return isColliding(s2, s1);
	}

	@Override
	public boolean isColliding(CircleShape s1, CircleShape s2) {
		if(s2.getCenter().dist2(s1.center) <= (s1.getRadius() + s2.radius) * (s1.getRadius() + s2.radius)  )
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean isColliding(CircleShape s1, Vec2f s2) {
		if(s2.dist2(s1.center) <= s1.radius*s1.radius)
		{
			return true;
		}
		
		return false;
	}

	private Interval getInterval(Vec2f axis,Vec2f p1,Vec2f p2)
	{
		double pTopleft = p1.dot(axis);
		Vec2f bottomRight = new Vec2f(p1.x + p2.x ,p1.y + p2.y );
		double pBottomRight = bottomRight.dot(axis);
		
		return new Interval(Math.min(pTopleft, pBottomRight),Math.max(pTopleft, pBottomRight));
	}
}
