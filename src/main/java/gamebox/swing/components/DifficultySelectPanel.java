package gamebox.swing.components;

import gamebox.game_samepic.game.entity.Difficulty;
import gamebox.util.exceptions.ErrorType;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class DifficultySelectPanel extends JPanel {
    private static final String FONT = "맑은 고딕";
    private static final String SELECT_DIFFICULTY = "난이도를 선택하세요.";
    private static final String EASY_BUTTON_TITLE = "쉬움 (4x4)";
    private static final String MEDIUM_BUTTON_TITLE = "보통 (6x6)";
    private static final String HARD_BUTTON_TITLE = "어려움 (8x8)";
    private static final int TITLE_FONT_SIZE = 40;
    private static final int DIFFICULTY_BUTTON_FONT_SIZE = 48;
    private static final int DIFFICULTY_BUTTON_WIDTH = 560;
    private static final int DIFFICULTY_BUTTON_HEIGHT = 134;

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
        JLabel titleLabel = new JLabel(SELECT_DIFFICULTY);
        titleLabel.setFont(new Font(FONT, Font.BOLD, TITLE_FONT_SIZE));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(177, 0, 645, 76);
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
        RoundedButton easyButton = createDifficultyButton(EASY_BUTTON_TITLE, Difficulty.EASY);
        RoundedButton mediumButton = createDifficultyButton(MEDIUM_BUTTON_TITLE, Difficulty.MEDIUM);
        RoundedButton hardButton = createDifficultyButton(HARD_BUTTON_TITLE, Difficulty.HARD);

        easyButton.setBounds(220, 20, 560, 134);
        mediumButton.setBounds(220, 174, 560, 134);
        hardButton.setBounds(220, 328, 560, 134);

        buttonPanel.add(easyButton);
        buttonPanel.add(mediumButton);
        buttonPanel.add(hardButton);
    }

    private RoundedButton createDifficultyButton(String text, Difficulty difficulty) {
        RoundedButton button = new RoundedButton(text);
        button.setFont(new Font(FONT, Font.PLAIN, DIFFICULTY_BUTTON_FONT_SIZE));
        button.addActionListener(e -> {
            if (onDifficultySelected == null) {
                throw new IllegalStateException(ErrorType.INVALID_SELECTED_DIFFICULTY.getMessage());
            }
            onDifficultySelected.accept(difficulty);
        });
        return button;
    }
}