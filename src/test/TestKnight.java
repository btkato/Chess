package test;

import model.Board;
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
        testOne = new Knight(3, 4, true, "test");
    }

    @Test
    void testConstructor() {
        assertEquals(3, testOne.getX());
        assertEquals(4, testOne.getY());
        assertEquals("test", testOne.getPlayer());
        assertTrue(testOne.isColored());
    }

    @Test
    void testValidMoves() {
        Boolean moveRD = testOne.move(5, 3);
        assertTrue(moveRD);

        Boolean moveRU = testOne.move(7, 4);
        assertTrue(moveRU);

        Boolean moveLD = testOne.move(5, 3);
        assertTrue(moveLD);

        Boolean moveLU = testOne.move(3, 4);
        assertTrue(moveLU);

        Boolean moveDL = testOne.move(2, 2);
        assertTrue(moveDL);

        Boolean moveDR = testOne.move(3, 0);
        assertTrue(moveDR);

        Boolean moveUL = testOne.move(2, 2);
        assertTrue(moveUL);

        Boolean moveUR = testOne.move(3, 4);
        assertTrue(moveUR);
        assertEquals(3, testOne.getX());
        assertEquals(4, testOne.getY());
    }

    @Test
    void testInvalidMoves() {
        Boolean move = testOne.move(6, 4);
        assertFalse(move);

        Boolean moveTwo = testOne.move(1, 4);
        assertFalse(moveTwo);

        Boolean moveThree = testOne.move(3, 6);
        assertFalse(moveThree);

        Boolean moveFour = testOne.move(3, 2);
        assertFalse(moveFour);

        Boolean moveFive = testOne.move(7, 7);
        assertFalse(moveFive);

        assertEquals(3, testOne.getX());
        assertEquals(4, testOne.getY());

    }
}
