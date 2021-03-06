package nz.ac.aut.ense701.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import nz.ac.aut.ense701.gameModel.Game;
import nz.ac.aut.ense701.gameModel.Terrain;

/*
 * Panel for representing a single GridSquare of the island on the GUI.
 * 
 * @author AS
 * @version 1.0 - created
 */

public class GridSquarePanel extends javax.swing.JPanel 
{
    /** 
     * Creates new GridSquarePanel.
     * @param game the game to represent
     * @param row the row to represent
     * @param column the column to represent
     */
      
    BufferedImage image;
    BufferedImage player;
    
    public GridSquarePanel(Game game, int row, int column)
    {
        this.game   = game;
        this.row    = row;
        this.column = column;
        initComponents();
        
    }

    // this method is used by update() to load images from file
    public void setImage(String file){   
        try {
            image = ImageIO.read(new File(file));
        } catch (IOException ex) {
            Logger.getLogger(GridSquarePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       // this method is used by update() to load images from file
    public void setImage2(String file){   
        try {
            player = ImageIO.read(new File(file));
        } catch (IOException ex) {
            Logger.getLogger(GridSquarePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // override the BufferedImage method to update and draw the image set by update() method
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 1, 0, null);  
        g.drawImage(player, 1, 0, null); 
         
    }
    
    /**
     * Updates the representation of the grid square panel.
     */
    public void update()
    {
        // get the GridSquare object from the world
        Terrain terrain   = game.getTerrain(row, column);
        boolean squareVisible = game.isVisible(row, column);
        boolean squareExplored = game.isExplored(row, column);
        
        if (game.hasPlayer(row, column)){
            setImage2("images/player.png");
        } else {
            player = null;
        }
        
        //ImageIcon image = null;//new ImageIcon("images/blank.png");
        JLabel lblImage = new JLabel(); // create a new label to put an image on
        
        // call the method to set a new Buffered image to this panel
        
        if (squareVisible && image == null){
            switch ( terrain )
            {
                case SAND     : setImage("images/sand.png"); break;// = new ImageIcon("images/sand.png"); break;
                case FOREST   : setImage("images/forest.png"); break;
                case WETLAND : setImage("images/wetland.png"); break;
                case SCRUB : setImage("images/scrub.png"); break;
                case WATER    : setImage("images/water.png"); break;
                default  : image = null; break;
            }
        }
       
// this is old code that use to change the graphics, trying BufferedImage instead of IconImage
//        switch ( terrain )
//        {
//            case SAND     : setImage("images/forest.png");// = new ImageIcon("images/sand.png"); break;
//            case FOREST   : image = new ImageIcon("images/forest.png"); break;
//            case WETLAND : image = new ImageIcon("images/wetland.png"); break;
//            case SCRUB : image = new ImageIcon("images/scrub.png"); break;
//            case WATER    : image = new ImageIcon("images/water.png"); break;
//            default  : image = null; break;
//        }
        
        if ( squareExplored || squareVisible )
        {
            // Set the text of the JLabel according to the occupant
            lblText.setText(game.getOccupantStringRepresentation(row,column));           
        }
        else {
            lblText.setText("");
            image = null;
        }
        
        // if the game is not being played, remove the activeBorder (this fixes the multiple borders glitch)
//        if (game.getState() != GameState.PLAYING) {
//            setBorder(normalBorder);
//        } 
       
        // set the redsquare border to active if the player is here
        setBorder(game.hasPlayer(row,column) ? activeBorder : normalBorder);
       

        // add the imageIcon to the gridsquare panel
        //lblImage.setIcon((Icon) image); // add the image to the label
        this.add(lblImage); // add the jlabel image to the current gridsquare
        
        JLabel lblImage2 = new JLabel(); 
        this.add(lblImage2);
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblText = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setLayout(new java.awt.BorderLayout());

        lblText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblText.setText("content");
        lblText.setOpaque(true);
        add(lblText, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblText;
    // End of variables declaration//GEN-END:variables
    
    private Game game;
    private int row, column;
    
    private static final Border normalBorder = new LineBorder(Color.BLACK, 1);
    private static final Border activeBorder = new LineBorder(Color.RED, 3);
}
