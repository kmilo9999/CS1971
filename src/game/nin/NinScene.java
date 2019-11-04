package game.nin;

import java.util.ArrayList;
import java.util.List;

import fxengine.application.GameApplication;
import fxengine.components.Animation;
import fxengine.components.CollisionComponent;
import fxengine.components.ComponentContants;
import fxengine.components.PhysicsComponent;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;
import fxengine.math.Vec2i;
import fxengine.objects.GameObject;
import fxengine.objects.GameWorld;
import fxengine.scene.GameWorldScene;
import fxengine.system.PhysicsSystem;



public class NinScene  extends GameWorldScene{
	
	private ResetButton myResetButton;
    
	
	public static String PLAYER = "playerCharacter";
	public static String ENEMY = "enemyCharacter";
	
	NinCharacter mainCharater;
	NinCharacter aiCharater;
	NinPlatform ground;
	NinPlatform ground2;
	NinElement ball;
	NinElement brick;
	
	NinBackground backgroundImage;
	
	Vec2d ninCharacterInitPos = new Vec2d(50, 0);
	Vec2d ninBallInitPos = new Vec2d(105, 0);
	Vec2d ninBrickInitPos = new Vec2d(105, 40);

    List<Animation> animations;
	
	public NinScene(String name, GameApplication application) {
		super(name, application);
		// TODO Auto-generated constructor stub

	    animations = new ArrayList<Animation>();
		
		Animation idle =new Animation(NinCharacter.idle,new Vec2d(0,36),new Vec2d(32,36),new Vec2i(1,3));
		Animation move = new Animation(NinCharacter.moveRight,new Vec2d(0,36),new Vec2d(32,36),new Vec2i(1,3));
		
		
		animations.add(idle);
		animations.add(move);
		
	}
	
	@Override
	public void initScene() 
	{
		
		myResetButton = new ResetButton("img/reset.png",this,700, 70);
		
		
		addProp(myResetButton);
		 // Initialize game world
		super.initScene();
		
		
		
		
		
		//characters
		List<Animation> animations = new ArrayList<Animation>();
		
		Animation idle =new Animation(NinCharacter.idle,new Vec2d(0,36),new Vec2d(32,36),new Vec2i(1,3));
		Animation move = new Animation(NinCharacter.moveRight,new Vec2d(0,36),new Vec2d(32,36),new Vec2i(1,3));
		
		
		animations.add(idle);
		animations.add(move);
		
		
		mainCharater = new NinControllableCharacter("mainCharacter", ninCharacterInitPos,animations);
		
		//aiCharater = new NinAICharacter("ai1", new Vec2d(120, 250),animations);
		
		backgroundImage = new NinBackground("sky");
		ground = new NinPlatform("ground1", new Vec2d(50, 250), "img/ground2.png");
		ground2 = new NinPlatform("ground2", new Vec2d(220, 250), "img/ground2.png");
		ball =  new NinElement("ball1", ninBallInitPos, "img/tenisball.png",  0.9f, 0.2);
		brick = new NinElement("brick1",ninBrickInitPos , "img/otherBrick.png",  1.50f, 0.27);
		
		
		this.myGameWorld.addGameObject(backgroundImage, GameWorld.BackLayer);
		this.myGameWorld.addGameObject(mainCharater, GameWorld.PlayerLayer);
		//this.myGameWorld.addGameObject(aiCharater, GameWorld.EnemyLayer);
		this.myGameWorld.addGameObject(ground, GameWorld.StaticObjectLayer);
		this.myGameWorld.addGameObject(ground2, GameWorld.StaticObjectLayer);
	    this.myGameWorld.addGameObject(brick, GameWorld.EnemyLayer);
		this.myGameWorld.addGameObject(ball, GameWorld.EnemyLayer);
		
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
	
	public void onResetScene()
	{
		((PhysicsSystem)myPhysicsSystem).resetPhysicsSystem();
		((PhysicsComponent)mainCharater.getComponent(ComponentContants.physics)).resetComponent();
		((TransformComponent)mainCharater.getComponent(ComponentContants.transform)).setPosition(ninCharacterInitPos);
		
		((PhysicsComponent)ball.getComponent(ComponentContants.physics)).resetComponent();
		((TransformComponent)ball.getComponent(ComponentContants.transform)).setPosition(ninBallInitPos);
		
		((PhysicsComponent)brick.getComponent(ComponentContants.physics)).resetComponent();
		((TransformComponent)brick.getComponent(ComponentContants.transform)).setPosition(ninBrickInitPos);
	}
	
}
