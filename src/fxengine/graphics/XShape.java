package fxengine.graphics;

import fxengine.UISystem.UIElement;
import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * A Shape in the form of an X. It is composed of two UIElement lines shapes
 *  centerX : X Coordinates of the center
 *  centerY : Y Coordinates of the center
 *  radius  : radius of the circle
 * @author cdiaz8
 *
 */

public class XShape extends Shape{

	protected Shape myLine1;
	protected Shape myLine2;
	
	public XShape(double x, double y, double size)
	{
		myPosition = new Vec2d(x,y);
		mySize = new Vec2d(size,size);
		myLine1 = new Line(myPosition.x, myPosition.y, myPosition.x + mySize.x, myPosition.y +mySize.x,myColor);
	    myLine2 = new Line(myPosition.x + mySize.x , myPosition.y, myPosition.x , myPosition.y +mySize.x,myColor);
		
	}
	
	public XShape(double x, double y, double size, Color color)
	{
		myPosition = new Vec2d(x,y);
		mySize = new Vec2d(size,size);
		myColor = color;
	}


	@Override
	public void onDraw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub
		myLine1.setColor(myColor);
		myLine2.setColor(myColor);
		myLine1.onDraw(graphicsCx);
		myLine2.onDraw(graphicsCx);
	}

	
	@Override
	public void onResize(Vec2d newSize) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		
	}
	
	
}
