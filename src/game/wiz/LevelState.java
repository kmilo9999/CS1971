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

public class LevelState {
    private int numEnemies;
    private List<WizCharacter> enemies = new ArrayList<WizCharacter>();
    private int numLevel;
    private GameTileMap myTerrain;
    
    
    public LevelState(int numEnemies, List<Vec2d> enemiesInitialPositions, GameTileMap terrain)
    {
    	myTerrain = terrain;
    	for(int i = 0 ; i < numEnemies ; i++)
    	{
    		 List<Animation> animations = new ArrayList<Animation>();
    		 int xAnimationInitPosition = 0;
    		 int yAnimationInitPosition = 0;
    		 if(i == 0)
    		 {
    			 xAnimationInitPosition = 96;
    			 yAnimationInitPosition = 0 ;
    		 }
    	     Animation right = new Animation(WizCharacter.moveRight,new Vec2d(xAnimationInitPosition,yAnimationInitPosition + 36),new Vec2d(32,36),new Vec2i(1,3));
    		 Animation left = new Animation(WizCharacter.moveLeft,new Vec2d(xAnimationInitPosition,yAnimationInitPosition+ 108),new Vec2d(32,36),new Vec2i(1,3));
    		 Animation up = new Animation(WizCharacter.moveUp,new Vec2d(xAnimationInitPosition,yAnimationInitPosition),new Vec2d(32,36),new Vec2i(1,3));
    		 Animation down = new Animation(WizCharacter.moveDown,new Vec2d(xAnimationInitPosition,yAnimationInitPosition + 72),new Vec2d(32,36),new Vec2i(1,3));
    		 animations.add(right);
    		 animations.add(left);
    		 animations.add(up);
    		 animations.add(down);
    	    	
    		
    		WizCharacter enemy= new WizAiCharacter("enemy"+i,"priest", new Vec2d(416,108),animations);

			Composite root = new Sequence();

			Node controlPoint0 = new Node(new Vec2i(3, 13));
			Node controlPoint1 = new Node(new Vec2i(3, 10));
			Node controlPoint2 = new Node(new Vec2i(3, 13));
			// Node controlPoint2 = new Node(new Vec2i(2,10));
			// Node controlPoint3 = new Node(new Vec2i(2,14));
			// Node controlPoint4 = new Node(new Vec2i(3,13));
			Queue<Node> controlsPoints = new LinkedList<Node>();
			controlsPoints.add(controlPoint0);
			controlsPoints.add(controlPoint1);
			controlsPoints.add(controlPoint2);
			// controlsPoints.add(controlPoint3);
			// controlsPoints.add(controlPoint4);

			Action patrol = new PatrolAction((GameObject) enemy, myTerrain, controlsPoints);
			Condition timeDelay = new TimeDelay(3);
			root.children.add(timeDelay);
			root.children.add(patrol);

			BehaviorTree behaviorTree = new BehaviorTree(root);

			AIComponent aiComponent = (AIComponent) ComponentFactory.getInstance()
					.createComponent(ComponentContants.AI);
			aiComponent.setBehaviorTree(behaviorTree);
			enemy.addComponent(aiComponent);

    		enemies.add(enemy);
    	}
    }

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
