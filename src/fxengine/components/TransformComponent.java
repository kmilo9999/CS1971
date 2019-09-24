package fxengine.components;

import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;

public class TransformComponent extends Component{

	private Vec2d myPosition = new Vec2d(0);
	private Vec2d myRotation;
	private Vec2d myScale  = new Vec2d(1);
	
	
	public TransformComponent(String name) {
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

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	public Vec2d getPosition() {
		return myPosition;
	}

	public void setPosition(Vec2d position) {
		this.myPosition = position;
	}

	public Vec2d getRotation() {
		return myRotation;
	}

	public void setRotation(Vec2d rotation) {
		this.myRotation = rotation;
	}

	public Vec2d getScale() {
		return myScale;
	}

	public void setScale(Vec2d scale) {
		this.myScale = scale;
	}

	@Override
	public void draw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Component clone() {
		Component clone = ComponentFactory.getInstance().createComponent(this.myName);
		clone.myParent =this.myParent;
		return clone;
	}

	


}
