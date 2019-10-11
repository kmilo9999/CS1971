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
	private Vec2d numTiles = new Vec2d(0);
	GameTile[][] tileMap;
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
				    	intLine.add(st.charAt(i) - 97); 
				    }
				    
				    intTileMap.add(intLine);
				    
					System.out.println(st);
				    
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
			 
			 tileMap = new GameTile[this.intTileMap.size()][minY];
			 
			 for(int i = 0 ; i < this.intTileMap.size() ; i++) 
			 {
				 double xPos = topLeft.x + (i * this.myTileSize.x);
				 
				 for(int j = 0 ; j < minY ; j++)
				 {
					double yPos = topLeft.y + (j * this.myTileSize.y);

					boolean isStatic = this.intTileMap.get(i).get(j) == 1 ? true : false;

					tileMap[i][j] = new GameTile("tile" + i +""+ j,myTextureMapFilePath,new Vec2d(xPos, yPos));
					tileMap[i][j].setColor(intTileMap.get(i).get(j));
					tileMap[i][j].setStatic(isStatic); 
					tileMap[i][j].setTileSize(this.myTileSize);
					tileMap[i][j].setNumFrames(this.myNumFrames);
					tileMap[i][j].setTextCoord(this.myInitialPosition);

					this.myScene.getGameWorld().addGameObject(tileMap[i][j], GameWorld.BackLayer);		
					
				 }
			 }
			 
			 
			
			 
		}
	}
	
}
