package fxengine.UISystem;

import fxengine.graphics.CircleShape;
import fxengine.graphics.Shape;
import fxengine.graphics.XShape;
import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * UI button with a shape at the center (X , or Circle)
 * X: X coordinate of the button
 * Y: Y coordinate of the button
 * String shape: shape to draw at the center
 * @author cdiaz8
 *
 */
public class PaintedButton extends UIButton{

	
	private Shape myShape;
	private String myShapeStr;
	
	public PaintedButton(double x, double y, String shape)
	{
		myPosition = new Vec2d(x,y);
		this.myShapeStr = shape;
	}
	
	public PaintedButton(double x, double y, String shape, Color borderColor)
	{
		myPosition = new Vec2d(x,y);
		this.myShapeStr = shape;
		myBorder =  borderColor;
	}
	
	
	@Override
	public void onTick(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		super.onTick(nanosSincePreviousTick);
		
	}

	@Override
	public void onDraw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub
		super.onDraw(graphicsCx);	
	
		if(this.myShape != null)
		{
			this.myShape.onDraw(graphicsCx);	
		}
		
	
	}
	
	@Override
	public void onResize(Vec2d newSize) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
		setShape();
		this.myShape.init();
		mySize = this.myShape.getSize();
		
	}
	
	protected void setShape()
	{
		switch(this.myShapeStr)
		{
		case UIConstants.SHAPE_X:
			this.myShape = new XShape(this.myPosition.x + 2.2, this.myPosition.y, 20.0);
			break;
		case UIConstants.SHAPE_CIRCLE:
			this.myShape = new CircleShape(this.myPosition.x + 2.2, this.myPosition.y, 22.0);
			break;
		default:
			this.myShape = null;
			break;
		}
	}

	@Override
	public void onMouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void setShapeColor(Color color)
	{
		if(this.myShape != null)
		{
			this.myShape.setColor(color); 	
		}
		
	}

}
