package fxengine.behaviortree;

import java.util.HashMap;
import java.util.Map;

public class BlackBoard {
	
	
	// String : name of the variable
	// BlackBoardData: type and value of data
    public Map<String,BlackBoardData> datum = new HashMap<>();
	
	private BlackBoard()
	{
	   	
	};
	
	private static BlackBoard instance = null;
	
	
	public static BlackBoard getInstance()
	{
		if(instance == null)
		{
			instance = new BlackBoard();
			
		}
		return instance;
	}
	
	
}
