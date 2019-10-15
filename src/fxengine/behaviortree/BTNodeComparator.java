package fxengine.behaviortree;

import java.util.Comparator;

public class BTNodeComparator implements Comparator<BTNode>{

	@Override
	public int compare(BTNode btn1, BTNode btn2) {
		// TODO Auto-generated method stub
		return btn1.priority- btn2.priority;
	}

}
