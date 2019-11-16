package game.nin;

import fxengine.scene.BaseScene;


public class NinMain {
	
	public static void main(String[] args)
	{
		NinGame game = new NinGame("Nin");
		
		
		BaseScene scene = new NinScene("level1", game);
		
		game.addScene(scene);
		
		game.setActiveScreen("level1");
		game.start(); 
		
	}
}
