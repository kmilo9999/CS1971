package fxengine.system;

import java.util.ArrayList;
import java.util.List;

import fxengine.collision.Collision;
import fxengine.collision.PhysicsCollision;
import fxengine.components.CollisionComponent;
import fxengine.components.ComponentContants;
import fxengine.components.PhysicsComponent;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import fxengine.objects.GameWorld;
import javafx.scene.canvas.GraphicsContext;

public class PhysicsSystem extends BaseGameSystem{

	private List<List<GameObject>> myLayerGameObjects;
	private static final Vec2d gravity = new Vec2d(0,0.01); 
	
	private long start = 0;
	
	private final double MAX_DELTA_TIME = 1.0;
	private final double DESIRED_FRAME_RATE = 1000.0 / 60.0;
	private final int MAX_PHYSICS_STEPS = 6;
	
	List<PhysicsCollision> myCollisions = new ArrayList<PhysicsCollision>(); 
	
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		start = System.currentTimeMillis();
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		
		// TODO Auto-generated method stub
		
		long newTime = System.currentTimeMillis();
		long frameTime = newTime - start;
		start = newTime;
		double totalDeltaTime = (double) frameTime /DESIRED_FRAME_RATE;
		
		int i = 0;
		while ( totalDeltaTime > 0.0  && i < MAX_PHYSICS_STEPS)
        {
			 double deltaTime = Math.min( totalDeltaTime, MAX_DELTA_TIME );
			 applyGravity();
			 updateTransform(deltaTime);
			 checkCollision(deltaTime);
			 resolveCollisions(deltaTime);
			 totalDeltaTime -= deltaTime;
	         i++;
        }
		
		//double enlapse = (double)(nanosSincePreviousTick) / MS_PER_UPDATE;
		//delta += (enlapse);
		//System.out.println(delta);
		//delta+=(double)(start - end) / MS_PER_UPDATE;
		//end = start;
		
		//while (delta >= 1.0) {
		//if(delta >= 1) {
		//	System.out.println(1);
		//	checkCollision(dt);
		//	applyGravity();
		//    updateTransform(dt);
		   // delta -= 1.0;
		//    delta =0;
		//}
		
		//checkCollision(nanosSincePreviousTick);
		//applyGravity();
	    //updateTransform(nanosSincePreviousTick);
	}

	private void resolveCollisions(double deltaTime) {
		// TODO Auto-generated method stub
		for(PhysicsCollision pCollision:myCollisions )
		{
			pCollision.resolveCollision(deltaTime);
		}
		
		myCollisions.clear();
	}

	private void updateTransform(double deltaTime) {
		// TODO Auto-generated method stub
		for (int i = 0; i < myGameObjects.size(); i++) 
		{
			if (myGameObjects.get(i).hasComponent(ComponentContants.physics)) 
			{
				((PhysicsComponent)myGameObjects.get(i).getComponent(ComponentContants.physics)).lateTick(deltaTime);
			}
		}
	}

	private void applyGravity() {
		// TODO Auto-generated method stub
		for (int i = 0; i < myGameObjects.size(); i++) 
		{
			if (myGameObjects.get(i).hasComponent(ComponentContants.physics)) 
			{
				if(!((PhysicsComponent)myGameObjects.get(i).getComponent(ComponentContants.physics)).isOnStacticObject())
				{
					((PhysicsComponent)myGameObjects.get(i).getComponent(ComponentContants.physics)).applyForce(gravity);	
				}
									
			}
		}
	}

	private void checkCollision(double  deltaTIme) {
		// TODO Auto-generated method stub
		for (int i = 0; i < myGameObjects.size(); i++) {

			if (myGameObjects.get(i).hasComponent(ComponentContants.collision)) {
				CollisionComponent collisionComponent = (CollisionComponent) myGameObjects.get(i)
						.getComponent(ComponentContants.collision);
				collisionComponent.update((long)deltaTIme);

				// player collision with enemy
				if (myGameObjects.get(i).getLayerOrder() == GameWorld.PlayerLayer)

				{
					// Enemy Layer
					List<GameObject> enemyLayer = myLayerGameObjects.get(GameWorld.EnemyLayer);
					for (GameObject enemy : enemyLayer) {
						if (enemy.hasComponent(ComponentContants.collision)) {
							CollisionComponent collisionComponent2 = (CollisionComponent) enemy
									.getComponent(ComponentContants.collision);
							//collisionComponent.setCollided(false);
							//collisionComponent.setCollisionInfo(null);

							if (collisionComponent.getCollisionShape()
									.isColliding(collisionComponent2.getCollisionShape())) {
								if (collisionComponent.getHitList().contains(enemy.getTag())) {
								
									//collisionComponent.setCollided(true);
									this.checkPhysicsCollision(collisionComponent, collisionComponent2);
								}
							}

						}
					}

					// Static Objects Layer
					List<GameObject> staticObjectsLayer = myLayerGameObjects.get(GameWorld.StaticObjectLayer);
					for (GameObject staticObject : staticObjectsLayer) {
						if (staticObject.hasComponent(ComponentContants.collision)) {
							CollisionComponent collisionComponent2 = (CollisionComponent) staticObject
									.getComponent(ComponentContants.collision);
							// collisionComponent.setCollided(false);
							// collisionComponent.setCollisionInfo(null);

							if (collisionComponent.getCollisionShape()
									.isColliding(collisionComponent2.getCollisionShape())) {

								//collisionComponent.setCollided(true);
								this.checkPhysicsCollision(collisionComponent, collisionComponent2);
								
								//Vec2d mvt = this.resolveStaticCollision(collisionComponent, collisionComponent2);
								
								//TransformComponent transform = (TransformComponent) myGameObjects.get(i)
								//		.getComponent(ComponentContants.transform);
								//transform.setPosition(transform.getPosition().plus(mvt));
							}//else
							//{
							//	if(collisionComponent.getParent().hasComponent(ComponentContants.physics) 
							//			&& ((PhysicsComponent)collisionComponent.getParent().getComponent(ComponentContants.physics)).isOnStacticObject())
							//	{
							//		((PhysicsComponent)collisionComponent.getParent().getComponent(ComponentContants.physics)).setOnStacticObject(false);
							//	}
							//}
						}

					}
				}

			}

		}

	}

	@Override
	public void draw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
   private void checkPhysicsCollision(CollisionComponent collisionComponent, CollisionComponent other)
   {
		Vec2d mvt = other.getCollisionShape()
				.colliding(collisionComponent.getCollisionShape()); 
		if(mvt != null) 
		{
			PhysicsCollision physicsCollision = new PhysicsCollision(collisionComponent.getParent(),
					other.getParent(), mvt, collisionComponent.getCollisionShape(), other.getCollisionShape());
			this.myCollisions.add(physicsCollision);
			
			//Collision collisionInfoObject1 = new Collision(other.getParent(),mvt ,collisionComponent.getCollisionShape(),other.getCollisionShape());;
			//Collision collisionInfoObject2 = new Collision(collisionComponent.getParent(),mvt ,other.getCollisionShape(),collisionComponent.getCollisionShape());;
			
			//collisionComponent.setCollisionInfo(collisionInfoObject1);
			//other.setCollisionInfo(collisionInfoObject2);
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

	public List<List<GameObject>> getLayerGameObjects() {
		return myLayerGameObjects;
	}

	public void setLayerGameObjects(List<List<GameObject>> layerGameObjects) {
		this.myLayerGameObjects = layerGameObjects;
	}
   

}
