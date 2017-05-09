/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

/**
 *
 * @author newuser
 */
public class Difficulty {
   public double MOVE_STAMINA = 1.0;
  // Player player;  Not sure why this was here- Tony
  
public Difficulty(){
  this.MOVE_STAMINA=1.0;     
}

public void easy(){
this.MOVE_STAMINA=0.5;

}

public void medium(){
this.MOVE_STAMINA=1.0;
}

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
