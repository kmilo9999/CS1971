package fxengine.collision;

import java.util.Vector;

import fxengine.components.CollisionComponent;
import fxengine.components.ComponentContants;
import fxengine.components.PhysicsComponent;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import fxengine.system.PhysicsSystem;

public class PhysicsCollision {
	
	private double maxImpulse = 25;
	private double minImpulse = 1;
	
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
			resolveNonStaticCollision(c1,c2);
		}
	}
	
	private Vec2d resolveNonStaticCollision(CollisionComponent collisionComponent, CollisionComponent other)
	{
		  
		   Vec2d mvt1 = other.getCollisionShape()
					.colliding(collisionComponent.getCollisionShape());
		   
		   Vec2d mvt2 = other.getCollisionShape()
					.colliding(collisionComponent.getCollisionShape()); 
		   
		   if(collisionComponent.getParent().hasComponent(ComponentContants.physics)
				   && other.getParent().hasComponent(ComponentContants.physics))
		   {
			   
			   PhysicsComponent physicsComponent = (PhysicsComponent)collisionComponent.getParent().getComponent(ComponentContants.physics); 
			   PhysicsComponent otherPhysicsComponent = (PhysicsComponent)other.getParent().getComponent(ComponentContants.physics);
				  
			   Vec2d velocityAfterCollision1 =  physicsComponent.resolveVelocity(otherPhysicsComponent);
			   Vec2d normalizedMvt1 = mvt1.normalize();
			   
			   Vec2d velocityAfterCollision2 =  otherPhysicsComponent.resolveVelocity(physicsComponent);
			   Vec2d normalizedMvt2 = mvt2.normalize();
				  
			   double sVelocityAfterCollision1 = velocityAfterCollision1.dot(normalizedMvt1);
			   mvt1 = mvt1.smult(sVelocityAfterCollision1);
			   physicsComponent.setVelocity(mvt1);
				  
			   double sVelocityAfterCollision2 = velocityAfterCollision2.dot(normalizedMvt2);
			   mvt2 = mvt2.smult(sVelocityAfterCollision2);
			   otherPhysicsComponent.setVelocity(mvt2);
			   
			   
			   
			   Vec2d impulse1 = physicsComponent.resolveImpulse(otherPhysicsComponent);
			   System.out.println("impulse "+ impulse1 +"from: "+otherPhysicsComponent.getParent().getId()+" to "+ physicsComponent.getParent().getId());
			   impulse1 = impulse1.pmult(PhysicsSystem.upVector); 
			   if(!physicsComponent.isOnStacticObject())
			   {
				   physicsComponent.applyImpulse(impulse1);   
			   }
			   
			   
			   Vec2d impulse2 = otherPhysicsComponent.resolveImpulse(physicsComponent);
			   System.out.println("impulse "+ impulse2 +"from: "+physicsComponent.getParent().getId()+" to "+ otherPhysicsComponent.getParent().getId());
			   
			   
			   if(impulse2.mag() > 15)
			   {
				   double sqrMagnitud = impulse2.mag2();
				   impulse2 =  impulse2.smult(0.25);
			   }
			   
			   if(impulse2.mag() > 0.13 )
			   {
				   if(!otherPhysicsComponent.isOnStacticObject())
				   {
					   impulse2 = impulse2.pmult(PhysicsSystem.upVector); 
					   otherPhysicsComponent.applyImpulse(impulse2);   
				   }      
			   }
			   
			   
		   }
		   
		   return mvt1;
	}
	
	
	private Vec2d resolveStaticCollision(CollisionComponent collisionComponent, CollisionComponent other)
	   {
		   
		   Vec2d mvt = other.getCollisionShape()
					.colliding(collisionComponent.getCollisionShape()); 
		   
		   if(collisionComponent.getParent().hasComponent(ComponentContants.physics)
				   && other.getParent().hasComponent(ComponentContants.physics))
		   {
			  PhysicsComponent physicsComponent = (PhysicsComponent)collisionComponent.getParent().getComponent(ComponentContants.physics); 
			  PhysicsComponent otherPhysicsComponent = (PhysicsComponent)other.getParent().getComponent(ComponentContants.physics);
			  
			  Vec2d velocityAfterCollision =  physicsComponent.resolveVelocity(otherPhysicsComponent);
			  Vec2d normalizedMvt = mvt.normalize();
			  
			  double sVelocityAfterCollision = velocityAfterCollision.dot(normalizedMvt);
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
		   
		   return mvt;
	   }
	
	
	public final GameObject object1;
	 public final GameObject object2;
	 public final Vec2d mtv;
	 public final CollisionShape thisShape;
	 public final CollisionShape otherShape;
}
