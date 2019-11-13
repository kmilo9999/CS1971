package game.nin;


import javafx.scene.input.MouseEvent;

public class LoadButton extends UiButton{

	public LoadButton(String filePath, NinScene scene, double x, double y) {
		super(filePath, scene, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public  void onMouseReleased(MouseEvent e) 
	{
		this.parentScene.cleanScene();
		super.onMouseReleased(e);
	}
}
