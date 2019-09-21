package game.tic;

import fxengine.UISystem.Layout;
import fxengine.UISystem.PaintedButton;
import fxengine.UISystem.UIConstants;
import fxengine.UISystem.UIElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class TicTokenLayout extends Layout{

	private TicPlayScene myScene;
	private String myId;
	private boolean isWinMove = false;
	private Color myNoHoveredColor;
	
	

	public TicTokenLayout(String id, double x, double y, double w, double h, TicPlayScene scene) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
		this.myScene = scene;
		this.myId = id;
	}
	
	public TicTokenLayout(String id,double x, double y, double w, double h, TicPlayScene scene, Color background) {
		super(x, y, w, h,background);
		// TODO Auto-generated constructor stub
		this.myScene = scene;
		this.myId = id;
		this.myNoHoveredColor = background;
	}
	

	@Override
	public void onDraw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub
		
		this.myColor = this.myIsHoverd? UIConstants.GRAYGREEN:this.myNoHoveredColor;
		super.onDraw(graphicsCx);
	}
	
	@Override
	 public void onMouseClicked(MouseEvent e) {
		
		
		if(this.myScene.getCurrentState().equals(TicPlayScene.STATE_PLAYING)  && !this.myScene.isGameOver() && myChildren.isEmpty())
		{
			UIElement button;
			if(this.myScene.currentPlayerMoving.getName().equals("Player1") )
			{
				button = new PaintedButton(1, 1,UIConstants.SHAPE_X,UIConstants.TRANSPARENT);
				
			}else
			{
				button = new PaintedButton(1, 1,UIConstants.SHAPE_CIRCLE,UIConstants.TRANSPARENT);
			}
			
			this.addChildElement(button);
			this.myScene.setPlayerMove(this);
		}

	}
	
	public String getId() {
		return myId;
	}

	public void setMyId(String myId) {
		this.myId = myId;
	}
	
	public boolean isWinMove() {
		return isWinMove;
	}

	public void setWinMove(boolean isWinMove) {
		this.isWinMove = isWinMove;
		PaintedButton button = (PaintedButton)this.myChildren.get(0);
		button.setShapeColor(UIConstants.RED);
		
	}
}
