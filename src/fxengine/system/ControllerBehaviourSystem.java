package fxengine.system;

import fxengine.components.ComponetContants;
import fxengine.components.ControllerBehaviourComponent;
import fxengine.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;

public class ControllerBehaviourSystem extends BaseGameSystem{

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		for(GameObject gameObject:myGameObjects)
		{
			ControllerBehaviourComponent gComponent = (ControllerBehaviourComponent)gameObject.getComponent(ComponetContants.controlled);
			if(gComponent != null)
			{
				gComponent.update(nanosSincePreviousTick);	
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
