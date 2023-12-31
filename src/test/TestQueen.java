package test;

import model.Board;
import model.Player;
import model.pieces.GamePiece;
import model.pieces.Queen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestQueen {

    private Board testBoard;

    private GamePiece testOne;

    @BeforeEach
    void runBefore() {
        testOne = new Queen(2, 3, true, new Player("test", true));
    }

    @Test
    void testConstructor() {
        assertEquals(2, testOne.getX());
        assertEquals(3, testOne.getY());
        assertTrue(testOne.isColored());
        assertEquals("test", testOne.getPlayer().getName());
    }

    @Test
    void testValidMoves() {
        Boolean move = testOne.validMove(3, 3);
        testOne.move(3, 3);
        assertTrue(move);

        move = testOne.validMove(3, 4);
        testOne.move(3, 4);
        assertTrue(move);

        move = testOne.validMove(2, 4);
        testOne.move(2, 4);
        assertTrue(move);

        move = testOne.validMove(2, 2);
        testOne.move(2, 2);
        assertTrue(move);

        move = testOne.validMove(4, 4);
        testOne.move(4, 4);
        assertTrue(move);

        move = testOne.validMove(2, 6);
        testOne.move(2, 6);
        assertTrue(move);

        move = testOne.validMove(5, 3);
        testOne.move(5, 3);
        assertTrue(move);

        move = testOne.validMove(3, 1);
        testOne.move(3, 1);
        assertEquals(3, testOne.getX());
        assertEquals(1, testOne.getY());
        assertTrue(move);
    }

    @Test
    void testInvalidMoves() {
        Boolean move = testOne.validMove(4, 0);
        assertFalse(move);

        move = testOne.validMove(4, 2);
        assertFalse(move);

        move = testOne.validMove(4, 4);
        assertFalse(move);

        assertEquals(2, testOne.getX());
        assertEquals(3, testOne.getY());
    }
}
