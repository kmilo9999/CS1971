package game.wiz;

import fxengine.components.Component;
import fxengine.components.ComponentContants;
import fxengine.components.ComponentFactory;
import fxengine.components.GraphicsComponent;
import fxengine.components.TerrainComponent;
import fxengine.math.Vec2d;
import fxengine.objects.GameObject;

public class TileMap {

	private double mapWidth;
	private double mapHeight;
	private int numTilesX;
	private int numTilesY;
	
	private Tile[][] tileMap;
	private int[][] intTileMap;
	
	public TileMap( double width, double height, int numTilesX,int numTilesY) {
		
		// TODO Auto-generated constructor stub
		this.mapWidth = width;
		this.mapHeight = height;
		this.numTilesX = numTilesX;
		this.numTilesY =  numTilesY;
		tileMap = new Tile[numTilesX][numTilesY];
		intTileMap = new int[numTilesX][numTilesY];
		for(int i = 0 ; i < 0 ; i++) 
		 {
			 
			 for(int j = 0 ; j < 1 ; j++)
			 {
				 intTileMap[i][j] = 0;
			 }
				
		}
		
		buildMap();
	}
    
	 
	 public void buildMap()
     {
		  Vec2d texCoord1 = new Vec2d(0);
		  Vec2d texCoord2 = new Vec2d(64,0);
		  Vec2d texCoord3 = new Vec2d(0);
		 
		 // build procedural map
		 
		 
		 double xTileSize =  this.mapWidth / this.numTilesX;
		 double yTileSize =  this.mapHeight / this.numTilesY;
		 
		 //random map first pass
		 int random = (int)(Math.random() * this.numTilesX + 1);
		 for(int i = 0 ; i < this.numTilesX ; i++) 
		 {
			 
			 for(int j = 0 ; j < this.numTilesY ; j++)
			 {
				 if(i == random) 
				 {
					 intTileMap[i][j] = 1;
				 }
			 }
		 }
		 
		 //random map second pass
		 int random1 =  (int)(Math.random() * ((random+1)));
		 
		 for(int i = 0 ; i < random ; i++) 
		 {
			 for(int j = 0 ; j < this.numTilesY; j++)
			 {
				 if(j == random1) 
				 {
					 intTileMap[i][j] = 1;
				 }
			 }
		 }
		 
		 int random2 = (random ) + (int)(Math.random() * (((this.numTilesX) - random) + 1));
		 

		 for(int i = random ; i < this.numTilesX ; i++) 
		 {
			 for(int j = 0 ; j < this.numTilesY ; j++)
			 {
				 if(j == random2) 
				 {
					 intTileMap[i][j] = 1;
				 }
			 }
		 }
		 
		 Vec2d topLeft = new Vec2d(0);
		 for(int i = 0 ; i < this.numTilesX ; i++) 
		 {
			 double xPos = topLeft.x + (i * 32);
			 
			 for(int j = 0 ; j < this.numTilesY ; j++)
			 {
				double yPos = topLeft.y + (j * 36);

				boolean isStatic = intTileMap[i][j] == 1 ? true : false;

				tileMap[i][j] = new Tile("tile" + i +""+ j, intTileMap[i][j], new Vec2d(xPos, yPos), texCoord1,isStatic);	

			 }
		 }
			 
		
	 }

	public Tile[][] getTileMap() {
		return tileMap;
	}

	public void setTileMap(Tile[][] tileMap) {
		this.tileMap = tileMap;
	}


	public double getMapWidth() {
		return mapWidth;
	}


	public void setMapWidth(double mapWidth) {
		this.mapWidth = mapWidth;
	}


	public double getMapHeight() {
		return mapHeight;
	}


	public void setMapHeight(double mapHeight) {
		this.mapHeight = mapHeight;
	}


	public int getNumTilesX() {
		return numTilesX;
	}


	public void setNumTilesX(int numTilesX) {
		this.numTilesX = numTilesX;
	}


	public int getNumTilesY() {
		return numTilesY;
	}


	public void setNumTilesY(int numTilesY) {
		this.numTilesY = numTilesY;
	}
	
}
