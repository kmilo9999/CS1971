package fxengine.manager;

import java.util.HashMap;
import java.util.Map;

import fxengine.manager.Resource.ResourceType;

public class ResourceManager {

	private static ResourceManager instance = null;
	
	private Map<String,Resource> myResources; 
	
	private ResourceManager() {
		myResources = new HashMap<String, Resource>();
		
	};
	
	public static ResourceManager getIntance()
	{
		if(instance == null)
		{
			instance = new ResourceManager();
		}
		
		return instance;
	}
	
	
	public Resource createOrGetResource(String filePath, ResourceType type)
	{
		if(myResources.containsKey(filePath))
		{
			return myResources.get(filePath);
		}
		
		Resource resource = new Resource(filePath, type);
		resource.load();
		myResources.put(filePath,resource);
		
		return resource;
	}
	
}
