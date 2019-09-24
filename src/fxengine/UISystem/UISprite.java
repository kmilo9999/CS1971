package fxengine.UISystem;

import fxengine.manager.ResourceManager;
import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class UISprite extends Layout{

	protected Image mySourceImage;
	protected String myFilePath;
	protected double myWidth;
	protected double myHeight;
	

	
	public UISprite(String filePath,double x, double y) {
		super( x,  y, 0,  0);
		// TODO Auto-generated constructor stub
		
		this.myFilePath = filePath;
		this.mySourceImage = ResourceManager.loadRasterImage(filePath);
		this.myWidth = this.mySourceImage.getWidth();
		this.myHeight = this.mySourceImage.getHeight();
		
		this.mySize = new Vec2d(this.myWidth/2,this.myHeight/2);
		
		
	}
	
	public  void onDraw(GraphicsContext graphicsCx) {
		if(this.mySourceImage != null)
		{
			//this.myColor = this.myIsHoverd? UIConstants.LIGTHGRAY:UIConstants.TRANSPARENT;
			//this.myColor = UIConstants.LIGTHGRAY;
			//this.myShape.setPosition(myPosition);
			//this.myShape.onDraw(graphicsCx);
			super.onDraw(graphicsCx);
			graphicsCx.drawImage(this.mySourceImage,this.myPosition.x,this.myPosition.y,this.mySize.x,this.mySize.y);
		}

	};
	
	public void onMouseMoved(MouseEvent e) {
		myMouseLastPosition = myMousePosition;
		myMousePosition = new Vec2d(e.getX(), e.getY());	
	}
	
	public void onMouseClicked(MouseEvent e)
	{
		//System.out.println("UISprite CLICKED");
		super.onMouseClicked(e);
	};
   
	@Override
	public void onMousePressed(MouseEvent e)
	{
		System.out.println("UISprite onMousePressed");
	}
	
	@Override
	public void onMouseReleased(MouseEvent e) {
		System.out.println("UISprite onMouseReleased");
	}
	
	public void onMouseDragged(MouseEvent e) {

	}


}
