package test;

import model.Board;
import model.GamePiece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestBoard{

    private Board testBoard;
    private GamePiece testPiece;

    @BeforeEach
    void runBefore() {
        testBoard = new Board();
    }

    @Test
    void testConstructor() {
        assertEquals(8, testBoard.getRows());
        assertEquals(8, testBoard.getCols(0));
        assertEquals(8, testBoard.getCols(1));
        assertEquals(8, testBoard.getCols(2));
        assertEquals(8, testBoard.getCols(3));
        assertEquals(8, testBoard.getCols(4));
        assertEquals(8, testBoard.getCols(5));
        assertEquals(8, testBoard.getCols(6));
        assertEquals(8, testBoard.getCols(7));
    }

    @Test
    void testPlacement() {
        testBoard.placePiece(testPiece);
        GamePiece placedPiece = testBoard.getGamePiece(testPiece.getX(), testPiece.getY());
        assertEquals(testPiece, placedPiece);
        assertEquals(0, placedPiece.getY());
        assertEquals(0, placedPiece.getX());
    }
}