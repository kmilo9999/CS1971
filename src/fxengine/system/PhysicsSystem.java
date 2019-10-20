package fxengine.system;

import java.util.List;

import fxengine.collision.Collision;
import fxengine.components.CollisionComponent;
import fxengine.components.ComponentContants;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import fxengine.objects.GameWorld;
import javafx.scene.canvas.GraphicsContext;

public class PhysicsSystem extends BaseGameSystem{

	private List<List<GameObject>> myLayerGameObjects;
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		for (int i = 0; i < myGameObjects.size(); i++) {
			
			if(myGameObjects.get(i).hasComponent(ComponentContants.collision))
			{
				CollisionComponent collisionComponent = (CollisionComponent) myGameObjects.get(i)
						.getComponent(ComponentContants.collision);
				collisionComponent.update(nanosSincePreviousTick);
				
				
				//player collision with enemy
				if(myGameObjects.get(i).getLayerOrder() == GameWorld.PlayerLayer)
						
				{
					// Enemy Layer
					List<GameObject> enemyLayer = myLayerGameObjects.get(GameWorld.EnemyLayer);
					for(GameObject enemy: enemyLayer)
					{
					  if(enemy.hasComponent(ComponentContants.collision))
					  {
							CollisionComponent collisionComponent2 = (CollisionComponent) enemy
									.getComponent(ComponentContants.collision);
							collisionComponent.setCollided(false);
							collisionComponent.setCollisionInfo(null);
							
							if(collisionComponent.getCollisionShape()
									.isColliding(collisionComponent2.getCollisionShape()))
							{
								if (collisionComponent.getHitList().contains(enemy.getTag()))
								{
									collisionComponent.setCollided(true);										
									this.resolveNonStaticCollision(collisionComponent, collisionComponent2);
								}
							}
							
					  }
					}
					
					// Static Objects Layer
					List<GameObject> staticObjectsLayer = myLayerGameObjects.get(GameWorld.StaticObjectLayer);
					for(GameObject staticObject: staticObjectsLayer)
					{
						if(staticObject.hasComponent(ComponentContants.collision))
						{
							CollisionComponent collisionComponent2 = (CollisionComponent) staticObject
									.getComponent(ComponentContants.collision);
							//collisionComponent.setCollided(false);
							//collisionComponent.setCollisionInfo(null);
							
							if(collisionComponent.getCollisionShape()
									.isColliding(collisionComponent2.getCollisionShape()))
							{
								
								collisionComponent.setCollided(true);
								Vec2d mvt = this.resolveStaticCollision(collisionComponent, collisionComponent2);
								TransformComponent transform = (TransformComponent)myGameObjects.get(i).getComponent(ComponentContants.transform);
								transform.setPosition(transform.getPosition().plus(mvt));
								
							}
						}
							
					}
				}
				
				
			}
			
			
			

		}
		
		
		/*if (collisionComponent != null) {
		collisionComponent.update(nanosSincePreviousTick);
		for (int j = 0; j < myGameObjects.size(); j++) 
		{
			if(myGameObjects.get(j).hasComponent(ComponentContants.collision))
			{
				CollisionComponent collisionComponent2 = (CollisionComponent) myGameObjects.get(j)
						.getComponent(ComponentContants.collision);
				
				if(collisionComponent.getCollisionShape()
						.isColliding(collisionComponent2.getCollisionShape()))
				{
					if(myGameObjects.get(j).getLayerOrder() == GameWorld.EnemyLayer)
					{
						
						if (collisionComponent.getHitList().contains(myGameObjects.get(j).getTag()))
						{
							collisionComponent.setCollided(true);										
							this.resolveNonStaticCollision(collisionComponent, collisionComponent2);
						}
						else
						{
							collisionComponent.setCollided(false);
							collisionComponent.setCollisionInfo(null);
						}	
					}
					
				}
				else
				{
					collisionComponent.setCollided(false);
					collisionComponent.setCollisionInfo(null);
				}
				
			}
				
			
		}
		
	}*/
		
		/*for (int i = 0; i < myGameObjects.size(); i++) {
			//player collision with static objects
			CollisionComponent collisionComponent = (CollisionComponent) myGameObjects.get(i)
					.getComponent(ComponentContants.collision);
			if (collisionComponent != null) 
			{
				collisionComponent.update(nanosSincePreviousTick);
				for (int j = 0; j < myGameObjects.size(); j++) 
				{
					if(myGameObjects.get(j).hasComponent(ComponentContants.collision))
					{
						CollisionComponent collisionComponent2 = (CollisionComponent) myGameObjects.get(j)
								.getComponent(ComponentContants.collision);
						
						if(collisionComponent.getCollisionShape()
								.isColliding(collisionComponent2.getCollisionShape()))
						{
							if(myGameObjects.get(j).getLayerOrder() == GameWorld.StaticObjectLayer)
							{
								collisionComponent.setCollided(true);
								Vec2d mvt = this.resolveStaticCollision(collisionComponent, collisionComponent2);
								TransformComponent transform = (TransformComponent)myGameObjects.get(i).getComponent(ComponentContants.transform);
								transform.setPosition(transform.getPosition().plus(mvt));
							}
							else
							{
								collisionComponent.setCollided(false);
								collisionComponent.setCollisionInfo(null);
							}
						}
					}
				}
			}
		}*/
		
	
	}

	@Override
	public void draw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
   private void resolveNonStaticCollision(CollisionComponent collisionComponent, CollisionComponent other)
   {
		Vec2d mvt = other.getCollisionShape()
				.colliding(collisionComponent.getCollisionShape()); 
		if(mvt != null) 
		{
			Collision collisionInfoObject1 = new Collision(other.getParent(),mvt ,collisionComponent.getCollisionShape(),other.getCollisionShape());;
			Collision collisionInfoObject2 = new Collision(collisionComponent.getParent(),mvt ,other.getCollisionShape(),collisionComponent.getCollisionShape());;
			
			collisionComponent.setCollisionInfo(collisionInfoObject1);
			other.setCollisionInfo(collisionInfoObject2);
		}
		
		
   }

   private Vec2d resolveStaticCollision(CollisionComponent collisionComponent, CollisionComponent other)
   {
	   Vec2d mvt = other.getCollisionShape()
				.colliding(collisionComponent.getCollisionShape()); 
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
