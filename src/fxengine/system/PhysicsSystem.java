package fxengine.system;

import fxengine.collision.Collision;
import fxengine.components.CollisionComponent;
import fxengine.components.ComponentContants;
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
								if (collisionComponent2.getHitList().contains(myGameObjects.get(i).getTag())) {
									
									// check collision mvt
									Vec2d mvt = collisionComponent.getCollisionShape()
											.colliding(collisionComponent2.getCollisionShape()); 
									if(mvt != null) 
									{
										if( mvt.x != 0 || mvt.y != 0)
										{
											//System.out.println("COLLISION!");
											Collision collisionInfo = new Collision(collisionComponent2.getParent(),mvt,collisionComponent.getCollisionShape(),collisionComponent2.getCollisionShape()); 
											collisionComponent.setCollisionInfo(collisionInfo);											
										}

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
