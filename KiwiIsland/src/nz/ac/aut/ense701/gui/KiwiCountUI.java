package nz.ac.aut.ense701.gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import java.awt.event.*;
import javax.swing.JButton;
import nz.ac.aut.ense701.gameModel.Game;
import nz.ac.aut.ense701.gameModel.GameEventListener;
import nz.ac.aut.ense701.gameModel.GameState;
import nz.ac.aut.ense701.gameModel.MoveDirection;
import nz.ac.aut.ense701.gameModel.Levels;
import nz.ac.aut.ense701.gameModel.Player;

/*
 * User interface form for Kiwi Island.
 * 
 * @author AS update by Tony van Swet & Mohammad
 * @version April 2017
 * Pair Progamming 5/05/2017
 */
public class KiwiCountUI
        extends javax.swing.JFrame
        implements GameEventListener, ActionListener, KeyListener {
   private Game game;
   
   
    /**
     * Creates a GUI for the KiwiIsland game.
     *
     * @param game the game object to represent with this GUI.
     */
    public KiwiCountUI(Game game) {
        assert game != null : "Make sure game object is created before UI";
        this.game = game;
        setAsGameListener();
        addKeyListener(this);
        setFocusable(true);
        initComponents();
        initIslandGrid();
        update();
    }

    /**
     * This method is called by the game model every time something changes.
     * Trigger an update.
     */
    /**
     * This method is called by the game model every time something changes.
     * Trigger an update.
     */
    @Override
    public void gameStateChanged() {
        update();
            //Conditions for Easy level, pop-up messages for user
            //if stamina level goes below 75% or below 50% or 20% message pops-up
            if(game.diff.MOVE_STAMINA==0.5){
            if((game.getPlayer().getStaminaLevel()<75.0)&&game.getPlayer().getStaminaLevel()>73.0){
              JOptionPane.showMessageDialog(
                    this,"Hint:\n"
                    + "Your stamina is getting lower!",
                     "Easy Level", 
                    JOptionPane.INFORMATION_MESSAGE);  
        }else if((game.getPlayer().getStaminaLevel()<50.0)&&game.getPlayer().getStaminaLevel()>48.0){
              JOptionPane.showMessageDialog(  
                    this,"Hint:\n"
                     +"Your stamina is low, please feed yourself!",
                     "Easy Level",
                    JOptionPane.INFORMATION_MESSAGE); 
        }else if((game.getPlayer().getStaminaLevel()<20.0)&&game.getPlayer().getStaminaLevel()>18.0){
              JOptionPane.showMessageDialog(  
                    this,"Hint:\n"
                     +"Your stamina is very low, please feed yourself!\n"
                     +"You need to use collected items to increase your stamina.",
                     "Easy Level",
                    JOptionPane.INFORMATION_MESSAGE); 
        }
    }       
            //Condition for Hard level, pop-up message for user since its a hard level
            //there will be ony one conditiond and only one hint for user
            //if stamina level goes below 20% message pops-up
            if(game.diff.MOVE_STAMINA==2.0){
            if((game.getPlayer().getStaminaLevel()<20.0)&&game.getPlayer().getStaminaLevel()>18.0){
              JOptionPane.showMessageDialog(  
                    this,"Hint:\n"
                     +"Your stamina is very low, please feed yourself!\n"
                     +"You need to use collected items to increase your stamina.",
                     "Hard Level",
                    JOptionPane.INFORMATION_MESSAGE); 
        }
    } 
            //Conditions for Medium level, pop-up messages for user
            //if stamina level goes below 50% or below 20% then message pops-up
            if(game.diff.MOVE_STAMINA==1.0){
            if((game.getPlayer().getStaminaLevel()<50.0)&&game.getPlayer().getStaminaLevel()>48.0){
              JOptionPane.showMessageDialog(  
                    this,"Hint:\n"
                     +"Your stamina is low, please feed yourself!",
                     "Medium Level",
                    JOptionPane.INFORMATION_MESSAGE); 
        }else if((game.getPlayer().getStaminaLevel()<20.0)&&game.getPlayer().getStaminaLevel()>18.0){
              JOptionPane.showMessageDialog(  
                    this,"Hint:\n"
                     +"Your stamina is very low, please feed yourself!\n"
                     +"You need to use collected items to increase your stamina.",
                     "Medium Level",
                    JOptionPane.INFORMATION_MESSAGE); 
        }
    }         
            
                    // if the game is over
        if (game.getState() == GameState.LOST || game.getState() == GameState.WON){
            
            int score;
            Player player = game.getPlayer();
            score = game.getKiwiCount() * 1500; // get 1500 points for each kiwi
            score -= game.getPredatorsRemaining() * 500; // take 500 points for each predator remaining
            score += player.getCurrentBackpackWeight() * 100; // add 1000 points for each item
            //score += player.hasItem(player.getInventory())
            //score += player.
            //score += game.getPlayer().getStaminaLevel() * 50;
            System.out.print(player.getInventory());
            
            /*
            kiwi - 1000
            stamina remaining * 50
            
            
            
            */
            
            System.out.print("Your score: "+score);
        }
            
            
            
        // check for "game over" or "game won"
        if (game.getState() == GameState.LOST) {
            JOptionPane.showMessageDialog(
                    this,
                    game.getLoseMessage(), "Game over!",
                    JOptionPane.INFORMATION_MESSAGE);
            game.createNewGame();
        } else if (game.getState() == GameState.WON) {
            JOptionPane.showMessageDialog(
                    this,
                    game.getWinMessage(), "Well Done!",
                    JOptionPane.INFORMATION_MESSAGE);
            game.createNewGame();
        } else if (game.messageForPlayer()) {
            JOptionPane.showMessageDialog(
                    this,
                    game.getPlayerMessage(), "Important Information",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        
    }

    private void setAsGameListener() {
        game.addGameEventListener(this);
    }

    /**
     * Updates the state of the UI based on the state of the game.
     */
    private void update() {
        // update the grid square panels
        Component[] components = pnlIsland.getComponents();
        for (Component c : components) {
            // all components in the panel are GridSquarePanels,
            // so we can safely cast
            GridSquarePanel gsp = (GridSquarePanel) c;
            gsp.update();
        }

        // update player information
        int[] playerValues = game.getPlayerValues();
        txtPlayerName.setText(game.getPlayerName());
        progPlayerStamina.setMaximum(playerValues[Game.MAXSTAMINA_INDEX]);
        progPlayerStamina.setValue(playerValues[Game.STAMINA_INDEX]);
        progBackpackWeight.setMaximum(playerValues[Game.MAXWEIGHT_INDEX]);
        progBackpackWeight.setValue(playerValues[Game.WEIGHT_INDEX]);
        progBackpackSize.setMaximum(playerValues[Game.MAXSIZE_INDEX]);
        progBackpackSize.setValue(playerValues[Game.SIZE_INDEX]);

        //Update Kiwi and Predator information
        txtKiwisCounted.setText(Integer.toString(game.getKiwiCount()));
        txtPredatorsLeft.setText(Integer.toString(game.getPredatorsRemaining()));

        // update inventory list
        listInventory.setListData(game.getPlayerInventory());
        listInventory.clearSelection();
        listInventory.setToolTipText(null);
        btnUse.setEnabled(false);
        btnDrop.setEnabled(false);

        // update list of visible objects
        listObjects.setListData(game.getOccupantsPlayerPosition());
        listObjects.clearSelection();
        listObjects.setToolTipText(null);
        btnCollect.setEnabled(false);
        btnCount.setEnabled(false);

        // update movement buttons
        btnMoveNorth.setEnabled(game.isPlayerMovePossible(MoveDirection.NORTH));
        btnMoveEast.setEnabled(game.isPlayerMovePossible(MoveDirection.EAST));
        btnMoveSouth.setEnabled(game.isPlayerMovePossible(MoveDirection.SOUTH));
        btnMoveWest.setEnabled(game.isPlayerMovePossible(MoveDirection.WEST));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        javax.swing.JPanel pnlContent = new javax.swing.JPanel();
        pnlIsland = new javax.swing.JPanel();
        javax.swing.JPanel pnlControls = new javax.swing.JPanel();
        javax.swing.JPanel pnlPlayer = new javax.swing.JPanel();
        javax.swing.JPanel pnlPlayerData = new javax.swing.JPanel();
        javax.swing.JLabel lblPlayerName = new javax.swing.JLabel();
        txtPlayerName = new javax.swing.JLabel();
        javax.swing.JLabel lblPlayerStamina = new javax.swing.JLabel();
        progPlayerStamina = new javax.swing.JProgressBar();
        javax.swing.JLabel lblBackpackWeight = new javax.swing.JLabel();
        progBackpackWeight = new javax.swing.JProgressBar();
        javax.swing.JLabel lblBackpackSize = new javax.swing.JLabel();
        progBackpackSize = new javax.swing.JProgressBar();
        lblPredators = new javax.swing.JLabel();
        lblKiwisCounted = new javax.swing.JLabel();
        txtKiwisCounted = new javax.swing.JLabel();
        txtPredatorsLeft = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Diffculty = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        diffcul = new javax.swing.JLabel();
        javax.swing.JPanel pnlMovement = new javax.swing.JPanel();
        btnMoveNorth = new javax.swing.JButton();
        btnMoveSouth = new javax.swing.JButton();
        btnMoveEast = new javax.swing.JButton();
        btnMoveWest = new javax.swing.JButton();
        javax.swing.JPanel pnlInventory = new javax.swing.JPanel();
        javax.swing.JScrollPane scrlInventory = new javax.swing.JScrollPane();
        listInventory = new javax.swing.JList();
        btnDrop = new javax.swing.JButton();
        btnUse = new javax.swing.JButton();
        javax.swing.JPanel pnlObjects = new javax.swing.JPanel();
        javax.swing.JScrollPane scrlObjects = new javax.swing.JScrollPane();
        listObjects = new javax.swing.JList();
        btnCollect = new javax.swing.JButton();
        btnCount = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Easy = new javax.swing.JMenuItem();
        Medium = new javax.swing.JMenuItem();
        Hard = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        Exit = new javax.swing.JMenuItem();
        Restart = new javax.swing.JMenuItem();
        Help = new javax.swing.JMenu();
        rules = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kiwi Island");
        setResizable(false);

        pnlContent.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlContent.setLayout(new java.awt.BorderLayout(10, 0));

        javax.swing.GroupLayout pnlIslandLayout = new javax.swing.GroupLayout(pnlIsland);
        pnlIsland.setLayout(pnlIslandLayout);
        pnlIslandLayout.setHorizontalGroup(
            pnlIslandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 492, Short.MAX_VALUE)
        );
        pnlIslandLayout.setVerticalGroup(
            pnlIslandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 597, Short.MAX_VALUE)
        );

        pnlContent.add(pnlIsland, java.awt.BorderLayout.CENTER);

        pnlControls.setLayout(new java.awt.GridBagLayout());

        pnlPlayer.setBorder(javax.swing.BorderFactory.createTitledBorder("Player"));

        pnlPlayerData.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        lblPlayerName.setText("Name:");

        lblPlayerStamina.setText("Stamina:");

        progPlayerStamina.setStringPainted(true);
        progPlayerStamina.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                progPlayerStaminaStateChanged(evt);
            }
        });

        lblBackpackWeight.setText("Backpack Weight:");

        progBackpackWeight.setStringPainted(true);

        lblBackpackSize.setText("Backpack Size:");

        progBackpackSize.setStringPainted(true);

        lblPredators.setText("Predators Left:");

        lblKiwisCounted.setText("Kiwis Counted :");

        txtKiwisCounted.setText("0");

        txtPredatorsLeft.setText("P");

        jLabel2.setText("Diffculty:");

        javax.swing.GroupLayout pnlPlayerDataLayout = new javax.swing.GroupLayout(pnlPlayerData);
        pnlPlayerData.setLayout(pnlPlayerDataLayout);
        pnlPlayerDataLayout.setHorizontalGroup(
            pnlPlayerDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPlayerDataLayout.createSequentialGroup()
                .addGroup(pnlPlayerDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPlayerDataLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(pnlPlayerDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPlayerName)
                            .addGroup(pnlPlayerDataLayout.createSequentialGroup()
                                .addComponent(lblPlayerStamina)
                                .addGap(68, 68, 68)
                                .addComponent(progPlayerStamina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlPlayerDataLayout.createSequentialGroup()
                                .addComponent(lblBackpackWeight)
                                .addGap(25, 25, 25)
                                .addComponent(progBackpackWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlPlayerDataLayout.createSequentialGroup()
                                .addComponent(lblBackpackSize)
                                .addGap(40, 40, 40)
                                .addGroup(pnlPlayerDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlPlayerDataLayout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(42, 42, 42)
                                        .addComponent(Diffculty, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(progBackpackSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlPlayerDataLayout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(diffcul))))))
                    .addGroup(pnlPlayerDataLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblPredators)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPredatorsLeft))
                    .addGroup(pnlPlayerDataLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblKiwisCounted)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtKiwisCounted)))
                .addGap(28, 28, 28))
        );
        pnlPlayerDataLayout.setVerticalGroup(
            pnlPlayerDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPlayerDataLayout.createSequentialGroup()
                .addComponent(lblPlayerName)
                .addGap(1, 1, 1)
                .addGroup(pnlPlayerDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPlayerStamina)
                    .addComponent(progPlayerStamina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlPlayerDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBackpackWeight)
                    .addComponent(progBackpackWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlPlayerDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBackpackSize)
                    .addComponent(progBackpackSize, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlPlayerDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPlayerDataLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(pnlPlayerDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(Diffculty))
                        .addGap(1, 1, 1)
                        .addGroup(pnlPlayerDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(diffcul)))
                    .addGroup(pnlPlayerDataLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPlayerDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPredators)
                            .addComponent(txtPredatorsLeft))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPlayerDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKiwisCounted)
                            .addComponent(txtKiwisCounted))))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlPlayerLayout = new javax.swing.GroupLayout(pnlPlayer);
        pnlPlayer.setLayout(pnlPlayerLayout);
        pnlPlayerLayout.setHorizontalGroup(
            pnlPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPlayerData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlPlayerLayout.setVerticalGroup(
            pnlPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPlayerData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        pnlControls.add(pnlPlayer, gridBagConstraints);

        pnlMovement.setBorder(javax.swing.BorderFactory.createTitledBorder("Movement"));
        pnlMovement.setLayout(new java.awt.GridBagLayout());

        btnMoveNorth.setText("N");
        btnMoveNorth.setFocusable(false);
        btnMoveNorth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveNorthActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlMovement.add(btnMoveNorth, gridBagConstraints);

        btnMoveSouth.setText("S");
        btnMoveSouth.setFocusable(false);
        btnMoveSouth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveSouthActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlMovement.add(btnMoveSouth, gridBagConstraints);

        btnMoveEast.setText("E");
        btnMoveEast.setFocusable(false);
        btnMoveEast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveEastActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlMovement.add(btnMoveEast, gridBagConstraints);

        btnMoveWest.setText("W");
        btnMoveWest.setFocusable(false);
        btnMoveWest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveWestActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlMovement.add(btnMoveWest, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        pnlControls.add(pnlMovement, gridBagConstraints);

        pnlInventory.setBorder(javax.swing.BorderFactory.createTitledBorder("Inventory"));
        pnlInventory.setLayout(new java.awt.GridBagLayout());

        listInventory.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listInventory.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listInventory.setVisibleRowCount(3);
        listInventory.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listInventoryValueChanged(evt);
            }
        });
        scrlInventory.setViewportView(listInventory);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlInventory.add(scrlInventory, gridBagConstraints);

        btnDrop.setText("Drop");
        btnDrop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDropActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlInventory.add(btnDrop, gridBagConstraints);

        btnUse.setText("Use");
        btnUse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlInventory.add(btnUse, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlControls.add(pnlInventory, gridBagConstraints);

        pnlObjects.setBorder(javax.swing.BorderFactory.createTitledBorder("Objects"));
        java.awt.GridBagLayout pnlObjectsLayout = new java.awt.GridBagLayout();
        pnlObjectsLayout.columnWidths = new int[] {0, 5, 0};
        pnlObjectsLayout.rowHeights = new int[] {0, 5, 0};
        pnlObjects.setLayout(pnlObjectsLayout);

        listObjects.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listObjects.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listObjects.setVisibleRowCount(3);
        listObjects.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listObjectsValueChanged(evt);
            }
        });
        scrlObjects.setViewportView(listObjects);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlObjects.add(scrlObjects, gridBagConstraints);

        btnCollect.setText("Collect");
        btnCollect.setToolTipText("");
        btnCollect.setMaximumSize(new java.awt.Dimension(61, 23));
        btnCollect.setMinimumSize(new java.awt.Dimension(61, 23));
        btnCollect.setPreferredSize(new java.awt.Dimension(61, 23));
        btnCollect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCollectActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlObjects.add(btnCollect, gridBagConstraints);

        btnCount.setText("Count");
        btnCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCountActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlObjects.add(btnCount, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlControls.add(pnlObjects, gridBagConstraints);

        pnlContent.add(pnlControls, java.awt.BorderLayout.EAST);

        getContentPane().add(pnlContent, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Select Level");

        Easy.setText("Easy");
        Easy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EasyActionPerformed(evt);
            }
        });
        jMenu1.add(Easy);

        Medium.setText("Medium");
        Medium.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MediumActionPerformed(evt);
            }
        });
        jMenu1.add(Medium);

        Hard.setText("Hard");
        Hard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HardActionPerformed(evt);
            }
        });
        jMenu1.add(Hard);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Quit");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        Exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        jMenu2.add(Exit);

        Restart.setText("Restart");
        Restart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RestartActionPerformed(evt);
            }
        });
        jMenu2.add(Restart);

        jMenuBar1.add(jMenu2);

        Help.setText("Help");
        Help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HelpActionPerformed(evt);
            }
        });

        rules.setText("Rules/instructions");
        rules.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rulesActionPerformed(evt);
            }
        });
        Help.add(rules);

        jMenuBar1.add(Help);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setText(String text){
        diffcul.setText(text);
    }
    
    private void btnMoveEastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveEastActionPerformed
        game.playerMove(MoveDirection.EAST);
    }//GEN-LAST:event_btnMoveEastActionPerformed

    private void btnMoveNorthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveNorthActionPerformed
        game.playerMove(MoveDirection.NORTH);
    }//GEN-LAST:event_btnMoveNorthActionPerformed

    private void btnMoveSouthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveSouthActionPerformed
        game.playerMove(MoveDirection.SOUTH);
    }//GEN-LAST:event_btnMoveSouthActionPerformed

    private void btnMoveWestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveWestActionPerformed
        game.playerMove(MoveDirection.WEST);
    }//GEN-LAST:event_btnMoveWestActionPerformed

    private void btnCollectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCollectActionPerformed
        Object obj = listObjects.getSelectedValue();
        game.collectItem(obj);
    }//GEN-LAST:event_btnCollectActionPerformed

    private void btnDropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDropActionPerformed
        game.dropItem(listInventory.getSelectedValue());
    }//GEN-LAST:event_btnDropActionPerformed

    private void listObjectsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listObjectsValueChanged
        Object occ = listObjects.getSelectedValue();
        if (occ != null) {
            btnCollect.setEnabled(game.canCollect(occ));
            btnCount.setEnabled(game.canCount(occ));
            listObjects.setToolTipText(game.getOccupantDescription(occ));
            listObjects.setFocusable(false);
        }
    }//GEN-LAST:event_listObjectsValueChanged

    private void btnUseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUseActionPerformed
        game.useItem(listInventory.getSelectedValue());
    }//GEN-LAST:event_btnUseActionPerformed

    private void listInventoryValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listInventoryValueChanged
        Object item = listInventory.getSelectedValue();
        btnDrop.setEnabled(true);
        if (item != null) {
            btnUse.setEnabled(game.canUse(item));
            listInventory.setToolTipText(game.getOccupantDescription(item));
            listInventory.setFocusable(false);
        }
    }//GEN-LAST:event_listInventoryValueChanged

    private void btnCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCountActionPerformed
        game.countKiwi();
    }//GEN-LAST:event_btnCountActionPerformed

    private void EasyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EasyActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(
                this,

                "You have selected EASY difficulty!\n          Press OK to start!", "New Game",

                JOptionPane.INFORMATION_MESSAGE);
        game.createNewGame();
        game.diff.easy();
    }//GEN-LAST:event_EasyActionPerformed

    /**
     * Once user click Exit button, message will pop-ups and game ends.
     */
    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed

        JOptionPane.showMessageDialog(
                this,
                game.getLoseMessage(), "Thank you for playing!",
                JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);//Ending game after message pop-up
    }//GEN-LAST:event_ExitActionPerformed
    /**
     * Restart game after message pop-ups
     */
    private void RestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RestartActionPerformed

        JOptionPane.showMessageDialog(
                this,
                " Restarting game!", " Restarting game!",
                JOptionPane.INFORMATION_MESSAGE);
        game.createNewGame();// Restarting game after message popups.
    }//GEN-LAST:event_RestartActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void HardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HardActionPerformed
        JOptionPane.showMessageDialog(
                this,

                 "You have selected HARD difficulty!\n          Press OK to start!", "New Game",

                JOptionPane.INFORMATION_MESSAGE);
        game.createNewGame();// Restarting game after message popups.
        game.diff.hard();
    }//GEN-LAST:event_HardActionPerformed

    private void MediumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MediumActionPerformed
        JOptionPane.showMessageDialog(
                this,

                "You have selected MEDIUM difficulty!\n          Press OK to start!", "New Game",

                JOptionPane.INFORMATION_MESSAGE);
        game.createNewGame();// Restarting game after message popups.
        game.diff.medium();
    }//GEN-LAST:event_MediumActionPerformed

    private void HelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HelpActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_HelpActionPerformed

    private void rulesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rulesActionPerformed
        //When user click on "instructions" button, it pop-ups the
        //dialogue box witout instructions and rules
        JOptionPane.showMessageDialog(
                this, "How to play:\n"
                + "\nYour goal is to count each kiwi in the game and trap all predators.\n"
                + "Each square can contain either a predator (to trap), a kiwi (to count)\n"
                + "or an item(to collect/use, set or fix).\n"
                + "\n"
                + "Use the arrow keys, ASDW or press the NSEW buttons to move around the island.\n"
                + "Each move uses stamina, the amount of stamina used each move depends\n"
                + "on the terrain you have moved too.\n"
                + "\nIf you run out of stamina or are severely injured, you will lose the game."
                + "\n"
                + "There are a number of hazards on the island, so take care!\n"
                + "\n"
                + "The goal of the game is to raise awareness of the conservation\n"
                + "of kiwis.\n"
                + "\nkiwisforkiwi.org",
                "Instructions",
                JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_rulesActionPerformed

    private void progPlayerStaminaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_progPlayerStaminaStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_progPlayerStaminaStateChanged

    /**
     * Creates and initialises the island grid.
     */
    private void initIslandGrid() {
        // Add the grid
        int rows = game.getNumRows();
        int columns = game.getNumColumns();
        // set up the layout manager for the island grid panel
        pnlIsland.setLayout(new GridLayout(rows, columns));
        // create all the grid square panels and add them to the panel
        // the layout manager of the panel takes care of assigning them to the
        // the right position
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                pnlIsland.add(new GridSquarePanel(game, row, col));
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Diffculty;
    private javax.swing.JMenuItem Easy;
    private javax.swing.JMenuItem Exit;
    private javax.swing.JMenuItem Hard;
    private javax.swing.JMenu Help;
    private javax.swing.JMenuItem Medium;
    private javax.swing.JMenuItem Restart;
    private javax.swing.JButton btnCollect;
    private javax.swing.JButton btnCount;
    private javax.swing.JButton btnDrop;
    private javax.swing.JButton btnMoveEast;
    private javax.swing.JButton btnMoveNorth;
    private javax.swing.JButton btnMoveSouth;
    private javax.swing.JButton btnMoveWest;
    private javax.swing.JButton btnUse;
    private javax.swing.JLabel diffcul;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblKiwisCounted;
    private javax.swing.JLabel lblPredators;
    private javax.swing.JList listInventory;
    private javax.swing.JList listObjects;
    private javax.swing.JPanel pnlIsland;
    private javax.swing.JProgressBar progBackpackSize;
    private javax.swing.JProgressBar progBackpackWeight;
    private javax.swing.JProgressBar progPlayerStamina;
    private javax.swing.JMenuItem rules;
    private javax.swing.JLabel txtKiwisCounted;
    private javax.swing.JLabel txtPlayerName;
    private javax.swing.JLabel txtPredatorsLeft;
    // End of variables declaration//GEN-END:variables
    @Override
    public void actionPerformed(ActionEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            game.playerMove(MoveDirection.NORTH);
        }
        if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            game.playerMove(MoveDirection.SOUTH);
        }
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            game.playerMove(MoveDirection.WEST);
        }
        if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            game.playerMove(MoveDirection.EAST);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 

}
