package game.wiz;

import java.util.ArrayList;
import java.util.List;

import fxengine.application.FXFrontEnd;
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
	WizControllableCharacter mainCharater;
	Vec2d playerCurrentTile = new Vec2d(0);
	int[][] fogofWar;
	
	boolean doFogOfWar = true;
	
	public WizScene(String name, FXFrontEnd application) {
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
		
		Animation right = new Animation(WizCharacter.moveRight,new Vec2d(0,36),new Vec2d(32,36),new Vec2i(1,3));
		Animation left = new Animation(WizCharacter.moveLeft,new Vec2d(0,108),new Vec2d(32,36),new Vec2i(1,3));
		Animation up = new Animation(WizCharacter.moveUp,new Vec2d(0,0),new Vec2d(32,36),new Vec2i(1,3));
		Animation down = new Animation(WizCharacter.moveDown,new Vec2d(0,72),new Vec2d(32,36),new Vec2i(1,3));
		animations.add(right);
		animations.add(left);
		animations.add(up);
		animations.add(down);

		mainCharater = new WizControllableCharacter("wiz1","warrior",animations);
		this.myGameWorld.addGameObject(mainCharater, GameWorld.FrontLayer);
		
        //terrain	
		terrain = new GameTileMap("text/mytilemap.txt", "img/tiles.png", 750,450, new Vec2d(0,0), new Vec2d(32,36), new Vec2i(1,4), this);
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
            fogOfWar(numTileX,numTileY);
        	/*TransformComponent transform = (TransformComponent)mainCharater.getComponent(ComponentContants.transform);
            int numTileX = (int)(transform.getPosition().x / 32);
    		int numTileY = (int)(transform.getPosition().y / 36);
            int minClampXTile =  Math.max(0,  Math.min(terrain.getNumTiles().x, numTileY - 2)); 
    		int minClampYTile =  Math.max(0,  Math.min(terrain.getNumTiles().y, numTileX - 2));
    		
    		int maxClampXTile =  Math.max(0,  Math.min(terrain.getNumTiles().y, numTileX + 2));
    		int maxClampYTile =  Math.max(0,  Math.min(terrain.getNumTiles().y, numTileX + 2));
            for (int i = 0; i < terrain.getNumTiles().y; i++) 
    		{
    			for (int j = 0; j < terrain.getNumTiles().x; j++)
    			{
    				if((j >= minClampXTile && j <=maxClampXTile)
    						&& (i >= minClampYTile &&  i<=maxClampYTile) )
    				{
    					terrain.getTile(j, i).setColor(fogofWar[j][i]);
    				}
    				else
    				{
    					terrain.getTile(j, i).setColor(3);
    				}
    			}
    		}
    		*/	
        }
        
	}

	
	@Override
	public void onTick(long nanosSincePreviousTick)
	{
		
		if(doFogOfWar)
		{
			TransformComponent transform = (TransformComponent)mainCharater.getComponent(ComponentContants.transform);
    		int numTileX = (int)(transform.getPosition().x / 32);
    		int numTileY = (int)(transform.getPosition().y / 36);
			if(playerCurrentTile.x != numTileX  || playerCurrentTile.y != numTileY)
			{
				fogOfWar(numTileX, numTileY);	
			}
			
			
		}
		
		
		
		super.onTick(nanosSincePreviousTick);
	}
	
	private void fogOfWar(int xTile, int yTile)
	{
		
		int minClampXTile = Math.max(0, Math.min(terrain.getNumTiles().x, xTile - 2));
		int minClampYTile = Math.max(0, Math.min(terrain.getNumTiles().y, yTile - 2));

		int maxClampXTile = Math.max(0, Math.min(terrain.getNumTiles().x, xTile + 2));
		int maxClampYTile = Math.max(0, Math.min(terrain.getNumTiles().y, yTile + 2));

		for (int i = 0; i < terrain.getNumTiles().x; i++) {
			for (int j = 0; j < terrain.getNumTiles().y; j++) {
				// terrain.getTile(i, j).setColor(3);
				if ((i >= minClampXTile && i <= maxClampXTile) && (j >= minClampYTile && j <= maxClampYTile)) {
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

