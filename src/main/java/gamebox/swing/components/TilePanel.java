package gamebox.swing.components;

import javax.swing.*;
import java.awt.*;

public class TilePanel extends JPanel {
    private static final int TILE_SIZE = 1;
    private static final int EMPTY_TILE_NUMBER = 0;

    private JLabel label;

    public TilePanel() {
        setLayout(new GridLayout(TILE_SIZE, TILE_SIZE));
        setOpaque(false);
        label = new JLabel("", SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        add(label);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int arc = 14; // 둥근 정도
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
    }

    public void setTile(int number, Color textColor, Color backgroundColor) {
        label.setText(String.valueOf(number));
        if (number == EMPTY_TILE_NUMBER) {
            label.setText("");
        }
        label.setForeground(textColor);
        label.setFont(new Font("맑은 고딕", Font.BOLD, 50));
        setBackground(backgroundColor);
        repaint();
    }
}
