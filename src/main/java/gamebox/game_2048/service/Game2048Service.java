package gamebox.game_2048.service;

import gamebox.game_2048.entity.Direction;
import gamebox.game_2048.entity.GameStatus;
import gamebox.game_2048.entity.Tile;
import gamebox.game_2048.entity.Game2048Board;

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

    /**
     * 이동 (타일 이동 + 타일 생성)
     */
    public boolean move(Direction direction) {
        boolean changed = tileMove(direction);
        if (changed) {
            spawn();
        }
        return changed;
    }


    /**
     * 타일 이동
     */
    public boolean tileMove(Direction direction) {
        return switch (direction) {
            case UP -> moveVertical(false);
            case DOWN -> moveVertical(true);
            case LEFT -> moveHorizontal(false);
            case RIGHT -> moveHorizontal(true);
        };
    }

    /**
     * 타일 생성
     */
    public void spawn() {
        board.randomSpawn(1);
    }


    /**
     * 수직 이동
     */
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

    /**
     * 수평 이동
     */
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

    /**
     * 타일 병합
     */
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

    /**
     * 게임 종료 여부
     */
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

    /**
     * 테스트용 - 보드 상태 로드
     */
    public void loadBoard(int[][] numbers) {
        board.loadFrom(numbers);
    }

    /**
     * 테스트용 - 보드 상태 스냅샷
     */
    public int[][] snapshotBoard() {
        return board.snapshotNumbers();
    }
}