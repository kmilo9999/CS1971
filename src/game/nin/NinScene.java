package game.nin;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import fxengine.UISystem.UIConstants;
import fxengine.UISystem.UILine;
import fxengine.UISystem.UISprite;
import fxengine.application.GameApplication;
import fxengine.components.Animation;
import fxengine.components.CollisionComponent;
import fxengine.components.ComponentContants;
import fxengine.components.PhysicsComponent;
import fxengine.components.SpriteComponent;
import fxengine.components.TransformComponent;
import fxengine.event.Event;
import fxengine.event.EventsConstants;
import fxengine.graphics.Line;
import fxengine.math.Vec2d;
import fxengine.math.Vec2i;
import fxengine.objects.GameObject;
import fxengine.objects.GameWorld;
import fxengine.raycasting.Ray;
import fxengine.scene.GameWorldScene;
import fxengine.system.PhysicsSystem;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;



public class NinScene  extends GameWorldScene{
	
	private ResetButton myResetButton;
	private SaveButton mySaveButton;
    public UISprite myFiledSavedMessage;
    public UISprite myIntroMessage;
	
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
	UILine rayLine;
	UILine ray;
	
	NinBackground backgroundImage;
	
	Vec2d ninCharacterInitPos = new Vec2d(50, 100);
	Vec2d ninBallInitPos = new Vec2d(105, 0);
	Vec2d ninBrickInitPos = new Vec2d(105, 40);
	Vec2d ninSpringInitPos = new Vec2d(350, 180);
	Vec2d ninCarrotInitPos = new Vec2d(520, 120);
	Vec2d ninBrick2InitPos = new Vec2d(350, 220);	

	Vec2d ninIntroPos = new Vec2d(250, 100);
    
    
    public boolean isShowing = false;
    long countShowing = 0;
    int currentSeconds =0;
    
    boolean showing;
    boolean showIntro = true;
    
    boolean loadingFromFile;
	String levelFileName;
    
	boolean showRay = false;
	boolean fired = false;
	int bulletTime = 3;
	long bulletTimer = 0;
	
	List<NinProjectile> projectiles = new ArrayList<NinProjectile>();
	int numProjectiles = 0 ;
	
	public NinScene(String name, GameApplication application, String loadFile) {
		super(name, application);
		if(!loadFile.isEmpty())
		{
			levelFileName = loadFile;
			showIntro = false;
			loadingFromFile = true;
			ninIntroPos = new Vec2d(1500, 100);
		}
	}
	
	@Override
	public void initScene() 
	{
		
		myResetButton = new ResetButton("img/reset.png",this,750, 70);
		mySaveButton =  new SaveButton("img/save.png", this,750, 120);
	//	myFiledSavedMessage =  new UISprite("img/filesaved.png",350, 190);
		myFiledSavedMessage =  new UISprite("img/filesaved.png",1000, 1000);
		myIntroMessage = new UISprite("img/introNinInfo.png",ninIntroPos.x, ninIntroPos.y);
		rayLine  = new UILine(0, 0, 0, 0, UIConstants.RED);
				
		addProp(myResetButton);
		addProp(mySaveButton);
		addProp(myFiledSavedMessage);
		addProp(myIntroMessage);
		addProp(rayLine);
		
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
			
			
			//this.myGameWorld.addGameObject(backgroundImage, GameWorld.BackLayer);
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
	  if(fired)
	  {
		  bulletTimer += nanosSincePreviousTick;
		  if(bulletTimer >= 1000000000)
		  {
			  bulletTimer = 0;
			  bulletTime--;
		  }
		  if(bulletTime <= 0)
		  {
			  bulletTime = 3;
			  fired = false;
		  }
	  }
	  
	  Vec2d characterPosition = ((TransformComponent)mainCharater.getComponent(ComponentContants.transform)).getPosition();
	  Vec2d characterSize = new Vec2d(
							((SpriteComponent)mainCharater.getComponent(ComponentContants.sprite)).getWidth(),
							((SpriteComponent)mainCharater.getComponent(ComponentContants.sprite)).getHeight());
	
	  Vec2d coodinatesToGame = this.myGameWorld.gameToScreenTransform(new Vec2d(characterPosition.x + characterSize.x * 0.5,
                         characterPosition.y + characterSize.y * 0.5)); 
	  
	  rayLine.setStartPos(coodinatesToGame);
	  if(showRay)
	  {
		  rayLine.setEndPos(new Vec2d(rayLine.geStartPos().x + 25, rayLine.geStartPos().y));  
	  }
	  else
	  {
		  rayLine.setEndPos(coodinatesToGame);
	  }
	  
	  
	  
	  for(NinProjectile projectile:projectiles)
	  {
		  if(projectile.isDestroy())
		  {
			  this.myGameWorld.removeGameObject(projectile.getId());  
		  }
		  
	  }
	
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
	
	@Override
	public void onKeyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(showIntro)
		{
			myIntroMessage.setPosition(new Vec2d(1500, 1000));
			showIntro = false;	
		}
		
		super.onKeyTyped(e);
	}
	
	@Override
	public void onKeyPressed(KeyEvent ke) 
	{		
		if(ke.getCode() == KeyCode.SPACE && !showRay)
		{
          // fire projectile
		 	Vec2d characterPosition = ((TransformComponent)mainCharater.getComponent(ComponentContants.transform)).getPosition();
		//	Vec2d characterSize = new Vec2d(
		//			((SpriteComponent)mainCharater.getComponent(ComponentContants.sprite)).getWidth(),
		//			((SpriteComponent)mainCharater.getComponent(ComponentContants.sprite)).getHeight());
			
		//	Vec2d pos = new Vec2d(characterPosition.x + characterSize.x ,characterPosition.y + characterSize.y * 0.5);
		//	NinProjectile projectile = new NinProjectile("projectile"+numProjectiles,
				//	pos, "img/poop.png", 1, 0.2);
		//	((PhysicsComponent)projectile.getComponent(ComponentContants.physics)).applyImpulse(new Vec2d());
			
		//	numProjectiles++;
			
			//Ray ray = new Ray(characterPosition,new Vec2d(1,0));
			
			
			
			showRay = true;
		}
		
		if(ke.getCode() == KeyCode.F && showRay && !fired) {
			System.out.println("FIRE");
			fired = true; 
			
		}
		super.onKeyPressed(ke);
	}
	
	@Override
	public void onKeyReleased(KeyEvent ke) {
		
		if(ke.getCode() == KeyCode.SPACE)
		{
          // fire projectile
			showRay = false;
		}
		

		
		super.onKeyReleased(ke);
	}
	
	@Override
	public void onDraw(GraphicsContext graphicsContext)
	{
		super.onDraw(graphicsContext);
	//	ray.onDraw(graphicsContext);
		
	}
	
}
