package game.tic;


import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import fxengine.UISystem.Label;
import fxengine.UISystem.Layout;
import fxengine.UISystem.UIConstants;
import fxengine.UISystem.UIElement;
import fxengine.application.FXFrontEnd;
import fxengine.scene.BaseScene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * The Tic Board scene. Composed of the start button, labels describing players turn
 * game result.
 * It checks the current state of the board and look for a win state and determines the winner player
 * @author cdiaz8
 *
 */


public class TicPlayScene extends BaseScene{
	
	private String[][] myGameGrid = new String[3][3];
	
	private final String TIC_WIN_ROW = "WIN_ROW";
	private final String TIC_WIN_COL = "WIN_COL";
	private final String TIC_WIN_DIAG = "WIN_DIAG";
	
	public static final String STATE_NO_PLAY = "NOPLAY";
	public static final String STATE_PLAYING = "PLAY";
	
	
	private String myCurrentState ;

	private TicPlayer player1;
	private TicPlayer player2;
    public TicPlayer currentPlayerMoving;
    private List<UIElement> myTokenlayouts;
    private UIElement myGameResultLabel ;
    private UIElement myPlayerTurnLabel ;
    private UIElement myPlayerTimeTurnLabel;
    
    private UIElement myStartButton;
    
    private boolean myisGameOver;
    private boolean myIsGameDraw;
    
	private TicTokenLayout myLastMove;
    private boolean myGameGridDirty ;
    
    private int myEnlapsSeconds;
	private long accumulatedTime;
	
	public TicPlayScene(String name, FXFrontEnd application)
	{
		super(name, application);
		this. myCurrentState = STATE_NO_PLAY;
		this.myIsActive = false;
	}
	
	@Override
	public void initScene() {
	   
		myEnlapsSeconds = 0;
		accumulatedTime = 0;
		
		for(int i = 0; i < 3 ; i++) 
		{
			for(int j = 0; j < 3 ; j++)
			{
				myGameGrid[i][j] = " ";
			}
		}
		
		myTokenlayouts = new ArrayList<UIElement>();
		myisGameOver = false;
		myGameGridDirty = false;
		myIsGameDraw = false;
		
		
		player1 = new TicPlayer("Player1");
		player2 = new TicPlayer("Player2");
		
		currentPlayerMoving = player1;
		
		UIElement globalLayout = new Layout(0,0, this.myWindowSize.x/2 , this.myWindowSize.y/2);
				
		
		double xOffset = 0.0;
		double yOffset = 0.0;
		double totalXoffset = 0.0;
		for(int i = 0; i < 3 ; i++) 
		{
			for(int j = 0; j < 3 ; j++)
			{	
				String id = ""+i+":"+j;
				UIElement tokenlayout = new TicTokenLayout(id,50 +xOffset , 80 + yOffset, 27, 27, this ,UIConstants.GOLD);
				myTokenlayouts.add(tokenlayout);
				xOffset += 30.0;
			}
			totalXoffset += xOffset;
			xOffset = 0.0;
			yOffset += 30.0;
		}
		
		UIElement titleLabel = new Label("TIC TAC TOE", 55 , 60 , UIConstants.FIREBRICK);
		
		myPlayerTurnLabel = new Label("", totalXoffset / 3 + 60 , 95 );
		myGameResultLabel = new Label("", totalXoffset / 3 + 60 , 95 + 30);
		myPlayerTimeTurnLabel = new Label("", totalXoffset / 3 + 60 , 95 + 50);
		
		myStartButton = new TicPlayButton(totalXoffset / 3 + 60 ,95 + 70,"CLICK TO START",this);
		
		
		
		globalLayout.addChildElements(myTokenlayouts);
		globalLayout.addChildElement(myPlayerTurnLabel);
		globalLayout.addChildElement(myGameResultLabel);
		globalLayout.addChildElement(myPlayerTimeTurnLabel);
		globalLayout.addChildElement(myStartButton);
		
		globalLayout.addChildElement(titleLabel);
		
		
		//this.myIsActive = true;
		this.props.add(globalLayout);
		
		//super.initScene();
	}
	
	@Override
	public void cleanScene()
	{
		myPlayerTurnLabel = null;
		myGameResultLabel = null;
		myStartButton = null;
		super.cleanScene();
		
	}
	
	public void restartScene()
	{
		cleanScene();
		this.myCurrentState = STATE_NO_PLAY;
		initScene();
	}
	
	@Override
	public void onTick(long nanosSincePreviousTick)
	{
		
	
		
		super.onTick( nanosSincePreviousTick);
		if(this.myCurrentState.equals(STATE_PLAYING))
		{
			accumulatedTime += nanosSincePreviousTick;
			if(accumulatedTime > 1000000000 && !this.myisGameOver)
			{
				myEnlapsSeconds++;
				accumulatedTime = 0;
			}
				
			((Label)this.myPlayerTimeTurnLabel).setText("Time Limit: "+ (10 -myEnlapsSeconds));
			
			((Label)this.myPlayerTurnLabel).setText(this.currentPlayerMoving.getName() + " TURN");
			
			if(10 -myEnlapsSeconds == 0 && !this.myGameGridDirty)
			{
				this.setNextPlayerMoving();
				myEnlapsSeconds = 0;
				return;
			}
			
			if(this.myGameGridDirty && !this.myisGameOver)
			{
				String[] xyPos = myLastMove.getId().split(":");
				int posx = Integer.parseInt(xyPos[0]);
				int posy = Integer.parseInt(xyPos[1]);
				myGameGrid[posx][posy] = this.currentPlayerMoving.getName();
				this.checkGameOver();	
				if(!this.myisGameOver && !this.myIsGameDraw )
				{
					this.setNextPlayerMoving();
					myEnlapsSeconds = 0;
					this.myGameGridDirty = false;
				}
				else if(this.myisGameOver && this.myIsGameDraw )
				{
					//draw game
					((Label)this.myGameResultLabel).setText("DRAW GAME");
					((Label)this.myPlayerTimeTurnLabel).setText("");
					this.myCurrentState = STATE_NO_PLAY;
				}
				else
				{
					((Label)this.myGameResultLabel).setText(this.currentPlayerMoving.getName()+" HAS WIN");
					((Label)this.myPlayerTimeTurnLabel).setText("");
					this.myCurrentState = STATE_NO_PLAY;
				}
			}
		}
		
		if(this.myCurrentState.equals(STATE_NO_PLAY) && this.myisGameOver )
		{
			((Label)this.myPlayerTurnLabel).setText("");
			((TicPlayButton)myStartButton).setText("CLICK TO RESTART");
		}
		
	}
	
	@Override
	public void onKeyPressed(KeyEvent event) {
		 if (event.getCode() == KeyCode.ESCAPE) {
			 myApplication.shutdown();
		 } 
	}

	public void setPlayerMove(TicTokenLayout token)
	{
		this.myLastMove = token;
		this.myGameGridDirty = true;
	}
	
	
	private void checkGameOver() {
		// TODO Auto-generated method stub
		  int result = checkRows();
		  if(result != -1 )
		  {
			  paintWinMove(TIC_WIN_ROW, result);
			  this.myisGameOver = true;
			  
			  return;
		  }
		  
		  result= checkColumns();
		  if(result != -1 )
		  {
			  paintWinMove(TIC_WIN_COL, result);
			  this.myisGameOver = true;
			  
			  return;
		  }
		  
		  result = checkDiagonals();
		  if(result != -1 )
		  {
			  paintWinMove(TIC_WIN_DIAG, result);
			  this.myisGameOver = true;
			  
			  return;
		  }
		  
		   result = checkNoMoreMoves();
			 if(result == 0 )
			 {
				  this.myisGameOver = true;
				  this.myIsGameDraw = true;
				  
				  return;
			 }
		        
	}

	private void paintWinMove(String move, int winMove) {
		// TODO Auto-generated method stub
		if(move.equals(TIC_WIN_ROW))
		{
			String id1 = "" + winMove +":0";
			String id2 = "" + winMove +":1";
			String id3 = "" + winMove +":2";
			
			for(UIElement element:myTokenlayouts)
			{
			  
			  TicTokenLayout ticLayout = (TicTokenLayout)element;
			
			  if(ticLayout.getId().equals(id1) || ticLayout.getId().equals(id2)
					  || ticLayout.getId().equals(id3))
			  {
				  ticLayout.setWinMove(true);
			  }

			}
		}
		else if(move.equals(TIC_WIN_COL))
		{
			String id1 = "" + "0:" +winMove ;
			String id2 = "" + "1:" + winMove ;
			String id3 = "" + "2:" + winMove ;
			
			for(UIElement element:myTokenlayouts)
			{
				 TicTokenLayout ticLayout = (TicTokenLayout)element;
					
				  if(ticLayout.getId().equals(id1) || ticLayout.getId().equals(id2)
						  || ticLayout.getId().equals(id3))
				  {
					  ticLayout.setWinMove(true);
				  }
			}			
		}
		else if(move.equals(TIC_WIN_DIAG))
		{
			
			String id1 = "";
			String id2 = "";
			String id3 = "";
			
			if(winMove == 1)
			{
				 id1 = "0:0";
				 id2 = "1:1";
				 id3 = "2:2";
			}
			else
			{
				 id1 = "0:2";
				 id2 = "1:1";
				 id3 = "2:0";
			}
			
			for(UIElement element:myTokenlayouts)
			{

				 TicTokenLayout ticLayout = (TicTokenLayout)element;
					
				  if(ticLayout.getId().equals(id1) || ticLayout.getId().equals(id2)
						  || ticLayout.getId().equals(id3))
				  {
					  ticLayout.setWinMove(true);
				  }
			}
		}
	}



	public void setNextPlayerMoving()
	{
		if(this.currentPlayerMoving.getName().equals("Player1"))
		{
			this.currentPlayerMoving = player2;
		}
		else
		{
			this.currentPlayerMoving = player1;
		}
	}
	
	private int checkRows() 
	{ 
		
	    for (int i=0; i < 3; i++) 
	    { 
	        if (myGameGrid[i][0] == myGameGrid[i][1] && 
	        	myGameGrid[i][1] == myGameGrid[i][2] &&  
	        	myGameGrid[i][0] != " ")
	        {
	        	return i;
	        }
	             
	    } 
	    
	    return  -1;
	     
	}
	
	private int checkColumns() 
	{ 
	    for (int i=0; i<3; i++) 
	    { 
	        if (myGameGrid[0][i] == myGameGrid[1][i] && 
	        	myGameGrid[1][i] == myGameGrid[2][i] &&  
	        	myGameGrid[0][i] != " ")
	        {
	        	return i;
	        }
	             
	    } 
	    return -1;
	     
	}
	
	
	private int checkDiagonals() 
	{ 
		
	    if (myGameGrid[0][0] == myGameGrid[1][1] && 
	    	myGameGrid[1][1] == myGameGrid[2][2] &&  
	    	myGameGrid[0][0] != " ") 
	    {
	    	  return  1;
	    	  
	    }
	       
	          
	    if (myGameGrid[0][2] == myGameGrid[1][1] && 
	    	myGameGrid[1][1] == myGameGrid[2][0] && 
	    	myGameGrid[0][2] != " ")
	    {
	    	return 2;
	    	
	    }
	         
	    return -1;
	     
	} 
	
	private int checkNoMoreMoves() 
	{ 
		
		for(int i = 0; i < 3 ; i++)
		{
			for(int j = 0; j < 3 ; j++)
			{
				if(myGameGrid[i][j] == " ")
				{
					return 1;
				}
			}
		}
	    
	    return 0;
	     
	} 
	
	
    public boolean isGameOver() {
		return myisGameOver;
	}

  
	
	public String getCurrentState() {
		return myCurrentState;
	}

	public void setCurrentState(String currentState) {
		this.myCurrentState = currentState;
	}

	@Override
	public Element saveState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadState() {
		// TODO Auto-generated method stub
		
	}

}
