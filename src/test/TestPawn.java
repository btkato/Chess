package test;

import model.Board;
import model.GamePiece;
import model.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestPawn {

    private Board testBoard;

    private GamePiece testOne;
    private GamePiece testTwo;
    private GamePiece testThree;
    private GamePiece testFour;

    @BeforeEach
    void runBefore() {
        testBoard = new Board();
        testOne = new Pawn(3, 3, false, "test");
        testTwo = new Pawn(4, 6, true, "test2");
        testThree = new Pawn(1, 7, false, "test");
        testFour = new Pawn(7, 0, true, "test2");
    }

    @Test
    void testConstructor() {
        assertEquals("test", testOne.getPlayer());
        assertEquals(3, testOne.getX());
        assertEquals(3, testOne.getY());
        assertEquals(false, testOne.isColored());
    }

    @Test
    void testValidFirstMoveW() {
        Boolean move = testOne.move(3, 5);
        assertTrue(move);
        assertEquals(testOne.getX(), 3);
        assertEquals(testOne.getY(), 5);
    }

    @Test
    void testValidSecondMoveW() {
        Boolean firstMove = testOne.move(3, 5);
        Boolean secondMove = testOne.move(3, 6);
        assertTrue(secondMove);
        assertEquals(testOne.getX(), 3);
        assertEquals(testOne.getY(), 6);
    }

    @Test
    void testInvalidXMoveW() {
        Boolean move = testOne.move(2, 3);
        assertFalse(move);
        assertEquals(testOne.getX(), 3);
        assertEquals(testOne.getY(), 3);
    }

    @Test
    void testInvalidYFirstMoveW() {
        Boolean move = testOne.move(3, 6);
        assertFalse(move);
        assertEquals(testOne.getX(), 3);
        assertEquals(testOne.getY(), 3);
    }

    @Test
    void testInvalidYSecondMoveW() {
        Boolean move = testOne.move(3, 4);
        Boolean moveTwo = testOne.move(3, 6);
        assertTrue(move);
        assertFalse(moveTwo);
        assertEquals(testOne.getX(), 3);
        assertEquals(testOne.getY(), 4);
    }

    @Test
    void testInvalidMoveBackW() {
        Boolean move = testOne.move(3, 2);
        assertFalse(move);
        assertEquals(testOne.getX(), 3);
        assertEquals(testOne.getY(), 3);
    }

    @Test
    void testOutOfBoundsMoveW() {
        Boolean move = testThree.move(1, 8);
        assertFalse(move);
        assertEquals(testThree.getX(), 1);
        assertEquals(testThree.getY(), 7);
    }

    @Test
    void testValidFirstMoveB() {
        Boolean move = testTwo.move(4, 4);
        assertTrue(move);
        assertEquals(testTwo.getX(), 4);
        assertEquals(testTwo.getY(), 4);
    }

    @Test
    void testValidSecondMoveB() {
        Boolean firstMove = testTwo.move(4, 4);
        Boolean secondMove = testTwo.move(4, 3);
        assertTrue(secondMove);
        assertEquals(testTwo.getX(), 4);
        assertEquals(testTwo.getY(), 3);
    }

    @Test
    void testInvalidXMoveB() {
        Boolean move = testTwo.move(3, 6);
        assertFalse(move);
        assertEquals(testTwo.getX(), 4);
        assertEquals(testTwo.getY(), 6);
    }

    @Test
    void testInvalidYFirstMoveB() {
        Boolean move = testTwo.move(4, 3);
        assertFalse(move);
        assertEquals(testTwo.getX(), 4);
        assertEquals(testTwo.getY(), 6);
    }

    @Test
    void testInvalidYSecondMoveB() {
        Boolean move = testTwo.move(4, 5);
        Boolean moveTwo = testTwo.move(4, 3);
        assertTrue(move);
        assertFalse(moveTwo);
        assertEquals(testTwo.getX(), 4);
        assertEquals(testTwo.getY(), 5);
    }

    @Test
    void testInvalidMoveBackB() {
        Boolean move = testTwo.move(4, 7);
        assertFalse(move);
        assertEquals(testTwo.getX(), 4);
        assertEquals(testTwo.getY(), 6);
    }

    @Test
    void testOutOfBoundsMoveB() {
        Boolean move = testFour.move(7, -1);
        assertFalse(move);
        assertEquals(testFour.getX(), 7);
        assertEquals(testFour.getY(), 0);
    }
}
