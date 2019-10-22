package fxengine.collision;

import fxengine.components.CollisionComponent;
import fxengine.components.ComponentContants;
import fxengine.components.PhysicsComponent;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;

public class PhysicsCollision {
	public PhysicsCollision(GameObject go1, GameObject go2, Vec2d mvt, CollisionShape s1, CollisionShape s2)
	{
		this.object1  = go1;
	    this.object2 = go2;
	    this.mtv = mvt;
	    this.thisShape = s1;
	    this.otherShape = s2;
	}
	
	
	public void resolveCollision(double deltaTime)
	{
		CollisionComponent c1 = (CollisionComponent)object1.getComponent(ComponentContants.collision);
		CollisionComponent c2 = (CollisionComponent)object2.getComponent(ComponentContants.collision);
		
		if(c1.isStatic() || c2.isStatic())
		{
			resolveStaticCollision(c1, c2);
		}
		else
		{
			
		}
	}
	
	private Vec2d resolveStaticCollision(CollisionComponent collisionComponent, CollisionComponent other)
	   {
		   
		   //Vec2d velocityAfterCollision = resol 
		   Vec2d mvt = other.getCollisionShape()
					.colliding(collisionComponent.getCollisionShape()); 
		   
		   if(collisionComponent.getParent().hasComponent(ComponentContants.physics)
				   && other.getParent().hasComponent(ComponentContants.physics))
		   {
			  PhysicsComponent physicsComponent = (PhysicsComponent)collisionComponent.getParent().getComponent(ComponentContants.physics); 
			  PhysicsComponent otherPhysicsComponent = (PhysicsComponent)other.getParent().getComponent(ComponentContants.physics);
			  
			  Vec2d velocityAfterCollision =  physicsComponent.resolveVelocity(otherPhysicsComponent);
			  Vec2d normalizedMvt = mvt.normalize();
			  
			  double sVelocityAfterCollision = normalizedMvt.dot(velocityAfterCollision);
			  mvt = mvt.smult(sVelocityAfterCollision);
			  physicsComponent.setVelocity(mvt);
			  
			  
			  Vec2d impulse = physicsComponent.resolveImpulse(otherPhysicsComponent);
			  physicsComponent.applyImpulse(impulse);
			  
			  if(other.isStatic() && normalizedMvt.equals(new Vec2d(0,-1)))
			  {
				  physicsComponent.setOnStacticObject(true);
			  }else
			  {
				  physicsComponent.setOnStacticObject(false);
			  }
		   }
		   
		  /* if(mvt != null) 
		   {
			   Collision collisionInfoObject1 = new Collision(other.getParent(),mvt.smult(2) ,collisionComponent.getCollisionShape(),other.getCollisionShape());
			   Collision collisionInfoObject2 = new Collision(collisionComponent.getParent(),new Vec2d(0) ,other.getCollisionShape(),collisionComponent.getCollisionShape());
				
			   collisionComponent.setCollisionInfo(collisionInfoObject1);
			   other.setCollisionInfo(collisionInfoObject2);
		   }*/
		   return mvt;
	   }
	
	
	public final GameObject object1;
	 public final GameObject object2;
	 public final Vec2d mtv;
	 public final CollisionShape thisShape;
	 public final CollisionShape otherShape;
}
