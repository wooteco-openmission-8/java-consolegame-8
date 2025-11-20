package gamebox.swing.components;

import gamebox.game_samepic.picture.service.entity.Picture;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton {
    private static final String IMAGE_PATH_KEY = "imagePath";
    private static final String IMAGE_ID_KEY = "imageId";
    private static final String IMAGE_GROUP_KEY = "imageGroup";

    private final Picture picture;

    public ImageButton(Picture picture) {
        this.picture = picture;
        setOpaque(false);
        initButton();
    }

    private void initButton() {
        if (picture != null) {
            putClientProperty(IMAGE_ID_KEY, picture.getId());
            putClientProperty(IMAGE_PATH_KEY, picture.getPath());
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