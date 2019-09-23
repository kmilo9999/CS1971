package fxengine.objects;

import fxengine.UISystem.Layout;
import fxengine.UISystem.UIConstants;
import fxengine.graphics.Shape;
import fxengine.graphics.ShapeFactory;
import fxengine.manager.ResourceManager;
import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;


public class Sprite extends Layout{

	protected Image mySourceImage;
	protected String myFilePath;
	protected double myWidth;
	protected double myHeight;
	
	
	public Sprite(String imagePath)
	{
		super(0, 0, 0, 0);
		this.myFilePath = imagePath;
		this.mySourceImage = ResourceManager.loadRasterImage(imagePath);
		this.myWidth = this.mySourceImage.getWidth();
		this.myHeight = this.mySourceImage.getHeight();
		
		this.mySize = new Vec2d(this.myWidth,this.myHeight);
	}
	
	
	public Sprite clone()
	{
		Sprite clone = new Sprite(this.myFilePath );
		clone.myWidth = this.mySourceImage.getWidth();
		clone.myHeight = this.mySourceImage.getHeight();
		return clone;
	}
	
	public void onDraw(GraphicsContext graphicsCx)
	{
		if(this.mySourceImage != null)
		{
			//this.myColor = this.myIsHoverd? UIConstants.LIGTHGRAY:UIConstants.TRANSPARENT;
			//this.myColor = UIConstants.LIGTHGRAY;
			//this.myShape.setPosition(myPosition);
			//this.myShape.onDraw(graphicsCx);
			super.onDraw(graphicsCx);
			
			
			graphicsCx.drawImage(mySourceImage,0,0,mySize.x,mySize.y,myPosition.x,myPosition.y,mySize.x,mySize.y);
		}

	}
	
	
	public double getWidth() {
		return myWidth;
	}

	public void setWidth(double width) {
		this.myWidth = width;
	}

	public double getHeight() {
		return myHeight;
	}

	public void setHeight(double height) {
		this.myHeight = height;
	}

}
