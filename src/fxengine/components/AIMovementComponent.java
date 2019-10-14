package fxengine.components;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;

public class AIMovementComponent extends Component{

	private Queue<Vec2d> myPathPoints;
	
	private Vec2d myStartPoint;
	private Vec2d myNextPoint;
	private Vec2d myCurrentPoint = null;
	private long myCurrentTime = 0;
	private long myStartTime = 0;
	private boolean myPathUpdated;
	private long myWalkTime = 180000;
	private boolean run = false;
	
	public AIMovementComponent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		myPathPoints = new LinkedList<Vec2d>();
		myStartPoint = null;
		myCurrentPoint = null;
		myCurrentTime = 0;
		myStartTime = 0;
	}

	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		if( myPathPoints.peek() != null)
		{
			isInitialized = true;
			
		}
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		if(myPathUpdated)
		{
			run = true;
		}
		
		if(run)
		{
			myPathUpdated = false;
			if(myNextPoint == null)
			{
				
				if(!this.myPathPoints.isEmpty())
				{
					myNextPoint = this.myPathPoints.poll();	
				}
				else
				{
					run= false;
				}
			}
			else{
				
				if (myStartTime == 0) {
		            myStartTime = nanosSincePreviousTick;
		          
		        }
				
				long now = nanosSincePreviousTick;
		        long diff = now - myStartTime;
		        /*if (diff >= myPlayTime) {
		            diff = myPlayTime;
		           
		        }*/
		        
		        myCurrentTime = diff / myWalkTime;
		        //pointInTime = i;

		      
		        
		        double pointX = myStartPoint.x + ((myNextPoint.x - myStartPoint.x) * ((float)myCurrentTime/100000.0f));
		        double pointY = myStartPoint.y + ((myNextPoint.y - myStartPoint.y) * ((float)myCurrentTime/100000.0f));
		        myCurrentPoint = new Vec2d(pointX,pointY);
		        System.out.println("myCurrentPoint: " + myCurrentPoint);
		        TransformComponent transform =  (TransformComponent)this.myParent.getComponent(ComponentContants.transform);
		        transform.setPosition(myCurrentPoint);
		        
		        if(myCurrentPoint.x - myNextPoint.x > 0.00001 && myCurrentPoint.y - myNextPoint.y > 0.00001)
		        {
		        	// I have arrived
		        	if(!this.myPathPoints.isEmpty())
		        	{
		        		myStartPoint = new Vec2d(myCurrentPoint);
		        		myNextPoint = null;
		        		myStartTime = 0;
		        	}
		        	else
		        	{
		        		run = false;
			        	return;	
		        	}
		        	
		        }
			
			}
			
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub
		
	}

	public Queue<Vec2d> getMyPathPoints() {
		return myPathPoints;
	}

	public void setPathPoints(Queue<Vec2d> pathPoints) {
		if(pathPoints !=null && !pathPoints.isEmpty())
		{
			this.myPathPoints = pathPoints;
			
			myStartPoint = this.myPathPoints.poll();
			if(myStartPoint == null)
			{
			   return;	
			}
			
			
			
			//at least has one point to go to
			myPathUpdated = true;
		}
		
	}

}
