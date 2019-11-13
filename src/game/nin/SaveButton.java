package game.nin;

import fxengine.math.Vec2d;
import javafx.scene.input.MouseEvent;

public class SaveButton extends UiButton{

	public SaveButton(String filePath, NinScene scene, double x, double y) {
		super(filePath, scene, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public  void onMouseReleased(MouseEvent e) 
	{
		this.parentScene.saveState();
		this.parentScene.myFiledSavedMessage.setPosition(new Vec2d(350, 190)) ;
		this.parentScene.isShowing = true;
		
		super.onMouseReleased(e);
	}
	
}
