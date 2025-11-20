package gamebox.game_2048.entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * 2048 게임 보드를 관리하는 클래스
 */
public class Game2048Board {
    private static final int EMPTY_TILE = 0;
    private final Tile[][] board;
    private boolean win = false;

    /**
     * 지정된 크기의 게임 보드를 생성합니다
     *
     * @param rows 보드의 행 수
     * @param cols 보드의 열 수
     */
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

    /**
     * 지정된 위치의 타일을 반환합니다
     *
     * @param x 행 인덱스
     * @param y 열 인덱스
     * @return 해당 위치의 타일
     */
    public Tile get(int x, int y) {
        Tile tile = board[x][y];
        if (tile == null || tile.isEmpty()) {
            board[x][y] = new Tile(EMPTY_TILE);
            return board[x][y];
        }
        return tile;
    }

    /**
     * 지정된 위치에 타일을 설정합니다
     *
     * @param x    행 인덱스
     * @param y    열 인덱스
     * @param tile 설정할 타일
     */
    public void set(int x, int y, Tile tile) {
        board[x][y] = tile;
    }

    /**
     * 승리 상태를 반환합니다
     *
     * @return 승리 여부
     */
    public boolean isWin() {
        return win;
    }

    /**
     * 승리 상태를 설정합니다
     *
     * @param win 승리 여부
     */
    public void setWin(boolean win) {
        this.win = win;
    }

    /**
     * 지정된 열에서 비어있지 않은 타일들을 필터링합니다
     *
     * @param columnIndex 열 인덱스
     * @return 비어있지 않은 타일 목록
     */
    public List<Tile> filterColumn(int columnIndex) {
        List<Tile> tiles = new ArrayList<>();
        for (int r = 0; r < board.length; r++) {
            Tile tile = get(r, columnIndex);
            if (tile.number() > EMPTY_TILE) {
                tiles.add(tile);
            }
        }
        return tiles;
    }

    /**
     * 지정된 행에서 비어있지 않은 타일들을 필터링합니다
     *
     * @param rowIndex 행 인덱스
     * @return 비어있지 않은 타일 목록
     */
    public List<Tile> filterRow(int rowIndex) {
        List<Tile> tiles = new ArrayList<>();
        for (int c = 0; c < board[0].length; c++) {
            Tile tile = get(rowIndex, c);
            if (tile.number() > EMPTY_TILE) {
                tiles.add(tile);
            }
        }
        return tiles;
    }

    /**
     * 랜덤 위치에 새로운 타일을 생성합니다
     *
     * @return 생성된 타일의 위치
     */
    public Point randomSpawn() {
        List<Point> emptyTiles = getEmptyTiles();

        Point selected = emptyTiles.get((int) (Math.random() * emptyTiles.size()));

        Tile tile = get(selected.x, selected.y);
        board[selected.x][selected.y] = tile.spawn();

        return selected;
    }

    /**
     * 보드가 가득 찼는지 확인합니다
     *
     * @return 보드가 가득 찼으면 true
     */
    public boolean isFull() {
        return getEmptyTiles().isEmpty();
    }

    /**
     * 빈 타일들의 위치를 반환합니다
     *
     * @return 빈 타일들의 위치 목록
     */
    private List<Point> getEmptyTiles() {
        List<Point> emptyTiles = new ArrayList<>();
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (get(r, c).number() == EMPTY_TILE) {
                    emptyTiles.add(new Point(r, c));
                }
            }
        }
        return emptyTiles;
    }

    /**
     * 이동 가능한 타일이 있는지 확인합니다
     *
     * @return 이동 가능하면 true
     */
    public boolean canMove() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                Tile current = get(r, c);
                if (c + 1 < board[0].length && current.number() == get(r, c + 1).number()) {
                    return true;
                }
                if (r + 1 < board.length && current.number() == get(r + 1, c).number()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 2차원 배열로부터 보드를 로드합니다
     *
     * @param numbers 로드할 숫자 배열
     */
    public void loadFrom(int[][] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[0].length; j++) {
                board[i][j] = new Tile(numbers[i][j]);
            }
        }
    }

    /**
     * 현재 보드 상태를 2차원 배열로 반환합니다
     *
     * @return 보드의 숫자 배열
     */
    public int[][] snapshotNumbers() {
        int[][] out = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                out[i][j] = get(i, j).number();
            }
        }
        return out;
    }
}