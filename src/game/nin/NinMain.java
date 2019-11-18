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
		
		BaseScene scene = new NinScene("level1", game,loadFile);
		
		game.addScene(scene);
		
		game.setActiveScreen("level1");
		game.start(); 
		
	}
}