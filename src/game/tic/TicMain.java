package game.tic;


import fxengine.scene.BaseScene;

public class TicMain {

	public static void main(String[] args) {
		TicGame game = new TicGame("Tic-Tac-Toe");
		BaseScene playScene = new TicPlayScene("playboard",game);
		BaseScene introScene = new TicIntroScene("intro",game);
		game.addScene(introScene);
		game.addScene(playScene);
		game.setActiveScreen("playboard");
		game.start(); // begin processing events
		// don't put code after start()!
	}
	
	
}
