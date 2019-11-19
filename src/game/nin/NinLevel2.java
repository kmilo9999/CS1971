package game.nin;

import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import fxengine.objects.GameWorld;

public class NinLevel2 extends LevelState{

	GameObject ground;
	GameObject block1;
	
	
	
	public NinLevel2(boolean loadFromFIle, String fileName) {
		super(loadFromFIle, fileName);
		
		
		
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		ground = new NinPlatform("ground1", new Vec2d(30, 250), "img/ground3.png");
		block1 = new NinDestructableBlock("block1", new Vec2d(120, 160), "img/shinniBlock.png", 1, 0.7);
		this.myScene.getGameWorld().addGameObject(ground, GameWorld.StaticObjectLayer);
		this.myScene.getGameWorld().addGameObject(block1, GameWorld.DestructableLayer);
	}

	@Override
	public void onResetLevel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTick(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		
	}

}
