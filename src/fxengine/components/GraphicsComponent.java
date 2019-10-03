package fxengine.components;

import fxengine.UISystem.UIConstants;
import fxengine.graphics.ShapeConstants;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;

public class GraphicsComponent extends Component{

	
	
	
	protected SpriteComponent mySprite; 
	
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
	
	private boolean myInViewportX = false;
	private boolean myInViewportY = false;
	
	private int myClipCode = 0;
	
	public GraphicsComponent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		 //mySprite = new Sprite(ShapeConstants.Circle);
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
      
		if(!isInitialized)
		{
			myPanelScreenViewPortUpperLeft = this.myParent.getGameWorld().getPanelScreenViewPortUpperLeft();
			myPanelScreenViewPortSize = this.myParent.getGameWorld().getPanelScreenViewPortSize();
			
			myXMax = myPanelScreenViewPortUpperLeft.x + myPanelScreenViewPortSize.x;
	        myYMax = myPanelScreenViewPortUpperLeft.y + myPanelScreenViewPortSize.y;
			myXMin = myPanelScreenViewPortUpperLeft.x;
			myYMin = myPanelScreenViewPortUpperLeft.y;
			
			if(this.myParent.hasComponent(ComponentContants.sprite))
			{
				((SpriteComponent) this.myParent.getComponent(ComponentContants.sprite)).initialize();;
				
				this.mySprite = (SpriteComponent) this.myParent.getComponent(ComponentContants.sprite);
			}
			isInitialized= true;
		}
	
	}
	
	@Override
	public void draw(GraphicsContext graphicsCx)
	{
		TransformComponent transformComponent = (TransformComponent)this.myParent.getComponent(ComponentContants.transform);
		
        if(this.mySprite != null && transformComponent != null)
        {
        	if(this.myParent.isSelected())
        	{
        		this.mySprite.getLayout().setColor(UIConstants.SELECTED);
        	}
        	else
        	{
        		this.mySprite.getLayout().setColor(UIConstants.LIGTHGRAY);
        	}
        	
        	
        	//paints in screen space			
			//Vec2d screenPosition = this.myParent.getGameWorld().gameToScreenTransform(transformComponent.getPosition());
			//mySprite.setPosition(screenPosition);
        	//myPanelScreenViewPortUpperLeft = this.myParent.getGameWorld().getPanelScreenViewPortUpperLeft();
        	//myPanelScreenViewPortSize = this.myParent.getGameWorld().getPanelScreenViewPortSize();
    		
        	//myXMax = myPanelScreenViewPortUpperLeft.x + myPanelScreenViewPortSize.x;
        	//myYMax = myPanelScreenViewPortUpperLeft.y + myPanelScreenViewPortSize.y;
        	//myXMin = myPanelScreenViewPortUpperLeft.x;
        	//myYMin = myPanelScreenViewPortUpperLeft.y;
        	this.clip();
        	if(this.myInViewportX || this.myInViewportY)
        	{
        						
    			this.mySprite.draw(graphicsCx);
        	}

        		
        }
		
	}


	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		if(this.myParent.hasComponent(ComponentContants.transform))
		{
			TransformComponent transformComponent = (TransformComponent)this.myParent.getComponent(ComponentContants.transform);
			if(transformComponent != null)
			{
				//Vec2d screenPosition = this.myParent.getGameWorld().gameToScreenTransform(transformComponent.getPosition());
				//mySprite.setPosition(screenPosition);
				//this is world position
				//Vec2d screenPosition = this.myParent.getGameWorld().gameToScreenTransform(transformComponent.getPosition());
				//mySprite.setPosition(screenPosition);	
				
				
				//is this world space??
				/*double xPos =  0;
				double yPos =  0;
				
				Vec2d currentPositionInScreenSpace = this.myParent.getGameWorld().gameToScreenTransform(transformComponent.getPosition());
				if(currentPositionInScreenSpace.x < myPanelScreenViewPortUpperLeft.x)
				{
					
				}
				if (currentPositionInScreenSpace.y < myPanelScreenViewPortUpperLeft.y)
				{
					
				}*/
				
				//mySprite.setPosition(transformComponent.getPosition());	
				
			}
			 
		}	
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public SpriteComponent getSprite() {
		return mySprite;
	}

	public void setSprite(SpriteComponent mySprite) {
		this.mySprite = mySprite;
	}

	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		Component clone = ComponentFactory.getInstance().createComponent(this.myName);
		SpriteComponent sprite = this.mySprite.clone();
		((GraphicsComponent)clone).mySprite = sprite;
		clone.myParent =this.myParent;
		return clone;
	}

	public void clip() {
		// TODO Auto-generated method stub

		TransformComponent transformComponent = (TransformComponent) this.myParent
				.getComponent(ComponentContants.transform);
		if (transformComponent != null) {

			// Vec2d spriteSizeGameSpace =
			// this.myParent.getGameWorld().screenToGameTransform(new
			// Vec2d(mySprite.getWidth(),mySprite.getHeight()));
			Vec2d currentPositionInScreenSpace = this.myParent.getGameWorld()
					.gameToScreenTransform(transformComponent.getPosition());
			Vec2d currentSizeInScreenSpace = this.myParent.getGameWorld().gameToScreenTransform(
					transformComponent.getPosition().plus(new Vec2d(mySprite.getWidth(), mySprite.getHeight())));

			double startPosX = Math.max(currentPositionInScreenSpace.x, myPanelScreenViewPortUpperLeft.x);
			startPosX = Math.min(startPosX, myPanelScreenViewPortUpperLeft.x + myPanelScreenViewPortSize.x);

			double startPosY = Math.max(currentPositionInScreenSpace.y, myPanelScreenViewPortUpperLeft.y);
			startPosY = Math.min(startPosY, myPanelScreenViewPortUpperLeft.y + myPanelScreenViewPortSize.y);

			this.mySprite.getLayout().setPosition(new Vec2d(startPosX, startPosY));

			Vec2d p1 = currentPositionInScreenSpace;

			Vec2d currentWidth = new Vec2d(currentSizeInScreenSpace.x, p1.y);
			Vec2d currentHeigth = new Vec2d(p1.x, currentSizeInScreenSpace.y);

			double vCurretWidth = Math.max(p1.dist(currentWidth), mySprite.getWidth());
			double vCurretHeight = Math.max(p1.dist(currentHeigth), mySprite.getHeight());

			this.mySprite.getLayout().setSize(new Vec2d(vCurretWidth, vCurretHeight));

			// actual clip
			// at this point the sprite size is in screen space coordinates
			Vec2d p2x = new Vec2d(p1.x + this.mySprite.getLayout().getSize().x, p1.y);
			Vec2d p2y = new Vec2d(p1.x, p1.y + this.mySprite.getLayout().getSize().y);
			Vec2d p2xy = new Vec2d(p1.x + this.mySprite.getLayout().getSize().x, p1.y + this.mySprite.getLayout().getSize().y);

			//double dx = 0;
			//double dy = 0;
			
			Vec2d xclipCoords[] = cohenSutherlandClip(p1, p2x);
			double xDistance = 0;

			if (xclipCoords[0] == null && xclipCoords[1] == null) {

				xclipCoords = cohenSutherlandClip(p2xy, p2y);

				if (xclipCoords[0] == null) {
					// not in viewport
					this.myInViewportX = false;
				} else {
					xDistance = xclipCoords[0].x;
					this.myInViewportX = true;
				}

			} else {
				//if((this.myClipCode & RIGHT) != 0)
				//{
				//	xDistance = xclipCoords[1].x;
				//}
				//else if((this.myClipCode & LEFT) != 0)
				//{
					xDistance =  xclipCoords[1].x ;
				//}
				
				this.myInViewportX = true;
			}

			Vec2d yclipCoords[] = cohenSutherlandClip(p1, p2y);
			double yDistance = 0;

			if (yclipCoords[0] == null && yclipCoords[1] == null) {

				xclipCoords = cohenSutherlandClip(p2xy, p2x);
				if (xclipCoords[0] == null) {
					// not in viewport
					this.myInViewportY = false;

				} else {
					yDistance = xclipCoords[0].y;
					this.myInViewportY = true;
				}

			} else {
				yDistance = yclipCoords[1].y;
				this.myInViewportY = true;
			}

			
			double dx = Math.abs(this.mySprite.getLayout().getPosition().x - xDistance);
			double dy = Math.abs(this.mySprite.getLayout().getPosition().y - yDistance);
			
			this.mySprite.getLayout().setSize(new Vec2d(dx, dy));
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
		this.myClipCode = 0;
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
	            	this.myClipCode = code_out;
	            	p1 = new Vec2d(x,y); 
	            	 
	                code1 = computeCode(p1); 
	            } 
	            else
	            { 
	            	this.myClipCode = code_out;
	            	p2 = new Vec2d(x,y); 
	                code2 = computeCode(p2); 
	            } 
	        } 
	    } 
	   
	    return acceptedValues;
	         
   }

	
}
