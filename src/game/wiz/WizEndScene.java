package game.wiz;

import fxengine.UISystem.UIElement;
import fxengine.UISystem.UISprite;
import fxengine.application.GameApplication;
import fxengine.scene.GameWorldScene;
import javafx.scene.input.KeyEvent;

public class WizEndScene extends GameWorldScene{

	UIElement introLayout;
	double timeSinceLastUpdate = 0.0;
	double totalSeconds = 0.0;
	boolean blockInput = true;
	String myimageFilePath;
	String myNextLevel;
	
	public WizEndScene(String name, GameApplication application, String imageFilePath, String nextLevel) {
		super(name, application);
		// TODO Auto-generated constructor stub
		myimageFilePath = imageFilePath ;
		myNextLevel =nextLevel;
	}
   
	@Override
	public void initScene() 
	{
		//Initialize game world
		super.initScene(); 
		introLayout  = new UISprite(myimageFilePath, 250, 150);
		this.addProp(introLayout);
	}
	
	@Override
	public void onTick(long nanosSincePreviousTick)
	{
		timeSinceLastUpdate+=nanosSincePreviousTick;
		if(timeSinceLastUpdate > 1000000000 && blockInput)
		{
			totalSeconds++;
			if(totalSeconds >=6)
			{
				blockInput = false;
			}
		}
		super.onTick(nanosSincePreviousTick);
	}
    
	
	@Override
	public void onKeyTyped(KeyEvent e) {
		
		if(!blockInput)
		{
			if(myNextLevel.isEmpty())
			{
				myApplication.shutdown();	
			}
			((WizGame)this.myApplication).setActiveScreen(myNextLevel);
				
				
		}
		 
		
	}
}
