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

    }

    public void initializePieces(Boolean color) {
        initializePawns(color);
        initializeRooks(color);
        initializeBishops(color);
        initializeKnights(color);
        initializeQueen(color);
        initializeKing(color);
    }

    public void initializePawns(Boolean color) {
        int y;
        if (color) {
            y = 6;
        } else {
            y = 1;
        }
        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(i, y, color, this));
        }
    }

    public void initializeRooks(Boolean color) {
        int y = setY();
        for (int i = 0; i < 8; i = i + 7) {
            pieces.add(new Rook(i, y, color, this));
        }
    }

    public void initializeBishops(Boolean color) {
        int y = setY();
        for (int i = 2; i < 6; i = i + 3) {
            pieces.add(new Bishop(i, y, color, this));
        }
    }

    public void initializeKnights(Boolean color) {
        int y = setY();
        for (int i = 1; i < 7; i = i + 5) {
            pieces.add(new Knight(i, y, color, this));
        }
    }

    public void initializeQueen(Boolean color) {
        int y = setY();
        pieces.add(new Queen(3, y, color, this));
    }

    public void initializeKing(Boolean color) {
        int y = setY();
        pieces.add(new King(4, y, color, this));
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
