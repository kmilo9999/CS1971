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
		
		if(componentName.equals(ComponetContants.mouseEvents))
		{
			return new MouseControllerBehaviorComponent(ComponetContants.mouseEvents);
		}
		
		if(componentName.equals(ComponetContants.keyEvents))
		{
			return new KeyControllerBehaviorComponent(ComponetContants.keyEvents);
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
