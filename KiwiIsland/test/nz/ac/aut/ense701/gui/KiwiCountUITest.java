/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gui;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import nz.ac.aut.ense701.gameModel.Game;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author TonyAlien
 */
public class KiwiCountUITest {

    public KiwiCountUITest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of gameStateChanged method, of class KiwiCountUI.
     */
//    @Test
//    public void testGameStateChanged() {
//        System.out.println("gameStateChanged");
//        KiwiCountUI instance = null;
//        instance.gameStateChanged();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of actionPerformed method, of class KiwiCountUI.
     */
//    @Test
//    public void testActionPerformed() {
//        System.out.println("actionPerformed");
//        ActionEvent e = null;
//        KiwiCountUI instance = null;
//        instance.actionPerformed(e);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of keyTyped method, of class KiwiCountUI.
     */
//    @Test
//    public void testKeyTyped() {
//        System.out.println("keyTyped");
//        KeyEvent e = null;
//        KiwiCountUI instance = null;
//        instance.keyTyped(e);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of keyPressed method, of class KiwiCountUI.
     */
    @Test
    public void testKeyPressed() throws AWTException {
        Game game = new Game();
        KiwiCountUI gui = new KiwiCountUI(game);
        gui.setFocusable(true);
        gui.requestFocus();
        KeyEvent key = new KeyEvent(gui, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, 'Z');
        gui.getKeyListeners()[0].keyPressed(key);
        System.out.println("keyPressed");

        try {
            gui.keyPressed(key);
        } catch (IllegalArgumentException expected) {
            fail("The test case is a prototype.");
        }
//        KiwiCountUI instance = null;
        //       instance.keyPressed(e);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of keyReleased method, of class KiwiCountUI.
     */
//    @Test
//    public void testKeyReleased() {
//        System.out.println("keyReleased");
//        KeyEvent e = null;
//        KiwiCountUI instance = null;
//        instance.keyReleased(e);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
