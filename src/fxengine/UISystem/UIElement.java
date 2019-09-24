package fxengine.UISystem;

import java.util.ArrayList;
import java.util.List;

import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Base UIElemen. All the UI that want to appear on scene must inherit from this class
 * @author cdiaz8
 *
 */

public abstract class UIElement {
	/**
	 * Called at a regular interval set by {@link #setTickFrequency(long)}. Use to update any state
	 * that changes over time.
	 * 
	 * @param nanosSincePreviousTick	approximate number of nanoseconds since the previous call
	 *                              	to onTick
	 */
	public  void onTick(long nanosSincePreviousTick)
	{
		
		if(!this.isInitialized)
		{
			this.init();
			this.isInitialized = true;
		}
		
		for(UIElement child:this.myChildren)
		{
			child.onTick(nanosSincePreviousTick);
		}
	};
	
	/**
	 * Called when the screen needs to be redrawn. This is at least once per tick, but possibly
	 * more frequently (for example, when the window is resizing).
	 * <br><br>
	 * Note that the entire drawing area is cleared before each call to this method. Furthermore,
	 * {@link #onResize} is guaranteed to be called before the first invocation of onDraw.
	 * 
	 * @param g		a {@link GraphicsContext} object used for drawing.
	 */
	public  void onDraw(GraphicsContext g) {
	

		for(UIElement child:this.myChildren)
		{
			child.onDraw(g);
		}
	};
	
	/**
	 * Called when the size of the drawing area changes. Any subsequent calls to onDraw should note
	 * the new size and be sure to fill the entire area appropriately. Guaranteed to be called
	 * before the first call to onDraw.
	 * 
	 * @param newSize	the new size of the drawing area.
	 */
	public  void onResize(Vec2d containerSize)
    {
		double containerRatio = containerSize.x / containerSize.y;
		double myRatio = this.mySize.x / this.mySize.y;
		
		/* double scaleFactor = 0;
		 if (containerResolution > myResolution)
		 {
			 scaleFactor = containerSize.y / this.mySize.y;
		 }
		 else {
			 scaleFactor = containerSize.x / this.mySize.x;
		 }
		 	this.mySize = this.mySize.smult(scaleFactor);
		 */
		    
		double widthRatio = containerSize.x / this.mySize.x;
		double heightRatio =  containerSize.y / this.mySize.y;
		double ratio =  Math.min(widthRatio, heightRatio);	;
		Vec2d newScaled ;
		if(widthRatio > heightRatio)
		{
			 newScaled = new Vec2d((this.mySize.x  ),(this.mySize.y * ratio ));
			 //System.out.println("HERE1");
		}else
		{
			 newScaled = new Vec2d((this.mySize.x * ratio ),(this.mySize.y));
			 //System.out.println("HERE2");
		}
		

	
	    
		
		
	  
		//if(	containerSize.y > containerSize.x)
		/*if(	containerRatio > myRatio)
		{
			//double adjusted_height = newSize.x * (this.mySize.y / this.mySize.x);
			//double adjusted_width = newSize.y  * this.mySize.x / this.mySize.y;
			//this.mySize = new Vec2d(adjusted_width,adjusted_height);
			double xsize = this.mySize.x ;
			double ysize = this.mySize.y * containerSize.x /this.mySize.x;
			System.out.println("1--- " + xsize+" "+ysize);
			newScaled = new Vec2d(xsize,ysize );
			//double resultWidth = imagewidth * screenheight / imageheight 
		}
		else
		{
			double xsize = this.mySize.x * containerSize.y /this.mySize.y;
			double ysize = this.mySize.y;
			
			System.out.println("2 --- " + xsize+" "+ysize);
			newScaled = new Vec2d(xsize , ysize);
		}*/
		
		
		/*if(containerSize.x < containerSize.y)
		{
			newScaled = new Vec2d(this.mySize.x ,this.mySize.y * (containerSize.x/containerSize.y));
		}else
	    {
			newScaled = new Vec2d(this.mySize.x * (containerSize.x/containerSize.y),this.mySize.y);
	    }
		*/

		this.mySize = newScaled;
		
		for(UIElement child:this.myChildren)
		{
			child.onResize(this.mySize);
		}
		
    };
    
    public  void onResize(Vec2d containerSize, double newAspectRatio)
    {
    	//double widthRatio = containerSize.x / this.mySize.x;
		//double heightRatio =  containerSize.y / this.mySize.y;
		//double ratio =  Math.min(widthRatio, heightRatio);
    	
		Vec2d newScaled ;
		if(containerSize.x > containerSize.y)
		{
			 newScaled = new Vec2d((this.mySize.x * newAspectRatio ),(this.mySize.y * newAspectRatio ));
			 //System.out.println("HERE1");
		}else
		{
			 newScaled = new Vec2d((this.mySize.x * newAspectRatio ),(this.mySize.y * newAspectRatio));
			 //System.out.println("HERE2");
		}
		
		this.mySize = newScaled;
		for(UIElement child:this.myChildren)
		{
			child.onResize(this.mySize,newAspectRatio);
		}
		
		
		System.out.println("this.mySize" + this.mySize);
    }
	
    public  void addChildElement(UIElement element) {
		
    	element.myParent = this;
    	Vec2d position = element.getPosition();
		position = position.plus(myPosition);
		element.setPosition(position);
    	myChildren.add(element);
		
	};
	
	public  void addChildElements(List<UIElement> elements) {
		for(UIElement element:elements)
		{
			element.myParent = this;
	    	Vec2d position = element.getPosition();
			position = position.plus(myPosition);
			element.setPosition(position);
	    	myChildren.add(element);
		}
	};
	
	
	public UIElement getChild(int index)
	{
		if(!myChildren.isEmpty())
		{
		  return myChildren.get(index);
		}
		
		return null;
	}
	
	public void onMouseClicked(MouseEvent e)
	{
		for(UIElement element:this.myChildren)
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
	};
	
	public void onMouseMoved(MouseEvent e) {
		
		for(UIElement element: this.myChildren)
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
	
	public void onMousePressed(MouseEvent e)
	{
		for(UIElement element:this.myChildren)
		{
			Vec2d clickPos = new Vec2d(e.getX(), e.getY());
			Vec2d elemetSize = element.getSize();
			Vec2d elemetPosition = element.getPosition();
			
			if((clickPos.x > elemetPosition.x && clickPos.x < elemetPosition.x + elemetSize.x)
			    &&( clickPos.y > elemetPosition.y && clickPos.y < elemetPosition.y + elemetSize.y) )
			{
				element.onMousePressed(e);
			}
			
		}
	}
	
	public void onMouseReleased(MouseEvent e) {
		for(UIElement element:this.myChildren)
		{
			Vec2d clickPos = new Vec2d(e.getX(), e.getY());
			Vec2d elemetSize = element.getSize();
			Vec2d elemetPosition = element.getPosition();
			
			if((clickPos.x > elemetPosition.x && clickPos.x < elemetPosition.x + elemetSize.x)
			    &&( clickPos.y > elemetPosition.y && clickPos.y < elemetPosition.y + elemetSize.y) )
			{
				element.onMouseReleased(e);
			}
			
		}
	}
	
	public void onMouseDragged(MouseEvent e) {
		for(UIElement element:this.myChildren)
		{
			Vec2d clickPos = new Vec2d(e.getX(), e.getY());
			Vec2d elemetSize = element.getSize();
			Vec2d elemetPosition = element.getPosition();
			
			if((clickPos.x > elemetPosition.x && clickPos.x < elemetPosition.x + elemetSize.x)
			    &&( clickPos.y > elemetPosition.y && clickPos.y < elemetPosition.y + elemetSize.y) )
			{
				element.onMouseDragged(e);
			}
			
		}
	}
	
	
	public Vec2d getPosition() {
		return myPosition;
	};

	public void setPosition(Vec2d myPosition) {
		this.myPosition = myPosition;
	};

	public Vec2d getSize() {
		return mySize;
	};

	public void setSize(Vec2d mySize) {
		this.mySize = mySize;
	};

	
	public void init() {
		
		for(UIElement child:this.myChildren)
		{
			if(!child.isInitialized)
			{
				child.init();
				child.isInitialized = true;	
			}
			
		}
		
		this.isInitialized = true;
	};
	
	
    public void remove() {
		
    	for(UIElement child:this.myChildren)
		{
    	   child.remove();		
		}
    	
    	myChildren.clear();
		this.isInitialized = false;
	};
	
	
	public Color getColor() {
		return myColor;
	}

	public void setColor(Color color) {
		this.myColor = color;
	}
	
	protected List<UIElement> myChildren  = new ArrayList<UIElement>();
	
	protected UIElement myParent = null;
	
	protected Vec2d myPosition = new Vec2d(0);
	

	protected Vec2d mySize = new Vec2d(0);
	
	protected Color myColor = UIConstants.BLACK;



	protected boolean isInitialized = false;

	protected boolean myIsHoverd = false;

	public void setIsHovered(boolean ishHover) {
		// TODO Auto-generated method stub
		this.myIsHoverd = ishHover;
	}
	
	
	
	
}
