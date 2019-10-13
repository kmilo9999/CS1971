package fxengine.AI;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class PathFinding {

	
	private final int myWidth;
	private final int myHeight;
	private  Node[][] myMap;
	
	private Set<String> obstacles;
	
	public PathFinding(int mapWidth, int mapHeight , List<List<Integer>> tileMap)
	{
		this.myWidth = mapWidth;
		this.myHeight = mapHeight;
	

		initializeObstacles(tileMap);
		
	}

	private void initializeObstacles(List<List<Integer>> tileMap)
	{
		for(int i = 0 ; i< tileMap.size(); i++)
		{
			for(int j = 0 ; i< tileMap.get(i).size(); j++)
			{
			   if(tileMap.get(i).get(j) == 1)
			   {
				   String nodeId = new String(i+""+j);
				   obstacles.add(nodeId);
			   }
			}
			
		}
	}
	
	public double getWidth() {
		return myWidth;
	}

	public double getHeight() {
		return myHeight;
	}
	
	private boolean isValid(int x, int y) { //If our Node is an obstacle it is not valid
		String nodeId = new String(x+""+y);
        
        
        if (!obstacles.contains(nodeId))
        {
        	if(  x < 0 || x > this.myWidth || y < 0 || y > this.myHeight) 
            {
                    return false;
            }
        }
        	
        return true;
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
	
	public List<Node> findPath(Node player, Node dest) {
		// A* implementation
		 
	        if (isValid(dest.x, dest.y) == false) {
	            System.out.println("Destination is an obstacle");  ;
	            return null;
	            //Destination is invalid
	        }
	        if (isDestination(player.x, player.y, dest)) {
	        	System.out.println("You are the destination");
	            return null;
	            //You clicked on yourself
	        }
	        boolean[][] closedList = new boolean[this.myWidth][this.myHeight];
	        
	        //Initialize whole map
	        //Node allMap[50][25];
	    	myMap = new Node[this.myWidth][this.myHeight];
	        
	        
	        for (int x = 0; x < this.myWidth; x++) {
	            for (int y = 0; y < this.myHeight; y++) {
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
	        int x = player.x;
	        int y = player.y;
	        myMap[x][y].fCost = 0.0f;
	        myMap[x][y].gCost = 0.0f;
	        myMap[x][y].hCost = 0.0f;
	        myMap[x][y].parentX = x;
	        myMap[x][y].parentY = y;

	       // Queue<Node> openList = new LinkedList<Node>();  
	        // openList.add(myNodes[x][y]);
	        boolean destinationFound = false;
	        
	        PriorityQueue<Node> pq = new 
	                PriorityQueue<Node>(new NodeComparator()); 
	        pq.add(myMap[x][y]);
	        while (!pq.isEmpty()&&pq.size()<this.myWidth*this.myHeight) {
	            Node node = pq.remove();
	            /*do {
	                float temp = Float.MAX_VALUE;
	                Iterator<Node> itOpenList = openList.iterator();
	                Iterator<Node> itNode = null;
	                while (itOpenList.hasNext()) {
	                    Node n = itOpenList.next();
	                    if (n.fCost < temp) {
	                        temp = n.fCost;
	                        itNode = itOpenList;
	                    }
	                }
	                
	                node = itNode.next();
	                openList.remove(itNode.next());
	            } while (isValid(node.x, node.y) == false);*/
	            
	            x = node.x;
	            y = node.y;
	            closedList[x][y] = true;
	            for (int newX = -1; newX <= 1; newX++) {
	                for (int newY = -1; newY <= 1; newY++) 
	                {
	                   float gNew, hNew, fNew;
	                   if (isValid(x + newX, y + newY))
	                   {
                           myMap[x + newX][y + newY].parentX = x;
                           myMap[x + newX][y + newY].parentY = y;
                           destinationFound = true;
                           return makePath( dest);
	                   }
	                   else if (closedList[x + newX][y + newY] == false)
                       {
	                	   gNew = node.gCost + 1.0f;
                           hNew = calculateH((int)(x + newX),(int)( y + newY), dest);
                           fNew = gNew + hNew;
                           if (myMap[x + newX][y + newY].fCost == Float.MAX_VALUE ||
                        		   myMap[x + newX][y + newY].fCost > fNew)
                           {

                               // Update the details of this neighbour node
                        	   myMap[x + newX][y + newY].fCost = fNew;
                        	   myMap[x + newX][y + newY].gCost = gNew;
                        	   myMap[x + newX][y + newY].hCost = hNew;
                        	   myMap[x + newX][y + newY].parentX = x;
                        	   myMap[x + newX][y + newY].parentY = y;
                               pq.add(myMap[x + newX][y + newY]);
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
}
