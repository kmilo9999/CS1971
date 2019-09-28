package fxengine.components;

import fxengine.UISystem.Layout;
import fxengine.manager.ResourceManager;
import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class SpriteComponent extends Layout{

	protected Image mySourceImage;
	protected String myFilePath;
	protected double myWidth;
	protected double myHeight;
	
	
	public SpriteComponent(String imagePath)
	{
		super(0, 0, 0, 0);
		this.myFilePath = imagePath;
		this.mySourceImage = ResourceManager.loadRasterImage(imagePath);
		this.myWidth = this.mySourceImage.getWidth();
		this.myHeight = this.mySourceImage.getHeight();
		
		this.mySize = new Vec2d(this.myWidth,this.myHeight);
	}
	
	public SpriteComponent(Image image,String imagePath)
	{
		super(0, 0, 0, 0);
		this.myFilePath = imagePath;
		this.mySourceImage = image;
		this.myWidth = this.mySourceImage.getWidth();
		this.myHeight = this.mySourceImage.getHeight();
		
		this.mySize = new Vec2d(this.myWidth,this.myHeight);
	}
	
	public SpriteComponent clone()
	{
		SpriteComponent clone = new SpriteComponent(this.myFilePath );
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
			
			double x_off = 0;
			double y_off = 0;
			
		    if(myPosition.x <=0)
			{
				x_off =  this.myWidth - mySize.x ;
			}
			
			if(myPosition.y <= 0)
			{
				y_off =  this.myHeight - mySize.y ;
			}
			
			graphicsCx.drawImage(mySourceImage,x_off,y_off,mySize.x,mySize.y,myPosition.x,myPosition.y,mySize.x,mySize.y);
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
