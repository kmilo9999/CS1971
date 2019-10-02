package fxengine.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class ResourceManager {

	
	public static Image loadRasterImage(String path)
	{
		 
		  
		 Image img =null;
		try {
			img = new Image(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return img;
	}
	
}
