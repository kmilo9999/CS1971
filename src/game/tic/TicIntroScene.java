package game.tic;

import fxengine.UISystem.Label;
import fxengine.UISystem.Layout;
import fxengine.UISystem.UIConstants;
import fxengine.UISystem.UIElement;
import fxengine.application.FXFrontEnd;
import fxengine.scene.BaseScene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


/**
 * 
 * Tic intro scene. Composed of 3 UI Labels describing the name of the game, its creator and some instructions
 * @author cdiaz8
 *
 */
public class TicIntroScene extends BaseScene{

	
	public TicIntroScene(String name,FXFrontEnd application) {
		super(name,application);
		// TODO Auto-generated constructor stub
		this.myIsActive = false;
	}

	
	@Override
	public void initScene() {
		
		UIElement layout = new Layout(0	,0, this.myWindowSize.x , this.myWindowSize.y);
		//System.out.println("layout.size: "+this.myWindowSize.x/2 +" "+ this.myWindowSize.y/2);
		UIElement myMainTitleLabel = new Label("CS 1971 TIC TAC TOE", 30 , 30 ,UIConstants.FIREBRICK );
		UIElement myNameLabel = new Label("By Camilo Diaz", 30 , 50, UIConstants.FIREBRICK );
		UIElement myInstructionsLabel = new Label("Press Enter or Click to continue", 30 , 70 ,UIConstants.FIREBRICK);
		
		layout.addChildElement(myMainTitleLabel);
		layout.addChildElement(myNameLabel);
		layout.addChildElement(myInstructionsLabel);
		
		this.props.add(layout);
	}
	
	@Override
	public void onKeyPressed(KeyEvent event) {
		 if (event.getCode() == KeyCode.ENTER) {
			 ((TicGame)this.myApplication).setActiveScreen("playboard");
		 } 
	}
	
	@Override
	public void onMouseClicked(MouseEvent e) {
		
		((TicGame)this.myApplication).setActiveScreen("playboard");
	}
	
	
	
}
