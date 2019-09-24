package fxengine.UISystem;

import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * UI Layout area containing other UIElements
 * X: X coordinate of the button
 * Y: Y coordinate of the button 
 * Color background : the fill color of the panel
 * @author cdiaz8
 *
 */
public class Layout extends UIElement{

	private double myBorderWidth = 0;
	public final int RightClick = 0;
	public final int LeftClick = 1;
	public final int MiddleClick = 2;
	
	protected Vec2d myMouseLastPosition = new Vec2d(0);
	protected Vec2d myMousePosition = new Vec2d(0);

	protected boolean[] mouseButtons = new boolean[3];
	
	public Layout(double x, double y, double w, double h) {
		// TODO Auto-generated constructor stub
		initPositions( x,  y,  w,  h);
		myColor = UIConstants.GREY;
	}
	
	public Layout(double x, double y, double w, double h, Color background) {
		// TODO Auto-generated constructor stub
		initPositions( x,  y,  w,  h);
		myColor = background;
	}
	
	public Layout(double x, double y, double w, double h, Color background, double borderWidth) {
		// TODO Auto-generated constructor stub
		initPositions( x,  y,  w,  h);
		myColor = background;
		myBorderWidth = borderWidth;
	}
	
	@Override
	public void onTick(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		super.onTick(nanosSincePreviousTick);
	}

	@Override
	public void onDraw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub
		if(myBorderWidth != 0)
		{
			graphicsCx.setLineWidth(myBorderWidth);
			graphicsCx.setStroke(UIConstants.BLACK);
			graphicsCx.strokeRect(this.myPosition.x ,this.myPosition.y,this.mySize.x + 5.5,this.mySize.y + 5.5);	
		}		
		graphicsCx.setFill(myColor);
		graphicsCx.fillRect(this.myPosition.x ,this.myPosition.y,this.mySize.x ,this.mySize.y );
		
		super.onDraw(graphicsCx);
	}


	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
	}

	private void initPositions(double x, double y, double w, double h)
	{
	 	myPosition = new Vec2d(x,y);
	   	mySize = new Vec2d(w,h);
		//
		
	}

	@Override
	public void onMouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("Layout CLICKED!");
		super.onMouseClicked(e);
	}
	
	@Override
	public void onMousePressed(MouseEvent e)
	{
		//System.out.println("Layout onMousePressed");
		int mouseButton = mapMouseButtonToInt(e.getButton());
		if(!mouseButtons[mouseButton])
		{
			mouseButtons[mouseButton] = true;	
		}
		super.onMousePressed(e);
	}
	
	@Override
	public void onMouseReleased(MouseEvent e) {
		//System.out.println("Layout onMouseReleased");
		int mouseButton = mapMouseButtonToInt(e.getButton());
		mouseButtons[mouseButton] = false;
		super.onMouseReleased(e);
	}
	
	@Override
	public void onMouseDragged(MouseEvent e) {

		int mouseButton = mapMouseButtonToInt(e.getButton());
		if (mouseButtons[0] && mouseButton == 0) {
			// is dragging this entity

			Vec2d currentMousePos = new Vec2d(e.getX(), e.getY());

			Vec2d delta = currentMousePos.minus(myMouseLastPosition);
			this.getPosition().plus(delta);
			
			myMouseLastPosition = currentMousePos;

		}
	}

	
	private int mapMouseButtonToInt(MouseButton mb)
	{		
		if( mb == MouseButton.PRIMARY)
		{
			return 0;
		}
		else if(mb== MouseButton.SECONDARY)
		{
			return 1;
		}
		else if(mb == MouseButton.MIDDLE)
		{
			return 1;
		}
		
		return -1;
	}

}
