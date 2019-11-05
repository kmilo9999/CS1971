package fxengine.components;

import java.util.Map;

import org.w3c.dom.Element;

import fxengine.UISystem.UIConstants;
import fxengine.manager.Resource;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;

public class TerrainComponent extends Component{

	private double myWidth = 0;
	private double myHeight = 0;
	private Vec2d myNumTiles = new Vec2d(0) ;
	
	private Vec2d panelScreenViewPortSize;
	private Vec2d panelScreenViewTopLeft;
	private Vec2d gameWorldTopLeft;
		
	
	
	private Map<Integer,Vec2d> myTextureCoordinates;
	
	private int debug = 0;
	
	public TerrainComponent(String name) {
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
		if(!isInitialized)
		{
			panelScreenViewPortSize = this.myParent.getGameWorld().getPanelScreenViewPortSize();
			panelScreenViewTopLeft = this.myParent.getGameWorld().getPanelScreenViewPortUpperLeft();
			gameWorldTopLeft =  this.myParent.getGameWorld().getPanelGameViewPortTopLeft();
		//	grid = new Tile [(int) this.myNumTiles.x][(int) this.myNumTiles.y];
			
			
			isInitialized = true;
		}
	}

	private void buildTerrain() {
		// TODO Auto-generated method stub
		
		int randomY = (int)(Math.random() * this.myNumTiles.y + 1);
		
		for(int i = 0 ; i < this.myNumTiles.x; i++)
		{
			for(int j = 0 ; j < this.myNumTiles.y; j++)
			{
				if(j == randomY)
				{
					//grid[i][j] = new Tile(1);		
				}
				else 
				{
					//grid[i][j] = new Tile(0);
				}
			}
		}
	}

	@Override
	public void update(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		panelScreenViewPortSize = this.myParent.getGameWorld().getPanelScreenViewPortSize();
		panelScreenViewTopLeft = this.myParent.getGameWorld().getPanelScreenViewPortUpperLeft();
		gameWorldTopLeft =  this.myParent.getGameWorld().getPanelGameViewPortTopLeft();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(GraphicsContext graphicsCx) {
		// TODO Auto-generated method stub
		double xTileSize =  this.myWidth / this.myNumTiles.x;
		double yTileSize =  this.myHeight / this.myNumTiles.y;
		
		if(debug == 0)
		{
			for(int i = 0; i < this.myNumTiles.x ; i++)
			{
				double xPos = gameWorldTopLeft.x + (i * xTileSize);
				double yPos = gameWorldTopLeft.y;
				
				Vec2d lineStart = new Vec2d(xPos,yPos);
				Vec2d lineEnd = new Vec2d(xPos,yPos + panelScreenViewPortSize.y);
				
				
				graphicsCx.setStroke(UIConstants.BLACK);
				graphicsCx.strokeLine(lineStart.x,lineStart.y,lineEnd.x,lineEnd.y);
				
				/*for(int j = 0; j < numTilesY ; j++)
				{
					double yPos = j * this.myTileSize.y;
					
					Vec2d lineStart = new Vec2d(xPos,yPos);
					Vec2d lineEnd = new Vec2d(xPos + panelScreenViewPortSize.x,yPos + panelScreenViewPortSize.y);
					
				}*/
			}
			
			for(int i = 0; i < this.myNumTiles.y ; i++)
			{
				double xPos = gameWorldTopLeft.x ;
				double yPos = gameWorldTopLeft.y + (i * yTileSize);
				
				Vec2d lineStart = new Vec2d(xPos,yPos);
				Vec2d lineEnd = new Vec2d(xPos + + panelScreenViewPortSize.x,yPos );
				
				
				graphicsCx.setStroke(UIConstants.BLACK);
				graphicsCx.strokeLine(lineStart.x,lineStart.y,lineEnd.x,lineEnd.y);
				
				/*for(int j = 0; j < numTilesY ; j++)
				{
					double yPos = j * this.myTileSize.y;
					
					Vec2d lineStart = new Vec2d(xPos,yPos);
					Vec2d lineEnd = new Vec2d(xPos + panelScreenViewPortSize.x,yPos + panelScreenViewPortSize.y);
					
				}*/
			}
		
		}
		
		for (int i = 0; i < this.myNumTiles.x; i++) {
			
			double xPos = gameWorldTopLeft.x + (i * xTileSize);
			
			for (int j = 0; i < this.myNumTiles.y; j++) {
				
				double yPos = gameWorldTopLeft.y + (j * yTileSize);
				
				/*if (grid[i][j].getColor() != 0) {
					Vec2d textCoord = myTextureCoordinates.get(grid[i][j].getColor());
					if (textCoord != null) {
						// textureMap.setMySpriteXCoordinates(textCoord.x);
						// textureMap.setMySpriteYCoordinates(textCoord.y);

					}
				}*/
			}
		}	
        
		
	}

	public double getWidth() {
		return myWidth;
	}

	public void setWidth(double myWidth) {
		this.myWidth = myWidth;
	}

	public double getHeight() {
		return myHeight;
	}

	public void setHeight(double myHeight) {
		this.myHeight = myHeight;
	}

	public Vec2d getNumTiles() {
		return myNumTiles;
	}

	public void setNumTiles(Vec2d myTileSize) {
		this.myNumTiles = myTileSize;
	}

	public Map<Integer, Vec2d> getMyTextureCoordinates() {
		return myTextureCoordinates;
	}

	public void setMyTextureCoordinates(Map<Integer, Vec2d> myTextureCoordinates) {
		this.myTextureCoordinates = myTextureCoordinates;
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
