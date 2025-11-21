package gamebox.swing.components;

import gamebox.swing.components.constants.ComponentsNumber;
import gamebox.swing.panel.constants.PanelString;

import javax.swing.*;
import java.awt.*;

public class TilePanel extends JPanel {
    private JLabel label;

    public TilePanel() {
        setLayout(new GridLayout(ComponentsNumber.TILE_SIZE, ComponentsNumber.TILE_SIZE));
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
        if (number == ComponentsNumber.EMPTY_TILE_NUMBER) {
            label.setText("");
        }
        label.setForeground(textColor);
        label.setFont(new Font(PanelString.FONT, Font.BOLD, ComponentsNumber.TILE_LABEL_FONT_SIZE));
        setBackground(backgroundColor);
        repaint();
    }
}
