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
			return new KeyControllerBehaviorComponent(componentName);
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
		
		else if(componentName.equals(ComponentContants.sprite_animation))
		{
			return new SpriteAnimationComponent(componentName);
		}
		
		else if(componentName.equals(ComponentContants.animation))
		{
			return new AnimationComponent(componentName);
		}
		
		return null;
	}
}
