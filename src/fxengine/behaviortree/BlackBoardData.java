package fxengine.behaviortree;

import java.util.List;
import java.util.Queue;

import fxengine.PathFinding.Node;
import fxengine.math.Vec2d;
import fxengine.math.Vec2i;

public class BlackBoardData {

	public int intValue= 0;
	public double doubleValue= 0;
	public String stringValue= "";
	public List<Vec2d> arrayVec2dValue = null;
	public List<Vec2i> arrayVec2iValue = null;
	public Queue<Vec2d> queueVec2dValue = null;
	public Queue<Vec2i> queueVec2iValue = null;
	public Queue<Node> queueNodeValue = null;
}
