package model.pieces;

import model.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * King game piece
 */
public class King extends GamePiece {

    public King (int x, int y, Boolean color, Player player) {
        super(x, y, color, player);
        setId(0);
    }

    /**
     * Checks if movement pattern is valid for King
     * @param newX X coordinate of movement
     * @param newY Y coordinate of movement
     * @return True if movement pattern is valid, False otherwise
     */
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
