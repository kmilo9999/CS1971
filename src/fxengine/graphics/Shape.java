package fxengine.graphics;

import fxengine.UISystem.UIConstants;
import fxengine.UISystem.UIElement;
import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Base Shape class to set common attributes of shapes.
 * In this case the color
 * @author cdiaz8
 *
 */

public abstract class Shape  {

	protected Vec2d myPosition;
    protected Vec2d mySize;
	protected Color myColor = UIConstants.BLACK ;
	
	
	public void init()
	{
		
	}
	
	public abstract void onDraw(GraphicsContext g);
	
	
	public void onResize(Vec2d newSize) {
		// TODO Auto-generated method stub
		
	}

	public void setColor(Color color) {
		this.myColor = color;
	}
	public Vec2d getPosition() {
		return myPosition;
	}

	public void setPosition(Vec2d myPostision) {
		this.myPosition = myPostision;
	}

	public Vec2d getSize() {
		return mySize;
	}

	public void setSize(Vec2d mySize) {
		this.mySize = mySize;
	}

}
