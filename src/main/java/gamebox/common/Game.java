package gamebox.common;

import gamebox.game_samepic.game.entity.Difficulty;

public interface Game {
    void start(Difficulty difficulty);
    String getName();
}