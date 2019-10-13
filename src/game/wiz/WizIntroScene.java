package game.wiz;

import fxengine.UISystem.UIElement;
import fxengine.UISystem.UISprite;
import fxengine.application.GameApplication;
import fxengine.scene.GameWorldScene;
import javafx.scene.input.KeyEvent;

public class WizIntroScene extends GameWorldScene{

	UIElement introLayout;
	
	public WizIntroScene(String name, GameApplication application) {
		super(name, application);
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void initScene() 
	{
		//Initialize game world
		super.initScene(); 
		introLayout  = new UISprite("img/introInfo.png", 250, 150);
		this.addProp(introLayout);
	}
	
	@Override
	public void onTick(long nanosSincePreviousTick)
	{
		super.onTick(nanosSincePreviousTick);
	}
    
	
	@Override
	public void onKeyTyped(KeyEvent e) {
		
		((WizGame)this.myApplication).setActiveScreen("level1");
		
	}
	
}
