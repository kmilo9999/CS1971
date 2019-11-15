package debugger.collisions;

import java.util.ArrayList;
import java.util.List;

import debugger.support.Vec2f;
import debugger.support.interfaces.Week5Reqs;

public final class Week5 extends Week5Reqs {

	// AXIS-ALIGNED BOXES
	
	public final class Edge
	{
	  public Vec2f p1;
	  public Vec2f p2;
	  public Vec2f normal;
	  Edge(Vec2f p1, Vec2f p2,Vec2f normal){
		  this.p1 = p1;
		  this.p2 = p2;
		  this.normal = normal;
	  };	
	}
	
	public final class IntervalsOnAxis
	{
		public Interval s1;
		public Interval s2;
		public Vec2f axis;
		IntervalsOnAxis(Interval i1, Interval i2, Vec2f axis)
		{
			this.s1 = i1;
			this.s2 = i2;
			this.axis = axis;
		}
	}
	
	
	@Override
	public Vec2f collision(AABShape s1, AABShape s2) {
		Vec2f aBottomRigth = new Vec2f(s1.topLeft.x + s1.size.x,s1.topLeft.y + s1.size.y );
		Vec2f bBottomRigth = new Vec2f(s2.getTopLeft().x + s2.getSize().x ,s2.getTopLeft().y + s2.getSize().y );
		
		if (s1.topLeft.x > bBottomRigth.x   || s2.getTopLeft().x > aBottomRigth.x) 
		{
			return null; 
		}
		  
		if (s1.topLeft.y > bBottomRigth.y || s2.getTopLeft().y > aBottomRigth.y)
		{
		    return null;
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
			return null;	
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
		
				
		return null;
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

	@Override
	public Vec2f collision(AABShape s1, PolygonShape s2) {

        List<Edge> edgesPolygon = new ArrayList<Edge>();
		
        
        // process Polygon
		for(int i = s2.points.length - 1 ; i > 0  ; i--)
		{
			Vec2f p1 = s2.points[i];
			Vec2f p2 = s2.points[i - 1 ];
			Vec2f normal = p2.minus(p1);
			normal =  new Vec2f(normal.y,-normal.x).normalize();
			Edge edge = new Edge(p1,p2,normal);
			edgesPolygon.add(edge);
		}
		
		Vec2f p1 = s2.points[0];
		Vec2f p2 = s2.points[ s2.points.length - 1];
		Vec2f normal = p2.minus(p1).normalize();
		normal = new Vec2f(normal.y, -normal.x);
		Edge edgeP = new Edge(p1, p2, normal);
		edgesPolygon.add(edgeP);
		
		
		
		
		boolean isProjecting = true;
		
		List<IntervalsOnAxis> projectedAxis = new ArrayList<IntervalsOnAxis>();
		
		for(int i = 0; i < edgesPolygon.size() && isProjecting ; i++)
		{
		    // for each edge, find it's normal	
			Vec2f edgeNormal = edgesPolygon.get(i).normal;
			
			// project all points of s1 to the axis
			float min1 = s2.points[0].dot(edgeNormal); 
			float max1 = min1;
			for(int j = 1 ; j < s2.points.length; j++)
			{
			   float p = s2.points[j].dot(edgeNormal);
			   if( p < min1)
			   {
				   min1 = p;
			   }
			   else if( p > max1)
			   {
				   max1 = p;
			   }
			}
			
			// project all points of s2 to the axis
			Vec2f aabPoints[] = new Vec2f[4];
			aabPoints[0] = s1.topLeft;
			aabPoints[1] = new Vec2f(s1.topLeft.x,s1.topLeft.y + s1.size.y) ;
			aabPoints[2] = new Vec2f(s1.topLeft.x + s1.size.x,s1.topLeft.y + s1.size.y) ;
			aabPoints[3] = new Vec2f(s1.topLeft.x + s1.size.x,s1.topLeft.y) ;
			
			float min2 = aabPoints[0].dot(edgeNormal); 
			float max2 = aabPoints[0].dot(edgeNormal);
			
		    for(int j = 1 ; j < aabPoints.length; j++)
		    {
		    	float p = aabPoints[j].dot(edgeNormal);
				   if( p < min2)
				   {
					   min2 = p;
				   }
				   else if( p > max2)
				   {
					   max2 = p;
				   }
		    }
		    
		    Interval intervalx1 = new Interval(min1,max1);
			Interval intervalx2 = new Interval(min2,max2);
			
			if(! intervalx1.overlap(intervalx2))
			{
				isProjecting = false;
				projectedAxis.clear();
			}
			else
			{
				IntervalsOnAxis intervalsOnAxis = new IntervalsOnAxis(intervalx2,intervalx1,edgeNormal);
				projectedAxis.add(intervalsOnAxis);
			}
			
		}
		
		if(isProjecting)
		{
			return shapeMTV(projectedAxis); 
		}
		
		return null;
		
	}
	
	public Vec2f shapeMTV(List<IntervalsOnAxis> shapesIntervals)
	{
		float minMagnitude = 10000000;
		Vec2f mtv = null;
		for( IntervalsOnAxis shapesInterval : shapesIntervals)
		{
			 Float mtv1d = intervalMTV(shapesInterval.s1,shapesInterval.s2);
			 if( mtv1d == null)
			 {
				 return null;
			 }
			 if(Math.abs(mtv1d) < minMagnitude)
			 {
				 minMagnitude = Math.abs(mtv1d);
				 mtv = shapesInterval.axis.smult(mtv1d);
			 }
		}
		
	    return mtv;
		
	}

	

	private Float intervalMTV(Interval s1, Interval s2) 
	{
		Float aRight = (float) (s2.max - s1.min);
		Float aLeft =(float)( s1.max - s2.min);
		if( aLeft < 0 || aRight < 0)
		{
			 return null;
		}
		if (aRight < aLeft)
		{
			 return aRight;
		}
		else
		{
			 return -aLeft;
		}
			
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
		
        
        
		return null;
	}

	@Override
	public Vec2f collision(CircleShape s1, Vec2f s2) {
		if(s2.dist2(s1.center) <= s1.radius*s1.radius)
		{
			return s2;
		}
		return null;
	}

	@Override
	public Vec2f collision(CircleShape s1, PolygonShape s2) {
		
		List<Edge> edgesPolygon = new ArrayList<Edge>();

		// process Polygon
		
		for (int i = s2.points.length - 1; i > 0; i--) {
			Vec2f p1 = s2.points[i];
			Vec2f p2 = s2.points[i - 1];
			Vec2f normal = p2.minus(p1);
			normal = new Vec2f(normal.y, -normal.x).normalize();
			Edge edge = new Edge(p1, p2, normal);
			edgesPolygon.add(edge);
		}

		Vec2f p1 = s2.points[1];
		Vec2f p2 = s2.points[0];
		Vec2f normal = p2.minus(p1);
		normal = new Vec2f(normal.y, -normal.x).normalize();
		Edge edge = new Edge(p1, p2, normal);
		edgesPolygon.add(edge);

		
		
		
		float minDistanceToCircle = 10000000;
		Vec2f minVector = null;
		for (int i = s2.points.length - 1; i >= 0; i--) 
		{
			float distance = s2.points[i].dist(s1.center);
			if(distance < minDistanceToCircle)
			{
				minDistanceToCircle = 	distance;
				minVector = s1.center.minus(s2.points[i]);
			}
			
		}
		minVector = minVector.normalize();
		
		
		
		
		boolean isProjecting = true;
		// project all points of s1 to the axis
		List<IntervalsOnAxis> projectedAxis = new ArrayList<IntervalsOnAxis>();
		
		float min1 = s2.points[0].dot(minVector); 
		float max1 = s2.points[0].dot(minVector);
		for(int j = 1 ; j < s2.points.length && isProjecting ; j++)
		{
			
		   float p = s2.points[j].dot(minVector);
		   if( p < min1)
		   {
			   min1 = p;
		   }
		   else if( p > max1)
		   {
			   max1 = p;
		   }
		   
			float min2 = s1.center.dot(minVector) - s1.radius;
			float max2 = s1.center.dot(minVector) + s1.radius;
			 
			Interval intervalx1 = new Interval(min1,max1);
			Interval intervalx2 = new Interval(min2,max2);
				
			if (!intervalx1.overlap(intervalx2)) {
				isProjecting = false;
				projectedAxis.clear();
			} else {
				IntervalsOnAxis intervalsOnAxis = new IntervalsOnAxis(intervalx2, intervalx1, minVector);
				projectedAxis.add(intervalsOnAxis);
			}
		   
		}
		
		if(isProjecting)
		{
			return shapeMTV(projectedAxis); 
		}
		
		return null;
		
		
	}
	
	// POLYGONS

	@Override
	public Vec2f collision(PolygonShape s1, AABShape s2) {
		Vec2f f = collision(s2, s1);
		return f == null ? null : f.reflect();
		
	}

	@Override
	public Vec2f collision(PolygonShape s1, CircleShape s2) {
		Vec2f f = collision(s2, s1);
		return f == null ? null : f.reflect();
	}

	@Override
	public Vec2f collision(PolygonShape s1, Vec2f s2) {
		
		List<Edge> edgesPolygon = new ArrayList<Edge>();

		// process Polygon s1
		for (int i = s1.points.length - 1; i > 0; i--) {
			Vec2f p1 = s1.points[i];
			Vec2f p2 = s1.points[i - 1];
			Vec2f normal = p2.minus(p1);
			normal = new Vec2f(normal.y, -normal.x).normalize();
			Edge edge = new Edge(p1, p2, normal);
			edgesPolygon.add(edge);
		}

		Vec2f p1 = s1.points[0];
		Vec2f p2 = s1.points[ s1.points.length - 1];
		Vec2f normal = p2.minus(p1);
		normal = new Vec2f(normal.y, -normal.x).normalize();
		Edge edgeP = new Edge(p1, p2, normal);
		edgesPolygon.add(edgeP);
		
		boolean inside = true;
		for(Edge edge:edgesPolygon )
		{
			Vec2f edgeVector = edge.p1.minus(edge.p2);
			Vec2f toPoint = s2.minus(edge.p2);
			if(edgeVector.cross(toPoint) > 0)
			{
				inside = false;
				break;
			}
		}
		
		if(inside)
		{
			return s2;
		}
		
		return null;
	}

	@Override
	public Vec2f collision(PolygonShape s1, PolygonShape s2) {

		List<Edge> edgesPolygon = new ArrayList<Edge>();
		
		// process Polygon s1
		for (int i = s1.points.length - 1; i > 0; i--) {
			Vec2f p1 = s1.points[i];
			Vec2f p2 = s1.points[i - 1];
			Vec2f normal = p2.minus(p1);
			normal = new Vec2f(normal.y, -normal.x).normalize();
			Edge edge = new Edge(p1, p2, normal);
			edgesPolygon.add(edge);
		}

		Vec2f p1 = s1.points[1];
		Vec2f p2 = s1.points[0];
		Vec2f normal = p2.minus(p1);
		normal = new Vec2f(normal.y, -normal.x).normalize();
		Edge edge = new Edge(p1, p2, normal);
		edgesPolygon.add(edge);
		
		
		
		
		float min1 = 100000; 
		float max1 = 0;
		float min2 = 100000; 
		float max2 = 0;
		
		boolean isProjecting = true;
		List<IntervalsOnAxis> projectedAxis = new ArrayList<IntervalsOnAxis>();
		
		for(int i = 0; i < edgesPolygon.size() && isProjecting ; i++)
		{
		    // for each edge, find it's normal	
			Vec2f edgeNormal = edgesPolygon.get(i).normal;
			
			// project all points of s1 to the axis
			for (int j = 0; j < s1.points.length; j++) 
			{
				float p = s1.points[j].dot(edgeNormal);
				if (p < min1) 
				{
					min1 = p;
				} 
				else if (p > max1) 
				{
					max1 = p;
				}
			}
			
			// project all points of s1 to the axis
			for (int j = 0; j < s2.points.length; j++) 
			{
				float p = s2.points[j].dot(edgeNormal);
				if (p < min1) 
				{
					min1 = p;
				} 
				else if (p > max1) 
				{
					max1 = p;
				}
			}
			
			Interval intervalx1 = new Interval(min1, max1);
			Interval intervalx2 = new Interval(min2, max2);

			if (!intervalx1.overlap(intervalx2)) 
			{
				isProjecting = false;
				projectedAxis.clear();
			} 
			else 
			{
				IntervalsOnAxis intervalsOnAxis = new IntervalsOnAxis(intervalx2, intervalx1, edgeNormal);
				projectedAxis.add(intervalsOnAxis);
			}
			
		}
		
		if(isProjecting)
		{
			return shapeMTV(projectedAxis); 
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
