package nz.ac.aut.ense701.main;

import java.awt.Panel;
import javax.swing.JFrame;
import nz.ac.aut.ense701.gameModel.Game;
import nz.ac.aut.ense701.gui.KiwiCountUI;
import nz.ac.aut.ense701.gui.SelectDifficultyUI;

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

                // create the game object
        final Game game = new Game();
        // create the GUI for the game
        final KiwiCountUI  gui  = new KiwiCountUI(game);
        // make the GUI visible
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                gui.setVisible(true);
            }
        });
        
// CREATE THE MAIN MENU
//        final MainMenuUI menuGui = new MainMenuUI(); 
//
//        // make the GUI visible
//        java.awt.EventQueue.invokeLater(new Runnable() 
//        {
//            @Override
//            public void run() 
//            {
//                JFrame frame = new JFrame();
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.getContentPane().add(menuGui);
//                frame.pack();
//                frame.setVisible(true);
//            }
//        });
        
        

    }

}
