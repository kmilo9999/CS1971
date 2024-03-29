package fxengine.components;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fxengine.UISystem.Layout;
import fxengine.UISystem.UIConstants;
import fxengine.manager.Resource;
import fxengine.manager.Resource.ResourceType;
import fxengine.manager.ResourceManager;
import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;


public class SpriteComponent extends Component {

	protected Layout myLayout;
	protected Image mySourceImage;
	protected String myFilePath;
	protected double myImageWidth;
	protected double myImageHeight;
	protected double myXClipOffSet = 0;
	protected double myYClipOffSet = 0;
	protected double mySpriteXCoordinates = 0;
	protected double mySpriteYCoordinates = 0;
	
	
	
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
					
					if(loadImageSprite())
					{
						Vec2d position = transform.getPosition();
						myLayout = new Layout(position.x, position.y, this.myImageWidth, this.myImageHeight, UIConstants.TRANSPARENT);
					}
					else
					{
						isInitialized = false;
						return;
					}
					
				}
			}
			
			
			
			isInitialized = true;
		}
	
		
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		TransformComponent transform = (TransformComponent)this.myParent.getComponent(ComponentContants.transform);
		if(transform != null)
		{
			Vec2d layoutScreenPos = new Vec2d(myLayout.getPosition()).minus(this.myParent.getGameWorld().getPanelScreenViewPortUpperLeft());
			
			
		    if(layoutScreenPos.x <= this.myParent.getGameWorld().getPanelScreenViewPortUpperLeft().x)
			{
				myXClipOffSet =  this.myImageWidth - this.myLayout.getSize().x ;
			}
			
			if(layoutScreenPos.y <= this.myParent.getGameWorld().getPanelScreenViewPortUpperLeft().y)
			{
				myYClipOffSet =  this.myImageHeight - this.myLayout.getSize().y ;
			}
		}
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
			
			Vec2d layoutScreenPos = new Vec2d(myLayout.getPosition()).minus(this.myParent.getGameWorld().getPanelScreenViewPortUpperLeft());
			myLayout.setPosition(layoutScreenPos);
			myLayout.onDraw(graphicsCx);
			
			graphicsCx.setGlobalBlendMode(BlendMode.SRC_OVER);	
			//graphicsCx.setGlobalBlendMode(BlendMode.OVERLAY);
			graphicsCx.drawImage(mySourceImage, mySpriteXCoordinates + myXClipOffSet,mySpriteYCoordinates +myYClipOffSet,this.myLayout.getSize().x,
						this.myLayout.getSize().y,layoutScreenPos.x,layoutScreenPos.y,
						this.myLayout.getSize().x,this.myLayout.getSize().y);
			
			graphicsCx.setGlobalBlendMode(BlendMode.SRC_ATOP);
			
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
	
	protected boolean loadImageSprite()
	{
		if(!this.myFilePath.isEmpty())
		{
				
			Resource imageResource = ResourceManager.getIntance().createOrGetResource(myFilePath, ResourceType.Image);
			if(imageResource.isLoaded())
			{
				this.mySourceImage = imageResource.getImage();
				this.myImageWidth = this.mySourceImage.getWidth();
				this.myImageHeight = this.mySourceImage.getHeight();
				return true;
			}
		}
		
		return false;
	}

	public double getMySpriteXCoordinates() {
		return mySpriteXCoordinates;
	}

	public void setMySpriteXCoordinates(double mySpriteXCoordinates) {
		this.mySpriteXCoordinates = mySpriteXCoordinates;
	}

	public double getMySpriteYCoordinates() {
		return mySpriteYCoordinates;
	}

	public void setMySpriteYCoordinates(double mySpriteYCoordinates) {
		this.mySpriteYCoordinates = mySpriteYCoordinates;
	}

	@Override
	public Element saveState() {
		// TODO Auto-generated method stub
		Element sprite = doc.createElement("Component");
		sprite.setAttribute("name", this.myName);
		
		Element filePath = doc.createElement("filePath");
		filePath.setAttribute("String", this.myFilePath);
		sprite.appendChild(filePath);
		return sprite;
		
	}

	@Override
	public void loadState(Node node) {
		// TODO Auto-generated method stub
		
			if (node.hasChildNodes()) {
				NodeList nodeList = node.getChildNodes();
				 for (int index = 0; index < nodeList.getLength(); index++) 
				 {
					 Node tempNode = nodeList.item(index);
					   
					   if(tempNode.getNodeType() == Node.ELEMENT_NODE
								&&  tempNode.getNodeName() == "filePath")
					   {
						   NamedNodeMap nodeMap = tempNode.getAttributes();		 
						   Node posNode = nodeMap.item(0);
						   String posStr = posNode.getNodeValue();
						   this.myFilePath = posStr;
					   }
			}
			
			
			
	  }
		
	}

}
