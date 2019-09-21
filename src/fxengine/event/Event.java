package fxengine.event;

import fxengine.math.Vec2d;

public class Event {
    
	public Vec2d vec2dValue;
	public int intValue;
	public double doubleValue;
	public String strValue;
	public String type;
	
	
	public Event(String id, Vec2d value)
    {
		this.type = id;
		this.vec2dValue = value;
    }
	
	public Event(String id, int value)
    {
		this.type = id;
		this.intValue = value;
    }
	
	public Event(String id, double value)
    {
		this.type = id;
		this.doubleValue = value;
    }
	
	public Event(String id, Vec2d vecValue, int intValue)
    {
		this.type = id;
		this.vec2dValue = vecValue;
		this.intValue = intValue;
    }
	
	public Event(String id, String strValue)
    {
		this.type = id;
		this.strValue = strValue;
    }
	
}
