package test;

import model.Board;
import model.Player;
import model.pieces.GamePiece;
import model.pieces.Pawn;
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
        testOne = new Pawn(3, 3, false, new Player("test", false));
        testTwo = new Pawn(4, 6, true, new Player("test2", true));
        testThree = new Pawn(1, 7, false, new Player("test", false));
        testFour = new Pawn(7, 0, true, new Player("test2", true));
    }

    @Test
    void testConstructor() {
        assertEquals("test", testOne.getPlayer().getName());
        assertEquals(3, testOne.getX());
        assertEquals(3, testOne.getY());
        assertEquals(false, testOne.isColored());
    }

    @Test
    void testValidFirstMoveW() {
        Boolean move = testOne.validMove(3, 5);
        assertTrue(move);
        testOne.move(3, 5);
        assertEquals(testOne.getX(), 3);
        assertEquals(testOne.getY(), 5);
    }

    @Test
    void testValidSecondMoveW() {
        Boolean firstMove = testOne.validMove(3, 5);
        testOne.move(3, 5);
        Boolean secondMove = testOne.validMove(3, 6);
        testOne.move(3, 6);
        assertTrue(firstMove);
        assertTrue(secondMove);
        assertEquals(testOne.getX(), 3);
        assertEquals(testOne.getY(), 6);
    }

    @Test
    void testInvalidXMoveW() {
        Boolean move = testOne.validMove(2, 3);
        assertFalse(move);
        assertEquals(testOne.getX(), 3);
        assertEquals(testOne.getY(), 3);
    }

    @Test
    void testInvalidYFirstMoveW() {
        Boolean move = testOne.validMove(3, 6);
        assertFalse(move);
        assertEquals(testOne.getX(), 3);
        assertEquals(testOne.getY(), 3);
    }

    @Test
    void testInvalidYSecondMoveW() {
        Boolean move = testOne.validMove(3, 4);
        testOne.move(3, 4);
        Boolean moveTwo = testOne.validMove(3, 6);
        assertTrue(move);
        assertFalse(moveTwo);
        assertEquals(testOne.getX(), 3);
        assertEquals(testOne.getY(), 4);
    }

    @Test
    void testInvalidMoveBackW() {
        Boolean move = testOne.validMove(3, 2);
        assertFalse(move);
        assertEquals(testOne.getX(), 3);
        assertEquals(testOne.getY(), 3);
    }

    @Test
    void testValidFirstMoveB() {
        Boolean move = testTwo.validMove(4, 4);
        testTwo.move(4, 4);
        assertTrue(move);
        assertEquals(testTwo.getX(), 4);
        assertEquals(testTwo.getY(), 4);
    }

    @Test
    void testValidSecondMoveB() {
        Boolean firstMove = testTwo.validMove(4, 4);
        testTwo.move(4, 4);
        Boolean secondMove = testTwo.validMove(4, 3);
        testTwo.move(4, 3);
        assertTrue(firstMove);
        assertTrue(secondMove);
        assertEquals(testTwo.getX(), 4);
        assertEquals(testTwo.getY(), 3);
    }

    @Test
    void testInvalidXMoveB() {
        Boolean move = testTwo.validMove(3, 6);
        assertFalse(move);
        assertEquals(testTwo.getX(), 4);
        assertEquals(testTwo.getY(), 6);
    }

    @Test
    void testInvalidYFirstMoveB() {
        Boolean move = testTwo.validMove(4, 3);
        assertFalse(move);
        assertEquals(testTwo.getX(), 4);
        assertEquals(testTwo.getY(), 6);
    }

    @Test
    void testInvalidYSecondMoveB() {
        Boolean move = testTwo.validMove(4, 5);
        testTwo.move(4, 5);
        Boolean moveTwo = testTwo.validMove(4, 3);
        assertTrue(move);
        assertFalse(moveTwo);
        assertEquals(testTwo.getX(), 4);
        assertEquals(testTwo.getY(), 5);
    }

    @Test
    void testInvalidMoveBackB() {
        Boolean move = testTwo.validMove(4, 7);
        assertFalse(move);
        assertEquals(testTwo.getX(), 4);
        assertEquals(testTwo.getY(), 6);
    }

}
