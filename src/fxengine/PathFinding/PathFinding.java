package fxengine.PathFinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class PathFinding {

	
	private final int myXTiles;
	private final int myYTiles;
	private  Node[][] myMap;
	
	private Set<String> obstacles = new HashSet<String>();
	
	public PathFinding(int xTiles, int yTiles , List<List<Integer>> tileMap)
	{
		this.myXTiles = xTiles;
		this.myYTiles = yTiles;
	

		initializeObstacles(tileMap);
		
	}

	private void initializeObstacles(List<List<Integer>> tileMap)
	{
		for(int i = 0 ; i< tileMap.size(); i++)
		{
			for(int j = 0 ; j< tileMap.get(i).size(); j++)
			{
			   if(tileMap.get(i).get(j) == 1)
			   {
				   String nodeId = new String(""+i+""+j);
				   obstacles.add(nodeId);
			   }
			}
			
		}
	}
	
	public double getWidth() {
		return myXTiles;
	}

	public double getHeight() {
		return myYTiles;
	}
	
	private boolean isValid(int x, int y) { //If our Node is an obstacle it is not valid
		String nodeId = new String(""+x+""+y);
        
        
        if (!obstacles.contains(nodeId))
        {
        	if(  x < 0 || x >= this.myXTiles || y < 0 || y >= this.myYTiles) 
            {
                    return false;
            }
        	
        	return true;
        }
        	
        return false;
    }
	
	private boolean isDestination(int x, int y, Node dest) {
        if (x == dest.x && y == dest.y) {
            return true;
        }
        return false;
    }
	
	private float calculateH(int x, int y, Node dest) {
        double H = (Math.sqrt((x - dest.x)*(x - dest.x)
            + (y - dest.y)*(y - dest.y)));
        return (float)H;
    }
	
	public List<Node> findPath(Node start, Node dest) {
		// A* implementation
		 
	        if (isValid(dest.x, dest.y) == false) {
	            System.out.println("Destination is an obstacle");  ;
	            return null;
	            //Destination is invalid
	        }
	        if (isDestination(start.x, start.y, dest)) {
	        	System.out.println("You are the destination");
	            return null;
	            //You clicked on yourself
	        }
	        boolean[][] closedList = new boolean[this.myXTiles][this.myYTiles];
	        
	        //Initialize whole map
	        //Node allMap[50][25];
	    	myMap = new Node[this.myXTiles][this.myYTiles];
	        
	        
	        for (int x = 0; x < this.myXTiles; x++) {
	            for (int y = 0; y < this.myYTiles; y++) {
	            	myMap[x][y] = new Node(x,y);
	            	myMap[x][y].fCost = Float.MAX_VALUE;
	            	myMap[x][y].gCost = Float.MAX_VALUE;
	            	myMap[x][y].hCost = Float.MAX_VALUE;
	            	myMap[x][y].parentX = -1;
	            	myMap[x][y].parentY = -1;
	            	myMap[x][y].x = x;
	            	myMap[x][y].y = y;

	                closedList[x][y] = false;
	            }
	        }
		
	        //Initialize our starting list
	        int currentX = start.x;
	        int currentY = start.y;
	        myMap[currentX][currentY].fCost = 0.0f;
	        myMap[currentX][currentY].gCost = 0.0f;
	        myMap[currentX][currentY].hCost = 0.0f;
	        myMap[currentX][currentY].parentX = currentX;
	        myMap[currentX][currentY].parentY = currentY;

	       // Queue<Node> openList = new LinkedList<Node>();  
	        // openList.add(myNodes[x][y]);
	        boolean destinationFound = false;
	        
	        PriorityQueue<Node> pq = new 
	                PriorityQueue<Node>(); 
	        pq.add(myMap[currentX][currentY]);
	        while (!pq.isEmpty()&&pq.size()<this.myXTiles*this.myYTiles) {
	            Node node = pq.remove();
	            
	            currentX = node.x;
	            currentY = node.y;
	            closedList[currentX][currentY] = true;
	            
	            
	            for (int newX = -1; newX <= 1; newX++) {
	                for (int newY = -1; newY <= 1; newY++) 
	                {
	                   float gNew, hNew, fNew;
	                   int neighborX =currentX + newX;
	                   int neighborY =currentY + newY;
	                   if (isValid(neighborX, neighborY))
	                   {
	                	   if(isDestination(currentX + newX, currentY + newY,dest))
	                	   {
	                		   // remove wrong diagonals
	                		   /*if(Math.abs(neighborX - currentX) == 1
	                				   && Math.abs(neighborY - currentY) == 1 )
	                		   {
	                			   
	                			   if(!isValid(currentX, currentY + newY) 
	                					   || !isValid(currentX + newX, currentY))
	                			   {
	                				   continue;
	                			   }
	                		   }*/
	                		   
	                		   if(!validDiagonals(currentX,currentY,neighborX,neighborY,newX,newY))
	                		   {
	                			   continue;
	                		   }
	                		   
	                		   myMap[neighborX][neighborY].parentX = currentX;
	                           myMap[neighborX][neighborY].parentY = currentY;
	                           destinationFound = true;
	                           return makePath( dest);   
	                	   }
	                	   else if (closedList[neighborX][neighborY] == false)
	                       {
	                		  
	                		   if(!validDiagonals(currentX,currentY,neighborX,neighborY,newX,newY))
	                		   {
	                			   continue;
	                		   }
	                		   
		                	   gNew = node.gCost + 1.0f;
	                           hNew = calculateH((int)(neighborX),(int)(neighborY), dest);
	                           fNew = gNew + hNew;
	                           if (myMap[neighborX][neighborY].fCost == Float.MAX_VALUE ||
	                        		   myMap[neighborX][neighborY].fCost > fNew)
	                           {

	                               // Update the details of this neighbour node
	                        	   myMap[neighborX][neighborY].fCost = fNew;
	                        	   myMap[neighborX][neighborY].gCost = gNew;
	                        	   myMap[neighborX][neighborY].hCost = hNew;
	                        	   myMap[neighborX][neighborY].parentX = currentX;
	                        	   myMap[neighborX][neighborY].parentY = currentY;
	                               pq.add(myMap[neighborX][neighborY]);
	                               
	                           }
	                       }
	                   }
	                  
	                }
	            }
	            
	        }
	        
	        if (destinationFound == false) {
               System.out.println("Destination not found");
                return null;
           }
		
		return null;
	}
	
	
	public List<Node> makePath(Node dest) {
		   System.out.println("Found a path");
           int x = dest.x;
           int y = dest.y;
           Stack<Node> path = new Stack<Node>();
           List<Node> usablePath = new ArrayList<Node>();

           while (!(myMap[x][y].parentX == x && myMap[x][y].parentY == y)
                   && myMap[x][y].x != -1 && myMap[x][y].y != -1) 
           {
                   path.push(myMap[x][y]);
                   int tempX = myMap[x][y].parentX;
                   int tempY = myMap[x][y].parentY;
                   x = tempX;
                   y = tempY;

           }
           
           path.push(myMap[x][y]);
           
           while (!path.empty()) {
               Node top = path.pop();
               usablePath.add(top);
           }
           
           return usablePath;

	}
	
	private boolean validDiagonals(int origX, int origY, int desX, int destY, int newX, int newY)
	{
		 // remove wrong diagonals
		   if(Math.abs(desX - origX) == 1
				   && Math.abs(destY - origY) == 1 )
		   {
			   
			   if(!isValid(origX, origY + newY) 
					   || !isValid(origX + newX, origY))
			   {
				   return false;
			   }
		   }
		   
		   return true;
	}
}
