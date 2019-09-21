package fxengine.scene;

import java.util.ArrayList;
import java.util.List;

import fxengine.UISystem.UIElement;
import fxengine.application.FXFrontEnd;
import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

/**
 * A class representing a Base Scene where the user will start setting up 
 * Elements from the UI Toolkit
 * 
 * @author cdiaz8
 */

public abstract class BaseScene {
	
	protected List<UIElement> props = new ArrayList<UIElement>();
	protected FXFrontEnd myApplication;
	protected boolean myIsActive;
	protected Vec2d myWindowSize;
	protected String mySceneName;
	protected boolean myIsInitialized = false;

    public BaseScene(String name, FXFrontEnd application)
	{
		this.mySceneName = name;
		this.myApplication = application;
		this.myIsActive = false;
	}
	
	
	/**
	 * Called at a regular interval set by {@link #setTickFrequency(long)}. Use to update any state
	 * that changes over time.
	 * 
	 * @param nanosSincePreviousTick	approximate number of nanoseconds since the previous call
	 *                              	to onTick
	 */
	public void onTick(long nanosSincePreviousTick)
	{
		for(UIElement element: this.props)
		{
			element.onTick(nanosSincePreviousTick);
		}
	}
	
	/**
	 * Called when the screen needs to be redrawn. This is at least once per tick, but possibly
	 * more frequently (for example, when the window is resizing).
	 * <br><br>
	 * Note that the entire drawing area is cleared before each call to this method. Furthermore,
	 * {@link #onResize} is guaranteed to be called before the first invocation of onDraw.
	 * 
	 * @param g		a {@link GraphicsContext} object used for drawing.
	 */
	public void onDraw(GraphicsContext g)
	{
		for(UIElement element: this.props)
		{
			element.onDraw(g);
		}
	}
	
	/**
	 * Called when the size of the drawing area changes. Any subsequent calls to onDraw should note
	 * the new size and be sure to fill the entire area appropriately. Guaranteed to be called
	 * before the first call to onDraw.
	 * 
	 * @param newSize	the new size of the drawing area.
	 */
	public void onResize(Vec2d newScreenSize)
	{
		double oldratio = this.myWindowSize.x /this.myWindowSize.y; 
		double newratio = newScreenSize.x /newScreenSize.y;
		//System.out.println("oldratio: "+ oldratio+ " , newratio:"+ newratio + ", oldratio/newratio: "+oldratio/newratio);
		this.myWindowSize = newScreenSize;
	    for(UIElement element: this.props)
		{
			element.onResize(newScreenSize,oldratio/newratio);
		}
	    
	    
	    
	}
	
	/**
	 * KeyBoard event listener
	 * @param event
	 */
	public void onKeyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
	
	}
	
	/**
	 * Initialize all the elements attached to the scene
	 * this function is call after the onStartUp event, and in the tick event if some of its elements
	 *  have not been initalized
	 */
	public void initScene() {
		// TODO Auto-generated method stub

		for(UIElement element: this.props)
		{
			element.init();
		}
		this.myIsInitialized = true;
	}
	
	/**
	 * Deletes all the elements inside the scene
	 */
	public void cleanScene() {
		// TODO Auto-generated method stub
		this.props.clear();
		this.myIsInitialized = false;
	}
	
	/**
	 * Sets the window size on the current scene
	 */
	public void setCurrentWindowSize(Vec2d winSize)
	{
		this.myWindowSize =  winSize;
		System.out.println("Scene size "+this.myWindowSize.x +" "+this.myWindowSize.y);
	}
	
	/**
	 * @param e		an FX {@link MouseEvent} representing the input event.
	 */
	public void onMouseClicked(MouseEvent e)
	{
		for(UIElement element: this.props)
		{
			Vec2d clickPos = new Vec2d(e.getX(), e.getY());
			Vec2d elemetSize = element.getSize();
			Vec2d elemetPosition = element.getPosition();
			
			if((clickPos.x > elemetPosition.x && clickPos.x < elemetPosition.x + elemetSize.x)
			    &&( clickPos.y > elemetPosition.y && clickPos.y < elemetPosition.y + elemetSize.y) )
			{
				element.onMouseClicked(e);
			}
		}
	}
	
	public void onMouseMoved(MouseEvent e) {
		for(UIElement element: this.props)
		{
			Vec2d clickPos = new Vec2d(e.getX(), e.getY());
			Vec2d elemetSize = element.getSize();
			Vec2d elemetPosition = element.getPosition();
			
			boolean hovered = false;
			element.setIsHovered(hovered);
			if((clickPos.x > elemetPosition.x && clickPos.x < elemetPosition.x + elemetSize.x)
			    &&( clickPos.y > elemetPosition.y && clickPos.y < elemetPosition.y + elemetSize.y) )
			{
				element.setIsHovered(!hovered);
				element.onMouseMoved(e);
			}
			
		
		}
	}
	
	public void onMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void onMouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void onMouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void onMouseWheelMoved(ScrollEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void onKeyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void onKeyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public String getSceneName() {
		return mySceneName;
	}


	public void setSceneName(String myName) {
		this.mySceneName = myName;
	}

	public boolean isActive() {
		return myIsActive;
	}

	public void setActive(boolean isActive) {
		this.myIsActive = isActive;
	}
	
	public boolean isInitialized() {
		return myIsInitialized;
	}


	public void setInitialized(boolean myIsInitialized) {
		this.myIsInitialized = myIsInitialized;
	}
	
}
