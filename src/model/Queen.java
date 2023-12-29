package model;

public class Queen extends GamePiece{

    public Queen(int x, int y, Boolean color, String player) {
        super(x, y, color, player);
    }

    @Override
    public Boolean validMove(int newX, int newY) {
        return false;
    }
}
