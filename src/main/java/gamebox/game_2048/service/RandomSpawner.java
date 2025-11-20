package gamebox.game_2048.service;

import java.util.Random;

/**
 * 랜덤 타일 생성을 담당하는 클래스
 */
public class RandomSpawner {
    private static final Random random = new Random();
    private static final double WEIGHT = 0.9;

    /**
     * 새로운 타일 값을 생성합니다 90% 확률로 2를 반환하고, 10% 확률로 4를 반환합니다
     *
     * @return 생성된 타일 값 (2 또는 4)
     */
    public static int spawn() {
        double probability = random.nextDouble();
        if (probability < WEIGHT) {
            return 2;
        }
        return 4;
    }
}