package game.nin;

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
		super.onMouseReleased(e);
	}
	
}
