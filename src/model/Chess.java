package model;

import model.pieces.GamePiece;

import java.util.ArrayList;
import java.util.Iterator;

public class Chess {

    private Player playerOne;

    private Player playerTwo;

    private Board board;
    public Chess() {
        playerOne = new Player("PlayerOne", false);
        playerTwo = new Player("PlayerTwo", true);
        board = new Board();
        initializeBoard(playerOne);
        initializeBoard(playerTwo);
    }

    public void initializeBoard(Player player) {
        Iterator<GamePiece> itOne = player.getPieces().iterator();
        while (itOne.hasNext()) {
            GamePiece p = itOne.next();
            board.placePiece(p);
        }
    }

    public ArrayList<ArrayList<GamePiece>> getBoard() {
        return board.getBoard();
    }
}
