package model.pieces;

import model.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.abs;

/**
 * Queen game piece
 */
public class Queen extends GamePiece {

    public Queen(int x, int y, Boolean color, Player player) {
        super(x, y, color, player);
        setId(1);
    }

    /**
     * Checks if movement pattern is valid for Queen
     * @param newX X coordinate of movement
     * @param newY Y coordinate of movement
     * @return True if movement pattern is valid, False otherwise
     */
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
