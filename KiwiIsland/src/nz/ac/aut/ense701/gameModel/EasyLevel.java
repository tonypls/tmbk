package nz.ac.aut.ense701.gameModel;
import nz.ac.aut.ense701.gameModel.Game;
import nz.ac.aut.ense701.gui.KiwiCountUI;

public class EasyLevel
{
    //Constants shared with UI to provide player data

   // private Game game;
    Game game = new Game();
    //KiwiCountUI  gui  = new KiwiCountUI(game);
    /**
     * A new instance of Kiwi island that reads data from "IslandData.txt".
     */
    public EasyLevel() 
    {   
        
    }
    
    public void easyGame()
    {   
//        this.game= game;
       game.createNewGame();
        //gui.setVisible(true);
    }
    
}