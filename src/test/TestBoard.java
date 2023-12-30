package test;

import model.*;
import model.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestBoard{

    private Board testBoard;
    private GamePiece testPawn;
    private GamePiece testPawnC;
    private GamePiece testRook;
    private GamePiece testKnight;
    private GamePiece testBishop;
    private GamePiece testQueen;
    private GamePiece testKing;


    @BeforeEach
    void runBefore() {
        testBoard = new Board();
        testPawn = new Pawn(2, 1, false, "test");
        testPawnC = new Pawn(2, 6, true, "test");
        testRook = new Rook(0, 0, true, "test");
        testBishop = new Bishop(2, 0, true, "test");
        testKnight = new Knight(6, 0, true, "test");
        testKing = new King(4, 0, true, "test");
        testQueen = new Queen(3, 0, true, "test");
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
        testBoard.placePiece(testPawn);
        GamePiece placedPiece = testBoard.getGamePiece(testPawn.getX(), testPawn.getY());
        assertEquals(testPawn, placedPiece);
        assertEquals(1, placedPiece.getY());
        assertEquals(2, placedPiece.getX());
    }

    @Test
    void testMovementPawnNonColor() {
        testBoard.placePiece(testPawn);
        assertTrue(testBoard.movePiece(testPawn.getX(), testPawn.getY(), 2, 3, testPawn));
        assertTrue(testBoard.movePiece(testPawn.getX(), testPawn.getY(), 2, 4, testPawn));
        assertFalse(testBoard.movePiece(testPawn.getX(), testPawn.getY(), 3, 4, testPawn));
        assertFalse(testBoard.movePiece(testPawn.getX(), testPawn.getY(), 7, 4, testPawn));
        assertFalse(testBoard.movePiece(testPawn.getX(), testPawn.getY(), 2, 6, testPawn));
        GamePiece placedPiece = testBoard.getGamePiece(2, 4);
        assertEquals(testPawn, placedPiece);
        assertEquals(4, placedPiece.getY());
        assertEquals(2, placedPiece.getX());
    }

    @Test
    void testMovementPawnColor() {
        testBoard.placePiece(testPawnC);
        assertTrue(testBoard.movePiece(testPawnC.getX(), testPawnC.getY(), 2, 4, testPawnC));
        assertTrue(testBoard.movePiece(testPawnC.getX(), testPawnC.getY(), 2, 3, testPawnC));
        assertFalse(testBoard.movePiece(testPawnC.getX(), testPawnC.getY(), 3, 3, testPawnC));
        assertFalse(testBoard.movePiece(testPawnC.getX(), testPawnC.getY(), 7, 3, testPawnC));
        assertFalse(testBoard.movePiece(testPawnC.getX(), testPawnC.getY(), 2, 1, testPawnC));
        GamePiece placedPiece = testBoard.getGamePiece(2, 3);
        assertEquals(testPawnC, placedPiece);
        assertEquals(3, placedPiece.getY());
        assertEquals(2, placedPiece.getX());
    }

    @Test
    void testMoveRook() {
        testBoard.placePiece(testRook);
        assertTrue(testBoard.movePiece(testRook.getX(), testRook.getY(), 0, 4, testRook));
        assertTrue(testBoard.movePiece(testRook.getX(), testRook.getY(), 6, 4, testRook));
        assertTrue(testBoard.movePiece(testRook.getX(), testRook.getY(), 4, 4, testRook));
        assertTrue(testBoard.movePiece(testRook.getX(), testRook.getY(), 4, 2, testRook));
        assertFalse(testBoard.movePiece(testRook.getX(), testRook.getY(), 5, 1, testRook));
        assertFalse(testBoard.movePiece(testRook.getX(), testRook.getY(), 3, 8, testRook));
        GamePiece placedPiece = testBoard.getGamePiece(4, 2);
        assertEquals(testRook, placedPiece);
        assertEquals(4, placedPiece.getX());
        assertEquals(2, placedPiece.getY());
    }

    @Test
    void testMoveKnight() {
        testBoard.placePiece(testKnight);
        assertTrue(testBoard.movePiece(testKnight.getX(), testKnight.getY(), 4, 1, testKnight));
        assertTrue(testBoard.movePiece(testKnight.getX(), testKnight.getY(), 2, 0, testKnight));
        assertTrue(testBoard.movePiece(testKnight.getX(), testKnight.getY(), 3, 2, testKnight));
        assertTrue(testBoard.movePiece(testKnight.getX(), testKnight.getY(), 2, 4, testKnight));
        assertFalse(testBoard.movePiece(testKnight.getX(), testKnight.getY(), 3, 4, testKnight));
        assertFalse(testBoard.movePiece(testKnight.getX(), testKnight.getY(), 2, 5, testKnight));
        assertFalse(testBoard.movePiece(testKnight.getX(), testKnight.getY(), 3, 5, testKnight));
        assertFalse(testBoard.movePiece(testKnight.getX(), testKnight.getY(), 1, 4, testKnight));
        assertFalse(testBoard.movePiece(testKnight.getX(), testKnight.getY(), 2, 3, testKnight));
        assertFalse(testBoard.movePiece(testKnight.getX(), testKnight.getY(), 1, 3, testKnight));
        GamePiece placedPiece = testBoard.getGamePiece(2, 4);
        assertEquals(testKnight, placedPiece);
        assertEquals(2, placedPiece.getX());
        assertEquals(4, placedPiece.getY());
    }

    @Test
    void testMoveBishop() {
        testBoard.placePiece(testBishop);
        assertTrue(testBoard.movePiece(testBishop.getX(), testBishop.getY(), 3, 1, testBishop));
        assertTrue(testBoard.movePiece(testBishop.getX(), testBishop.getY(), 2, 2, testBishop));
        assertTrue(testBoard.movePiece(testBishop.getX(), testBishop.getY(), 0, 0, testBishop));
        assertTrue(testBoard.movePiece(testBishop.getX(), testBishop.getY(), 4, 4, testBishop));
        assertFalse(testBoard.movePiece(testBishop.getX(), testBishop.getY(), 4, 3, testBishop));
        assertFalse(testBoard.movePiece(testBishop.getX(), testBishop.getY(), 6, 1, testBishop));
        GamePiece placedPiece = testBoard.getGamePiece(4, 4);
        assertEquals(testBishop, placedPiece);
        assertEquals(4, placedPiece.getX());
        assertEquals(4, placedPiece.getY());
    }

    @Test
    void testMoveQueen() {
        testBoard.placePiece(testQueen);
        assertTrue(testBoard.movePiece(testQueen.getX(), testQueen.getY(), 3, 3, testQueen));
        assertTrue(testBoard.movePiece(testQueen.getX(), testQueen.getY(), 0, 3, testQueen));
        assertTrue(testBoard.movePiece(testQueen.getX(), testQueen.getY(), 0, 0, testQueen));
        assertTrue(testBoard.movePiece(testQueen.getX(), testQueen.getY(), 3, 0, testQueen));
        assertTrue(testBoard.movePiece(testQueen.getX(), testQueen.getY(), 4, 1, testQueen));
        assertTrue(testBoard.movePiece(testQueen.getX(), testQueen.getY(), 3, 2, testQueen));
        assertTrue(testBoard.movePiece(testQueen.getX(), testQueen.getY(), 2, 1, testQueen));
        assertTrue(testBoard.movePiece(testQueen.getX(), testQueen.getY(), 3, 0, testQueen));
        assertFalse(testBoard.movePiece(testQueen.getX(), testQueen.getY(), 5, 1, testQueen));
        assertTrue(testBoard.movePiece(testQueen.getX(), testQueen.getY(), 5, 2, testQueen));
        assertFalse(testBoard.movePiece(testQueen.getX(), testQueen.getY(), 3, 1, testQueen));
        GamePiece placedPiece = testBoard.getGamePiece(5, 2);
        assertEquals(testQueen, placedPiece);
        assertEquals(5, placedPiece.getX());
        assertEquals(2, placedPiece.getY());

    }

    @Test
    void testMoveKing() {
        testBoard.placePiece(testKing);
        assertTrue(testBoard.movePiece(testKing.getX(), testKing.getY(), 4, 1, testKing));
        assertTrue(testBoard.movePiece(testKing.getX(), testKing.getY(), 5, 1, testKing));
        assertTrue(testBoard.movePiece(testKing.getX(), testKing.getY(), 4, 0, testKing));
        assertTrue(testBoard.movePiece(testKing.getX(), testKing.getY(), 5, 1, testKing));
        assertTrue(testBoard.movePiece(testKing.getX(), testKing.getY(), 4, 2, testKing));
        assertTrue(testBoard.movePiece(testKing.getX(), testKing.getY(), 5, 1, testKing));
        assertTrue(testBoard.movePiece(testKing.getX(), testKing.getY(), 4, 0, testKing));
        assertFalse(testBoard.movePiece(testKing.getX(), testKing.getY(), 6, 0, testKing));
        assertFalse(testBoard.movePiece(testKing.getX(), testKing.getY(), 2, 0, testKing));
        assertFalse(testBoard.movePiece(testKing.getX(), testKing.getY(), 2, 3, testKing));
        GamePiece placedPiece = testBoard.getGamePiece(4, 0);
        assertEquals(testKing, placedPiece);
        assertEquals(4, placedPiece.getX());
        assertEquals(0, placedPiece.getY());

    }
}