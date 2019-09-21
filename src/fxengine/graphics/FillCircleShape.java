package fxengine.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FillCircleShape extends CircleShape{

	protected Color myFillColor;
	
	public FillCircleShape(double centerX, double centerY, double radius, Color fillColor) {
		super(centerX, centerY, radius);
		myFillColor = fillColor;
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void onDraw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub
		graphicsCx.setFill(this.myFillColor);
		graphicsCx.fillOval(this.myPosition.x,this.myPosition.y,this.mySize.x,this.mySize.y);
		
	}
	
	public Color getFillColor() {
		return myFillColor;
	}

	public void setFillColor(Color myFillColor) {
		this.myFillColor = myFillColor;
	}
	

}
