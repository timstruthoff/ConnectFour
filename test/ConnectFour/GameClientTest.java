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
public class GameClientTest {

    public GameClientTest() {
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
    public void testNetworkSetUp() throws InterruptedException {
        // Start new game window with selected server ip.
        GameServer server = new GameServer();
        NetworkingClient client = new NetworkingClient("127.0.0.1");

        TimeUnit.MILLISECONDS.sleep(300);
        client.sendPlayerName("TestName");
        TimeUnit.MILLISECONDS.sleep(300);

        NetworkingClient client2 = new NetworkingClient("127.0.0.1");
        TimeUnit.MILLISECONDS.sleep(300);
        client2.sendPlayerName("TestName2");
        TimeUnit.MILLISECONDS.sleep(300);
    }

}
