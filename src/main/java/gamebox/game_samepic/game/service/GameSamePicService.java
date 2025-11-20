package gamebox.game_samepic.game.service;

import gamebox.game_samepic.game.entity.GameSamePicBoard;
import gamebox.game_samepic.picture.service.entity.Picture;
import gamebox.game_samepic.picture.service.repository.PictureRepository;
import gamebox.util.exceptions.ErrorType;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 같은 그림 찾기 게임의 비즈니스 로직을 처리하는 서비스 클래스
 */
public class GameSamePicService {
    private final PictureRepository pictureRepository;
    private GameSamePicBoard gameSamePicBoard;

    /**
     * GameSamePicService 생성자
     *
     * @param repo Picture 데이터를 관리하는 Repository
     */
    public GameSamePicService(PictureRepository repo) {
        this.pictureRepository = repo;
    }

    /**
     * 새로운 게임 시작
     *
     * @param rows 게임 보드의 행 수
     * @param cols 게임 보드의 열 수
     * @throws IllegalStateException 사용 가능한 그림이 부족한 경우
     */
    public void newGame(int rows, int cols) {
        int needed = (rows * cols) / 2;
        List<String> pictures = pictureRepository.findAllIds();
        Collections.shuffle(pictures);
        if (pictures.size() < needed) {
            throw new IllegalStateException(ErrorType.NOT_ENOUGH_PICTURES.getMessage());
        }

        gameSamePicBoard = new GameSamePicBoard(rows, cols);
        gameSamePicBoard.initWithPictureIds(pictures.subList(0, needed));
    }

    /**
     * 지정된 위치의 카드 뒤집기
     *
     * @param index 뒤집을 카드의 인덱스
     * @return 매칭 성공 여부 (Optional.empty: 뒤집기 불가, true: 매칭 성공, false: 매칭 실패)
     * @throws IllegalStateException 게임이 시작되지 않은 경우
     */
    public Optional<Boolean> flipCard(int index) {
        if (gameSamePicBoard == null) {
            throw new IllegalStateException(ErrorType.GAME_NOT_STARTED.getMessage());
        }
        return gameSamePicBoard.flip(index);
    }

    /**
     * 현재 게임 보드 조회
     *
     * @return 현재 게임의 보드 객체
     */
    public GameSamePicBoard getBoard() {
        return gameSamePicBoard;
    }

    /**
     * 게임 종료 여부 확인
     *
     * @return 게임 종료 여부
     */
    public boolean isGameOver() {
        if (gameSamePicBoard == null) {
            return false;
        }
        return gameSamePicBoard.gameOver();
    }

    /**
     * 현재까지의 이동 횟수 조회
     *
     * @return 이동 횟수
     */
    public int getMoves() {
        if (gameSamePicBoard == null) {
            return 0;
        }
        return gameSamePicBoard.getMoves();
    }

    /**
     * ID로 그림 정보 조회
     *
     * @param pictureId 조회할 그림의 ID
     * @return Picture 객체
     */
    public Picture getPicture(String pictureId) {
        return pictureRepository.findById(pictureId);
    }
}