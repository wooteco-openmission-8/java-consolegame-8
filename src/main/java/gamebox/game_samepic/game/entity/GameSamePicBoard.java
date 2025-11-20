package gamebox.game_samepic.game.entity;

import gamebox.util.exceptions.ErrorType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 같은 그림 찾기 게임의 보드를 나타내는 엔티티 카드 배치, 뒤집기 로직, 매칭 확인 등을 담당
 */
public class GameSamePicBoard {
    private final int rows;
    private final int cols;
    private final List<Card> cards = new ArrayList<>();
    private int moves;
    private int matches;
    private boolean waiting = false;
    private Card firstOpen;
    private Card secondOpen;

    /**
     * GameSamePicBoard 생성자
     *
     * @param rows 보드의 행 수
     * @param cols 보드의 열 수
     * @throws IllegalArgumentException 행×열이 홀수인 경우 (짝을 맞출 수 없음)
     */
    public GameSamePicBoard(int rows, int cols) {
        if ((rows * cols) % 2 != 0) {
            throw new IllegalArgumentException(ErrorType.INVALID_BOARD_SIZE.getMessage());
        }
        this.rows = rows;
        this.cols = cols;
    }

    /**
     * 그림 ID 목록으로 보드 초기화 각 그림 ID당 2장의 카드를 생성하고 섞어서 배치
     *
     * @param pictureIds 사용할 그림 ID 목록
     */
    public void initWithPictureIds(List<String> pictureIds) {
        for (String pid : pictureIds) {
            cards.add(new Card(pid));
            cards.add(new Card(pid));
        }
        Collections.shuffle(cards);
    }

    /**
     * 지정된 위치의 카드 뒤집기
     *
     * @param index 뒤집을 카드의 인덱스
     * @return Optional.empty: 뒤집기 불가, true: 매칭 성공, false: 매칭 실패
     */
    public Optional<Boolean> flip(int index) {
        if (waiting) {
            return Optional.empty();
        }

        Card target = get(index);
        if (target == null) {
            return Optional.empty();
        }

        boolean setOpenedCards = selectCards(target);
        if (!setOpenedCards) {
            return Optional.empty();
        }

        boolean matched = setMatchedCards();
        return Optional.of(matched);
    }

    /**
     * 카드 선택 처리
     *
     * @param target 선택한 카드
     * @return 두 번째 카드가 선택되었으면 true
     */
    private boolean selectCards(Card target) {
        if (target.isMatched() || target == firstOpen) {
            return false;
        }

        target.flip();
        if (firstOpen == null) {
            firstOpen = target;
            return false;
        }
        secondOpen = target;
        moves++;

        return true;
    }

    /**
     * 선택된 두 카드의 매칭 여부 확인 및 처리
     *
     * @return 매칭 성공 시 true
     */
    private boolean setMatchedCards() {
        boolean matched = firstOpen.samePicture(secondOpen);

        if (matched) {
            firstOpen.setMatched();
            secondOpen.setMatched();
            matches++;
            firstOpen = null;
            secondOpen = null;
        } else {
            waiting = true;
        }

        return matched;
    }

    /**
     * 인덱스로 카드 조회
     *
     * @param index 조회할 카드의 인덱스
     * @return Card 객체
     * @throws IndexOutOfBoundsException 유효하지 않은 인덱스인 경우
     */
    private Card get(int index) {
        if (index < 0 || index >= cards.size()) {
            throw new IndexOutOfBoundsException(ErrorType.INVALID_INDEX.getMessage() + index);
        }
        return cards.get(index);
    }

    /**
     * 게임 종료 여부 확인 모든 카드가 매칭되면 게임 종료
     *
     * @return 게임 종료 여부
     */
    public boolean gameOver() {
        return matches * 2 == cards.size();
    }

    /**
     * 이동 횟수 조회
     *
     * @return 현재까지의 이동 횟수
     */
    public int getMoves() {
        return moves;
    }

    /**
     * 모든 카드 목록 조회 (읽기 전용)
     *
     * @return 카드 목록
     */
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    /**
     * 보드의 행 수 조회
     *
     * @return 행 수
     */
    public int getRows() {
        return rows;
    }

    /**
     * 보드의 열 수 조회
     *
     * @return 열 수
     */
    public int getCols() {
        return cols;
    }

    /**
     * 매칭되지 않은 카드를 뒷면으로 되돌리기 두 카드가 매칭되지 않았을 때 호출
     */
    public void resetUnmatched() {
        if (firstOpen != null && !firstOpen.isMatched()) {
            firstOpen.flip();
        }
        if (secondOpen != null && !secondOpen.isMatched()) {
            secondOpen.flip();
        }
        firstOpen = null;
        secondOpen = null;
        waiting = false;
    }

    /**
     * 특정 위치의 카드 조회
     *
     * @param index 조회할 카드의 인덱스
     * @return Card 객체
     */
    public Card getCard(int index) {
        return cards.get(index);
    }
}