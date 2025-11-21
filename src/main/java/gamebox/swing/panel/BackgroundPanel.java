package gamebox.swing.panel;

import gamebox.swing.components.GameColors;
import gamebox.swing.components.RoundedButton;
import gamebox.swing.panel.constants.PanelNumber;
import gamebox.swing.panel.constants.PanelString;
import gamebox.swing.swing_util.SwingUtils;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private ImageIcon logoImage = new ImageIcon(getClass().getResource("/images/logo.png"));
    private final JLabel logo = new JLabel(logoImage);
    private final JLabel title = new JLabel(PanelString.APP_TITLE);
    private final JLabel selectGame = new JLabel(PanelString.SELECT_GAME_TITLE);

    public BackgroundPanel(){
        setBackground(GameColors.beige);
        setLayout(null);
        setComponents();
        addComponents();
    }

    private void setComponents() {
        setLogo();
        setTitle();
        setSelectGameTitle();
    }

    private void setLogo() {
        Image scaledImage = logoImage.getImage().getScaledInstance(
                PanelNumber.LOGO_WIDTH,
                PanelNumber.LOGO_HEIGHT,
                Image.SCALE_SMOOTH
        );

        logo.setIcon(new ImageIcon(scaledImage));

        logo.setBounds(
                PanelNumber.LOGO_POSITION_X, PanelNumber.LOGO_POSITION_Y,
                PanelNumber.LOGO_WIDTH, PanelNumber.LOGO_HEIGHT
        );
    }

    private void setTitle() {
        title.setFont(new Font(PanelString.FONT, Font.BOLD, PanelNumber.TITLE_FONT_SIZE));
        title.setForeground(GameColors.brown);
        title.setBounds(
                PanelNumber.TITLE_POSITION_X, PanelNumber.TITLE_POSITION_Y,
                PanelNumber.TITLE_WIDTH, PanelNumber.TITLE_HEIGHT
        );
        title.setHorizontalAlignment(JLabel.CENTER);
    }

    private void setSelectGameTitle() {
        selectGame.setFont(new Font(PanelString.FONT, Font.BOLD, PanelNumber.SELECT_GAME_TITLE_FONT_SIZE));
        selectGame.setForeground(GameColors.brown);
        selectGame.setBounds(
                PanelNumber.SELECT_GAME_TITLE_POSITION_X, PanelNumber.SELECT_GAME_TITLE_POSITION_Y,
                PanelNumber.SELECT_GAME_TITLE_WIDTH, PanelNumber.SELECT_GAME_TITLE_HEIGHT
        );
        selectGame.setHorizontalAlignment(JLabel.CENTER);
    }

    private void addComponents(){
        add(logo);
        add(title);
        add(selectGame);
    }
}
