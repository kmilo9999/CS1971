package fxengine.behaviortree;

import java.util.ArrayList;


public class Sequence extends Composite {

	int lastRunnedNode;
	
	public Sequence()
	{
		
	}
	
	@Override
	public Status update(long seconds) {
		
		for (int i = lastRunnedNode; i < children.size(); i++) {
			BTNode node = children.get(i);
			Status nodeStatus = node.update(seconds); 
			if(nodeStatus == Status.RUNNING)
			{
				lastRunnedNode = i;
				return Status.RUNNING;
			}
			else if (nodeStatus == Status.FAIL) {
				lastRunnedNode = i;
				return Status.FAIL;
			}
		}
		lastRunnedNode = 0;
		//reset nodes
		for (int i = 0; i < children.size(); i++) 
		{
			children.get(i).reset();
		}
		
		
		return Status.SUCCED;
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		lastRunnedNode = 0;
	}

	@Override
	public int compareTo(BTNode o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
