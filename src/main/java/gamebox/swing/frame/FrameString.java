package gamebox.swing.frame;

public enum FrameString {
    APP_NAME("GameBox");

    private final String value;

    FrameString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
