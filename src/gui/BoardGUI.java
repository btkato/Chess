package gui;

import model.Chess;
import model.Player;
import model.pieces.GamePiece;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class BoardGUI {

    private JFrame boardFrame;

    private JPanel boardPanel;

    private Chess game;

    private GamePiece selectedPiece;

    private String turn;

    public BoardGUI() {
        game = new Chess();
        initializeBoard();
        turn = game.getPlayerOne().getName();
        playerMove();
    }

    public void initializeBoard() {
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

    public void playerMove() {

        boardFrame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (getPiece(e.getX(), e.getY()) == null) {
                    System.out.println("nothing!");
                } else {
                    System.out.println(getPiece(e.getX(), e.getY()).getId());
                    System.out.println(e.getX() / 64 + " ");
                    System.out.println(e.getY() / 64);
                }
                selectedPiece = getPiece(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println(e.getX() / 64 + " ");
                System.out.println(e.getY() / 64);
                if (selectedPiece != null) {
                    if (game.validMove(turn, selectedPiece, e.getX() / 64, e.getY() / 64)) {
                        game.move(turn, selectedPiece, e.getX()/64, e.getY()/64);
                        boardPanel.repaint();

                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Invalid Move",
                                null, JOptionPane.WARNING_MESSAGE);
                    }
                }
                if (selectedPiece != null) {
                    nextTurn(turn);
                }
                selectedPiece = null;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public GamePiece getPiece(int x, int y) {
        ArrayList<ArrayList<GamePiece>> board = game.getBoard();
        return board.get(y / 64).get(x / 64);
    }

    public void nextTurn(String turn) {
        if (turn.equals(game.getPlayerOne().getName())) {
            this.turn = game.getPlayerTwo().getName();
        } else {
            this.turn = game.getPlayerOne().getName();
        }
    }
}
