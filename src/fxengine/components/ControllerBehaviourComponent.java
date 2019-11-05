package fxengine.components;

import org.w3c.dom.Element;

import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
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
		//TransformComponent transformComponent = (TransformComponent)this.myParent.getComponent(ComponetContants.transform);
		///MouseControllerBehaviorComponent mouseComponent = (MouseControllerBehaviorComponent)this.myParent.getComponent(ComponetContants.mouseEvents);
		//if(transformComponent != null && mouseComponent !=null)
		//{
			//MouseEventComponent mouseComponent = (MouseEventComponent)this.myParent.getComponent(ComponetContants.mouseEvents);
			
		//}
		
		
		
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

	@Override
	public Element saveState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadState() {
		// TODO Auto-generated method stub
		
	}


}
