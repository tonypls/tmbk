package nz.ac.aut.ense701.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import nz.ac.aut.ense701.gameModel.Game;
import nz.ac.aut.ense701.gui.KiwiCountUI;
import nz.ac.aut.ense701.gui.MainMenu;

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
        // final KiwiCountUI gui = new KiwiCountUI (game);
        final MainMenu  gui  = new MainMenu();
        
        // set the location of the created levelGUI to center of the window
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        gui.setLocation(screenSize.width/2 - gui.getWidth()/2, screenSize.height/2 - gui.getHeight()/2);
        
        // make the GUI visible
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                gui.setVisible(true);
            }
        });
    }

}
