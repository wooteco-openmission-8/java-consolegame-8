package gamebox.view.gameScreen.game2048;

import javax.swing.*;
import java.awt.*;

public class TilePanel extends JPanel {
    private static final int TILE_SIZE = 1;

    private int number;
    private JLabel label;
    private Color color;

    public TilePanel() {
        setLayout(new GridLayout(TILE_SIZE, TILE_SIZE));
    }

    public void setTile(int number) {
        this.number = number;
        label = new JLabel(String.valueOf(this.number));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        add(label);
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(this.color);
    }
}
