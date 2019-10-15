package fxengine.behaviortree;

import java.util.List;
import java.util.PriorityQueue;

public abstract class  Composite extends BTNode {
	
	
	List<BTNode> children;
	BTNode lastRunning;
	@Override
	public Status update(float seconds) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}