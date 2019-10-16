package game.wiz;

import fxengine.UISystem.UIElement;
import fxengine.UISystem.UISprite;
import fxengine.application.GameApplication;
import fxengine.scene.GameWorldScene;
import javafx.scene.input.KeyEvent;

public class WizIntroScene extends GameWorldScene{

	UIElement introLayout;
	String myImagefilePath;
	String myNexSceneStr;
	public WizIntroScene(String name, GameApplication application, String imagePath, String nextScene) {
		super(name, application);
		// TODO Auto-generated constructor stub
		myImagefilePath = imagePath;
		myNexSceneStr = nextScene;
	}
	
	@Override
	public void initScene() 
	{
		//Initialize game world
		super.initScene(); 
		introLayout  = new UISprite(myImagefilePath, 250, 150);
		this.addProp(introLayout);
	}
	
	@Override
	public void onTick(long nanosSincePreviousTick)
	{
		super.onTick(nanosSincePreviousTick);
	}
    
	
	@Override
	public void onKeyTyped(KeyEvent e) {
		
		((WizGame)this.myApplication).setActiveScreen(myNexSceneStr);
		
	}
	
}
