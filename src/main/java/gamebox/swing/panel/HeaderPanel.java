package gamebox.swing.panel;

import gamebox.swing.components.RoundedButton;
import gamebox.swing.swing_util.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HeaderPanel extends JPanel {
    private final RoundedButton homeButton = new RoundedButton("홈으로");
    private final RoundedButton resetButton = new RoundedButton("새 게임");
    private final JLabel game2048Title = new JLabel("2048");

    public HeaderPanel() {
        setBounds(0, 0, 1000, 100);
        setBackground(Color.white);
        setLayout(null);
        setInitialContents();
        addContents();
    }

    private void setInitialContents() {
        homeButton.setFont(new Font("맑은 고딕", Font.BOLD, 32));
        homeButton.setBounds(739, 30, 211, 66);
        homeButton.setHorizontalAlignment(SwingConstants.CENTER);

        resetButton.setVisible(false);
        game2048Title.setVisible(false);
    }

    public void set2048Contents() {
        resetButton.setFont(new Font("맑은 고딕", Font.BOLD, 32));
        resetButton.setBounds(40, 30, 211, 66);
        resetButton.setHorizontalAlignment(SwingConstants.CENTER);

        game2048Title.setFont(new Font("맑은 고딕", Font.BOLD, 80));
        game2048Title.setBounds(331, 20, 338, 76);
        game2048Title.setHorizontalAlignment(SwingConstants.CENTER);

        resetButton.setVisible(true);
        game2048Title.setVisible(true);
    }

    private void addContents() {
        add(resetButton);
        add(homeButton);
        add(game2048Title);
    }

    public void addHomeButtonListener(ActionListener listener) {
        homeButton.addActionListener(listener);
    }

    public RoundedButton getResetButton() {
        return resetButton;
    }
}
