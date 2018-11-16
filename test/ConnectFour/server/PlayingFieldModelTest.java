/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour.server;

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

        instance.setMark("test", 0, 0);
        instance.cleanPlayingField();
        String expResult = "[, , ] [, , ] ";
        String result = instance.toString().replaceAll(System.lineSeparator(), " ");
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

        instance.setMark("test", pColumn, result);
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
        instance.setMark("test", 0, 0);
        instance.setMark("test", 1, 0);
        instance.setMark("test", 0, 1);
        instance.setMark("test", 1, 1);
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

        instance.setMark("test", 0, 0);
        String expResult = "[test, , ] [, , ] ";
        String result = instance.toString().replaceAll(System.lineSeparator(), " ");
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class PlayingFieldModel.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        PlayingFieldModel instance = new PlayingFieldModel(2, 2);

        instance.setMark("test", 0, 0);
        String expResult = "[test, , ] [, , ] ";
        String result = instance.toString().replaceAll(System.lineSeparator(), " ");
        assertEquals(expResult, result);
    }

    /**
     * Test of hasGameEnded method, of class PlayingFieldModel.
     */
    @Test
    public void testHasGameEnded() {
        System.out.println("hasGameEnded");
        PlayingFieldModel instance = new PlayingFieldModel(4, 4);

        instance.setMark("test", 0, 0);
        boolean expResult = false;
        boolean result = instance.hasGameEnded();
        assertEquals(expResult, result);

        instance.cleanPlayingField();
        instance.setMark("test", 0, 0);
        instance.setMark("test", 0, 1);
        instance.setMark("test", 0, 2);
        instance.setMark("test", 0, 3);
        boolean expResult2 = true;
        boolean result2 = instance.hasGameEnded();
        assertEquals(expResult2, result2);

        instance.cleanPlayingField();
        instance.setMark("test", 0, 0);
        instance.setMark("test", 1, 0);
        instance.setMark("test", 2, 0);
        instance.setMark("test", 3, 0);
        boolean expResult3 = true;
        boolean result3 = instance.hasGameEnded();
        assertEquals(expResult3, result3);

        instance.cleanPlayingField();
        instance.setMark("test", 0, 0);
        instance.setMark("test", 1, 1);
        instance.setMark("test", 2, 2);
        instance.setMark("test", 3, 3);
        boolean expResult4 = true;
        boolean result4 = instance.hasGameEnded();
        assertEquals(expResult4, result4);

        instance.cleanPlayingField();
        instance.setMark("test", 3, 0);
        instance.setMark("test", 2, 1);
        instance.setMark("test", 1, 2);
        instance.setMark("test", 0, 3);
        boolean expResult5 = true;
        boolean result5 = instance.hasGameEnded();
        assertEquals(expResult5, result5);
    }

}
