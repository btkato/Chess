package model;

public class King extends GamePiece{

    public King (int x, int y, Boolean color, String player) {
        setX(x);
        setY(y);
        setColor(color);
        setPlayer(player);
    }

    @Override
    public Boolean move(int newX, int newY) {
        if (validMove(newX, newY)) {
            setX(newX);
            setY(newY);
            return true;
        }
        return false;
    }

    @Override
    public Boolean validMove(int newX, int newY) {
        if (inBounds(newX, newY)) {
            if (newX >= getX() - 1 && newX <= getX() + 1 && newY >= getY() - 1 && newY <= getY() + 1) {
                return true;
            }
        }
        return false;
    }
}
