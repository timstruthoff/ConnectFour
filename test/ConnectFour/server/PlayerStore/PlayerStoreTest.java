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
        String pIpAddress = "192.168.2.4";
        int pPort = 12345;
        PlayerStore instance = new PlayerStore();
        Player p = instance.addPlayer(pIpAddress, pIpAddress, pPort);

        assertEquals(p.getIpAddress(), pIpAddress);
        assertEquals(p.getPort(), pPort);
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

        instance.addPlayer("test", "192.16.2.3", 12345);
        instance.addPlayer("test2", "192.16.2.3", 12346);
        int expResult2 = 2;
        int result2 = instance.getNumberOfPlayers();
        assertEquals(expResult2, result2);
    }

    /**
     * Test of getNumberOfPlayer method, of class PlayerStore.
     */
    @Test
    public void testGetNumberOfPlayer() {
        System.out.println("getNumberOfPlayer");
        PlayerStore instance = new PlayerStore();
        Player p = instance.addPlayer("TestName", "192.168.2.3", 12345);

        int expResult = 0;
        int result = instance.getNumberOfPlayer(p);
        assertEquals(expResult, result);

        Player p2 = instance.addPlayer("TestName2", "192.168.2.3", 12346);
        int expResult2 = 1;
        int result2 = instance.getNumberOfPlayer(p2);
        assertEquals(expResult2, result2);
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
