package fxengine.maploader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import fxengine.math.Vec2d;
import fxengine.math.Vec2i;
import fxengine.objects.GameWorld;
import fxengine.scene.GameWorldScene;
import fxengine.PathFinding.Node;
import fxengine.PathFinding.PathFinding;
import fxengine.manager.Resource;
import fxengine.manager.Resource.ResourceType;
import fxengine.manager.ResourceManager;
import fxengine.maploader.GameTile;

public class GameTileMap {
  
	private String myFilePath;
	private String myTextureMapFilePath;
	private Vec2d myInitialPosition= new Vec2d(0);
	private Vec2d myTileSize= new Vec2d(0);
	private Vec2i myNumFrames= new Vec2i(0);
	private int myWidth=0;
	private int myHeight= 0;
	private Vec2i myNumTiles = new Vec2i(0);
	GameTile[][] myTileMap;
	List<List<Integer>> intTileMap = new ArrayList<List<Integer>>(); 
	private boolean isExternal = false;
	private Vec2d myPlayerInitialPosition;
	
	private GameWorldScene myScene;
	
	private PathFinding myPathFinding;
	
	private boolean myEnablePathFinding = false; 
	
	public GameTileMap(String mapTextFilePath, String textureMapFilePath, int width, int height, Vec2d initialPoisition, Vec2d tileSize, Vec2i numFrames, GameWorldScene scene)
	{
		this.myFilePath = mapTextFilePath;
		this.myScene = scene;
		this.myInitialPosition =initialPoisition;
		this.myNumFrames = numFrames;
		this.myTileSize = tileSize;
		this.myTextureMapFilePath = textureMapFilePath;
		this.myWidth = width;
		this.myHeight = height;
		
	}
	
	
	public void load()
	{
		if(!this.myFilePath.isEmpty())
		{
			 
			 boolean readFile = false;
			 int minY = 1000000;
			
			 Resource resource;
			if(!this.isExternal)
			{
				resource = ResourceManager.getIntance().createOrGetResource(this.myFilePath, ResourceType.InternalTextFile);	
			}
			else
			{
				resource = ResourceManager.getIntance().createOrGetResource(this.myFilePath, ResourceType.ExternalTextFile);
			}
			
			
			
			//new FileReader(getClass().getClassLoader().getResource(this.myFilePath).toString());

            try {
				BufferedReader br = resource.getFileReaader();
				String st; 
				while ((st = br.readLine()) != null)
				{
					
					if(st.length() < minY  )
					{
						minY = st.length();
					} 
					
					List<Integer> intLine = new ArrayList<Integer>(); 
				    for(int i = 0 ; i < st.length();i++)
				    {
				    	if(st.charAt(i) == 'X')
				    	{
				    		intLine.add(99);	
				    	}
				    	else
				    	{
				    		int number = st.charAt(i) - 48;
					    	intLine.add(number);	
				    	}
				    }
				    
				    intTileMap.add(intLine);
				    
					//System.out.println(st);
				    
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Path path = resource.getFileReaader().toPath();
			//System.out.println(path.toString());
 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			String st; 
			
			/*while ((st = br.readLine()) != null)
			{
				
				if(st.length() < minY  )
				{
					minY = st.length();
				} 
				
				List<Integer> intLine = new ArrayList<Integer>(); 
			    for(int i = 0 ; i < st.length();i++)
			    {
			    	intLine.add(st.charAt(i) - 97); 
			    }
			    
			    intTileMap.add(intLine);
			    
				System.out.println(st);
			    
			}*/
			
			readFile = true; 
				 
			if(!readFile)
			{
				return;
			}
			
			
			 Vec2d topLeft = this.myScene.getGameWorld().getPanelGameViewPortTopLeft();
			 
			 myTileMap = new GameTile[this.intTileMap.size()][minY];
			 
			 myNumTiles = new Vec2i(this.intTileMap.size(),minY);
			 System.out.println("numTiles: " + myNumTiles);
			 
			 
			 for(int i = 0 ; i < minY ; i++) 
			 {
				 double xPos = topLeft.x + (i * this.myTileSize.x);
				 
				 for(int j = 0 ; j < this.intTileMap.size() ; j++)
				 {
					double yPos = topLeft.y + (j * this.myTileSize.y);

					String tileId ="tile" + i +""+ j;
					boolean isStatic = false;
					int tileColor = intTileMap.get(j).get(i);
					if(this.intTileMap.get(j).get(i) == 99)
					{
						tileColor = 0;
						this.myPlayerInitialPosition = new Vec2d(xPos, yPos);
					}
					else {
						
						isStatic = tileColor == 1 || tileColor == 2 || 
								tileColor == 4 ? true : false;

					}
						
					
					
					
					GameTile tile = new GameTile(tileId,myTextureMapFilePath,new Vec2d(xPos, yPos));
					if(intTileMap.get(j).get(i) == 2)
					{
						tile.setTag("GOAL");
					}
					if(intTileMap.get(j).get(i) == 4)
					{
						tile.setTag("LIGHT");
					}
					
					
					tile.setColor(tileColor);
					tile.setStatic(isStatic); 
					tile.setTileSize(this.myTileSize);
					tile.setNumFrames(this.myNumFrames);
					tile.setTextCoord(this.myInitialPosition);
					myTileMap[j][i] = tile;

					this.myScene.getGameWorld().addGameObject(myTileMap[j][i], GameWorld.BackLayer);		
					
				 }
			 }
			 
			 
			
			 System.out.println("Map generation completed");
		}
	}

	
	public void loadMap(String mapPath)
	{
		this.myFilePath = mapPath;
		if(!this.myFilePath.isEmpty())
		{
			for(List<Integer> l: intTileMap)
			{
				l.clear();
			}
			intTileMap.clear();
			
			 FileReader fr;
			 boolean readFile = false;
			 int minY = 1000000;
			 
			Resource resource = ResourceManager.getIntance().createOrGetResource(this.myFilePath, ResourceType.InternalTextFile);
			
			
			//new FileReader(getClass().getClassLoader().getResource(this.myFilePath).toString());

            try {
				BufferedReader br = resource.getFileReaader();
				String st; 
				while ((st = br.readLine()) != null)
				{
					
					if(st.length() < minY  )
					{
						minY = st.length();
					} 
					
					List<Integer> intLine = new ArrayList<Integer>(); 
				    for(int i = 0 ; i < st.length();i++)
				    {
				    	int number = st.charAt(i) - 48;
				    	intLine.add(number); 
				    }
				    
				    intTileMap.add(intLine);
				    
					//System.out.println(st);
				    
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Path path = resource.getFileReaader().toPath();
			//System.out.println(path.toString());
 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			String st; 
			
			/*while ((st = br.readLine()) != null)
			{
				
				if(st.length() < minY  )
				{
					minY = st.length();
				} 
				
				List<Integer> intLine = new ArrayList<Integer>(); 
			    for(int i = 0 ; i < st.length();i++)
			    {
			    	intLine.add(st.charAt(i) - 97); 
			    }
			    
			    intTileMap.add(intLine);
			    
				System.out.println(st);
			    
			}*/
			
			readFile = true; 
				 
			if(!readFile)
			{
				return;
			}
			
			
			 Vec2d topLeft = this.myScene.getGameWorld().getPanelGameViewPortTopLeft();
			 
			 //myTileMap = new GameTile[this.intTileMap.size()][minY];
			 
			 myNumTiles = new Vec2i(this.intTileMap.size(),minY);
			 System.out.println("numTiles: " + myNumTiles);
			 
			 
			 for(int i = 0 ; i < minY ; i++) 
			 {
				 
				 
				 for(int j = 0 ; j < this.intTileMap.size() ; j++)
				 {
				

					boolean isStatic = this.intTileMap.get(j).get(i) == 1 ? true : false;

					String tileId ="tile" + i +""+ j;
					if(intTileMap.get(j).get(i) == 2)
					{
						tileId = "GOAL";
					}
					
					myTileMap[j][i].setColor(intTileMap.get(j).get(i));
					
					myTileMap[j][i].setStatic(isStatic);
		
					
				 }
			 }
			 
			 
			
			 System.out.println("Map generation completed");
		}
	}

	public Vec2i getNumTiles() {
		return myNumTiles;
	}


	public void setNumTiles(Vec2i numTiles) {
		this.myNumTiles = numTiles;
	}


	public List<List<Integer>> getIntTileMap() {
		return intTileMap;
	}


	public void setIntTileMap(List<List<Integer>> intTileMap) {
		this.intTileMap = intTileMap;
	}
	
	public GameTile getTile(int x, int y)
	{
		return myTileMap[x][y];
	}
	
	public void changeTileColor(int x, int y, int color)
	{
		myTileMap[x][y].setColor(color);
	}


	public boolean isExternal() {
		return isExternal;
	}


	public void setExternal(boolean isExternal) {
		this.isExternal = isExternal;
	}


	public Vec2d getPlayerInitialPosition() {
		return myPlayerInitialPosition;
	}


	public void setPlayerInitialPosition(Vec2d myPlayerInitialPosition) {
		this.myPlayerInitialPosition = myPlayerInitialPosition;
	}
	
	public Vec2i coordinateToTile(double x, double y)
	{
		return new Vec2i((int)(y / myTileSize.y), (int)(x / myTileSize.x));
	}
	
	public Vec2i coordinateToTile(Vec2d position)
	{
		return new Vec2i((int)(position.y / myTileSize.y), (int)( position.x / myTileSize.x));
	}
	
	public Vec2d tileToCoordinate(int x, int y)
	{
		return new Vec2d(y * myTileSize.x, x * myTileSize.y);
	}


	public PathFinding getPathFinding() {
		return myPathFinding;
	}


	public void setPathFinding(PathFinding pathFinding) {
		this.myPathFinding = pathFinding;
	}


	public boolean isEnablePathFinding() {
		return myEnablePathFinding;
	}


	public void enablePathFinding() {
		this.myEnablePathFinding = true;
		if(this.myPathFinding == null)
		{
			this.myPathFinding = new PathFinding(this.getNumTiles().x, this.getNumTiles().y, 
					this.getIntTileMap());
		}
	}
	
	public void disablePathFinding() {
		this.myEnablePathFinding = false;
		this.myPathFinding = null;
	}
	
	public Queue<Vec2d> findPath(Node startNode, Node endNode)
	{
		if(myEnablePathFinding)
		{
			
			List<Node> resultPath = myPathFinding.findPath(startNode, endNode);
			if(resultPath != null)
			{
				Queue<Vec2d> pathPoints = new LinkedList<Vec2d>();
				for(Node  node: resultPath)
				{
					//	convert to game coordinates
					Vec2d gameCoordinate = tileToCoordinate(node.x, node.y);
					//Vec2d gameCoordinate = new Vec2d(node.y * 32,node.x * 36);
					pathPoints.add(gameCoordinate);
					
				}
				
				return pathPoints;
			} 
			
			
		}
		return null;
	}
	
}
