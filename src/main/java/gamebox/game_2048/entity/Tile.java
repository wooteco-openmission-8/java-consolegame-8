package gamebox.game_2048.entity;

import gamebox.game_2048.service.RandomSpawner;
import java.awt.Color;

/**
 * 2048 게임의 타일을 나타내는 클래스
 */
public record Tile(int number) {
    /**
     * 특정 값을 가진 타일을 생성합니다
     *
     * @param number 타일의 숫자 값 (0은 빈 칸, 2~2048 사이의 2의 거듭제곱)
     * @throws IllegalArgumentException 유효하지 않은 값인 경우
     */
    public Tile {
        if (!isValidTileValue(number)) {
            throw new IllegalArgumentException(
                    "타일 값은 0 또는 2~2048 사이의 2의 거듭제곱이어야 합니다: " + number
            );
        }
    }

    /**
     * 빈 타일(값이 0인 타일)을 생성합니다
     *
     * @return 빈 타일
     */
    public static Tile Default() {
        return new Tile(0);
    }

    /**
     * 유효한 타일 값인지 확인합니다
     *
     * @param value 확인할 값
     * @return 0이거나 2~2048 사이의 2의 거듭제곱이면 true
     */
    private boolean isValidTileValue(int value) {
        if (value == 0) {
            return true;
        }
        if (value < 2 || value > 2048) {
            return false;
        }
        return (value & (value - 1)) == 0;
    }

    /**
     * 타일의 숫자 값을 반환합니다
     *
     * @return 타일의 숫자 값
     */
    @Override
    public int number() {
        return number;
    }

    /**
     * 빈 칸인지 확인합니다
     *
     * @return 빈 칸이면 true
     */
    public boolean isEmpty() {
        return number == 0;
    }

    /**
     * 다른 타일과 병합하여 새로운 타일을 생성합니다
     *
     * @param other 병합할 타일
     * @return 병합된 새로운 타일 또는 병합 불가시 현재 타일
     */
    public Tile merge(Tile other) {
        if (this.number != other.number) {
            return this;
        }

        int newNumber = this.number + other.number;
        return new Tile(newNumber);
    }

    /**
     * 빈 칸에 새로운 값을 생성합니다 90% 확률로 2를 생성하고, 10% 확률로 4를 생성합니다
     *
     * @return 새로운 값이 생성된 타일 또는 빈 칸이 아닌 경우 현재 타일
     */
    public Tile spawn() {
        if (isEmpty()) {
            return new Tile(RandomSpawner.spawn());
        }
        return this;
    }

    /**
     * 타일의 배경색을 반환합니다
     *
     * @return 타일 값에 따른 배경색
     */
    public Color getBackgroundColor() {
        TileValue tileValue = TileValue.fromValue(this.number);
        return tileValue.getBackground();
    }

    /**
     * 타일의 텍스트 색상을 반환합니다
     *
     * @return 타일 값에 따른 텍스트 색상
     */
    public Color getTextColor() {
        TileValue tileValue = TileValue.fromValue(this.number);
        return tileValue.getTextColor();
    }
}