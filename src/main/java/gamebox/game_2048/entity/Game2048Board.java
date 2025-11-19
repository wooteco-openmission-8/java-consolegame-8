package gamebox.game_2048.entity;

import java.awt.Point;
import java.util.*;

public class Game2048Board {
    private static final int EMPTY_TILE = 0;
    private final Tile[][] board;
    private boolean win = false;

    public Game2048Board(int rows, int cols) {
        this.board = new Tile[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board[r][c] = new Tile(EMPTY_TILE);
            }
        }
        randomSpawn();
        randomSpawn();
    }

    public Tile get(int x, int y) {
        Tile tile = board[x][y];
        if (tile == null || tile.isEmpty()) {
            board[x][y] = new Tile(EMPTY_TILE);
            return board[x][y];
        }
        return tile;
    }

    public void set(int x, int y, Tile tile) {
        board[x][y] = tile;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public List<Tile> filterColumn(int columnIndex) {
        List<Tile> tiles = new ArrayList<>();
        for (int r = 0; r < board.length; r++) {
            Tile tile = get(r, columnIndex);
            if (tile.getNumber() > EMPTY_TILE) {
                tiles.add(tile);
            }
        }
        return tiles;
    }

    public List<Tile> filterRow(int rowIndex) {
        List<Tile> tiles = new ArrayList<>();
        for (int c = 0; c < board[0].length; c++) {
            Tile tile = get(rowIndex, c);
            if (tile.getNumber() > EMPTY_TILE) {
                tiles.add(tile);
            }
        }
        return tiles;
    }

    public Point randomSpawn() {
        List<Point> emptyTiles = getEmptyTiles();

        Point selected = emptyTiles.get((int) (Math.random() * emptyTiles.size()));

        Tile tile = get(selected.x, selected.y);
        board[selected.x][selected.y] = tile.spawn();

        return selected;
    }

    public boolean isFull() {
        return getEmptyTiles().isEmpty();
    }

    private List<Point> getEmptyTiles() {
        List<Point> emptyTiles = new ArrayList<>();
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (get(r, c).getNumber() == EMPTY_TILE) {
                    emptyTiles.add(new Point(r, c));
                }
            }
        }
        return emptyTiles;
    }

    public boolean canMove() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                Tile current = get(r, c);
                if (c + 1 < board[0].length && current.getNumber() == get(r, c + 1).getNumber()) {
                    return true;
                }
                if (r + 1 < board.length && current.getNumber() == get(r + 1, c).getNumber()) {
                    return true;
                }
            }
        }
        return false;
    }
    public void loadFrom(int[][] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[0].length; j++) {
                board[i][j] = new Tile(numbers[i][j]);
            }
        }
    }
    public int[][] snapshotNumbers() {
        int[][] out = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                out[i][j] = get(i, j).getNumber();
            }
        }
        return out;
    }
}