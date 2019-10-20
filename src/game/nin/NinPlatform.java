package game.nin;

import fxengine.collision.CollisionConstants;
import fxengine.collision.CollisionShape;
import fxengine.collision.CollisionShapeFactory;
import fxengine.components.Animation;
import fxengine.components.AnimationComponent;
import fxengine.components.CollisionComponent;
import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.components.SpriteComponent;
import fxengine.components.TiledSpriteComponent;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;

public class NinPlatform extends GameObject{

	
	private Vec2d myInitialPosition;
	private String myTexturePath;
	
	public NinPlatform(String id, Vec2d initialPosition, String texturePath) {
		super(id);
		// TODO Auto-generated constructor stub
		this.myInitialPosition = initialPosition;
		this.myTexturePath = texturePath;
	}

	@Override
	public void initialize()
	{
		 Component graphicsComponent = ComponentFactory.getInstance().createComponent(ComponentContants.graphics);
		 Component spriteComponent = ComponentFactory.getInstance().createComponent(ComponentContants.sprite);
		 ((SpriteComponent)spriteComponent).setFilePath(this.myTexturePath);		
		 
		 Component tranformComponent = ComponentFactory.getInstance().createComponent(ComponentContants.transform);
		 ((TransformComponent) tranformComponent).setPosition(this.myInitialPosition);
		 
		 Component collisionComponemt = ComponentFactory.getInstance().createComponent(ComponentContants.collision);
		 CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
		 ((CollisionComponent) collisionComponemt).setCollisionShape(myCollisionShape);
		 ((CollisionComponent) collisionComponemt).setStatic(true);
		 
		 this.addComponent(graphicsComponent);
		 this.addComponent(tranformComponent);
		 this.addComponent(spriteComponent);
		 this.addComponent(collisionComponemt);
	     
		 super.initialize();
	}
}
