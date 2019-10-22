package fxengine.components;

import fxengine.collision.CollisionShape;
import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;

public class PhysicsComponent extends Component{

	
	private float myMass = 1;
	private Vec2d myVelocity;
	private double myRestitution = 0;
	
	private Vec2d myImpulse; 
	private Vec2d myForce;
	
	private CollisionShape myCollisionShape;
	
	private boolean onStacticObject = false;
	
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
		if(!this.isInitialized())
		{
			if (this.myParent.hasComponent(ComponentContants.collision) ) 
			{
				if(!this.myParent.getComponent(ComponentContants.collision).isInitialized())
				{
					this.myParent.getComponent(ComponentContants.collision).initialize();	
				}
				
				myCollisionShape = ((CollisionComponent) this.myParent.getComponent(ComponentContants.collision))
						.getCollisionShape();
			}
			else
			{
				CollisionComponent collisionComponent = (CollisionComponent)ComponentFactory.getInstance().createComponent(ComponentContants.collision);
				collisionComponent.initialize();
				myCollisionShape = collisionComponent.getCollisionShape();
			}
           this.isInitialized = true;			
		}
		
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
			
			
			this.myVelocity = this.myVelocity.plus(this.myForce.plus(this.myImpulse)); 
			//this.myVelocity = this.myVelocity.plus(this.myForce);
			
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
		if(this.myParent.hasComponent(ComponentContants.collision))			
		{
			if(((CollisionComponent)this.myParent.getComponent(ComponentContants.collision)).isStatic())
			{
				return;
			}
		}
		
		this.myForce = this.myForce.plus(force);
    }
	
	public void applyImpulse(Vec2d impulse) {
		if(this.myParent.hasComponent(ComponentContants.collision))			
		{
			if(((CollisionComponent)this.myParent.getComponent(ComponentContants.collision)).isStatic())
			{
				return;
			}
		}
		this.myImpulse = this.myImpulse.plus(impulse);
    }


	public float getMass() {
		return myMass;
	}

	public void setMass(float mass) {
		this.myMass = mass;
	}

	public Vec2d getVelocity() {
		return myVelocity;
	}

	public void setVelocity(Vec2d velocity) {
		this.myVelocity = velocity;
	}

	public double getRestitution() {
		return myRestitution;
	}

	public void setRestitution(double restitution) {
		this.myRestitution = restitution;
	}

	public Vec2d resolveVelocity(PhysicsComponent other)
	{
		if( other.getParent().hasComponent(ComponentContants.physics))
		{
			
			PhysicsComponent otherPhysicsComponent = (PhysicsComponent)other.getParent().getComponent(ComponentContants.physics);
			
			double COR = Math.sqrt(myRestitution * otherPhysicsComponent.getRestitution());
			
			Vec2d initMomemtum =  myVelocity.smult(myMass);
			Vec2d otherInitMomemtum =  otherPhysicsComponent.getVelocity().smult(otherPhysicsComponent.getMass());
			Vec2d corFactor = otherPhysicsComponent.getVelocity().minus(myVelocity).smult(COR * otherPhysicsComponent.getMass());
			double totalMass = myMass + otherPhysicsComponent.getMass();
			
			Vec2d finalVector = initMomemtum.plus(otherInitMomemtum).plus(corFactor).sdiv((float)totalMass);
			return finalVector;
			
		}
		
		return null;
		
	}
	
	public Vec2d resolveImpulse(PhysicsComponent other)
	{
		if(other.getParent().hasComponent(ComponentContants.physics))
		{
			
			Vec2d impulseVector = new Vec2d(0);
			CollisionComponent otherCollisionComponent = (CollisionComponent) other.getParent()
					.getComponent(ComponentContants.collision);
			
			PhysicsComponent otherPhysicsComponent = (PhysicsComponent) other.getParent()
					.getComponent(ComponentContants.physics);
             
			double COR = Math.sqrt(myRestitution * otherPhysicsComponent.getRestitution());
			
			if(otherCollisionComponent.isStatic())
			{
				double scaleFactor = myMass * (1 + COR);
				//double scaleFactor = other.getMass() * (1 + COR);
				impulseVector = other.getVelocity().minus(myVelocity);
				impulseVector = impulseVector.smult(scaleFactor);
				//impulseVector = new Vec2d(0);
				//impulseVector = myVelocity.minus(other.getVelocity()).smult(scaleFactor);
			}
			else
			{
				
				double totalMass = myMass + otherPhysicsComponent.getMass();
				double multipliedMass = myMass * otherPhysicsComponent.getMass();
				double scaleFactor = (multipliedMass * (1 + COR)) / totalMass;
				impulseVector = otherPhysicsComponent.getVelocity().minus(myVelocity)
						.smult(scaleFactor);
								
			}
			
			return impulseVector;

			
		}
		
		return null;
	}

	public CollisionShape getCollisionShape() {
		return myCollisionShape;
	}

	public void setCollisionShape(CollisionShape collisionShape) {
		this.myCollisionShape = collisionShape;
	}

	public boolean isOnStacticObject() {
		return onStacticObject;
	}

	public void setOnStacticObject(boolean onStacticObject) {
		this.onStacticObject = onStacticObject;
	}


}
