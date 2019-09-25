package debugger.collisions;

import debugger.support.Vec2f;
import debugger.support.interfaces.Week6Reqs;

public final class Week6 extends Week6Reqs {

	// AXIS-ALIGNED BOXES
	
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
		return -1;
	}
	
	@Override
	public float raycast(CircleShape s1, Ray s2) {
		return -1;
	}
	
	@Override
	public float raycast(PolygonShape s1, Ray s2) {
		return -1;
	}

}
