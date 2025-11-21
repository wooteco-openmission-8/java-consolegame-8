package gamebox.swing.components;

import gamebox.common.Difficulty;
import gamebox.swing.components.constants.ComponentsNumber;
import gamebox.swing.components.constants.ComponentsString;
import gamebox.swing.panel.constants.PanelNumber;
import gamebox.swing.panel.constants.PanelString;
import gamebox.util.exceptions.ErrorType;
import java.awt.Color;
import java.awt.Font;
import java.util.function.Consumer;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DifficultySelectPanel extends JPanel {
    private final Consumer<Difficulty> onDifficultySelected;

    public DifficultySelectPanel(Consumer<Difficulty> onDifficultySelected) {
        this.onDifficultySelected = onDifficultySelected;
        setBackground(GameColors.beige);
        setLayout(null);
        initializeUI();
    }

    private void initializeUI() {
        setTitle();
        setButtonPanel();
    }

    private void setTitle() {
        JLabel titleLabel = new JLabel(ComponentsString.SELECT_DIFFICULTY);
        titleLabel.setFont(new Font(PanelString.FONT, Font.BOLD, ComponentsNumber.TITLE_FONT_SIZE));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(
                ComponentsNumber.DIFFICULTY_POSITION_X, ComponentsNumber.DIFFICULTY_POSITION_Y,
                ComponentsNumber.DIFFICULTY_WIDTH, ComponentsNumber.DIFFICULTY_HEIGHT
        );
        add(titleLabel);
    }

    private void setButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(
                ComponentsNumber.BUTTON_PANEL_POSITION_X, ComponentsNumber.BUTTON_PANEL_POSITION_Y,
                ComponentsNumber.BUTTON_PANEL_WIDTH, ComponentsNumber.BUTTON_PANEL_HEIGHT
        );
        buttonPanel.setBackground(GameColors.beige);
        buttonPanel.setLayout(null);

        createButtons(buttonPanel);

        add(buttonPanel);
    }

    private void createButtons(JPanel buttonPanel) {
        setEasyButton(buttonPanel);
        setMediumButton(buttonPanel);
        setHardButton(buttonPanel);
    }

    private void setEasyButton(JPanel buttonPanel) {
        RoundedButton easyButton = createDifficultyButton(ComponentsString.EASY_BUTTON_TITLE, Difficulty.EASY);
        easyButton.setBounds(
                ComponentsNumber.EASY_BUTTON_POSITION_X, ComponentsNumber.EASY_BUTTON_POSITION_Y,
                ComponentsNumber.EASY_BUTTON_WIDTH, ComponentsNumber.EASY_BUTTON_HEIGHT
        );
        buttonPanel.add(easyButton);
    }

    private void setMediumButton(JPanel buttonPanel) {
        RoundedButton mediumButton = createDifficultyButton(ComponentsString.MEDIUM_BUTTON_TITLE, Difficulty.MEDIUM);
        mediumButton.setBounds(
                ComponentsNumber.MEDIUM_BUTTON_POSITION_X, ComponentsNumber.MEDIUM_BUTTON_POSITION_Y,
                ComponentsNumber.MEDIUM_BUTTON_WIDTH, ComponentsNumber.MEDIUM_BUTTON_HEIGHT
        );
        buttonPanel.add(mediumButton);
    }

    private void setHardButton(JPanel buttonPanel) {
        RoundedButton hardButton = createDifficultyButton(ComponentsString.HARD_BUTTON_TITLE, Difficulty.HARD);
        hardButton.setBounds(
                ComponentsNumber.HARD_BUTTON_POSITION_X, ComponentsNumber.HARD_BUTTON_POSITION_Y,
                ComponentsNumber.HARD_BUTTON_WIDTH, ComponentsNumber.HARD_BUTTON_HEIGHT
        );
        buttonPanel.add(hardButton);
    }

    private RoundedButton createDifficultyButton(String text, Difficulty difficulty) {
        RoundedButton button = new RoundedButton(text);
        button.setFont(new Font(PanelString.FONT, Font.PLAIN, ComponentsNumber.DIFFICULTY_BUTTON_FONT_SIZE));
        button.addActionListener(e -> {
            if (onDifficultySelected == null) {
                throw new IllegalStateException(ErrorType.INVALID_SELECTED_DIFFICULTY.getMessage());
            }
            onDifficultySelected.accept(difficulty);
        });
        return button;
    }
}
