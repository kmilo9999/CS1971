package fxengine.behaviortree;


public abstract class BTNode implements Comparable<BTNode>{
	
	protected int priority;
	protected Status currentStatus;
	
	public static enum Status
	{
	   FAIL,SUCCED,RUNNING;
	}
	
	
	
	public abstract Status update(long seconds);
	public abstract void reset() ;
}
