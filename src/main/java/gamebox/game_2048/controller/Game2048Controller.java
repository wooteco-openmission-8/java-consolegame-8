package gamebox.game_2048.controller;

import gamebox.common.Game;
import gamebox.game_2048.entity.Direction;
import gamebox.game_2048.entity.GameStatus;
import gamebox.game_2048.entity.Tile;
import gamebox.game_2048.service.Game2048Service;

public class Game2048Controller implements Game {

    private static final String GAME_NAME = "2048";
    private Game2048Service gameService;

    @Override
    public void start() {
        gameService = new Game2048Service(4, 4);
    }

    // 이동 메서드
    public boolean moveUp() {
        return gameService.move(Direction.UP);
    }

    public boolean moveDown() {
        return gameService.move(Direction.DOWN);
    }

    public boolean moveLeft() {
        return gameService.move(Direction.LEFT);
    }

    public boolean moveRight() {
        return gameService.move(Direction.RIGHT);
    }

    public Tile getTile(int row, int col) {
        return gameService.getTile(row, col);
    }

    public GameStatus getGameStatus() {
        return gameService.getGameStatus();
    }

    /**
     * 게임 이름 반환
     * @return "2048"
     */
    @Override
    public String getName() {
        return GAME_NAME;
    }

}