package gamebox.game_2048.entity;

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
        randomSpawn(2);
    }

    public Tile get(int x, int y) {
        return board[x][y];
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

    /**
     * 열의 0이 아닌 타일들을 필터링
     */
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

    /**
     * 행의 0이 아닌 타일들을 필터링
     */
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

    public void randomSpawn(int n) {
        int spawnCount = 0;

        while (spawnCount < n) {
            if (spawnAtEmptyTile()) {
                spawnCount++;
            }

            if (spawnCount < n && isFull()) {
                break;
            }
        }
    }

    private boolean spawnAtEmptyTile() {
        int x = (int) (Math.random() * board.length);
        int y = (int) (Math.random() * board[0].length);

        if (get(x, y).getNumber() == EMPTY_TILE) {
            Tile tile = get(x, y);
            tile.spawn();
            board[x][y] = tile;
            return true;
        }
        return false;
    }

    public boolean isFull() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (get(row, col).getNumber() == EMPTY_TILE) {
                    return false;
                }
            }
        }
        return true;
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

    // 테스트용
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