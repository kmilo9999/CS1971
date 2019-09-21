package fxengine.UISystem;

import fxengine.math.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class UIButton extends UIElement {

	protected Color myBorder = UIConstants.BLUE;
    
	
	public UIButton() {

	}

	public void onTick(long nanosSincePreviousTick) {
		super.onTick(nanosSincePreviousTick);
	}

	public void onDraw(GraphicsContext graphicsCx) {
        graphicsCx.setStroke(myBorder);
        if(this.myIsHoverd)
        {
        	graphicsCx.setFill(UIConstants.GRAYBLUE);
        	graphicsCx.fillRect(this.myPosition.x ,this.myPosition.y,this.mySize.x + 5.5,this.mySize.y + 5.5);
        }
        else
        {
        	graphicsCx.strokeRect(this.myPosition.x ,this.myPosition.y,this.mySize.x + 5.5,this.mySize.y + 5.5);	
        }
		
		super.onDraw(graphicsCx);
	}

	public void onResize(Vec2d newSize) {
		super.onResize(newSize);
	}

	public void init() {
		super.init();
	}

	
}
