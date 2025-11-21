package gamebox.game_2048.service;

import gamebox.game_2048.entity.Direction;
import gamebox.game_2048.entity.Game2048Board;
import gamebox.game_2048.entity.GameStatus;
import gamebox.game_2048.entity.Tile;
import gamebox.util.exceptions.ErrorType;

import java.awt.Point;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 2048 게임 로직을 처리하는 서비스 클래스
 */
public class Game2048Service {

    private static final int BOARD_SIZE = 4;
    private static final int WIN_TILE_VALUE = 2048;
    private final Game2048Board board;

    /**
     * 지정된 크기의 게임 서비스를 생성합니다
     *
     * @param rows 보드의 행 수
     * @param cols 보드의 열 수
     */
    public Game2048Service(int rows, int cols) {
        this.board = new Game2048Board(rows, cols);
    }

    /**
     * 지정된 위치의 타일을 반환합니다
     *
     * @param row 행 인덱스
     * @param col 열 인덱스
     * @return 해당 위치의 타일
     */
    public Tile getTile(int row, int col) {
        return board.get(row, col);
    }

    /**
     * 지정된 방향으로 타일을 이동시킵니다
     *
     * @param direction 이동 방향
     * @return 이동이 발생했으면 true
     */
    public boolean tileMove(Direction direction) {
        if (board.isWin()) {
            throw new IllegalArgumentException(ErrorType.GAME_ALREADY_OVER.getMessage());
        }
        return switch (direction) {
            case UP -> moveVertical(false);
            case DOWN -> moveVertical(true);
            case LEFT -> moveHorizontal(false);
            case RIGHT -> moveHorizontal(true);
        };
    }

    /**
     * 새로운 타일을 생성합니다
     *
     * @return 생성된 타일의 위치
     */
    public Point spawn() {
        return board.randomSpawn();
    }

    /**
     * 수직 방향으로 타일을 이동시킵니다
     *
     * @param reverse true면 아래 방향, false면 위 방향
     * @return 이동이 발생했으면 true
     */
    private boolean moveVertical(boolean reverse) {
        boolean changed = false;
        for (int c = 0; c < BOARD_SIZE; c++) {
            List<Tile> tiles = board.filterColumn(c);
            Tile[] merged = getTiles(reverse, tiles);
            for (int r = 0; r < BOARD_SIZE; r++) {
                Tile newTile = (merged[r] != null) ? merged[r] : Tile.Default();
                if (board.get(r, c).number() != newTile.number()) {
                    board.set(r, c, newTile);
                    changed = true;
                }
            }
        }
        return changed;
    }



    private Tile[] getTiles(boolean reverse, List<Tile> tiles) {
        if (reverse) {
            Collections.reverse(tiles);
        }
        Tile[] merged = merge(tiles);
        if (reverse) {
            Collections.reverse(Arrays.asList(merged));
        }
        return merged;
    }

    /**
     * 수평 방향으로 타일을 이동시킵니다
     *
     * @param reverse true면 오른쪽 방향, false면 왼쪽 방향
     * @return 이동이 발생했으면 true
     */
    private boolean moveHorizontal(boolean reverse) {
        boolean changed = false;
        for (int r = 0; r < BOARD_SIZE; r++) {
            List<Tile> tiles = board.filterRow(r);
            Tile[] merged = getTiles(reverse, tiles);
            for (int c = 0; c < BOARD_SIZE; c++) {
                Tile newTile = (merged[c] != null) ? merged[c] : Tile.Default();
                if (board.get(r, c).number() != newTile.number()) {
                    board.set(r, c, newTile);
                    changed = true;
                }
            }
        }
        return changed;
    }

    /**
     * 타일 리스트를 병합합니다
     *
     * @param tiles 병합할 타일 리스트
     * @return 병합된 타일 배열
     */
    private Tile[] merge(List<Tile> tiles) {
        Tile[] result = new Tile[BOARD_SIZE];
        int write = 0;
        for (int read = 0; read < tiles.size(); read++) {
            Tile currentTile = tiles.get(read);
            if (read + 1 < tiles.size() && currentTile.number() == tiles.get(read + 1).number()) {
                Tile merged = currentTile.merge(tiles.get(read + 1));
                checkWin(merged);
                result[write++] = merged;
                read++;
                continue;
            }
            result[write++] = currentTile;
        }
        return result;
    }

    /**
     * 게임이 끝났는지 확인합니다
     *
     * @return 게임이 끝났으면 true
     */
    private void checkWin(Tile merged) {
        if (merged.number() == WIN_TILE_VALUE) {
            board.setWin(true);
        }
    }

    public boolean isGameOver() {
        return board.isFull() && !board.canMove();
    }

    /**
     * 승리했는지 확인합니다
     *
     * @return 승리했으면 true
     */
    public boolean isWin() {
        return board.isWin();
    }

    /**
     * 현재 게임 상태를 반환합니다
     *
     * @return 게임 상태
     */
    public GameStatus getGameStatus() {
        if (isWin()) {
            return GameStatus.WIN;
        }
        if (isGameOver()) {
            return GameStatus.GAME_OVER;
        }
        return GameStatus.RUNNING;
    }

    /**
     * 보드에 특정 숫자 배열을 로드합니다 (테스트용)
     *
     * @param numbers 로드할 숫자 배열
     */
    public void loadBoard(int[][] numbers) {
        board.loadFrom(numbers);
    }

    /**
     * 현재 보드 상태를 숫자 배열로 반환합니다 (테스트용)
     *
     * @return 보드의 숫자 배열
     */
    public int[][] snapshotBoard() {
        return board.snapshotNumbers();
    }
}