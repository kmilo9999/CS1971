package fxengine.behaviortree;

import java.util.ArrayList;


public class Sequence extends Composite {

	int lastRunnedNode;
	
	public Sequence()
	{
		children = new ArrayList<BTNode>();
	}
	
	@Override
	public Status update(float seconds) {
		
		for (int i = lastRunnedNode; i < children.size(); i++) {
			BTNode node = children.get(i);
			if(node.update(seconds) == Status.RUNNING)
			{
				lastRunnedNode = i;
				return Status.RUNNING;
			}
			else if (node.update(seconds) == Status.FAIL) {
				lastRunnedNode = i;
				return Status.FAIL;
			}
		}
		return Status.SUCCED;
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		lastRunnedNode = 0;
	}
}
