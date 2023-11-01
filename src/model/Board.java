package model;

import java.util.ArrayList;

/**
 * model.Chess game board
 */
public class Board {
    private ArrayList<ArrayList<GamePiece>> board;

    public Board() {
        this.board = new ArrayList<>();
    }

    /**
     * Places new chess piece onto game board
     * @param x x position of chess piece
     * @param y y position of chess piece
     * @param g game piece that will be placed on the board
     */
    public void placePiece(int x, int y, GamePiece g) {
        ArrayList<GamePiece> row = board.get(y);
        row.set(x, g);
        board.set(y, row);
    }

    /**
     * Moves chess piece on board in valid movement pattern
     * @param newX new x coordinate of chess piece
     * @param newY new y coordinate of chess piece
     * @param g game piece that will be moved
     */
    public void movePiece(int newX, int newY, GamePiece g) {

    }
}
