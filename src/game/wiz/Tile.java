package game.wiz;

import fxengine.collision.CollisionConstants;
import fxengine.collision.CollisionShape;
import fxengine.collision.CollisionShapeFactory;
import fxengine.components.CollisionComponent;
import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.components.SpriteAnimationComponent;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;
import fxengine.math.Vec2i;
import fxengine.objects.GameObject;

public class Tile extends GameObject{

	private int myColor;
	private Vec2d myTextCoord;
	private Vec2d myPosition;
	private boolean isStatic;
	
	public Tile(String id, int color, Vec2d position, Vec2d textCoord, boolean isStatic) {
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
		
		Component spriteComponent = ComponentFactory.getInstance().createComponent(ComponentContants.sprite_animation);
		((SpriteAnimationComponent)spriteComponent).setFilePath("img/tiles.png");
		((SpriteAnimationComponent)spriteComponent).setFrameSize(new Vec2d(32, 36));
		((SpriteAnimationComponent)spriteComponent).setNumFrames(new Vec2i(1, 3));
		((SpriteAnimationComponent)spriteComponent).setFramePosition(new Vec2d(0, 0));
		((SpriteAnimationComponent)spriteComponent).setCurrentFrame(myColor);
		
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
		this.addComponent(spriteComponent);
		
		
		super.initialize();
	}

	public int getMyColor() {
		return myColor;
	}

	public void setMyColor(int myColor) {
		this.myColor = myColor;
	}
	
}
