package game.alch;


import fxengine.scene.BaseScene;



public class AlchMain {
	
	public static void main(String[] args) {
		AlchGame game = new AlchGame("Alch");
		BaseScene playScene = new AlchScene("playboard",game);
		game.addScene(playScene);
		game.setActiveScreen("playboard");
		game.start(); // begin processing events
		// don't put code after start()!
	}
}
