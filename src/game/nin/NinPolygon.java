package game.nin;

import java.util.ArrayList;
import java.util.List;

import fxengine.collision.CollisionConstants;
import fxengine.collision.CollisionShape;
import fxengine.collision.CollisionShapeFactory;
import fxengine.collision.PolygonColliderShape;
import fxengine.components.CollisionComponent;
import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.components.PhysicsComponent;
import fxengine.components.SpriteComponent;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;

public class NinPolygon extends GameObject{

	private Vec2d originalPos;
	private String textFilePath;
	private double restitution;
	private float mass;
	private List<Vec2d> myPoints;
	//private String collisionType = CollisionConstants.POLYGONShape;
	
	public NinPolygon(String id, Vec2d initialPosition, String texture, float mass, double restitution
			,List<Vec2d> points) {
		super(id);
		// TODO Auto-generated constructor stub
		this.originalPos =  initialPosition;
		this.textFilePath = texture;
		this.restitution = restitution;
		this.mass = mass;
		this.myPoints = points;
	}


	@Override
	public void initialize() {
		
		Component graphicsComponent = ComponentFactory.getInstance().createComponent(ComponentContants.graphics);
		Component spriteComponent = ComponentFactory.getInstance().createComponent(ComponentContants.sprite);
		((SpriteComponent) spriteComponent).setFilePath(this.textFilePath);

		Component tranformComponent = ComponentFactory.getInstance().createComponent(ComponentContants.transform);
		((TransformComponent) tranformComponent).setPosition(originalPos);
		Component collisionComponemt = ComponentFactory.getInstance().createComponent(ComponentContants.collision);
		PolygonColliderShape myCollisionShape = (PolygonColliderShape) CollisionShapeFactory.getInstance()
				.createShape(CollisionConstants.POLYGONShape);
		myCollisionShape.setPoints(this.myPoints);
		((CollisionComponent) collisionComponemt).setCollisionShape(myCollisionShape);

		Component physicsComponent = ComponentFactory.getInstance().createComponent(ComponentContants.physics);
		((PhysicsComponent) physicsComponent).setMass(this.mass);
		((PhysicsComponent) physicsComponent).setRestitution(this.restitution);

		this.addComponent(graphicsComponent);
		this.addComponent(spriteComponent);
		this.addComponent(tranformComponent);
		this.addComponent(collisionComponemt);
		this.addComponent(physicsComponent);
		this.setTag(NinScene.ENEMY);
		super.initialize();
	}

	
}
