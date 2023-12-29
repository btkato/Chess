package test;

import model.Board;
import model.GamePiece;
import model.Queen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestQueen {

    private Board testBoard;

    private GamePiece testOne;

    @BeforeEach
    void runBefore() {
        testOne = new Queen(2, 3, true, "test");
    }

    @Test
    void testConstructor() {
        assertEquals(2, testOne.getX());
        assertEquals(3, testOne.getY());
        assertTrue(testOne.isColored());
        assertEquals("test", testOne.getPlayer());
    }

    @Test
    void testValidMoves() {
        Boolean move = testOne.move(3, 3);
        assertTrue(move);

        move = testOne.move(3, 4);
        assertTrue(move);

        move = testOne.move(2, 4);
        assertTrue(move);

        move = testOne.move(2, 2);
        assertTrue(move);

        move = testOne.move(4, 4);
        assertTrue(move);

        move = testOne.move(2, 6);
        assertTrue(move);

        move = testOne.move(5, 3);
        assertTrue(move);

        move = testOne.move(3, 1);
        assertEquals(3, testOne.getX());
        assertEquals(1, testOne.getY());
        assertTrue(move);
    }

    @Test
    void testInvalidMoves() {
        Boolean move = testOne.move(4, 0);
        assertFalse(move);

        move = testOne.move(4, 2);
        assertFalse(move);

        move = testOne.move(4, 4);
        assertFalse(move);

        assertEquals(2, testOne.getX());
        assertEquals(3, testOne.getY());
    }
}
