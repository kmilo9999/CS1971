package game.nin;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import fxengine.UISystem.UISprite;
import fxengine.application.GameApplication;
import fxengine.components.Animation;
import fxengine.components.CollisionComponent;
import fxengine.components.ComponentContants;
import fxengine.components.PhysicsComponent;
import fxengine.components.TransformComponent;
import fxengine.event.Event;
import fxengine.event.EventsConstants;
import fxengine.math.Vec2d;
import fxengine.math.Vec2i;
import fxengine.objects.GameObject;
import fxengine.objects.GameWorld;
import fxengine.scene.GameWorldScene;
import fxengine.system.PhysicsSystem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;



public class NinScene  extends GameWorldScene{
	
	private ResetButton myResetButton;
	private SaveButton mySaveButton;
    public UISprite myFiledSavedMessage;
	
	public static String PLAYER = "playerCharacter";
	public static String ENEMY = "enemyCharacter";
	
	NinControllableCharacter mainCharater;
	NinCharacter aiCharater;
	NinPlatform ground;
	NinPlatform ground2;
	NinPlatform ground3;
	NinElement ball;
	NinElement brick;
	NinElement brick2;
	NinElement spring;
	NinElement carrot;
	
	NinBackground backgroundImage;
	
	Vec2d ninCharacterInitPos = new Vec2d(50, 100);
	Vec2d ninBallInitPos = new Vec2d(105, 0);
	Vec2d ninBrickInitPos = new Vec2d(105, 40);
	Vec2d ninSpringInitPos = new Vec2d(350, 180);
	Vec2d ninCarrotInitPos = new Vec2d(520, 120);
	Vec2d ninBrick2InitPos = new Vec2d(350, 220);	

    
    
    public boolean isShowing = false;
    long countShowing = 0;
    int currentSeconds =0;
    
    boolean showing;
    
    boolean loadingFromFile;
	String levelFileName;
    
	public NinScene(String name, GameApplication application, String loadFile) {
		super(name, application);
		if(!loadFile.isEmpty())
		{
			levelFileName = loadFile;
			loadingFromFile = true;
		}
	}
	
	@Override
	public void initScene() 
	{
		
		myResetButton = new ResetButton("img/reset.png",this,750, 70);
		mySaveButton =  new SaveButton("img/save.png", this,750, 120);
	//	myFiledSavedMessage =  new UISprite("img/filesaved.png",350, 190);
		myFiledSavedMessage =  new UISprite("img/filesaved.png",1000, 1000);
		
		addProp(myResetButton);
		addProp(mySaveButton);
		addProp(myFiledSavedMessage);
		 // Initialize game world
		super.initScene();
		
		
		if(loadingFromFile)
		{
			this.loadState(levelFileName);
		}
		else
		{

			mainCharater = new NinControllableCharacter("mainCharacter",ninCharacterInitPos,"img/bunny.png" ,1.f,0.25);
			
			//aiCharater = new NinAICharacter("ai1", new Vec2d(120, 250),animations);
			
			backgroundImage = new NinBackground("sky");
			ground = new NinPlatform("ground1", new Vec2d(50, 250), "img/ground2.png");
			ground2 = new NinPlatform("ground2", new Vec2d(270, 250), "img/ground2.png");
			ground3 = new NinPlatform("ground3", new Vec2d(500, 150), "img/ground2.png");
			ball =  new NinElement("ball1", ninBallInitPos, "img/tenisball.png",  0.5f, 0.55);
			brick = new NinElement("brick1",ninBrickInitPos , "img/otherBrick.png",  1.50f, 0.27);
			brick2 = new NinElement("brick2",ninBrick2InitPos , "img/otherBrick.png",  1.50f, 0.27);
			carrot = new NinElement("carrot",ninCarrotInitPos , "img/carrot.png",  1.50f, 0.0);
			spring = new NinSpring("spring1",ninSpringInitPos , "img/spring.png",  0.50f, 0.3);
			
			
			this.myGameWorld.addGameObject(backgroundImage, GameWorld.BackLayer);
			this.myGameWorld.addGameObject(mainCharater, GameWorld.PlayerLayer);
			//this.myGameWorld.addGameObject(aiCharater, GameWorld.EnemyLayer);
			this.myGameWorld.addGameObject(ground, GameWorld.StaticObjectLayer);
			this.myGameWorld.addGameObject(ground2, GameWorld.StaticObjectLayer);
			this.myGameWorld.addGameObject(ground3, GameWorld.StaticObjectLayer);
		    this.myGameWorld.addGameObject(brick, GameWorld.EnemyLayer);
		    this.myGameWorld.addGameObject(brick2, GameWorld.EnemyLayer);
			this.myGameWorld.addGameObject(ball, GameWorld.EnemyLayer);
			this.myGameWorld.addGameObject(carrot, GameWorld.EnemyLayer);
			this.myGameWorld.addGameObject(spring, GameWorld.EnemyLayer);
			
		}
	}
	
	@Override
	public void onTick(long nanosSincePreviousTick)
	{
	  if(isShowing)
	  {
		  countShowing+= nanosSincePreviousTick;
		  if(countShowing >= 1000000000)
		  {
			  currentSeconds++;
			  countShowing = 0;
		  }
				  
		  if(currentSeconds > 2)
		  {
			  myFiledSavedMessage.setPosition(new Vec2d(1000, 1000)) ;
			  currentSeconds = 0;
			  countShowing = 0;
			  isShowing = false;
		  }
	  }
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
		
		((PhysicsComponent)brick2.getComponent(ComponentContants.physics)).resetComponent();
		((TransformComponent)brick2.getComponent(ComponentContants.transform)).setPosition(ninBrick2InitPos);
		
		((PhysicsComponent)spring.getComponent(ComponentContants.physics)).resetComponent();
		((TransformComponent)spring.getComponent(ComponentContants.transform)).setPosition(ninSpringInitPos);
		
		((PhysicsComponent)carrot.getComponent(ComponentContants.physics)).resetComponent();
		((TransformComponent)carrot.getComponent(ComponentContants.transform)).setPosition(ninCarrotInitPos);
	}
	
	
}
