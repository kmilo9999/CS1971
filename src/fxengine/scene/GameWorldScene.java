package fxengine.scene;



import fxengine.application.FXFrontEnd;
import fxengine.components.ComponentContants;
import fxengine.event.Event;
import fxengine.event.EventsConstants;
import fxengine.math.Vec2d;
import fxengine.objects.GameWorld;
import fxengine.system.BaseGameSystem;
import fxengine.system.CollisionSystem;
import fxengine.system.GraphicsSystem;
import fxengine.system.KeyboardEventSystem;
import fxengine.system.MouseEventSystem;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class GameWorldScene extends BaseScene{
	
	protected GameWorld myGameWorld;
	protected MouseEventSystem myMouseSystem = new MouseEventSystem();
	protected KeyboardEventSystem myKeyBoardSystem = new KeyboardEventSystem();
	protected BaseGameSystem myGhrapicsSystem = new GraphicsSystem();
	protected BaseGameSystem myCollSystem = new CollisionSystem();
	

	public GameWorldScene(String name, FXFrontEnd application) {
		super(name, application);
		// TODO Auto-generated constructor stub
		this.myGameWorld = new GameWorld();
	}
	
	public GameWorldScene(String name, FXFrontEnd application, GameWorld gameworld) {
		super(name, application);
		// TODO Auto-generated constructor stub
		this.myGameWorld = gameworld;
	}
	
	@Override
	public void initScene() {
		// TODO Auto-generated method stub

		this.myGameWorld.addSystem(ComponentContants.graphics,myGhrapicsSystem);
		this.myGameWorld.addSystem(ComponentContants.mouseEvents,myMouseSystem);
		this.myGameWorld.addSystem(ComponentContants.keyEvents,myKeyBoardSystem);
		this.myGameWorld.addSystem(ComponentContants.collision,myCollSystem);
		this.myGameWorld.initialize();
		
		
		super.initScene();
	}
	
	@Override
	public void onTick(long nanosSincePreviousTick)
	{
		myGameWorld.update(nanosSincePreviousTick);
		super.onTick(nanosSincePreviousTick);
	}
	
	/**
	 * Called when the screen needs to be redrawn. This is at least once per tick, but possibly
	 * more frequently (for example, when the window is resizing).
	 * <br><br>
	 * Note that the entire drawing area is cleared before each call to this method. Furthermore,
	 * {@link #onResize} is guaranteed to be called before the first invocation of onDraw.
	 * 
	 * @param graphicsContext		a {@link GraphicsContext} object used for drawing.
	 */
	
	@Override
	public void onDraw(GraphicsContext graphicsContext)
	{
		myGameWorld.draw(graphicsContext);
		super.onDraw(graphicsContext);
	}
	
	@Override
	public void onMouseMoved(MouseEvent e) {
		Event event = new Event(EventsConstants.MouseMoved,new Vec2d(e.getX(),e.getY()));
		myMouseSystem.onNotify(event);
		super.onMouseMoved(e);
	}
	
	@Override
	public void onMouseClicked(MouseEvent e)
	{
		int button = mapMouseButtonToInt(e.getButton());
			
		Event event = new Event(EventsConstants.MouseButtonClick,new Vec2d(e.getX(),e.getY()) ,button);
		myMouseSystem.onNotify(event);
		super.onMouseClicked(e);
	}
	
	@Override
	public void onMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int button = mapMouseButtonToInt(e.getButton());
		
		Event event = new Event(EventsConstants.MouseButtonPressed,new Vec2d(e.getX(),e.getY()) ,button);
		myMouseSystem.onNotify(event);
		super.onMousePressed(e);
	}

	@Override
	public void onMouseReleased(MouseEvent e) {
		
		int button = mapMouseButtonToInt(e.getButton());
		// TODO Auto-generated method stub
		Event event = new Event(EventsConstants.MouseButtonReleased,new Vec2d(e.getX(),e.getY()) ,button);
		myMouseSystem.onNotify(event);
		super.onMouseReleased(e);
	}

	@Override
	public void onMouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		int button = mapMouseButtonToInt(e.getButton());
		Event event = new Event(EventsConstants.MouseDragged,new Vec2d(e.getX(),e.getY()),button);
		myMouseSystem.onNotify(event);
		super.onMouseDragged(e);
	}
	
	@Override
	public void onMouseWheelMoved(ScrollEvent e) {
		// TODO Auto-generated method stub
		Event event = new Event(EventsConstants.MouseWheelMoved,new Vec2d(e.getDeltaX(),e.getDeltaY()));
		myMouseSystem.onNotify(event);
	}
	
	@Override
	public void onKeyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		Event event = new Event(EventsConstants.KeyTyped,e.getCharacter());
		myKeyBoardSystem.onNotify(event);
	}
	
	@Override
	public void onKeyReleased(KeyEvent ke) {
		// TODO Auto-generated method stub
		Event event;
		if(ke.getCode() == KeyCode.SHIFT)
		{
			 event = new Event(EventsConstants.KeyReleased,"SHIFT");
		}
		else if(ke.getCode() == KeyCode.CONTROL)
		{
			event = new Event(EventsConstants.KeyReleased,"CONTROL");
		}
		else if(ke.getCode() == KeyCode.ALT)
		{
			event = new Event(EventsConstants.KeyReleased,"ALT");
		}
		else
		{
			 event = new Event(EventsConstants.KeyReleased,ke.getText());	
		}
		myKeyBoardSystem.onNotify(event);
	}
	
	@Override
	public void onKeyPressed(KeyEvent ke) {
		// TODO Auto-generated method stub
		Event event;
		if(ke.getCode() == KeyCode.SHIFT)
		{
			 event = new Event(EventsConstants.KeyPressed,KeyboardEventSystem.SHIFT_KEY);
		}else if(ke.getCode() == KeyCode.CONTROL)
		{
			event = new Event(EventsConstants.KeyPressed,KeyboardEventSystem.CONTROL_KEY);
		}
		else if(ke.getCode() == KeyCode.ALT)
		{
			event = new Event(EventsConstants.KeyPressed,KeyboardEventSystem.ALT_KEY);
		}
		else
		{
			 event = new Event(EventsConstants.KeyPressed,ke.getText());	
		}
		
		myKeyBoardSystem.onNotify(event);
	}
	
    public GameWorld getGameWorld() {
		return myGameWorld;
	}

	public void setGameWorld(GameWorld myGameWorld) {
		this.myGameWorld = myGameWorld;
	}
	
	private int mapMouseButtonToInt(MouseButton mb)
	{		
		if( mb == MouseButton.PRIMARY)
		{
			return 0;
		}
		else if(mb== MouseButton.SECONDARY)
		{
			return 1;
		}
		else if(mb == MouseButton.MIDDLE)
		{
			return 1;
		}
		
		return -1;
	}
	
	
	
	

}
