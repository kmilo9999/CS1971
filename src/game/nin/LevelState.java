package game.nin;

import fxengine.scene.GameWorldScene;

public abstract class LevelState {

	 GameWorldScene myScene;
	 boolean myLoadFromFile;
	 public String myFileName;
	
	 public LevelState(boolean loadFromFIle, String fileName) {
		 this.myLoadFromFile = loadFromFIle;
		 this.myFileName = fileName;
	 };
	 
	 public abstract void initialize() ;
	 public abstract void onResetLevel();
	 public abstract void onTick(long nanosSincePreviousTick);

	public GameWorldScene getScene() {
		return myScene;
	}

	public void setScene(GameWorldScene scene) {
		this.myScene = scene;
	}
	
}
