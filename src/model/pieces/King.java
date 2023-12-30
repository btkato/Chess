package model.pieces;

import model.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class King extends GamePiece {

    public King (int x, int y, Boolean color, Player player) {
        super(x, y, color, player);
        setId(0);
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
