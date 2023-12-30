package model.pieces;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.abs;

public class Queen extends GamePiece {

    public Queen(int x, int y, Boolean color, String player) {
        super(x, y, color, player);
        setId(1);
    }

    @Override
    public Boolean validMove(int newX, int newY) {
        Boolean horizontal = newY == getY() && newX >= 0 && newX <= 7;
        Boolean vertical = newX == getX() && newY >= 0 && newY <= 7;
        Boolean diagonal = abs(newX - getX()) == abs(newY - getY());
        if (horizontal || vertical || diagonal) {
            return true;
        }
        return false;
    }
}
