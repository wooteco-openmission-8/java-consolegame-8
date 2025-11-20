package gamebox.common;

import gamebox.game_samepic.game.entity.Difficulty;

/**
 * 게임 인터페이스
 */
public interface Game {
    /**
     * 게임을 시작합니다
     *
     * @param difficulty 게임 난이도
     */
    void start(Difficulty difficulty);
}