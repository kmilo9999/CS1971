package game.nin;

import fxengine.components.CollisionComponent;
import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.math.Vec2d;

public class NinControllableCharacter extends NinElement{

	public NinControllableCharacter(String id, Vec2d initialPosition,String filePath, float mass, double restitution) {
		super(id, initialPosition,  filePath,  mass , restitution);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initialize() {
		
		super.initialize();
		
		this.setTag("character");
		Component keyControllerComponent = ComponentFactory.getInstance()
				.createComponent(ComponentContants.controllerKeyEvents);
		
		this.addComponent(keyControllerComponent);
		
		CollisionComponent collisionCompomemt = (CollisionComponent)this.getComponent(ComponentContants.collision);
		((CollisionComponent)collisionCompomemt).getHitList().add(NinScene.ENEMY);

	   
	}

}
