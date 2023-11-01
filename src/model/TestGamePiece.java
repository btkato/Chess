package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestGamePiece {

    private GamePiece piece;

    @BeforeEach
    void runBefore() {
        piece = new GamePiece(true, 0, 0);
    }

    @Test
    void testConstructorColor() {
        GamePiece testPiece = new GamePiece(true, 0, 0);
        assertEquals(0, testPiece.getX());
        assertEquals(0, testPiece.getY());
        assertTrue(testPiece.isColored());
    }

    @Test
    void testConstructorNoColor() {
        GamePiece testPiece = new GamePiece(false, 7, 7);
        assertEquals(7, testPiece.getX());
        assertEquals(7, testPiece.getY());
        assertFalse(testPiece.isColored());
    }

    @Test
    void testConstructorMidpointPositions() {
        GamePiece testPiece = new GamePiece(false, 3, 6);
        assertEquals(3, testPiece.getX());
        assertEquals(6, testPiece.getY());
        assertFalse(testPiece.isColored());
    }

    @Test
    void testMoveX() {
        piece.move(2, 0);
        assertEquals(2, piece.getX());
        assertEquals(0, piece.getY());
    }

    @Test
    void testMoveY() {
        piece.move(2, 0);
        assertEquals(2, piece.getX());
        assertEquals(0, piece.getY());
    }

    @Test
    void testMoveXY() {
        piece.move(4, 3);
        assertEquals(4, piece.getX());
        assertEquals(3, piece.getY());
    }
}
