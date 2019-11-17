package fxengine.components;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import fxengine.math.Vec2d;
import fxengine.objects.GameWorld;
import fxengine.particleeffects.Particle;
import javafx.scene.canvas.GraphicsContext;

public class ParticleEmitterComponent extends Component{

	private int myDuration;
	private boolean myLooping;
	private int myMaxParticles;
	private int myInitParticles;
	private String parentName;
	
	private String myParticleTexture;
	private float myParticlesMass;
	private float myParticlesRestitution;
	private int myParticlesTimeToLive;
	private Vec2d myParticlesIntialDirection;
	private Vec2d myParticlesInitialPosition;	
	

	private double delayToCreateParticle;
	
	private List<Particle> myParticles = new ArrayList<Particle>();
	private Queue<Integer> myParticlesToRemove = new LinkedList<Integer>();
	
	private double numberId = 0.51;
	
	public ParticleEmitterComponent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize() {
		parentName = this.myParent.getId();
		Random rand = new Random(); 
		double rand_int1 = rand.nextDouble()+numberId;
		this.delayToCreateParticle = 0.5;
	    
		double anglesDt = (360.0/myInitParticles) * (Math.PI/180);
		
		double currentAngle = 0;
		for(int i = 0; i < myInitParticles; i++)
		{
		    double x = Math.cos(currentAngle) * myParticlesIntialDirection.x - Math.sin(currentAngle)  * myParticlesIntialDirection.y;
		    double y = Math.sin(currentAngle) * myParticlesIntialDirection.x + Math.cos(currentAngle)  * myParticlesIntialDirection.y;
			Vec2d rotatedDirection = new Vec2d(x,y);
			Particle particle = new Particle(parentName+"_particle"+rand_int1,
					myParticleTexture,myParticlesInitialPosition,
					myParticlesMass,myParticlesRestitution,
					myParticlesTimeToLive,rotatedDirection);
			
			particle.setGameWorld(this.myParent.getGameWorld());
			particle.initialize();
			myParticles.add(particle);
			this.myParent.getGameWorld().addGameObject(particle, GameWorld.ParticlesLayer);
			currentAngle += anglesDt;
		}
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		while(!myParticlesToRemove.isEmpty())
		{
			Integer index = myParticlesToRemove.remove();
			Particle p = myParticles.get(index);
			myParticles.remove(index);
			this.myParent.getGameWorld().removeGameObject(p.getId());
		}
		
		
		for(int i = 0 ; i < myParticles.size();i++)
		{
			if(myParticles.get(i).getTimeToLive() <= 0)
			{
				myParticlesToRemove.add(i);
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

	@Override
	public Element saveState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadState(Node node) {
		// TODO Auto-generated method stub
		
	}

	public int getDuration() {
		return myDuration;
	}

	public void setDuration(int duration) {
		this.myDuration = duration;
	}

	public boolean isLooping() {
		return myLooping;
	}

	public void setLooping(boolean looping) {
		this.myLooping = looping;
	}

	public int getMaxParticles() {
		return myMaxParticles;
	}

	public void setMaxParticles(int maxParticles) {
		this.myMaxParticles = maxParticles;
	}

	public String getParticleTexture() {
		return myParticleTexture;
	}

	public void setParticleTexture(String particleTexture) {
		this.myParticleTexture = particleTexture;
	}

	public float getParticlesMass() {
		return myParticlesMass;
	}

	public void setParticlesMass(float particlesMass) {
		this.myParticlesMass = particlesMass;
	}

	public float getParticlesRestitution() {
		return myParticlesRestitution;
	}

	public void setParticlesRestitution(float particlesRestitution) {
		this.myParticlesRestitution = particlesRestitution;
	}

	public int getParticlesTimeToLive() {
		return myParticlesTimeToLive;
	}

	public void setParticlesTimeToLive(int particlesTimeToLive) {
		this.myParticlesTimeToLive = particlesTimeToLive;
	}

	public Vec2d getParticlesIntialDirection() {
		return myParticlesIntialDirection;
	}

	public void setParticlesIntialDirection(Vec2d intialDirection) {
		this.myParticlesIntialDirection = intialDirection;
	}

	public int getInitParticles() {
		return myInitParticles;
	}

	public void setInitParticles(int initParticles) {
		this.myInitParticles = initParticles;
	}

	public Vec2d getParticlesInitialPosition() {
		return myParticlesInitialPosition;
	}

	public void setParticlesInitialPosition(Vec2d particlesInitialPosition) {
		this.myParticlesInitialPosition = particlesInitialPosition;
	}
  
}
