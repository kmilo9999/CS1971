package fxengine.components;

import fxengine.event.Event;
import fxengine.event.EventsConstants;
import fxengine.math.Vec2d;
import fxengine.system.KeyboardEventSystem;
import javafx.scene.canvas.GraphicsContext;

public class CameraMouseControllerBehavior extends MouseEventComponent {

	
	private boolean myMouseInViewPort = false; 
	private double myZoomFactor = 1.25;
	
	
	public CameraMouseControllerBehavior(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	
	}

	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
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

	@Override
	public void processEvent(Event e) {
		// TODO Auto-generated method stub
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

	private void mouseDragged(Vec2d currentMousePosition, int intValue) {
		// TODO Auto-generated method stub
		
		if(myMouseInViewPort)
		{
			
			CameraKeyControllerBehavior keyController = (CameraKeyControllerBehavior)this.myParent.getComponent(ComponentContants.cameraControllerKeyEvents);
			if(keyController != null)
			{
				if(keyController.getSpecialKeyState(KeyboardEventSystem.CONTROL_KEYCODE))
				{
					Vec2d gameUpLeft = this.myParent.getGameWorld().getPanelGameViewPortTopLeft();
					
					Vec2d currentMousePosGameSpace = this.myParent.getGameWorld().screenToGameTransform(currentMousePosition);
					Vec2d lastMousePositionGameSpace = this.myParent.getGameWorld().screenToGameTransform(this.myLastPosition);
					Vec2d delta = currentMousePosGameSpace.minus(lastMousePositionGameSpace);
					this.myParent.getGameWorld().setPanelGameViewPort(gameUpLeft.plus(delta));
					
					this.myParent.getGameWorld().deltax = gameUpLeft.x;
					this.myParent.getGameWorld().deltay = gameUpLeft.y;
					//System.out.println(this.myParent.getGameWorld().getPanelGameViewPort());
					
					myLastPosition = currentMousePosition;
					
				}
			}
		}
	
		
		
	}

	private void mouseWheelMoved(Vec2d vec2dValue) {
		// TODO Auto-generated method stub
		//System.out.println();
		
		if(myMouseInViewPort )
		{
			//myZoomFactor = vec2dValue.y>0 ? myZoomFactor++:myZoomFactor--;
			double currentGameZoom = this.myParent.getGameWorld().getViewportScaleFactor();	
			double newGameWorldZoom = vec2dValue.y > 0 ? currentGameZoom * myZoomFactor
					: currentGameZoom / myZoomFactor;

			this.myParent.getGameWorld().setViewportScaleFactor(newGameWorldZoom);

		}
	}

	private void mouseReleased(Vec2d vec2dValue, int intValue) {
		// TODO Auto-generated method stub
		
	}

	private void mousePressed(Vec2d vec2dValue, int intValue) {
		// TODO Auto-generated method stub
		
	}

	private void mouseClicked(Vec2d vec2dValue, int intValue) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void onMouseMoved(Vec2d position)
	{
		Vec2d viewPortPos = this.myParent.getGameWorld().getPanelScreenViewPortUpperLeft();
		Vec2d viewPortSize = this.myParent.getGameWorld().getPanelScreenViewPortSize();
		if ((position.x > viewPortPos.x && position.x < viewPortPos.x + viewPortSize.x)
				&& (position.y > viewPortPos.y && position.y < viewPortPos.y + viewPortSize.y)) {
			myMouseInViewPort = true;
		} else {
			myMouseInViewPort = false;
		}

		super.onMouseMoved(position);

	}

	public boolean isMouseInViewPort() {
		return myMouseInViewPort;
	}

	public void setMouseInViewPort(boolean mouseInViewPort) {
		this.myMouseInViewPort = mouseInViewPort;
	}
   
  
	
}
