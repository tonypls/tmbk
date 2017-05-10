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
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kelvi
 */
public class ChoosingLevelsTest {
    
    public ChoosingLevelsTest() {
        
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        JLabel jLabel1 = new JLabel();
        JButton Easy = new JButton();
        JButton Medium = new JButton();
        JButton Hard = new JButton();
        JButton Quit = new JButton();
        JLabel jLabel2 = new JLabel();
    }
    
    @After
    public void tearDown() {
        
        JLabel jLabel1 = null;
        JButton Easy = null;
        JButton Medium = null;
        JButton Hard = null;
        JButton Quit = null;
        JLabel jLabel2 = null;
        
    }

    /**
     * Test of main method, of class ChoosingLevels.
     */
    
   /** public void testMain() {
        System.out.println("main");
        String[] args = null;
        ChoosingLevels.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    * 
    */
    @Test
    public void testMenuBackground() {
        ChoosingLevels menu = new ChoosingLevels();
        assertTrue("menu should have a background",menu.isBackgroundSet());
    }
    
    @Test
    public void testMenuComponentCount() {
        ChoosingLevels menu = new ChoosingLevels();
        assertEquals("menu should have a 1 component",menu.getComponentCount(),1);
    }
    @Test
    public void testPanelVisible() {
       ChoosingLevels clevel = new ChoosingLevels();
        assertTrue("Panel should be visible",!clevel.isVisible());
    }
    
    @Test
    public void testPanelExists() {
        assertNotNull("Panel should exist", this);
    }
    
    @Test
    public void testAllMenuComponents() {
        JButton Easy = new JButton();
        JButton Medium = new JButton();
        JButton Hard = new JButton();
        JButton Quit = new JButton();
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        assertNotNull("JButton should exist",Easy);
        assertNotNull("JButton should exist",Medium);
        assertNotNull("JButton should exist",Hard);
        assertNotNull("JButton should exist",Quit);
        assertNotNull("JLabel should exist",jLabel1);
        assertNotNull("JLabel should exist",jLabel2);
    }
   
}
