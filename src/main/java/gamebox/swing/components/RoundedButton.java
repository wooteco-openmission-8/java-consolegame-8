package gamebox.swing.components;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {
    private Color bgColor = GameColors.brown;      // 기본 배경색
    private Color textColor = GameColors.beige; // 기본 글자색

    public RoundedButton(String text) { super(text); decorate(); }

    public void setButtonColors(Color bg, Color text) {
        this.bgColor = bg;
        this.textColor = text;
        repaint();
    }

    protected void decorate() {
        setBorderPainted(false);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // 상태에 따라 밝기 변화
        Color colorToUse = bgColor;
        if (getModel().isArmed()) colorToUse = bgColor.darker();
        else if (getModel().isRollover()) colorToUse = bgColor.brighter();

        graphics.setColor(colorToUse);
        graphics.fillRoundRect(0, 0, width, height, 20, 20);

        // 글자
        graphics.setFont(getFont());
        graphics.setColor(textColor);

        FontMetrics fm = graphics.getFontMetrics();
        int textX = (width - fm.stringWidth(getText())) / 2;
        int textY = (height - fm.getHeight()) / 2 + fm.getAscent();

        graphics.drawString(getText(), textX, textY);

        graphics.dispose();
        super.paintComponent(g);
    }
}
