package gamebox.swing.components;

import gamebox.game_samepic.game.entity.Difficulty;
import gamebox.swing.components.constants.ComponentsNumber;
import gamebox.swing.components.constants.ComponentsString;
import gamebox.swing.panel.constants.PanelNumber;
import gamebox.swing.panel.constants.PanelString;
import gamebox.util.exceptions.ErrorType;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class DifficultySelectPanel extends JPanel {
    private final Consumer<Difficulty> onDifficultySelected;

    public DifficultySelectPanel(Consumer<Difficulty> onDifficultySelected) {
        this.onDifficultySelected = onDifficultySelected;
        setBackground(Color.white);
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
        titleLabel.setBounds(177, 20, 645, 76);
        add(titleLabel);
    }

    private void setButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 80, 1000, 500);
        buttonPanel.setBackground(Color.white);
        buttonPanel.setLayout(null);

        createButtons(buttonPanel);

        add(buttonPanel);
    }

    private void createButtons(JPanel buttonPanel) {
        RoundedButton easyButton = createDifficultyButton(ComponentsString.EASY_BUTTON_TITLE, Difficulty.EASY);
        RoundedButton mediumButton = createDifficultyButton(ComponentsString.MEDIUM_BUTTON_TITLE, Difficulty.MEDIUM);
        RoundedButton hardButton = createDifficultyButton(ComponentsString.HARD_BUTTON_TITLE, Difficulty.HARD);

        easyButton.setBounds(220, 40, 560, 134);
        mediumButton.setBounds(220, 194, 560, 134);
        hardButton.setBounds(220, 348, 560, 134);

        buttonPanel.add(easyButton);
        buttonPanel.add(mediumButton);
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