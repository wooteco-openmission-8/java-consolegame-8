package gamebox.swing.panel;

import gamebox.game_2048.controller.Game2048Controller;
import gamebox.game_2048.entity.Tile;
import gamebox.game_2048.entity.GameStatus;
import gamebox.game_samepic.game.entity.Difficulty;
import gamebox.swing.components.Grid;
import gamebox.swing.components.TilePanel;
import gamebox.swing.listener.Game2048KeyListener;
import gamebox.swing.listener.GameListener;
import gamebox.swing.swing_util.SwingUtils;

import javax.swing.*;
import java.awt.*;

public class Game2048Panel extends JPanel {
    private static final String RESET_BUTTON_NAME = "Reset";
    private static final String RESET_MESSAGE = "게임을 초기화하시겠습니까?";
    private static final String YES = "확인";
    private static final String WIN_MESSAGE = "You WIN!";
    private static final String GAME_OVER_MESSAGE = "You WIN!";
    private static final int GRID_SIZE = 4;

    private final JPanel resetPanel = new JPanel();
    private JPanel gamePanel;
    private final JButton resetButton = new JButton(RESET_BUTTON_NAME);
    private TilePanel[][] tilePanels;
    private final Game2048Controller controller;

    /**
     * Game2048Panel(BorderLayout) -> resetPanel(NORTH) + gamePanel(CENTER)
     * gamePanel(GridLayout) -> tilePanels(Grid)
     */
    public Game2048Panel() {
        this.controller = new Game2048Controller();

        setLayout(new BorderLayout());
        setFocusable(false);

        setResetPanel();
        setGamePanel();

        add(resetPanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);

        controller.start(Difficulty.EASY);  // Service 초기화
        updateBoard();
    }

    private void setResetPanel() {
        resetPanel.setBackground(Color.WHITE);
        createResetButton();
        resetPanel.add(resetButton);
    }

    private void createResetButton() {
        resetButton.setFocusable(false);
        resetButton.addActionListener(
                new GameListener(
                        this,
                        RESET_MESSAGE,
                        YES,
                        () -> {
                            controller.start(Difficulty.EASY);
                            updateBoard();
                            SwingUtilities.invokeLater(() -> gamePanel.requestFocusInWindow());
                        }
                )
        );
    }

    private void setGamePanel() {
        gamePanel = Grid.createGridPanel(GRID_SIZE, GRID_SIZE);
        gamePanel.setBackground(Color.WHITE);

        addKeyListenerToPanel();

        gamePanel.setFocusable(true);
        gamePanel.setFocusTraversalKeysEnabled(false);

        SwingUtilities.invokeLater(() -> gamePanel.requestFocusInWindow());
    }

    private void addKeyListenerToPanel() {
        gamePanel.addKeyListener(new Game2048KeyListener(
                controller,
                this::updateBoard
        ));
    }

    private void updateBoard() {
        drawTiles();
        checkGameStatus();
    }

    private void drawTiles() {
        gamePanel.removeAll();
        tilePanels = new TilePanel[GRID_SIZE][GRID_SIZE];

        createTile();

        SwingUtils.refresh(this);
    }

    private void createTile() {
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                TilePanel tilePanel = new TilePanel();
                Tile t = controller.getTile(r, c);

                tilePanel.setTile(t.getNumber(), t.getTextColor(), t.getBackgroundColor());

                tilePanels[r][c] = tilePanel;
                gamePanel.add(tilePanel);
            }
        }
    }

    private void checkGameStatus() {
        GameStatus status = controller.getGameStatus();
        if (status == GameStatus.WIN) {
            JOptionPane.showMessageDialog(this, WIN_MESSAGE);
        } else if (status == GameStatus.GAME_OVER) {
            JOptionPane.showMessageDialog(this, GAME_OVER_MESSAGE);
        }
    }
}
