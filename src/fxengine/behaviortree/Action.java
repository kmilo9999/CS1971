package fxengine.behaviortree;



public abstract class Action extends BTNode{

	@Override
	Status update(float seconds) {return Status.FAIL;};
	
	@Override
	void reset() {};
}
