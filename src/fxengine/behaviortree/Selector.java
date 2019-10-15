package fxengine.behaviortree;

import java.util.ArrayList;

public class Selector extends Composite {

	
	public Selector()
	{
		
	}
	
	@Override
	public Status update(long seconds) {
		// TODO Auto-generated method stub
		children.sort(new BTNodeComparator());
		
		for (int i = 0; i < children.size(); i++) 
		{
		  if(children.get(i).update(seconds) == Status.RUNNING)
		  {
			  return Status.RUNNING;
		  }
		  else if(children.get(i).update(seconds) == Status.SUCCED)
		  {
			  return Status.RUNNING;
		  }
		  else
		  {
			  children.get(i).reset();
		  }
		}
		
		return Status.FAIL;
	}

	@Override
	public int compareTo(BTNode o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
}
