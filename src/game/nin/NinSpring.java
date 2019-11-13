package game.nin;

import fxengine.components.CollisionComponent;

import fxengine.components.ComponentContants;

import fxengine.math.Vec2d;

public class NinSpring extends NinElement{

	public NinSpring(String id, Vec2d initialPosition, String texture, float mass, double restitution) {
		super(id, initialPosition, texture, mass, restitution);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initialize() {
		super.initialize();
		/*Component keyControllerComponent = ComponentFactory.getInstance()
				.createComponent(ComponentContants.controllerKeyEvents);
		
		this.addComponent(keyControllerComponent);*/
		
		CollisionComponent collisionCompomemt = (CollisionComponent)this.getComponent(ComponentContants.collision);
		((CollisionComponent)collisionCompomemt).setSpring(true);
	}
}
