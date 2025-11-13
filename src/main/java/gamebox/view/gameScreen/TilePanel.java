package gamebox.view.gameScreen;

import javax.swing.*;
import java.awt.*;

public class TilePanel extends JPanel {
    private static final int TILE_SIZE = 1;

    private int number;
    private JLabel label;

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
}
