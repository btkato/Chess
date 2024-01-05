package model;

import model.pieces.GamePiece;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Chess game class
 */
public class Chess {

    private Player playerOne;

    private Player playerTwo;

    private Boolean gameOver;

    private Board board;

    public Chess() {
        playerOne = new Player("PlayerOne", false);
        playerTwo = new Player("PlayerTwo", true);
        board = new Board();
        initializeBoard(playerOne);
        initializeBoard(playerTwo);
        gameOver = false;
    }

    /**
     * Places all initial GamePiece for a specified player onto Board
     * @param player Player participating in chess game
     */
    public void initializeBoard(Player player) {
        player.initializePieces(player.isColored());
        Iterator<GamePiece> itOne = player.getPieces().iterator();
        while (itOne.hasNext()) {
            GamePiece p = itOne.next();
            board.placePiece(p);
        }
    }

    /**
     * Moves GamePiece in a valid direction, otherwise does nothing
     * @param piece GamePiece being moved
     * @param newX New X coordinate of movement
     * @param newY New Y coordinate of movement
     * @return True if valid move is completed, otherwise produce false
     */
    public Boolean move(GamePiece piece, int newX, int newY) {
        board.movePiece(piece.getX(), piece.getY(), newX, newY, piece);
        if (piece.getY() == newY && piece.getX() == newX) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Moves GamePiece to produce a valid attack to opposing player's GamePiece, otherwise does nothing
     * @param piece GamePiece attacking
     * @param newX New X coordinate of attack
     * @param newY New Y coordinate of attack
     * @return True if valid attack is completed, otherwise produce false
     */
    public Boolean attack(GamePiece piece, int newX, int newY) {
        board.attackPiece(piece.getX(), piece.getY(), newX, newY, piece);
        if (piece.getY() == newY && piece.getX() == newX) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Produce Board with currently placed GamePieces
     * @return Board with currently placed GamePieces
     */
    public ArrayList<ArrayList<GamePiece>> getBoard() {
        return board.getBoard();
    }

    /**
     * Produce True if game is over, otherwise false
     * @return True if gameOver is reached, otherwise false
     */
    public Boolean isGameOver() {
        return gameOver;
    }

    /**
     * Produces playerOne participating in Chess
     * @return Player participating as playerOne in Chess
     */
    public Player getPlayerOne() {
        return playerOne;
    }

    /**
     * Produces playerTwo participating in Chess
     * @return Player participating as playerTwo in Chess
     */
    public Player getPlayerTwo() {
        return playerTwo;
    }
}
