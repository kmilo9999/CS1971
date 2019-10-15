package fxengine.PathFinding;

import fxengine.math.Vec2i;

public class Node implements Comparable<Node>{
	public int y;
    public int x;
    int parentX;
    int parentY;
    float gCost;
    float hCost; 
    float fCost;
    
    public Node()
    {
    	
    }
    
    public Node(int x, int y)
    {
    	 this.x = x;
        this.y =  y;
    }
    public Node(Vec2i position)
    {
    	 this.x = position.x;
         this.y = position.y;
    }
  
    
    public boolean isLess(Node other)
    {
    	return this.fCost < other.fCost;
    }
    
    @Override
    public String toString() { 
        return String.format(this.x + " " + this.y); 
    }

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		 return ((int)this.fCost-(int) o.fCost);
	} 
};


