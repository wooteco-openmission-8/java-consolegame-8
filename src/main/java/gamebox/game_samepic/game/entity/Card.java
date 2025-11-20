package gamebox.game_samepic.game.entity;

import java.util.Objects;

/**
 * 같은 그림 찾기 게임의 카드를 나타내는 엔티티
 */
public class Card {
    private final String pictureId;
    private boolean faceUp;
    private boolean matched;

    /**
     * Card 생성자
     *
     * @param pictureId 카드에 할당된 그림 ID
     */
    public Card(String pictureId) {
        this.pictureId = pictureId;
    }

    /**
     * 카드 뒤집기 (매칭된 카드는 뒤집을 수 없음)
     */
    public void flip() {
        if (!matched) {
            faceUp = !faceUp;
        }
    }

    /**
     * 다른 카드와 같은 그림인지 확인
     *
     * @param other 비교할 카드
     * @return 같은 그림이면 true
     */
    public boolean samePicture(Card other) {
        return Objects.equals(this.pictureId, other.pictureId);
    }

    /**
     * 카드를 매칭된 상태로 설정
     */
    public void setMatched() {
        matched = true;
    }

    /**
     * 카드의 그림 ID 조회
     *
     * @return 그림 ID
     */
    public String getPictureId() {
        return pictureId;
    }

    /**
     * 카드가 앞면인지 확인
     *
     * @return 앞면이면 true
     */
    public boolean isFaceUp() {
        return faceUp;
    }

    /**
     * 카드가 매칭되었는지 확인
     *
     * @return 매칭되었으면 true
     */
    public boolean isMatched() {
        return matched;
    }
}