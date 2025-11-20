package gamebox.swing.panel;

import gamebox.game_samepic.game.controller.GameSamePicController;
import gamebox.game_samepic.game.entity.GameSamePicBoard;
import gamebox.game_samepic.game.entity.Card;
import gamebox.game_samepic.game.service.GameSamePicService;
import gamebox.game_samepic.picture.service.entity.Picture;
import gamebox.game_samepic.picture.service.repository.PictureRepository;
import gamebox.swing.components.DifficultySelectPanel;
import gamebox.game_samepic.game.entity.Difficulty;
import gamebox.swing.components.Grid;
import gamebox.swing.components.ImageButton;
import gamebox.swing.listener.GameListener;
import gamebox.swing.panel.constants.PanelNumber;
import gamebox.swing.panel.constants.PanelString;
import gamebox.swing.swing_util.SwingUtils;

import java.util.Optional;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static gamebox.swing.panel.constants.PanelString.CARD_GAME;
import static gamebox.swing.panel.constants.PanelString.GAME_SELECT;

public class GameSamePicPanel extends JPanel {
    private final Color defaultColor = new Color(245, 245, 245);

    private final GameSamePicController controller;
    private final JPanel containerPanel;
    private final CardLayout cardLayout;
    private final List<ImageButton> imageButtons = new ArrayList<>();
    private final HeaderPanel headerPanel;

    public GameSamePicPanel(HeaderPanel headerPanel) {
        PictureRepository repo = PictureRepository.getInstance();
        GameSamePicService service = new GameSamePicService(repo);
        this.controller = new GameSamePicController(service);

        this.headerPanel = headerPanel;
        setLayout(new BorderLayout());
        cardLayout = new CardLayout();
        containerPanel = new JPanel(cardLayout);
        add(containerPanel, BorderLayout.CENTER);

        showDifficultySelect();
    }

    private void showDifficultySelect() {
        DifficultySelectPanel selectPanel = new DifficultySelectPanel(this::startGame);
        containerPanel.add(selectPanel, PanelString.GAME_SELECT);
        cardLayout.show(containerPanel, PanelString.GAME_SELECT);
    }

    private void startGame(Difficulty difficulty) {
        controller.start(difficulty);
        headerPanel.setSamePicContents();

        buildGameScreen();
        cardLayout.show(containerPanel, PanelString.CARD_GAME);
    }

    private void buildGameScreen() {
        imageButtons.clear();

        JPanel gamePanel = new JPanel(null);
        gamePanel.setBackground(Color.white);

        drawBoard(gamePanel);

        containerPanel.removeAll();
        containerPanel.add(gamePanel, PanelString.CARD_GAME);

        SwingUtils.refresh(containerPanel);
    }

    private void drawBoard(JPanel gamePanel) {
        GameSamePicBoard gameSamePicBoard = controller.getBoard();

        JPanel gridPanel = Grid.createGridPanel(gameSamePicBoard.getRows(), gameSamePicBoard.getCols());
        gridPanel.setBackground(Color.black);
        gridPanel.setBounds(
                PanelNumber.GRID_POSITION_X, PanelNumber.GRID_POSITION_Y,
                PanelNumber.GRID_WIDTH, PanelNumber.GRID_HEIGHT
        );

        createPictureButtons(gameSamePicBoard, gridPanel);

        gamePanel.add(gridPanel);
    }

    private void createPictureButtons(GameSamePicBoard gameSamePicBoard, JPanel gridPanel) {
        List<Card> cards = gameSamePicBoard.getCards();

        for (int i = 0; i < cards.size(); i++) {
            ImageButton btn = createImageButton(cards.get(i), i);
            imageButtons.add(btn);
            gridPanel.add(btn);
        }
    }

    private ImageButton createImageButton(Card card, int index) {
        String pictureId = card.getPictureId();
        Picture picture = controller.getPicture(pictureId);
        ImageButton btn = new ImageButton(picture);

        btn.setPreferredSize(new Dimension(PanelNumber.IMAGE_BUTTON_SIZE, PanelNumber.IMAGE_BUTTON_SIZE));
        btn.setText(PanelString.HIDDEN_CARD_TEXT);
        btn.setFont(new Font(PanelString.FONT, Font.BOLD, PanelNumber.IMAGE_BUTTON_FONT_SIZE));
        btn.setBackground(defaultColor);
        btn.setIcon(null);

        btn.addActionListener(e -> handleCardClick(index));

        return btn;
    }

    private void handleCardClick(int index) {
        Optional<Boolean> result = controller.flip(index);

        if (result.isEmpty()) {
            updateCard(index);
            return;
        }

        updateCard(index);

        boolean isMatched = result.get();
        if (!isMatched) {
            resetMismatchedCards();
        }

        checkGameOver();
    }

    private void resetMismatchedCards() {
        List<Integer> flippedIndices = findFlippedCardIndices();

        Timer timer = new Timer(PanelNumber.FLIP_BACK_DELAY_MS, ev -> {
            controller.getBoard().resetUnmatched();
            for (int i : flippedIndices) {
                updateCard(i);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private List<Integer> findFlippedCardIndices() {
        List<Integer> indices = new ArrayList<>();
        List<Card> cards = controller.getBoard().getCards();

        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (card.isFaceUp() && !card.isMatched()) {
                indices.add(i);
            }
        }

        return indices;
    }

    private void updateCard(int index) {
        Card card = controller.getCard(index);
        ImageButton btn = imageButtons.get(index);

        if (card.isFaceUp() || card.isMatched()) {
            showCardFront(btn);
        } else {
            showCardBack(btn);
        }

        if (card.isMatched()) {
            btn.setEnabled(false);
            btn.setDisabledIcon(btn.getIcon());
        }
    }

    private void showCardFront(ImageButton btn) {
        String imagePath = (String) btn.getClientProperty(PanelString.IMAGE_PATH_KEY);
        try {
            int size = controller.getDifficulty().getImageSize();

            btn.setIcon(btn.getImageIcon(imagePath, size));
            btn.setText(PanelString.VISIBLE_CARD_TEXT);
            btn.setBackground(Color.WHITE);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void showCardBack(ImageButton btn) {
        btn.setIcon(null);
        btn.setText(PanelString.HIDDEN_CARD_TEXT);
        btn.setBackground(defaultColor);
    }

    private void checkGameOver() {
        if (controller.isGameOver()) {
            JOptionPane.showMessageDialog(
                    this,
                    PanelString.GAME_CLEAR_MESSAGE + controller.getMoves());

            resetPictures();
        }
    }

    private void resetPictures() {
        controller.removePictures(imageButtons.stream()
                .map(i -> i.getClientProperty(PanelString.IMAGE_GROUP_KEY).toString())
                .findAny().orElse("")
        );
    }
}
