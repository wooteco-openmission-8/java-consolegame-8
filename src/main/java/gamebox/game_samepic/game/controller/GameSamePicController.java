package gamebox.game_samepic.game.controller;

import gamebox.common.Difficulty;
import gamebox.common.Game;
import gamebox.game_samepic.game.entity.Card;
import gamebox.game_samepic.game.entity.GameSamePicBoard;
import gamebox.game_samepic.game.service.GameSamePicService;
import gamebox.game_samepic.picture.service.entity.Picture;
import java.util.Optional;

/**
 * 같은 그림 찾기 게임의 컨트롤러 클래스 Game 인터페이스를 구현하여 게임의 전반적인 흐름을 제어
 */
public class GameSamePicController implements Game {
    private final GameSamePicService gameSamePicService;
    private Difficulty difficulty;

    /**
     * GameSamePicController 생성자
     *
     * @param gameSamePicService 게임 로직을 처리하는 서비스
     */
    public GameSamePicController(GameSamePicService gameSamePicService) {
        this.gameSamePicService = gameSamePicService;
    }

    /**
     * 게임 시작
     *
     * @param difficulty 게임 난이도
     */
    @Override
    public void start(Difficulty difficulty) {
        this.difficulty = difficulty;
        int rows = difficulty.getRows();
        int cols = difficulty.getCols();
        gameSamePicService.newGame(rows, cols);
    }

    /**
     * 현재 게임 난이도 조회
     *
     * @return 현재 게임의 난이도
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * 그림 정보 조회
     *
     * @param pictureId 조회할 그림의 ID
     * @return Picture 객체
     */
    public Picture getPicture(String pictureId) {
        return gameSamePicService.getPicture(pictureId);
    }

    /**
     * 카드 뒤집기
     *
     * @param index 뒤집을 카드의 인덱스
     * @return 매칭 결과 (Optional.empty: 뒤집기 불가, true: 매칭 성공, false: 매칭 실패)
     */
    public Optional<Boolean> flip(int index) {
        return gameSamePicService.flipCard(index);
    }

    /**
     * 게임 보드 조회
     *
     * @return 현재 게임 보드
     */
    public GameSamePicBoard getBoard() {
        return gameSamePicService.getBoard();
    }

    /**
     * 특정 위치의 카드 조회
     *
     * @param index 조회할 카드의 인덱스
     * @return Card 객체
     */
    public Card getCard(int index) {
        return getBoard().getCard(index);
    }

    /**
     * 게임 종료 여부 확인
     *
     * @return 게임 종료 여부
     */
    public boolean isGameOver() {
        return gameSamePicService.isGameOver();
    }

    /**
     * 이동 횟수 조회
     *
     * @return 현재까지의 이동 횟수
     */
    public int getMoves() {
        return gameSamePicService.getMoves();
    }
}