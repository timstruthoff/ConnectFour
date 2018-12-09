/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour;

import ConnectFour.server.*;
import ConnectFour.client.*;
import java.util.concurrent.TimeUnit;

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
public class DublicateNameTest {

    private NetworkingClient clientOne;
    private GameWindow windowOne;
    private ClientGameLogic gameLogicOne;

    private NetworkingClient clientTwo;
    private GameWindow windowTwo;
    private ClientGameLogic gameLogicTwo;

    public DublicateNameTest() {
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

    @Test
    public void testDublicateName() throws InterruptedException {
        // Start new game window with selected server ip.
        GameServer server = new GameServer();

        this.startClientOne();
        TimeUnit.MILLISECONDS.sleep(2000);
        this.startClientTwo();
        TimeUnit.MILLISECONDS.sleep(10000);
    }

    public void startClientOne() {
        // Start new game window with selected server ip.
        clientOne = new NetworkingClient("127.0.0.1");
        windowOne = new GameWindow();
        gameLogicOne = new ClientGameLogic(clientOne, windowOne, "testName");

        gameLogicOne.setWindow(windowOne);
        gameLogicOne.setClient(clientOne);

        windowOne.setGameLogic(gameLogicOne);

        clientOne.setGameLogic(gameLogicOne);
        clientOne.setWindow(windowTwo);
    }

    public void startClientTwo() {
        // Start new game window with selected server ip.
        clientTwo = new NetworkingClient("127.0.0.1");
        windowTwo = new GameWindow();
        gameLogicTwo = new ClientGameLogic(clientTwo, windowTwo, "testName");

        gameLogicTwo.setWindow(windowTwo);
        gameLogicTwo.setClient(clientTwo);

        windowTwo.setGameLogic(gameLogicTwo);

        clientTwo.setGameLogic(gameLogicTwo);
        clientTwo.setWindow(windowTwo);
    }
}
