package test;

import model.Board;
import model.Player;
import model.pieces.GamePiece;
import model.pieces.Bishop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestBishop {

    private Board testBoard;

    private GamePiece testOne;

    @BeforeEach
    void runBefore() {
        testOne = new Bishop(2, 3, false, new Player("test", false));
    }

    @Test
    void testConstructor() {
        assertFalse(testOne.isColored());
        assertEquals(2, testOne.getX());
        assertEquals(3, testOne.getY());
        assertEquals("test", testOne.getPlayer().getName());
    }

    @Test
    void testValidMoves() {
        Boolean moveUR = testOne.validMove(4, 5);
        testOne.move(4, 5);
        assertTrue(moveUR);

        Boolean moveUL = testOne.validMove(2, 7);
        testOne.move(2, 7);
        assertTrue(moveUL);

        Boolean moveDR = testOne.validMove(5, 4);
        testOne.move(5, 4);
        assertTrue(moveDR);

        Boolean moveDL = testOne.validMove(3, 2);
        testOne.move(3, 2);
        assertTrue(moveDL);

        assertEquals(3, testOne.getX());
        assertEquals(2, testOne.getY());
    }

    @Test
    void testInvalidMoves() {
        Boolean move = testOne.validMove(4, 6);
        assertFalse(move);

        move = testOne.validMove(0, 0);
        assertFalse(move);

        move = testOne.validMove(3, 6);
        assertFalse(move);

        move = testOne.validMove(3, 0);
        assertFalse(move);

        move = testOne.validMove(5, 3);
        assertFalse(move);

        move = testOne.validMove(0, 3);
        assertFalse(move);

        assertEquals(2, testOne.getX());
        assertEquals(3, testOne.getY());
    }
}
