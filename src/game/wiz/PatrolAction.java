package game.wiz;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import fxengine.PathFinding.Node;
import fxengine.PathFinding.PathFinding;
import fxengine.behaviortree.Action;
import fxengine.behaviortree.BTNode;
import fxengine.behaviortree.BlackBoard;
import fxengine.behaviortree.BlackBoardData;
import fxengine.components.AIMovementComponent;
import fxengine.components.ComponentContants;
import fxengine.components.TransformComponent;
import fxengine.maploader.GameTileMap;
import fxengine.math.Vec2d;
import fxengine.math.Vec2i;
import fxengine.objects.GameObject;

public class PatrolAction extends Action{

	private Queue<Node> myPatrolPoints;

	private GameObject myGameObject;
	private GameTileMap myNavGraph;

	private Node currentNode;

	private Node nextNode;
	private Queue<Vec2d> currentPath;
	
	
	public PatrolAction(GameObject gameObject, GameTileMap navGraph, Queue<Node> controlPoints)
	{
		this.myGameObject = gameObject;
		this.myPatrolPoints = controlPoints;
		
		 
		BlackBoardData myAuxpatrolPoint = new BlackBoardData();
		myAuxpatrolPoint.queueNodeValue = new LinkedList<Node>();
		for(Node node:this.myPatrolPoints)
		{
			myAuxpatrolPoint.queueNodeValue.add(new Node(node.x,node.y));
		}
		
		BlackBoard.getInstance().datum.put("myAuxpatrolPoint", myAuxpatrolPoint);
		
		this.myNavGraph = navGraph;
		this.nextNode = null;
		this.currentPath = null;
	
	}
	
	@Override
	public Status update(long nanoseconds) {
		// TODO Auto-generated method stub
		if(currentStatus != Status.RUNNING)
		{
			setNextPoint();
				
		}else
		{
			//evaluate if reach the point
			TransformComponent tranform = (TransformComponent)myGameObject.getComponent(ComponentContants.transform);
		    Vec2i currentTile = myNavGraph.coordinateToTile(tranform.getPosition());
		    //System.out.println("running");
			if( currentPath != null && currentPath.isEmpty() )
			{
				if(!this.myPatrolPoints.isEmpty())
                {
					Vec2d tileCoordinate = myNavGraph.tileToCoordinate(this.nextNode.x,this.nextNode.y); 
					if(tranform.getPosition().equals(tileCoordinate))
					{
						this.currentNode = new Node(this.nextNode.x,this.nextNode.y);
						this.nextNode = null;
						setNextPoint();
						currentStatus = Status.RUNNING;	
					}
						
                }else
                {
                	Vec2d tileCoordinate = myNavGraph.tileToCoordinate(this.nextNode.x,this.nextNode.y); 
					if(tranform.getPosition().equals(tileCoordinate))
                	{
                		currentStatus = Status.SUCCED;
        				//System.out.println("End");	
                	}
                		
                }
				
			}
			else
			{
				if(this.nextNode != null)
				{
					Vec2d tileCoordinate = myNavGraph.tileToCoordinate(this.nextNode.x,this.nextNode.y);
					if(tranform.getPosition().equals(tileCoordinate) 
							&& !this.myPatrolPoints.isEmpty()) {
						this.currentNode = new Node(this.nextNode.x,this.nextNode.y);
						this.nextNode  = null;
						setNextPoint();
					}
				}
			}
				
		}
		
		
		return currentStatus;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		BlackBoardData myAuxpatrolPoint = BlackBoard.getInstance().datum.get("myAuxpatrolPoint");
		
		this.myPatrolPoints.clear();
		for(Node node: myAuxpatrolPoint.queueNodeValue)
		{
			this.myPatrolPoints.add(new Node(node.x,node.y));
		}
		
		this.currentNode = null;
		this.nextNode = null;
		
		currentStatus = Status.FAIL;
	}

	@Override
	public int compareTo(BTNode o) {
		// TODO Auto-generated method stub
		return 0;
	}

	private void setNextPoint()
	{
		if(this.myGameObject != null && this.myNavGraph !=null)
		{
			if(this.currentNode == null)
			{
				this.currentNode = myPatrolPoints.poll();
				 
			}
			//TransformComponent tranform = (TransformComponent)myGameObject.getComponent(ComponentContants.transform);
			if(this.currentNode != null && this.nextNode ==null)
			{
				this.nextNode =  this.myPatrolPoints.poll();
				 
				currentPath = myNavGraph.findPath(this.currentNode, this.nextNode);
				if(myGameObject.hasComponent(ComponentContants.AIMovement))
				{
				  AIMovementComponent aiMovement = (AIMovementComponent)myGameObject.getComponent(ComponentContants.AIMovement);
				  aiMovement.setPathPoints(currentPath);
				  currentStatus = Status.RUNNING;
				}
			}
			
			
		}
	}
	
	
}
