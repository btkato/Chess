package model.pieces;

import model.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Pawn game piece
 */
public class Pawn extends GamePiece {

    private Boolean firstMove;

    public Pawn(int x, int y, Boolean color, Player player) {
        super(x, y, color, player);
        setId(5);
        this.firstMove = true;
    }

    /**
     * Checks if movement pattern is valid for Pawn
     * @param newX X coordinate of movement
     * @param newY Y coordinate of movement
     * @return True if movement pattern is valid, False otherwise
     */
    @Override
    public Boolean validMove(int newX, int newY) {
        if (isColored()) {
            return colorValidMove(newX, newY);
        } else {
            return nonColorValidMove(newX, newY);
        }
    }

    /**
     * Checks if movement pattern is valid for colored Pawn
     * @param newX X coordinate of movement
     * @param newY Y coordinate of movement
     * @return True if movement pattern is valid, False otherwise
     */
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

    /**
     * Checks if movement pattern is valid for non-colored Pawn
     * @param newX X coordinate of movement
     * @param newY Y coordinate of movement
     * @return True if movement pattern is valid, False otherwise
     */
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

    /**
     * Checks if attack pattern is valid for Pawn
     * @param newX X coordinate of attack
     * @param newY X coordinate of attack
     * @return True if attack pattern is valid, False otherwise
     */
    @Override
    public Boolean validAttack(int newX, int newY) {
        if (isColored()) {
            return colorValidAttack(newX, newY);
        } else {
            return nonColorValidAttack(newX, newY);
        }
    }

    /**
     * Checks if attack pattern is valid for colored Pawn
     * @param newX X coordinate of attack
     * @param newY X coordinate of attack
     * @return True if attack pattern is valid, False otherwise
     */
    public Boolean colorValidAttack(int newX, int newY) {
        if (newY == this.getY() - 1 && (newX == this.getX() - 1 || newX == this.getX() + 1)) {
            if (firstMove) {
                firstMove = false;
            }
            return true;
        }
        return false;
    }

    /**
     * Checks if attack pattern is valid for non-colored Pawn
     * @param newX X coordinate of attack
     * @param newY X coordinate of attack
     * @return True if attack pattern is valid, False otherwise
     */
    public Boolean nonColorValidAttack(int newX, int newY) {
        if (newY == this.getY() + 1 && (newX == this.getX() - 1 || newX == this.getX() + 1)) {
            if (firstMove) {
                firstMove = false;
            }
            return true;
        }
        return false;
    }

}
