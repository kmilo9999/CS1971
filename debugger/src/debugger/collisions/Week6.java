package debugger.collisions;

import java.util.ArrayList;
import java.util.List;

import debugger.support.Vec2f;
import debugger.support.interfaces.Week6Reqs;

public final class Week6 extends Week6Reqs {

	// AXIS-ALIGNED BOXES
	
	public final float MAXIMUM_RANGE = 1000000.0f;
	
	@Override
	public Vec2f collision(AABShape s1, AABShape s2) {
		return null;
	}

	@Override
	public Vec2f collision(AABShape s1, CircleShape s2) {
		return null;
	}

	@Override
	public Vec2f collision(AABShape s1, Vec2f s2) {
		return null;
	}

	@Override
	public Vec2f collision(AABShape s1, PolygonShape s2) {
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
		return null;
	}

	@Override
	public Vec2f collision(CircleShape s1, Vec2f s2) {
		return null;
	}

	@Override
	public Vec2f collision(CircleShape s1, PolygonShape s2) {
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
		return null;
	}

	@Override
	public Vec2f collision(PolygonShape s1, PolygonShape s2) {
		return null;
	}
	
	// RAYCASTING
	
	@Override
	public float raycast(AABShape s1, Ray s2) {
		final class Edge
		{
		  Vec2f p1;
		  Vec2f p2;
		  Edge(Vec2f p1, Vec2f p2){
			  this.p1 = p1;
			  this.p2 = p2;
		  };	
		}
		
		Vec2f p0 = s1.getTopLeft();
		Vec2f p3 = new Vec2f(s1.getTopLeft().x ,s1.getTopLeft().y + s1.getSize().y);
		Vec2f p2 = new Vec2f(s1.getTopLeft().x + s1.getSize().x ,s1.getTopLeft().y + s1.getSize().y);
		Vec2f p1 = new Vec2f(s1.getTopLeft().x + s1.getSize().x ,s1.getTopLeft().y);
		
		
		
		List<Edge> edges = new ArrayList<Edge>();
		edges.add(new Edge(p0,p1));
		edges.add(new Edge(p1,p2));
		edges.add(new Edge(p2,p3));
		edges.add(new Edge(p3,p0));
		
		float min = MAXIMUM_RANGE;
		
		for(int i = 0; i < edges.size()  ; i++)
		{
			
			Vec2f m = edges.get(i).p2.minus(edges.get(i).p1).normalize();
			Vec2f n = new Vec2f(-m.y,m.x).normalize();
					
			Vec2f d1 = edges.get(i).p1.minus(s2.src);
			Vec2f d2 = edges.get(i).p2.minus(s2.src);
			
			float cp1 = d1.cross(s2.dir);
			float cp2 = d2.cross(s2.dir);
			
			
			if((int)cp1 * (int)cp2 >= 0)
			{
			  	continue;
			}
			
			float t = d2.dot(n)/s2.dir.dot(n);
			
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
	public float raycast(CircleShape s1, Ray s2) {
		if(s1.getCenter().dist(s2.src) > s1.getRadius())
		{
			
			// outside circle
			Vec2f l = s1.getCenter().minus(s2.src).projectOnto(s2.dir);
			Vec2f pointOnAxis = l.plus(s2.src);
			float tca = s1.getCenter().minus(s2.src).dot(s2.dir.normalize());
		    float d2 = l.dot(l) - ( tca * tca);
			float thc = (float)Math.sqrt(s1.getRadius() * s1.getRadius()  - d2 * d2);
			float t =  tca - thc;
			
			if(tca > 0 && pointOnAxis.dist2(s1.center) <= s1.radius*s1.radius)
			{
				return t;
			}
			
			return 0;
			
			
			
		}
		else
		{
		
			// inside circle
			Vec2f l = s1.getCenter().minus(s2.src).projectOnto(s2.dir.normalize());
			Vec2f pointOnAxis = l.plus(s2.src);
			/*float d2 = pointOnAxis.minus(s1.center).mag();
			float tca = l.dot(l);
			float thc = (float)Math.sqrt(s1.getRadius() * s1.getRadius()  - d2 * d2);
			float t =  tca + thc;
			//Vec2f point = s2.src.plus(s2.dir.smult(t));*/

			float d2 = pointOnAxis.minus(s1.getCenter()).mag2();
			float t = l.mag() + (float)Math.sqrt(s1.getRadius() * s1.getRadius()  - d2 );
			
			if( pointOnAxis.dist2(s1.center) <= s1.radius*s1.radius)
			{
				return t;
			}
			
			return 0;
		}
	}
	
	@Override
	public float raycast(PolygonShape s1, Ray s2) {
		final class Edge
		{
		  Vec2f p1;
		  Vec2f p2;
		  Edge(Vec2f p1, Vec2f p2){
			  this.p1 = p1;
			  this.p2 = p2;
		  };	
		}
		
		float min = MAXIMUM_RANGE;
		
		List<Edge> edges = new ArrayList<Edge>();
		
		for(int i = 0; i < s1.points.length - 1 ; i++)
		{
			Edge edge = new Edge(s1.points[i],s1.points[i +1 ]);
			edges.add(edge);
		}
		
		edges.add(new Edge(s1.points[s1.points.length - 1],s1.points[0]));
		
		for(int i = 0; i < edges.size()  ; i++)
		{
			
			Vec2f m = edges.get(i).p2.minus(edges.get(i).p1).normalize();
			Vec2f n = new Vec2f(-m.y,m.x).normalize();
					
			Vec2f d1 = edges.get(i).p1.minus(s2.src);
			Vec2f d2 = edges.get(i).p2.minus(s2.src);
			
			float cp1 = d1.cross(s2.dir);
			float cp2 = d2.cross(s2.dir);
			
			
			if((int)cp1 * (int)cp2 >= 0)
			{
			  	continue;
			}
			
			float t = d2.dot(n)/s2.dir.dot(n);
			
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
