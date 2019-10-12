package game.wiz;

import fxengine.scene.BaseScene;


public class WizMain {
	public static void main(String[] args) {
		
		String fogOfWar = "Yes";
		String defaultMap = "";
		switch(args.length )
		{
		case 1:
			fogOfWar = args[0];
			break;
		case 2:
			fogOfWar = args[0];
			defaultMap = args[1];
			break;
		}
		
		
		WizGame game = new WizGame("Wiz");
		BaseScene playScene = new WizScene("playboard",game,fogOfWar,defaultMap);
		game.addScene(playScene);
		game.setActiveScreen("playboard");
		game.start(); // begin processing events
		// don't put code after start()!
	}
}
