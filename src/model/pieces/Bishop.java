package model.pieces;

import model.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.abs;

/**
 * Bishop game piece
 */
public class Bishop extends GamePiece {

    public Bishop(int x, int y, Boolean color, Player player) {
        super(x, y, color, player);
        setId(2);
    }

    /**
     * Checks if movement pattern is valid for Bishop piece
     * @param newX X coordinate of movement
     * @param newY Y coordinate of movement
     * @return True if movement pattern is valid, False otherwise
     */
    @Override
    public Boolean validMove(int newX, int newY) {
        int xDist = abs(newX - getX());
        int yDist = abs(newY - getY());
        if (xDist == yDist) {
            return true;
        }
        return false;
    }
}
