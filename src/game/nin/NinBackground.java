package game.nin;

import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.components.SpriteComponent;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;

public class NinBackground extends GameObject{

	public NinBackground(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	

	 @Override
	 public void initialize() {
		 
		 Component graphicsComponent = ComponentFactory.getInstance().createComponent(ComponentContants.graphics);
		 Component sprite = ComponentFactory.getInstance().createComponent(ComponentContants.sprite);
		 ((SpriteComponent)sprite).setFilePath("img/sky.png");
		 
		 Component transformComponent = ComponentFactory.getInstance().createComponent(ComponentContants.transform);
		 ((TransformComponent)transformComponent).setPosition(new Vec2d(0,0));
		 
		 this.addComponent(graphicsComponent);
		 this.addComponent(transformComponent);
		 this.addComponent(sprite);
		 
		 super.initialize();
	 }
	 
	 

}
