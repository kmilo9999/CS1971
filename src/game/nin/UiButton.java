package game.nin;

import fxengine.UISystem.UISprite;
import fxengine.math.Vec2d;
import fxengine.scene.GameWorldScene;
import game.alch.AlchScene;
import javafx.scene.input.MouseEvent;

public class UiButton extends UISprite{
	protected Vec2d myOriginalPos;
	protected String myElementType;
	protected NinScene parentScene;
	
	public UiButton(String filePath,NinScene scene, double x,double y) {
		super(filePath, x, y);
		// TODO Auto-generated constructor stub
		this.myOriginalPos = new Vec2d(x,y);
		this.parentScene = scene;
	}
   
	

	/*@Override
	public  void onMouseReleased(MouseEvent e) 
	{
		this.parentScene.onResetScene();
		super.onMouseReleased(e);
	}*/
	
	public String getElementType() {
		return myElementType;
	}

	public void setElementType(String elementType) {
		this.myElementType = elementType;
	}
}
