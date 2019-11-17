package fxengine.system;

import fxengine.components.ComponentContants;
import fxengine.components.ParticleEmitterComponent;
import javafx.scene.canvas.GraphicsContext;

public class EmitterSystem extends BaseGameSystem{

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		for (int i = 0; i < myGameObjects.size(); i++) {
			ParticleEmitterComponent component = (ParticleEmitterComponent) myGameObjects.get(i).getComponent(ComponentContants.particles);
			if(component != null)
			{
				component.update(nanosSincePreviousTick);
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
