package game.nin;

import fxengine.math.Vec2d;

public class NinProjectile extends NinElement{

	private int timeToLive = 2;
	private long timer = 0;
	private boolean destroy = false;
	
	public NinProjectile(String id, Vec2d initialPosition, String texture, float mass ,double restitution) {
		super(id, initialPosition, texture, mass, restitution);
	}
	
	@Override
	public void onTick(long nanosSincePreviousTick)
	{
		timer+=nanosSincePreviousTick;
		if(timer >= 1000000000)
		{
			timeToLive--;
		}
		
		if(timeToLive <= 0)
		{
			destroy = true;     
     	}
	}

	public boolean isDestroy() {
		return destroy;
	}
}
