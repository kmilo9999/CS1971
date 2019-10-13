package fxengine.maploader;

import fxengine.collision.CollisionConstants;
import fxengine.collision.CollisionShape;
import fxengine.collision.CollisionShapeFactory;
import fxengine.components.CollisionComponent;
import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.components.TiledSpriteComponent;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;
import fxengine.math.Vec2i;
import fxengine.objects.GameObject;

public class GameTile extends GameObject{

	private int myColor;
	private Vec2d myTextCoord;
	private Vec2d myPosition;
	private boolean isStatic;
	private String myTextureFilePath;
	private Vec2d myTileSize;
	private Vec2i myNumFrames;
	
	public GameTile(String id, String myTextureFilePath, Vec2d position)
	{
		super(id);
		this.myTextureFilePath = myTextureFilePath;
		this.myPosition = position;
	}
	
	public GameTile(String id, int color, Vec2d position, Vec2d textCoord, boolean isStatic) {
		super(id);
		// TODO Auto-generated constructor stub
		this.myColor =  color;
		this.myTextCoord = textCoord;
		this.myPosition = position;
		this.isStatic =  isStatic;
	}

	@Override
	public void initialize()
	{
		Component tranformComponent = ComponentFactory.getInstance().createComponent(ComponentContants.transform);
		((TransformComponent)tranformComponent).setPosition(myPosition);
		
        Component graphicsComponent = ComponentFactory.getInstance().createComponent(ComponentContants.graphics);
		
		Component tiledspriteComponent = ComponentFactory.getInstance().createComponent(ComponentContants.tiled_sprite);
		//((TiledSpriteComponent)tiledspriteComponent).setFilePath("img/tiles.png");
		//((TiledSpriteComponent)tiledspriteComponent).setFrameSize(new Vec2d(32, 36));
		//((TiledSpriteComponent)tiledspriteComponent).setNumFrames(new Vec2i(1, 3));
		//((TiledSpriteComponent)tiledspriteComponent).setFramePosition(new Vec2d(0, 0));
		//((TiledSpriteComponent)tiledspriteComponent).setCurrentFrame(myColor);
		
		((TiledSpriteComponent)tiledspriteComponent).setFilePath(this.myTextureFilePath);
		((TiledSpriteComponent)tiledspriteComponent).setFrameSize(this.myTileSize);
		((TiledSpriteComponent)tiledspriteComponent).setNumFrames(this.myNumFrames);
		((TiledSpriteComponent)tiledspriteComponent).setFramePosition(this.myTextCoord);
		((TiledSpriteComponent)tiledspriteComponent).setCurrentFrame(this.myColor);
		
		if(this.isStatic)
		{
			Component collisionComponent = ComponentFactory.getInstance().createComponent(ComponentContants.collision);
			CollisionShape collisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
			((CollisionComponent)collisionComponent).setCollisionShape(collisionShape);
			((CollisionComponent)collisionComponent).setStatic(this.isStatic);
			this.addComponent(collisionComponent);
		}
		
		
		this.addComponent(tranformComponent);
		this.addComponent(graphicsComponent);
		this.addComponent(tiledspriteComponent);
		
		
		super.initialize();
	}

	public int getColor() {
		return myColor;
	}

	public void setColor(int myColor) {
		this.myColor = myColor;
		Component tiledspriteComponent = this.getComponent(ComponentContants.tiled_sprite) ;
		if(tiledspriteComponent != null)
		{
			((TiledSpriteComponent)tiledspriteComponent).setCurrentFrame(this.myColor);	
		}
		
	}

	public Vec2d getTextCoord() {
		return myTextCoord;
	}

	public void setTextCoord(Vec2d myTextCoord) {
		this.myTextCoord = myTextCoord;
	}

	public String getTextureFilePath() {
		return myTextureFilePath;
	}

	public void setTextureFilePath(String myTextureFilePath) {
		this.myTextureFilePath = myTextureFilePath;
	}

	public Vec2d getTileSize() {
		return myTileSize;
	}

	public void setTileSize(Vec2d myTileSize) {
		this.myTileSize = myTileSize;
	}

	public Vec2i getNumFrames() {
		return myNumFrames;
	}

	public void setNumFrames(Vec2i myNumFrames) {
		this.myNumFrames = myNumFrames;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
		if(this.isStatic)
		{
			if(this.hasComponent(ComponentContants.collision))
			{
				CollisionComponent collisionComponent = (CollisionComponent)this.getComponent(ComponentContants.collision);
				collisionComponent.setStatic(this.isStatic);	
			}
			else
			{
				Component collisionComponent = ComponentFactory.getInstance().createComponent(ComponentContants.collision);
				CollisionShape collisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
				((CollisionComponent)collisionComponent).setCollisionShape(collisionShape);
				((CollisionComponent)collisionComponent).setStatic(this.isStatic);
				this.addComponent(collisionComponent);				
			}
			
			
		}
		else
		{
			this.removeComponent(ComponentContants.collision);
		}
	}
	
}
