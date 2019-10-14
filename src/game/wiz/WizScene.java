package game.wiz;

import java.util.ArrayList;
import java.util.List;

import fxengine.application.FXFrontEnd;
import fxengine.application.GameApplication;
import fxengine.collision.CollisionConstants;
import fxengine.collision.CollisionShape;
import fxengine.collision.CollisionShapeFactory;
import fxengine.components.TiledSpriteComponent;
import fxengine.components.TerrainComponent;
import fxengine.components.Animation;
import fxengine.components.CollisionComponent;
import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.components.GraphicsComponent;
import fxengine.components.TransformComponent;
import fxengine.maploader.GameTileMap;
import fxengine.math.Vec2d;
import fxengine.math.Vec2i;
import fxengine.objects.GameObject;
import fxengine.objects.GameWorld;
import fxengine.scene.GameWorldScene;

public class WizScene extends GameWorldScene{

	GameTileMap terrain;
	WizCharacter mainCharater;
	Vec2i playerCurrentTile = new Vec2i(0);
	int[][] fogofWar;
	
	boolean doFogOfWar = true;
	String currentMapPath;
	
	public static String goal = "GOAL";
	
	private int currentLevel = 0;
	private boolean isMapExternal = false;
	
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
		
		this.currentLevel = level;
		
		if(currentLevel == 0)
		{
			if(defaultMap.isEmpty())
			{
				this.currentMapPath = "text/mytilemap.txt";	
			}
			else
			{
				this.currentMapPath = defaultMap;
				isMapExternal = true;
			}
				
		}
		else if(currentLevel ==  1)
		{
			this.currentMapPath = defaultMap.isEmpty()? "text/myTileMap2.txt":defaultMap;
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
		mainCharater = new WizAiCharacter("wiz1","warrior", new Vec2d(32,72),animations);
		this.myGameWorld.addGameObject(mainCharater, GameWorld.FrontLayer);
		
        //terrain	
		terrain = new GameTileMap(this.currentMapPath, "img/tiles.png", 750,450, new Vec2d(0,0), new Vec2d(32,36), new Vec2i(1,4), this);
		terrain.setExternal(isMapExternal);
		terrain.load();
		
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
        		
        }
        
	}

	
	@Override
	public void onTick(long nanosSincePreviousTick)
	{
		
		//correct sprite position to tile
		//TransformComponent transform = (TransformComponent)mainCharater.getComponent(ComponentContants.transform);
		//int numTileX = (int)(transform.getPosition().x / 32);
		//int numTileY = (int)(transform.getPosition().y / 36);
		
		
		
		
		if(doFogOfWar)
		{
			TransformComponent transform = (TransformComponent)mainCharater.getComponent(ComponentContants.transform);
    		int numTileX = (int)(transform.getPosition().x / 32);
    		int numTileY = (int)(transform.getPosition().y / 36);
			if(playerCurrentTile.x != numTileX  || playerCurrentTile.y != numTileY)
			{
				playerCurrentTile = new Vec2i(numTileX,numTileY);
				fogOfWar();
				
			}
			
			
		}
		
	    CollisionComponent collision = (CollisionComponent)mainCharater.getComponent(ComponentContants.collision);
		if(collision != null  && collision.isCollided() )
		{
			if(collision.getOtherCollider().getTag().equals(goal))
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

	public boolean isDoFogOfWar() {
		return doFogOfWar;
	}

	public void setDoFogOfWar(boolean doFogOfWar) {
		this.doFogOfWar = doFogOfWar;
	}
}

