package game.wiz;

import java.util.List;

public abstract class Character {
  
   public enum State
   {
	   idle, left, right, up, down
   }
   
   private List<Animation> animations;
   
   private String name;
   
   private int currentAnimation;
   
   
}
