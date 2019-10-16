package game.wiz;

import java.util.ArrayList;
import java.util.List;

import fxengine.PathFinding.PathFinding;
import fxengine.application.GameApplication;
import fxengine.components.Animation;
import fxengine.components.CollisionComponent;
import fxengine.components.ComponentContants;
import fxengine.components.TiledSpriteComponent;
import fxengine.components.TransformComponent;
import fxengine.maploader.GameTileMap;
import fxengine.math.Vec2d;
import fxengine.math.Vec2i;
import fxengine.objects.GameWorld;
import fxengine.scene.GameWorldScene;

public class WizScene extends GameWorldScene{

	GameTileMap terrain;
	WizCharacter mainCharater;
	
	int numEnemiesPerLevel;
	Vec2i playerCurrentTile = new Vec2i(0);
	int[][] fogofWar;
	PathFinding pathFinding ;
	
	int currentSecond = 0;
	long currentTime = 0;
	boolean activateLight = false;
	boolean activatedLigth = false;
	
	boolean doFogOfWar = true;
	boolean defaultDoFogOfWar;
	String currentMapPath;
	int dummylight = 0;
	
	public static String goal = "GOAL";
	public static String ligth = "LIGHT";
	public static String enemy = "ENEMY";
	
	private int currentLevel = 0;
	private boolean isMapExternal = false;
	
	Vec2d lightPos;
	
	LevelState levelState;
	
	public WizScene(String name, GameApplication application,String fogOfWar, String defaultMap, int level) {
		super(name, application);
		// TODO Auto-generated constructor stub
		if(fogOfWar.equals("YES") || fogOfWar.equals("Y") || fogOfWar.equals("yes")
				|| fogOfWar.equals("y") || fogOfWar.equals("yEs") || fogOfWar.equals("yES"))
		{
			doFogOfWar = true;
			
		}
		else if(fogOfWar.equals("NO")  || fogOfWar.equals("N") || fogOfWar.equals("no")
				|| fogOfWar.equals("n") || fogOfWar.equals("No") || fogOfWar.equals("nO"))
		{
			doFogOfWar = false;
		}
		
		defaultDoFogOfWar = doFogOfWar;
		
		this.currentLevel = level;
		
		if(currentLevel == 0)
		{
			if(defaultMap.isEmpty())
			{
				this.currentMapPath = "text/mytilemap.txt";	
				numEnemiesPerLevel = 1;
				
			}
			else
			{
				this.currentMapPath = defaultMap;
				isMapExternal = true;
			}
				
		}
		else if(currentLevel ==  1)
		{
			if(defaultMap.isEmpty())
			{
				numEnemiesPerLevel = 1;
				this.currentMapPath = "text/myTileMap2.txt";	
			}
			else
			{
				this.currentMapPath = defaultMap;
				isMapExternal = true;
			}
		}
		
	}
	
	@Override
	public void initScene() 
	{
		 // Initialize game world
		super.initScene();
		
		
		//characters
		List<Animation> animations = new ArrayList<Animation>();
		
		Animation right = new Animation(WizCharacter.moveRight,new Vec2d(0,36),new Vec2d(32,36),new Vec2i(1,3));
		Animation left = new Animation(WizCharacter.moveLeft,new Vec2d(0,108),new Vec2d(32,36),new Vec2i(1,3));
		Animation up = new Animation(WizCharacter.moveUp,new Vec2d(0,0),new Vec2d(32,36),new Vec2i(1,3));
		Animation down = new Animation(WizCharacter.moveDown,new Vec2d(0,72),new Vec2d(32,36),new Vec2i(1,3));
		animations.add(right);
		animations.add(left);
		animations.add(up);
		animations.add(down);

		//mainCharater = new WizControllableCharacter("wiz1","warrior",animations);
		
		
        //terrain	
		terrain = new GameTileMap(this.currentMapPath, "img/tiles.png", 750,450, new Vec2d(0,0), new Vec2d(32,36), new Vec2i(1,5), this);
		terrain.setExternal(isMapExternal);
		terrain.load();
		terrain.enablePathFinding();
		
		Vec2d playerInitialPosition = terrain.getPlayerInitialPosition();
		if(terrain.getPlayerInitialPosition() == null)
		{
			playerInitialPosition =  new Vec2d(32,72);

		}
		
		mainCharater = new WizControllableCharacter("wiz1","warrior", playerInitialPosition,animations);
		this.myGameWorld.addGameObject(mainCharater, GameWorld.FrontLayer);
		
		
		if(currentLevel == 0)
		{
			List<Vec2d> positions = new ArrayList<>();
			positions.add(new Vec2d(416,108));
			levelState = new Level1(numEnemiesPerLevel, positions, terrain);
			levelState.initialize();
			for(WizCharacter enemy: levelState.getEnemies())
			{
				this.myGameWorld.addGameObject(enemy, GameWorld.FrontLayer);
			}
		}
		else if(currentLevel == 1)
		{
			List<Vec2d> positions = new ArrayList<>();
			positions.add(new Vec2d(288,396));
			levelState = new Level2(numEnemiesPerLevel, positions, terrain);
			levelState.initialize();
			for(WizCharacter enemy: levelState.getEnemies())
			{
				this.myGameWorld.addGameObject(enemy, GameWorld.FrontLayer);
			}
		}
		
		//int index = 0;
		//while(index < numEnemiesPerLevel)
		//{
		//	aiCharaters[index] = new WizAiCharacter("enemy"+index,"priest", playerInitialPosition,animations);
		//	this.myGameWorld.addGameObject(aiCharaters[index], GameWorld.FrontLayer);
		//}
		
		//mainCharater = new WizAiCharacter("wiz1","warrior", playerInitialPosition,animations);
		//this.myGameWorld.addGameObject(mainCharater, GameWorld.FrontLayer);
		
		///-------------------
		// init path finding
		
		//pathFinding  = new PathFinding(terrain.getNumTiles().x, terrain.getNumTiles().y, terrain.getIntTileMap());

		
		
        //TransformComponent transformIinit = (TransformComponent)aiCharaters[index].getComponent(ComponentContants.transform);
		//int startNumTileY = (int)(transformIinit.getPosition().x / 32);
		//int startNumTileX = (int)(transformIinit.getPosition().y / 36);
		
		//Vec2d endPos = new Vec2d(transformIinit.getPosition().x + 32,transformIinit.getPosition().y + 36);
		//Vec2i endTile = terrain.coordinateToTile(endPos.x, endPos.y);
		
		
		//Node startNode = new Node(startNumTileX,startNumTileY);
		
		//Node endNode = new Node(new Vec2i(2,10));
		
		//Queue<Vec2d> resultPath = terrain.findPath(startNode, endNode);
		//Queue<Vec2d> pathPoints = new LinkedList<Vec2d>();
		//AIMovementComponent aiMovementComponent = (AIMovementComponent) mainCharater.getComponent(ComponentContants.AIMovement);
		//aiMovementComponent.setPathPoints(resultPath);
		/*if(resultPath != null)
		{
			for(Node  node: resultPath)
			{
				//	convert to game coordinates
				Vec2d gameCoordinate = terrain.tileToCoordinate(node.x, node.y);
				//Vec2d gameCoordinate = new Vec2d(node.y * 32,node.x * 36);
				pathPoints.add(gameCoordinate);
				
			}
			
			
		}*/
		
       // System.out.println("end of AI Movement");
		

		//--------------------------------------
        // AI Actions
        
        //Composite root = new Sequence();
        
       // Node controlPoint0 = new Node(new Vec2i(3,13));
      //  Node controlPoint1 = new Node(new Vec2i(3,10));
      //  Node controlPoint2 = new Node(new Vec2i(3,13));
        //Node controlPoint2 = new Node(new Vec2i(2,10));
        //Node controlPoint3 = new Node(new Vec2i(2,14));
        //Node controlPoint4 = new Node(new Vec2i(3,13));
     //   Queue<Node> controlsPoints  = new LinkedList<Node>();
     //   controlsPoints.add(controlPoint0);
    //    controlsPoints.add(controlPoint1);
    //    controlsPoints.add(controlPoint2);
        //controlsPoints.add(controlPoint3);
        //controlsPoints.add(controlPoint4);
        
    //    Action patrol = new PatrolAction((GameObject)mainCharater, terrain,controlsPoints);
     //   Condition timeDelay = new TimeDelay(3);
    //    root.children.add(timeDelay);
    //    root.children.add(patrol);
        
    //    BehaviorTree behaviorTree = new BehaviorTree(root);
        
    //    AIComponent aiComponent = (AIComponent)ComponentFactory.getInstance().createComponent(ComponentContants.AI);
    //    aiComponent.setBehaviorTree(behaviorTree);
        //aiCharaters[index].addComponent(aiComponent);
        
		//--------------------------------------
		
        // init fog of war
        
        if(doFogOfWar)
        {
        	fogofWar = new int[terrain.getNumTiles().x][terrain.getNumTiles().y];
            for(int i = 0 ; i < terrain.getNumTiles().x ; i++)
            {
            	for(int j = 0 ; j < terrain.getNumTiles().y ; j++)
            	{
            		fogofWar[i][j] = terrain.getTile(i, j).getColor();
            	}
            }
            
            TransformComponent transform = (TransformComponent)mainCharater.getComponent(ComponentContants.transform);
    		int numTileX = (int)(transform.getPosition().x / 32);
    		int numTileY = (int)(transform.getPosition().y / 36);
    		playerCurrentTile = new Vec2i(numTileX,numTileY);
            fogOfWar();
        	if(levelState != null && levelState.getEnemies() != null)
    		{
    			List<WizCharacter>  enemies = levelState.getEnemies();
    			for(WizCharacter enemy : enemies)
    			{
    				TransformComponent enemyTransform = (TransformComponent)enemy.getComponent(ComponentContants.transform);
    				Vec2i enemyTile = terrain.coordinateToTile(enemyTransform.getPosition());
    				TiledSpriteComponent tileComponent = (TiledSpriteComponent)enemy.getComponent(ComponentContants.tiled_sprite);
    				if(terrain.getTile(enemyTile.x, enemyTile.y).getColor() == 3)
    				{			
    					tileComponent.setEnabled(false);
    				}
    				else
    				{
    					tileComponent.setEnabled(true);
    				}
    			}
    		}
        }
        
	}

	
	@Override
	public void onTick(long nanosSincePreviousTick)
	{
		/*if(lightPos != null) {
			TransformComponent transform = (TransformComponent)mainCharater.getComponent(ComponentContants.transform);
			if(lightPos.dist(transform.getPosition()) > 64)
			{
				dummylight = 0;
			}
		}*/
		TransformComponent transform = (TransformComponent)mainCharater.getComponent(ComponentContants.transform);
		Vec2i currenTile = terrain.coordinateToTile(transform.getPosition());
		if(terrain.getTile(currenTile.x, currenTile.y).getColor() == 4)
		{
			activateLight = true;	
		}
		
		
		if(activateLight && defaultDoFogOfWar == true)
		{
			currentTime += nanosSincePreviousTick;
			
			if(!activatedLigth)
			{
				doFogOfWar = false;
				turnOnLights();	
			}
			
		}
		
		if(currentTime > 1000000000)
		{
			currentSecond++;
			currentTime= 0;
			//System.out.println(currentSecond);
			if( currentSecond > 3  )
			{
				currentSecond = 0;
				currentTime= 0;
				activateLight = false;
				doFogOfWar = true;
				activatedLigth = false;
				fogOfWar();
				if(levelState != null && levelState.getEnemies() != null)
				{
					List<WizCharacter>  enemies = levelState.getEnemies();
					for(WizCharacter enemy : enemies)
					{
						TransformComponent enemyTransform = (TransformComponent)enemy.getComponent(ComponentContants.transform);
						Vec2i enemyTile = terrain.coordinateToTile(enemyTransform.getPosition());
						TiledSpriteComponent tileComponent = (TiledSpriteComponent)enemy.getComponent(ComponentContants.tiled_sprite);
						if(terrain.getTile(enemyTile.x, enemyTile.y).getColor() == 3)
						{			
							tileComponent.setEnabled(false);
						}
						else
						{
							tileComponent.setEnabled(true);
						}
					}
				}
				return;
			}
		}
	
		if(doFogOfWar )
		{
			TransformComponent transformHero = (TransformComponent)mainCharater.getComponent(ComponentContants.transform);
    		int numTileX = (int)(transformHero.getPosition().x / 32);
    		int numTileY = (int)(transformHero.getPosition().y / 36);
			if(playerCurrentTile.x != numTileX  || playerCurrentTile.y != numTileY)
			{
				playerCurrentTile = new Vec2i(numTileX,numTileY);
				fogOfWar();
				if(levelState != null && levelState.getEnemies() != null)
				{
					List<WizCharacter>  enemies = levelState.getEnemies();
					for(WizCharacter enemy : enemies)
					{
						TransformComponent enemyTransform = (TransformComponent)enemy.getComponent(ComponentContants.transform);
						Vec2i enemyTile = terrain.coordinateToTile(enemyTransform.getPosition());
						TiledSpriteComponent tileComponent = (TiledSpriteComponent)enemy.getComponent(ComponentContants.tiled_sprite);
						if(terrain.getTile(enemyTile.x, enemyTile.y).getColor() == 3)
						{			
							tileComponent.setEnabled(false);
						}
						else
						{
							tileComponent.setEnabled(true);
						}
					}
				}
			}
			
			
		}
		
	    CollisionComponent collision = (CollisionComponent)mainCharater.getComponent(ComponentContants.collision);
		if(collision != null  && collision.isCollided() )
		{
			if(collision.getOtherCollider() != null && collision.getOtherCollider().getTag().equals(goal))
			{
				if(this.currentLevel == 0)
				{
					((WizGame)this.myApplication).setActiveScreen("level2");	
				}
				else if(currentLevel ==  1)
				{
					((WizGame)this.myApplication).setActiveScreen("End");
				}
			}
			
			if(collision.getOtherCollider() != null && collision.getOtherCollider().getTag().equals(enemy))
			{
				((WizGame)this.myApplication).setActiveScreen("dead");	
			}
			
		}
		
		super.onTick(nanosSincePreviousTick);
	}
	
	private void fogOfWar()
	{
		int xTile = playerCurrentTile.x;
		int yTile = playerCurrentTile.y;
		
		int minClampXTile = Math.max(0, Math.min(terrain.getNumTiles().x, yTile - 1));
		int minClampYTile = Math.max(0, Math.min(terrain.getNumTiles().y, xTile - 1));

		int maxClampXTile = Math.max(0, Math.min(terrain.getNumTiles().x, yTile + 2));
		int maxClampYTile = Math.max(0, Math.min(terrain.getNumTiles().y, xTile + 2));
		

		for (int i = 0; i < terrain.getNumTiles().x; i++) {
			for (int j = 0; j < terrain.getNumTiles().y; j++) {
				if ((j >= minClampYTile && j <= maxClampYTile) && (i >= minClampXTile && i <= maxClampXTile)) {
					terrain.getTile(i, j).setColor(fogofWar[i][j]);
				} else {
					terrain.getTile(i, j).setColor(3);
				}

			}
		}
	}
	
	private void turnOnLights()
	{
		
		for (int i = 0; i < terrain.getNumTiles().x; i++) {
			for (int j = 0; j < terrain.getNumTiles().y; j++) {
					terrain.getTile(i, j).setColor(fogofWar[i][j]);
			}
		}
		
		List<WizCharacter>  enemies = levelState.getEnemies();
		for(WizCharacter enemy : enemies)
		{
			TiledSpriteComponent tileComponent = (TiledSpriteComponent)enemy.getComponent(ComponentContants.tiled_sprite);
			tileComponent.setEnabled(true);
		}
		activatedLigth = true;
	}

	public boolean isDoFogOfWar() {
		return doFogOfWar;
	}

	public void setDoFogOfWar(boolean doFogOfWar) {
		this.doFogOfWar = doFogOfWar;
	}
}

