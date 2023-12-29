package model;

/**
 * model.Chess game piece
 */
public abstract class GamePiece {

    private int xPos;

    private int yPos;

    private boolean color;

    private String player;

    public GamePiece (int x, int y, Boolean color, String player) {
        setX(x);
        setY(y);
        setColor(color);
        setPlayer(player);
    }

    public Boolean move(int newX, int newY) {
        if (validMove(newX, newY)) {
            setX(newX);
            setY(newY);
            return true;
        }
        return false;
    }

    abstract Boolean validMove(int newX, int newY);

    public void setX(int x) {
        this.xPos = x;
    }

    public void setY(int y) {
        this.yPos = y;
    }

    public void setColor(Boolean color) {
        this.color = color;
    }

    public void setPlayer(String player) {
        this.player = player;
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

    public String getPlayer() {
        return player;
    }

    public Boolean inBounds(int newX, int newY) {
        if (newX >= 0 && newX <= 7 && newY >= 0 && newY <= 7) {
            return true;
        }
        return false;
    }
}
