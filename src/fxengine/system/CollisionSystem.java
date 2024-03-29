package fxengine.system;


import fxengine.components.CollisionComponent;
import fxengine.components.ComponentContants;
import fxengine.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;

public class CollisionSystem extends BaseGameSystem{

	
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
								if (collisionComponent.getHitList().contains(myGameObjects.get(j).getTag())) {
									// check collision
									if (collisionComponent.getCollisionShape()
											.isColliding(collisionComponent2.getCollisionShape())) {
										System.out.println("COLLISION with "+ myGameObjects.get(j).getTag()+"!");
										collisionComponent.setCollided(true);
										collisionComponent.setColliderId(((GameObject)myGameObjects.get(j)).getId());
										collisionComponent.setOtherCollider((GameObject)myGameObjects.get(j));
										
										collisionComponent2.setCollided(true);
										collisionComponent2.setColliderId(((GameObject)myGameObjects.get(i)).getId());
										collisionComponent2.setOtherCollider((GameObject)myGameObjects.get(i));
										
									
										
										
									}
									/*else
									{
										System.out.println("no COLLISION with "+ myGameObjects.get(j).getTag()+"!");
										collisionComponent.setCollided(false);
										collisionComponent.setColliderId(null);
										collisionComponent.setOtherCollider(null);
									}*/

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
