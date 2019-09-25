package fxengine.objects;

import java.util.HashMap;
import java.util.Map;

import fxengine.components.Component;

public class GameObject {

	private GameWorld myGameWorld;
	
	private String myId;
	
	private String myTag;
	

	private Map<String,Component> myComponents = new HashMap<String, Component>();
	
	private boolean isSelected = false;
	
	private int myLayerOrder = 0;
	
	private boolean isInitialized = false;
	
	private boolean markForDestoryed = false;


	

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
		if(myGameWorld.getSelected() == null)
		{			
			myGameWorld.setSelected(this);
		}
		else if(!myGameWorld.getSelected().getId().equals(myId))
		{
			myGameWorld.getSelected().setSelected(false);
			myGameWorld.setSelected(this);
		}
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
}
