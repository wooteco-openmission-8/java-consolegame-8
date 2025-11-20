package gamebox.swing.panel;

import gamebox.common.Difficulty;
import gamebox.game_2048.controller.Game2048Controller;
import gamebox.game_2048.entity.GameStatus;
import gamebox.game_2048.entity.Tile;
import gamebox.swing.components.Grid;
import gamebox.swing.components.RoundedButton;
import gamebox.swing.components.TilePanel;
import gamebox.swing.listener.GameListener;
import gamebox.swing.listener.constants.ListenerString;
import gamebox.swing.panel.constants.PanelNumber;
import gamebox.swing.panel.constants.PanelString;
import gamebox.swing.swing_util.SwingUtils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Game2048Panel extends JPanel {
    private RoundedButton resetButton;
    private JPanel gamePanel;
    private TilePanel[][] tilePanels;
    private final Game2048Controller controller;
    private boolean isProcessing = false;

    public Game2048Panel() {
        this.controller = new Game2048Controller();

        setLayout(null);
        setFocusable(false);
        setBackground(Color.white);

        setGamePanel();
        setGridWrapper();

        controller.start(Difficulty.EASY);  // Service 초기화
        updateBoard();
    }

    private void setGamePanel() {
        gamePanel = Grid.createGridPanel(PanelNumber.GRID_SIZE, PanelNumber.GRID_SIZE);
        gamePanel.setBackground(Color.BLACK);
        gamePanel.setPreferredSize(new Dimension(PanelNumber.GAME_PANEL_WIDTH, PanelNumber.GAME_PANEL_HEIGHT));

        addKeyListenerToPanel();

        gamePanel.setFocusable(true);
        gamePanel.setFocusTraversalKeysEnabled(false);

        SwingUtilities.invokeLater(() -> gamePanel.requestFocusInWindow());
    }

    private void addKeyListenerToPanel() {
        gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (isProcessing) return;

                boolean moved = switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> controller.moveUp();
                    case KeyEvent.VK_DOWN -> controller.moveDown();
                    case KeyEvent.VK_LEFT -> controller.moveLeft();
                    case KeyEvent.VK_RIGHT -> controller.moveRight();
                    default -> false;
                };

                if (moved) {
                    delayBoardUpdate();
                }
            }
        });
    }

    private void delayBoardUpdate() {
        isProcessing = true;
        drawTiles();
        Timer timer = new Timer(PanelNumber.BOARD_UPDATE_DELAY_MS, evt -> {
            Point newTilePos = controller.spawn();
            updateBoard();
            isProcessing = false;
            ((Timer) evt.getSource()).stop();
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void setGridWrapper() {
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(Color.WHITE);
        wrapper.setBounds(
                PanelNumber.WRAPPER_POSITION_X, PanelNumber.WRAPPER_POSITION_Y,
                PanelNumber.WRAPPER_WIDTH, PanelNumber.WRAPPER_HEIGHT
        );
        wrapper.add(gamePanel);
        add(wrapper);
    }

    private void addResetButtonListener() {
        resetPreviousListener();
        resetButton.setFocusable(false);
        resetButton.addActionListener(
                new GameListener(
                        this,
                        ListenerString.RESET_MESSAGE,
                        ListenerString.CONFIRM_MESSAGE,
                        () -> {
                            controller.start(Difficulty.EASY);
                            updateBoard();
                            SwingUtilities.invokeLater(() -> gamePanel.requestFocusInWindow());
                        }
                )
        );
    }

    private void resetPreviousListener() {
        for (var listener : resetButton.getActionListeners()) {
            resetButton.removeActionListener(listener);
        }
    }

    private void updateBoard() {
        drawTiles();
        checkGameStatus();
    }

    private void drawTiles() {
        gamePanel.removeAll();
        tilePanels = new TilePanel[PanelNumber.GRID_SIZE][PanelNumber.GRID_SIZE];

        createTile();

        SwingUtils.refresh(this);
    }

    private void createTile() {
        for (int r = 0; r < PanelNumber.GRID_SIZE; r++) {
            for (int c = 0; c < PanelNumber.GRID_SIZE; c++) {
                TilePanel tilePanel = new TilePanel();
                Tile t = controller.getTile(r, c);

                tilePanel.setTile(t.number(), t.getTextColor(), t.getBackgroundColor());

                tilePanels[r][c] = tilePanel;
                gamePanel.add(tilePanel);
            }
        }
    }

    private void checkGameStatus() {
        GameStatus status = controller.getGameStatus();
        if (status == GameStatus.WIN) {
            JOptionPane.showMessageDialog(this, PanelString.WIN_MESSAGE);
        } else if (status == GameStatus.GAME_OVER) {
            JOptionPane.showMessageDialog(this, PanelString.GAME_OVER_MESSAGE);
        }
    }

    public void setResetButton(RoundedButton resetButton) {
        this.resetButton = resetButton;
        addResetButtonListener();
    }
}