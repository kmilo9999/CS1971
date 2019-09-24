package game.alch;

import fxengine.UISystem.Label;
import fxengine.UISystem.Layout;
import fxengine.UISystem.UIConstants;
import fxengine.UISystem.UIElement;
import fxengine.UISystem.UISprite;
import fxengine.application.FXFrontEnd;
import fxengine.collision.CollisionConstants;
import fxengine.collision.CollisionShape;
import fxengine.collision.CollisionShapeFactory;
import fxengine.components.CollisionComponent;
import fxengine.components.Component;
import fxengine.components.ComponentFactory;
import fxengine.components.GraphicsComponent;
import fxengine.components.SpriteComponent;
import fxengine.components.ComponentContants;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import fxengine.objects.GameWorld;
import fxengine.scene.GameWorldScene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;


public class AlchScene extends GameWorldScene{

	private UIElement myMenuLayout = new Layout(450, 50, 70, 300, UIConstants.TRANSPARENT,2.5);
	private UIElement myMenuLabel = new Label("Elements",450, 40, UIConstants.BLACK,Font.font ("Verdana", 20) );
	private UIElement mySqueareMenu = new UISprite("resources/img/square.png",5, 20);
	private UIElement myCircleMenu = new UISprite("resources/img/circle.png",5, 110);
	
	
	public AlchScene(String name, FXFrontEnd application) {
		super(name, application);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initScene() 
	{
		myMenuLayout.addChildElement(mySqueareMenu);
		myMenuLayout.addChildElement(myCircleMenu);
		this.props.add(myMenuLayout);
		this.props.add(myMenuLabel);
		
        // Initialize game world
		super.initScene();
		
		
		GameObject gameObject = new GameObject("Sprite1");
		Component graphicsComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.graphics);
		SpriteComponent mySprite1 = new SpriteComponent("resources/img/square.png");
		((GraphicsComponent)graphicsComponent).setSprite(mySprite1);
		
		Component tranformComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.transform);
		//Component mouseEventsComponent =  ComponentFactory.getInstance().createComponent(ComponetContants.mouseEvents);
		//Component keyEventsComponent =  ComponentFactory.getInstance().createComponent(ComponetContants.keyEvents);
		Component mouseControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerMouseEvents);
		Component keyControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerKeyEvents);
		((TransformComponent)tranformComponent).setPosition(new Vec2d(3,10));
		Component collisionCompomemt =  ComponentFactory.getInstance().createComponent(ComponentContants.collision);
		CollisionShape myCollisionShape = CollisionShapeFactory.getInstance().createShape(CollisionConstants.AABShape);
		((CollisionComponent)collisionCompomemt).setCollisionShape(myCollisionShape);
		
		gameObject.addComponent(graphicsComponent);
		gameObject.addComponent(tranformComponent);
		//gameObject.addComponent(mouseEventsComponent);
		//gameObject.addComponent(keyEventsComponent);
		gameObject.addComponent(mouseControllerComponent);
		gameObject.addComponent(keyControllerComponent);
		gameObject.addComponent(collisionCompomemt);
		
		
		
		this.myGameWorld.addGameObject(gameObject, GameWorld.FrontLayer);		
		
		
		GameObject camera = new GameObject("Camera");
		Component cameraMouseControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.cameraControllerMouseEvents);
		Component cameraKeyControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.cameraControllerKeyEvents);
		camera.addComponent(cameraMouseControllerComponent);
		camera.addComponent(cameraKeyControllerComponent);
		
		this.myGameWorld.addGameObject(camera, GameWorld.FrontLayer);	
		
		GameObject gameObject2 = new GameObject("Sprite2");
		Component graphicsComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.graphics);
		SpriteComponent mySprite2 = new SpriteComponent("resources/img/circle.png");
		((GraphicsComponent)graphicsComponent2).setSprite(mySprite2);
		Component tranformComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.transform);
		//Component mouseEventsComponent =  ComponentFactory.getInstance().createComponent(ComponetContants.mouseEvents);
		//Component keyEventsComponent =  ComponentFactory.getInstance().createComponent(ComponetContants.keyEvents);
		Component mouseControllerComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerMouseEvents);
		Component keyControllerComponent2 =  ComponentFactory.getInstance().createComponent(ComponentContants.controllerKeyEvents);
		((TransformComponent)tranformComponent2).setPosition(new Vec2d(50));
		Component collisionCompomemt2 =  ComponentFactory.getInstance().createComponent(ComponentContants.collision);
		((CollisionComponent)collisionCompomemt2).getHitList().add("Sprite1");
		CollisionShape myCollisionShape2 = CollisionShapeFactory.getInstance().createShape(CollisionConstants.CIRCLEShape);
		((CollisionComponent)collisionCompomemt2).setCollisionShape(myCollisionShape2);
		
		
		gameObject2.addComponent(graphicsComponent2);
		gameObject2.addComponent(tranformComponent2);
		//gameObject.addComponent(mouseEventsComponent);
		//gameObject.addComponent(keyEventsComponent);
		gameObject2.addComponent(mouseControllerComponent2);
		gameObject2.addComponent(keyControllerComponent2);
		gameObject2.addComponent(collisionCompomemt2);
		
		
		this.myGameWorld.addGameObject(gameObject2, GameWorld.FrontLayer);
	}
	
	
	@Override
	public void onDraw(GraphicsContext graphicsContext)
	{
		//myMenuLayout.onDraw(graphicsContext);
		//myMenuLabel.onDraw(graphicsContext);
		super.onDraw(graphicsContext);
	}

	

}
