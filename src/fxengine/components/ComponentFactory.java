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
		
		if(componentName.equals(ComponentContants.controllerMouseEvents))
		{
			return new MouseControllerBehaviorComponent(componentName);
		}
		
		if(componentName.equals(ComponentContants.controllerKeyEvents))
		{
			return new KeyControllerBehaviorComponent(componentName);
		}
		
		if(componentName.equals(ComponentContants.cameraControllerKeyEvents))
		{
			return new CameraKeyControllerBehavior(componentName);
		}
		
		if(componentName.equals(ComponentContants.cameraControllerMouseEvents))
		{
			return new CameraMouseControllerBehavior(componentName);
		}
		
		if(componentName.equals(ComponentContants.mouseEvents))
		{
			return new MouseEventComponent(componentName);
		}
		
		if(componentName.equals(ComponentContants.keyEvents))
		{
			return new KeyEventComponent(componentName);
		}
		
		if(componentName.equals(ComponentContants.transform))
		{
			return new TransformComponent(componentName);
		}
		
		if(componentName.equals(ComponentContants.controlled))
		{
			return new ControllerBehaviourComponent(componentName);
		}
		
		if(componentName.equals(ComponentContants.collision))
		{
			return new CollisionComponent(componentName);
		}
		
		return null;
	}
}
