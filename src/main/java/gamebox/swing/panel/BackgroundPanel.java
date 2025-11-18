package gamebox.swing.panel;

import gamebox.swing.components.RoundedButton;
import gamebox.swing.swing_util.SwingUtils;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private ImageIcon logoImage;
    private final JLabel logo = new JLabel("");
    private final JLabel title = new JLabel("GameBox");
    private final JLabel selectGame = new JLabel("게임을 선택하세요.");
    private final RoundedButton homeButton = new RoundedButton("홈버튼");

    public BackgroundPanel(){
        setBackground(Color.white);
        setLayout(null);
        setLabels();
        selectGame.setVisible(true);
        homeButton.setVisible(false);
        alignTexts();
        addComponents();
    }

    private void setLabels() {
        logoImage = new ImageIcon(getClass().getResource("/images/logo.png"));
        logo.setIcon(logoImage);
        logo.setBounds(27, 10, 193, 60);

        title.setFont(new Font("맑은 고딕", Font.BOLD, 96));
        title.setBounds(244, 139, 512, 84);
        selectGame.setFont(new Font("맑은 고딕", Font.PLAIN, 40));
        selectGame.setBounds(244, 263, 512, 37);
    }

    private void alignTexts(){
        title.setHorizontalAlignment(JLabel.CENTER);
        selectGame.setHorizontalAlignment(JLabel.CENTER);
        homeButton.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void addComponents(){
        add(title);
        add(selectGame);
        add(homeButton);
        add(logo);
    }

    public void addHomeButtonListener(ActionListener listener) {
        homeButton.addActionListener(listener);
    }

    public void showHomeButton(boolean show) {
        homeButton.setVisible(show);
        selectGame.setVisible(!show);
        SwingUtils.refresh(this);
    }
}
