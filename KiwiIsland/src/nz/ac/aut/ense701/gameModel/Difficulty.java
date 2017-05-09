package nz.ac.aut.ense701.gameModel;

/**
 * This class is build so stamina can be calculated 
 *as in three different levels.
 * 
 * @author Talib
 */
public class Difficulty {
   public double MOVE_STAMINA = 1.0;
   Player player;
   Game game;
   
// Default constructor.
public Difficulty(){
  this.MOVE_STAMINA=1.0;     
}

// Method to implement easy level and setting Move_stamina to 0.5
//to calculate stamina reduction
public void easy(){
this.MOVE_STAMINA=0.5;
}

// Method to implement meduim level and setting Move_stamina to 1.0
//to calculate stamina reduction
public void medium(){
this.MOVE_STAMINA=1.0;
}

// Method to implement hard level and seting Move_stamina to 2.0
//to calculate stamina reduction
public void hard(){
this.MOVE_STAMINA=2.0;
}
    public void setMOVE_STAMINA(double MOVE_STAMINA) {
        this.MOVE_STAMINA = MOVE_STAMINA;
    }

    public double getMOVE_STAMINA() {
        return MOVE_STAMINA;
    }
    
}

