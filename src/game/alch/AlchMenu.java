package game.alch;

import fxengine.UISystem.Label;
import fxengine.UISystem.Layout;
import fxengine.UISystem.UIConstants;
import fxengine.UISystem.UIElement;
import fxengine.UISystem.UISprite;
import javafx.scene.text.Font;

public class AlchMenu {

	private UIElement myMenuLayout;
	private UIElement myMenuLabel;
	private AlchElementMenu mySqueareMenu;
	private AlchElementMenu myCircleMenu;
	
	private AlchScene myScene;
	
	public AlchMenu(AlchScene scene)
	{
		myMenuLayout = new Layout(450, 50, 70, 300, UIConstants.TRANSPARENT,2.5);
		myMenuLabel = new Label("Elements",450, 40, UIConstants.BLACK,Font.font ("Verdana", 20) );
		mySqueareMenu = new AlchElementMenu("resources/img/square.png","CIRCLE",455, 70);
		myCircleMenu = new AlchElementMenu("resources/img/circle.png","SQUARE",455, 160);
		myScene = scene;
		
		//myMenuLayout.addChildElement(mySqueareMenu);
		//myMenuLayout.addChildElement(myCircleMenu);
		
		myScene.addProp(mySqueareMenu);
		myScene.addProp(myCircleMenu);
		myScene.addProp(myMenuLayout);
		myScene.addProp(myMenuLabel);
	}
	
}
