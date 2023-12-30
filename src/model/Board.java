package model;

import model.pieces.GamePiece;

import java.util.ArrayList;

/**
 * model.Chess game board
 */
public class Board {
    private ArrayList<ArrayList<GamePiece>> board;

    public Board() {
        this.board = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            ArrayList<GamePiece> row = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                row.add(j, null);
            }
            board.add(i, row);
        }
    }

    /**
     * Places new chess piece onto game board
     * @param g game piece that will be placed on the board
     */
    public void placePiece(GamePiece g) {
        ArrayList<GamePiece> row = board.get(g.getY());
        row.set(g.getX(), g);
        board.set(g.getY(), row);
    }

    public void removePiece(int oldX, int oldY) {
        ArrayList<GamePiece> row = board.get(oldY);
        row.set(oldX, null);
        board.set(oldY, row);
    }

    /**
     * Moves chess piece on board in valid movement pattern
     * @param oldX old x coordinate of chess piece
     * @param oldY old y coordinate of chess piece
     * @param newX new x coordinate of chess piece
     * @param newY new y coordinate of chess piece
     * @param g game piece that will be moved
     */
    public Boolean movePiece(int oldX, int oldY, int newX, int newY, GamePiece g) {
        GamePiece piece = getGamePiece(oldX, oldY);
        if (piece.move(newX, newY)) {
            placePiece(g);
            removePiece(oldX, oldY);
            return true;
        }
        return false;
    }

    public int getRows() {
        return board.size();
    }

    public int getCols(int i) {
        return board.get(i).size();
    }

    public GamePiece getGamePiece(int x, int y) {
        return board.get(y).get(x);
    }

    public ArrayList<ArrayList<GamePiece>> getBoard() {
        return board;
    }
}
