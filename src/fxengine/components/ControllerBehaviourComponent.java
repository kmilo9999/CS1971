package fxengine.components;

import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;

public class ControllerBehaviourComponent extends Component{

	
	
	public ControllerBehaviourComponent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		
		
	}

	private void checkDragged() {
		// TODO Auto-generated method stub
		
	}

	private void checkClicked() {
		// TODO Auto-generated method stub
		TransformComponent transformComponent = (TransformComponent)this.myParent.getComponent(ComponetContants.transform);
		if(transformComponent != null)
		{
			MouseEventComponent mouseComponent = (MouseEventComponent)this.myParent.getComponent(ComponetContants.mouseEvents);
			Vec2d mousePositionScreenSpace = mouseComponent.getPosition();	
			Affine affineTransformation = this.myParent.getGameWorld().getAffineTransform();
			affineTransformation.appendTranslation(-this.myParent.getGameWorld().getPanelScreenViewPortUpperLeft().x , -this.myParent.getGameWorld().getPanelScreenViewPortUpperLeft().y);
			affineTransformation.appendScale(1/this.myParent.getGameWorld().getViewportScaleFactor(), 1/this.myParent.getGameWorld().getViewportScaleFactor());
			affineTransformation.appendTranslation(this.myParent.getGameWorld().getPanelGameViewPort().x , this.myParent.getGameWorld().getPanelGameViewPort().y);
			
			
			
		}
		
		
		
	}

	private void checkOnHover() {
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
	public Component clone() {
		// TODO Auto-generated method stub
		return new ControllerBehaviourComponent(this.myName);
	}

}
