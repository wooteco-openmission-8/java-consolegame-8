package gamebox.swing.components;

import gamebox.game_samepic.picture.service.entity.Picture;
import gamebox.swing.components.constants.ComponentsString;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class ImageButton extends JButton {
    private final Picture picture;

    public ImageButton(Picture picture) {
        this.picture = picture;;
        setOpaque(false);
        initButton();
    }

    private void initButton() {
        if (picture != null) {
            putClientProperty(ComponentsString.IMAGE_ID_KEY, picture.getId());
            putClientProperty(ComponentsString.IMAGE_PATH_KEY, picture.getPath());
            putClientProperty(ComponentsString.IMAGE_GROUP_KEY, picture.getGroup());
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