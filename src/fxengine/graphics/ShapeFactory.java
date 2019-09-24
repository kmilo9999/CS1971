package fxengine.graphics;


import fxengine.UISystem.UIConstants;

public class ShapeFactory {
	
	private ShapeFactory()
	{
	   	
	};
	
	private static ShapeFactory instance = null;
	
	
	public static ShapeFactory getInstance()
	{
		if(instance == null)
		{
			instance = new ShapeFactory();
			
		}
		return instance;
	}
	
	
	public Shape createShape(String shapeName)
	{
		if(shapeName.equals(ShapeConstants.XShape))
		{
			return new XShape(1, 1, 20);
			
		}else if(shapeName.equals(ShapeConstants.Circle))
		{
			return new CircleShape(1, 1,22);
		}
		else if(shapeName.equals(ShapeConstants.Fill_Circle))
		{
			return new FillCircleShape(1, 1,22, UIConstants.FIREBRICK);
		}
		else if(shapeName.equals(ShapeConstants.Line))
		{
			return new Line(1, 1,2,2);
		}
		else if(shapeName.equals(ShapeConstants.Triangle))
		{
			return new TriangleShape(1, 1,5,5,1,5);
		}
		else if(shapeName.equals(ShapeConstants.Fill_Triangle))
		{
			return new FillTriangle(1, 1,5,5,1,5,UIConstants.FIREBRICK);
		}
		
		
		return null;
	}
}
