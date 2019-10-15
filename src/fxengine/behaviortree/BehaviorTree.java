package fxengine.behaviortree;

import fxengine.behaviortree.BTNode.Status;

public class BehaviorTree {
   
	public static BlackBoard blackBoard;
	private BTNode myRoot;
	
	
	public BehaviorTree(BTNode root)
	{
		this.myRoot = root;
	}
	
	public Status update(long nanoSecondsSinceLastTick)
	{
		if(this.myRoot != null)
		{
			return myRoot.update(nanoSecondsSinceLastTick);
		}
		
		return Status.SUCCED;
	}
}
