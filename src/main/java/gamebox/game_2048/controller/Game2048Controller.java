package gamebox.game_2048.controller;

import gamebox.common.Game;
import gamebox.game_2048.entity.Direction;
import gamebox.game_2048.entity.GameStatus;
import gamebox.game_2048.entity.Tile;
import gamebox.game_2048.service.Game2048Service;
import gamebox.game_samepic.game.entity.Difficulty;

import java.awt.Point;

public class Game2048Controller implements Game {

    private static final String GAME_NAME = "2048";
    private Game2048Service gameService;

    @Override
    public void start(Difficulty difficulty) {
        gameService = new Game2048Service(difficulty.getRows(), difficulty.getCols());
    }

    public boolean moveUp() {
        return gameService.tileMove(Direction.UP);
    }

    public boolean moveDown() {
        return gameService.tileMove(Direction.DOWN);
    }

    public boolean moveLeft() {
        return gameService.tileMove(Direction.LEFT);
    }

    public boolean moveRight() {
        return gameService.tileMove(Direction.RIGHT);
    }

    public Point spawn() {
        return gameService.spawn();
    }

    public Tile getTile(int row, int col) {
        return gameService.getTile(row, col);
    }

    public GameStatus getGameStatus() {
        return gameService.getGameStatus();
    }

    @Override
    public String getName() {
        return GAME_NAME;
    }

}