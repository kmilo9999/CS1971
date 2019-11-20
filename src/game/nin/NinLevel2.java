package game.nin;

import fxengine.components.ComponentContants;
import fxengine.components.PhysicsComponent;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import fxengine.objects.GameWorld;

public class NinLevel2 extends LevelState{

	GameObject ground;
	GameObject ground2;
	GameObject block1;
	GameObject block2;
	GameObject block3;
	GameObject block4;
	GameObject block5;
	GameObject block6;
	GameObject block7;
	GameObject carrot;
	GameObject spring;
	
	
	Vec2d ninDesctBlock1InitPos = new Vec2d(120, 160);
	Vec2d ninDesctBlock2InitPos = new Vec2d(155, 160);
	Vec2d ninDesctBlock3InitPos = new Vec2d(190, 160);
	Vec2d ninDesctBlock4InitPos = new Vec2d(225, 160);
	Vec2d ninDesctBlock5InitPos = new Vec2d(260, 160);
	Vec2d ninDesctBlock6InitPos = new Vec2d(295, 160);
	Vec2d ninDesctBlock7InitPos = new Vec2d(330, 160);
	
	Vec2d ninSpringInitPos = new Vec2d(155, 128);
	Vec2d ninCarrotInitPos = new Vec2d(520, 120);
	
	public NinLevel2(boolean loadFromFIle, String fileName) {
		super(loadFromFIle, fileName);
		
		
		
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		ground = new NinPlatform("ground1", new Vec2d(30, 250), "img/ground3.png");
		ground2 = new NinPlatform("ground2", new Vec2d(480, 100), "img/ground2.png");
		block1 = new NinDestructableBlock("block1", ninDesctBlock1InitPos, "img/shinniBlock.png", 1, 0.7);
		block2 = new NinDestructableBlock("block2", ninDesctBlock2InitPos, "img/shinniBlock.png", 1, 0.7);
		block3 = new NinDestructableBlock("block3", ninDesctBlock3InitPos, "img/shinniBlock.png", 1, 0.7);
		block4 = new NinDestructableBlock("block4", ninDesctBlock4InitPos, "img/shinniBlock.png", 1, 0.7);
		block5 = new NinDestructableBlock("block5", ninDesctBlock5InitPos, "img/shinniBlock.png", 1, 0.7);
		block6 = new NinDestructableBlock("block6", ninDesctBlock6InitPos, "img/shinniBlock.png", 1, 0.7);
		block7 = new NinDestructableBlock("block7", ninDesctBlock7InitPos, "img/shinniBlock.png", 1, 0.7);
		carrot = new NinElement("carrot",ninCarrotInitPos , "img/carrot.png",  1.50f, 0.0);
		spring = new NinSpring("spring1",ninSpringInitPos , "img/spring.png",  0.50f, 0.3);
		
		
		this.myScene.getGameWorld().addGameObject(ground, GameWorld.StaticObjectLayer);
		this.myScene.getGameWorld().addGameObject(ground2, GameWorld.StaticObjectLayer);
		this.myScene.getGameWorld().addGameObject(block1, GameWorld.DestructableLayer);
		this.myScene.getGameWorld().addGameObject(block2, GameWorld.DestructableLayer);
		this.myScene.getGameWorld().addGameObject(block3, GameWorld.DestructableLayer);
		this.myScene.getGameWorld().addGameObject(block4, GameWorld.DestructableLayer);
		this.myScene.getGameWorld().addGameObject(block5, GameWorld.DestructableLayer);
		this.myScene.getGameWorld().addGameObject(block6, GameWorld.DestructableLayer);
		this.myScene.getGameWorld().addGameObject(block7, GameWorld.DestructableLayer);
		this.myScene.getGameWorld().addGameObject(carrot, GameWorld.EnemyLayer);
		this.myScene.getGameWorld().addGameObject(spring, GameWorld.EnemyLayer);
	}

	@Override
	public void onResetLevel() {
		// TODO Auto-generated method stub
		/*for(int i = 1; i < 8 ; i++)
		{
			this.myScene.getGameWorld().removeGameObject("block"+i);	
		}*/
		
		((PhysicsComponent)spring.getComponent(ComponentContants.physics)).resetComponent();
		((TransformComponent)spring.getComponent(ComponentContants.transform)).setPosition(ninSpringInitPos);
		
		((PhysicsComponent)carrot.getComponent(ComponentContants.physics)).resetComponent();
		((TransformComponent)carrot.getComponent(ComponentContants.transform)).setPosition(ninCarrotInitPos);
		
		block1 = new NinDestructableBlock("block1", ninDesctBlock1InitPos, "img/shinniBlock.png", 1, 0.7);
		block2 = new NinDestructableBlock("block2", ninDesctBlock2InitPos, "img/shinniBlock.png", 1, 0.7);
		block3 = new NinDestructableBlock("block3", ninDesctBlock3InitPos, "img/shinniBlock.png", 1, 0.7);
		block4 = new NinDestructableBlock("block4", ninDesctBlock4InitPos, "img/shinniBlock.png", 1, 0.7);
		block5 = new NinDestructableBlock("block5", ninDesctBlock5InitPos, "img/shinniBlock.png", 1, 0.7);
		block6 = new NinDestructableBlock("block6", ninDesctBlock6InitPos, "img/shinniBlock.png", 1, 0.7);
		block7 = new NinDestructableBlock("block7", ninDesctBlock7InitPos, "img/shinniBlock.png", 1, 0.7);
		this.myScene.getGameWorld().addGameObject(block1, GameWorld.DestructableLayer);
		this.myScene.getGameWorld().addGameObject(block2, GameWorld.DestructableLayer);
		this.myScene.getGameWorld().addGameObject(block3, GameWorld.DestructableLayer);
		this.myScene.getGameWorld().addGameObject(block4, GameWorld.DestructableLayer);
		this.myScene.getGameWorld().addGameObject(block5, GameWorld.DestructableLayer);
		this.myScene.getGameWorld().addGameObject(block6, GameWorld.DestructableLayer);
		this.myScene.getGameWorld().addGameObject(block7, GameWorld.DestructableLayer);
		
	}

	@Override
	public void onTick(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		
	}

}
