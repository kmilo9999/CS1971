package game.wiz;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import fxengine.PathFinding.Node;
import fxengine.behaviortree.Action;
import fxengine.behaviortree.BehaviorTree;
import fxengine.behaviortree.Composite;
import fxengine.behaviortree.Condition;
import fxengine.behaviortree.Sequence;
import fxengine.components.AIComponent;
import fxengine.components.Animation;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.maploader.GameTileMap;
import fxengine.math.Vec2d;
import fxengine.math.Vec2i;
import fxengine.objects.GameObject;

public abstract class LevelState {
    protected int numEnemies;
    protected List<WizCharacter> enemies = new ArrayList<WizCharacter>();
    protected int numLevel;
    protected GameTileMap myTerrain;
    protected List<Vec2d> myEnemiesInitialPositions;
    
    public LevelState(int numEnemies, List<Vec2d> enemiesInitialPositions, GameTileMap terrain)
    {
    	
    	myTerrain = terrain;
    	this.numEnemies = numEnemies;
    	myEnemiesInitialPositions = enemiesInitialPositions;
    
    }
    
    public  abstract void initialize() ;
    

	public int getNumEnemies() {
		return numEnemies;
	}

	public void setNumEnemies(int numEnemies) {
		this.numEnemies = numEnemies;
	}

	public List<WizCharacter> getEnemies() {
		return enemies;
	}

	public void setEnemies(List<WizCharacter> enemies) {
		this.enemies = enemies;
	}
    
    
}
