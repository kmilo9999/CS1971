package fxengine.collision;

import java.util.ArrayList;
import java.util.List;

import fxengine.math.Vec2d;
import fxengine.raycasting.Ray;

public class PolygonColliderShape extends CollisionShape {

	public final class Edge
	{
	  public Vec2d p1;
	  public Vec2d p2;
	  public Vec2d normal;
	  Edge(Vec2d p1, Vec2d p2,Vec2d normal){
		  this.p1 = p1;
		  this.p2 = p2;
		  this.normal = normal;
	  };
	}
	
	
	private List<Vec2d> myPoints = new ArrayList<Vec2d>();
    
    
    private List<Edge> pointsToEdges()
    {
    	List<Edge> edges = new ArrayList<PolygonColliderShape.Edge>();
    	for(int i = this.myPoints.size() - 1; i > 0; i--)
		{
			Vec2d p1 = this.myPoints.get(i);
			Vec2d p2 = this.myPoints.get(i - 1);
			Vec2d normal = p2.minus(p1);
			normal = new Vec2d(normal.y, -normal.x).normalize();
			Edge edge = new Edge(p1, p2, normal);
			edges.add(edge);
		}
		
		
		Vec2d p1 = this.myPoints.get(0);
		Vec2d p2 = this.myPoints.get(this.myPoints.size() - 1);
		Vec2d normal = p2.minus(p1);
		normal = new Vec2d(normal.y, -normal.x).normalize();
		Edge edge = new Edge(p1, p2, normal);
		edges.add(edge);
    	
		return edges;
    }
    
	
	public PolygonColliderShape(List<Vec2d> points) {
		this.myPoints = points;
		
	}

	@Override
	public boolean isCollidingCircle(CircleCollisionShape c) {
		
		
		double minDistanceToCircle = 10000000;
		Vec2d minVector = null;
		for (Vec2d point:this.myPoints) 
		{
			double distance = point.dist(c.getCenter());
			if(distance < minDistanceToCircle)
			{
				minDistanceToCircle = distance;
				minVector = c.getCenter().minus(point);
			}
			
		}
		minVector = minVector.normalize();
		
		
		boolean isProjecting = true;
		// project all points of s1 to the axis
		List<IntervalsOnAxis> projectedAxis = new ArrayList<IntervalsOnAxis>();
		
		double min1 = this.myPoints.get(0).dot(minVector); 
		double max1 = this.myPoints.get(0).dot(minVector);
		
		
		for(int j = 1 ; j < this.myPoints.size() && isProjecting ; j++)
		{
			
		   double p = this.myPoints.get(j).dot(minVector);
		   if( p < min1)
		   {
			   min1 = p;
		   }
		   else if( p > max1)
		   {
			   max1 = p;
		   }
		   
		   double min2 = c.getCenter().dot(minVector) - c.getRadius();
		   double max2 = c.getCenter().dot(minVector) + c.getRadius();
			 
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
			return true;
		}
		
		return false;
	}

	@Override
	public boolean isCollidingAAB(AABCollideShape aab) {
		
		 
		    List<Edge> edges = pointsToEdges();
			boolean isProjecting = true;
			
			List<IntervalsOnAxis> projectedAxis = new ArrayList<IntervalsOnAxis>();
			
			for(int i = 0; i < edges.size() && isProjecting ; i++)
			{
			    // for each edge, find it's normal	
				Vec2d edgeNormal = edges.get(i).normal;
				
				// project all points of s1 to the axis
				double min1 = this.myPoints.get(0).dot(edgeNormal); 
				double max1 = min1;
				for(int j = 1 ; j < this.myPoints.size(); j++)
				{
				   double p = this.myPoints.get(j).dot(edgeNormal);
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
				Vec2d aabPoints[] = new Vec2d[4];
				aabPoints[0] = aab.getTopLeft();
				aabPoints[1] = new Vec2d(aab.getTopLeft().x,aab.getTopLeft().y + aab.getSize().y) ;
				aabPoints[2] = new Vec2d(aab.getTopLeft().x + aab.getSize().x,aab.getTopLeft().y + aab.getSize().y) ;
				aabPoints[3] = new Vec2d(aab.getTopLeft().x + aab.getSize().x,aab.getTopLeft().y) ;
				
				double min2 = aabPoints[0].dot(edgeNormal); 
				double max2 = aabPoints[0].dot(edgeNormal);
				
			    for(int j = 1 ; j < aabPoints.length; j++)
			    {
			    	double p = aabPoints[j].dot(edgeNormal);
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
				return true; 
			}
			
			return false;
	}

	@Override
	public boolean isColliding(CollisionShape o) {
		return o.isCollidingPolygon(this);
	}

	@Override
	public boolean isCollidingPoint(Vec2d s2) {
		
		List<Edge> edges = new ArrayList<PolygonColliderShape.Edge>();
		boolean inside = true;
		for(Edge edge:edges )
		{
			Vec2d edgeVector = edge.p1.minus(edge.p2);
			Vec2d toPoint = s2.minus(edge.p2);
			if(edgeVector.cross(toPoint) > 0)
			{
				inside = false;
				break;
			}
		}
		
		if(inside)
		{
			return true;
		}
		
		return false;
	}

	@Override
	public Vec2d collisionCircle(CircleCollisionShape c) {
		// TODO Auto-generated method stub

		double minDistanceToCircle = 10000000;
		Vec2d minVector = null;
		for (Vec2d point:this.myPoints) 
		{
			double distance = point.dist(c.getCenter());
			if(distance < minDistanceToCircle)
			{
				minDistanceToCircle = distance;
				minVector = c.getCenter().minus(point);
			}
			
		}
		minVector = minVector.normalize();
		
		
		boolean isProjecting = true;
		// project all points of s1 to the axis
		List<IntervalsOnAxis> projectedAxis = new ArrayList<IntervalsOnAxis>();
		
		double min1 = this.myPoints.get(0).dot(minVector); 
		double max1 = this.myPoints.get(0).dot(minVector);
		
		
		for(int j = 1 ; j < this.myPoints.size() && isProjecting ; j++)
		{
			
		   double p = this.myPoints.get(j).dot(minVector);
		   if( p < min1)
		   {
			   min1 = p;
		   }
		   else if( p > max1)
		   {
			   max1 = p;
		   }
		   
		   double min2 = c.getCenter().dot(minVector) - c.getRadius();
		   double max2 = c.getCenter().dot(minVector) + c.getRadius();
			 
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

	@Override
	public Vec2d collisionAABS(AABCollideShape aab) {
		
		
		List<Edge> edges = pointsToEdges();
		
		boolean isProjecting = true;
		
		
		List<IntervalsOnAxis> projectedAxis = new ArrayList<IntervalsOnAxis>();
		
		for(int i = 0; i < edges.size() && isProjecting ; i++)
		{
		    // for each edge, find it's normal	
			Vec2d edgeNormal = edges.get(i).normal;
			
			// project all points of s1 to the axis
			double min1 = this.myPoints.get(0).dot(edgeNormal); 
			double max1 = min1;
			for(int j = 1 ; j < this.myPoints.size(); j++)
			{
			   double p = this.myPoints.get(j).dot(edgeNormal);
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
			Vec2d aabPoints[] = new Vec2d[4];
			aabPoints[0] = aab.getTopLeft();
			aabPoints[1] = new Vec2d(aab.getTopLeft().x,aab.getTopLeft().y + aab.getSize().y) ;
			aabPoints[2] = new Vec2d(aab.getTopLeft().x + aab.getSize().x,aab.getTopLeft().y + aab.getSize().y) ;
			aabPoints[3] = new Vec2d(aab.getTopLeft().x + aab.getSize().x,aab.getTopLeft().y) ;
			
			double min2 = aabPoints[0].dot(edgeNormal); 
			double max2 = aabPoints[0].dot(edgeNormal);
			
		    for(int j = 1 ; j < aabPoints.length; j++)
		    {
		    	double p = aabPoints[j].dot(edgeNormal);
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

	@Override
	public Vec2d colliding(CollisionShape o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vec2d collidingPoint(Vec2d s2) {
		// TODO Auto-generated method stub
		if(isCollidingPoint(s2)) 
		{
			return s2;
		}
		return null;
	}

	@Override
	public void update(Vec2d position, Vec2d size) {
		// TODO Auto-generated method stub
		for(Vec2d point:this.myPoints)
		{
			point = point.plus(position);
		}
		// size;
	}


	@Override
	public double raycast(Ray ray) {
      double min = MAXIMUM_RANGE;
		
        
		List<PolygonColliderShape.Edge> edges = pointsToEdges();
		
		
		for(int i = 0; i < edges.size()  ; i++)
		{
			
			Vec2d m = edges.get(i).p2.minus(edges.get(i).p1).normalize();
			Vec2d n = new Vec2d(-m.y,m.x).normalize();
					
			Vec2d d1 = edges.get(i).p1.minus(ray.getSource());
			Vec2d d2 = edges.get(i).p2.minus(ray.getSource());
			
			double cp1 = d1.cross(ray.getDirection());
			double cp2 = d2.cross(ray.getDirection());
			
			
			if((int)cp1 * (int)cp2 >= 0)
			{
			  	continue;
			}
			
			double t = d2.dot(n)/ray.getDirection().dot(n);
			
			if(t > 0 && t < min)
			{
				min = t;
			}
			
		}
		
		if(min != MAXIMUM_RANGE)
		{
			return min;
		}
		
		return 0;
		
	}


	@Override
	public boolean isCollidingPolygon(PolygonColliderShape other) {
		
		List<Edge> edges =  new ArrayList<PolygonColliderShape.Edge>();
		
		double min1 = 100000; 
		double max1 = 0;
		double min2 = 100000; 
		double max2 = 0;
		
		boolean isProjecting = true;
		List<IntervalsOnAxis> projectedAxis = new ArrayList<IntervalsOnAxis>();
		
		for(int i = 0; i < edges.size() && isProjecting ; i++)
		{
		    // for each edge, find it's normal	
			Vec2d edgeNormal = edges.get(i).normal;
			
			// project all points of s1 to the axis
			for (int j = 0; j < this.myPoints.size(); j++) 
			{
				double p = this.myPoints.get(j).dot(edgeNormal);
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
			for (int j = 0; j < other.getPoints().size(); j++) 
			{
				double p = other.getPoints().get(j).dot(edgeNormal);
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
			return true; 
		}
		
		return false;
	}

	@Override
	public Vec2d collidingPolygon(PolygonColliderShape other) {
		
		List<Edge> edges = pointsToEdges();
		
		double min1 = 100000; 
		double max1 = 0;
		double min2 = 100000; 
		double max2 = 0;
		
		boolean isProjecting = true;
		List<IntervalsOnAxis> projectedAxis = new ArrayList<IntervalsOnAxis>();
		
		for(int i = 0; i < edges.size() && isProjecting ; i++)
		{
		    // for each edge, find it's normal	
			Vec2d edgeNormal = edges.get(i).normal;
			
			// project all points of s1 to the axis
			for (int j = 0; j < this.myPoints.size(); j++) 
			{
				double p = this.myPoints.get(j).dot(edgeNormal);
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
			for (int j = 0; j < other.getPoints().size(); j++) 
			{
				double p = other.getPoints().get(j).dot(edgeNormal);
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

	public List<Vec2d> getPoints() {
		return myPoints;
	}

	public void setPoints(List<Vec2d> points) {
		this.myPoints = points;
	}
	
	public Vec2d shapeMTV(List<IntervalsOnAxis> shapesIntervals)
	{
		double minMagnitude = 10000000;
		Vec2d mtv = null;
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
}
