package model.pieces;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Rook extends GamePiece {

    public Rook (int x, int y, Boolean color, String player) {
        super(x, y, color, player);
        setId(4);
    }

    @Override
    public Boolean validMove(int newX, int newY) {
        Boolean horizontal = newX >= 0 && newX <= 7;
        Boolean vertical = newY >= 0 && newY <= 7;

        if (vertical && getX() == newX) {
            return true;
        } else if (horizontal && getY() == newY) {
            return true;
        } else {
            return false;
        }
    }
}