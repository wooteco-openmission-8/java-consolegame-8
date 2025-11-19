package gamebox.swing.panel;

import gamebox.swing.components.RoundedButton;
import gamebox.swing.listener.GameListener;
import gamebox.swing.swing_util.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HeaderPanel extends JPanel {
    private static final String FONT = "";
    // HeaderPanel
    private static final int HEADER_PANEL_POSITION_X = 0;
    private static final int HEADER_PANEL_POSITION_Y = 0;
    private static final int HEADER_PANEL_WIDTH = 1000;
    private static final int HEADER_PANEL_HEIGHT = 100;
    // homeButton
    private static final int HOME_BUTTON_FONT_SIZE = 32;;
    private static final int HOME_BUTTON_POSITION_X = 739;
    private static final int HOME_BUTTON_POSITION_Y = 30;
    private static final int HOME_BUTTON_WIDTH = 211;
    private static final int HOME_BUTTON_HEIGHT = 66;
    // resetButton
    private static final int RESET_BUTTON_FONT_SIZE = 32;
    private static final int RESET_BUTTON_POSITION_X = 40;
    private static final int RESET_BUTTON_POSITION_Y = 40;
    private static final int RESET_BUTTON_WIDTH = 211;
    private static final int RESET_BUTTON_HEIGHT = 66;
    // game2048Title
    private static final int GAME_2048_TITLE_FONT_SIZE = 80;
    private static final int GAME_2048_TITLE_POSITION_X = 331;
    private static final int GAME_2048_TITLE_POSITION_Y = 20;
    private static final int GAME_2048_TITLE_WIDTH = 338;
    private static final int GAME_2048_TITLE_HEIGHT = 76;
    // selectDifficultyButton
    private static final int DIFFICULTY_BUTTON_FONT_SIZE = 32;
    private static final int DIFFICULTY_BUTTON_POSITION_X = 40;
    private static final int DIFFICULTY_BUTTON_POSITION_Y = 30;
    private static final int DIFFICULTY_BUTTON_WIDTH = 211;
    private static final int DIFFICULTY_BUTTON_HEIGHT = 66;
    // gameSamePicTitle
    private static final int GAME_SAME_PIC_TITLE_FONT_SIZE = 48;
    private static final int GAME_SAME_PIC_TITLE_POSITION_X = 331;
    private static final int GAME_SAME_PIC_TITLE_POSITION_Y = 20;
    private static final int GAME_SAME_PIC_TITLE_WIDTH = 338;
    private static final int GAME_SAME_PIC_TITLE_HEIGHT = 76;

    private final RoundedButton homeButton = new RoundedButton("홈으로");
    private final RoundedButton resetButton = new RoundedButton("새 게임");
    private final RoundedButton selectDifficultyButton = new RoundedButton("난이도 선택");
    private final JLabel game2048Title = new JLabel("2048");
    private final JLabel gameSamePicTitle = new JLabel("같은그림찾기");

    public HeaderPanel() {
        setBounds(
                HEADER_PANEL_POSITION_X, HEADER_PANEL_POSITION_Y,
                HEADER_PANEL_WIDTH, HEADER_PANEL_HEIGHT
        );
        setBackground(Color.white);
        setLayout(null);
        setInitialContents();
        addContents();
    }

    private void setInitialContents() {
        homeButton.setFont(new Font(FONT, Font.BOLD, HOME_BUTTON_FONT_SIZE));
        homeButton.setBounds(
                HOME_BUTTON_POSITION_X, HOME_BUTTON_POSITION_Y,
                HOME_BUTTON_WIDTH, HOME_BUTTON_HEIGHT
        );
        homeButton.setHorizontalAlignment(SwingConstants.CENTER);

        resetButton.setVisible(false);
        game2048Title.setVisible(false);
        gameSamePicTitle.setVisible(false);
        selectDifficultyButton.setVisible(false);
    }

    public void set2048Contents() {
        resetButton.setFont(new Font(FONT, Font.BOLD, RESET_BUTTON_FONT_SIZE));
        resetButton.setBounds(
                RESET_BUTTON_POSITION_X, RESET_BUTTON_POSITION_Y,
                RESET_BUTTON_WIDTH, RESET_BUTTON_HEIGHT
        );
        resetButton.setHorizontalAlignment(SwingConstants.CENTER);
        resetButton.setVisible(true);

        game2048Title.setFont(new Font(FONT, Font.BOLD, GAME_2048_TITLE_FONT_SIZE));
        game2048Title.setBounds(
                GAME_2048_TITLE_POSITION_X, GAME_2048_TITLE_POSITION_Y,
                GAME_2048_TITLE_WIDTH, GAME_2048_TITLE_HEIGHT
        );
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
        selectDifficultyButton.setFont(new Font(FONT, Font.BOLD, DIFFICULTY_BUTTON_FONT_SIZE));
        selectDifficultyButton.setBounds(
                DIFFICULTY_BUTTON_POSITION_X, DIFFICULTY_BUTTON_POSITION_Y,
                DIFFICULTY_BUTTON_WIDTH, DIFFICULTY_BUTTON_HEIGHT
        );
        selectDifficultyButton.setHorizontalAlignment(SwingConstants.CENTER);
        selectDifficultyButton.setVisible(true);

        gameSamePicTitle.setFont(new Font(FONT, Font.BOLD, GAME_SAME_PIC_TITLE_FONT_SIZE));
        gameSamePicTitle.setBounds(
                GAME_SAME_PIC_TITLE_POSITION_X, GAME_SAME_PIC_TITLE_POSITION_Y,
                GAME_SAME_PIC_TITLE_WIDTH, GAME_SAME_PIC_TITLE_HEIGHT
        );
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
