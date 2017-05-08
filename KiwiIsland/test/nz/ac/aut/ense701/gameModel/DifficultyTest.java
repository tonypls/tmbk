/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author newuser
 */
public class DifficultyTest extends junit.framework.TestCase
{
    
    public DifficultyTest() {
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
     * Test of easy method, of class Difficulty.
     */
    @Test
    public void testEasy() {
        System.out.println("easy");
        Difficulty instance = new Difficulty();
        instance.easy();
         if(instance.MOVE_STAMINA==0.5){
          assertEquals(0.5, instance.getMOVE_STAMINA());  
        }
         
    }

    /**
     * Test of medium method, of class Difficulty.
     */
    @Test
    public void testMedium() {
        System.out.println("medium");
        Difficulty instance = new Difficulty();
        instance.medium();
        if(instance.MOVE_STAMINA==1.0){
          assertEquals(1.0, instance.getMOVE_STAMINA());  
        }
        
    }

    /**
     * Test of hard method, of class Difficulty.
     */
    @Test
    public void testHard() {
        System.out.println("hard");
        Difficulty instance = new Difficulty();
        instance.hard();
       if(instance.MOVE_STAMINA==2.0){
          assertEquals(2.0, instance.getMOVE_STAMINA());  
        }
    }

    /**
     * Test of setMOVE_STAMINA method, of class Difficulty.
     */
    @Test
    public void testSetMOVE_STAMINA() {
        System.out.println("setMOVE_STAMINA");
        double MOVE_STAMINA = 0.0;
        Difficulty instance = new Difficulty();
        instance.setMOVE_STAMINA(MOVE_STAMINA);
        
    }

    /**
     * Test of getMOVE_STAMINA method, of class Difficulty.
     */
    @Test
    public void testGetMOVE_STAMINA() {
        System.out.println("getMOVE_STAMINA");
        Difficulty instance = new Difficulty();
        double expResult = 1.0;
        double result = instance.getMOVE_STAMINA();
        assertEquals(expResult, result, 1.0);
       
    }
    
}
