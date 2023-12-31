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
        testPawn = new Pawn(2, 1, false, new Player("test", false));
        testPawnC = new Pawn(2, 6, true, new Player("test", true));
        testRook = new Rook(0, 0, true, new Player("test", true));
        testBishop = new Bishop(2, 0, true, new Player("test", true));
        testKnight = new Knight(6, 0, true, new Player("test", true));
        testKing = new King(4, 0, true, new Player("test", true));
        testQueen = new Queen(3, 0, true, new Player("test", true));
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
        testBoard.movePiece(testPawn.getX(), testPawn.getY(), 2, 3, testPawn);
        testBoard.movePiece(testPawn.getX(), testPawn.getY(), 2, 4, testPawn);
        testBoard.movePiece(testPawn.getX(), testPawn.getY(), 3, 4, testPawn);
        testBoard.movePiece(testPawn.getX(), testPawn.getY(), 7, 4, testPawn);
        testBoard.movePiece(testPawn.getX(), testPawn.getY(), 2, 6, testPawn);
        GamePiece placedPiece = testBoard.getGamePiece(2, 4);
        assertEquals(testPawn, placedPiece);
        assertEquals(4, placedPiece.getY());
        assertEquals(2, placedPiece.getX());
    }

    @Test
    void testMovementPawnNonColorObstruction() {
        GamePiece testObstruction = new Pawn(2, 2, true, new Player("block", true));
        testBoard.placePiece(testPawn);
        testBoard.placePiece(testObstruction);
        testBoard.movePiece(testPawn.getX(), testPawn.getY(), 2, 4, testPawn);
        testBoard.movePiece(testPawn.getX(), testPawn.getY(), 2, 3, testPawn);
        GamePiece placedPiece = testBoard.getGamePiece(2, 2);
        assertEquals(2, placedPiece.getY());
        assertEquals(2, placedPiece.getX());
    }

    @Test
    void testMovementPawnColor() {
        testBoard.placePiece(testPawnC);
        testBoard.movePiece(testPawnC.getX(), testPawnC.getY(), 2, 4, testPawnC);
        testBoard.movePiece(testPawnC.getX(), testPawnC.getY(), 2, 3, testPawnC);
        testBoard.movePiece(testPawnC.getX(), testPawnC.getY(), 3, 3, testPawnC);
        testBoard.movePiece(testPawnC.getX(), testPawnC.getY(), 7, 3, testPawnC);
        testBoard.movePiece(testPawnC.getX(), testPawnC.getY(), 2, 1, testPawnC);
        GamePiece placedPiece = testBoard.getGamePiece(2, 3);
        assertEquals(testPawnC, placedPiece);
        assertEquals(3, placedPiece.getY());
        assertEquals(2, placedPiece.getX());
    }

    @Test
    void testMovementPawnColorObstruction() {
        GamePiece block = new Pawn(2, 5, false, new Player("block", false));
        testBoard.placePiece(testPawnC);
        testBoard.placePiece(block);
        testBoard.movePiece(testPawnC.getX(), testPawnC.getY(), 2, 4, testPawnC);
        testBoard.movePiece(testPawnC.getX(), testPawnC.getY(), 2, 5, testPawnC);
        GamePiece placedPiece = testBoard.getGamePiece(2, 6);
        assertEquals(6, placedPiece.getY());
        assertEquals(2, placedPiece.getX());
    }

    @Test
    void testMoveRook() {
        testBoard.placePiece(testRook);
        testBoard.movePiece(testRook.getX(), testRook.getY(), 0, 4, testRook);
        testBoard.movePiece(testRook.getX(), testRook.getY(), 6, 4, testRook);
        testBoard.movePiece(testRook.getX(), testRook.getY(), 4, 4, testRook);
        testBoard.movePiece(testRook.getX(), testRook.getY(), 4, 2, testRook);
        testBoard.movePiece(testRook.getX(), testRook.getY(), 5, 1, testRook);
        testBoard.movePiece(testRook.getX(), testRook.getY(), 3, 8, testRook);
        GamePiece placedPiece = testBoard.getGamePiece(4, 2);
        assertEquals(testRook, placedPiece);
        assertEquals(4, placedPiece.getX());
        assertEquals(2, placedPiece.getY());
    }

    @Test
    void testRookObstruction() {
        testRook.move(4, 4);
        GamePiece up = new Rook(4, 3, false, new Player("block", true));
        GamePiece down = new Rook(4, 5, false, new Player("block", true));
        GamePiece right = new Rook(5, 4, false, new Player("block", true));
        GamePiece left = new Rook(3, 4, false, new Player("block", true));
        testBoard.placePiece(testRook);
        testBoard.placePiece(up);
        testBoard.placePiece(down);
        testBoard.placePiece(right);
        testBoard.placePiece(left);
        testBoard.movePiece(testRook.getX(), testRook.getY(), 0, 4, testRook);
        testBoard.movePiece(testRook.getX(), testRook.getY(), 6, 4, testRook);
        testBoard.movePiece(testRook.getX(), testRook.getY(), 4, 0, testRook);
        testBoard.movePiece(testRook.getX(), testRook.getY(), 4, 5, testRook);
        GamePiece placedPiece = testBoard.getGamePiece(4, 4);
        assertEquals(4, placedPiece.getX());
        assertEquals(4, placedPiece.getY());
    }

    @Test
    void testMoveKnight() {
        testBoard.placePiece(testKnight);
        testBoard.movePiece(testKnight.getX(), testKnight.getY(), 4, 1, testKnight);
        testBoard.movePiece(testKnight.getX(), testKnight.getY(), 2, 0, testKnight);
        testBoard.movePiece(testKnight.getX(), testKnight.getY(), 3, 2, testKnight);
        testBoard.movePiece(testKnight.getX(), testKnight.getY(), 2, 4, testKnight);
        testBoard.movePiece(testKnight.getX(), testKnight.getY(), 3, 4, testKnight);
        testBoard.movePiece(testKnight.getX(), testKnight.getY(), 2, 5, testKnight);
        testBoard.movePiece(testKnight.getX(), testKnight.getY(), 3, 5, testKnight);
        testBoard.movePiece(testKnight.getX(), testKnight.getY(), 1, 4, testKnight);
        testBoard.movePiece(testKnight.getX(), testKnight.getY(), 2, 3, testKnight);
        testBoard.movePiece(testKnight.getX(), testKnight.getY(), 1, 3, testKnight);
        GamePiece placedPiece = testBoard.getGamePiece(2, 4);
        assertEquals(2, placedPiece.getX());
        assertEquals(4, placedPiece.getY());
    }

    @Test
    void testKnightObstructionOneJumpY() {
        //testKnight = new Knight(6, 0, true, new Player("test", true));
        GamePiece block = new Pawn(6, 1, false, new Player("block", false));
        testBoard.placePiece(testKnight);
        testBoard.placePiece(block);
        testBoard.movePiece(testKnight.getX(), testKnight.getY(), 5, 2, testKnight);

        GamePiece placedPiece = testBoard.getGamePiece(5, 2);
        assertEquals(5, placedPiece.getX());
        assertEquals(2, placedPiece.getY());
    }

    @Test
    void testKnightObstructionOneJumpX() {
        testKnight.move(4, 4);
        GamePiece block = new Pawn(5, 4, false, new Player("block", false));
        testBoard.placePiece(testKnight);
        testBoard.placePiece(block);
        testBoard.movePiece(testKnight.getX(), testKnight.getY(), 6, 5, testKnight);

        GamePiece placedPiece = testBoard.getGamePiece(6, 5);
        assertEquals(6, placedPiece.getX());
        assertEquals(5, placedPiece.getY());
    }

    @Test
    void testKnightObstructionOneValidOneInvalid() {
        //testKnight = new Knight(6, 0, true, new Player("test", true));
        GamePiece block = new Pawn(7, 0, false, new Player("block", false));
        GamePiece blockTwo = new Pawn(7, 1, false, new Player("block", false));
        GamePiece blockThree = new Pawn(6, 1, false, new Player("block", false));
        testBoard.placePiece(testKnight);
        testBoard.placePiece(block);
        testBoard.placePiece(blockTwo);
        testBoard.placePiece(blockThree);
        testBoard.movePiece(testKnight.getX(), testKnight.getY(), 7, 2, testKnight);
        GamePiece placedPiece = testBoard.getGamePiece(7, 2);
        assertEquals(7, placedPiece.getX());
        assertEquals(2, placedPiece.getY());
    }

    @Test
    void testMoveBishop() {
        testBoard.placePiece(testBishop);
        testBoard.movePiece(testBishop.getX(), testBishop.getY(), 3, 1, testBishop);
        testBoard.movePiece(testBishop.getX(), testBishop.getY(), 2, 2, testBishop);
        testBoard.movePiece(testBishop.getX(), testBishop.getY(), 0, 0, testBishop);
        testBoard.movePiece(testBishop.getX(), testBishop.getY(), 4, 4, testBishop);
        testBoard.movePiece(testBishop.getX(), testBishop.getY(), 4, 3, testBishop);
        testBoard.movePiece(testBishop.getX(), testBishop.getY(), 6, 1, testBishop);
        GamePiece placedPiece = testBoard.getGamePiece(4, 4);
        assertEquals(testBishop, placedPiece);
        assertEquals(4, placedPiece.getX());
        assertEquals(4, placedPiece.getY());
    }

    @Test
    void testBishopObstruction() {
        testBishop.move(4, 4);
        GamePiece upRight = new Bishop(5, 3, false, new Player("block", false));
        GamePiece upLeft = new Bishop(3, 3, false, new Player("block", false));
        GamePiece downRight = new Bishop(5, 5, false, new Player("block", false));
        GamePiece downLeft = new Bishop(3, 5, false, new Player("block", false));
        testBoard.placePiece(testBishop);
        testBoard.placePiece(upRight);
        testBoard.placePiece(upLeft);
        testBoard.placePiece(downRight);
        testBoard.placePiece(downLeft);
        testBoard.movePiece(testBishop.getX(), testBishop.getY(), 6, 2, testBishop);
        testBoard.movePiece(testBishop.getX(), testBishop.getY(), 2, 2, testBishop);
        testBoard.movePiece(testBishop.getX(), testBishop.getY(), 6, 6, testBishop);
        testBoard.movePiece(testBishop.getX(), testBishop.getY(), 2, 6, testBishop);
        GamePiece placedPiece = testBoard.getGamePiece(4, 4);
        assertEquals(4, placedPiece.getX());
        assertEquals(4, placedPiece.getY());

    }

    @Test
    void testMoveQueen() {
        testBoard.placePiece(testQueen);
        testBoard.movePiece(testQueen.getX(), testQueen.getY(), 3, 3, testQueen);
        testBoard.movePiece(testQueen.getX(), testQueen.getY(), 0, 3, testQueen);
        testBoard.movePiece(testQueen.getX(), testQueen.getY(), 0, 0, testQueen);
        testBoard.movePiece(testQueen.getX(), testQueen.getY(), 3, 0, testQueen);
        testBoard.movePiece(testQueen.getX(), testQueen.getY(), 4, 1, testQueen);
        testBoard.movePiece(testQueen.getX(), testQueen.getY(), 3, 2, testQueen);
        testBoard.movePiece(testQueen.getX(), testQueen.getY(), 2, 1, testQueen);
        testBoard.movePiece(testQueen.getX(), testQueen.getY(), 3, 0, testQueen);
        testBoard.movePiece(testQueen.getX(), testQueen.getY(), 5, 1, testQueen);
        testBoard.movePiece(testQueen.getX(), testQueen.getY(), 5, 2, testQueen);
        testBoard.movePiece(testQueen.getX(), testQueen.getY(), 3, 1, testQueen);
        GamePiece placedPiece = testBoard.getGamePiece(5, 2);
        assertEquals(testQueen, placedPiece);
        assertEquals(5, placedPiece.getX());
        assertEquals(2, placedPiece.getY());
    }

    @Test
    void testQueenObstructionVerticals() {
        testQueen.move(4, 4);
        GamePiece up = new Pawn(4, 3, false, new Player("block", false));
        GamePiece down = new Pawn(4, 5, false, new Player("block", false));
        testBoard.placePiece(testQueen);
        testBoard.placePiece(up);
        testBoard.placePiece(down);
        testBoard.movePiece(testQueen.getX(), testQueen.getY(), 4, 6, testQueen);
        testBoard.movePiece(testQueen.getX(), testQueen.getY(), 4, 2, testQueen);
        GamePiece placedPiece = testBoard.getGamePiece(4, 4);
        assertEquals(4, placedPiece.getX());
        assertEquals(4, placedPiece.getY());
    }

    @Test
    void testQueenObstructionHorizontals() {
        testQueen.move(4, 4);
        GamePiece left = new Pawn(3, 4, false, new Player("block", false));
        GamePiece right = new Pawn(5, 4, false, new Player("block", false));
        testBoard.placePiece(testQueen);
        testBoard.placePiece(left);
        testBoard.placePiece(right);
        testBoard.movePiece(testQueen.getX(), testQueen.getY(), 6, 4, testQueen);
        testBoard.movePiece(testQueen.getX(), testQueen.getY(), 2,4, testQueen);
        GamePiece placedPiece = testBoard.getGamePiece(4, 4);
        assertEquals(4, placedPiece.getX());
        assertEquals(4, placedPiece.getY());
    }

    @Test
    void testQueenObstructionDiagonals() {
        testQueen.move(4, 4);
        GamePiece upRight = new Pawn(5, 3, false, new Player("block", false));
        GamePiece upLeft = new Pawn(3, 3, false, new Player("block", false));
        GamePiece downRight = new Pawn(5, 5, false, new Player("block", false));
        GamePiece downLeft = new Pawn(3, 5, false, new Player("block", false));
        testBoard.placePiece(testQueen);
        testBoard.placePiece(upRight);
        testBoard.placePiece(upLeft);
        testBoard.placePiece(downRight);
        testBoard.placePiece(downLeft);
        testBoard.movePiece(testQueen.getX(), testQueen.getY(), 2,2, testQueen);
        testBoard.movePiece(testQueen.getX(), testQueen.getY(), 2, 6, testQueen);
        testBoard.movePiece(testQueen.getX(), testQueen.getY(), 6, 6, testQueen);
        testBoard.movePiece(testQueen.getX(), testQueen.getY(), 6, 2, testQueen);
        GamePiece placedPiece = testBoard.getGamePiece(4, 4);
        assertEquals(4, placedPiece.getX());
        assertEquals(4, placedPiece.getY());
    }

    @Test
    void testMoveKing() {
        testBoard.placePiece(testKing);
        testBoard.movePiece(testKing.getX(), testKing.getY(), 4, 1, testKing);
        testBoard.movePiece(testKing.getX(), testKing.getY(), 5, 1, testKing);
        testBoard.movePiece(testKing.getX(), testKing.getY(), 4, 0, testKing);
        testBoard.movePiece(testKing.getX(), testKing.getY(), 5, 1, testKing);
        testBoard.movePiece(testKing.getX(), testKing.getY(), 4, 2, testKing);
        testBoard.movePiece(testKing.getX(), testKing.getY(), 5, 1, testKing);
        testBoard.movePiece(testKing.getX(), testKing.getY(), 4, 0, testKing);
        testBoard.movePiece(testKing.getX(), testKing.getY(), 6, 0, testKing);
        testBoard.movePiece(testKing.getX(), testKing.getY(), 2, 0, testKing);
        testBoard.movePiece(testKing.getX(), testKing.getY(), 2, 3, testKing);
        GamePiece placedPiece = testBoard.getGamePiece(4, 0);
        assertEquals(testKing, placedPiece);
        assertEquals(4, placedPiece.getX());
        assertEquals(0, placedPiece.getY());
    }

    @Test
    void testKingObstructionVerticals() {
        testKing.move(4, 4);
        GamePiece up = new Pawn(4, 3, true, new Player("test", true));
        GamePiece down = new Pawn(4, 5, true, new Player("test", true));
        testBoard.placePiece(testKing);
        testBoard.placePiece(up);
        testBoard.placePiece(down);
        testBoard.movePiece(testKing.getX(), testKing.getY(), 4, 3, testKing);
        testBoard.movePiece(testKing.getX(), testKing.getY(), 4, 5, testKing);
        GamePiece placedPiece = testBoard.getGamePiece(4, 4);
        assertEquals(4, placedPiece.getX());
        assertEquals(4, placedPiece.getY());
    }

    @Test
    void testKingObstructionHorizontals() {
        testKing.move(4, 4);
        GamePiece left = new Pawn(3, 4, true, new Player("test", true));
        GamePiece right = new Pawn(5, 4, true, new Player("test", true));
        testBoard.placePiece(testKing);
        testBoard.placePiece(left);
        testBoard.placePiece(right);
        testBoard.movePiece(testKing.getX(), testKing.getY(), 3, 4, testKing);
        testBoard.movePiece(testKing.getX(), testKing.getY(), 5,4, testKing);
        GamePiece placedPiece = testBoard.getGamePiece(4, 4);
        assertEquals(4, placedPiece.getX());
        assertEquals(4, placedPiece.getY());
    }

    @Test
    void testKingObstructionDiagonals() {
        testKing.move(4, 4);
        GamePiece upRight = new Pawn(5, 3, true, new Player("test", true));
        GamePiece upLeft = new Pawn(3, 3, true, new Player("test", true));
        GamePiece downRight = new Pawn(5, 5, true, new Player("test", true));
        GamePiece downLeft = new Pawn(3, 5, true, new Player("test", true));
        testBoard.placePiece(testKing);
        testBoard.placePiece(upRight);
        testBoard.placePiece(upLeft);
        testBoard.placePiece(downRight);
        testBoard.placePiece(downLeft);
        testBoard.movePiece(testKing.getX(), testKing.getY(), 2,2, testKing);
        testBoard.movePiece(testKing.getX(), testKing.getY(), 2, 6, testKing);
        testBoard.movePiece(testKing.getX(), testKing.getY(), 6, 6, testKing);
        testBoard.movePiece(testKing.getX(), testKing.getY(), 6, 2, testKing);
        GamePiece placedPiece = testBoard.getGamePiece(4, 4);
        assertEquals(4, placedPiece.getX());
        assertEquals(4, placedPiece.getY());
    }
}