package fxengine.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javafx.scene.image.Image;
import javafx.scene.media.Media;

public class Resource {

	public enum ResourceType 
	{ 
	    Image, Sound, InternalTextFile, ExternalTextFile; 
	} 
	
	private String myFilePath;
	private ResourceType myType;
	private Image myImage;
	private Media mySound;
	private BufferedReader myFileText;
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
			try
			{
				String strPath = getClass().getClassLoader().getResource(this.myFilePath).toString();
				myImage  = new Image(strPath,false);
				if(myImage != null)
				{
					isLoaded = true;
					return;
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		
			
				System.err.println("Image " + this.myFilePath + "not found");
			
		}
		else if(this.myType == ResourceType.InternalTextFile && !this.myFilePath.isEmpty())
		{
			InputStream resource = getClass().getClassLoader().getResourceAsStream(this.myFilePath);
			  if (resource == null) {
		            throw new IllegalArgumentException("file is not found!");
		        } else {
		        	myFileText = new  BufferedReader(new InputStreamReader(resource));
		        }
			
			
			//myFileText  = new File(getClass().getClassLoader().getResource(this.myFilePath).toString());
			if(myFileText != null)
			{
				isLoaded = true;
				return;
			}
			
			System.err.println("File" + this.myFilePath + "not found");
		}
		else if(this.myType == ResourceType.ExternalTextFile && !this.myFilePath.isEmpty())
		{
			  FileReader fr = null;
			  
			try {
				fr = new FileReader(this.myFilePath);
				myFileText =new BufferedReader(fr);
				
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
	        
			if(myFileText != null)
			{
				isLoaded = true;
				return;
			}
			
			System.err.println("File" + this.myFilePath + "not found");
		}
		// So far only supports Images
	}

	public BufferedReader getFileReaader() {
		return myFileText;
	}

	public void setText(BufferedReader text) {
		this.myFileText = text;
	}
}
