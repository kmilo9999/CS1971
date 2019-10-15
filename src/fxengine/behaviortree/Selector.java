package fxengine.behaviortree;

import java.util.ArrayList;

public class Selector extends Composite {

	
	public Selector()
	{
		children = new ArrayList<BTNode>();
	}
	
	@Override
	public Status update(float seconds) {
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
	
}
