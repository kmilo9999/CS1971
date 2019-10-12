package fxengine.maploader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import fxengine.math.Vec2d;
import fxengine.math.Vec2i;
import fxengine.objects.GameWorld;
import fxengine.scene.GameWorldScene;
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
	
	
	private GameWorldScene myScene;
	
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
			 FileReader fr;
			 boolean readFile = false;
			 int minY = 1000000;
			 
			Resource resource = ResourceManager.getIntance().createOrGetResource(this.myFilePath, ResourceType.TextFile);
			
			
			//new FileReader(getClass().getClassLoader().getResource(this.myFilePath).toString());

            try {
				BufferedReader br = new BufferedReader(new FileReader(resource.getFileReaader()));
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
			 
			 myTileMap = new GameTile[this.intTileMap.size()][minY];
			 
			 myNumTiles = new Vec2i(this.intTileMap.size(),minY);
			 System.out.println("numTiles: " + myNumTiles);
			 
			 
			 for(int i = 0 ; i < minY ; i++) 
			 {
				 double xPos = topLeft.x + (i * this.myTileSize.x);
				 
				 for(int j = 0 ; j < this.intTileMap.size() ; j++)
				 {
					double yPos = topLeft.y + (j * this.myTileSize.y);

					boolean isStatic = this.intTileMap.get(j).get(i) == 1 || 
							this.intTileMap.get(j).get(i) == 2 ? true : false;

					String tileId ="tile" + i +""+ j;
					
					GameTile tile = new GameTile(tileId,myTextureMapFilePath,new Vec2d(xPos, yPos));
					if(intTileMap.get(j).get(i) == 2)
					{
						tile.setTag("GOAL");
					}
					
					
					tile.setColor(intTileMap.get(j).get(i));
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
			 
			Resource resource = ResourceManager.getIntance().createOrGetResource(this.myFilePath, ResourceType.TextFile);
			
			
			//new FileReader(getClass().getClassLoader().getResource(this.myFilePath).toString());

            try {
				BufferedReader br = new BufferedReader(new FileReader(resource.getFileReaader()));
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
	
}
