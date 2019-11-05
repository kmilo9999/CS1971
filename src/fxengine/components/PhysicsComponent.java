package fxengine.components;

import org.w3c.dom.Element;

import fxengine.collision.CollisionShape;
import fxengine.math.Vec2d;
import fxengine.system.PhysicsSystem;
import javafx.scene.canvas.GraphicsContext;

public class PhysicsComponent extends Component{

	
	private float myMass = 1;
	private Vec2d myVelocity;
	private double myRestitution = 0;
	
	private Vec2d myImpulse; 
	private Vec2d myForce;
	
	private CollisionShape myCollisionShape;
	
	private double gravityMultiplier = 1;
	
	private boolean onStacticObject = false;
	
	public PhysicsComponent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		this.myVelocity = new Vec2d(0);
		
		this.myImpulse =new Vec2d(0) ; 
		this.myForce =  new Vec2d(0);
	}

	public void resetComponent()
	{
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
		
	}
	
	public void lateTick(double deltaTime)
	{
		// TODO Auto-generated method stub
		//double milliseconds = nanosSincePreviousTick / 1000000;
		if(this.myParent.hasComponent(ComponentContants.transform))
		{
			TransformComponent transform = (TransformComponent)this.myParent.getComponent(ComponentContants.transform);
			
			if(Double.isNaN(this.myVelocity.x) || Double.isNaN(this.myVelocity.y) )
			{
				System.out.println("ERROR VEL");
			}
			
			this.myForce = this.myForce.smult(deltaTime).sdiv(this.myMass);
			if(this.myParent.hasComponent(ComponentContants.collision))			
			{
				if(!((CollisionComponent)this.myParent.getComponent(ComponentContants.collision)).isStatic()
						&& (!isOnStacticObject()))
				{
					this.myForce = this.myForce.plus(PhysicsSystem.down.smult(PhysicsSystem.gravityConstant * myMass)).smult(gravityMultiplier);
				}
			}
			
			
			this.myImpulse = this.myImpulse.sdiv(this.myMass);
			
			
			this.myVelocity = this.myVelocity.plus(this.myForce.plus(this.myImpulse)); 
			//this.myVelocity = this.myVelocity.plus(this.myForce);
			
			Vec2d position = transform.getPosition();
			
			position = position.plus(this.myVelocity.smult(deltaTime));
			
			transform.setPosition(position);
			
			this.myForce = this.myForce.smult(0);
			this.myImpulse = this.myImpulse.smult(0);
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
				impulseVector = myVelocity;
				impulseVector = impulseVector.smult(scaleFactor);
				//impulseVector = new Vec2d(0);
				//impulseVector = myVelocity.minus(other.getVelocity()).smult(scaleFactor);
			}
			else
			{
				
				double totalMass = myMass + otherPhysicsComponent.getMass();
				double multipliedMass = myMass * otherPhysicsComponent.getMass();
				double scaleFactor = (multipliedMass * (1 + COR)) / totalMass;
				impulseVector = otherPhysicsComponent.getVelocity().minus(myVelocity);
				impulseVector = impulseVector.smult(scaleFactor);
								
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

	public double getGravityMultiplier() {
		return gravityMultiplier;
	}

	public void setGravityMultiplier(double gravityMultiplier) {
		this.gravityMultiplier = gravityMultiplier;
	}

	@Override
	public Element saveState() {
		Element physics = doc.createElement("PhysicsComponent");
		Element mass = doc.createElement("mass");
		mass.setAttribute("float", ""+myMass);
		Element restitution = doc.createElement("restitution");
		restitution.setAttribute("double", ""+myRestitution);
		
		physics.appendChild(mass);
		
		physics.appendChild(restitution);
		return physics;
		
	}

	@Override
	public void loadState() {
		// TODO Auto-generated method stub
		
	}


}
