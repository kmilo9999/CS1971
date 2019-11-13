package game.nin;

import javafx.scene.input.MouseEvent;

public class ResetButton extends UiButton{

	public ResetButton(String filePath, NinScene scene, double x, double y) {
		super(filePath, scene, x, y);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public  void onMouseReleased(MouseEvent e) 
	{
		this.parentScene.onResetScene();
		super.onMouseReleased(e);
	}
}
