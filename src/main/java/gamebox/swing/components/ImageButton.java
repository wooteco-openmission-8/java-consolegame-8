package gamebox.swing.components;

import gamebox.game_samepic.picture.service.entity.Picture;
import gamebox.swing.components.constants.ComponentsNumber;
import gamebox.swing.components.constants.ComponentsString;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class ImageButton extends JButton {
    private final Picture picture;

    public ImageButton(Picture picture) {
        this.picture = picture;
        initButton();
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // 부드러운 곡선
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 배경색
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), ComponentsNumber.IMAGE_BUTTON_ARC, ComponentsNumber.IMAGE_BUTTON_ARC);

        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth(), getHeight(), ComponentsNumber.IMAGE_BUTTON_ARC, ComponentsNumber.IMAGE_BUTTON_ARC);

        g2.dispose();
    }

    private void initButton() {
        if (picture != null) {
            putClientProperty(ComponentsString.IMAGE_ID_KEY, picture.getId());
            putClientProperty(ComponentsString.IMAGE_PATH_KEY, picture.getPath());
            setBorderPainted(false);
            setContentAreaFilled(true);
            setFocusPainted(false);
            setOpaque(true);
        }
    }

    public ImageIcon getImageIcon(String imagePath, int size) throws IOException {
        URL resource = ImageButton.class.getResource(imagePath);
        if (resource == null) {
            return new ImageIcon("");
        }

        Image image = scaleImage(ImageIO.read(resource), size, size);
        return new ImageIcon(image);
    }

    public Image scaleImage(Image image, int newWidth, int newHeight) {
        return image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    }
}