package fxengine.collision;

import fxengine.collision.CollisionShape;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;

public class Collision {
	
	public Collision(GameObject other, Vec2d mvt, CollisionShape s1, CollisionShape s2)
	{
	    this.other = other;
	    this.mtv = mvt;
	    this.thisShape = s1;
	    this.otherShape = s2;
	    
	    
	}
	
	 public final GameObject other;
	 public final Vec2d mtv;
	 public final CollisionShape thisShape;
	 public final CollisionShape otherShape;
	 
	 
}
