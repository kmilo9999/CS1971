package debugger.support.interfaces;

import debugger.collisions.AABShape;
import debugger.collisions.CircleShape;
import debugger.collisions.PolygonShape;
import debugger.collisions.Ray;
import debugger.support.Vec2f;

public interface CollisionFunctions {

	public Vec2f collision(AABShape s1, AABShape s2);
	public Vec2f collision(AABShape s1, CircleShape s2);
	public Vec2f collision(AABShape s1, Vec2f s2);
	public Vec2f collision(AABShape s1, PolygonShape s2);
	
	public Vec2f collision(CircleShape s1, AABShape s2);
	public Vec2f collision(CircleShape s1, CircleShape s2);
	public Vec2f collision(CircleShape s1, Vec2f s2);
	public Vec2f collision(CircleShape s1, PolygonShape s2);
	
	
	public Vec2f collision(PolygonShape s1, AABShape s2);
	public Vec2f collision(PolygonShape s1, CircleShape s2);
	public Vec2f collision(PolygonShape s1, Vec2f s2);
	public Vec2f collision(PolygonShape s1, PolygonShape s2);
	
	public float raycast(AABShape s1, Ray s2);
	public float raycast(CircleShape s1, Ray s2);
	public float raycast(PolygonShape s1, Ray s2);
	
}
