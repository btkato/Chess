package test;

import model.Board;
import model.GamePiece;
import model.Pawn;
import model.Rook;
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
        testOne = new Rook(3, 2, false, "test");
        testTwo = new Rook(4, 6, true, "test2");
        testThree = new Rook(1, 7, false, "test");
        testFour = new Rook(7, 0, true, "test2");
    }

    @Test
    void testConstructor() {
        assertEquals(testOne.getX(), 3);
        assertEquals(testOne.getY(), 2);
        assertFalse(testOne.isColored());
        assertEquals(testOne.getPlayer(), "test");
    }

    @Test
    void testValidMovesHorizontalAndVertical() {
        Boolean move = testOne.move(3, 6);
        assertTrue(move);
        assertEquals(testOne.getX(), 3);
        assertEquals(testOne.getY(), 6);

        Boolean moveTwo = testOne.move(3, 2);
        assertTrue(moveTwo);
        assertEquals(testOne.getX(), 3);
        assertEquals(testOne.getY(), 2);

        Boolean moveThree = testOne.move(4, 2);
        assertTrue(moveThree);
        assertEquals(testOne.getX(), 4);
        assertEquals(testOne.getY(), 2);

        Boolean moveFour = testOne.move(1, 2);
        assertTrue(moveFour);
        assertEquals(testOne.getX(), 1);
        assertEquals(testOne.getY(), 2);
    }

    @Test
    void testInvalidMoves() {
        Boolean move = testOne.move(4, 3);
        assertFalse(move);
        assertEquals(3, testOne.getX());
        assertEquals(2, testOne.getY());

        Boolean moveTwo = testOne.move(2, 3);
        assertFalse(moveTwo);
        assertEquals(3, testOne.getX());
        assertEquals(2, testOne.getY());

        Boolean moveThree = testOne.move(4, 1);
        assertFalse(moveThree);
        assertEquals(3, testOne.getX());
        assertEquals(2, testOne.getY());

        Boolean moveFour = testOne.move(2, 1);
        assertFalse(moveFour);
        assertEquals(3, testOne.getX());
        assertEquals(2, testOne.getY());
    }
}