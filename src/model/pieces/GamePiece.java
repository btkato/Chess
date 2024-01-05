package model.pieces;

import model.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * GamePiece class for Chess
 */
public abstract class GamePiece {

    private int xPos;

    private int yPos;

    private boolean color;

    private int id;

    private Player player;

    BufferedImage sheet;
    {
        try {
            sheet = ImageIO.read(new File("./data/Pieces.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    protected int sheetW = sheet.getWidth();

    protected int sheetH = sheet.getHeight();

    protected int iconW = sheetW / 6;

    protected int iconH = sheetH / 2;

    private BufferedImage iconR;
    protected Image icon;

    public GamePiece(int x, int y, Boolean color, Player player) {
        setX(x);
        setY(y);
        setColor(color);
        setPlayer(player);
    }

    /**
     * Moves GamePiece to specified X and Y coordinate
     * @param newX X coordinate of movement
     * @param newY Y coordinate of movement
     */
    public void move(int newX, int newY) {
        setX(newX);
        setY(newY);
    }

    /**
     * Determines if GamePiece movement pattern is valid
     * @param newX X coordinate of movement
     * @param newY Y coordinate of movement
     * @return Produce true if movement pattern is valid of GamePiece, otherwise produce false
     */
    public abstract Boolean validMove(int newX, int newY);

    /**
     * Determines if GamePiece attack pattern is valid
     * @param newX X coordinate of attack
     * @param newY X coordinate of attack
     * @return Produce true if attack pattern is valid of GamePiece, otherwise produce false
     */
    public Boolean validAttack(int newX, int newY) {
        return validMove(newX, newY);
    }

    /**
     * Sets piece identification
     * 0 is King
     * 1 is Queen
     * 2 is Bishop
     * 3 is Knight
     * 4 is Rook
     * 5 is Pawn
     * @param id Identification number of GamePiece
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets X coordinate of GamePiece to a position of [0, 7] inclusive
     * @param x X coordinate of GamePiece
     */
    public void setX(int x) {
        this.xPos = x;
    }

    /**
     * Sets Y coordinate of GamePiece to a position of [0, 7] inclusive
     * @param y Y coordinate of GamePiece
     */
    public void setY(int y) {
        this.yPos = y;
    }

    /**
     * Sets color of GamePiece
     * @param color True if GamePiece is colored, otherwise False
     */
    public void setColor(Boolean color) {
        this.color = color;
    }

    /**
     * Sets Player that owns GamePiece
     * @param player Player class that owns the GamePiece
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Produces GamePiece identification number
     * @return GamePiece identification number
     */
    public int getId() {
        return id;
    }

    /**
     * Produces X coordinate of GamePiece
     * @return GamePiece's X coordinate
     */
    public int getX() {
        return xPos;
    }

    /**
     * Produces Y coordinate of GamePiece
     * @return GamePiece's Y coordinate
     */
    public int getY() {
        return yPos;
    }

    /**
     * Determines if GamePiece is colored
     * @return Produce True if GamePiece is colored, otherwise False
     */
    public boolean isColored() {
        return color;
    }

    /**
     * Produces Player that owns GamePiece
     * @return Player that owns GamePiece
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Determines if GamePiece is within the bounds of [0, 7] inclusive for both X and Y coordinates
     * @param newX X position of movement
     * @param newY Y position of movement
     * @return True if X and Y coordinates are within the bounds of [0, 7] inclusive, otherwise False
     */
    public Boolean inBounds(int newX, int newY) {
        if (newX >= 0 && newX <= 7 && newY >= 0 && newY <= 7) {
            return true;
        }
        return false;
    }

    /**
     * Produces GamePiece icon image
     * @param id GamePiece identification number
     * @param color True if GamePiece is colored, otherwise False
     * @return GamePiece icon image
     */
    public Image getIcon(int id, Boolean color) {
        if (color) {
            iconR = sheet.getSubimage(id * iconW, iconH, iconW, iconH);
            return icon = iconR.getScaledInstance(64, 64, 1);
        }
        else {
            iconR = sheet.getSubimage(id * iconW, 0, iconW, iconH);
            return icon = iconR.getScaledInstance(64, 64, 1);
        }
    }
}
