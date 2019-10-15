package fxengine.behaviortree;


public abstract class BTNode implements Comparable<BTNode>{
	
	public int priority;
	public Status currenStatus; 
	
	public static enum Status
	{
	   FAIL,SUCCED,RUNNING;
	}
	
	@Override
	public int compareTo(BTNode o) {
		// TODO Auto-generated method stub
		 return this.priority- o.priority;
	} 
	
	Status update(float seconds) {return Status.SUCCED;};
	void reset() {};
}
