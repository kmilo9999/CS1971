package game.wiz;

import fxengine.behaviortree.BTNode;
import fxengine.behaviortree.Condition;

public class TimeDelay extends Condition{

	private int currentSeconds = 0;
	private long currentTime = 0;
	private int delayTimeInSeconds = 3;
	
	public TimeDelay(int delayTimeInSeconds) {
		// TODO Auto-generated constructor stub
		this.delayTimeInSeconds = delayTimeInSeconds;
	}
	
	@Override
	public int compareTo(BTNode o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Status update(long nanosecondsSinceLastTick) {
		// TODO Auto-generated method stub
		currentTime += nanosecondsSinceLastTick;
		if(currentTime > 1000000000)
		{
			currentSeconds++;
			currentTime = 0;
		}
		
		if(currentSeconds > delayTimeInSeconds)
		{
			currentStatus = Status.SUCCED;
		}
		else
		{
			currentStatus = Status.RUNNING;
		}
	
		return currentStatus;
	
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		currentTime = 0;
		currentSeconds = 0;
		currentStatus = Status.FAIL;
	}

}
