package gamebox.game_2048.service;

import gamebox.game_2048.entity.Direction;
import gamebox.game_2048.entity.GameStatus;
import gamebox.game_2048.entity.Tile;
import gamebox.game_2048.entity.Game2048Board;

import java.awt.Point;
import java.util.*;

public class Game2048Service {

    private static final int BOARD_SIZE = 4;
    private static final int WIN_TILE_VALUE = 2048;
    private final Game2048Board board;

    public Game2048Service(int rows, int cols) {
        this.board = new Game2048Board(rows, cols);
    }

    public Tile getTile(int row, int col) {
        return board.get(row, col);
    }

    public boolean tileMove(Direction direction) {
        return switch (direction) {
            case UP -> moveVertical(false);
            case DOWN -> moveVertical(true);
            case LEFT -> moveHorizontal(false);
            case RIGHT -> moveHorizontal(true);
        };
    }

    public Point spawn() {
        return board.randomSpawn();
    }

    private boolean moveVertical(boolean reverse) {
        boolean changed = false;
        for (int c = 0; c < BOARD_SIZE; c++) {
            List<Tile> tiles = board.filterColumn(c);
            if (reverse) {
                Collections.reverse(tiles);
            }
            Tile[] merged = merge(tiles);
            if (reverse) {
                Collections.reverse(Arrays.asList(merged));
            }
            for (int r = 0; r < BOARD_SIZE; r++) {
                Tile newTile = (merged[r] != null) ? merged[r] : Tile.Default();
                if (board.get(r, c).getNumber() != newTile.getNumber()) {
                    board.set(r, c, newTile);
                    changed = true;
                }
            }
        }
        return changed;
    }

    private boolean moveHorizontal(boolean reverse) {
        boolean changed = false;
        for (int r = 0; r < BOARD_SIZE; r++) {
            List<Tile> tiles = board.filterRow(r);
            if (reverse) {
                Collections.reverse(tiles);
            }
            Tile[] merged = merge(tiles);
            if (reverse) {
                Collections.reverse(Arrays.asList(merged));
            }
            for (int c = 0; c < BOARD_SIZE; c++) {
                Tile newTile = (merged[c] != null) ? merged[c] : Tile.Default();
                if (board.get(r, c).getNumber() != newTile.getNumber()) {
                    board.set(r, c, newTile);
                    changed = true;
                }
            }
        }
        return changed;
    }

    private Tile[] merge(List<Tile> tiles) {
        Tile[] result = new Tile[BOARD_SIZE];
        int write = 0;
        for (int read = 0; read < tiles.size(); read++) {
            Tile currentTile = tiles.get(read);
            if (read + 1 < tiles.size() && currentTile.getNumber() == tiles.get(read + 1).getNumber()) {
                Tile merged = currentTile.merge(tiles.get(read + 1));
                if (merged.getNumber() == WIN_TILE_VALUE) {
                    board.setWin(true);
                }
                result[write++] = merged;
                read++;
                continue;
            }
            result[write++] = currentTile;
        }
        return result;
    }

    public boolean isGameOver() {
        return board.isFull() && !board.canMove();
    }

    public boolean isWin() {
        return board.isWin();
    }

    public GameStatus getGameStatus() {
        if (isWin()) return GameStatus.WIN;
        if (isGameOver()) return GameStatus.GAME_OVER;
        return GameStatus.RUNNING;
    }

    public void loadBoard(int[][] numbers) {
        board.loadFrom(numbers);
    }

    public int[][] snapshotBoard() {
        return board.snapshotNumbers();
    }
}