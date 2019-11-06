package fxengine.components;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import fxengine.behaviortree.BehaviorTree;
import javafx.scene.canvas.GraphicsContext;

public class AIComponent extends Component{

	private BehaviorTree myBehaviorTree;
	
	public AIComponent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		if(this.myBehaviorTree != null)
		{
			myBehaviorTree.update(nanosSincePreviousTick);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub
		
	}

	public BehaviorTree getBehaviorTree() {
		return myBehaviorTree;
	}

	public void setBehaviorTree(BehaviorTree behaviorTree) {
		this.myBehaviorTree = behaviorTree;
	}

	@Override
	public Element saveState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadState(Node node) {
		// TODO Auto-generated method stub
		
	}

}
