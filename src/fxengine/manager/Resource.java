package fxengine.manager;

import javafx.scene.image.Image;
import javafx.scene.media.Media;

public class Resource {

	public enum ResourceType 
	{ 
	    Image, Sound; 
	} 
	
	private String myFilePath;
	private ResourceType myType;
	private Image myImage;
	private Media mySound;
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
			
		}
		
		// So far only supports Images
	}
}
