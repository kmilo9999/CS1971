package fxengine.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class ResourceManager {

	private static ResourceManager instance = null;
	
	private ResourceManager() {};
	
	public static ResourceManager getIntance()
	{
		if(instance == null)
		{
			instance = new ResourceManager();
		}
		
		return instance;
	}
	
	public Image loadRasterImage(String path)
	{
		 Image img =null;
		//img = new Image(new FileInputStream(path));
		img = new Image(getClass().getClassLoader().getResource(path).toString(),false);
		 return img;
	}
	
}
