package fxengine.components;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;

public class TransformComponent extends Component {

	private Vec2d myPosition = new Vec2d(0);
	private Vec2d myRotation;
	private Vec2d myScale = new Vec2d(1);

	public TransformComponent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		isInitialized = true;
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
         
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public Vec2d getPosition() {
		return myPosition;
	}

	public void setPosition(Vec2d position) {
		this.myPosition = position;
	}

	public Vec2d getRotation() {
		return myRotation;
	}

	public void setRotation(Vec2d rotation) {
		this.myRotation = rotation;
	}

	public Vec2d getScale() {
		return myScale;
	}

	public void setScale(Vec2d scale) {
		this.myScale = scale;
	}

	@Override
	public void draw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub

	}

	@Override
	public Component clone() {
		Component clone = ComponentFactory.getInstance().createComponent(this.myName);
		clone.myParent = this.myParent;
		return clone;
	}

	@Override
	public Element saveState() {
		
		Element transform = doc.createElement("Component");
		transform.setAttribute("name", this.myName);
		
		Element position = doc.createElement("position");
		position.setAttribute("Vec2d", this.myPosition.x + " " +this.myPosition.y);
		transform.appendChild(position);
		return transform;
	}

	
	@Override
	public void loadState(Node node) {

		if (node.hasChildNodes()) {
			NodeList nodeList = node.getChildNodes();
			 for (int index = 0; index < nodeList.getLength(); index++) 
			 {
				 Node tempNode = nodeList.item(index);
				   
				   if(tempNode.getNodeType() == Node.ELEMENT_NODE
							&&  tempNode.getNodeName() == "position")
				   {
					   NamedNodeMap nodeMap = tempNode.getAttributes();		 
						Node posNode = nodeMap.item(0);
						String posStr = posNode.getNodeValue();
						String[] arrOfStr = posStr.split(" ");
						Vec2d pos = new Vec2d(Double.parseDouble(arrOfStr[0]),Double.parseDouble(arrOfStr[1]));
						this.setPosition(pos);
				   }
			 }
			
			
			
		
		}
	}

}
