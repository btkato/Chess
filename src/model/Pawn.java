package model;

public class Pawn extends GamePiece {

    private Boolean firstMove;

    public Pawn(int x, int y, Boolean color, String player) {
        setX(x);
        setY(y);
        setColor(color);
        setPlayer(player);
        this.firstMove = true;
    }
    @Override
    public Boolean move(int newX, int newY) {
        if (validMove(newX,newY)) {
            setX(newX);
            setY(newY);
            if (firstMove) {
                this.firstMove = false;
            }
            return true;
        }
        return false;
    }

    @Override
    public Boolean validMove(int newX, int newY) {
        int x = this.getX();
        int y = this.getY();
        if (isColored()) {
            if (firstMove) {
                if (newY >= y - 2 && newY <= y && newX == x) {
                    return true;
                }
            } else {
                if (newY >= y - 1 && newY <= y && newX == x) {
                    return true;
                }
            }
        } else {
            if (firstMove) {
                if (newY <= y + 2 && newY >= y && newX == x) {
                    return true;
                }
            } else {
                if (newY <= y + 1 && newY >= y && newX == x) {
                    return true;
                }
            }
        }
        return false;
    }
}
