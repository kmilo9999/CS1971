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
	  Edge(Vec2d p1, Vec2d p2){
		  this.p1 = p1;
		  this.p2 = p2;
	  };	
	}
	
	List<Edge> myEdges = new ArrayList<Edge>();
	
	public PolygonColliderShape(List<Vec2d> points) {
		// TODO Auto-generated constructor stub
		
		for(int i = 0 ; i < points.size() - 1 ; i ++)
		{
			myEdges.add(new Edge(points.get(i), points.get(i + 1)));
		}
		
		myEdges.add(new Edge(points.get(points.size() - 1), points.get(0)));
		
		
		
	}

	@Override
	public boolean isCollidingCircle(CircleCollisionShape c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCollidingAAB(AABCollideShape aab) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isColliding(CollisionShape o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCollidingPoint(Vec2d s2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vec2d collisionCircle(CircleCollisionShape c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vec2d collisionAABS(AABCollideShape c) {
		// TODO Auto-generated method stub
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
		return null;
	}

	@Override
	public void update(Vec2d position, Vec2d size) {
		// TODO Auto-generated method stub
		
	}

	public List<Edge> getEdges() {
		return myEdges;
	}

	public void setEdges(List<Edge> myEdges) {
		this.myEdges = myEdges;
	}

	@Override
	public double raycast(Ray ray) {
double min = MAXIMUM_RANGE;
		
		List<PolygonColliderShape.Edge> edges = getEdges();
		
		
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
