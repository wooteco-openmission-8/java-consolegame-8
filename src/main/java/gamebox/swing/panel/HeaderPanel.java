package gamebox.swing.panel;

import gamebox.swing.components.RoundedButton;
import gamebox.swing.listener.GameListener;
import gamebox.swing.swing_util.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HeaderPanel extends JPanel {
    private final RoundedButton homeButton = new RoundedButton("홈으로");
    private final RoundedButton resetButton = new RoundedButton("새 게임");
    private final RoundedButton selectDifficultyButton = new RoundedButton("난이도 선택");
    private final JLabel game2048Title = new JLabel("2048");
    private final JLabel gameSamePicTitle = new JLabel("같은그림찾기");

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
        gameSamePicTitle.setVisible(false);
        selectDifficultyButton.setVisible(false);
    }

    public void set2048Contents() {
        resetButton.setFont(new Font("맑은 고딕", Font.BOLD, 32));
        resetButton.setBounds(40, 30, 211, 66);
        resetButton.setHorizontalAlignment(SwingConstants.CENTER);
        resetButton.setVisible(true);

        game2048Title.setFont(new Font("맑은 고딕", Font.BOLD, 80));
        game2048Title.setBounds(331, 20, 338, 76);
        game2048Title.setHorizontalAlignment(SwingConstants.CENTER);
        game2048Title.setVisible(true);

        gameSamePicTitle.setVisible(false);
        selectDifficultyButton.setVisible(false);
    }

    public void setSelectDifficultyContents() {
        resetButton.setVisible(false);
        game2048Title.setVisible(false);
        gameSamePicTitle.setVisible(false);
        selectDifficultyButton.setVisible(false);
    }

    public void setSamePicContents() {
        selectDifficultyButton.setFont(new Font("맑은 고딕", Font.BOLD, 32));
        selectDifficultyButton.setBounds(40, 30, 211, 66);
        selectDifficultyButton.setHorizontalAlignment(SwingConstants.CENTER);
        selectDifficultyButton.setVisible(true);

        gameSamePicTitle.setFont(new Font("맑은 고딕", Font.BOLD, 48));
        gameSamePicTitle.setBounds(331, 20, 338, 76);
        gameSamePicTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gameSamePicTitle.setVisible(true);

        resetButton.setVisible(false);
        game2048Title.setVisible(false);
    }

    private void addContents() {
        add(resetButton);
        add(homeButton);
        add(selectDifficultyButton);

        add(game2048Title);
        add(gameSamePicTitle);
    }

    public void addHomeButtonListener(ActionListener listener) {
        homeButton.addActionListener(listener);
        selectDifficultyButton.addActionListener(listener);
    }

    public RoundedButton getResetButton() {
        return resetButton;
    }
}
