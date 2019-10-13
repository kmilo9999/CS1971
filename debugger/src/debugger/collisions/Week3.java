package debugger.collisions;

import debugger.support.Vec2f;
import debugger.support.interfaces.Week3Reqs;

public final class Week3 extends Week3Reqs {

	// AXIS-ALIGNED BOXES
	
	@Override
	public Vec2f collision(AABShape s1, AABShape s2) {
		Vec2f aBottomRigth = new Vec2f(s1.topLeft.x + s1.size.x,s1.topLeft.y + s1.size.y );
		Vec2f bBottomRigth = new Vec2f(s2.getTopLeft().x + s2.getSize().x ,s2.getTopLeft().y + s2.getSize().y );
		
		if (s1.topLeft.x > bBottomRigth.x   || s2.getTopLeft().x > aBottomRigth.x) 
		{
			return new Vec2f(0); 
		}
		  
		if (s1.topLeft.y > bBottomRigth.y || s2.getTopLeft().y > aBottomRigth.y)
		{
		    return new Vec2f(0);
		} 
		
		Interval intervalx1 = getInterval(new Vec2f(1,0) ,s1.topLeft ,s1.size);
		Interval intervalx2 = getInterval(new Vec2f(1,0) ,s2.getTopLeft() ,s2.getSize());
		
		
		Interval intervaly1 = getInterval(new Vec2f(0, 1) ,s1.topLeft ,s1.size);
		Interval intervaly2 = getInterval(new Vec2f(0, 1) ,s2.getTopLeft() ,s2.getSize());
		
		if( intervalx1.overlap(intervalx2) && intervaly1.overlap(intervaly2))
		{
			float shortest = Math.abs((s1.topLeft.y + s1.size.y) - (s2.topLeft.y));
			Vec2f mvtAxis = new Vec2f(0,-1);
			if(shortest > Math.abs((s1.topLeft.y) - (s2.topLeft.y + s2.size.y)))
			{
				shortest = Math.abs((s1.topLeft.y) - (s2.topLeft.y + s2.size.y));
				mvtAxis = new Vec2f(0,1);
			}
			if(shortest > Math.abs((s1.topLeft.x + s1.size.x) - (s2.topLeft.x)))
			{
				shortest = (s1.topLeft.x + s1.size.x) - (s2.topLeft.x);
				mvtAxis = new Vec2f(-1,0);
			}
			if(shortest > Math.abs((s1.topLeft.x) - (s2.topLeft.x + s2.size.x)))
			{
				shortest = Math.abs((s1.topLeft.x) - (s2.topLeft.x + s2.size.x));
				mvtAxis = new Vec2f(1,0);
			}
			
			return mvtAxis.smult(shortest);
			
		}
		else
		{
			return new Vec2f(0);	
		}
		
		
	}

	@Override
	public Vec2f collision(AABShape s1, CircleShape s2) {
		
		
		double clampedValueX = Math.max(s1.topLeft.x, Math.min(s2.getCenter().x, s1.topLeft.x + s1.size.x));
		double clampedValueY = Math.max(s1.topLeft.y, Math.min(s2.getCenter().y, s1.topLeft.y + s1.size.y));
		
		Vec2f point = new Vec2f(clampedValueX,clampedValueY);
		if(point.dist2(s2.center) <= s2.radius*s2.radius)
		{
			if( (s2.center.x >= s1.topLeft.x && s2.center.x <= s1.topLeft.x + s1.size.x )
					&& (s2.center.y >= s1.topLeft.y && s2.center.y <= s1.topLeft.y + s1.size.y ) )
			{
				
				Vec2f closesPoint = s1.topLeft;
				
				if( closesPoint.dist(s2.center) > new Vec2f(s1.topLeft.x,s1.topLeft.x + s1.size.x).dist(s2.center))
				{
					closesPoint = new Vec2f(s1.topLeft.x,s1.topLeft.x + s1.size.x); 
				}
				
				
				if( closesPoint.dist(s2.center) > new Vec2f(s1.topLeft.x,s1.topLeft.y + s1.size.y).dist(s2.center))
				{
					closesPoint = new Vec2f(s1.topLeft.x,s1.topLeft.y + s1.size.y);
				}
				
				if( closesPoint.dist(s2.center) > new Vec2f(s1.topLeft.x  + s1.size.x ,s1.topLeft.y + s1.size.y).dist(s2.center))
				{
					closesPoint = new Vec2f(s1.topLeft.x  + s1.size.x ,s1.topLeft.y + s1.size.y);
				}
				
				
				float lenght = s2.radius + s2.center.dist(closesPoint);
				Vec2f mvtAxis = new Vec2f(0,1);
				return mvtAxis.smult( lenght /2);
			}
		
			
			
			float lenght = s2.radius + s2.center.dist(point);
			Vec2f aabCenter = new Vec2f(s1.topLeft.x + (s1.size.x /2),s1.topLeft.y + (s1.size.y /2));
			Vec2f mvtAxis =  s2.center.minus(aabCenter);
			
			
			Vec2f distanceVector1 = s2.center.minus(aabCenter);
			if(mvtAxis.dot(distanceVector1) >= 0) 
			{
				mvtAxis = mvtAxis.reflect();
			}
			
	        return mvtAxis.normalize().smult(lenght);

	
		}
		
				
		return new Vec2f(0);
	}

	@Override
	public Vec2f collision(AABShape s1, Vec2f s2) {
		if((s1.topLeft.x <= s2.x && s1.topLeft.x + s1.size.x >= s2.x)
				&& (s1.topLeft.y <= s2.y && s1.topLeft.y + s1.size.x >= s2.y))
		{
			return s2;
		}
		return null;
	}
	
	// CIRCLES

	@Override
	public Vec2f collision(CircleShape s1, AABShape s2) {
		Vec2f f = collision(s2, s1);
		return f == null ? null : f.reflect();
	}

	@Override
	public Vec2f collision(CircleShape s1, CircleShape s2) {
		
		float sumRadi = s1.radius + s2.radius;
		float distCenter = s1.center.dist(s2.center);
		
		if(distCenter < sumRadi)
		{
			Vec2f mvtAxis = s2.center.minus(s1.center);
			
			Vec2f distanceVector1 = s2.center.minus(s1.center);
			if(mvtAxis.dot(distanceVector1) >= 0) 
			{
				mvtAxis = mvtAxis.reflect();
			}
			
	        return mvtAxis.normalize().smult(sumRadi-distCenter );
		}	
		
        
        
		return new Vec2f(0);
	}

	@Override
	public Vec2f collision(CircleShape s1, Vec2f s2) {
		if(s2.dist2(s1.center) <= s1.radius*s1.radius)
		{
			return s2;
		}
		return null;
	}

	private Interval getInterval(Vec2f axis,Vec2f p1,Vec2f p2)
	{
		double pTopleft = p1.dot(axis);
		Vec2f bottomRight = new Vec2f(p1.x + p2.x ,p1.y + p2.y );
		double pBottomRight = bottomRight.dot(axis);
		
		return new Interval(Math.min(pTopleft, pBottomRight),Math.max(pTopleft, pBottomRight));
	}
}
