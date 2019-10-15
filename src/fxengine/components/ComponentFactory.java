package fxengine.components;

public class ComponentFactory {

	private ComponentFactory()
	{
	   	
	};
	
	private static ComponentFactory instance = null;
	
	
	public static ComponentFactory getInstance()
	{
		if(instance == null)
		{
			instance = new ComponentFactory();
			
		}
		return instance;
	}
	
	
	public Component createComponent(String componentName)
	{
		if(componentName.equals(ComponentContants.graphics))
		{
			return new GraphicsComponent(componentName);
		}
		
		else if(componentName.equals(ComponentContants.controllerMouseEvents))
		{
			return new MouseControllerBehaviorComponent(componentName);
		}
		
		else if(componentName.equals(ComponentContants.controllerKeyEvents))
		{
			return new AWSDKeyControllerBehaviorComponent(componentName);
		}
		
		else if(componentName.equals(ComponentContants.cameraControllerKeyEvents))
		{
			return new CameraKeyControllerBehavior(componentName);
		}
		
		else if(componentName.equals(ComponentContants.cameraControllerMouseEvents))
		{
			return new CameraMouseControllerBehavior(componentName);
		}
		
		else if(componentName.equals(ComponentContants.mouseEvents))
		{
			return new MouseEventComponent(componentName);
		}
		
		else if(componentName.equals(ComponentContants.keyEvents))
		{
			return new KeyEventComponent(componentName);
		}
		
		else if(componentName.equals(ComponentContants.transform))
		{
			return new TransformComponent(componentName);
		}
		
		else if(componentName.equals(ComponentContants.controlled))
		{
			return new ControllerBehaviourComponent(componentName);
		}
		
		else if(componentName.equals(ComponentContants.collision))
		{
			return new CollisionComponent(componentName);
		}
		
		else if(componentName.equals(ComponentContants.sprite))
		{
			return new SpriteComponent(componentName);
		}
		
		else if(componentName.equals(ComponentContants.tiled_sprite))
		{
			return new TiledSpriteComponent(componentName);
		}
		
		else if(componentName.equals(ComponentContants.animation))
		{
			return new AnimationComponent(componentName);
		}
		
		else if(componentName.equals(ComponentContants.keyAnimationController))
		{
			return new AnimationControllerComponent(componentName);
		}
		
		else if(componentName.equals(ComponentContants.terrain))
		{
			return new TerrainComponent(componentName);
		}
		
		else if(componentName.equals(ComponentContants.AIMovement))
		{
			return new AIMovementComponent(componentName);
		}
		
		else if(componentName.equals(ComponentContants.AutoAnimation))
		{
			return new AnimationNonControlledComponent(componentName);
		}
		
		else if(componentName.equals(ComponentContants.AI))
		{
			return new AIComponent(componentName);
		}
		
		return null;
	}
}
