package test;

import model.Board;
import model.Player;
import model.pieces.GamePiece;
import model.pieces.King;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TestKing {

    private Board testBoard;

    private GamePiece testOne;

    @BeforeEach
    void runBefore() {
        testOne = new King(6, 3, true, new Player("test", true));
    }

    @Test
    void testConstructor() {
        assertEquals("test", testOne.getPlayer().getName());
        assertEquals(6, testOne.getX());
        assertEquals(3, testOne.getY());
        assertTrue(testOne.isColored());
    }

    @Test
    void testValidMoveVertical() {
        Boolean move = testOne.validMove(6, 2);
        assertTrue(move);
        testOne.move(6, 2);
        assertEquals(6, testOne.getX());
        assertEquals(2, testOne.getY());
        Boolean moveTwo = testOne.validMove(6, 3);
        assertTrue(moveTwo);
        testOne.move(6, 3);
        assertEquals(6, testOne.getX());
        assertEquals(3, testOne.getY());
    }

    @Test
    void testValidMoveHorizontal() {
        Boolean move = testOne.validMove(5, 3);
        assertTrue(move);
        testOne.move(5, 3);
        assertEquals(5, testOne.getX());
        assertEquals(3, testOne.getY());
        Boolean moveTwo = testOne.validMove(6, 3);
        assertTrue(moveTwo);
        testOne.move(6, 3);
        assertEquals(6, testOne.getX());
        assertEquals(3, testOne.getY());
    }

    @Test
    void testValidMoveDiagonal() {
        Boolean move = testOne.validMove(7, 4);
        assertTrue(move);
        testOne.move(7, 4);
        assertEquals(7, testOne.getX());
        assertEquals(4, testOne.getY());
        Boolean moveTwo = testOne.validMove(6, 5);
        assertTrue(moveTwo);
        testOne.move(6, 5);
        assertEquals(6, testOne.getX());
        assertEquals(5, testOne.getY());
        Boolean moveThree = testOne.validMove(5, 4);
        assertTrue(moveThree);
        testOne.move(5, 4);
        assertEquals(5, testOne.getX());
        assertEquals(4, testOne.getY());
        Boolean moveFour = testOne.validMove(6, 3);
        assertTrue(moveFour);
        testOne.move(6, 3);
        assertEquals(6, testOne.getX());
        assertEquals(3, testOne.getY());
    }

    @Test
    void testInvalidMoves() {
        Boolean move = testOne.validMove(3,6);
        assertFalse(move);
        Boolean moveTwo = testOne.validMove(6,5);
        assertFalse(moveTwo);
        assertEquals(6, testOne.getX());
        assertEquals(3, testOne.getY());
    }
}
