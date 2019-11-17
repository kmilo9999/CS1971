package game.nin;

import fxengine.components.CollisionComponent;
import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.components.ParticleEmitterComponent;
import fxengine.math.Vec2d;
import fxengine.system.PhysicsSystem;

public class NinControllableCharacter extends NinElement{

	public NinControllableCharacter(String id, Vec2d initialPosition,String filePath, float mass, double restitution) {
		super(id, initialPosition,  filePath,  mass , restitution);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initialize() {
		
		
		
		this.setTag("character");
		Component keyControllerComponent = ComponentFactory.getInstance()
				.createComponent(ComponentContants.controllerKeyEvents);
		
		ParticleEmitterComponent emitterComponent = (ParticleEmitterComponent)ComponentFactory.getInstance()
				.createComponent(ComponentContants.particles);
		
		emitterComponent.setParticlesInitialPosition(this.originalPos);
		emitterComponent.setInitParticles(10);
		emitterComponent.setParticleTexture("img/particle.png");
		emitterComponent.setDuration(3);
		emitterComponent.setParticlesMass(0.5f);
		emitterComponent.setParticlesRestitution(0.21f);
		emitterComponent.setParticlesTimeToLive(1);
		emitterComponent.setParticlesIntialDirection(PhysicsSystem.upVector);
		
		
		
		this.addComponent(emitterComponent);
		this.addComponent(keyControllerComponent);
		
		super.initialize();
		
		CollisionComponent collisionCompomemt = (CollisionComponent)this.getComponent(ComponentContants.collision);
		((CollisionComponent)collisionCompomemt).getHitList().add(NinScene.ENEMY);
		

	   
	}

}
