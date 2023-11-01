package model;

/**
 * model.Chess game piece
 */
public class GamePiece {

    private int xPos;
    private int yPos;
    private boolean color;

    /**
     * Constructor for chess game piece with color, x and y position
     * @param color True if piece is black, false if piece is white
     * @param x x position on chess board
     * @param y y position on chess board
     */
    public GamePiece(boolean color, int x, int y) {
        this.xPos = x;
        this.yPos = y;
        this.color = color;
    }

    /**
     * Moves game piece's x and y position on the board.
     * x and y coordinate must be within 0-based index of game board size [0,7].
     * @param newX
     * @param newY
     */
    public void move(int newX, int newY) {
        setX(newX);
        setY(newY);
    }
    public void setX(int x) {
        this.xPos = x;
    }

    public void setY(int y) {
        this.yPos = y;
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    public boolean isColored() {
        return color;
    }
}
