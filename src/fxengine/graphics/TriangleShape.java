package fxengine.graphics;

import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TriangleShape extends Shape{

	protected Vec2d p1;
	protected Vec2d p2;
	protected Vec2d p3;
	
	public TriangleShape(double x1, double y1,double x2,double y2,double x3,double y3)
	{
		this.p1 = new Vec2d(x1,y1);
		this.p2 = new Vec2d(x2,y2);
		this.p3 = new Vec2d(x3,y3);
		
	}
	
	public TriangleShape(double x1, double y1,double x2,double y2,double x3,double y3, Color borderColor)
	{
		this.p1 = new Vec2d(x1,y1);
		this.p2 = new Vec2d(x2,y2);
		this.p3 = new Vec2d(x3,y3);
		myColor =  borderColor;
	}
	
	@Override
	public void onDraw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub
		
		graphicsCx.setStroke(myColor);
		double[] xPoints = {this.p1.x,this.p2.x,this.p3.x};
		double[] yPoints = {this.p1.y,this.p2.y,this.p3.y};
		
		graphicsCx.strokePolygon(xPoints,yPoints,3);
		
	}
}
