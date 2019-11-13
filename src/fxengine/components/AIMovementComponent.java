package fxengine.components;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;

public class AIMovementComponent extends Component{

	private Queue<Vec2d> myPathPoints;
	
	private Vec2d myStartPoint;
	private Vec2d myNextPoint;
	private Vec2d myCurrentPoint = null;
	private long myCurrentTime = 0;
	
	private boolean myPathUpdated;
	private long myWalkTime = 900000000;
	private double myNumSteps = 0 ;
	private double myCurrentStep = 0;
	private boolean run = false;
	
	public AIMovementComponent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		this.myPathPoints = new LinkedList<Vec2d>();
		this.myStartPoint = null;
		this.myCurrentPoint = null;
		this.myCurrentTime = 0;
		this.myNumSteps = 9;
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
			this.isInitialized = true;
			
		}
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		if(myPathUpdated)
		{
			this.run = true;
		}
		
		if(run)
		{
			this.myPathUpdated = false;
			if(this.myNextPoint == null)
			{
				
				if(!this.myPathPoints.isEmpty())
				{
					this.myNextPoint = this.myPathPoints.poll();
					if(this.myParent.hasComponent(ComponentContants.animation)
							&& this.myParent.hasComponent(ComponentContants.AutoAnimation))
					{
						AnimationNonControlledComponent animationControllers = (AnimationNonControlledComponent) this.myParent.getComponent(ComponentContants.AutoAnimation);
						Vec2d currentDirection = this.myNextPoint.minus(this.myStartPoint).normalize();

						// animation.
						animationControllers.setCurrentDirection(currentDirection);
						
					}
				}
				else
				{
					this.run= false;
				}
			}
			else{
				
				this.myCurrentTime += nanosSincePreviousTick;
		        
				if(this.myCurrentTime >= this.myWalkTime && this.myCurrentStep < this.myNumSteps)
				{
					
					this.myCurrentTime = 0;
					this.myCurrentStep++;
					this.myCurrentPoint = this.myStartPoint.lerpTo(this.myNextPoint, this.myCurrentStep  * (1/this.myNumSteps));
					TransformComponent transform =  (TransformComponent)this.myParent.getComponent(ComponentContants.transform);
			        transform.setPosition(this.myCurrentPoint);
			    
				}
				else
				{
					if(this.myCurrentStep == this.myNumSteps)
					{
						// I have arrived
						//System.out.println("I have arrived");
						if(!this.myPathPoints.isEmpty())
			        	{
							this.myStartPoint = new Vec2d(this.myCurrentPoint);
			        	}
			        	else
			        	{	
			        		run = false;
			        		if(this.myParent.hasComponent(ComponentContants.animation)
									&& this.myParent.hasComponent(ComponentContants.AutoAnimation))
			        		{
			        			AnimationNonControlledComponent animationNonControlled = (AnimationNonControlledComponent) this.myParent.getComponent(ComponentContants.AutoAnimation);
								animationNonControlled.setCurrentDirection(new Vec2d(0));
										        			
			        		}
			        		
			        	}
						this.myNextPoint = null;
						this.myCurrentTime = 0;
						this.myCurrentStep = 0;
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

	@Override
	public Element saveState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadState(Node node) {
		// TODO Auto-generated method stub
		
	}

}
