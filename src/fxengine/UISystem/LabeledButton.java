package fxengine.UISystem;



import fxengine.application.FontMetrics;
import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/**
 * UI Button with a label. The label is a text located in the center of the button
 * label:  the text of the button
 * X: X coordinate of the button
 * Y: Y coordinate of the button
 * @author cdiaz8
 *
 */

public class LabeledButton extends UIButton{

	
	
	protected Label myLabel;
	protected String myText;
	


	public LabeledButton(double x, double y, String label)
	{
		
		this.myPosition = new Vec2d(x,y);
		
		this.myText = label;
		//FontMetrics fontMetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(Font.getDefault());
		
		//this.myChildren.add(myLabel);
	}
	
	public LabeledButton(double x, double y, String label, Color borderColor)
	{
		
		this.myPosition = new Vec2d(x,y);
		
		this.myText = label;
		myBorder = borderColor;

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
		if(myLabel != null )
		{
			myLabel.onDraw(graphicsCx);	
		}
		
		
	}

	@Override
	public void onResize(Vec2d newSize) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void init() {
		
		super.init();
		
		// TODO Auto-generated method stub
		if(myLabel == null)
		{
			Font font = Font.font ("Verdana", 12);
			FontMetrics metrics = new FontMetrics(this.myText, font);
			double width = metrics.width;
			double height = metrics.height;
			this.mySize = new Vec2d(width,height);
			myLabel = new Label(this.myText, this.myPosition.x + 2.2, this.myPosition.y + this.mySize.y ,UIConstants.BLUE);
		}
	}

	@Override
	public void onMouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("onMouseClicked " + this.myText);
	}
	
	
	public String getMyText() {
		return myText;
	}

	public void setText(String myText) {
		this.myText = myText;
		this.myLabel = null;
		this.init();
	}

}
