package game.alch;

import fxengine.UISystem.UISprite;
import fxengine.math.Vec2d;
import fxengine.scene.GameWorldScene;
import javafx.scene.input.MouseEvent;

public class AlchElementMenu extends UISprite{

	private Vec2d myOriginalPos;
	private String myElementType;
	
	public AlchElementMenu(String filePath, String type,double x, double y) {
		super(filePath, x, y);
		// TODO Auto-generated constructor stub
		this.myOriginalPos = new Vec2d(x,y);
		this.myElementType = type;
	}
   
	

	@Override
	public void onMouseReleased(MouseEvent e) 
	{
		Vec2d viewPortTopLeft = ((GameWorldScene)this.getScene()).getGameWorld().getPanelScreenViewPortUpperLeft();
		Vec2d viewPortSize = ((GameWorldScene)this.getScene()).getGameWorld().getPanelScreenViewPortSize();
		
		Vec2d aBottomRigth = new Vec2d(this.myPosition.x + this.mySize.x,this.myPosition.y + this.mySize.y);
		Vec2d bBottomRigth = new Vec2d(viewPortTopLeft.x + viewPortSize.x ,viewPortTopLeft.y + viewPortSize.y );
		
		if (this.myPosition.x > bBottomRigth.x   || viewPortTopLeft.x > aBottomRigth.x) 
		{
			//place in original position
			this.myPosition = this.myOriginalPos;
			
			return;
		}
		  
		if (this.myPosition.y > bBottomRigth.y || viewPortTopLeft.y > aBottomRigth.y)
		{
			//place in original position
			this.myPosition = this.myOriginalPos;
			
		    return;
		}
		
		Vec2d myPosinGameCoordinates = ((GameWorldScene)this.getScene()).getGameWorld().screenToGameTransform(myPosition);
		
	    ((AlchScene)this.myScene).createAlchGameObjectFromMenu("alch_elem", myPosinGameCoordinates, this);
	    
		this.myPosition = this.myOriginalPos;
		super.onMouseReleased(e);
	}
	
	public String getElementType() {
		return myElementType;
	}

	public void setElementType(String elementType) {
		this.myElementType = elementType;
	}
}
