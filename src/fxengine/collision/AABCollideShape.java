package fxengine.collision;


import java.util.ArrayList;
import java.util.List;

import fxengine.math.Vec2d;
import fxengine.raycasting.Ray;

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
	public boolean isColliding(CollisionShape o) {
		// TODO Auto-generated method stub
		return o.isCollidingAAB(this);
	}

	@Override
	public boolean isCollidingCircle(CircleCollisionShape c) {
		// TODO Auto-generated method stub
		double clampedValueX = Math.max(myTopLeft.x, Math.min(c.getCenter().x, myTopLeft.x + mySize.x));
		double clampedValueY = Math.max(myTopLeft.y, Math.min(c.getCenter().y, myTopLeft.y + mySize.y));
		
		return c.isCollidingPoint(new Vec2d(clampedValueX,clampedValueY));
		
	}

	@Override
	public boolean isCollidingAAB(AABCollideShape other) {
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
	public boolean isCollidingPoint(Vec2d s2) {
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

	@Override
	public Vec2d collisionCircle(CircleCollisionShape c) {
		// TODO Auto-generated method stub
		double clampedValueX = Math.max(this.myTopLeft.x, Math.min(c.getCenter().x, this.myTopLeft.x + this.mySize.x));
		double clampedValueY = Math.max(this.myTopLeft.y, Math.min(c.getCenter().y, this.myTopLeft.y + this.mySize.y));
		
		Vec2d point = new Vec2d(clampedValueX,clampedValueY);
		if(point.dist2(c.center) <= c.radius*c.radius)
		{
			if( (c.center.x >= this.myTopLeft.x && c.center.x <= this.myTopLeft.x + this.mySize.x )
					&& (c.center.y >= this.myTopLeft.y && c.center.y <= this.myTopLeft.y + this.mySize.y ) )
			{
				
				Vec2d closesPoint = this.myTopLeft;
				
				if( closesPoint.dist(c.center) > new Vec2d(this.myTopLeft.x,this.myTopLeft.x + this.mySize.x).dist(c.center))
				{
					closesPoint = new Vec2d(this.myTopLeft.x,this.myTopLeft.x + this.mySize.x); 
				}
				
				
				if( closesPoint.dist(c.center) > new Vec2d(this.myTopLeft.x,this.myTopLeft.y + this.mySize.y).dist(c.center))
				{
					closesPoint = new Vec2d(this.myTopLeft.x,this.myTopLeft.y + this.mySize.y);
				}
				
				if( closesPoint.dist(c.center) > new Vec2d(this.myTopLeft.x + this.mySize.x, this.myTopLeft.y + this.mySize.y).dist(c.center))
				{
					closesPoint = new Vec2d(this.myTopLeft.x + this.mySize.x , this.myTopLeft.y + this.mySize.y);
				}
				
				
				double lenght = c.radius + c.center.dist(closesPoint);
				Vec2d mvtAxis = new Vec2d(0,1);
				return mvtAxis.smult( lenght /2);
			}
		
			
			
			double lenght = c.radius + c.center.dist(point);
			Vec2d aabCenter = new Vec2d(this.myTopLeft.x + (this.mySize.x /2),this.myTopLeft.y + (this.mySize.y /2));
			Vec2d mvtAxis =  c.center.minus(aabCenter);
			
			
			Vec2d distanceVector1 = c.center.minus(aabCenter);
			if(mvtAxis.dot(distanceVector1) >= 0) 
			{
				mvtAxis = mvtAxis.reflect();
			}
			
	        return mvtAxis.normalize().smult(lenght/2);

	
		}
		
				
		return new Vec2d(0);
	}

	@Override
	public Vec2d collisionAABS(AABCollideShape s2) {
		// TODO Auto-generated method stub
		Vec2d aBottomRigth = new Vec2d(this.myTopLeft.x + this.mySize.x,this.myTopLeft.y + this.mySize.y );
		Vec2d bBottomRigth = new Vec2d(s2.getTopLeft().x + s2.getSize().x ,s2.getTopLeft().y + s2.getSize().y );
		
		if (this.myTopLeft.x > bBottomRigth.x   || s2.getTopLeft().x > aBottomRigth.x) 
		{
			return new Vec2d(0); 
		}
		  
		if (this.myTopLeft.y > bBottomRigth.y || s2.getTopLeft().y > aBottomRigth.y)
		{
		    return new Vec2d(0);
		} 
		
		Interval intervalx1 = getInterval(new Vec2d(1,0) , this.myTopLeft ,this.mySize);
		Interval intervalx2 = getInterval(new Vec2d(1,0) , s2.getTopLeft() ,s2.getSize());
		
		
		Interval intervaly1 = getInterval(new Vec2d(0, 1) , this.myTopLeft , this.mySize);
		Interval intervaly2 = getInterval(new Vec2d(0, 1) , s2.getTopLeft() ,s2.getSize());
		
		if( intervalx1.overlap(intervalx2) && intervaly1.overlap(intervaly2))
		{
			double shortest = Math.abs((this.myTopLeft.y + this.mySize.y) - (s2.getTopLeft().y));
			Vec2d mvtAxis = new Vec2d(0,-1);
			if(shortest > Math.abs((this.myTopLeft.y) - (s2.getTopLeft().y + s2.getSize().y)))
			{
				shortest = Math.abs((this.myTopLeft.y) - (s2.getTopLeft().y + s2.getSize().y));
				mvtAxis = new Vec2d(0,1);
			}
			if(shortest > Math.abs((this.myTopLeft.x + this.mySize.x) - (s2.getTopLeft().x)))
			{
				shortest = (this.myTopLeft.x + this.mySize.x) - (s2.getTopLeft().x);
				mvtAxis = new Vec2d(-1,0);
			}
			if(shortest > Math.abs((this.myTopLeft.x) - (s2.getTopLeft().x + s2.getSize().x)))
			{
				shortest = Math.abs((this.myTopLeft.x) - (s2.getTopLeft().x + s2.getSize().x));
				mvtAxis = new Vec2d(1,0);
			}
			
			
			return mvtAxis.smult(shortest/2);
			
		}
		else
		{
			return new Vec2d(0);	
		}
		
		
	}

	@Override
	public Vec2d colliding(CollisionShape o) {
		// TODO Auto-generated method stub
		return o.collisionAABS(this);
	}

	@Override
	public Vec2d collidingPoint(Vec2d s2) {
		// TODO Auto-generated method stub
		if((this.myTopLeft.x <= s2.x && this.myTopLeft.x + this.mySize.x >= s2.x)
				&& (this.myTopLeft.y <= s2.y && this.myTopLeft.y + this.mySize.y >= s2.y))
		{
			return s2;
		}
		return null;
	}

	@Override
	public double raycast(Ray ray) {
		final class Edge
		{
		  Vec2d p1;
		  Vec2d p2;
		  Edge(Vec2d p1, Vec2d p2){
			  this.p1 = p1;
			  this.p2 = p2;
		  };	
		}
		
		Vec2d p0 = getTopLeft();
		Vec2d p3 = new Vec2d(getTopLeft().x , getTopLeft().y + getSize().y);
		Vec2d p2 = new Vec2d(getTopLeft().x + getSize().x ,getTopLeft().y + getSize().y);
		Vec2d p1 = new Vec2d(getTopLeft().x + getSize().x ,getTopLeft().y);
		
		
		
		List<Edge> edges = new ArrayList<Edge>();
		edges.add(new Edge(p0,p1));
		edges.add(new Edge(p1,p2));
		edges.add(new Edge(p2,p3));
		edges.add(new Edge(p3,p0));
		
		double min = MAXIMUM_RANGE;
		
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
