package fxengine.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

import javafx.scene.image.Image;
import javafx.scene.media.Media;

public class Resource {

	public enum ResourceType 
	{ 
	    Image, Sound, TextFile; 
	} 
	
	private String myFilePath;
	private ResourceType myType;
	private Image myImage;
	private Media mySound;
	private File myFileText;
	private boolean isLoaded = false;
	
	public Resource(String filePath, ResourceType type)
	{
		this.myFilePath = filePath;
		this.myType = type;
	}

	public String getFilePath() {
		return myFilePath;
	}

	public void setFilePath(String myFilePath) {
		this.myFilePath = myFilePath;
	}

	public ResourceType getType() {
		return myType;
	}

	public void setType(ResourceType myType) {
		this.myType = myType;
	}

	public Image getImage() {
		return myImage;
	}

	public void setImage(Image myImage) {
		this.myImage = myImage;
	}

	public Media getSound() {
		return mySound;
	}

	public void setSound(Media mySound) {
		this.mySound = mySound;
	}

	public boolean isLoaded() {
		return isLoaded;
	}

	public void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}
	
	public void load()
	{
		if(this.myType == ResourceType.Image && !this.myFilePath.isEmpty())
		{
			myImage  = new Image(getClass().getClassLoader().getResource(this.myFilePath).toString(),false);
			if(myImage != null)
			{
				isLoaded = true;
				return;
			}
			
				System.err.println("Image " + this.myFilePath + "not found");
			
		}
		else if(this.myType == ResourceType.TextFile && !this.myFilePath.isEmpty())
		{
			URL resource = getClass().getClassLoader().getResource(this.myFilePath);
			  if (resource == null) {
		            throw new IllegalArgumentException("file is not found!");
		        } else {
		        	myFileText = new  File(resource.getFile());
		        }
			
			
			//myFileText  = new File(getClass().getClassLoader().getResource(this.myFilePath).toString());
			if(myFileText != null)
			{
				isLoaded = true;
				return;
			}
			
			System.err.println("File" + this.myFilePath + "not found");
		}
		
		// So far only supports Images
	}

	public File getFileReaader() {
		return myFileText;
	}

	public void setText(File text) {
		this.myFileText = text;
	}
}
