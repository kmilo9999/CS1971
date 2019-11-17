package fxengine.scene;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fxengine.application.GameApplication;
import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.event.Event;
import fxengine.event.EventsConstants;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import fxengine.objects.GameWorld;
import fxengine.system.AiSystem;
import fxengine.system.AnimationSystem;
import fxengine.system.BaseGameSystem;
import fxengine.system.CollisionSystem;
import fxengine.system.EmitterSystem;
import fxengine.system.GraphicsSystem;
import fxengine.system.KeyboardEventSystem;
import fxengine.system.MouseEventSystem;
import fxengine.system.PhysicsSystem;
import fxengine.system.TickableSystem;
import fxengine.system.TransformSystem;
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
	//protected BaseGameSystem myCollSystem = new CollisionSystem();
	protected BaseGameSystem myAnimationSystem = new AnimationSystem();
	protected BaseGameSystem myPhysicsSystem = new PhysicsSystem();
	protected BaseGameSystem myTransformSystem = new TransformSystem();
	protected BaseGameSystem myAISystem = new AiSystem();
	protected BaseGameSystem myTickableSystem = new TickableSystem();
	protected BaseGameSystem myPatriclesSystem = new EmitterSystem();
	
	
	
	protected String myFileToLoad;

	public GameWorldScene(String name, GameApplication application) {
		super(name, application);
		// TODO Auto-generated constructor stub
		this.myGameWorld = new GameWorld();
	}
	
	public GameWorldScene(String name, GameApplication application, GameWorld gameworld) {
		super(name, application);
		// TODO Auto-generated constructor stub
		this.myGameWorld = gameworld;
	}
	
	@Override
	public void initScene() {
		// TODO Auto-generated method stub
		
		this.mySceneCleared = true;
		this.mySceneInitializing = true;
		this.myGameWorld.addSystem(ComponentContants.tickable, myTickableSystem);
		this.myGameWorld.addSystem(ComponentContants.transform, myTransformSystem);
		this.myGameWorld.addSystem(ComponentContants.graphics,myGhrapicsSystem);
		this.myGameWorld.addSystem(ComponentContants.mouseEvents,myMouseSystem);
		this.myGameWorld.addSystem(ComponentContants.keyEvents,myKeyBoardSystem);
		//this.myGameWorld.addSystem(ComponentContants.collision,myCollSystem);
		this.myGameWorld.addSystem(ComponentContants.animation, myAnimationSystem);
		this.myGameWorld.addSystem(ComponentContants.physics, myPhysicsSystem);
		this.myGameWorld.addSystem(ComponentContants.AI, myAISystem);
		this.myGameWorld.addSystem(ComponentContants.particles,myPatriclesSystem);
		
		
		this.myGameWorld.initialize();
		
		///----------- Camera
		GameObject camera = new GameObject("Camera");
		Component cameraMouseControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.cameraControllerMouseEvents);
		Component cameraKeyControllerComponent =  ComponentFactory.getInstance().createComponent(ComponentContants.cameraControllerKeyEvents);
		camera.addComponent(cameraMouseControllerComponent);
		camera.addComponent(cameraKeyControllerComponent);
		
		this.myGameWorld.setSceneCamera(camera);
		///----------------------------------
		
		super.initScene();
		this.mySceneInitializing = true;
		this.mySceneRunning = true;
		this.mySceneCleared = false;
	}
	
	@Override
	public void onTick(long nanosSincePreviousTick)
	{
		if(this.mySceneRunning)
		{
			myGameWorld.update(nanosSincePreviousTick);
			super.onTick(nanosSincePreviousTick);
		}
		if(this.mySceneIsDestroying)
		{
			this.mySceneRunning = false;
			this.mySceneIsDestroying = false;
			this.mySceneCleared = true;
		}
		
	}
	
	@Override
	public void cleanScene() {
		if(this.mySceneRunning)
		{
			myGameWorld.destroyGameObjects();
			super.cleanScene();
			this.mySceneIsDestroying = true;
		}
		
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
			 event = new Event(EventsConstants.KeyReleased,KeyboardEventSystem.SHIFT_KEY);
		}
		else if(ke.getCode() == KeyCode.CONTROL)
		{
			event = new Event(EventsConstants.KeyReleased,KeyboardEventSystem.CONTROL_KEY);
		}
		else if(ke.getCode() == KeyCode.ALT)
		{
			event = new Event(EventsConstants.KeyReleased,KeyboardEventSystem.ALT_KEY);
		}
		else if(ke.getCode() == KeyCode.SPACE)
		{
			event = new Event(EventsConstants.KeyReleased,KeyboardEventSystem.SPACEBAR_KEY);
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
		else if(ke.getCode() == KeyCode.SPACE)
		{
			event = new Event(EventsConstants.KeyPressed,KeyboardEventSystem.SPACEBAR_KEY);
		}
		else
		{
			 event = new Event(EventsConstants.KeyPressed,ke.getText());	
		}
		
		myKeyBoardSystem.onNotify(event);
		super.onKeyPressed(ke);
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

	@Override
	public Element saveState() {
		// TODO Auto-generated method stub
		Element scene = doc.createElement("GameWorldScene");
		scene.setAttribute("name", this.mySceneName);
		for(int i =0; i< this.myGameWorld.numLayers ; i++)
		{
			List<GameObject> gameObjectsPerLayer =this.myGameWorld.getGameObjectsByLayer(i);
			
			if(!gameObjectsPerLayer.isEmpty())
			{
				Element layer = doc.createElement("Layer");
				layer.setAttribute("order", "" +i);
				
				for(GameObject gameObject:gameObjectsPerLayer)
				{
					Element gameObjectState = gameObject.saveState();
					layer.appendChild(gameObjectState);
				}
				scene.appendChild(layer);
			}	
			
		}
		
		
		doc.appendChild(scene);
		
		 Transformer tr;
		try {
			tr = TransformerFactory.newInstance().newTransformer();
			 tr.setOutputProperty(OutputKeys.INDENT, "yes");
	         tr.setOutputProperty(OutputKeys.METHOD, "xml");
	         tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	         tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	         
	         // send DOM to file
	         tr.transform(new DOMSource(doc), 
	                              new StreamResult(new File(this.mySceneName+".xml")));
		} catch (TransformerConfigurationException | TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
		return null;
	}

	
	public void loadState(String filepath) {
		// TODO Auto-generated method stub
		myFileToLoad = filepath;
		
		Document doc;
		try {
			doc = docBuilder.parse(myFileToLoad);
			doc.getDocumentElement().normalize();
			
			NodeList nodeList = doc.getChildNodes();
			
			if(nodeList.getLength() > 0)
			{
				Node tempNode = nodeList.item(0);
				this.loadState(tempNode);	
			}
			System.out.println(filepath+" Loaded completed");
			
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void loadState(Node node) {
		// TODO Auto-generated method stub
		
		if (node.hasChildNodes()) {
			  
			NodeList nodeList = node.getChildNodes();
			 for (int count = 0; count < nodeList.getLength(); count++) {

					Node tempNode = nodeList.item(count);
					// make sure it's element node.
					if (tempNode.getNodeType() == Node.ELEMENT_NODE
							&&  tempNode.getNodeName() == "Layer") {
						
						NamedNodeMap nodeMap = tempNode.getAttributes();
						Node layerOrder = nodeMap.item(0);
						String layerNumber = layerOrder.getNodeValue();
						int layerInt = Integer.parseInt(layerNumber);
						
						NodeList layerList = tempNode.getChildNodes();
						
						
						 for (int i = 0; i < layerList.getLength(); i++) {
						     Node gameObjectTag = layerList.item(i);
						     if(gameObjectTag.getNodeType() == Node.ELEMENT_NODE 
						    		 && gameObjectTag.getNodeName() == "GameObject")
						     {
						    	 GameObject go = GameObject.buildGameObject(gameObjectTag);
						    	 System.out.println("GameObject " + go.getId()+" completed");
						    	 
								 this.myGameWorld.addGameObject(go, layerInt);	 
						     }
							 
						 }
						
						
						
						//this.myGameWorld.addGameObject(go, 0);
					}
			 }
			
		}
		
	}

	
	

}
