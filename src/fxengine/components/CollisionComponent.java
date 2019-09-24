package fxengine.components;


import java.util.HashSet;
import java.util.Set;

import fxengine.collision.CollisionConstants;
import fxengine.collision.CollisionShape;
import fxengine.collision.CollisionShapeFactory;
import javafx.scene.canvas.GraphicsContext;

public class CollisionComponent extends Component{

	
	private CollisionShape myCollisionShape;

	private Set<String> myHitList;

	public CollisionComponent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
		myHitList = new HashSet<String>();
	}

	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
		TransformComponent tranform = (TransformComponent)this.myParent.getComponent(ComponentContants.transform);
		GraphicsComponent graphics = (GraphicsComponent)this.myParent.getComponent(ComponentContants.graphics);
		
		if(tranform != null && graphics != null)
		{
			myCollisionShape.initialize(tranform.getPosition(),graphics.getSprite().getSize());
		}
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		TransformComponent tranform = (TransformComponent)this.myParent.getComponent(ComponentContants.transform);
        GraphicsComponent graphics = (GraphicsComponent)this.myParent.getComponent(ComponentContants.graphics);
		
		if(tranform != null && graphics != null)
		{
			myCollisionShape.initialize(tranform.getPosition(),graphics.getSprite().getSize());
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub
		
	}

	public Set<String> getHitList() {
		return myHitList;
	}

	public void setHitList(Set<String> myHitList) {
		this.myHitList = myHitList;
	}
	
	public CollisionShape getCollisionShape() {
		return myCollisionShape;
	}

	public void setCollisionShape(CollisionShape myCollisionShape) {
		this.myCollisionShape = myCollisionShape;
	}
}