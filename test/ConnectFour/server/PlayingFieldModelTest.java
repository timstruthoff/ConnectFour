/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour.server;

import ConnectFour.server.PlayerStore.Player;
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
public class PlayingFieldModelTest {

    private Player testPlayerOne = new Player("test", "192.168.2.3", 12345);
    private Player testPlayerTwo = new Player("testTwo", "192.168.2.4", 12345);

    public PlayingFieldModelTest() {
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
     * Test of cleanPlayingField method, of class PlayingFieldModel.
     */
    @Test
    public void testCleanPlayingField() {
        System.out.println("cleanPlayingField");
        PlayingFieldModel instance = new PlayingFieldModel(2, 2);

        instance.setMark(testPlayerOne, 0, 0);
        instance.cleanPlayingField();
        String expResult = "[ -, -,  ][ -, -,  ]";
        String result = instance.toString().replaceAll(System.lineSeparator(), "");
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidColumnNumber method, of class PlayingFieldModel.
     */
    @Test
    public void testIsValidColumnNumber() {
        System.out.println("isValidColumnNumber");

        PlayingFieldModel instance = new PlayingFieldModel(5, 5);
        boolean expResult = true;
        boolean result = instance.isValidColumnNumber(1);
        assertEquals(expResult, result);

        boolean expResult2 = false;
        boolean result2 = instance.isValidColumnNumber(5);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of getFreeRowInColumn method, of class PlayingFieldModel.
     */
    @Test
    public void testGetFreeRowInColumn() {
        System.out.println("getFreeRowInColumn");

        int pColumn = 0;
        PlayingFieldModel instance = new PlayingFieldModel(5, 5);

        int expResult = 4;
        int result = instance.getFreeRowInColumn(pColumn);
        assertEquals(expResult, result);

        instance.setMark(testPlayerOne, pColumn, result);
        int expResult2 = 3;
        int result2 = instance.getFreeRowInColumn(pColumn);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of isFieldFull method, of class PlayingFieldModel.
     */
    @Test
    public void testIsFieldFull() {
        System.out.println("isFieldFull");
        PlayingFieldModel instance = new PlayingFieldModel(2, 2);
        instance.setMark(testPlayerOne, 0, 0);
        instance.setMark(testPlayerOne, 1, 0);
        instance.setMark(testPlayerOne, 0, 1);
        instance.setMark(testPlayerOne, 1, 1);
        boolean expResult = true;
        boolean result = instance.isFieldFull();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMark method, of class PlayingFieldModel.
     */
    @Test
    public void testSetMark() {
        System.out.println("setMark");
        PlayingFieldModel instance = new PlayingFieldModel(2, 2);

        instance.setMark(testPlayerOne, 0, 0);
        String expResult = "[ test, -,  ][ -, -,  ]";
        String result = instance.toString().replaceAll(System.lineSeparator(), "");
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class PlayingFieldModel.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        PlayingFieldModel instance = new PlayingFieldModel(2, 2);

        instance.setMark(testPlayerOne, 0, 0);
        String expResult = "[ test, -,  ][ -, -,  ]";
        String result = instance.toString().replaceAll(System.lineSeparator(), "");
        assertEquals(expResult, result);
    }

    /**
     * Test of getWinner method, of class PlayingFieldModel.
     */
    @Test
    public void testHasGameEnded() {
        System.out.println("hasGameEnded");
        PlayingFieldModel instance = new PlayingFieldModel(4, 4);

        instance.setMark(testPlayerOne, 0, 0);
        Player expResult = null;
        Player result = instance.getWinner();
        assertEquals(expResult, result);

        // Positive test in all directions
        instance.cleanPlayingField();
        instance.setMark(testPlayerOne, 0, 0);
        instance.setMark(testPlayerOne, 0, 1);
        instance.setMark(testPlayerOne, 0, 2);
        instance.setMark(testPlayerOne, 0, 3);
        Player expResult2 = testPlayerOne;
        Player result2 = instance.getWinner();
        assertEquals(expResult2, result2);

        instance.cleanPlayingField();
        instance.setMark(testPlayerOne, 0, 0);
        instance.setMark(testPlayerOne, 1, 0);
        instance.setMark(testPlayerOne, 2, 0);
        instance.setMark(testPlayerOne, 3, 0);
        Player expResult3 = testPlayerOne;
        Player result3 = instance.getWinner();
        assertEquals(expResult3, result3);

        instance.cleanPlayingField();
        instance.setMark(testPlayerOne, 0, 0);
        instance.setMark(testPlayerOne, 1, 1);
        instance.setMark(testPlayerOne, 2, 2);
        instance.setMark(testPlayerOne, 3, 3);
        Player expResult4 = testPlayerOne;
        Player result4 = instance.getWinner();
        assertEquals(expResult4, result4);

        instance.cleanPlayingField();
        instance.setMark(testPlayerOne, 3, 0);
        instance.setMark(testPlayerOne, 2, 1);
        instance.setMark(testPlayerOne, 1, 2);
        instance.setMark(testPlayerOne, 0, 3);
        Player expResult5 = testPlayerOne;
        Player result5 = instance.getWinner();
        assertEquals(expResult5, result5);

        // Negative test in all directions
        instance.cleanPlayingField();
        instance.setMark(testPlayerOne, 0, 0);
        instance.setMark(testPlayerTwo, 0, 1);
        instance.setMark(testPlayerOne, 0, 2);
        instance.setMark(testPlayerOne, 0, 3);
        Player expResult6 = null;
        Player result6 = instance.getWinner();
        assertEquals(expResult6, result6);
    }

}
