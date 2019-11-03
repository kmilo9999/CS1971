package game.nin;

import java.util.ArrayList;
import java.util.List;

import fxengine.application.GameApplication;
import fxengine.components.Animation;
import fxengine.components.CollisionComponent;
import fxengine.components.ComponentContants;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;
import fxengine.math.Vec2i;
import fxengine.objects.GameWorld;
import fxengine.scene.GameWorldScene;


public class NinScene  extends GameWorldScene{

	
	public static String PLAYER = "playerCharacter";
	public static String ENEMY = "enemyCharacter";
	
	NinCharacter mainCharater;
	NinCharacter aiCharater;
	NinPlatform ground;
	NinPlatform ground2;
	NinElement ball;
	NinElement brick;
	
	public NinScene(String name, GameApplication application) {
		super(name, application);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initScene() 
	{
		 // Initialize game world
		super.initScene();
		
		
		//characters
		List<Animation> animations = new ArrayList<Animation>();
		
		Animation idle =new Animation(NinCharacter.idle,new Vec2d(0,36),new Vec2d(32,36),new Vec2i(1,3));
		Animation move = new Animation(NinCharacter.moveRight,new Vec2d(0,36),new Vec2d(32,36),new Vec2i(1,3));
		
		
		animations.add(idle);
		animations.add(move);
		
		
		mainCharater = new NinControllableCharacter("mainCharacter", new Vec2d(50, 190),animations);
		
		//aiCharater = new NinAICharacter("ai1", new Vec2d(120, 250),animations);
		
		ground = new NinPlatform("ground1", new Vec2d(50, 250), "img/ground2.png");
		ground2 = new NinPlatform("ground2", new Vec2d(220, 250), "img/ground2.png");
		ball =  new NinElement("ball1", new Vec2d(105, 0), "img/tenisball.png",  1.25f, 1.25);
		brick = new NinElement("brick1", new Vec2d(105, 40), "img/otherBrick.png",  2.50f, 1.17);
		
		this.myGameWorld.addGameObject(mainCharater, GameWorld.PlayerLayer);
		//this.myGameWorld.addGameObject(aiCharater, GameWorld.EnemyLayer);
		this.myGameWorld.addGameObject(ground, GameWorld.StaticObjectLayer);
		this.myGameWorld.addGameObject(ground2, GameWorld.StaticObjectLayer);
		//this.myGameWorld.addGameObject(brick, GameWorld.EnemyLayer);
		//this.myGameWorld.addGameObject(ball, GameWorld.EnemyLayer);
		
	}
	
	@Override
	public void onTick(long nanosSincePreviousTick)
	{
	  //Logic Here
	  /*if(mainCharater.hasComponent(ComponentContants.collision))
	  {
		  CollisionComponent collisionComponent = (CollisionComponent) mainCharater.getComponent(ComponentContants.collision);
		  
		  if(collisionComponent.isCollided() &&  collisionComponent.getCollisionInfo()!= null)
		  {
			  if(collisionComponent.getCollisionInfo().other.getTag() == ENEMY)
			  {
				  System.out.println("COLLIDE IN SCENE");  
				  TransformComponent mainCharacterTransform = (TransformComponent) mainCharater.getComponent(ComponentContants.transform);
				  Vec2d mvt = collisionComponent.getCollisionInfo().mtv.smult(50);
				  
				  mainCharacterTransform.setPosition(mainCharacterTransform.getPosition().plus(mvt));
			  }
			  
		  }
	  }*/
		  
	  super.onTick(nanosSincePreviousTick);	
	}
	
	
}
