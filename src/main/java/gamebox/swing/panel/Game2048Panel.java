package gamebox.swing.panel;

import gamebox.common.Difficulty;
import gamebox.game_2048.controller.Game2048Controller;
import gamebox.game_2048.entity.GameStatus;
import gamebox.game_2048.entity.Tile;
import gamebox.swing.components.Grid;
import gamebox.swing.components.RoundedButton;
import gamebox.swing.components.TilePanel;
import gamebox.swing.listener.GameListener;
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
    private static final String RESET_MESSAGE = "게임을 초기화하시겠습니까?";
    private static final String YES = "확인";
    private static final String WIN_MESSAGE = "You WIN!";
    private static final String GAME_OVER_MESSAGE = "Game Over!";
    private static final int GRID_SIZE = 4;
    private final Game2048Controller controller;
    private RoundedButton resetButton;
    private JPanel gamePanel;
    private TilePanel[][] tilePanels;
    private boolean isProcessing = false;

    /**
     * Game2048Panel(BorderLayout) -> resetPanel(NORTH) + gamePanel(CENTER) gamePanel(GridLayout) -> tilePanels(Grid)
     */
    public Game2048Panel() {
        this.controller = new Game2048Controller();

        setLayout(null);
        setFocusable(false);
        setBackground(Color.white);

        setGamePanel();

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(Color.WHITE);
        wrapper.setBounds(215, 35, 570, 570);
        wrapper.add(gamePanel);

        add(wrapper);

        controller.start(Difficulty.EASY);  // Service 초기화
        updateBoard();
    }

    public void setResetButton(RoundedButton resetButton) {
        this.resetButton = resetButton;
        addResetButtonListener();
    }

    private void addResetButtonListener() {
        for (var listener : resetButton.getActionListeners()) {
            resetButton.removeActionListener(listener);
        }

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
        gamePanel.setBackground(Color.BLACK);
        gamePanel.setPreferredSize(new Dimension(550, 550));

        addKeyListenerToPanel();

        gamePanel.setFocusable(true);
        gamePanel.setFocusTraversalKeysEnabled(false);

        SwingUtilities.invokeLater(() -> gamePanel.requestFocusInWindow());
    }

    private void addKeyListenerToPanel() {
        gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (isProcessing) {
                    return;
                }

                boolean moved = switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> controller.moveUp();
                    case KeyEvent.VK_DOWN -> controller.moveDown();
                    case KeyEvent.VK_LEFT -> controller.moveLeft();
                    case KeyEvent.VK_RIGHT -> controller.moveRight();
                    default -> false;
                };

                if (moved) {
                    isProcessing = true;
                    updateBoard();
                    Timer timer = new Timer(150, evt -> {
                        Point newTilePos = controller.spawn();
                        updateBoard();
                        isProcessing = false;
                        ((Timer) evt.getSource()).stop();
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
            }
        });
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

                tilePanel.setTile(t.number(), t.getTextColor(), t.getBackgroundColor());

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