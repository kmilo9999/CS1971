package fxengine.system;

import fxengine.components.AnimationComponent;
import fxengine.components.ComponentContants;
import fxengine.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;

public class AnimationSystem extends BaseGameSystem{

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		for(GameObject gameObject: myGameObjects)
		{
			if(gameObject.hasComponent(ComponentContants.animation))
			{
				gameObject.getComponent(ComponentContants.animation).update(nanosSincePreviousTick);
			}
            if(gameObject.hasComponent(ComponentContants.AutoAnimation)) {
            	gameObject.getComponent(ComponentContants.AutoAnimation).update(nanosSincePreviousTick);	
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
