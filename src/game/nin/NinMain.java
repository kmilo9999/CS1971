package game.nin;

import fxengine.scene.BaseScene;


public class NinMain {
	
	public static void main(String[] args)
	{
		NinGame game = new NinGame("Nin");
		String loadFile = "";
		if(args.length > 0)
		{
			loadFile = args[0];
		}
		
		BaseScene level1 = new NinScene(1,"level1", game,new NinLevel1(false, loadFile));
		BaseScene level2 = new NinScene(2,"level2", game,new NinLevel2(false, loadFile));
		BaseScene end = new NinFinalScene("End", game);
		
		
		game.addScene(level1);
		game.addScene(level2);
		game.addScene(end);
		
		game.setActiveScreen("level1");
		game.start(); 
		
	}
}