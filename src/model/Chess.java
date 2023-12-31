package model;

import model.pieces.GamePiece;

import java.util.ArrayList;
import java.util.Iterator;

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

    public void initializeBoard(Player player) {
        player.initializePieces(player.isColored());
        Iterator<GamePiece> itOne = player.getPieces().iterator();
        while (itOne.hasNext()) {
            GamePiece p = itOne.next();
            board.placePiece(p);
        }
    }

    public void move(String player, GamePiece piece, int newX, int newY) {
        board.movePiece(piece.getX(), piece.getY(), newX, newY, piece);
    }

    public Boolean validMove(String player, GamePiece piece, int newX, int newY) {
        //if (piece.getPlayer().getName().equals(player)) {
            if (piece.validMove(newX, newY)) {
               if (board.noObstructions(piece, newX, newY)) {
                   return true;
               }
            }
        //}
        return false;
    }
    public ArrayList<ArrayList<GamePiece>> getBoard() {
        return board.getBoard();
    }

    public Boolean isGameOver() {
        return gameOver;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }
}
