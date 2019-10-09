package fxengine.components;

import fxengine.manager.Resource;
import fxengine.objects.GameObject;

public class Tile extends GameObject{

	private int myColor = 0;
	
	public Tile(String id, int color) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
        	 
	}

	public int getColor() {
		return myColor;
	}

	public void setColor(int myColor) {
		this.myColor = myColor;
	}
	
	
}
