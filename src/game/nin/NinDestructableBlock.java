package game.nin;

import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.components.ParticleEmitterComponent;
import fxengine.math.Vec2d;
import fxengine.system.PhysicsSystem;

public class NinDestructableBlock extends NinAnimatedElement{

	
	
	private long currentTime = 0;
	private boolean setToDestroy = false;
	public NinDestructableBlock(String id, Vec2d initialPosition, String texture, float mass, double restitution) {
		super(id, initialPosition, texture, mass, restitution);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize()
	{
		   ParticleEmitterComponent emitterComponent = (ParticleEmitterComponent)ComponentFactory.getInstance()
				.createComponent(ComponentContants.particles);

			emitterComponent.setParticlesInitialPosition(this.originalPos);
			emitterComponent.setInitParticles(10);
			emitterComponent.setParticleTexture("img/particleBlock.png");
			emitterComponent.setDuration(3);
			emitterComponent.setParticlesMass(0.5f);
			emitterComponent.setParticlesRestitution(0.21f);
			emitterComponent.setParticlesTimeToLive(1);
			emitterComponent.setParticlesIntialDirection(PhysicsSystem.upVector);
			
			emitterComponent.setEnabled(false);



			this.addComponent(emitterComponent);
			
			super.initialize();
	}
	
	
	
	@Override
	public void onTick(long nanosSincePreviousTick)
	{
		if(setToDestroy)
		{
			currentTime+=nanosSincePreviousTick;
			if(currentTime > 75000000)
			{
				((ParticleEmitterComponent)this.getComponent(ComponentContants.particles)).setEnabled(true);
				this.getGameWorld().removeGameObject(this.getId());
			}
		}
		
	}
	
	public void destroyBlock()
	{
		
		setToDestroy = true;
	}
}
