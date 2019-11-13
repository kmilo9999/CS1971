package fxengine.graphics;

import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * 
 * UI line element to draw a stroke line from P1 to P1
 * X1: X coordinate of the point P1
 * Y1: Y coordinate of the point P1
 * X2: X coordinate of the point P2
 * Y2: Y coordinate of the point P2
 * Color color: color of the line
 * @author cdiaz8
 *
 */
public class Line extends Shape{

	private Vec2d myStartPos;
	private Vec2d myEndPos;
	private double myLineWidth = 3.0;
	
	public Line(double x1, double y1, double x2, double y2 )
	{
		initPositions( x1,  y1,  x2,  y2);
	}
	
	
	public Line(double x1, double y1, double x2, double y2 , Color color)
	{
		initPositions( x1,  y1,  x2,  y2);
		
		myColor = color;
	}
	
	
	public Line(double x1, double y1, double x2, double y2 , Color color, double lineWidth)
	{
		initPositions( x1,  y1,  x2,  y2);
		
		myColor = color;
		this.myLineWidth = lineWidth;
	}


	@Override
	public void onDraw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub
		graphicsCx.setLineWidth(myLineWidth);
		graphicsCx.setStroke(myColor);
		graphicsCx.strokeLine(this.myStartPos.x,this.myStartPos.y,this.myEndPos.x,this.myEndPos.y);
		
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
	
	private void initPositions(double x1, double y1, double x2, double y2)
	{
		this.myStartPos = new Vec2d(x1,y1);
		this.myEndPos = new Vec2d(x2,y2);
		//center of the line
		myPosition = myEndPos.minus(myStartPos).sdiv(2);
	}


	public Vec2d geStartPos() {
		return myStartPos;
	}


	public void setStartPos(Vec2d startPos) {
		this.myStartPos = startPos;
	}


	public Vec2d getEndPos() {
		return myEndPos;
	}


	public void setEndPos(Vec2d endPos) {
		this.myEndPos = endPos;
	}


}
