package game.tic;

import fxengine.UISystem.LabeledButton;
import javafx.scene.input.MouseEvent;

/**
 * UI Button to indicate to start playing, or restart the game
 * @author cdiaz8
 *
 */
public class TicPlayButton extends LabeledButton{

	private TicPlayScene myScene;
	
	public TicPlayButton(double x, double y, String label, TicPlayScene scene) {
		super(x, y, label);
		this.myScene = scene;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	 public void onMouseClicked(MouseEvent e) {
		
		
		if(this.myScene.getCurrentState().equals(TicPlayScene.STATE_NO_PLAY) && !this.myScene.isGameOver())
		{
			this.setText("CLICK TO RESTART");  
			this.myScene.setCurrentState(TicPlayScene.STATE_PLAYING);	
		}
		else if(this.myScene.getCurrentState().equals(TicPlayScene.STATE_NO_PLAY) && this.myScene.isGameOver())
		{
			this.myScene.restartScene();
			
		}
		else if(this.myScene.getCurrentState().equals(TicPlayScene.STATE_PLAYING) && !this.myScene.isGameOver() )
		{
			this.setText("CLICK TO RESTART"); 
			this.myScene.restartScene();	
		}
		
	}
	
	

}
