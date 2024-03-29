package model;

import model.pieces.GamePiece;
import model.pieces.Pawn;
import model.pieces.Rook;
import model.pieces.Bishop;
import model.pieces.Knight;
import model.pieces.Queen;
import model.pieces.King;

import java.util.ArrayList;

/**
 * Player class in Chess
 */
public class Player {

    private String name;
    private Boolean color;
    private ArrayList<GamePiece> pieces;

    public Player(String name, Boolean color) {
        this.name = name;
        this.color = color;
        pieces = new ArrayList<>();

    }

    /**
     * Creates all pieces at correct starting positions for a specified player
     * @param color True if player is designated as colored, otherwise false
     */
    public void initializePieces(Boolean color) {
        initializePawns(color);
        initializeRooks(color);
        initializeBishops(color);
        initializeKnights(color);
        initializeQueen(color);
        initializeKing(color);
    }

    /**
     * Creates all Pawns for player at correct starting positions
     * @param color True if player is designated as colored, otherwise false
     */
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

    /**
     * Creates all Rooks for player at correct starting positions
     * @param color True if player is designated as colored, otherwise false
     */
    public void initializeRooks(Boolean color) {
        int y = setY();
        for (int i = 0; i < 8; i = i + 7) {
            pieces.add(new Rook(i, y, color, this));
        }
    }

    /**
     * Creates all Bishops for player at correct starting positions
     * @param color True if player is designated as colored, otherwise false
     */
    public void initializeBishops(Boolean color) {
        int y = setY();
        for (int i = 2; i < 6; i = i + 3) {
            pieces.add(new Bishop(i, y, color, this));
        }
    }

    /**
     * Creates all Knights for player at correct starting positions
     * @param color True if player is designated as colored, otherwise false
     */
    public void initializeKnights(Boolean color) {
        int y = setY();
        for (int i = 1; i < 7; i = i + 5) {
            pieces.add(new Knight(i, y, color, this));
        }
    }

    /**
     * Creates Queen for player at correct starting position
     * @param color True if player is designated as colored, otherwise false
     */
    public void initializeQueen(Boolean color) {
        int y = setY();
        pieces.add(new Queen(3, y, color, this));
    }

    /**
     * Creates King for player at correct starting position
     * @param color True if player is designated as colored, otherwise false
     */
    public void initializeKing(Boolean color) {
        int y = setY();
        pieces.add(new King(4, y, color, this));
    }

    /**
     * Sets Y position of back-row pieces depending on if pieces are colored or not
     * @return Y position of back-row pieces
     */
    public int setY() {
        if (color) {
            return 7;
        } else {
            return 0;
        }
    }

    /**
     * Produces list of all GamePieces for Player
     * @return List of GamePiece
     */
    public ArrayList<GamePiece> getPieces() {
        return pieces;
    }

    /**
     * Produce name of Player
     * @return Name of Player
     */
    public String getName() {
        return this.name;
    }

    /**
     * Determines if player has colored pieces
     * @return Produce True if Player's GamePieces are colored, otherwise produce False
     */
    public Boolean isColored() {
        return this.color;
    }
}
