package fxengine.components;

import fxengine.UISystem.UIConstants;
import fxengine.graphics.ShapeConstants;
import fxengine.math.Vec2d;
import fxengine.objects.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class GraphicsComponent extends Component{

	protected Sprite mySprite; 
	
	private final int INSIDE = 0; // 0000 
	private final int LEFT = 1;   // 0001 
	private final int RIGHT = 2;  // 0010 
	private final int BOTTOM = 4; // 0100 
	private final int TOP = 8;    // 1000 
	
	private double myXMax = 0;
	private double myYMax = 0;
	private double myXMin = 0;
	private double myYMin = 0;
	
	private Vec2d myPanelScreenViewPortUpperLeft;
	private Vec2d myPanelScreenViewPortSize;
	
	public GraphicsComponent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		
		
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
       mySprite = new Sprite(ShapeConstants.Circle);
		
		myPanelScreenViewPortUpperLeft = this.myParent.getGameWorld().getPanelScreenViewPortUpperLeft();
		myPanelScreenViewPortSize = this.myParent.getGameWorld().getPanelScreenViewPortSize();
		
		myXMax = myPanelScreenViewPortUpperLeft.x + myPanelScreenViewPortSize.x;
        myYMax = myPanelScreenViewPortUpperLeft.y + myPanelScreenViewPortSize.y;
		myXMin = myPanelScreenViewPortUpperLeft.x;
		myYMin = myPanelScreenViewPortUpperLeft.y;
	}
	
	@Override
	public void draw(GraphicsContext graphicsCx)
	{
        if(mySprite != null)
        {
        	if(this.myParent.isSelected())
        	{
        		mySprite.setColor(UIConstants.SELECTED);
        	}
        	else
        	{
        		mySprite.setColor(UIConstants.TRANSPARENT);
        	}
        	this.clip();
        	mySprite.onDraw(graphicsCx);	
        }
		
	}
	
	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		if(this.myParent.hasComponent(ComponetContants.transform))
		{
			TransformComponent transformComponent = (TransformComponent)this.myParent.getComponent(ComponetContants.transform);
			if(transformComponent != null)
			{
				mySprite.setPosition(transformComponent.getPosition());	
			}
			 
		}	
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public Sprite getSprite() {
		return mySprite;
	}

	public void setSprite(Sprite mySprite) {
		this.mySprite = mySprite;
	}

	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		Component clone = ComponentFactory.getInstance().createComponent(this.myName);
		Sprite sprite = this.mySprite.clone();
		((GraphicsComponent)clone).mySprite = sprite;
		clone.myParent =this.myParent;
		return clone;
	}

	public void clip() {
		// TODO Auto-generated method stub
		if(this.myParent.hasComponent(ComponetContants.transform))
		{
			
			TransformComponent transformComponent = (TransformComponent)this.myParent.getComponent(ComponetContants.transform);
			if(transformComponent != null)
			{
				Vec2d p1 = transformComponent.getPosition();
				Vec2d p2x = new Vec2d(transformComponent.getPosition().x+ mySprite.getSize().x,p1.y);
				Vec2d p2y = new Vec2d(p1.x,transformComponent.getPosition().y+ mySprite.getSize().y);
				
				
				/*if(p1.dist(p2x) < mySprite.getWidth()) {
					p2x = new Vec2d(p1.x,mySprite.getWidth());
				}
				
				if(p1.dist(p2y) < mySprite.getHeight())
				{
					p2x = new Vec2d(p1.y,mySprite.getHeight());
				}*/
				
				
				
				
				Vec2d xclipCoords[] = cohenSutherlandClip(p1,p2x);
					
					
				Vec2d yclipCoords[] = cohenSutherlandClip(p1,p2y);
					
				mySprite.setSize(new Vec2d(p1.dist(xclipCoords[1]),p1.dist(yclipCoords[1])));
				
					
				
				
				
				
			}
			 
		}	
		
	}

	private int computeCode(Vec2d point) 
	{ 
	    // initialized as being inside  
	    int code = INSIDE; 
	  
	    if (point.x < myPanelScreenViewPortUpperLeft.x)       // to the left of rectangle 
	        code |= LEFT; 
	    else if (point.x > myPanelScreenViewPortUpperLeft.x + myPanelScreenViewPortSize.x)  // to the right of rectangle 
	        code |= RIGHT; 
	    if (point.y < myPanelScreenViewPortUpperLeft.y)       // below the rectangle 
	        code |= BOTTOM; 
	    else if (point.y > myPanelScreenViewPortUpperLeft.y + myPanelScreenViewPortSize.y)  // above the rectangle 
	        code |= TOP; 
	  
	    return code; 
	} 
	
	
	private Vec2d[] cohenSutherlandClip(Vec2d p1, Vec2d p2) 
   {
		
		Vec2d[] acceptedValues =  new Vec2d[2];
		
		// Compute region codes for P1, P2 
	    int code1 = computeCode(p1); 
	    int code2 = computeCode(p2); 
	  
	    // Initialize line as outside the rectangular window 
	    
	  
	    while (true) 
	    { 
	        if ((code1 == 0) && (code2 == 0)) 
	        { 
	            // If both endpoints lie within rectangle 
	        	acceptedValues[0] =  p1;
	        	acceptedValues[1] =  p2;
	            break; 
	        } 
	        else if ((code1 & code2) != 0) 
	        { 
	            // If both endpoints are outside rectangle, 
	            // in same region
	            break; 
	        } 
	        else
	        { 
	            // Some segment of line lies within the 
	            // rectangle 
	            int code_out; 
	            double x = 0, y = 0; 
	  
	            // At least one endpoint is outside the  
	            // rectangle, pick it. 
	            if (code1 != 0) 
	                code_out = code1; 
	            else
	                code_out = code2; 
	  
	            // Find intersection point; 
	            // using formulas y = y1 + slope * (x - x1), 
	            // x = x1 + (1 / slope) * (y - y1) 
	            if ((code_out & TOP) != 0 ) 
	            { 
	                // point is above the clip rectangle 
	                x = p1.x + (p2.x - p1.x) * (myYMax - p1.y) / (p2.y - p1.y); 
	                y = myYMax; 
	            } 
	            else if ((code_out & BOTTOM) != 0) 
	            { 
	                // point is below the rectangle 
	                x = p1.x + (p2.x - p1.x) * (myYMin - p1.y) / (p2.y - p1.y); 
	                y = myYMin; 
	            } 
	            else if ((code_out & RIGHT) != 0) 
	            { 
	                // point is to the right of rectangle 
	                y = p1.y + (p2.y - p1.y) * (myXMax - p1.x) / (p2.x - p1.x); 
	                x = myXMax; 
	            } 
	            else if ((code_out & LEFT) != 0) 
	            { 
	                // point is to the left of rectangle 
	                y = p1.y + (p2.y - p1.y) * (myXMin - p1.x) / (p2.x - p1.x); 
	                x = myXMin; 
	            } 
	  
	            // Now intersection point x,y is found 
	            // We replace point outside rectangle 
	            // by intersection point 
	            if (code_out == code1) 
	            { 
	            	p1 = new Vec2d(x,y); 
	            	 
	                code1 = computeCode(p1); 
	            } 
	            else
	            { 
	            	p2 = new Vec2d(x,y); 
	                code2 = computeCode(p2); 
	            } 
	        } 
	    } 
	   
	    return acceptedValues;
	         
   }
}
