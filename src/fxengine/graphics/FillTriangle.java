package fxengine.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FillTriangle extends TriangleShape{
  
	protected Color myFillColor;
	
    public FillTriangle(double x1, double y1,double x2,double y2,double x3,double y3, Color fillColor)
	{
		super(x1,  y1, x2, y2, x3, y3);
		this.myFillColor = fillColor;
	}
    
    @Override
	public void onDraw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub
		
    	graphicsCx.setFill(this.myFillColor);
		double[] xPoints = {this.p1.x,this.p2.x,this.p3.x};
		double[] yPoints = {this.p1.y,this.p2.y,this.p3.y};
		
		graphicsCx.strokePolygon(xPoints,yPoints,3);
		
	}
	
	public Color getFillColor() {
		return myFillColor;
	}

	public void setFillColor(Color myFillColor) {
		this.myFillColor = myFillColor;
	}
	
	
}
