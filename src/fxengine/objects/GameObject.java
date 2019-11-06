package fxengine.objects;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fxengine.components.Component;
import fxengine.components.ComponentFactory;
import fxengine.datamanagement.Serializable;

public class GameObject extends Serializable{

	private GameWorld myGameWorld;
	
	private String myId;
	
	private String myTag;
	

	private Map<String,Component> myComponents = new HashMap<String, Component>();
	
	private boolean isSelected = false;
	
	private int myLayerOrder = 0;
	
	private boolean isInitialized = false;
	
	private boolean markForDestoryed = false;
	
	
	public static GameObject buildGameObject(Node node)
	{
		 
		if (node.hasAttributes()) {
            			
			
			NamedNodeMap nodeMap = node.getAttributes();
			String id = nodeMap.item(0).getNodeValue();
			
			GameObject go = new GameObject(id);
			

			// Components
			if (node.hasChildNodes()) {
                
				NodeList nodeList = node.getChildNodes();
				for (int count = 0; count < nodeList.getLength(); count++) 
				{
				   Node tempNode = nodeList.item(count);
				   if(tempNode.getNodeType() == Node.ELEMENT_NODE
							&&  tempNode.getNodeName() == "Components")
				   {
					   NodeList componenList = tempNode.getChildNodes();
					   for (int index = 0; index < componenList.getLength(); index++) 
					   {
						   Node tempNode2 = componenList.item(index);
						   
						   if(tempNode2.getNodeType() == Node.ELEMENT_NODE
									&&  tempNode2.getNodeName() == "Component")
						   {
							   NamedNodeMap componentNodeMap = tempNode2.getAttributes();
							   String componentName = componentNodeMap.item(0).getNodeValue();
							   Component component = ComponentFactory.getInstance().createComponent(componentName);
							   component.loadState(tempNode2);
							   go.addComponent(component);
							   
						   }
						  
						  
					   }
					 
				   }
				   if(tempNode.getNodeType() == Node.ELEMENT_NODE
							&&  tempNode.getNodeName() == "Component")
				   {
					   NamedNodeMap massMap = tempNode.getAttributes();
						 Node massAttr = massMap.item(0);
						 String massStr = massAttr.getNodeValue();
						 go.setTag(massStr);
					   
				   }
				  
				}

			}
			
			return go;
			
		}
		
		// it should never get to this point
		return null;
	}
	
	
	public void initialize()
	{
		
		for(Map.Entry<String,Component> entry : myComponents.entrySet())
		{
			entry.getValue().initialize();
			
		}
		isInitialized = true; 
	}
	
	public GameObject clone()
	{
		GameObject clone = new GameObject(myId);
		clone.myGameWorld = myGameWorld;
		for(Map.Entry<String,Component> entry : myComponents.entrySet())
		{
			Component cloneComponent = entry.getValue().clone();
			clone.addComponent(cloneComponent);
		}
		clone.isSelected = false;
		clone.myLayerOrder = this.myLayerOrder;
		myGameWorld.addClonedGameObject(clone);
		
		return clone;
		
	}
	
	public GameObject(String id)
	{
		this.myId =id;	
	}
	
	
	public void addComponent(Component component)
	{
		component.setParent(this);
		myComponents.put(component.getName(),  component);
	}
	
	public void removeComponent(String name)
	{
		myComponents.remove(name);
	}
	
	public String getId() {
		return myId;
	}

	public void setId(String myId) {
		this.myId = myId;
	}
	
	public boolean hasComponent(String componentName)
	{
	  return myComponents.containsKey(componentName);	
	}
	
	public Component getComponent(String componentName) 
	{
		return myComponents.get(componentName);
	}

	public GameWorld getGameWorld() {
		return myGameWorld;
	}


	public void setGameWorld(GameWorld gameWorld) {
		this.myGameWorld = gameWorld;
	}
	
	public boolean isSelected() {
		return isSelected;
	}


	public void setSelected(boolean isSelected) {

			myGameWorld.setSelected(this);
			this.isSelected = isSelected;
		
	}
	
	public int getLayerOrder() {
		return myLayerOrder;
	}

	public void setLayerOrder(int layerOrder) {
		this.myLayerOrder = layerOrder;
	}
	
	public boolean isInitialized() {
		return isInitialized;
	}

	public void setInitialized(boolean isInitialized) {
		this.isInitialized = isInitialized;
	}

	public String getTag() {
		return myTag;
	}

	public void setTag(String tag) {
		this.myTag = tag;
	}
	
	public boolean isMarkForDestoryed() {
		return markForDestoryed;
	}

	public void setMarkForDestoryed(boolean markForDestoryed) {
		this.markForDestoryed = markForDestoryed;
	}

	@Override
	public Element saveState() {
		
		Element gameObject = doc.createElement("GameObject");
		gameObject.setAttribute("name", ""+this.getId());
		
		
		
		if(!myComponents.isEmpty())
		{
			Element components = doc.createElement("Components");
			for (Map.Entry<String,Component> entry : myComponents.entrySet())  
			{
				Element componentElement = entry.getValue().saveState();
				components.appendChild(componentElement);
			}	
			gameObject.appendChild(components);	
		}
		
		if(this.myTag != "")
		{
			Element tag = doc.createElement("Tag");
			tag.setAttribute("name", this.myTag);
			gameObject.appendChild(tag);
		}
		
		return gameObject;
		
	}

	@Override
	public void loadState(Node node) {
		// TODO Auto-generated method stub
		
	}
}
