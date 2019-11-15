package fxengine.collision;

import java.util.ArrayList;

import fxengine.math.Vec2d;

public class CollisionShapeFactory {
	private CollisionShapeFactory()
	{
	   	
	};
	
	private static CollisionShapeFactory instance = null;
	
	
	public static CollisionShapeFactory getInstance()
	{
		if(instance == null)
		{
			instance = new CollisionShapeFactory();
			
		}
		return instance;
	}
	
	
	public CollisionShape createShape(String shapeName)
	{
		if(shapeName.equals(CollisionConstants.AABShape))
		{
			return new AABCollideShape(new Vec2d(0,0),new Vec2d(0,0));
			
		}else if(shapeName.equals(CollisionConstants.CIRCLEShape))
		{
			return new CircleCollisionShape(new Vec2d(0,0), 0);
		}
		else if(shapeName.equals(CollisionConstants.POLYGONShape))
		{
			return new PolygonColliderShape(new ArrayList<Vec2d>());
		}
		return null;
	}
}
