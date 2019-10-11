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

	
	
	
	public WizScene(String name, FXFrontEnd application) {
		super(name, application);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initScene() 
	{
		 // Initialize game world
		super.initScene();
		
		/*AnimationComponent animation = new AnimationComponent(ComponentContants.sprite);
		animation.setFilePath("img/charactes_sprite_sheet.png");
		animation.setFrameSize(new Vec2d(32,36));
		animation.setNumFrames(new Vec2d(3,4));*/
		
		//terrain
		//TileMap terrain = new TileMap(750,450, 24,13);
		
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

		WizControllableCharacter mainCharater = new WizControllableCharacter("wiz1","warrior",animations);
		this.myGameWorld.addGameObject(mainCharater, GameWorld.FrontLayer);
		
		/*Tile[][] tileMap =  terrain.getTileMap();
		for(int i = 0 ; i < terrain.getNumTilesX(); i++)
		{
			for(int j = 0 ; j < terrain.getNumTilesY(); j++)
			{
				this.myGameWorld.addGameObject(tileMap[i][j], GameWorld.BackLayer);		
			}
		}*/
		
		GameTileMap terrain = new GameTileMap("text/mytilemap.txt", "img/tiles.png", 750,450, new Vec2d(0,0), new Vec2d(32,36), new Vec2i(1,3), this);
		terrain.load();
		
				
		
		
		
	}

	
	@Override
	public void onTick(long nanosSincePreviousTick)
	{
		super.onTick(nanosSincePreviousTick);
	}
}

