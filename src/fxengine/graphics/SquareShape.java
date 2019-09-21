package fxengine.graphics;

import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SquareShape extends Shape{

	public SquareShape(double x, double y , double w, double h)
	{
	   this.myPosition = new Vec2d(x,y);
	   this.mySize = new Vec2d(x,y);
	}
	
	public SquareShape(double x, double y , double w, double h, Color borderColor)
	{
		this.myPosition = new Vec2d(x,y);
		this.mySize = new Vec2d(x,y);
		
	}

	@Override
	public void onDraw(GraphicsContext g) {
		// TODO Auto-generated method stub
		
	}
}
