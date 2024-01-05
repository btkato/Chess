package model;

import model.pieces.GamePiece;

import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.min;

/**
 * Chess game board
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

    /**
     * Removes existing placed piece on game board
     * @param oldX X coordinate of piece that will be removed
     * @param oldY Y coordinate of piece that will be removed
     */
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

    /**
     * Moves chess piece to attack in a valid position
     * @param oldX X position of chess piece that will be attacking
     * @param oldY Y position of chess piece that will be attacking
     * @param newX X position of chess piece that will be captured
     * @param newY Y position of chess piece that will be captured
     * @param g Chess piece that will be attacking
     */
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

    /**
     * Produce true if GamePiece has no obstructions on the board
     * @param piece GamePiece attempting to make a move or attack
     * @param newX X coordinate of piece's move or attack
     * @param newY Y coordinate of piece's move or attack
     * @param type 0 if piece is moving, 1 if piece is attacking
     * @return true if GamePiece has no obstructions, otherwise false
     */
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

    /**
     * Produce number of obstructions for the downward direction of piece
     * @param piece GamePiece moving
     * @param newX X coordinate of piece's movement
     * @param newY Y coordinate of piece's movement
     * @return Number of pieces obstructing GamePiece, not including the moving piece
     */
    public int downObstruction (GamePiece piece, int newX, int newY) {
        int i = 0;
        for (int y = piece.getY() + 1; y <= newY; y++) {
            if (board.get(y).get(piece.getX()) != null) {
                i++;
            }
        }
        return i;
    }

    /**
     * Produce number of obstructions for the upward direction of piece
     * @param piece GamePiece moving
     * @param newX X coordinate of piece's movement
     * @param newY Y coordinate of piece's movement
     * @return Number of pieces obstructing GamePiece, not including the moving piece
     */
    public int upObstruction (GamePiece piece, int newX, int newY) {
        int i = 0;
        for (int y = piece.getY() - 1; y >= newY; y--) {
            if (board.get(y).get(piece.getX()) != null) {
                i++;
            }
        }
        return i;
    }

    /**
     * Produce number of obstructions for the rightward direction of piece
     * @param piece GamePiece moving
     * @param newX X coordinate of piece's movement
     * @param newY Y coordinate of piece's movement
     * @return Number of pieces obstructing GamePiece, not including the moving piece
     */
    public int rightObstruction (GamePiece piece, int newX, int newY) {
        int i = 0;
        for (int x = piece.getX() + 1; x <= newX; x++) {
            if (board.get(piece.getY()).get(x) != null) {
                i++;
            }
        }
        return i;
    }

    /**
     * Produce number of obstructions for the leftward direction of piece
     * @param piece GamePiece moving
     * @param newX X coordinate of piece's movement
     * @param newY Y coordinate of piece's movement
     * @return Number of pieces obstructing GamePiece, not including the moving piece
     */
    public int leftObstruction (GamePiece piece, int newX, int newY) {
        int i = 0;
        for (int x = piece.getX() - 1; x >= newX; x--) {
            if (board.get(piece.getY()).get(x) != null) {
                i++;
            }
        }
        return i;
    }

    /**
     * Produce number of obstructions for the diagonal down-right direction of piece
     * @param piece GamePiece moving
     * @param newX X coordinate of piece's movement
     * @param newY Y coordinate of piece's movement
     * @return Number of pieces obstructing GamePiece, not including the moving piece
     */
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

    /**
     * Produce number of obstructions for the diagonal down-left direction of piece
     * @param piece GamePiece moving
     * @param newX X coordinate of piece's movement
     * @param newY Y coordinate of piece's movement
     * @return Number of pieces obstructing GamePiece, not including the moving piece
     */
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

    /**
     * Produce number of obstructions for the Y direction of piece
     * @param piece GamePiece moving
     * @param newX X coordinate of piece's movement
     * @param newY Y coordinate of piece's movement
     * @return Number of pieces obstructing GamePiece, not including the moving piece
     */
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

    /**
     * Produce number of obstructions for the diagonal up-right direction of piece
     * @param piece GamePiece moving
     * @param newX X coordinate of piece's movement
     * @param newY Y coordinate of piece's movement
     * @return Number of pieces obstructing GamePiece, not including the moving piece
     */
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

    /**
     * Produce number of obstructions for the King or Queen's movement
     * @param piece GamePiece moving
     * @param newX X coordinate of piece's movement
     * @param newY Y coordinate of piece's movement
     * @return Number of pieces obstructing King or Queen, not including itself
     */
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

    /**
     * Produce number of obstructions for the Bishop's movement
     * @param piece GamePiece moving
     * @param newX X coordinate of piece's movement
     * @param newY Y coordinate of piece's movement
     * @return Number of pieces obstructing Bishop, not including itself
     */
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

    /**
     * Produce number of obstructions for the Knight's movement
     * @param piece GamePiece moving downwards
     * @param newX X coordinate of piece's movement
     * @param newY Y coordinate of piece's movement
     * @return Number of pieces obstructing Knight, not including itself
     */
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

    /**
     * Produce the number of obstructions when Knight makes a larger X movement compared to Y
     * @param piece GamePiece moving
     * @param dX Change in piece's X direction
     * @param dY Change in piece's Y direction
     * @return Number of obstructions in Knight's movement path including itself
     */
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

    /**
     * Produce the number of obstructions when Knight makes a larger Y movement compared to X
     * @param piece GamePiece moving
     * @param dX Change in piece's X direction
     * @param dY Change in piece's Y direction
     * @return Number of obstructions in Knight's movement path including itself
     */
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

    /**
     * Produce number of obstructions for the Rook's movement
     * @param piece GamePiece moving
     * @param newX X coordinate of piece's movement
     * @param newY Y coordinate of piece's movement
     * @return Number of pieces obstructing Rook, not including itself
     */
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

    /**
     * Produce number of obstructions for Pawn's attack or movement pattern
     * @param piece GamePiece moving
     * @param newX X coordinate of piece's movement
     * @param newY Y coordinate of piece's movement
     * @param type If 0 produce movement pattern, if 1 produce attack pattern
     * @return Number of pieces obstructing Pawn, not including itself
     */
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

    /**
     * Returns the number of rows in this chess board
     * @return Number of rows in board
     */
    public int getRows() {
        return board.size();
    }

    /**
     * Returns the number of columns in this chess board at the specified row i
     * @param i Row of chess board from [0, 7] inclusive
     * @return Number of columns at the specified row number
     */
    public int getCols(int i) {
        return board.get(i).size();
    }

    /**
     * Returns the GamePiece at the specified position on the board
     * @param x X position of the board at coordinates [0, 7] inclusive
     * @param y Y position of the board at coordinates [0, 7] inclusive
     * @return GamePiece at specified X and Y position
     */
    public GamePiece getGamePiece(int x, int y) {
        return board.get(y).get(x);
    }

    /**
     * Returns chess board with all currently placed pieces
     * @return Board with all current GamePieces placed
     */
    public ArrayList<ArrayList<GamePiece>> getBoard() {
        return board;
    }
}
