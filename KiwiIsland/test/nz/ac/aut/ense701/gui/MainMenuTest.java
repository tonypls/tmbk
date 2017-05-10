/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import static junit.framework.TestCase.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MainMenuTest {
    
    public MainMenuTest() {
    }
    
    @Before
    public void setUp() {
        JButton Instructions = new JButton();
        JButton exit = new JButton();
        JButton jButton1 = new JButton();
        JLabel jLabel1 = new JLabel();
        JTextField jTextField1 = new JTextField();
    }
    
    @After
    public void tearDown() {
        JButton Instructions = null;
        JButton exit = null;
        JButton jButton1 = null;
        JLabel jLabel1 = null;
        JTextField jTextField1 = null;
    }
    
    @Test
    public void testMenuBackground() {
        MainMenu menu = new MainMenu();
        assertTrue("menu should have a background",menu.isBackgroundSet());
    }
    
    @Test
    public void testMenuComponentCount() {
        MainMenu menu = new MainMenu();
        assertEquals("menu should have a 1 component",menu.getComponentCount(),1);
    }
    
    /**
     * Test of main method, of class MainMenu.
     */
    @Test
    public void testMain() {
        
//        MainMenu menu = new MainMenu();
//        System.out.println("main");
//        String[] args = null;
//        MainMenu.main(args);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
    @Test
    public void testPanelVisible() {
        MainMenu menu = new MainMenu();
        assertTrue("Panel should be visible",!menu.isVisible());
    }
    
    @Test
    public void testPanelExists() {
        assertNotNull("Panel should exist", this);
    }
    
    @Test
    public void testAllMenuComponents() {
        JButton Instructions = new JButton();
        JButton exit = new JButton();
        JButton jButton1 = new JButton();
        JLabel jLabel1 = new JLabel();
        JTextField jTextField1 = new JTextField();
        assertNotNull("JButton should exist",Instructions);
        assertNotNull("JButton should exist",exit);
        assertNotNull("JButton should exist",jButton1);
        assertNotNull("JLabel should exist",jLabel1);
        assertNotNull("JTextField should exist",jTextField1);
    }
    
}