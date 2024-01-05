package model.pieces;

import model.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Rook game piece
 */
public class Rook extends GamePiece {

    public Rook (int x, int y, Boolean color, Player player) {
        super(x, y, color, player);
        setId(4);
    }

    /**
     * Checks if movement pattern is valid for Rook
     * @param newX X coordinate of movement
     * @param newY Y coordinate of movement
     * @return True if movement pattern is valid, False otherwise
     */
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
