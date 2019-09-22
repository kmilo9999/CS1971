package fxengine.components;

import fxengine.event.Event;
import fxengine.event.EventsConstants;
import fxengine.math.Vec2d;
import fxengine.objects.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;

public class MouseEventComponent extends Component {

	public static final int RightClick = 0;
	public static final int LeftClick = 1;
	public static final int MiddleClick = 2;
	
	private Vec2d myLastPosition;
	private Vec2d myPosition;

	private boolean[] mouseButtons = new boolean[3];
	
	public MouseEventComponent(String name) {
		super(name);
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		myLastPosition = new Vec2d(0);
		myPosition = new Vec2d(0);
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub
		
	}
	
	public void onMouseMoved(Vec2d position)
	{
		myLastPosition = myPosition;
		myPosition = position;	
		//System.out.println("MOUSE MOVE "+myPosition.x +" "+ myPosition.y);
	}
	
	public void onMouseClicked(Vec2d clickPosition,int clickedButton)
	{
		TransformComponent transform = (TransformComponent) this.myParent.getComponent(ComponetContants.transform);
		Sprite sprite = ((GraphicsComponent) this.myParent.getComponent(ComponetContants.graphics)).getSprite();
		
		Vec2d elemetSize = sprite.getSize();
		Vec2d elemetPosition = transform.getPosition();
		Vec2d transFormClickPos = this.myParent.getGameWorld().screenToGameTransform(clickPosition);
	  	//System.out.println("clickPos: " + elemetPosition.x + " " + elemetPosition.y + " ");
	  	
		
		if((transFormClickPos.x > elemetPosition.x && transFormClickPos.x < elemetPosition.x + elemetSize.x)
		    &&( transFormClickPos.y > elemetPosition.y && transFormClickPos.y < elemetPosition.y + elemetSize.y) )
		{
		  	//System.out.println("CLICKED " + this.myParent.getId());
		    this.myParent.setSelected(true);
		}
		
		
	}
	
	public void onMousePressed(Vec2d clickPosition, int pressedButton)
	{
		
		if(!mouseButtons[pressedButton] )
		{
			TransformComponent transform = (TransformComponent) this.myParent.getComponent(ComponetContants.transform);
			Sprite sprite = ((GraphicsComponent) this.myParent.getComponent(ComponetContants.graphics)).getSprite();
			
			
			Vec2d elemetSize = sprite.getSize();
			Vec2d elemetPosition = sprite.getPosition();
			Vec2d transFormClickPos = this.myParent.getGameWorld().screenToGameTransform(clickPosition);
		  	//System.out.println("clickPos: " + elemetPosition.x + " " + elemetPosition.y + " ");
		  	
			
			if((transFormClickPos.x > elemetPosition.x && transFormClickPos.x < elemetPosition.x + elemetSize.x)
			    &&( transFormClickPos.y > elemetPosition.y && transFormClickPos.y < elemetPosition.y + elemetSize.y) )
			{
			  	//System.out.println("onMousePressed " + this.myParent.getId());
				mouseButtons[pressedButton] = true;
			}
			
		}
		
		
	}
	
	public void onMouseReleased(Vec2d clickPos, int releasedButton)
	{
		mouseButtons[releasedButton] = false;
		//System.out.println("onMouseReleased " + mouseButtons[releasedButton]);
		//System.out.println(mouseButtons[releasedButton]);
	}
	
	public void onMouseWheelMoved(Vec2d value)
	{
		
	}
	
	public void onMouseDragged(Vec2d currentMousePosition, int button)
	{
		if(mouseButtons[0] && button == 0)
		{
			//is dragging this entity
		
			TransformComponent transform = (TransformComponent) this.myParent.getComponent(ComponetContants.transform);
			Vec2d currentMousePosGameSpace = this.myParent.getGameWorld().screenToGameTransform(currentMousePosition);
			Vec2d lastMousePositionGameSpace = this.myParent.getGameWorld().screenToGameTransform(this.myLastPosition);
			
			Vec2d delta = currentMousePosGameSpace.minus(lastMousePositionGameSpace);
			transform.setPosition(transform.getPosition().plus(delta));
			myLastPosition = currentMousePosition;
			
		}
	}

	public Vec2d getPosition() {
		return myPosition;
	}

	public void setPosition(Vec2d myPosition) {
		this.myPosition = myPosition;
	}
	
	public boolean isRightPressed()
	{
		return mouseButtons[0];
	}
	
	public boolean isLeftPressedd()
	{
		return mouseButtons[1];
	}
	
	public boolean isMiddlePressed()
	{
		return mouseButtons[2];
	}
	
	public void processEvent(Event e)
	{
		if(e.type.equals(EventsConstants.MouseMoved))
		{
			onMouseMoved(e.vec2dValue);
		}
		else if(e.type.equals(EventsConstants.MouseButtonClick))
		{
			onMouseClicked(e.vec2dValue,e.intValue);
		}
		else if(e.type.equals(EventsConstants.MouseButtonPressed))
		{
			onMousePressed(e.vec2dValue,e.intValue);
		}
		else if(e.type.equals(EventsConstants.MouseButtonReleased))
		{
			onMouseReleased(e.vec2dValue,e.intValue);
		}
		else if(e.type.equals(EventsConstants.MouseWheelMoved))
		{
			onMouseWheelMoved(e.vec2dValue);
		}
		else if(e.type.equals(EventsConstants.MouseDragged))
		{
			onMouseDragged(e.vec2dValue, e.intValue);
		}
	}

	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		Component clone = ComponentFactory.getInstance().createComponent(this.myName);
		clone.myParent =this.myParent;
		return clone;
	}
}
