package game.wiz;

import fxengine.scene.BaseScene;


public class WizMain {
	public static void main(String[] args) {
		WizGame game = new WizGame("Wiz");
		BaseScene playScene = new WizScene("playboard",game);
		game.addScene(playScene);
		game.setActiveScreen("playboard");
		game.start(); // begin processing events
		// don't put code after start()!
	}
}
