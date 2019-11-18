package game.nin;

import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import fxengine.objects.GameWorld;
import fxengine.scene.BaseScene;

import java.util.List;

import fxengine.components.CollisionComponent;
import fxengine.components.ComponentContants;
import fxengine.components.PhysicsComponent;
import fxengine.components.SpriteComponent;
import fxengine.components.TransformComponent;

public class NinLevel1 extends LevelState{
	
	GameObject ground;
	GameObject ground2;
	GameObject ground3;
	GameObject ball;
	GameObject brick;
	GameObject brick2;
	GameObject spring;
	GameObject carrot;
	
	Vec2d ninBallInitPos = new Vec2d(105, 0);
	Vec2d ninBrickInitPos = new Vec2d(105, 40);
	Vec2d ninSpringInitPos = new Vec2d(350, 180);
	Vec2d ninCarrotInitPos = new Vec2d(520, 120);
	Vec2d ninBrick2InitPos = new Vec2d(350, 220);	


	public NinLevel1(boolean loadFromFIle, String fileName) {
		super(loadFromFIle, fileName);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void initialize() {
		// TODO Auto-generated method stub
	
		
		if(myLoadFromFile)
		{
			this.myScene.loadState(myFileName);
			
			List<GameObject> enemies = this.myScene.getGameWorld().getGameObjectsByLayer(GameWorld.EnemyLayer); 
            for(GameObject go:enemies)
            {
            	if(go.getId().equals("ball1") )
            	{
            		ball = go;	
            	}
            	else if(go.getId().equals("brick1"))
            	{
            		brick = go;			
            	}
            	else if(go.getId().equals("brick2"))
            	{
            		brick2 = go;	
            	}
            	else if(go.getId().equals("carrot"))
            	{
            		carrot = go;
            	}
            	else if(go.getId().equals("spring1"))
            	{
            		spring = go;	
            	}
    			
    				
            }
		    
		}
		else
		{

			
			
			
			
			//aiCharater = new NinAICharacter("ai1", new Vec2d(120, 250),animations);
			
			
			ground = new NinPlatform("ground1", new Vec2d(30, 250), "img/ground2.png");
			ground2 = new NinPlatform("ground2", new Vec2d(300, 250), "img/ground2.png");
			ground3 = new NinPlatform("ground3", new Vec2d(500, 150), "img/ground2.png");
			ball =  new NinElement("ball1", ninBallInitPos, "img/tenisball.png",  0.5f, 0.55);
			brick = new NinElement("brick1",ninBrickInitPos , "img/otherBrick.png",  1.50f, 0.27);
			brick2 = new NinElement("brick2",ninBrick2InitPos , "img/otherBrick.png",  1.50f, 0.27);
			carrot = new NinElement("carrot",ninCarrotInitPos , "img/carrot.png",  1.50f, 0.0);
			spring = new NinSpring("spring1",ninSpringInitPos , "img/spring.png",  0.50f, 0.3);
			
			
		
			this.myScene.getGameWorld().addGameObject(ground, GameWorld.StaticObjectLayer);
			this.myScene.getGameWorld().addGameObject(ground2, GameWorld.StaticObjectLayer);
			this.myScene.getGameWorld().addGameObject(ground3, GameWorld.StaticObjectLayer);
			this.myScene.getGameWorld().addGameObject(brick, GameWorld.EnemyLayer);
			this.myScene.getGameWorld().addGameObject(brick2, GameWorld.EnemyLayer);
			this.myScene.getGameWorld().addGameObject(ball, GameWorld.EnemyLayer);
			this.myScene.getGameWorld().addGameObject(carrot, GameWorld.EnemyLayer);
			this.myScene.getGameWorld().addGameObject(spring, GameWorld.EnemyLayer);
			
		}
	}

	@Override
	public void onResetLevel() {
		// TODO Auto-generated method stub
		((PhysicsComponent)brick.getComponent(ComponentContants.physics)).resetComponent();
		((TransformComponent)brick.getComponent(ComponentContants.transform)).setPosition(ninBrickInitPos);
		
		
		((PhysicsComponent)ball.getComponent(ComponentContants.physics)).resetComponent();
		((TransformComponent)ball.getComponent(ComponentContants.transform)).setPosition(ninBallInitPos);
		
		((PhysicsComponent)brick2.getComponent(ComponentContants.physics)).resetComponent();
		((TransformComponent)brick2.getComponent(ComponentContants.transform)).setPosition(ninBrick2InitPos);
		
		((PhysicsComponent)spring.getComponent(ComponentContants.physics)).resetComponent();
		((TransformComponent)spring.getComponent(ComponentContants.transform)).setPosition(ninSpringInitPos);
		
		((PhysicsComponent)carrot.getComponent(ComponentContants.physics)).resetComponent();
		((TransformComponent)carrot.getComponent(ComponentContants.transform)).setPosition(ninCarrotInitPos);
	}

	@Override
	public void onTick(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		
	}

}
