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
		
		if(c1.getCollisionShape()
				.isColliding(c2.getCollisionShape()))
		{
			if(c1.isStatic() || c2.isStatic())
			{
				resolveStaticCollision(c1, c2, deltaTime);
				
			}
			
			else
			{
				resolveNonStaticCollision(c1,c2, deltaTime);
			}
		}
		
		
	}
	
	private Vec2d resolveSpringCollision(CollisionComponent collisionComponent, CollisionComponent other, double deltaTime) {
		  
		   Vec2d mvt1 = other.getCollisionShape()
					.colliding(collisionComponent.getCollisionShape());
		   
		   Vec2d currentPosition1 = 
					  ((TransformComponent)collisionComponent.getParent().getComponent(ComponentContants.transform))
					  .getPosition();

		   currentPosition1 = currentPosition1.plus(mvt1.smult(0.5));
			  
			  
		  //move away half vector
			((TransformComponent)collisionComponent.getParent().getComponent(ComponentContants.transform))
			  .setPosition(currentPosition1);
			   
		   
		   Vec2d mvt2 = collisionComponent.getCollisionShape()
					.colliding(other.getCollisionShape());
		   
		   if(mvt1.isZero() && mvt2.isZero())
		   {
			   return null;
		   }
		   
		   
		   Vec2d currentPosition2 = 
					  ((TransformComponent)other.getParent().getComponent(ComponentContants.transform))
					  .getPosition();
		   currentPosition2 = currentPosition2.plus(mvt2.smult(0.5));
		   
		   //move away half vector
			((TransformComponent)other.getParent().getComponent(ComponentContants.transform))
			  .setPosition(currentPosition2);
		   
		   
		   Vec2d normalizedMvt1 = mvt1.normalize();
		   Vec2d normalizedMvt2 = mvt2.normalize();
		   
		   
		   if(collisionComponent.getParent().hasComponent(ComponentContants.physics)
				   && other.getParent().hasComponent(ComponentContants.physics))
		   {
			   
			   
			   
			   PhysicsComponent physicsComponent = (PhysicsComponent)collisionComponent.getParent().getComponent(ComponentContants.physics); 
			   PhysicsComponent otherPhysicsComponent = (PhysicsComponent)other.getParent().getComponent(ComponentContants.physics);
			   
			   //if (otherPhysicsComponent.isOnStacticObject() && normalizedMvt2.y > 0) 
			   if (otherPhysicsComponent.isOnStacticObject() && normalizedMvt2.y > 0)
				{

					Vec2d velocityAfterCollision1 = physicsComponent.resolveVelocity(otherPhysicsComponent);
	
					Vec2d velocityAfterCollision2 = otherPhysicsComponent.resolveVelocity(physicsComponent);
	
					double sVelocityAfterCollision1 = velocityAfterCollision1.dot(normalizedMvt1);
					double sVelocityAfterCollision2 = velocityAfterCollision2.dot(normalizedMvt2);
					mvt1 = normalizedMvt1.smult(sVelocityAfterCollision1);
					mvt2 = normalizedMvt2.smult(sVelocityAfterCollision2);
	
					physicsComponent.setVelocity(mvt2);
					//otherPhysicsComponent.setVelocity(mvt1);
	
					Vec2d impulse1 = physicsComponent.resolveImpulse(otherPhysicsComponent);
					
					physicsComponent.applyImpulse(impulse1);
					
	
					//Vec2d impulse2 = otherPhysicsComponent.resolveImpulse(physicsComponent);
				
					//otherPhysicsComponent.applyImpulse(impulse2);
				}
				
				else
				{
					Vec2d velocityAfterCollision1 = physicsComponent.resolveVelocity(otherPhysicsComponent);
	
					Vec2d velocityAfterCollision2 = otherPhysicsComponent.resolveVelocity(physicsComponent);
	
					double sVelocityAfterCollision1 = velocityAfterCollision1.dot(normalizedMvt1);
					double sVelocityAfterCollision2 = velocityAfterCollision2.dot(normalizedMvt2);
					mvt1 = normalizedMvt1.smult(sVelocityAfterCollision1);
					mvt2 = normalizedMvt2.smult(sVelocityAfterCollision2);
	
					physicsComponent.setVelocity(mvt2);
					otherPhysicsComponent.setVelocity(mvt1);
	
					Vec2d impulse1 = physicsComponent.resolveImpulse(otherPhysicsComponent);
					
					physicsComponent.applyImpulse(impulse1);
					
	
					Vec2d impulse2 = otherPhysicsComponent.resolveImpulse(physicsComponent);
				
					otherPhysicsComponent.applyImpulse(impulse2);
					
				}
				
			   
		   }
		   
		   return mvt1;
		
	}


	private Vec2d resolveNonStaticCollision(CollisionComponent collisionComponent, CollisionComponent other, double deltaTime)
	{
		  
		   Vec2d mvt1 = other.getCollisionShape()
					.colliding(collisionComponent.getCollisionShape());
		   
		   Vec2d currentPosition1 = 
					  ((TransformComponent)collisionComponent.getParent().getComponent(ComponentContants.transform))
					  .getPosition();

		   currentPosition1 = currentPosition1.plus(mvt1.smult(0.5));
			  
			  
		  //move away half vector
			((TransformComponent)collisionComponent.getParent().getComponent(ComponentContants.transform))
			  .setPosition(currentPosition1);
			   
		   
		   Vec2d mvt2 = collisionComponent.getCollisionShape()
					.colliding(other.getCollisionShape());
		   
		   if(mvt1.isZero() && mvt2.isZero())
		   {
			   return null;
		   }
		   
		   
		   Vec2d currentPosition2 = 
					  ((TransformComponent)other.getParent().getComponent(ComponentContants.transform))
					  .getPosition();
		   currentPosition2 = currentPosition2.plus(mvt2.smult(0.5));
		   
		   //move away half vector
			((TransformComponent)other.getParent().getComponent(ComponentContants.transform))
			  .setPosition(currentPosition2);
		   
		   
		   Vec2d normalizedMvt1 = mvt1.normalize();
		   Vec2d normalizedMvt2 = mvt2.normalize();
		   
		   
		   if(collisionComponent.getParent().hasComponent(ComponentContants.physics)
				   && other.getParent().hasComponent(ComponentContants.physics))
		   {
			   
			   
			   
			   PhysicsComponent physicsComponent = (PhysicsComponent)collisionComponent.getParent().getComponent(ComponentContants.physics); 
			   PhysicsComponent otherPhysicsComponent = (PhysicsComponent)other.getParent().getComponent(ComponentContants.physics);
			   
			   //if (otherPhysicsComponent.isOnStacticObject() && normalizedMvt2.y > 0) 
			    if (otherPhysicsComponent.isOnStacticObject() && normalizedMvt2.y > 0)
				{
			    	if(other.isSpring())
			    	{
			    		return resolveSpringCollision(collisionComponent, other, deltaTime);
			    	}
			    	else
			    	{
			    		return resolveStaticCollision(collisionComponent, other, deltaTime);	
			    	}
					
				}
				
				else
				{
					Vec2d velocityAfterCollision1 = physicsComponent.resolveVelocity(otherPhysicsComponent);
	
					Vec2d velocityAfterCollision2 = otherPhysicsComponent.resolveVelocity(physicsComponent);
	
					double sVelocityAfterCollision1 = velocityAfterCollision1.dot(normalizedMvt1);
					double sVelocityAfterCollision2 = velocityAfterCollision2.dot(normalizedMvt2);
					mvt1 = normalizedMvt1.smult(sVelocityAfterCollision1);
					mvt2 = normalizedMvt2.smult(sVelocityAfterCollision2);
	
					physicsComponent.setVelocity(mvt2);
					otherPhysicsComponent.setVelocity(mvt1);
	
					Vec2d impulse1 = physicsComponent.resolveImpulse(otherPhysicsComponent);
					
					physicsComponent.applyImpulse(impulse1);
					
	
					Vec2d impulse2 = otherPhysicsComponent.resolveImpulse(physicsComponent);
				
					otherPhysicsComponent.applyImpulse(impulse2);
					
				}
				
			   
		   }
		   
		   return mvt1;
	}
	
	
	private Vec2d resolveStaticCollision(CollisionComponent collisionComponent, CollisionComponent other , double deltaTime)
	   {	
		   
		  Vec2d mvt = other.getCollisionShape()
					.colliding(collisionComponent.getCollisionShape()); 
		  
		  if(mvt.isZero() )
		   {
			   return null;
		   }
		  
		  Vec2d currentPosition = 
				  ((TransformComponent)collisionComponent.getParent().getComponent(ComponentContants.transform))
				  .getPosition();

		  currentPosition = currentPosition.plus(mtv.smult(0.5));
		  
		  //move away half vector
		  ((TransformComponent)collisionComponent.getParent().getComponent(ComponentContants.transform))
		  .setPosition(currentPosition);;
		   
		   if(collisionComponent.getParent().hasComponent(ComponentContants.physics)
				   && other.getParent().hasComponent(ComponentContants.physics))
		   {
			  PhysicsComponent physicsComponent = (PhysicsComponent)collisionComponent.getParent().getComponent(ComponentContants.physics); 
			  PhysicsComponent otherPhysicsComponent = (PhysicsComponent)other.getParent().getComponent(ComponentContants.physics);
			  
			  
			  Vec2d normalizedMvt = mvt.normalize();
			  
			
			  
			  if(  normalizedMvt.y < 0 )
			  {
				  Vec2d velocityAfterCollision =  physicsComponent.resolveVelocity(otherPhysicsComponent);
				  double sVelocityAfterCollision = velocityAfterCollision.dot(normalizedMvt);
				  mvt = normalizedMvt.smult(sVelocityAfterCollision);
				  physicsComponent.setVelocity(mvt);
				  
				  
				  Vec2d impulse = physicsComponent.resolveImpulse(otherPhysicsComponent);
				  //System.out.println("impulse "+impulse);
				  physicsComponent.applyImpulse(impulse);
				  physicsComponent.setOnStacticObject(true);
			  }else if (normalizedMvt.x !=0 &&  normalizedMvt.y == 0 )
			  {
				  Vec2d velocityAfterCollision =  physicsComponent.resolveVelocity(otherPhysicsComponent);
				  double sVelocityAfterCollision = velocityAfterCollision.dot(normalizedMvt);
				  mvt = normalizedMvt.smult(sVelocityAfterCollision);
				  physicsComponent.setVelocity(mvt);
				  
				  /*Vec2d pos = ((TransformComponent)collisionComponent.getParent()
						  .getComponent(ComponentContants.transform)).getPosition();
				  
				  pos = pos.plus(mvt);
				  ((TransformComponent)collisionComponent.getParent()
						  .getComponent(ComponentContants.transform)).setPosition(pos);*/
				 
				  
				  Vec2d impulse = physicsComponent.resolveImpulse(otherPhysicsComponent);
				  //System.out.println("impulse "+impulse);
				  physicsComponent.applyImpulse(impulse);
				  physicsComponent.setOnStacticObject(false);
			  }
			  else
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
