package fxengine.components;

import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;

public class PhysicsComponent extends Component{

	
	
	private float myMass = 1;
	private Vec2d myVelocity;
	
	private Vec2d myImpulse; 
	private Vec2d myForce;
	
	public PhysicsComponent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		this.myVelocity = new Vec2d(0);
		
		this.myImpulse =new Vec2d(0) ; 
		this.myForce =  new Vec2d(0);
	}

	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		double milliseconds = nanosSincePreviousTick / 1000000;
		if(this.myParent.hasComponent(ComponentContants.transform))
		{
			TransformComponent transform = (TransformComponent)this.myParent.getComponent(ComponentContants.transform);
			
			this.myForce = this.myForce.smult(milliseconds).sdiv(this.myMass);
			this.myImpulse = this.myImpulse.sdiv(this.myMass);
			
			
			//this.myVelocity = this.myVelocity.plus(this.myForce.plus(this.myImpulse)); 
			this.myVelocity = this.myVelocity.plus(this.myForce);
			
			Vec2d position = transform.getPosition();
			
			position = position.plus(this.myVelocity.smult(milliseconds));
			
			transform.setPosition(position);
			
			this.myForce.smult(0);
			this.myImpulse.smult(0);
		}
	}
	
	public void lateTick(double deltaTime)
	{
		// TODO Auto-generated method stub
		//double milliseconds = nanosSincePreviousTick / 1000000;
		if(this.myParent.hasComponent(ComponentContants.transform))
		{
			TransformComponent transform = (TransformComponent)this.myParent.getComponent(ComponentContants.transform);
			
			this.myForce = this.myForce.smult(deltaTime).sdiv(this.myMass);
			this.myImpulse = this.myImpulse.sdiv(this.myMass);
			
			
			//this.myVelocity = this.myVelocity.plus(this.myForce.plus(this.myImpulse)); 
			this.myVelocity = this.myVelocity.plus(this.myForce);
			
			Vec2d position = transform.getPosition();
			
			position = position.plus(this.myVelocity.smult(deltaTime));
			
			transform.setPosition(position);
			
			this.myForce.smult(0);
			this.myImpulse.smult(0);
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
	
	public void applyForce(Vec2d force) {
		this.myForce = this.myForce.plus(force);
    }
	
	public void applyImpulse(Vec2d impulse) {
		this.myImpulse = this.myImpulse.plus(impulse);
    }


	public float getMass() {
		return myMass;
	}

	public void setMyMass(float mass) {
		this.myMass = mass;
	}

	public Vec2d getVelocity() {
		return myVelocity;
	}

	public void setVelocity(Vec2d velocity) {
		this.myVelocity = velocity;
	}

	
	/*public void OnCollide(Collision c)
	{
		
	}*/

}
