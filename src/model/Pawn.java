package model;

public class Pawn extends GamePiece {

    private Boolean firstMove;

    public Pawn(int x, int y, Boolean color, String player) {
        super(x, y, color, player);
        this.firstMove = true;
    }

    @Override
    public Boolean validMove(int newX, int newY) {
        int x = this.getX();
        int y = this.getY();
        if (inBounds(newX, newY)) {
            if (isColored()) {
                if (firstMove) {
                    if (newY >= y - 2 && newY <= y && newX == x) {
                        this.firstMove = false;
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
                        this.firstMove = false;
                        return true;
                    }
                } else {
                    if (newY <= y + 1 && newY >= y && newX == x) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
