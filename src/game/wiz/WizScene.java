package game.wiz;

import fxengine.application.FXFrontEnd;
import fxengine.collision.CollisionConstants;
import fxengine.collision.CollisionShape;
import fxengine.collision.CollisionShapeFactory;
import fxengine.components.AnimationComponent;
import fxengine.components.CollisionComponent;
import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.components.GraphicsComponent;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import fxengine.objects.GameWorld;
import fxengine.scene.GameWorldScene;

public class WizScene extends GameWorldScene{

	private GameObject gameObject;
	private long myLapseTime = 0;
	private int currentFrame = 3;
	
	public WizScene(String name, FXFrontEnd application) {
		super(name, application);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initScene() 
	{
		 // Initialize game world
		super.initScene();
		
		/*AnimationComponent animation = new AnimationComponent(ComponentContants.sprite);
		animation.setFilePath("img/charactes_sprite_sheet.png");
		animation.setFrameSize(new Vec2d(32,36));
		animation.setNumFrames(new Vec2d(3,4));*/
		
		
		gameObject = new GameObject("warrior");
		//gameObject.setTag(alch_square);
		Component graphicsComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.graphics);
		AnimationComponent animation = new AnimationComponent(ComponentContants.sprite_animation);
		animation.setFilePath("img/charactes_sprite_sheet.png");
		animation.setFrameSize(new Vec2d(32,36));
		animation.setNumFrames(new Vec2d(3,4));
		animation.setFramePosition(new Vec2d(0,36));
		animation.setCurrentFrame(currentFrame);
		
		Component tranformComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.transform);
		Component mouseControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerMouseEvents);
		Component keyControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerKeyEvents);
		((TransformComponent)tranformComponent).setPosition(new Vec2d(3,10));
		Component collisionCompomemt =  ComponentFactory.getInstance().createComponent(ComponentContants.collision);
		CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
		((CollisionComponent)collisionCompomemt).setCollisionShape(myCollisionShape);
		//((CollisionComponent)collisionCompomemt).getHitList().add(alch_circle);
		
		gameObject.addComponent(graphicsComponent);
		gameObject.addComponent(tranformComponent);
		gameObject.addComponent(mouseControllerComponent);
		gameObject.addComponent(keyControllerComponent);
		gameObject.addComponent(collisionCompomemt);
		gameObject.addComponent(animation);
		
		
		this.myGameWorld.addGameObject(gameObject, GameWorld.FrontLayer);		
		
		///----------- Camera
		GameObject camera = new GameObject("Camera");
		Component cameraMouseControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.cameraControllerMouseEvents);
		Component cameraKeyControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.cameraControllerKeyEvents);
		camera.addComponent(cameraMouseControllerComponent);
		camera.addComponent(cameraKeyControllerComponent);
		
		this.myGameWorld.addGameObject(camera, GameWorld.FrontLayer);
		///----------------------------------
		
	}

	
	@Override
	public void onTick(long nanosSincePreviousTick)
	{
		
		AnimationComponent animation = (AnimationComponent)gameObject.getComponent(ComponentContants.sprite_animation);
		
		myLapseTime += nanosSincePreviousTick;
		if(myLapseTime > 1000000000)
		{
			currentFrame++;
			animation.setCurrentFrame(currentFrame );
			myLapseTime -= 1000000000;
		}
		
		//animation.setCurrentFrame(5);
		
		super.onTick(nanosSincePreviousTick);
	}
}
