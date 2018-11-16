/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour.server.PlayerStore;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tmst
 */
public class PlayerTest {

    public PlayerTest() {
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
     * Test of getID method, of class Player.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");

        Player instance = new Player("TestName", "192.168.2.3", 12345);
        String result = instance.getID();
        assertTrue(result instanceof String);
    }

    /**
     * Test of setName method, of class Player.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");

        Player instance = new Player("TestName", "192.168.2.3", 12345);
        instance.setName("TestName2");
        String expResult = "TestName2";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");

        Player instance = new Player("TestName", "192.168.2.3", 12345);
        String expResult = "TestName";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIpAddress method, of class Player.
     */
    @Test
    public void testGetIpAddress() {
        System.out.println("getIpAddress");

        Player instance = new Player("TestName", "192.168.2.3", 12345);
        String expResult = "192.168.2.3";
        String result = instance.getIpAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPort method, of class Player.
     */
    @Test
    public void testGetPort() {
        System.out.println("getPort");

        Player instance = new Player("TestName", "192.168.2.3", 12345);
        int expResult = 12345;
        int result = instance.getPort();
        assertEquals(expResult, result);
    }

}
