package gamebox.swing.panel;

import gamebox.swing.components.RoundedButton;
import gamebox.swing.listener.GameListener;
import gamebox.swing.panel.constants.PanelNumber;
import gamebox.swing.panel.constants.PanelString;
import gamebox.swing.swing_util.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HeaderPanel extends JPanel {
    private final RoundedButton homeButton = new RoundedButton(PanelString.HOME_BUTTON_TITLE);
    private final RoundedButton resetButton = new RoundedButton(PanelString.RESET_BUTTON_TITLE);
    private final RoundedButton selectDifficultyButton = new RoundedButton(PanelString.DIFFICULTY_BUTTON_TITLE);
    private final JLabel game2048Title = new JLabel(PanelString.GAME_2048_TITLE);
    private final JLabel gameSamePicTitle = new JLabel(PanelString.GAME_SAME_PIC_TITLE);

    public HeaderPanel() {
        setBounds(
                PanelNumber.HEADER_PANEL_POSITION_X, PanelNumber.HEADER_PANEL_POSITION_Y,
                PanelNumber.HEADER_PANEL_WIDTH, PanelNumber.HEADER_PANEL_HEIGHT
        );
        setBackground(Color.white);
        setLayout(null);
        setInitialContents();
        addContents();
    }

    private void setInitialContents() {
        homeButton.setFont(new Font(PanelString.FONT, Font.BOLD, PanelNumber.HOME_BUTTON_FONT_SIZE));
        homeButton.setBounds(
                PanelNumber.HOME_BUTTON_POSITION_X, PanelNumber.HOME_BUTTON_POSITION_Y,
                PanelNumber.HOME_BUTTON_WIDTH, PanelNumber.HOME_BUTTON_HEIGHT
        );
        homeButton.setHorizontalAlignment(SwingConstants.CENTER);

        resetButton.setVisible(false);
        game2048Title.setVisible(false);
        gameSamePicTitle.setVisible(false);
        selectDifficultyButton.setVisible(false);
    }

    private void addContents() {
        add(resetButton);
        add(homeButton);
        add(selectDifficultyButton);
        add(game2048Title);
        add(gameSamePicTitle);
    }

    private void setResetButton() {
        resetButton.setFont(new Font(PanelString.FONT, Font.BOLD, PanelNumber.RESET_BUTTON_FONT_SIZE));
        resetButton.setBounds(
                PanelNumber.RESET_BUTTON_POSITION_X, PanelNumber.RESET_BUTTON_POSITION_Y,
                PanelNumber.RESET_BUTTON_WIDTH, PanelNumber.RESET_BUTTON_HEIGHT
        );
        resetButton.setHorizontalAlignment(SwingConstants.CENTER);
        resetButton.setVisible(true);
    }

    private void setGame2048Title() {
        game2048Title.setFont(new Font(PanelString.FONT, Font.BOLD, PanelNumber.GAME_2048_TITLE_FONT_SIZE));
        game2048Title.setBounds(
                PanelNumber.GAME_2048_TITLE_POSITION_X, PanelNumber.GAME_2048_TITLE_POSITION_Y,
                PanelNumber.GAME_2048_TITLE_WIDTH, PanelNumber.GAME_2048_TITLE_HEIGHT
        );
        game2048Title.setHorizontalAlignment(SwingConstants.CENTER);
        game2048Title.setVisible(true);
    }

    private void setDifficultyButton() {
        selectDifficultyButton.setFont(new Font(PanelString.FONT, Font.BOLD, PanelNumber.DIFFICULTY_BUTTON_FONT_SIZE));
        selectDifficultyButton.setBounds(
                PanelNumber.DIFFICULTY_BUTTON_POSITION_X, PanelNumber.DIFFICULTY_BUTTON_POSITION_Y,
                PanelNumber.DIFFICULTY_BUTTON_WIDTH, PanelNumber.DIFFICULTY_BUTTON_HEIGHT
        );
        selectDifficultyButton.setHorizontalAlignment(SwingConstants.CENTER);
        selectDifficultyButton.setVisible(true);
    }

    private void setGameSamePicTitle() {
        gameSamePicTitle.setFont(new Font(PanelString.FONT, Font.BOLD, PanelNumber.GAME_SAME_PIC_TITLE_FONT_SIZE));
        gameSamePicTitle.setBounds(
                PanelNumber.GAME_SAME_PIC_TITLE_POSITION_X, PanelNumber.GAME_SAME_PIC_TITLE_POSITION_Y,
                PanelNumber.GAME_SAME_PIC_TITLE_WIDTH, PanelNumber.GAME_SAME_PIC_TITLE_HEIGHT
        );
        gameSamePicTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gameSamePicTitle.setVisible(true);
    }

    public void set2048Contents() {
        setResetButton();
        setGame2048Title();

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
        setDifficultyButton();
        setGameSamePicTitle();

        resetButton.setVisible(false);
        game2048Title.setVisible(false);
    }

    public void addHomeButtonListener(ActionListener listener) {
        homeButton.addActionListener(listener);
        selectDifficultyButton.addActionListener(listener);
    }

    public RoundedButton getResetButton() {
        return resetButton;
    }
}
