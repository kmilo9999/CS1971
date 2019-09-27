package fxengine.components;

import fxengine.event.Event;
import fxengine.event.EventsConstants;
import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;


public class MouseControllerBehaviorComponent extends MouseEventComponent {

	

	
	public MouseControllerBehaviorComponent(String name) {
		super(name);
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		//myLastPosition = new Vec2d(0);
		//myPosition = new Vec2d(0);
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
	
	public void mouseMoved(Vec2d position)
	{
		//myLastPosition = myPosition;
		//myPosition = position;	
		//System.out.println("MOUSE MOVE "+myPosition.x +" "+ myPosition.y);
	}
	
	public void mouseClicked(Vec2d clickPosition,int clickedButton)
	{
		
		/*TransformComponent transform = (TransformComponent) this.myParent.getComponent(ComponetContants.transform);
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
		}*/
		
		
	}
	
	public void mousePressed(Vec2d clickPosition, int pressedButton)
	{
		
		SpriteComponent sprite = ((GraphicsComponent) this.myParent.getComponent(ComponentContants.graphics))
				.getSprite();

		Vec2d elemetSize = sprite.getSize();
		Vec2d elemetPosition = sprite.getPosition();
		Vec2d transFormClickPos = this.myParent.getGameWorld().screenToGameTransform(clickPosition);

		if ((transFormClickPos.x > elemetPosition.x && transFormClickPos.x < elemetPosition.x + elemetSize.x)
				&& (transFormClickPos.y > elemetPosition.y && transFormClickPos.y < elemetPosition.y + elemetSize.y)) {
			// System.out.println("onMousePressed " + this.myParent.getId());
			this.myParent.setSelected(true);
		} else {
			this.myParent.setSelected(false);
		}
		
		
	}
	
	public void mouseReleased(Vec2d clickPos, int releasedButton)
	{
		
		//mouseButtons[releasedButton] = false;
		//System.out.println("onMouseReleased " + mouseButtons[releasedButton]);
		//System.out.println(mouseButtons[releasedButton]);
	}
	
	public void mouseWheelMoved(Vec2d value)
	{
		
	}
	
	public void mouseDragged(Vec2d currentMousePosition, int button)
	{
	
		
		if(mouseButtons[0] && button == 0 && this.myParent.isSelected())
		{
			//is dragging this entity
		   
			TransformComponent transform = (TransformComponent) this.myParent.getComponent(ComponentContants.transform);
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
		super.processEvent(e);
		
	
		if(e.type.equals(EventsConstants.MouseButtonClick))
		{
			mouseClicked(e.vec2dValue,e.intValue);
		}
		else if(e.type.equals(EventsConstants.MouseButtonPressed))
		{
			mousePressed(e.vec2dValue,e.intValue);
		}
		else if(e.type.equals(EventsConstants.MouseButtonReleased))
		{
			mouseReleased(e.vec2dValue,e.intValue);
		}
		else if(e.type.equals(EventsConstants.MouseWheelMoved))
		{
			mouseWheelMoved(e.vec2dValue);
		}
		else if(e.type.equals(EventsConstants.MouseDragged))
		{
			mouseDragged(e.vec2dValue, e.intValue);
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
