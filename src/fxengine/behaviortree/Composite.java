package fxengine.behaviortree;

import java.util.ArrayList;
import java.util.List;

public abstract class Composite extends BTNode {
	
	
	public List<BTNode> children = new ArrayList<BTNode>();
	public BTNode lastRunning = null;
	
}