package test;

import model.Board;
import model.Player;
import model.pieces.GamePiece;
import model.pieces.Knight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TestKnight {

    private Board testBoard;

    private GamePiece testOne;

    @BeforeEach
    void runBefore() {
        testOne = new Knight(3, 4, true, new Player("test", true));
    }

    @Test
    void testConstructor() {
        assertEquals(3, testOne.getX());
        assertEquals(4, testOne.getY());
        assertEquals("test", testOne.getPlayer().getName());
        assertTrue(testOne.isColored());
    }

    @Test
    void testValidMoves() {
        Boolean moveRD = testOne.validMove(5, 3);
        testOne.move(5, 3);
        assertTrue(moveRD);

        Boolean moveRU = testOne.validMove(7, 4);
        testOne.move(7, 4);
        assertTrue(moveRU);

        Boolean moveLD = testOne.validMove(5, 3);
        testOne.move(5, 3);
        assertTrue(moveLD);

        Boolean moveLU = testOne.validMove(3, 4);
        testOne.move(3, 4);
        assertTrue(moveLU);

        Boolean moveDL = testOne.validMove(2, 2);
        testOne.move(2, 2);
        assertTrue(moveDL);

        Boolean moveDR = testOne.validMove(3, 0);
        testOne.move(3, 0);
        assertTrue(moveDR);

        Boolean moveUL = testOne.validMove(2, 2);
        testOne.move(2, 2);
        assertTrue(moveUL);

        Boolean moveUR = testOne.validMove(3, 4);
        testOne.move(3, 4);
        assertTrue(moveUR);
        assertEquals(3, testOne.getX());
        assertEquals(4, testOne.getY());
    }

    @Test
    void testInvalidMoves() {
        Boolean move = testOne.validMove(6, 4);
        assertFalse(move);

        Boolean moveTwo = testOne.validMove(1, 4);
        assertFalse(moveTwo);

        Boolean moveThree = testOne.validMove(3, 6);
        assertFalse(moveThree);

        Boolean moveFour = testOne.validMove(3, 2);
        assertFalse(moveFour);

        Boolean moveFive = testOne.validMove(7, 7);
        assertFalse(moveFive);

        assertEquals(3, testOne.getX());
        assertEquals(4, testOne.getY());

    }
}
