package fxengine.components;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fxengine.event.Event;
import fxengine.event.EventsConstants;
import fxengine.math.Vec2d;
import fxengine.system.KeyboardEventSystem;
import javafx.scene.canvas.GraphicsContext;


public class AWSDKeyControllerBehaviorComponent extends KeyEventComponent{

	
	private double mySpeed = 0.85;
	private double myMaxImpulse = 0.009; 
	private double currentImpulse = 0;
	private boolean jumped = false;
	
	public AWSDKeyControllerBehaviorComponent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		if(keys[22] && !jumped)
		{
	

			PhysicsComponent physicsComponent = (PhysicsComponent) this.myParent
					.getComponent(ComponentContants.physics);
			if (physicsComponent != null && physicsComponent.isOnStacticObject()) {
				this.myParent.onJump();
				physicsComponent.applyImpulse(new Vec2d(0, -2.5));
				
				jumped = true;
			}
			
		}
		
		if(keys[0])
		{
		

			PhysicsComponent physicsComponent = (PhysicsComponent)this.myParent.getComponent(ComponentContants.physics);
			if(physicsComponent != null && physicsComponent.isOnStacticObject() )
			{
				physicsComponent.applyImpulse(new Vec2d(-1,0));
			}
			else if(physicsComponent != null && !physicsComponent.isOnStacticObject() )
			{
				currentImpulse += 0.0025;
				double totalImpulse = Math.min(myMaxImpulse, currentImpulse);
				physicsComponent.applyImpulse(new Vec2d(-totalImpulse,0));
			}
		}
		
		if(keys[18])
		{
			
		}
		
		if(keys[3])
		{
		
			
			PhysicsComponent physicsComponent = (PhysicsComponent)this.myParent.getComponent(ComponentContants.physics);
			if(physicsComponent != null && physicsComponent.isOnStacticObject() )
			{
				physicsComponent.applyImpulse(new Vec2d(1,0));
			}
			else if(physicsComponent != null && !physicsComponent.isOnStacticObject() )
			{
				currentImpulse += 0.0025;
				double totalImpulse = Math.min(myMaxImpulse, currentImpulse);
				physicsComponent.applyImpulse(new Vec2d(totalImpulse,0));
			}
		}
		

		if(!keys[3] && !keys[0])
		{
			currentImpulse = 0;
		}
		
		if(!keys[22] && jumped )
		{
			jumped = false;
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

	/**
	 * @param value
	 */
	public void keyTyped(String value) {
		// TODO Auto-generated method stub
		//System.out.println("onKeyTyped " + value);
		if(value.length() == 1)
		{
			
			if((int)value.charAt(0) - 97 == 2 && this.myParent.isSelected())
			{
				
				this.myParent.clone();
			}
			
		}
	}
	
	public void keyReleased(String value) {
		// TODO Auto-generated method stub
	
		
		//System.out.println("onKeyReleased " + value);
	}
	
	public void keyPressed(String value) {
		// TODO Auto-generated method stub
		
	    //System.out.println("onKeyPressed " + value );
	}
	
	public void processEvent(Event e)
	{
		super.processEvent(e);
		
		if(e.type.equals(EventsConstants.KeyPressed))
		{
			keyReleased(e.strValue);
		}
		else if(e.type.equals(EventsConstants.KeyReleased))
		{
			keyPressed(e.strValue);
		}
		else if(e.type.equals(EventsConstants.KeyTyped))
		{
			keyTyped(e.strValue);
		}
	
	}


	@Override
	public Component clone() {
		Component clone = ComponentFactory.getInstance().createComponent(this.myName);
		clone.myParent =this.myParent;
		return clone;
	}
	
	
	@Override
	public Element saveState() {
		

		Element awsdKey = doc.createElement("Component");
		awsdKey.setAttribute("name", this.myName);
		
		Element speedNode = doc.createElement("speed");
		speedNode.setAttribute("float",""+ this.mySpeed);
		Element maxImpulseNode  = doc.createElement("maxImpulse");
		maxImpulseNode.setAttribute("float",""+ this.myMaxImpulse);
		
		awsdKey.appendChild(speedNode);
		awsdKey.appendChild(maxImpulseNode);
		
		return awsdKey;
		
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
							&&  tempNode.getNodeName() == "speed")
				 {
					 NamedNodeMap nodeMap = tempNode.getAttributes();		 
					 Node posNode = nodeMap.item(0);
					 String posStr = posNode.getNodeValue();
					 this.mySpeed = Double.parseDouble(posStr);
				 }
				
				 else if(tempNode.getNodeType() == Node.ELEMENT_NODE
							&&  tempNode.getNodeName() == "maxImpulse")
				 {
					 NamedNodeMap nodeMap = tempNode.getAttributes();		 
					 Node posNode = nodeMap.item(0);
					 String maxImpulseStr = posNode.getNodeValue();
					 this.myMaxImpulse = Double.parseDouble(maxImpulseStr);
				}
				
				
				/*NodeList nodeList = node.getChildNodes();
				
				Node maxImpulseNode = nodeList.item(0);
				NamedNodeMap maxImpulseMap = maxImpulseNode.getAttributes();
				Node maxImpulseAttr = maxImpulseMap.item(0);
				String maxImpulseStr = maxImpulseAttr.getNodeValue();
			
				Node speedNode = nodeList.item(2);
				NamedNodeMap speedMap = speedNode.getAttributes();
				Node speedAttr = speedMap.item(0);
				String speedStr = speedAttr.getNodeValue();
			
				this.myMaxImpulse = Double.parseDouble(maxImpulseStr);
				this.mySpeed = Double.parseDouble(speedStr);*/
				
			}
			
			
			
			
		}
	}
}
