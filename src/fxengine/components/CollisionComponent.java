package fxengine.components;


import java.util.HashSet;
import java.util.Set;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fxengine.collision.Collision;
import fxengine.collision.CollisionConstants;
import fxengine.collision.CollisionShape;
import fxengine.collision.CollisionShapeFactory;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;

public class CollisionComponent extends Component{

	
	private CollisionShape myCollisionShape;

	private Set<String> myHitList;
	
	private boolean isCollided = false;
	
	private String myColliderId;
	
	private GameObject myOtherCollider;
	
	private Collision myCollisionInfo;
	
	private boolean isStatic = false;
	
	private boolean isSpring = false;

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
		
		if(!isInitialized)
		{
			if(!this.myParent.getComponent(ComponentContants.transform).isInitialized())
			{
				this.myParent.getComponent(ComponentContants.transform).initialize();
			}
			
			if(!this.myParent.getComponent(ComponentContants.graphics).isInitialized())
			{
				this.myParent.getComponent(ComponentContants.graphics).initialize();
			}
			
			TransformComponent tranform = (TransformComponent)this.myParent.getComponent(ComponentContants.transform);
			GraphicsComponent graphics = (GraphicsComponent)this.myParent.getComponent(ComponentContants.graphics);
			
			if(tranform != null && graphics != null) {
				
				if(!tranform.isInitialized())
				{
					tranform.initialize();
				}
				
				
				if(!graphics.isInitialized())
				{
					graphics.initialize();
				}
				
				myCollisionShape.update(tranform.getPosition(),graphics.getSprite().getLayout().getSize());
			}
			
			isInitialized = true;
		}
	
		
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		TransformComponent tranform = (TransformComponent)this.myParent.getComponent(ComponentContants.transform);
        GraphicsComponent graphics = (GraphicsComponent)this.myParent.getComponent(ComponentContants.graphics);
		
		if(tranform != null && graphics != null)
		{
			myCollisionShape.update(tranform.getPosition(),graphics.getSprite().getLayout().getSize());
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
	
	public boolean isCollided() {
		return isCollided;
	}

	public void setCollided(boolean isCollided) {
		this.isCollided = isCollided;
	}
	
	public String getColliderId() {
		return myColliderId;
	}

	public void setColliderId(String colliderId) {
		this.myColliderId = colliderId;
	}

	public GameObject getOtherCollider() {
		return myOtherCollider;
	}

	public void setOtherCollider(GameObject myOtherCollider) {
		this.myOtherCollider = myOtherCollider;
	}
	
	public Collision getCollisionInfo() {
		return myCollisionInfo;
	}

	public void setCollisionInfo(Collision collisionInfo) {
		this.myCollisionInfo = collisionInfo;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public boolean isSpring() {
		return isSpring;
	}

	public void setSpring(boolean isSpring) {
		this.isSpring = isSpring;
	}

	@Override
	public Element saveState() {
		
		Element collision = doc.createElement("Component");
		collision.setAttribute("name", this.myName);
		
		Element isStatic = doc.createElement("isStatic");
		isStatic.setAttribute("boolean", ""+this.isStatic);
		Element isSpring = doc.createElement("isSpring");
		isSpring.setAttribute("boolean", ""+this.isSpring);
		
		collision.appendChild(isStatic);
		
		collision.appendChild(isSpring);
		return collision;
	}

	@Override
	public void loadState(Node node) {
		// TODO Auto-generated method stub
		if (node.hasChildNodes()) {
			NodeList nodeList = node.getChildNodes();
			 for (int index = 0; index < nodeList.getLength(); index++) 
			 {
				 Node tempNode = nodeList.item(index);
				   
				 if(tempNode.getNodeType() == Node.ELEMENT_NODE
							&&  tempNode.getNodeName() == "isStatic")
				 {
					 
					 NamedNodeMap nodeMap = tempNode.getAttributes();
					 Node nodeAttr = nodeMap.item(0);
					 String nodeStr = nodeAttr.getNodeValue();
					 this.isStatic = Boolean.parseBoolean(nodeStr);
					
				 }
				 
				 if(tempNode.getNodeType() == Node.ELEMENT_NODE
							&&  tempNode.getNodeName() == "isSpring")
				 {
					 
					 NamedNodeMap nodeMap = tempNode.getAttributes();
					 Node nodeAttr = nodeMap.item(0);
					 String nodeStr = nodeAttr.getNodeValue();
					 this.isSpring = Boolean.parseBoolean(nodeStr);
				 }
			 }
		
		}
	}
	
	

}
