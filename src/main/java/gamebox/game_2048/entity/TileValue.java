package gamebox.game_2048.entity;

import java.awt.Color;

public enum TileValue {
    EMPTY(0, new Color(245, 245, 245), Color.DARK_GRAY),
    TWO(2, new Color(255, 240, 220), Color.DARK_GRAY),
    FOUR(4, new Color(255, 220, 180), Color.DARK_GRAY),
    EIGHT(8, new Color(255, 190, 200), Color.DARK_GRAY),
    SIXTEEN(16, new Color(255, 160, 180), Color.DARK_GRAY),
    THIRTY_TWO(32, new Color(230, 190, 255), Color.DARK_GRAY),
    SIXTY_FOUR(64, new Color(210, 170, 250), Color.DARK_GRAY),
    ONE_TWENTY_EIGHT(128, new Color(180, 210, 255), Color.DARK_GRAY),
    TWO_FIFTY_SIX(256, new Color(150, 190, 255), Color.DARK_GRAY),
    FIVE_TWELVE(512, new Color(175, 240, 220), Color.DARK_GRAY),
    ONE_ZERO_TWO_FOUR(1024, new Color(150, 230, 200), Color.DARK_GRAY),
    TWO_ZERO_FOUR_EIGHT(2048, new Color(140, 220, 255), Color.DARK_GRAY);

    private final int value;
    private final Color background;
    private final Color textColor;

    TileValue(int value, Color background, Color textColor) {
        this.value = value;
        this.background = background;
        this.textColor = textColor;
    }

    public Color getBackground() {
        return background;
    }

    public Color getTextColor() {
        return textColor;
    }

    public static TileValue fromValue(int value) {
        for (TileValue tv : values()) {
            if (tv.value == value) {
                return tv;
            }
        }
        return Default();
    }

    public static TileValue Default() {
        return fromValue(0);
    }
}
