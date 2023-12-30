package model;

import model.pieces.GamePiece;
import model.pieces.Pawn;
import model.pieces.Rook;
import model.pieces.Bishop;
import model.pieces.Knight;
import model.pieces.Queen;
import model.pieces.King;

import java.util.ArrayList;

public class Player {

    private String name;
    private Boolean color;
    private ArrayList<GamePiece> pieces;

    public Player(String name, Boolean color) {
        this.name = name;
        this.color = color;
        pieces = new ArrayList<>();
        initializePawns(name, color);
        initializeRooks(name, color);
        initializeBishops(name, color);
        initializeKnights(name, color);
        initializeQueen(name, color);
        initializeKing(name, color);
    }

    public void initializePawns(String name, Boolean color) {
        int y;
        if (color) {
            y = 6;
        } else {
            y = 1;
        }
        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(i, y, color, name));
        }
    }

    public void initializeRooks(String name, Boolean color) {
        int y = setY();
        for (int i = 0; i < 8; i = i + 7) {
            pieces.add(new Rook(i, y, color, name));
        }
    }

    public void initializeBishops(String name, Boolean color) {
        int y = setY();
        for (int i = 2; i < 6; i = i + 3) {
            pieces.add(new Bishop(i, y, color, name));
        }
    }

    public void initializeKnights(String name, Boolean color) {
        int y = setY();
        for (int i = 1; i < 7; i = i + 5) {
            pieces.add(new Knight(i, y, color, name));
        }
    }

    public void initializeQueen(String name, Boolean color) {
        int y = setY();
        pieces.add(new Queen(3, y, color, name));
    }

    public void initializeKing(String name, Boolean color) {
        int y = setY();
        pieces.add(new King(4, y, color, name));
    }

    public int setY() {
        if (color) {
            return 7;
        } else {
            return 0;
        }
    }

    public ArrayList<GamePiece> getPieces() {
        return pieces;
    }

    public String getName() {
        return this.name;
    }

    public Boolean isColored() {
        return this.color;
    }
}
