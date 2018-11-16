/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour.server.PlayerStore;

import java.util.List;
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
public class PlayerStoreTest {

    public PlayerStoreTest() {
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
     * Test of getPlayerBySocket method, of class PlayerStore.
     */
    @Test
    public void testGetPlayerBySocket() {
        System.out.println("getPlayerBySocket");
        String pIpAddress = "";
        int pPort = 0;
        PlayerStore instance = new PlayerStore();
        Player expResult = null;
        Player result = instance.getPlayerBySocket(pIpAddress, pPort);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerByName method, of class PlayerStore.
     */
    @Test
    public void testGetPlayerByName() {
        System.out.println("getPlayerByName");
        String pName = "";
        PlayerStore instance = new PlayerStore();
        Player expResult = null;
        Player result = instance.getPlayerByName(pName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerByID method, of class PlayerStore.
     */
    @Test
    public void testGetPlayerByID() {
        System.out.println("getPlayerByID");
        String pID = "";
        PlayerStore instance = new PlayerStore();
        Player expResult = null;
        Player result = instance.getPlayerByID(pID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerByNumber method, of class PlayerStore.
     */
    @Test
    public void testGetPlayerByNumber() {
        System.out.println("getPlayerByNumber");
        int pNumber = 0;
        PlayerStore instance = new PlayerStore();
        Player expResult = null;
        Player result = instance.getPlayerByNumber(pNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPlayer method, of class PlayerStore.
     */
    @Test
    public void testAddPlayer() {
        System.out.println("addPlayer");
        String pName = "";
        String pClientIP = "";
        int pPort = 0;
        PlayerStore instance = new PlayerStore();
        Player expResult = null;
        Player result = instance.addPlayer(pName, pClientIP, pPort);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberOfPlayers method, of class PlayerStore.
     */
    @Test
    public void testGetNumberOfPlayers() {
        System.out.println("getNumberOfPlayers");
        PlayerStore instance = new PlayerStore();
        int expResult = 0;
        int result = instance.getNumberOfPlayers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllExcept method, of class PlayerStore.
     */
    @Test
    public void testGetAllExcept() {
        System.out.println("getAllExcept");
        Player pException = null;
        PlayerStore instance = new PlayerStore();
        List<Player> expResult = null;
        List<Player> result = instance.getAllExcept(pException);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
