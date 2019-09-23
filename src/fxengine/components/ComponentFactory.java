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
		if(componentName.equals(ComponetContants.graphics))
		{
			return new GraphicsComponent(ComponetContants.graphics);
		}
		
		if(componentName.equals(ComponetContants.controllerMouseEvents))
		{
			return new MouseControllerBehaviorComponent(ComponetContants.controllerMouseEvents);
		}
		
		if(componentName.equals(ComponetContants.controllerKeyEvents))
		{
			return new KeyControllerBehaviorComponent(ComponetContants.controllerKeyEvents);
		}
		
		if(componentName.equals(ComponetContants.cameraControllerKeyEvents))
		{
			return new CameraKeyControllerBehavior(ComponetContants.cameraControllerKeyEvents);
		}
		
		if(componentName.equals(ComponetContants.cameraControllerMouseEvents))
		{
			return new CameraMouseControllerBehavior(ComponetContants.cameraControllerMouseEvents);
		}
		
		if(componentName.equals(ComponetContants.mouseEvents))
		{
			return new MouseEventComponent(ComponetContants.mouseEvents);
		}
		
		if(componentName.equals(ComponetContants.keyEvents))
		{
			return new KeyEventComponent(ComponetContants.keyEvents);
		}
		
		if(componentName.equals(ComponetContants.transform))
		{
			return new TransformComponent(ComponetContants.transform);
		}
		
		if(componentName.equals(ComponetContants.controlled))
		{
			return new ControllerBehaviourComponent(ComponetContants.controlled);
		}
		
		return null;
	}
}
