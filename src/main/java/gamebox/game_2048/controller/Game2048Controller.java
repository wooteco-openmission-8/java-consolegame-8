package gamebox.game_2048.controller;

import gamebox.common.Difficulty;
import gamebox.common.Game;
import gamebox.game_2048.entity.Direction;
import gamebox.game_2048.entity.GameStatus;
import gamebox.game_2048.entity.Tile;
import gamebox.game_2048.service.Game2048Service;
import java.awt.Point;

/**
 * 2048 게임 컨트롤러
 */
public class Game2048Controller implements Game {

    private Game2048Service gameService;

    /**
     * 게임을 시작합니다
     *
     * @param difficulty 게임 난이도
     */
    @Override
    public void start(Difficulty difficulty) {
        gameService = new Game2048Service(difficulty.getRows(), difficulty.getCols());
    }

    /**
     * 타일을 위로 이동시킵니다
     *
     * @return 이동이 발생했으면 true
     */
    public boolean moveUp() {
        return gameService.tileMove(Direction.UP);
    }

    /**
     * 타일을 아래로 이동시킵니다
     *
     * @return 이동이 발생했으면 true
     */
    public boolean moveDown() {
        return gameService.tileMove(Direction.DOWN);
    }

    /**
     * 타일을 왼쪽으로 이동시킵니다
     *
     * @return 이동이 발생했으면 true
     */
    public boolean moveLeft() {
        return gameService.tileMove(Direction.LEFT);
    }

    /**
     * 타일을 오른쪽으로 이동시킵니다
     *
     * @return 이동이 발생했으면 true
     */
    public boolean moveRight() {
        return gameService.tileMove(Direction.RIGHT);
    }

    /**
     * 새로운 타일을 생성합니다
     *
     * @return 생성된 타일의 위치
     */
    public Point spawn() {
        return gameService.spawn();
    }

    /**
     * 지정된 위치의 타일을 반환합니다
     *
     * @param row 행 인덱스
     * @param col 열 인덱스
     * @return 해당 위치의 타일
     */
    public Tile getTile(int row, int col) {
        return gameService.getTile(row, col);
    }

    /**
     * 현재 게임 상태를 반환합니다
     *
     * @return 게임 상태
     */
    public GameStatus getGameStatus() {
        return gameService.getGameStatus();
    }
}