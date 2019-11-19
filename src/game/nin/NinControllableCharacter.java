package game.nin;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;



import fxengine.components.CollisionComponent;
import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.components.ParticleEmitterComponent;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import fxengine.objects.GameWorld;
import fxengine.system.PhysicsSystem;
import javafx.scene.media.AudioClip;


public class NinControllableCharacter extends NinElement{

	
	private AudioClip jumpSound;
	
	public NinControllableCharacter(String id, Vec2d initialPosition,String filePath, float mass, double restitution) {
		super(id, initialPosition,  filePath,  mass , restitution);
		// TODO Auto-generated constructor stub
		
		jumpSound  = new AudioClip(this.getClass().getClassLoader().getResource("sound/Mario_Jumping-Mike_Koenig-989896458.wav").toString()); 

		
	}
	
	@Override
	public void initialize() {
		
		
		
		this.setTag("character");
		Component keyControllerComponent = ComponentFactory.getInstance()
				.createComponent(ComponentContants.controllerKeyEvents);
		
		this.addComponent(keyControllerComponent);
		
		super.initialize();
		
		CollisionComponent collisionCompomemt = (CollisionComponent)this.getComponent(ComponentContants.collision);
		((CollisionComponent)collisionCompomemt).getHitList().add(NinScene.ENEMY);
		

	   
	}
	
	
	@Override
	public void onCollide(GameObject other)
	{
		if(other.getId() == "carrot")
		{
			System.out.println("you win");	
		}
		if(other.getLayerOrder() == GameWorld.DestructableLayer)
		{
			((NinDestructableBlock)other).destroyBlock();
		}
		
	}

	
	@Override
	public void onJump()
	{
		 jumpSound.play();
	}
}
