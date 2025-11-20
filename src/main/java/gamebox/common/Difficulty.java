package gamebox.game_samepic.game.entity;

public enum Difficulty {
    EASY(4, 4, 128),
    MEDIUM(6, 6, 96),
    HARD(8, 8, 64);

    private final int rows;
    private final int cols;
    private final int imageSize;

    Difficulty(int rows, int cols, int imageSize) {
        this.rows = rows;
        this.cols = cols;
        this.imageSize = imageSize;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getImageSize() {
        return imageSize;
    }
}