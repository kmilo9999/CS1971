package fxengine.behaviortree;



public abstract class Condition extends BTNode{
	@Override
	Status update(float seconds) {return Status.FAIL;};
	
	@Override
	void reset() {};
}
