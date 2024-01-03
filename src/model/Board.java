package model;

import model.pieces.GamePiece;
import model.pieces.Pawn;

import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.min;

/**
 * model.Chess game board
 */
public class Board {
    private ArrayList<ArrayList<GamePiece>> board;

    public Board() {
        this.board = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            ArrayList<GamePiece> row = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                row.add(j, null);
            }
            board.add(i, row);
        }
    }

    /**
     * Places new chess piece onto game board
     * @param g game piece that will be placed on the board
     */
    public void placePiece(GamePiece g) {
        ArrayList<GamePiece> row = board.get(g.getY());
        row.set(g.getX(), g);
        board.set(g.getY(), row);
    }

    public void removePiece(int oldX, int oldY) {
        ArrayList<GamePiece> row = board.get(oldY);
        row.set(oldX, null);
        board.set(oldY, row);
    }

    /**
     * Moves chess piece on board in valid movement pattern
     * @param oldX old x coordinate of chess piece
     * @param oldY old y coordinate of chess piece
     * @param newX new x coordinate of chess piece
     * @param newY new y coordinate of chess piece
     * @param g game piece that will be moved
     */
    public void movePiece(int oldX, int oldY, int newX, int newY, GamePiece g) {
        GamePiece p = getGamePiece(newX, newY);
        Boolean sameX = newX == oldX;
        Boolean sameY = newY == oldY;
        Boolean sameCoord = sameX && sameY;
        if (!sameCoord) {
            if (g.validMove(newX, newY)) {
                if (noObstructions(g, newX, newY, 0)) {
                    if (p == null) {
                        g.move(newX, newY);
                        placePiece(g);
                        removePiece(oldX, oldY);
                    }
                }
            }
        }
    }

    public void attackPiece(int oldX, int oldY, int newX, int newY, GamePiece g) {
        GamePiece attack = getGamePiece(newX, newY);
        Boolean sameX = newX == oldX;
        Boolean sameY = newY == oldY;
        Boolean sameCoord = sameX && sameY;
        if (!sameCoord) {
            if (attack != null) {
                if (!g.getPlayer().getName().equals(attack.getPlayer().getName())) {
                    if (g.validAttack(newX, newY)) {
                        if (noObstructions(g, newX, newY, 1)) {
                            g.move(newX, newY);
                            placePiece(g);
                            removePiece(oldX, oldY);
                        }
                    }
                }
            }
        }
    }

    public Boolean noObstructions(GamePiece piece, int newX, int newY, int type) {
        int result = 0;
        switch (piece.getId()) {
            case 0:
                result = kingandQueenObstructions(piece, newX, newY);
                break;
            case 1:
                result = kingandQueenObstructions(piece, newX, newY);
                break;
            case 2:
                result = bishopObstructions(piece, newX, newY);
                break;
            case 3:
                result = knightObstructions(piece, newX, newY, type);
                break;
            case 4:
                result = rookObstructions(piece, newX, newY);
                break;
            case 5:
                result = pawnObstructions(piece, newX, newY, type);
                break;
        }
        if (type == 0) {
            return result <= 0;
        } else {
            return result == 1;
        }
    }

    public int downObstruction (GamePiece piece, int newX, int newY) {
        int i = 0;
        for (int y = piece.getY() + 1; y <= newY; y++) {
            if (board.get(y).get(piece.getX()) != null) {
                i++;
            }
        }
        return i;
    }

    public int upObstruction (GamePiece piece, int newX, int newY) {
        int i = 0;
        for (int y = piece.getY() - 1; y >= newY; y--) {
            if (board.get(y).get(piece.getX()) != null) {
                i++;
            }
        }
        return i;
    }

    public int rightObstruction (GamePiece piece, int newX, int newY) {
        int i = 0;
        for (int x = piece.getX() + 1; x <= newX; x++) {
            if (board.get(piece.getY()).get(x) != null) {
                i++;
            }
        }
        return i;
    }

    public int leftObstruction (GamePiece piece, int newX, int newY) {
        int i = 0;
        for (int x = piece.getX() - 1; x >= newX; x--) {
            if (board.get(piece.getY()).get(x) != null) {
                i++;
            }
        }
        return i;
    }

    public int downRightObstruction (GamePiece piece, int newX, int newY) {
        int i = 0;
        for (int y = piece.getY() + 1; y <= newY; y++) {
            for (int x = piece.getX() + 1; x <= newX; x++) {
                if (abs(newX - x) == abs(newY - y)) {
                    if (board.get(y).get(x) != null) {
                        i++;
                    }
                }
            }
        }
        return i;
    }

    public int downLeftObstruction (GamePiece piece, int newX, int newY) {
        int i = 0;
        for (int y = piece.getY() + 1; y <= newY; y++) {
            for (int x = piece.getX() - 1; x >= newX; x--) {
                if (abs(newX - x) == abs(newY - y)) {
                    if (board.get(y).get(x) != null) {
                        i++;
                    }
                }
            }
        }
        return i;
    }

    public int upLeftObstruction (GamePiece piece, int newX, int newY) {
        int i = 0;
        for (int y = piece.getY() - 1; y >= newY; y--) {
            for (int x = piece.getX() - 1; x >= newX; x--) {
                if (abs(newX - x) == abs(newY - y)) {
                    if (board.get(y).get(x) != null) {
                        i++;
                    }
                }
            }
        }
        return i;
    }

    public int upRightObstruction (GamePiece piece, int newX, int newY) {
        int i = 0;
        for (int y = piece.getY() - 1; y >= newY; y--) {
            for (int x = piece.getX() + 1; x <= newX; x++) {
                if (abs(newX - x) == abs(newY - y)) {
                    if (board.get(y).get(x) != null) {
                        i++;
                    }
                }
            }
        }
        return i;
    }

    public int kingandQueenObstructions(GamePiece piece, int newX, int newY) {
        int dX = newX - piece.getX();
        int dY = newY - piece.getY();
        if (dX == 0 && dY > 0) {
            return downObstruction(piece, newX, newY);
        } else if (dX == 0 && dY < 0) {
            return upObstruction(piece, newX, newY);
        } else if (dX > 0 && dY == 0) {
            return rightObstruction(piece, newX, newY);
        } else if (dX < 0 && dY == 0) {
            return leftObstruction(piece, newX, newY);
        } else if (dX > 0 && dY > 0) {
            return downRightObstruction(piece, newX, newY);
        } else if (dX > 0 && dY < 0) {
            return upRightObstruction(piece, newX, newY);
        } else if (dX < 0 && dY < 0) {
            return upLeftObstruction(piece, newX, newY);
        } else {
            return downLeftObstruction(piece, newX, newY);
        }
    }

    public int bishopObstructions(GamePiece piece, int newX, int newY) {
        int dX = newX - piece.getX();
        int dY = newY - piece.getY();
        if (dX > 0 && dY > 0) {
            return downRightObstruction(piece, newX, newY);
        } else if (dX > 0 && dY < 0) {
            return upRightObstruction(piece, newX, newY);
        } else if (dX < 0 && dY < 0) {
            return upLeftObstruction(piece, newX, newY);
        } else {
            return downLeftObstruction(piece, newX, newY);
        }
    }

    public int knightObstructions(GamePiece piece, int newX, int newY, int type) {
        int dX = newX - piece.getX();
        int dY = newY - piece.getY();
        int i = 0;
        if (abs(dX) > abs(dY)) {
            i = largerX(piece, dX, dY);
        } else {
            i = largerY(piece, dX, dY);
        }
        if (type == 0) {
            return i - 2;
        }
        else {
            if (i == 2 || i == 3) {
                return 1;
            } else {
                return i;
            }
        }
    }

    public int largerX(GamePiece piece, int dX, int dY) {
        int count;
        int countOne = 0;
        int countTwo = 0;
        int xDir = dX < 0? -1 : 1;
        int yDir = dY < 0? -1 : 1;
        for (int y = 0; y <= abs(dY); y++) {
            for (int x = 0; x <= abs(dX); x++) {
                if (y == 0 || (y == dY && x == dX)) {
                    GamePiece p = board.get(y * yDir + piece.getY()).get(x * xDir + piece.getX());
                    if (p != null) {
                        countOne++;
                    }
                }
                if (y == 1 || (y == dY && x == dX) || (x == 0 && y == 0)) {
                    GamePiece p = board.get(y * yDir + piece.getY()).get(x * xDir + piece.getX());
                    if (p != null) {
                        countTwo++;
                    }
                }
            }
        }
        count = min(countOne, countTwo);
        return count;
    }

    public int largerY(GamePiece piece, int dX, int dY) {
        int count;
        int countOne = 0;
        int countTwo = 0;
        int xDir = dX < 0? -1 : 1;
        int yDir = dY < 0? -1 : 1;
        for (int y = 0; y <= abs(dY); y++) {
            for (int x = 0; x <= abs(dX); x++) {
                Boolean end = x == abs(dX) && y == abs(dY);
                Boolean start = x == 0 && y == 0;
                if (x == 0 || end) {
                    GamePiece p = board.get(y * yDir + piece.getY()).get(x * xDir + piece.getX());
                    if (p != null) {
                        countOne++;
                    }
                }
                if (x == 1 || end || start) {
                    GamePiece p = board.get(y * yDir + piece.getY()).get(x * xDir + piece.getX());
                    if (p != null) {
                        countTwo++;
                    }
                }
            }
        }
        count = min(countOne, countTwo);
        return count;
    }

    public int rookObstructions(GamePiece piece, int newX, int newY) {
        int dX = newX - piece.getX();
        int dY = newY - piece.getY();
        if (dX == 0 && dY < 0) {
            return upObstruction(piece, newX, newY);
        } else if (dX == 0 && dY > 0) {
            return downObstruction(piece, newX, newY);
        } else if (dX > 0 && dY == 0) {
            return rightObstruction(piece, newX, newY);
        } else {
            return leftObstruction(piece, newX, newY);
        }
    }

    public int pawnObstructions(GamePiece piece, int newX, int newY, int type) {
        int dX = newX - piece.getX();
        int dY = newY - piece.getY();
        if (type == 0) {
            if (dX == 0 && dY < 0) {
                return upObstruction(piece, newX, newY);
            } else {
                return downObstruction(piece, newX, newY);
            }
        } else {
            if (dX > 0 && dY > 0) {
                return downRightObstruction(piece, newX, newY);
            } else if (dX > 0 && dY < 0) {
                return upRightObstruction(piece, newX, newY);
            } else if (dX < 0 && dY < 0) {
                return upLeftObstruction(piece, newX, newY);
            } else {
                return downLeftObstruction(piece, newX, newY);
            }
        }
    }

    public int getRows() {
        return board.size();
    }

    public int getCols(int i) {
        return board.get(i).size();
    }

    public GamePiece getGamePiece(int x, int y) {
        return board.get(y).get(x);
    }

    public ArrayList<ArrayList<GamePiece>> getBoard() {
        return board;
    }
}
