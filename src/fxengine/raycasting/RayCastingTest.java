package fxengine.raycasting;

import java.util.ArrayList;
import java.util.List;

import fxengine.collision.AABCollideShape;
import fxengine.collision.CircleCollisionShape;
import fxengine.collision.CollisionShape;
import fxengine.collision.PolygonColliderShape;
import fxengine.math.Vec2d;

public class RayCastingTest {

	private static final double MAXIMUM_RANGE = 1000000.0f;
		
	public static double raycast(AABCollideShape aabShape, Ray ray)
	{
		final class Edge
		{
		  Vec2d p1;
		  Vec2d p2;
		  Edge(Vec2d p1, Vec2d p2){
			  this.p1 = p1;
			  this.p2 = p2;
		  };	
		}
		
		Vec2d p0 = aabShape.getTopLeft();
		Vec2d p3 = new Vec2d(aabShape.getTopLeft().x , aabShape.getTopLeft().y + aabShape.getSize().y);
		Vec2d p2 = new Vec2d(aabShape.getTopLeft().x + aabShape.getSize().x ,aabShape.getTopLeft().y + aabShape.getSize().y);
		Vec2d p1 = new Vec2d(aabShape.getTopLeft().x + aabShape.getSize().x ,aabShape.getTopLeft().y);
		
		
		
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
	
	
	public static double raycast(CircleCollisionShape ccShape, Ray ray) {
		
		if(ccShape.getCenter().dist(ray.getSource()) > ccShape.getRadius())
		{
			
			// outside circle
			Vec2d l = ccShape.getCenter().minus(ray.getSource()).projectOnto(ray.getDirection());
			Vec2d pointOnAxis = l.plus(ray.getSource());
			double tca = ccShape.getCenter().minus(ray.getSource()).dot(ray.getDirection().normalize());
			double d2 = l.dot(l) - ( tca * tca);
			double thc = Math.sqrt(ccShape.getRadius() * ccShape.getRadius()  - d2 * d2);
			double t =  tca - thc;
			
			if(tca > 0 && pointOnAxis.dist2(ccShape.getCenter()) <= ccShape.getRadius()*ccShape.getRadius())
			{
				return t;
			}
			
			return 0;
			
			
			
		}
		else
		{
		
			// inside circle
			Vec2d l = ccShape.getCenter().minus(ray.getSource()).projectOnto(ray.getDirection().normalize());
			Vec2d pointOnAxis = l.plus(ray.getSource());
			/*float d2 = pointOnAxis.minus(s1.center).mag();
			float tca = l.dot(l);
			float thc = (float)Math.sqrt(s1.getRadius() * s1.getRadius()  - d2 * d2);
			float t =  tca + thc;
			//Vec2f point = s2.src.plus(s2.dir.smult(t));*/

			double d2 = pointOnAxis.minus(ccShape.getCenter()).mag2();
			double t = l.mag() + (float)Math.sqrt(ccShape.getRadius() * ccShape.getRadius()  - d2 );
			
			if( pointOnAxis.dist2(ccShape.getCenter()) <= ccShape.getRadius() * ccShape.getRadius())
			{
				return t;
			}
			
			return 0;
		}
	}
	
	public static double raycast(PolygonColliderShape plgShape, Ray ray) {
		
		
		double min = MAXIMUM_RANGE;
		
		List<PolygonColliderShape.Edge> edges = plgShape.getEdges();
		
		
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

	
}
