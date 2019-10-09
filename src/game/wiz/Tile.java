package game.wiz;

import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.components.SpriteAnimationComponent;
import fxengine.components.TransformComponent;
import fxengine.math.Vec2d;
import fxengine.math.Vec2i;
import fxengine.objects.GameObject;

public class Tile extends GameObject{

	private int myColor;
	private Vec2d myTextCoord;
	private Vec2d myPosition;
	
	public Tile(String id, int color, Vec2d position, Vec2d textCoord) {
		super(id);
		// TODO Auto-generated constructor stub
		myColor =  color;
		myTextCoord = textCoord;
		myPosition = position;
	}

	@Override
	public void initialize()
	{
		Component tranformComponent = ComponentFactory.getInstance().createComponent(ComponentContants.transform);
		((TransformComponent)tranformComponent).setPosition(myPosition);
		
        Component graphicsComponent = ComponentFactory.getInstance().createComponent(ComponentContants.graphics);
		
		Component spriteComponent = ComponentFactory.getInstance().createComponent(ComponentContants.sprite_animation);
		((SpriteAnimationComponent)spriteComponent).setFilePath("img/tiles.png");
		((SpriteAnimationComponent)spriteComponent).setFrameSize(new Vec2d(32, 36));
		((SpriteAnimationComponent)spriteComponent).setNumFrames(new Vec2i(1, 3));
		((SpriteAnimationComponent)spriteComponent).setFramePosition(new Vec2d(0, 0));
		((SpriteAnimationComponent)spriteComponent).setCurrentFrame(myColor);
		
		this.addComponent(tranformComponent);
		this.addComponent(graphicsComponent);
		this.addComponent(spriteComponent);
		
		super.initialize();
	}

	public int getMyColor() {
		return myColor;
	}

	public void setMyColor(int myColor) {
		this.myColor = myColor;
	}
	
}
