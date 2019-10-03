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
	private AlchElementMenu myTriangleMenu;
	private AlchElementMenu myStarMenu;
	
	private AlchScene myScene;
	
	public AlchMenu(AlchScene scene)
	{
		myMenuLayout = new Layout(800, 50, 70, 350, UIConstants.TRANSPARENT,2.5);
		myMenuLabel = new Label("Elements",800, 40, UIConstants.BLACK,Font.font ("Verdana", 20) );
		mySqueareMenu = new AlchElementMenu("img/square.png","SQUARE",800, 70);
		myCircleMenu = new AlchElementMenu("img/circle.png","CIRCLE",800, 160);
		myTriangleMenu = new AlchElementMenu("img/triangle.png","TRIANGLE",800, 250);
		myStarMenu = new AlchElementMenu("img/star.png","STAR",800, 330);
		myScene = scene;
		
		//myMenuLayout.addChildElement(mySqueareMenu);
		//myMenuLayout.addChildElement(myCircleMenu);
		
		myScene.addProp(mySqueareMenu);
		myScene.addProp(myCircleMenu);
		myScene.addProp(myTriangleMenu);
		myScene.addProp(myStarMenu);
		myScene.addProp(myMenuLayout);
		myScene.addProp(myMenuLabel);
	}
	
}
