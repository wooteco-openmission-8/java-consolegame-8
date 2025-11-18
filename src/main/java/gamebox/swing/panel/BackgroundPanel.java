package gamebox.swing.panel;

import gamebox.swing.components.RoundedButton;
import gamebox.swing.swing_util.SwingUtils;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private ImageIcon logoImage = new ImageIcon(getClass().getResource("/images/logo.png"));
    private final JLabel logo = new JLabel(logoImage);
    private final JLabel title = new JLabel("GameBox");
    private final JLabel selectGame = new JLabel("게임을 선택하세요.");
    private final RoundedButton homeButton = new RoundedButton("홈으로");

    public BackgroundPanel(){
        setBackground(Color.white);
        setLayout(null);
        setComponents();
        addComponents();
    }

    private void setComponents() {
        logo.setBounds(27, 10, 193, 60);

        title.setFont(new Font("맑은 고딕", Font.BOLD, 96));
        title.setBounds(244, 139, 512, 84);
        title.setHorizontalAlignment(JLabel.CENTER);

        selectGame.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        selectGame.setBounds(244, 233, 512, 84);
        selectGame.setHorizontalAlignment(JLabel.CENTER);

        homeButton.setFont(new Font("맑은 고딕", Font.BOLD, 32));
        homeButton.setBounds(40, 30, 211, 66);
        homeButton.setHorizontalAlignment(SwingConstants.CENTER);
        homeButton.setVisible(false);
    }

    private void addComponents(){
        add(logo);
        add(title);
        add(selectGame);
        add(homeButton);
    }

    public void addHomeButtonListener(ActionListener listener) {
        homeButton.addActionListener(listener);
    }

    public void showHomeButton(boolean show) {
        homeButton.setVisible(show);
        selectGame.setVisible(!show);
        SwingUtils.refresh(this);
    }

    public void removeContents() {
        logo.setVisible(false);
        title.setVisible(false);
        selectGame.setVisible(false);
    }

    public void setVisibleContents() {
        logo.setVisible(true);
        title.setVisible(true);
        selectGame.setVisible(true);
    }
}
