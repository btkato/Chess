package model.pieces;

import model.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.abs;

public class Bishop extends GamePiece {

    public Bishop(int x, int y, Boolean color, Player player) {
        super(x, y, color, player);
        setId(2);
    }

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
