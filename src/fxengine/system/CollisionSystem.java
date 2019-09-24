package fxengine.system;


import fxengine.components.CollisionComponent;
import fxengine.components.ComponentContants;
import javafx.scene.canvas.GraphicsContext;

public class CollisionSystem extends BaseGameSystem{

	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		
		
		for(int i = 0 ; i < myGameObjects.size() ; i++)
		{
			CollisionComponent collisionComponent = (CollisionComponent)myGameObjects.get(i).getComponent(ComponentContants.collision);
			
			if(collisionComponent != null)
			{
				collisionComponent.update(nanosSincePreviousTick);
				for(int j = 0 ; j < myGameObjects.size() ; j++)
				{
					if(!myGameObjects.get(i).getId().equals(myGameObjects.get(j).getId()))
					{
						CollisionComponent collisionComponent2 = (CollisionComponent)myGameObjects.get(j).getComponent(ComponentContants.collision);
						if(collisionComponent2 != null)
						{
							if(collisionComponent2.getHitList().contains(myGameObjects.get(i).getId()))
							{
								// check collision
								if(collisionComponent.getCollisionShape().collides(collisionComponent2.getCollisionShape()))
								{
									//System.out.println("COLLISION!");
									
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
