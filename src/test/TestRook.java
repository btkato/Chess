package test;

import model.Board;
import model.Player;
import model.pieces.GamePiece;
import model.pieces.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestRook {

    private Board testBoard;

    private GamePiece testOne;
    private GamePiece testTwo;
    private GamePiece testThree;
    private GamePiece testFour;

    @BeforeEach
    void runBefore() {
        testBoard = new Board();
        testOne = new Rook(3, 2, false, new Player("test", false));
        testTwo = new Rook(4, 6, true, new Player("test", true));
        testThree = new Rook(1, 7, false, new Player("test", false));
        testFour = new Rook(7, 0, true, new Player("test2", true));
    }

    @Test
    void testConstructor() {
        assertEquals(testOne.getX(), 3);
        assertEquals(testOne.getY(), 2);
        assertFalse(testOne.isColored());
        assertEquals(testOne.getPlayer().getName(), "test");
    }

    @Test
    void testValidMovesHorizontalAndVertical() {
        Boolean move = testOne.validMove(3, 6);
        testOne.move(3, 6);
        assertTrue(move);
        assertEquals(testOne.getX(), 3);
        assertEquals(testOne.getY(), 6);

        Boolean moveTwo = testOne.validMove(3, 2);
        testOne.move(3, 2);
        assertTrue(moveTwo);
        assertEquals(testOne.getX(), 3);
        assertEquals(testOne.getY(), 2);

        Boolean moveThree = testOne.validMove(4, 2);
        testOne.move(4, 2);
        assertTrue(moveThree);
        assertEquals(testOne.getX(), 4);
        assertEquals(testOne.getY(), 2);

        Boolean moveFour = testOne.validMove(1, 2);
        testOne.move(1, 2);
        assertTrue(moveFour);
        assertEquals(testOne.getX(), 1);
        assertEquals(testOne.getY(), 2);
    }

    @Test
    void testInvalidMoves() {
        Boolean move = testOne.validMove(4, 3);
        assertFalse(move);

        Boolean moveTwo = testOne.validMove(2, 3);
        assertFalse(moveTwo);

        Boolean moveThree = testOne.validMove(4, 1);
        assertFalse(moveThree);

        Boolean moveFour = testOne.validMove(2, 1);
        assertFalse(moveFour);
        assertEquals(3, testOne.getX());
        assertEquals(2, testOne.getY());
    }
}
