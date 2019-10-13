package game.wiz;

import fxengine.scene.BaseScene;


public class WizMain {
	public static void main(String[] args) {
		
		String fogOfWar = "Yes";
		String level1 = "";
		String level2 = "";
		
		switch(args.length )
		{
		case 1:
			fogOfWar = args[0];
			break;
		case 2:
			fogOfWar = args[0];
			level1 = args[1];
			break;
		case 3:
			fogOfWar = args[0];
			level1 = args[1];
			level2 = args[2];
			break;
		}
		
		
		WizGame game = new WizGame("Wiz");
		BaseScene level1Scene = new WizScene("level1",game,fogOfWar,level1,0);
		BaseScene level2Scene = new WizScene("level2",game,fogOfWar,level2,1);

		game.addScene(level1Scene);
		game.addScene(level2Scene);
		game.setActiveScreen("level1");
		game.start(); // begin processing events
		// don't put code after start()!
	}
}
