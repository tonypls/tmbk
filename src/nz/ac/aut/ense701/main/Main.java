package nz.ac.aut.ense701.main;

import java.awt.Panel;
import nz.ac.aut.ense701.gameModel.Game;
import nz.ac.aut.ense701.gui.KiwiCountUI;
import nz.ac.aut.ense701.gui.MainMenuUI;

/**
 * Kiwi Count Project
 * 
 * @author AS
 * @version 2011
 */
public class Main 
{
    /**
     * Main method of Kiwi Count.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        
        final MainMenuUI menu = new MainMenuUI();
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                menu.setVisible(true);
                menu.setEnabled(true);
            }
        });
        
//        // create the game object
//        final Game game = new Game();
//        // create the GUI for the game
//        final KiwiCountUI  gui  = new KiwiCountUI(game);
//        // make the GUI visible
//        java.awt.EventQueue.invokeLater(new Runnable() 
//        {
//            @Override
//            public void run() 
//            {
//                gui.setVisible(true);
//            }
//        });
    }

}
