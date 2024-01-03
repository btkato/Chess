package model.pieces;

import model.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * model.Chess game piece
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

    public void move(int newX, int newY) {
        setX(newX);
        setY(newY);
    }

    public abstract Boolean validMove(int newX, int newY);

    public void attack(int newX, int newY) {
        move(newX, newY);
    }

    public Boolean validAttack(int newX, int newY) {
        return validMove(newX, newY);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setX(int x) {
        this.xPos = x;
    }

    public void setY(int y) {
        this.yPos = y;
    }

    public void setColor(Boolean color) {
        this.color = color;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getId() {
        return id;
    }
    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    public boolean isColored() {
        return color;
    }

    public Player getPlayer() {
        return player;
    }

    public Boolean inBounds(int newX, int newY) {
        if (newX >= 0 && newX <= 7 && newY >= 0 && newY <= 7) {
            return true;
        }
        return false;
    }

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
