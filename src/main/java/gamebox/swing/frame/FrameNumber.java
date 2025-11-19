package gamebox.swing.frame;

public enum FrameNumber {
    WINDOW_WIDTH(1000),
    WINDOW_HEIGHT(800);

    private int value;

    FrameNumber(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
