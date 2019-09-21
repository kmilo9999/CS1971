package fxengine.graphics;

import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * A Shape in the form of a circle
 *  centerX : X Coordinates of the center
 *  centerY : Y Coordinates of the center
 *  radius  : radius of the circle
 * @author cdiaz8
 *
 */
public class CircleShape extends Shape{

	protected double myLineWidth = 3.0;
	
	public CircleShape( double centerX, double centerY, double radius)
	{
		myPosition = new Vec2d(centerX,centerY );
		mySize = new Vec2d(radius,radius);
	}
	
	public CircleShape(double centerX, double centerY, double radius, Color color)
	{
		myPosition = new Vec2d(centerX,centerY );
		mySize = new Vec2d(radius,radius);
		myColor = color;
	}
	
	public CircleShape(double centerX, double centerY, double radius, Color color, double lineWidth)
	{
		myPosition = new Vec2d(centerX,centerY );
		mySize = new Vec2d(radius,radius);
		myColor = color;
		myLineWidth = lineWidth;
	}


	@Override
	public void onDraw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub
		
		graphicsCx.setLineWidth(myLineWidth);
		graphicsCx.setStroke(myColor);
		graphicsCx.strokeOval(this.myPosition.x,this.myPosition.y,this.mySize.x,this.mySize.y);
		
		
	}

	
	@Override
	public void onResize(Vec2d newSize) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
	}


}