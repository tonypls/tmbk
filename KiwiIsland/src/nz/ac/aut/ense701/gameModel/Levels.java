/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import javax.swing.JOptionPane;


/**
 *
 * @author kelvin
 */
public class Levels {
   
    Game game = new Game();
    Player player;
    Item item;
    
    public void StaminaHint()
    {
        if (player.getStaminaLevel() <= 50)
        {
            System.out.print("djejifj");
        }else if (player.getStaminaLevel() <= 30){
            
            System.out.print("fmk");
        }
       
    }
    
    public void BagBackHint(){
        
        if(player.getCurrentBackpackWeight() < 50 ){
            System.out.print("weight below 50");
                  
        }else if (player.getCurrentBackpackWeight() < 30){
            System.out.print("weight below 30");
        }
    }
      
    public void checkkforitem(){
        
        if(player.hasItem(item))
            System.out.print("has item");
        
    }
    }
    
        
   
   
    
    
