package fxengine.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ResourceManager {

	
	public static Image loadRasterImage(String path)
	{
		  System.out.println(new java.io.File("").getAbsolutePath());
		  
		 Image img =null;
		try {
			img = new Image(new FileInputStream("resources/img/dragonballsuper.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return img;
	}
	
}
