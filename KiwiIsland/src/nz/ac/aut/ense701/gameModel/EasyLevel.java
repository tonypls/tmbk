/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;


/**
 *
 * @author kelvin
 */
public class EasyLevel {
    
    Player player;
    
    private void StaminaHint(){
        
         double d1 = player.getStaminaLevel();
        double d2 = 100;
      
        int CheckStamina = Double.compare(d1, d2);
        if ( CheckStamina <= 100 ) {
             // output "please eat fruits to get more energy"
    
        }else if( CheckStamina <= 80){
            
            // output "please eat fruits to get more energy"           
        }else if( CheckStamina <= 20){
            
        };  
        
        // try this too if above does not work with output
        /* double d1 = player.getStaminaLevel();
           double d2 = 100;
           double d3 = 50;
           double d4 = 20;
      
        int CheckStamina = Double.compare(d1, d2);
        if ( CheckStamina <= d2 ) {
        /output for when stamina = d2 (100)
        }else{
            return;
        }
        
        int CheckStamina = Double.compare(d1, d);
        if ( CheckStamina <= d ) {
        /output for when stamina = d3 (50)
        }else{
            return;
        }
        
        int CheckStamina = Double.compare(d1, d4);
        if ( CheckStamina <= d2 ) {
        /output for when stamina = d2 (20)
        }else{
            return;
        }
        */
        
    }
 
    private void CollectKiwiHint(){
        
        
 
    }
    
    private void TrapPredatorHint(){
      
    }
    
    
    
    
}
