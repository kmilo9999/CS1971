package fxengine.system;

import fxengine.collision.Collision;
import fxengine.components.CollisionComponent;
import fxengine.components.ComponentContants;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;

public class PhysicsSystem extends BaseGameSystem{

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		for (int i = 0; i < myGameObjects.size(); i++) {
			CollisionComponent collisionComponent = (CollisionComponent) myGameObjects.get(i)
					.getComponent(ComponentContants.collision);

			if (collisionComponent != null) {
				collisionComponent.update(nanosSincePreviousTick);

				if (!collisionComponent.isCollided()) {
					for (int j = 0; j < myGameObjects.size(); j++) {
						if (!myGameObjects.get(i).getId().equals(myGameObjects.get(j).getId())) {
							CollisionComponent collisionComponent2 = (CollisionComponent) myGameObjects.get(j)
									.getComponent(ComponentContants.collision);
							if (collisionComponent2 != null) {
								if (collisionComponent2.getHitList().contains(myGameObjects.get(i).getTag())
										|| (!collisionComponent.isStatic() && collisionComponent2.isStatic())) {
									
									// check collision mvt
									
									Vec2d mvt = collisionComponent2.getCollisionShape()
											.colliding(collisionComponent.getCollisionShape()); 
									if(mvt != null) 
									{
										Collision collisionInfoObject1;
										Collision collisionInfoObject2;
										if(collisionComponent2.isStatic())
										{
											
											collisionInfoObject1 = new Collision(collisionComponent.getParent(),mvt.smult(2) ,collisionComponent.getCollisionShape(),collisionComponent2.getCollisionShape(), true);
											collisionInfoObject2 = new Collision(collisionComponent2.getParent(),new Vec2d(0) ,collisionComponent2.getCollisionShape(),collisionComponent.getCollisionShape(), false);
											  
											TransformComponent transform = (TransformComponent)myGameObjects.get(i).getComponent(ComponentContants.transform);
											transform.setPosition(transform.getPosition().plus(mvt));
											
							        	    //this.myPosition.plus(mvt);
											
										}	
										else {

											collisionInfoObject1 = new Collision(collisionComponent.getParent(),mvt ,collisionComponent.getCollisionShape(),collisionComponent2.getCollisionShape(), false);
											collisionInfoObject2 = new Collision(collisionComponent2.getParent(),mvt ,collisionComponent2.getCollisionShape(),collisionComponent.getCollisionShape(), false);
										}
										collisionComponent.setCollisionInfo(collisionInfoObject1);
										collisionComponent2.setCollisionInfo(collisionInfoObject2);
									}

								}
							}

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

}
