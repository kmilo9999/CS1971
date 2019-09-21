package game.tic;

/**
 * A Tic Player
 * @author cdiaz8
 *
 */

public class TicPlayer {

	private int myPoints;
	
	
	
	private String myName;
	
	public TicPlayer(String name)
	{
		this.myPoints = 0;
		this.myName = name;
	
	}
	
	public boolean equals(TicPlayer p)
	{
		if(this.myName.equals(p.myName))
		{
			return true;
		}
		return false;
	}
	
	
	public String getName() {
		return myName;
	}

	public void setName(String name) {
		this.myName = name;
	}
}
