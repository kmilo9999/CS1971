package fxengine.components;

import fxengine.UISystem.Layout;
import fxengine.UISystem.UIConstants;
import fxengine.manager.ResourceManager;
import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class SpriteComponent extends Component {

	private Layout myLayout;
	private Image mySourceImage;
	protected String myFilePath;
	protected double myImageWidth;
	protected double myImageHeight;
	
	
	public SpriteComponent(String name)
	{
		super(name);
	
		
	}
	
	public SpriteComponent clone()
	{
		SpriteComponent clone = new SpriteComponent(this.myFilePath );
		clone.myImageWidth = this.mySourceImage.getWidth();
		clone.myImageHeight = this.mySourceImage.getHeight();
		return clone;
	}
	
	public double getWidth() {
		return myImageWidth;
	}

	public void setWidth(double width) {
		this.myImageWidth = width;
	}

	public double getHeight() {
		return myImageHeight;
	}

	public void setHeight(double height) {
		this.myImageHeight = height;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		if(!isInitialized)
		{
			TransformComponent transform = (TransformComponent)this.myParent.getComponent(ComponentContants.transform);
			if(transform != null)
			{
				if(!transform.isInitialized)
				{
					transform.initialize();
				}
				
				if(!this.myFilePath.isEmpty())
				{
					this.mySourceImage = ResourceManager.loadRasterImage(myFilePath);
					this.myImageWidth = this.mySourceImage.getWidth();
					this.myImageHeight = this.mySourceImage.getHeight();
					myLayout = new Layout(transform.getPosition().x, transform.getPosition().y, this.myImageWidth, this.myImageHeight, UIConstants.TRANSPARENT);
				}
			}
			
			
			
			isInitialized = true;
		}
	
		
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub
		if(this.mySourceImage != null)
		{
			//this.myColor = this.myIsHoverd? UIConstants.LIGTHGRAY:UIConstants.TRANSPARENT;
			//this.myColor = UIConstants.LIGTHGRAY;
			//this.myShape.setPosition(myPosition);
			//this.myShape.onDraw(graphicsCx);
			
			
			TransformComponent transform = (TransformComponent)this.myParent.getComponent(ComponentContants.transform);
			if(transform != null)
			{
				myLayout.setPosition(transform.getPosition());
				myLayout.onDraw(graphicsCx);
				
				double x_off = 0;
				double y_off = 0;
				
				
				Vec2d positionScreenSpace = this.myParent.getGameWorld().gameToScreenTransform(transform.getPosition());
				
			    if(positionScreenSpace.x <= this.myParent.getGameWorld().getPanelScreenViewPortUpperLeft().x)
				{
					x_off =  this.myImageWidth - this.myLayout.getSize().x ;
				}
				
				if(positionScreenSpace.y <= this.myParent.getGameWorld().getPanelScreenViewPortUpperLeft().y)
				{
					y_off =  this.myImageHeight - this.myLayout.getSize().y ;
				}
				
				graphicsCx.drawImage(mySourceImage,x_off,y_off,this.myLayout.getSize().x,this.myLayout.getSize().y,transform.getPosition().x,transform.getPosition().y,this.myLayout.getSize().x,this.myLayout.getSize().y);
			}
			
			
		}
	}

	public String getMyFilePath() {
		return myFilePath;
	}

	public void setFilePath(String myFilePath) {
		this.myFilePath = myFilePath;
	}

	public Layout getLayout() {
		return myLayout;
	}

	public void setLayout(Layout myLayout) {
		this.myLayout = myLayout;
	}

}
