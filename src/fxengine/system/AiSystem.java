package fxengine.system;

import fxengine.components.ComponentContants;
import javafx.scene.canvas.GraphicsContext;

public class AiSystem extends BaseGameSystem{

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		for (int i = 0; i < myGameObjects.size(); i++) {
			if(myGameObjects.get(i).hasComponent(ComponentContants.AI)) {
				myGameObjects.get(i).getComponent(ComponentContants.AI).update(nanosSincePreviousTick);
			}
			
			if(myGameObjects.get(i).hasComponent(ComponentContants.AIMovement)) {
				myGameObjects.get(i).getComponent(ComponentContants.AIMovement).update(nanosSincePreviousTick);
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
