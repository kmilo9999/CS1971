package fxengine.AI;

import java.util.Comparator;

public class Node {
	int y;
    int x;
    int parentX;
    int parentY;
    float gCost;
    float hCost; 
    float fCost;
    
    public Node()
    {
    	
    }
    
    public boolean isLess(Node other)
    {
    	return this.fCost < other.fCost;
    }
    
    
};


