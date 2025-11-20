package gamebox.game_2048.service;

import java.util.Random;

public class RandomSpawner {
    private static final Random random = new Random();
    private static final double WEIGHT = 0.9;

    public static int spawn() {
        double probability = random.nextDouble();
        if (probability < WEIGHT) {
            return 2;
        }
        return 4;
    }
}
