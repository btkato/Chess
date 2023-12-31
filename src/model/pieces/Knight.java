package model.pieces;

import model.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Knight extends GamePiece {

    public Knight(int x, int y, Boolean color, Player player) {
        super(x, y, color, player);
        setId(3);
    }

    @Override
    public Boolean validMove(int newX, int newY) {
        Boolean downRight = newY + 2 == getY() && newX + 1 == getX();
        Boolean downLeft = newY + 2 == getY() && newX - 1 == getX();
        Boolean rightDown = newX + 2 == getX() && newY + 1 == getY();
        Boolean rightUp = newX + 2 == getX() && newY - 1 == getY();
        Boolean upRight = newY - 2 == getY() && newX + 1 == getX();
        Boolean upLeft = newY - 2 == getY() && newX - 1 == getX();
        Boolean leftDown = newX - 2 == getX() && newY + 1 == getY();
        Boolean leftUp = newX - 2 == getX() && newY - 1 == getY();
        Boolean moves = upLeft || upRight || rightUp || rightDown || downRight || downLeft || leftUp || leftDown;
        if (moves) {
            return true;
        }
        return false;
    }
}
