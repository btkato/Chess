package model.pieces;

import model.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Pawn extends GamePiece {

    private Boolean firstMove;

    public Pawn(int x, int y, Boolean color, Player player) {
        super(x, y, color, player);
        setId(5);
        this.firstMove = true;
    }

    @Override
    public Boolean validMove(int newX, int newY) {
        int x = this.getX();
        int y = this.getY();
        if (inBounds(newX, newY)) {
            if (isColored()) {
                return colorValidMove(newX, newY);
            } else {
                return nonColorValidMove(newX, newY);
            }
        }
        return false;
    }
    public Boolean colorValidMove(int newX, int newY) {
        int x = this.getX();
        int y = this.getY();
        if (firstMove) {
            if (newY >= y - 2 && newY <= y && newX == x) {
                this.firstMove = false;
                return true;
            }
        } else {
            if (newY >= y - 1 && newY <= y && newX == x) {
                return true;
            }
        }
        return false;
    }

    public Boolean nonColorValidMove(int newX, int newY) {
        int x = this.getX();
        int y = this.getY();
        if (firstMove) {
            if (newY <= y + 2 && newY >= y && newX == x) {
                this.firstMove = false;
                return true;
            }
        } else {
            if (newY <= y + 1 && newY >= y && newX == x) {
                return true;
            }
        }
        return false;
    }
}
