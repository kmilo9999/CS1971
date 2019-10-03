package fxengine.UISystem;

import fxengine.manager.ResourceManager;
import fxengine.manager.Resource.ResourceType;
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
		this.mySourceImage = ResourceManager.getIntance().createOrGetResource(filePath, ResourceType.Image).getImage();
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
	
	@Override
	public void onMouseMoved(MouseEvent e) {
		//System.out.println("UISprite onMouseMoved");
		myMouseLastPosition = myMousePosition;
		myMousePosition = new Vec2d(e.getX(), e.getY());
		super.onMouseMoved(e);
	}
	
	public void onMouseClicked(MouseEvent e)
	{
		System.out.println("UISprite CLICKED " + this.myScene.getSceneName());
		super.onMouseClicked(e);
		
		
	};
   
	@Override
	public void onMouseDragged(MouseEvent e) {
		//System.out.println("UISprite onMouseDragged");
		
		int mouseButton = mapMouseButtonToInt(e.getButton());
		if (mouseButtons[0] && mouseButton == 0) {
			// is dragging this entity
		//	System.out.println("UISprite onMouseDragged");
			Vec2d currentMousePos = new Vec2d(e.getX(), e.getY());

			Vec2d delta = currentMousePos.minus(myMouseLastPosition);
			myPosition = myPosition.plus(delta);
			
			myMouseLastPosition = currentMousePos;

		}
	}
	
	public String getFilePath() {
		return myFilePath;
	}

	public void setFilePath(String filePath) {
		this.myFilePath = filePath;
	}

	public Image getSourceImage() {
		return mySourceImage;
	}

	public void setSourceImage(Image sourceImage) {
		this.mySourceImage = sourceImage;
	}
}
