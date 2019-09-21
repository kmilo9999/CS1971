package fxengine.UISystem;

import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/**
 * 
 * A UI Label. It can be used to draw text on the scene
 * text:  the label's text
 * X: X coordinate of the label
 * Y: Y coordinate of the label
 * Color: font color of the text
 * Font: font of the text
 * @author cdiaz8
 *
 */
public class Label extends UIElement{

	private String myText;
	private Font myFont = Font.font ("Verdana", 12);
	

	public Label(String text, double x, double y)
	{
		this.myText = text;
		this.myPosition = new Vec2d(x,y);
	}
	
	public Label(String text, double x, double y, Color color)
	{
		this.myText = text;
		this.myPosition = new Vec2d(x,y);
		this.myColor = color;
	}
	
	public Label(String text, double x, double y, Color color, Font font)
	{
		this.myText = text;
		this.myPosition = new Vec2d(x,y);
		myColor = color;
		myFont = font;
	}
	
	@Override
	public void onTick(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDraw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub
		
		graphicsCx.setFill(myColor);
		graphicsCx.setFont(myFont);
		graphicsCx.fillText(myText,this.myPosition.x,this.myPosition.y);
		
		super.onDraw(graphicsCx);
	}

	@Override
	public void onResize(Vec2d newSize) {
		// TODO Auto-generated method stub
		
	}

	public Font getMyFont() {
		return myFont;
	}

	public void setMyFont(Font myFont) {
		this.myFont = myFont;
	}
	
	public String getText() {
		return myText;
	}

	public void setText(String myText) {
		this.myText = myText;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	public void onMouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
