package model.gui;

import model.Chess;
import model.pieces.GamePiece;
import model.pieces.Pawn;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BoardGUI {

    private JFrame boardFrame;

    private JPanel boardPanel;

    private Chess game;

    public BoardGUI() {
        game = new Chess();
        boardFrame = new JFrame();
        boardFrame.setUndecorated(true);
        boardFrame.setBounds(10, 10, 512, 512);
        renderBoard();
        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardFrame.setVisible(true);
    }

    public void renderBoard() {
        boardPanel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                renderSquares(g);
                renderPieces(g);
            }
        };
        boardFrame.add(boardPanel);
    }

    public void renderSquares(Graphics g) {
        Boolean colorWhite = true;
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (colorWhite) {
                    g.setColor(Color.GRAY);
                } else {
                    g.setColor(Color.DARK_GRAY);
                }
                g.fillRect(x * 64, y * 64, 64, 64);
                colorWhite = !colorWhite;
            }
            colorWhite = !colorWhite;
        }
    }

    public void renderPieces(Graphics g) {
        ArrayList<ArrayList<GamePiece>> board = game.getBoard();
        for (int i = 0; i < board.size(); i ++) {
            ArrayList<GamePiece> row = board.get(i);
            for (int j = 0; j < row.size(); j++) {
                if (row.get(j) != null) {
                    paintPiece(g, row.get(j));
                }
            }
        }
    }

    public void paintPiece(Graphics g, GamePiece p) {
        g.drawImage(p.getIcon(p.getId(), p.isColored()), p.getX() * 64, p.getY() * 64, null);
    }

}
